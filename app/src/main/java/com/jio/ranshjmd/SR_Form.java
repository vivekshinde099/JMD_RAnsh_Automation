package com.jio.ranshjmd;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.provider.Settings;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;


import java.util.ArrayList;
import java.util.List;

import com.jio.ranshjmd.Adapters.Addattachmentadapter;
import com.jio.ranshjmd.Common.Datacontainer;
import com.jio.ranshjmd.Common.Utils;
import com.jio.ranshjmd.Models.GetAttachmentPath;
import com.jio.ranshjmd.Models.Myattachmentlist;
import com.jio.ranshjmd.RequestAPI.AutoResTicketPostData;
import com.jio.ranshjmd.RequestAPI.CreateTicketPostdata;
import com.jio.ranshjmd.Ticketresults.Ticket_NotResolved;

public class SR_Form extends Activity {

    private ImageView Imagefirst;
    Button createticket;
    int autoresolvercounter = 0;
    int counter = 0;
    Handler handler = new Handler();
    TextView receipent, btticketno;
    EditText encodestr,edtxt1, edtxt2, edtxt3;
    String picturePath1, Issuetitle,phonenumber,encImage;
    private static int RESULT_LOAD_IMAGE1 = 1;
    DatabaseReference myRef;
    RecyclerView.Adapter radapter;
    ArrayList<Myattachmentlist> rarraylist;
    ArrayList<GetAttachmentPath> pathlist;
    Addattachmentadapter madapter;
    Myattachmentlist[] myattachmentlists;
    List<Myattachmentlist> LISTITEM;
    TextView query1,query2,querys1,querys2,queryt1,queryt2;
    Button querybtn1,querybtn2,querybtn3;
    CardView cardView1,cardView2,cardView3;
    LinearLayout checkinginformation , Mainlayout,createticketlayout;
    ScrollView scrollviewsrpage;
    RelativeLayout linearLayout;
    ImageView imageView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sr_form);
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        linearLayout = (RelativeLayout) findViewById(R.id.formlayouttoshow);
        imageView = (ImageView)findViewById(R.id.abc); //        handler = new Handler();

        SharedPreferences getshared = getSharedPreferences("Data", MODE_PRIVATE);
        String nprm = getshared.getString("strprm", "PRM");
        phonenumber = getshared.getString("strmob", "Mobile Number");
        String Email = getshared.getString("stremail", "Email");
        String nname = getshared.getString("strname", "Name");

        btticketno = findViewById(R.id.btticket);
        final ProgressBar srprogressbar = (ProgressBar) findViewById(R.id.sr_progressbar);

        edtxt1 = findViewById(R.id.editTextTextPersonName4);
        edtxt1.setText(nprm);
        edtxt2 = findViewById(R.id.editTextTextPersonName5);
        edtxt3 = findViewById(R.id.editTextTextPersonName6);
        encodestr = (EditText) findViewById(R.id.encoded_string);
        Imagefirst = (ImageView) findViewById(R.id.imageattchemnt1);

        final String PRM = edtxt1.getText().toString();
        final String subject = edtxt2.getText().toString();
        final String body = edtxt3.getText().toString();

//        ------------------------------------------------------------------------------------------
        rarraylist = new ArrayList<>();
        pathlist = new ArrayList<>();
//        ------------------------------------------------------------------------------------------
        createticket = (Button) findViewById(R.id.createticket);

        //Send Data for ticket creation
        createticket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (isPermissionGranted()) {
                    final String finalticketno = btticketno.getText().toString();
                    String a = edtxt1.getText().toString();
                    String b = edtxt2.getText().toString();
                    String c = edtxt3.getText().toString();

                    if (a.isEmpty()) {
                        edtxt1.requestFocus();
                        edtxt1.setError("Please enter PRM ID");
                    } else if (b.isEmpty()) {
                        edtxt2.requestFocus();
                        edtxt2.setError("Please enter Subject Line");
                    } else if (c.isEmpty()) {
                        edtxt3.requestFocus();
                        edtxt3.setError("Kindly add the description below");
                    } else {
                        try {
                            CreateTicketPostdata.postdata("", "Team 0", "", Datacontainer.getDate(), "R-Ansh", "", phonenumber, nprm, c, "open", b, "", "",SR_Form.this, new APICallBack() {
                                @Override
                                public void onResponse(boolean success) {
                                    if (!success){
                                        Toast.makeText(SR_Form.this, "Unable to create ticket, Kindly Re-try", Toast.LENGTH_SHORT).show();
                                    }else if(success){
                                        for (int i = 0; i < pathlist.size(); i++) {
                                            String path = pathlist.get(i).getGetattachmentpath();
                                            CreateTicketPostdata.postattachmentdata(Datacontainer.getintid, path, Datacontainer.getDate(), SR_Form.this, new APICallBack() {
                                                @Override
                                                public void onResponse(boolean S) {
                                                    if (S) {
                                                        String agentname = "RANSH";
                                                        CreateTicketPostdata.postlogdata(Datacontainer.getintid, "Attachment Added", Datacontainer.getDate(),agentname, "open", SR_Form.this, new APICallBack() {
                                                            @Override
                                                            public void onResponse(boolean succ) {
                                                                if (succ){

                                                                }
                                                            }
                                                        });

                                                        Toast.makeText(SR_Form.this, "Done", Toast.LENGTH_SHORT).show();
                                                    }
                                                }
                                            });
                                        }

                                        String agentname = "RANSH";
                                            CreateTicketPostdata.postlogdata(Datacontainer.getintid, "Created by RAnsh", Datacontainer.getDate(),agentname, "open", SR_Form.this, new APICallBack() {
                                                @Override
                                                public void onResponse(boolean succ) {
                                                    if (succ){
                                                        Intent intent = new Intent(SR_Form.this, Ticket_NotResolved.class);
                                                        intent.putExtra("Ticket_no", Datacontainer.getintid );
                                                        intent.putExtra("Title",b);
                                                        intent.putExtra("Mobilenumber",phonenumber);
                                                        intent.putExtra("Resolved", "notresolved");
                                                        startActivity(intent);
                                                        finish();
                                                        Toast.makeText(SR_Form.this, "Thankyou for using RAnsh, will connect you soon.", Toast.LENGTH_SHORT).show();
                                                    }
                                                }
                                            });



                                    }
                                }
                            });
                        }
                        catch (Exception e){
                            Log.e("mylog", "Error: " + e.getMessage());
                        }
                    }
                }
                else if ( ContextCompat.checkSelfPermission(getBaseContext(),Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
                {
                    ActivityCompat.requestPermissions(SR_Form.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1000);
                    takePermission();
                }
            }
        });

        //Pick image from device
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

        //Initilization
        initviews();
        query1.setText(R.string.rpos_jpos_folder_delete);
        querybtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Datacontainer.deletefolderusingpath();
                if (!Datacontainer.folderdeletesuccess){
                    query2.setText("No Folder's Found");
                    wholeprocess();
                }else {
                    query2.setText("RPOS/JPOS Folder's Deleted");
                    wholeprocess();
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

                encodestr.setText(Base64Image.getBase64FromPath(picturePath1));
                String abc = encodestr.getText().toString();
                encImage = Base64Image.getBase64FromPath(picturePath1);
                Log.d("data", encodestr.getText().toString());

                RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
                recyclerView.setLayoutManager(new LinearLayoutManager(this));

                pathlist.add(new GetAttachmentPath(Base64Image.getBase64FromPath(picturePath1)));
                rarraylist.add(new Myattachmentlist(picturePath1));

                radapter = new Addattachmentadapter(rarraylist, SR_Form.this);
                recyclerView.setAdapter(radapter);

           }

        }else if (resultCode == RESULT_OK) {
            if (requestCode == 100) {
                if (Build.VERSION.SDK_INT == Build.VERSION_CODES.R) {
                    if (Environment.isExternalStorageManager()) {
                        Datacontainer.customtoast("Permission Granted in android 11", SR_Form.this,R.drawable.check_icon);

                    } else {
                        takePermission();
                    }
                }
            }
        }
    }

    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void initviews(){
        query1 = findViewById(R.id.query1);
        query2 = findViewById(R.id.query2);
        querys1 = findViewById(R.id.querys1);
        querys2 = findViewById(R.id.querys2);
        queryt1 = findViewById(R.id.queryt1);
        queryt2 = findViewById(R.id.queryt2);

        querybtn1 = (Button) findViewById(R.id.btnquery1);
        querybtn2 = (Button) findViewById(R.id.btnquerys2);
        querybtn3 = (Button) findViewById(R.id.btnqueryt1);
        cardView1 = (CardView) findViewById(R.id.cardlayout1);
        cardView2 = (CardView) findViewById(R.id.cardlayout2);
        cardView3 = (CardView) findViewById(R.id.cardlayout3);
        checkinginformation = (LinearLayout) findViewById(R.id.checking_information);
        Mainlayout = (LinearLayout) findViewById(R.id.main_layout);
        createticketlayout = (LinearLayout) findViewById(R.id.layoutcreateticket);
        scrollviewsrpage = (ScrollView) findViewById(R.id.Scrollviewsrpage);
    }

    public void onButtonShowPopupWindowClick() {

        Issuetitle = edtxt2.getText().toString();

        SharedPreferences getshared = getSharedPreferences("Data", MODE_PRIVATE);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setCancelable(false);

        alertDialogBuilder.setMessage("Does your issue resolved?")
                .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Datacontainer.pleasewait_popup(linearLayout,SR_Form.this);

                        String nprm = getshared.getString("strprm", "PRM");
                        String nmob = getshared.getString("strmob", "Mobile Number");
                        String nemail = getshared.getString("stremail", "Email ID");
                        String nname = getshared.getString("strname", "Name");

                        try {
                            AutoResTicketPostData.postdata(Datacontainer.getDate(), nemail, "Resolved by Basic troubleshooting", nmob, nname, nprm, "Autoresolved", "", "", "", SR_Form.this, new APICallBack() {
                                @Override
                                public void onResponse(boolean success) {
                                    if (!success){
                                        Toast.makeText(SR_Form.this, "Unable to create ticket, Kindly Re-try", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        }catch (Exception e){
                            Log.e("mylog", "Error: " + e.getMessage());
                        }

                    }

                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                checkinginformation.setVisibility(View.GONE);
                Mainlayout.setVisibility(View.VISIBLE);
                scrollviewsrpage.setVisibility(View.VISIBLE);
                createticketlayout.setVisibility(View.VISIBLE);
            }
        });

        AlertDialog alert = alertDialogBuilder.create();
        alert.show();
    }

    private void wholeprocess(){
        cardView2.setVisibility(View.VISIBLE);
        querys1.setText(R.string.clear_cache_clear_data);
        querybtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openapplicationsetting();

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(SR_Form.this);
                alertDialogBuilder.setCancelable(false);
                alertDialogBuilder.setMessage(Html.fromHtml("Have you Cleared cache and data ?"))
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(SR_Form.this, "Kindly click again to do clear cache/data", Toast.LENGTH_LONG).show();
                            }
                        }).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        querys2.setText("Clear Cache/Data Done");
                        cardView3.setVisibility(View.VISIBLE);
                        queryt1.setText(R.string.data_delta_sync);
                        querybtn3.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                openapplicationfordatasync();

                                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(SR_Form.this);
                                alertDialogBuilder.setCancelable(false);
                                alertDialogBuilder.setMessage(Html.fromHtml("Have you Done Data/Delta Sync ?"))
                                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                Toast.makeText(SR_Form.this, "Kindly click again to do Data/Delta Sync", Toast.LENGTH_LONG).show();
                                            }
                                        }).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        queryt2.setText("Data/Delta Sync Done");
                                        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(SR_Form.this);
                                        alertDialogBuilder.setCancelable(false);
                                        alertDialogBuilder.setMessage(Html.fromHtml("Now try to login Jio Pos Plus and check"))
                                                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialog, int which) {
                                                       onButtonShowPopupWindowClick();
                                                    }
                                                });

                                        AlertDialog alert = alertDialogBuilder.create();
                                        alert.show();
                                    }
                                });

                                AlertDialog alert = alertDialogBuilder.create();
                                alert.show();
                            }
                        });
                    }
                });

                AlertDialog alert = alertDialogBuilder.create();
                alert.show();
            }
        });

    }

    private void openapplicationfordatasync(){
        boolean isInstalled = Utils.isPackageInstalled("com.jio.jpp1", getPackageManager());
        if (isInstalled){
            Intent intent = getPackageManager().getLaunchIntentForPackage("com.jio.jpp1");
            startActivity(intent);
        }else {
            Intent intent1 = getPackageManager().getLaunchIntentForPackage("com.ril.rposcentral");
            startActivity(intent1);
        }
        Toast.makeText(getApplicationContext(), "Login JPP >> Menu >> Utility >> Data/Delta Sync", Toast.LENGTH_LONG).show();
    }

    private void openapplicationsetting(){
        boolean isInstalled = Utils.isPackageInstalled("com.jio.jpp1", getPackageManager());
        if (isInstalled){
            Intent intent = new Intent();
            intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
            Uri uri = Uri.fromParts("package", "com.jio.jpp1", null);
            intent.setData(uri);
            startActivity(intent);

        }else {
            Intent intent = new Intent();
            intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
            Uri uri = Uri.fromParts("package", "com.ril.rposcentral", null);
            intent.setData(uri);
            startActivity(intent);
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
                    Datacontainer.customtoast("Read Permission is granted for android 10 or below",SR_Form.this,R.drawable.check_icon);
                } else {
                    takePermission();
                }

            }else if (requestCode == 102){
                boolean camera = grantResults[1] == PackageManager.PERMISSION_GRANTED;
                if (camera) {
                    Datacontainer.customtoast("Camera Permission is granted for android 10 or below",SR_Form.this,R.drawable.check_icon);
                } else {
                    takePermission();
                }
            }else if (requestCode == 103){
                boolean LOCATION = grantResults[2] == PackageManager.PERMISSION_GRANTED;
                if (LOCATION) {
                    Datacontainer.customtoast("Location Permission is granted for android 10 or below",SR_Form.this,R.drawable.check_icon);
                } else {
                    takePermission();
                }
            }

        }

    }

}

