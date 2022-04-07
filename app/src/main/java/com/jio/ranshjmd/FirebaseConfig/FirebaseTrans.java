package com.jio.ranshjmd.FirebaseConfig;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.TextView;
//
//import com.google.android.gms.tasks.OnSuccessListener;
//
//import com.google.mlkit.nl.translate.TranslateLanguage;
//import com.google.mlkit.nl.translate.Translation;
//import com.google.mlkit.nl.translate.Translator;
//import com.google.mlkit.nl.translate.TranslatorOptions;
//
//
//import kini.akshay.project.R;
//
//public class FirebaseTrans extends AppCompatActivity {
//
//    String abcd="";
//    TextView text1;
//    EditText text;
//    Translator firebaseTranslator;
//
//    String str ;
//
//    Button btn;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_firebase_translator);
//
//        text = (EditText) findViewById(R.id.textView2);
//        text1 = (TextView)findViewById(R.id.textView3);
//        btn = (Button)findViewById(R.id.button2);
//        String a = btn.getText().toString();
//
//
//
//     //   setupprogressbar();
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                abcd = text.getText().toString();
////                String a= model(abcd);
//
////               btn.setText(model(btn.getText().toString(),btn.getId(),btn));
//                model(btn);
//
//            }
//        });
//
//
//
//    }
//
////    private void setupprogressbar() {
////
////        pDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
////        pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
////        pDialog.setTitleText("Loading");
////        pDialog.setCancelable(false);
////        pDialog.show();
////    }
//
//
//    public void model(Button button) {
//
//
//        String value=button.getText().toString();
//
//
//        TranslatorOptions options =
//                new TranslatorOptions.Builder()
//                        .setSourceLanguage(TranslateLanguage.ENGLISH)
//                        .setTargetLanguage(TranslateLanguage.HINDI)
//                        .build();
//        final Translator englishhindiTranslator = Translation.getClient(options);
//     //   final String[] ans = new String[1];
//
////        DownloadConditions conditions = new DownloadConditions.Builder()
////                .requireWifi()
////                .build();
////            //      pDialog.setTitleText("Downloading feature");
////            //      pDialog.show();
////        englishhindiTranslator.downloadModelIfNeeded().addOnSuccessListener(new OnSuccessListener<Void>() {
////            @Override
////            public void onSuccess(Void unused) {
////
////                str = translatelanguagle(value);
//
//
//
//
//        englishhindiTranslator.translate(value).addOnSuccessListener(new OnSuccessListener<String>() {
//            @Override
//            public void onSuccess(String s) {
//                str = s;
//                button.setText(s);
//
//            }
//
//        });
//
////        Thread convert = new Thread(new Runnable() {
////            @Override
////            public void run() {
////
////            }
////        });convert.start();
//
//
//
//
////        englishhindiTranslator.translate(value)
////                .addOnSuccessListener(
////                        new OnSuccessListener<String>() {
////                            @Override
////                            public void onSuccess(@NonNull String translatedText) {
////                                // Translation successful.
////
////
////                            }
////                        })
////                .addOnFailureListener(
////                        new OnFailureListener() {
////                            @Override
////                            public void onFailure(@NonNull Exception e) {
////                                // Error.
////                                // ...
////                            }
////
////                        });
//
////            }
////        }).addOnFailureListener(new OnFailureListener() {
////            @Override
////            public void onFailure(@NonNull @NotNull Exception e) {
////                pDialog.dismissWithAnimation();
////            }
////        })
//
//
//    }
//
//
//
////    public String translatelanguagle(String v) {
////    //    pDialog.setTitleText("converting");
////    //    pDialog.show();
////        final String[] ans = new String[1];
////
////        englishhindiTranslator.translate(v).addOnSuccessListener(new OnSuccessListener<String>() {
////            @Override
////            public void onSuccess(String s) {
////
////                ans[0] =s;
////       //         text.setText(s);
////
////
////            }
////        }).addOnFailureListener(new OnFailureListener() {
////            @Override
////            public void onFailure(@NonNull @NotNull Exception e) {
////                pDialog.dismissWithAnimation();
////            }
////        });
////        return ans[0];
////    }
//
//}