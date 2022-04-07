package com.jio.ranshjmd;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.text.TextBlock;
import com.google.android.gms.vision.text.TextRecognizer;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.IOException;

import com.jio.ranshjmd.Bottomnavigationbar.MainHomePage;

public class Upload_imagebutton_activity extends AppCompatActivity {

    private Button upload_image_button,getsolution;
    private int PICK_IMAGE_REQUEST = 2;
    Bitmap bitmap;
    private TextView uploadresulttext,uploadtext;
    ImageView uploadimage;

    Uri uri,imageuri;

    Handler handler;
    Runnable r;
    TextView read;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_imagebutton);
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        getSupportActionBar().hide();

//        Datacontainer.checkfirebase(Upload_imagebutton_activity.this);
//        handler = new Handler();
//        r = new Runnable() {
//
//            @Override
//            public void run() {
//                // TODO Auto-generated method stub
//                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Upload_imagebutton_activity.this);
//                alertDialogBuilder.setCancelable(false);
//                alertDialogBuilder.setMessage(Html.fromHtml("Session Expired <br> Idle Timeout Error!!"))
//                        .setPositiveButton("ok", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                finishAffinity();
//                            }
//                        });
//
//                AlertDialog alert = alertDialogBuilder.create();
//                if(!isFinishing()) {
//                    alert.show();
//                }
//            }
//        };
//        startHandler();

//        Datacontainer.checkfirebase(Upload_imagebutton_activity.this);
        //new FunctiontocheckwithFB(Upload_imagebutton_activity.this);

        getsolution = (Button) findViewById(R.id.getsolution);
        uploadresulttext = (TextView) findViewById(R.id.uploadresulttext);
        upload_image_button = (Button) findViewById(R.id.upload_imagebutton);
        uploadtext = (TextView) findViewById(R.id.uploadtext);
        upload_image_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
// Show only images, no videos or anything else
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_PICK);
// Always show the chooser (if there are multiple options available)
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
//                CropImage.startPickImageActivity(Upload_imagebutton_activity.this);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,  Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {

            Uri uri = data.getData();

            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                // Log.d(TAG, String.valueOf(bitmap));
                uploadimage = (ImageView) findViewById(R.id.uploadimage);
                uploadimage.setImageBitmap(bitmap);
                if(uploadimage != null){
                    upload_image_button.setVisibility(View.GONE);
                    Toast.makeText(this,"Image Updated Successfully",Toast.LENGTH_SHORT).show();
//                    getsolution.setVisibility(View.VISIBLE);
                    getTextFromImage();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                if (requestCode == CropImage.PICK_IMAGE_CHOOSER_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
//                    imageuri = CropImage.getPickImageResultUri(this, data);
//                    if (CropImage.isReadExternalStoragePermissionsRequired(this, imageuri)) {
//                        uri = imageuri;
//
//                        requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 0);
//
//                    }
//                    else {
//                        startCrop(imageuri);
//                    }
//                }
//                if(requestCode==CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE){
//                    CropImage.ActivityResult result=CropImage.getActivityResult(data);
//                    if(requestCode==203)
//                    {
//                        imageuri=result.getUri();
//                        uploadimage = (ImageView) findViewById(R.id.uploadimage);
//                        uploadimage.setImageURI(result.getUri());
//                        Toast.makeText(this,"Image Updated Successfully",Toast.LENGTH_SHORT).show();
//                        upload_image_button.setVisibility(View.GONE);
//                        getsolution.setVisibility(View.VISIBLE);
//                    }
//                }
//            }

//        switch (requestCode){
//
//            case 2:
//                if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
//
//                    Uri uri = data.getData();
//
//                    try {
//                        bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
//                        // Log.d(TAG, String.valueOf(bitmap));
//
//                        uploadimage = (ImageView) findViewById(R.id.uploadimage);
//                        uploadimage.setImageBitmap(bitmap);
//                        if(uploadimage != null){
//                            upload_image_button.setVisibility(View.GONE);
//                            Snackbar snackbar = Snackbar.make(findViewById(R.id.idLayout),"Uploaded",Snackbar.LENGTH_LONG);
//                            snackbar.show();
//                            getsolution.setVisibility(View.VISIBLE);
//                        }break;
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }break;
        }

    private void startCrop(Uri imageuri) {
        CropImage.activity(imageuri)
                .setGuidelines(CropImageView.Guidelines.ON)
                .setMultiTouchEnabled(true)
                .start(this);

    }

    public void getTextFromImage() {

        TextRecognizer TEXTRECOGNIZER = new TextRecognizer.Builder(this).build();

        if (!TEXTRECOGNIZER.isOperational()) {
            new AlertDialog.Builder(this)
                    .setMessage("Text recognizer could not be set up on your device :(").show();
        } else {
            Frame frame = new Frame.Builder().setBitmap(bitmap).build();

            SparseArray<TextBlock> items = TEXTRECOGNIZER.detect(frame);

            StringBuilder ab = new StringBuilder();

            for(int l=0;l<items.size();++l){

                TextBlock myitem = items.valueAt(l);
                ab.append(myitem.getValue());
                ab.append("\n");
//            try {
//                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageuri);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            Frame frame = new Frame.Builder().setBitmap(bitmap).build();
//
//            SparseArray<TextBlock> items = TEXTRECOGNIZER.detect(frame);
//
//            StringBuilder ab = new StringBuilder();
//
//            for (int l = 0; l < items.size(); ++l) {
//
//                TextBlock myitem = items.valueAt(l);
//                ab.append(myitem.getValue());
////                ab.append("\n");
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
                    Intent intent = new Intent(Upload_imagebutton_activity.this, Checking_issue.class);
                    intent.putExtra("A", abc);
                    intent.putExtra("Close","Close");
                    startActivity(intent);
                }else{
                    Intent intent = new Intent(Upload_imagebutton_activity.this, Checking_issue.class);
                    intent.putExtra("A", uploadissuename);
                    intent.putExtra("Close","Close");
                    startActivity(intent);
                }

        }
    }

    public void open_main_layout(View view) {
        Intent intent = new Intent(Upload_imagebutton_activity.this,Burgerlayout.class);
        startActivity(intent);

    }

    public void checkPermission(String permission, int requestCode)
    {
        if (ContextCompat.checkSelfPermission(this, permission)
                == PackageManager.PERMISSION_DENIED) {

            // Requesting the permission
            ActivityCompat.requestPermissions(this,new String[] { permission }, requestCode);
        }
        else {
            //Toast.makeText(this,"Permission already granted",Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Upload_imagebutton_activity.this, MainHomePage.class);
        startActivity(intent);
    }
}