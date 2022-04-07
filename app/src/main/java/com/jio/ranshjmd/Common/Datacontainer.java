package com.jio.ranshjmd.Common;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.provider.Settings;
import android.text.Html;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.mlkit.nl.translate.TranslateLanguage;
import com.google.mlkit.nl.translate.Translation;
import com.google.mlkit.nl.translate.Translator;
import com.google.mlkit.nl.translate.TranslatorOptions;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.jio.ranshjmd.R;

import static android.content.Context.MODE_PRIVATE;


public class Datacontainer  {

    public static long Maxid = 0;
    public static long Best5=0;
   // public static Translator englishhindiTranslator;
    public static String str ;
    public static String getlanguage = "English";
    public static String finallanguage;
    public static SharedPreferences getshared;
    public static TranslatorOptions options;
    public static boolean folderdeletesuccess;
    public static String commonstring="";
    public static Uri uri;
    public static String checkbox = "";
    public static String mailsender;
    public static Translator englishhindiTranslator;
    public static PopupWindow popupWindow1;
    public static Handler handler;
    public static Runnable r;
    public static String ticketpagedatahandler ="";
    public static String nprm,nmob,nemail,nname;
    public static long noticount;
    public static String sentotp;
    public static String validateotp;
    public static String checkidresult;
    public static String status="",title="",remarks="",ar_status,ar_title,ar_remarks;
    public static String result;

    public static String getintid;
    public static int notificationdot;


    // Translator Model
    public static void model(Button button, TextView textView, Activity activity, String href) {
        boolean servicedesk=false;
        String value_ignore="";
        final int[] no = {1};

        if(Datacontainer.getlanguage.equals("English")) {
        }
        else {
            switch (Datacontainer.getlanguage) {
                case "Hindi":
                    options = new TranslatorOptions.Builder()
                            .setSourceLanguage(TranslateLanguage.ENGLISH)
                            .setTargetLanguage(TranslateLanguage.HINDI)
                            .build();
                    break;
                case "Kannada":
                    options = new TranslatorOptions.Builder()
                            .setSourceLanguage(TranslateLanguage.ENGLISH)
                            .setTargetLanguage(TranslateLanguage.KANNADA)
                            .build();
                    break;
                case "Telugu":
                    options = new TranslatorOptions.Builder()
                            .setSourceLanguage(TranslateLanguage.ENGLISH)
                            .setTargetLanguage(TranslateLanguage.TELUGU)
                            .build();
                    break;
//            case "Malayalam" :
//                options = new TranslatorOptions.Builder()
//                        .setSourceLanguage(TranslateLanguage.ENGLISH)
//                        .setTargetLanguage(TranslateLanguage.MALAY)
//                        .build();
//                break;
                case "Tamil":
                    options = new TranslatorOptions.Builder()
                            .setSourceLanguage(TranslateLanguage.ENGLISH)
                            .setTargetLanguage(TranslateLanguage.TAMIL)
                            .build();
                    break;
                case "Bengali":
                    options = new TranslatorOptions.Builder()
                            .setSourceLanguage(TranslateLanguage.ENGLISH)
                            .setTargetLanguage(TranslateLanguage.BENGALI)
                            .build();
                    break;
//                case "English":
//                    options = new TranslatorOptions.Builder()
//                            .setSourceLanguage(TranslateLanguage.ENGLISH)
//                            .setTargetLanguage(TranslateLanguage.ENGLISH)
//                            .build();
//                    break;
                case "Marathi":
                    options = new TranslatorOptions.Builder()
                            .setSourceLanguage(TranslateLanguage.ENGLISH)
                            .setTargetLanguage(TranslateLanguage.MARATHI)
                            .build();
                    break;
            }
            englishhindiTranslator = Translation.getClient(options);

            String value = "";

            if (button != null)
                value = button.getText().toString();
            else if (textView != null)
                value = textView.getText().toString().replace("LINK1", "").replace("LINK2", "").replace("LINK3", "").replace("desk1234", "");


            String[] rep = new String[]{"servicedesk.ril.com", "AREA-POS", "ONLINE-EFT", "L1", "R-Ansh", "plan","Jio","Pos","Plus","username","POS","whitelist","OC","Aadhar"
            ,"POI","POA","Outstation","OTP","CAF","ORN","Issue","IMEI","Distributor","SPOC","HR","Refund_With_History","M-Assist","Intelligent-HUB","ICCID","IMSI","A-4","Automatic_Time_Zone","EKYC","APK","Continue"
            ,"E_Aadhar","OrderType","Utilities","WiFi","Term","PRM","PartnerIT","Contact_to_HO","Generate_OTP"};
            String[] val = value.split(" ");
            for (int j = 0; j < val.length; j++) {
                for (int i = 0; i < rep.length; i++) {
                    if (val[j].equals(rep[i])) {
//                    String sa = String.valueOf(i);
                        value = value.replace(rep[i], " # ");
//                        value = value.replace(rep[i], " <br> ");
                        value_ignore += "," + rep[i];
//                            value_position += "," + " " + sa + " ";
                    }
                }
            }


            String finalValue_ignore = value_ignore;
            englishhindiTranslator.translate(value).addOnSuccessListener(new OnSuccessListener<String>() {
                @Override
                public void onSuccess(String s) {
                    str = s;
                    if (href != "") {
                        if (button != null)
                            button.setText(Html.fromHtml(s + "\n" + href));

                        else if (textView != null) {
                            word_replace(s);
                            textView.setText(Html.fromHtml(s + "\n" + href));
                        }
                    } else {
                        if (button != null) {
                            button.setText(s);

                        }
                        else if (textView != null) {

                            String[] word = finalValue_ignore.split(",");
                            for (; no[0] < word.length; no[0]++) {
                                String sa = String.valueOf(no[0]);
                                s = s.replaceFirst(" # ", " " + word[no[0]] + " ");
//                                s = s.replaceFirst(" <br> ", " " + word[no[0]] + " ");
                            }
                            s = s.replace("#", "");
//                            s = s.replace("<br>", "");
//                        s = s.replace(" # ", "servicedesk.ril.com");
                            textView.setText(s);
                        }
                    }

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull @NotNull Exception e) {
//                    ProgressDialog progressDialog = new ProgressDialog(activity);
//                    progressDialog.setMessage("This may take several minutes depending upon network speed"); // Setting Message
//                    progressDialog.setTitle("Configuring resource language"); // Setting Title
//                    progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER); // Progress Dialog Style Spinner
//                    progressDialog.show(); // Display Progress Dialog
//                    progressDialog.setCancelable(false);
//
//                    englishhindiTranslator.downloadModelIfNeeded().addOnSuccessListener(new OnSuccessListener<Void>() {
//                        @Override
//                        public void onSuccess(Void unused) {
//                            progressDialog.dismiss();
//
//                        }
//                    });
                }
            });
        }
    }

    // Delete Folder Model

    public static boolean deleteDir(File dir) {

        if (dir.isDirectory() && dir.exists()) {

            String[] children = dir.list();

            for (int i=0; i<children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }else{
        }
        return dir.delete();
    }

    public static void deletefolderusingpath(){

        folderdeletesuccess = deleteDir(new File(Environment.getExternalStorageDirectory() +

                File.separator + "Jpos_plus_"));

        folderdeletesuccess = deleteDir(new File(Environment.getExternalStorageDirectory() +

                File.separator + "Jpos_plus_TxnId"));

        folderdeletesuccess = deleteDir(new File(Environment.getExternalStorageDirectory() +

                File.separator + "Jpos_plus_TxnRcpt"));

        folderdeletesuccess = deleteDir(new File(Environment.getExternalStorageDirectory() +

                File.separator + "Jpos_plus_Util"));

        folderdeletesuccess = deleteDir(new File(Environment.getExternalStorageDirectory() +

                File.separator + "RPOS"));

    }


    public static void word_replace(String replace_value){
    }

    public static void checkfirebase(Activity activity){

        DatabaseReference masterdatasearchid;
        final String[] masteruniquedeviceid = new String[1];
        final String[] android_uniqueid = new String[1];
        final int[] first = {0};
        SharedPreferences getshared = activity.getSharedPreferences("Data", MODE_PRIVATE);
        String nmob = getshared.getString("strmob", null);

        masterdatasearchid = FirebaseDatabase.getInstance().getReference().child("UserMasterData");
        masterdatasearchid.child("+91"+nmob).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    masteruniquedeviceid[0] = String.valueOf(snapshot.child("unique_device_id").getValue());
                    android_uniqueid[0] = android.provider.Settings.Secure.getString(activity.getContentResolver(), Settings.Secure.ANDROID_ID);
                    if (!android_uniqueid[0].equals(masteruniquedeviceid[0])){
                        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(activity);
                        alertDialogBuilder.setCancelable(false);
                        alertDialogBuilder.setMessage(Html.fromHtml("OOPS!! You has logged in with another Device"))
                                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        SharedPreferences sharedPreferences;
                                        sharedPreferences = activity.getSharedPreferences("Data", MODE_PRIVATE);
                                        SharedPreferences.Editor editor = sharedPreferences.edit();
                                        editor.putString("strprm", null);
                                        editor.putString("strmob", null);
                                        editor.putString("stremail", null);
                                        editor.putString("strname", null);
                                        editor.apply();
                                        activity.finishAffinity();
                                    }
                                });

                        AlertDialog alert = alertDialogBuilder.create();
                        if(!activity.isFinishing()) {
                            alert.show();
                        }
                    }
                }


            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });


    }


    public static void customtoast(String text, Activity activity, int drawable){
        View toast = LayoutInflater.from(activity).inflate(R.layout.customtoastmessage,null);
        Toast custom = new Toast(activity);
        custom.setView(toast);
        ImageView imageView = toast.findViewById(R.id.toast_imageView);
        imageView.setBackgroundResource(drawable);
        TextView message = toast.findViewById(R.id.toast_textview);
        message.setText(text);
        custom.setDuration(Toast.LENGTH_LONG);
        custom.show();
    }

    public static String getDate(){
        DateFormat dfDate = new SimpleDateFormat("yyyy-MM-dd");
        String date=dfDate.format(Calendar.getInstance().getTime());
        DateFormat dfTime = new SimpleDateFormat("HH:mm");
        String time = dfTime.format(Calendar.getInstance().getTime());
        return (date + " " + time);
    }

    public static void pleasewait_popup(RelativeLayout linearLayout, Activity activity) {
        View popupView1 = activity.getLayoutInflater().inflate(R.layout.fragment_pleasewait, null);
        // create the popup window
        int width1 = LinearLayout.LayoutParams.MATCH_PARENT;
        int height1 = LinearLayout.LayoutParams.MATCH_PARENT;
        boolean focusable1 = true; // lets taps outside the popup also dismiss it
        popupWindow1 = new PopupWindow(popupView1, width1, height1, focusable1);
        popupWindow1.showAtLocation(linearLayout, Gravity.CENTER, 0, 0);
    }

//    public static void session_expired(Activity activity){
//        handler = new Handler();
//        r = new Runnable() {
//
//            @Override
//            public void run() {
//                // TODO Auto-generated method stub
//
//                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(activity);
//                alertDialogBuilder.setCancelable(false);
//                alertDialogBuilder.setMessage(Html.fromHtml("Session Expired <br> Idle Timeout Error!!"))
//                        .setPositiveButton("ok", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                activity.finishAffinity();
//                            }
//                        });
//
//                AlertDialog alert = alertDialogBuilder.create();
//                if(!activity.isFinishing()) {
//                    alert.show();
//                }
//            }
//        };
//       startHandler();
//    }
//
//    public static void stopHandler() {
//        handler.removeCallbacks(r);
//    }
//    public static void startHandler() {
//        handler.postDelayed(r, 1*60*1000); //for 5 minutes
//    }

    public static void sharedprefrence(Context context){
        SharedPreferences getshared = context.getSharedPreferences("Data", MODE_PRIVATE);
        nprm = getshared.getString("strprm", "PRM");
        nmob = getshared.getString("strmob", "Mobile Number");
        nemail = getshared.getString("stremail", "Email ID");
        nname = getshared.getString("strname", "Name");
    }





}