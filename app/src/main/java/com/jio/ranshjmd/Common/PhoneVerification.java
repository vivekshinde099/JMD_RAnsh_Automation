package com.jio.ranshjmd.Common;

import android.app.Activity;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.TimeUnit;


public class PhoneVerification
{
    private static FirebaseAuth mAuth =FirebaseAuth.getInstance();
    private static String verificationcodesentbysystem;
    public static String phoneverificationtask = null;
    public static Activity commonactivity;


    public static void sendverificationtouser(String phoneno, Activity activity) {
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber("+91"+phoneno)       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(activity)                 // Activity (for callback binding)
                        .setCallbacks(mCallbacks)          // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
        commonactivity = activity;
    }

    private static PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks =  new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
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

            Toast.makeText(commonactivity, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    };

    public static void verifycode(String codebyuser,Activity activity) {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationcodesentbysystem,codebyuser);
        signintheuserbycredential(credential,activity);
    }

    public static void signintheuserbycredential(PhoneAuthCredential credential,Activity activity) {


        mAuth = FirebaseAuth.getInstance();
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull @NotNull Task<AuthResult> task) {

                        if (task.isSuccessful()){

                                phoneverificationtask = "Verified";

                        }else {
                            Toast.makeText(activity, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

}
