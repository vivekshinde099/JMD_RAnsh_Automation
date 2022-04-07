package com.jio.ranshjmd.Common;

import android.app.Activity;
import android.content.SharedPreferences;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import com.jio.ranshjmd.R;

import static android.content.Context.MODE_PRIVATE;

public class FirebaseConnectivity {


    public static FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    public static DatabaseReference updateprofile = firebaseDatabase.getReference().child("UserMasterData");
    public static DatabaseReference updatebotdata = firebaseDatabase.getReference("Bot Data");
    public static DatabaseReference userdata = firebaseDatabase.getReference("User Data");
    public static DatabaseReference Autoresolvertickets = firebaseDatabase.getReference("AutoresolverTickets");
    public static DatabaseReference zonewiseissuedata = firebaseDatabase.getReference("ZoneWise_IssueData");
    public static String  createticket = "" ;
    public static String Getzone;
    public static long count;
    public static DatabaseReference myRef1;

    public static void updateprofiledata(String PRM, String EMAIL, String NAME, String MOB, Activity activity){

        String phonemunber = "+91"+MOB;

        updateprofile.child(phonemunber).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                try {
                    updateprofile.child(phonemunber).child("name").setValue(NAME);
                    updateprofile.child(phonemunber).child("prm").setValue(PRM);
                    updateprofile.child(phonemunber).child("email").setValue(EMAIL);

                    SharedPreferences sharedPreferences = activity.getSharedPreferences("Data", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("strprm", PRM);
                    editor.putString("stremail", EMAIL);
                    editor.putString("strname", NAME);
                    editor.apply();

                    Datacontainer.customtoast("Details Updated Succesfully",activity, R.drawable.check_icon);

                }catch (Exception e){
                    Toast.makeText(activity, e.getMessage(), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
    }

    public static void updatebotdata(String BTNumber,String Title,String Date ,String maxid,String Phonenumber,Activity activity,String PRM){

//        updateprofile.child("+91"+Phonenumber).addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
//                UserMasterData value = snapshot.getValue(UserMasterData.class);
//                Getzone = value.getZone();
//            }
//            @Override
//            public void onCancelled(@NonNull @NotNull DatabaseError error) {
//
//            }
//        });

//        Datasent_botdata dbd = new Datasent_botdata(BTNumber,Title,"open",Date+"\nStatus(Open)",Datacontainer.getDate(),PRM,"+91"+Phonenumber,"Team 0");
//        updatebotdata.child(BTNumber).setValue(dbd);
//        createticket = "a";

        if (createticket == "a"){
//            DatabaseReference botdatalog = firebaseDatabase.getReference("Bot Data/"+BTNumber+"/logs/0");
//            BotDataLogValues btlv = new BotDataLogValues(Datacontainer.getDate(),"Bot-Open","R-Ansh",Title);
//            botdatalog.setValue(btlv);
//            Datasentotfirebase dsf = new Datasentotfirebase(BTNumber,maxid);
//            userdata.child("+91"+Phonenumber).child(maxid).setValue(dsf);
//            createticket = "b";
        }
    }

}


