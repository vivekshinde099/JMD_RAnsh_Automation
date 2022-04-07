package com.jio.ranshjmd.Bottomnavigationbar;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.text.TextBlock;
import com.google.android.gms.vision.text.TextRecognizer;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.List;

import com.jio.ranshjmd.ApiConstructor.TickerConstructor;
import com.jio.ranshjmd.Checking_issue;
import com.jio.ranshjmd.Common.Datacontainer;
import com.jio.ranshjmd.PlaceHolderAPI.Ticker_PlaceHolderAPI;
import com.jio.ranshjmd.R;
import com.jio.ranshjmd.RequestAPI.APIConnection;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_OK;

public class HomeFragment extends Fragment {

    private int PICK_IMAGE_REQUEST = 2;
    LinearLayout upload_imagebutton;
    Bitmap bitmap;
    private TextView uploadresulttext,uploadtext,outageupdatetext;
    ImageView uploadimage;

    TextView let_s_quick,upload_scre,label,no_worries_,donthavescreenshot;
    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_home,container,false );
      //  languagetextconverter();
        donthavescreenshot = (TextView) view.findViewById(R.id.dont_have);
        donthavescreenshot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container,new MainCategoryIssuePage()).commit();
            }
        });

        upload_imagebutton = (LinearLayout) view.findViewById(R.id.upload_imagebutton);
        upload_imagebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().detach(HomeFragment.this).attach(HomeFragment.this).commit();
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_PICK);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
            }
        });
        uploadimage = (ImageView) view.findViewById(R.id.uploadimage);
        uploadresulttext = (TextView) view.findViewById(R.id.uploadresulttext);
        uploadtext = (TextView) view.findViewById(R.id.uploadtext);

        let_s_quick = (TextView) view.findViewById(R.id.let_s_quick);
        upload_scre = (TextView) view.findViewById(R.id.upload_scre);
        label = (TextView) view.findViewById(R.id.label);
        no_worries_ = (TextView) view.findViewById(R.id.no_worries_);
        outageupdatetext = (TextView) view.findViewById(R.id.outageupdate_text);
        outageupdatetext.setSelected(true);
        outageupdatetext.setEllipsize(TextUtils.TruncateAt.MARQUEE);

        Ticker_PlaceHolderAPI ticker_PlaceHolderAPI = APIConnection.getretrofitinstance().create(Ticker_PlaceHolderAPI.class);
        Call<List<TickerConstructor>> call = ticker_PlaceHolderAPI.getPosts();
        call.enqueue(new Callback<List<TickerConstructor>>() {
            @Override
            public void onResponse(Call<List<TickerConstructor>> call, Response<List<TickerConstructor>> response) {

                List<TickerConstructor> posts = response.body();
                for (TickerConstructor post : posts) {

                    String content = post.getOutage();
                    outageupdatetext.setText(content);
                    Log.d("Data:", content);
                }
            }

            @Override
            public void onFailure(Call<List<TickerConstructor>> call, Throwable t) {
         }
        });
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {

            Uri uri = data.getData();

            try {
                bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), uri);
                // Log.d(TAG, String.valueOf(bitmap));

                uploadimage.setImageBitmap(bitmap);
                if (uploadimage != null) {
//                    upload_imagebutton.setVisibility(View.GONE);
                    Toast.makeText(HomeFragment.this.getActivity(), "Image Updated Successfully", Toast.LENGTH_SHORT).show();
//                    getsolution.setVisibility(View.VISIBLE);
                    getTextFromImage();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void getTextFromImage() {

        TextRecognizer TEXTRECOGNIZER = new TextRecognizer.Builder(HomeFragment.this.getActivity()).build();

        if (!TEXTRECOGNIZER.isOperational()) {
            new AlertDialog.Builder(HomeFragment.this.getActivity())
                    .setMessage("Text recognizer could not be set up on your device :(").show();
        } else {
            Frame frame = new Frame.Builder().setBitmap(bitmap).build();

            SparseArray<TextBlock> items = TEXTRECOGNIZER.detect(frame);

            StringBuilder ab = new StringBuilder();

            for(int l=0;l<items.size();++l){

                TextBlock myitem = items.valueAt(l);
                ab.append(myitem.getValue());
                ab.append("\n");

            }
            uploadresulttext.setText(ab.toString());
            uploadresulttext.getText().toString();
            String uploadissuename = uploadresulttext.getText().toString().toLowerCase().replace("information","#").replace("ok","#").replace("\n"," ");

            String string = uploadissuename;
            if (string.contains("#")) {
                String[] parts = string.split("#");
                String part1 = parts[0]; // 004-
                String part2 = parts[1]; // 034556
                uploadtext.setText(part2);
                String abc = uploadtext.getText().toString();
                Intent intent = new Intent(HomeFragment.this.getActivity(), Checking_issue.class);
                intent.putExtra("A", abc);
                startActivity(intent);
            }else{
                Intent intent = new Intent(HomeFragment.this.getActivity(), Checking_issue.class);
                intent.putExtra("A", uploadissuename);
                startActivity(intent);
            }

        }
    }

    public void languagetextconverter(){
        Datacontainer.model(null,let_s_quick,HomeFragment.this.getActivity(),"");
        Datacontainer.model(null,upload_scre,HomeFragment.this.getActivity(),"");
        Datacontainer.model(null,label,HomeFragment.this.getActivity(),"");
        Datacontainer.model(null,no_worries_,HomeFragment.this.getActivity(),"");
        Datacontainer.model(null,donthavescreenshot,HomeFragment.this.getActivity(),"");
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            // Refresh your fragment here
            Fragment frg = null;
            frg = getFragmentManager().findFragmentByTag("Your_Fragment_TAG");
            final FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.detach(frg);
            ft.attach(frg);
            ft.commit();
            Log.i("IsRefresh", "Yes");
        }
    }


    @Override
    public void onStart() {
      //  languagetextconverter();
        super.onStart();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


}
