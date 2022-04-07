package com.jio.ranshjmd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.provider.Settings;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import com.jio.ranshjmd.Adapters.CommentAddattachmentadapter;
import com.jio.ranshjmd.ApiConstructor.AutoResTicketConstructor;
import com.jio.ranshjmd.ApiConstructor.CreateTicketConstructor;
import com.jio.ranshjmd.Common.Datacontainer;
import com.jio.ranshjmd.Models.GetAttachmentPath;
import com.jio.ranshjmd.Models.Myattachmentlist;
import com.jio.ranshjmd.PlaceHolderAPI.AutoResTicket_PlaceHolderAPI;
import com.jio.ranshjmd.PlaceHolderAPI.CreateTicket_PlaceHolderAPI;
import com.jio.ranshjmd.RequestAPI.APIConnection;
import com.jio.ranshjmd.RequestAPI.CreateTicketPostdata;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Ticket_status extends AppCompatActivity {

    TextView s_btno;
    TextView s_sdno,s_title,s_status,cloneticket,cloneremarks,clonestatus;
    EditText s_remarks;
    DatabaseReference databaseReference,databaseReference1;
   // Handler handler;
    Runnable r;
    RelativeLayout linearLayout;
    String savesdno;
    ArrayList<Myattachmentlist> rarraylist;
    ArrayList<GetAttachmentPath> pathlist;
    private ImageView Imagefirst;
    private static int RESULT_LOAD_IMAGE1 = 1;
    String picturePath1;
    RecyclerView.Adapter radapter;
    RecyclerView recyclerView;
    LinearLayout sendmessagebtnlayout;
    TextView reach_out_t;
    String attachment = "";
    String updatestatus = "";
    View ticktview;
    TextView Send_message;
    long maxidattachment;
    long logcount;
    Handler handler = new Handler();
    private Object CreateTicketConstructor;
    List<com.jio.ranshjmd.ApiConstructor.CreateTicketConstructor> createTicketConstructors;
    private CreateTicket_PlaceHolderAPI createTicket_placeHolderAPI;
    String account_history_btno,ar_history_btno;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_status);
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_round_arrow_back_ios_24);
        linearLayout = (RelativeLayout) findViewById(R.id.formlayouttoshow);
        Datacontainer.sharedprefrence(getApplicationContext());

        s_btno = findViewById(R.id.status_btnumber);
        s_sdno = findViewById(R.id.status_sdnumber);
        s_title = findViewById(R.id.status_title);
        s_status = findViewById(R.id.status_status);
        s_remarks = findViewById(R.id.status_remark);
        cloneticket = findViewById(R.id.clone_sdnumber);
        cloneremarks = findViewById(R.id.clone_remarks);
        clonestatus = findViewById(R.id.clone_status);
        ticktview = (View)findViewById(R.id.ticketview);
        Send_message = findViewById(R.id.send_us_message);
        reach_out_t = findViewById(R.id.reach_out_t);

        sendmessagebtnlayout = (LinearLayout)findViewById(R.id.sendmessage);
        databaseReference1 = FirebaseDatabase.getInstance().getReference().child("Bot Data");

        rarraylist = new ArrayList<>();
        pathlist = new ArrayList<>();
        ar_history_btno = getIntent().getExtras().getString("Account_Res_BTNO");
        account_history_btno = getIntent().getExtras().getString("Account_history_BTNO");

          if (account_history_btno != null) {

                getSupportActionBar().setTitle("BT"+account_history_btno);
                s_btno.setText(account_history_btno);
                String number = s_btno.getText().toString();
                CreateTicket_PlaceHolderAPI createTicket_placeHolderAPI = APIConnection.getretrofitinstance().create(CreateTicket_PlaceHolderAPI.class);
                Call<CreateTicketConstructor> call = createTicket_placeHolderAPI.getticketstatus(number);

                call.enqueue(new Callback<CreateTicketConstructor>() {
                    @Override
                    public void onResponse(Call<CreateTicketConstructor> call, Response<CreateTicketConstructor> response) {

                        if(response.isSuccessful()){
                            CreateTicketConstructor posts = response.body();

                            String status = posts.getRt_status();
                            String title = posts.getRt_title();
                            String remarks = posts.getRt_remark();

                            s_status.setText(status);
                            clonestatus.setText(status);
                            String gettextforcolorchange = s_status.getText().toString();
                            if (gettextforcolorchange.contains("Resolved") || gettextforcolorchange.contains("Auto - Resolved") ) {
                                Send_message.setText("Reopen");

                                s_status.setTextColor(Color.parseColor("#25AB21"));
                            } else {
                                s_status.setTextColor(Color.parseColor("#F06D0F"));
                            }
                            if (gettextforcolorchange.equals("open") || gettextforcolorchange.equals("Closed")) {
                                ticktview.setVisibility(View.GONE);
                                reach_out_t.setVisibility(View.GONE);
                                sendmessagebtnlayout.setVisibility(View.GONE);
                            }
                            s_title.setText(title);
                            s_remarks.setText(Html.fromHtml(remarks));
                            s_remarks.setMovementMethod(LinkMovementMethod.getInstance());
                            s_remarks.setLinksClickable(true);
                            cloneremarks.setText(remarks);
                        }
                    }

                    @Override
                    public void onFailure(Call<CreateTicketConstructor> call, Throwable t) {

                    }
                });

            }else if(ar_history_btno != null) {
                getSupportActionBar().setTitle("AR"+ar_history_btno);
                s_btno.setText(ar_history_btno);
                String ARnumber = s_btno.getText().toString();
                AutoResTicket_PlaceHolderAPI autoResTicket_placeHolderAPI = APIConnection.getretrofitinstance().create(AutoResTicket_PlaceHolderAPI.class);
                Call<AutoResTicketConstructor> call = autoResTicket_placeHolderAPI.ar_getticketstatusdata(ARnumber);

                call.enqueue(new Callback<AutoResTicketConstructor>() {
                    @Override
                    public void onResponse(Call<AutoResTicketConstructor> call, Response<AutoResTicketConstructor> response) {

                        if(response.isSuccessful()){
                            AutoResTicketConstructor posts = response.body();


                            String status = posts.getAr_status();
                            String title = posts.getAr_title();
                            String remarks = "Resolved by RAnsh";

                            s_status.setText(status);
                            clonestatus.setText(status);
                            s_status.setTextColor(Color.parseColor("#25AB21"));
                                ticktview.setVisibility(View.GONE);
                                reach_out_t.setVisibility(View.GONE);
                                sendmessagebtnlayout.setVisibility(View.GONE);

                            s_title.setText(title);
                            s_remarks.setText(remarks);
                            cloneremarks.setText(remarks);
                        }
                    }

                    @Override
                    public void onFailure(Call<AutoResTicketConstructor> call, Throwable t) {

                    }
                });
            }

    }
    public void open_reopen_layout(View view) {
        LayoutInflater inflater = (LayoutInflater)
                getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.fragment_reopencomment, null);

        // create the popup window
        int width = LinearLayout.LayoutParams.MATCH_PARENT;
        int height = LinearLayout.LayoutParams.MATCH_PARENT;
        boolean focusable = true; // lets taps outside the popup also dismiss it
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

        popupWindow.showAtLocation(linearLayout, Gravity.CENTER, 0, 0);
        linearLayout.setAlpha(0.1f);
        View v =popupWindow.getContentView();
        ImageView imageView = v.findViewById(R.id.close_popup);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
                linearLayout.setAlpha(1f);
            }
        });

        Imagefirst = (ImageView) v.findViewById(R.id.imageattchemnt1);


        Imagefirst.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                callChooseFileFromDevice();
            }

            private void callChooseFileFromDevice() {
                Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                //open gallary by starting intent Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                startActivityForResult(i, RESULT_LOAD_IMAGE1);
            }

        });

        recyclerView = (RecyclerView) v.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        EditText reopen_comment = v.findViewById(R.id.reopen_comment);
        Button sendmail = v.findViewById(R.id.send_reopenmail);
        sendmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPermissionGranted()) {
//                    Datacontainer.pleasewait_popup(linearLayout,Ticket_status.this);
                    Thread sender = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {

                                SharedPreferences getshared = getSharedPreferences("Data", MODE_PRIVATE);

                                String clonesdnumber =cloneticket.getText().toString();
                                String cloneremarksold = cloneremarks.getText().toString();
                                String reopencommentdata = reopen_comment.getText().toString();
                                String btnumberclone = s_btno.getText().toString();
                                String updateremarks = Datacontainer.getDate()+"\n"+reopencommentdata+"\n"+cloneremarksold;

                                    if (rarraylist.size() >= 1){
                                        attachment = Datacontainer.getDate()+"\n"+"Attachment Added"+"\n"+ reopencommentdata+"\n"+cloneremarksold;
                                    }else {
                                        attachment = updateremarks;
                                    }
                                    String abc = clonestatus.getText().toString();
                                    if (abc.contains("Resolved")){
                                        updatestatus = "User - Reopened";
                                    }else {
                                        updatestatus = "User - Commented";
                                    }
                                    CreateTicketPostdata.addattachdata(account_history_btno, updatestatus, "Me : \n" + Datacontainer.getDate() + "\n" + reopencommentdata + "\n" + cloneremarksold, Ticket_status.this, new APICallBack() {
                                        @Override
                                        public void onResponse(boolean b) {
                                            for (int i = 0; i < pathlist.size(); i++) {
                                                String path = pathlist.get(i).getGetattachmentpath();
                                                CreateTicketPostdata.postattachmentdata(Datacontainer.getintid, path, Datacontainer.getDate(), Ticket_status.this, new APICallBack() {
                                                    @Override
                                                    public void onResponse(boolean S) {
                                                        if (S) {
                                                            //String agentname = "RANSH";
                                                            CreateTicketPostdata.addattachdata(account_history_btno, updatestatus, "Attachment Added \n" + Datacontainer.getDate() + "\n" + reopencommentdata + "\n" + cloneremarksold, Ticket_status.this, new APICallBack() {
                                                                @Override
                                                                public void onResponse(boolean succ) {
                                                                    if (succ){

                                                                    }
                                                                }
                                                            });
                                                            Toast.makeText(Ticket_status.this, "Attachment Attached", Toast.LENGTH_SHORT).show();
                                                        }
                                                    }
                                                });
                                            }
                                            Toast.makeText(Ticket_status.this, "Submitted", Toast.LENGTH_SHORT).show();
                                        }
                                    });






                            } catch (Exception e) {
                                Log.e("mylog", "Error: " + e.getMessage());
                            }
                        }
                    });
                    sender.start();
                    linearLayout.setAlpha(1f);
                    popupWindow.dismiss();


                } else if ( ContextCompat.checkSelfPermission(getBaseContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
                {
                    ActivityCompat.requestPermissions(Ticket_status.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1000);
                    takePermission();
                }

            }
        });


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_LOAD_IMAGE1 && resultCode == RESULT_OK) {
            Uri selectedImage = data.getData();
            if (null != selectedImage) {
                String[] filePathColumn = {MediaStore.Images.Media.DATA};

                Cursor cursor = getContentResolver().query(selectedImage,
                        filePathColumn, null, null, null);
                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                picturePath1 = cursor.getString(columnIndex);
                cursor.close();

                pathlist.add(new GetAttachmentPath(Base64Image.getBase64FromPath(picturePath1)));
                rarraylist.add(new Myattachmentlist(picturePath1));
                radapter = new CommentAddattachmentadapter(rarraylist, Ticket_status.this);
                recyclerView.setAdapter(radapter);

            }
        }else if (requestCode == 100 && resultCode == RESULT_OK) {

                if (Build.VERSION.SDK_INT == Build.VERSION_CODES.R) {
                    if (Environment.isExternalStorageManager()) {
                        Datacontainer.customtoast("Permission Granted in android 11", Ticket_status.this,R.drawable.check_icon);

                    } else {
                        takePermission();
                    }
                }
        }
    }


    public boolean isPermissionGranted() {
        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.R) {
            //For Android 11
            return Environment.isExternalStorageManager();
        } else {
            //For below
            int readEnternalStoragePermission = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
            return readEnternalStoragePermission == PackageManager.PERMISSION_GRANTED;
        }
    }

    public void takePermission() {
        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.R) {
            try {
                Intent intent = new Intent(Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION);
                intent.addCategory("android.intent.category.DEFAULT");
                intent.setData(Uri.parse(String.format("package:%s", getApplicationContext().getPackageName())));
                startActivityForResult(intent, 100);
            } catch (Exception exception) {
                Intent intent = new Intent();
                intent.setAction(Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION);
                startActivityForResult(intent, 100);
            }
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 101);
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 102);
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 103);
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults.length > 0) {
            if (requestCode == 101) {

                boolean readExternalStorage = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                if (readExternalStorage) {
                    Datacontainer.customtoast("Read Permission is granted for android 10 or below",Ticket_status.this,R.drawable.check_icon);
                } else {
                    takePermission();
                }

            }else if (requestCode == 102){
                boolean camera = grantResults[1] == PackageManager.PERMISSION_GRANTED;
                if (camera) {
                    Datacontainer.customtoast("Camera Permission is granted for android 10 or below",Ticket_status.this,R.drawable.check_icon);
                } else {
                    takePermission();
                }
            }else if (requestCode == 103){
                boolean LOCATION = grantResults[2] == PackageManager.PERMISSION_GRANTED;
                if (LOCATION) {
                    Datacontainer.customtoast("Location Permission is granted for android 10 or below",Ticket_status.this,R.drawable.check_icon);
                } else {
                    takePermission();
                }
            }

        }

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id =item.getItemId();
        if (id == android.R.id.home){
            this.finish();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {

        super.onBackPressed();
    }

}
