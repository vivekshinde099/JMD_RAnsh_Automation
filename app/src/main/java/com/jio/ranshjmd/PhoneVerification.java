package com.jio.ranshjmd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import com.jio.ranshjmd.FirebaseConfig.Phonenumberconstructor;

public class PhoneVerification extends AppCompatActivity {

    String verificationcodesentbysystem;
    Button verify_btn;
    EditText phonenumberenteredbyuser;
    ProgressBar progressbar;
    private FirebaseAuth mAuth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference myRef;
    String phoneno,email,name,prm;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_verification);
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        mAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        myRef = firebaseDatabase.getReference("User Data");

        verify_btn = findViewById(R.id.verifybutton);
        phonenumberenteredbyuser = findViewById(R.id.phonenumberenteredbyuser);
        progressbar = findViewById(R.id.phoneverifyprogressbar);

        phoneno = getIntent().getStringExtra("mob");
        email = getIntent().getStringExtra("email");
        name = getIntent().getStringExtra("name");
        prm = getIntent().getStringExtra("prm");
        
        sendverificationtouser(phoneno);
        verify_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    String code = phonenumberenteredbyuser.getText().toString();
                    if (code.isEmpty() || code.length()<6){
                        phonenumberenteredbyuser.setError("Wrong OTP");
                        phonenumberenteredbyuser.requestFocus();
                        return;
                    }
                    progressbar.setVisibility(View.VISIBLE);
                    verifycode(code);
                }
        });
    }

    private void sendverificationtouser(String phoneno) {

        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber("+91"+phoneno)       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // Activity (for callback binding)
                        .setCallbacks(mCallbacks)          // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
        progressbar.setVisibility(View.VISIBLE);
    }

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks =  new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onCodeSent(@NonNull @NotNull String s, @NonNull @NotNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);

            verificationcodesentbysystem = s;
        }

        @Override
        public void onVerificationCompleted(@NonNull @NotNull PhoneAuthCredential phoneAuthCredential) {

       /*     String code = phoneAuthCredential.getSmsCode();
            if(code!=null){
                progressbar.setVisibility(View.VISIBLE);
                verifycode(code);
            }*/
        }

        @Override
        public void onVerificationFailed(@NonNull @NotNull FirebaseException e) {

            Toast.makeText(PhoneVerification.this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    };

    private void verifycode(String codebyuser) {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationcodesentbysystem,codebyuser);
        signintheuserbycredential(credential);
    }

    private void signintheuserbycredential(PhoneAuthCredential credential) {


        mAuth = FirebaseAuth.getInstance();
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(PhoneVerification.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
                        if (task.isSuccessful()){

                            try {
                                Phonenumberconstructor record = new Phonenumberconstructor("0");
                                myRef.child(mAuth.getCurrentUser().getPhoneNumber()).child("0").setValue(record);
                                sharedPreferences = getSharedPreferences("Data", MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPreferences.edit();

                                editor.putString("strprm", prm);
                                editor.putString("strmob", phoneno);
                                editor.putString("stremail", email);
                                editor.putString("strname", name);

                                editor.apply();
                                Intent intent = new Intent(PhoneVerification.this,MainScreen.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);

                            }catch (Exception e){
                                Toast.makeText(PhoneVerification.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            }

                        }else {
                            Toast.makeText(PhoneVerification.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
    private String getDate(){
        DateFormat dfDate = new SimpleDateFormat("yyyy/MM/dd");
        String date=dfDate.format(Calendar.getInstance().getTime());
        DateFormat dfTime = new SimpleDateFormat("HH:mm");
        String time = dfTime.format(Calendar.getInstance().getTime());
        return date + " " + time;
    }
}