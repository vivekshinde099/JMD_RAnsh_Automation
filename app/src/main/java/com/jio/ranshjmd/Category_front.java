package com.jio.ranshjmd;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.jio.ranshjmd.Common.Utils;

public class Category_front extends AppCompatActivity{

    private String[] title = {
            "JIO POS Plus - Login Related Issue",
            "JIO MART Digital - Login Related Issue",
            "JIO POS Plus - Process Related Issue",
            "JIO MART Digital -  Process Related Issue",
            "JIO POS Plus - Payment Related Issue",


    };
    int numberOfColumns = 1;
    CardView card;

    TextView test;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_front);
        getSupportActionBar().hide();
//
//        new FunctiontocheckwithFB(Category_front.this);
        test = (TextView) findViewById(R.id.category_versiontab);
        PackageInfo versioname = Utils.getversionname("com.jio.jpp1",getPackageManager());

            if (versioname == null){
                test.setText("Jio Pos Plus Current Version : No App Installed");
            }else {
                test.setText("Jio Pos Plus current version : " + versioname.versionName);
            }


        card = findViewById(R.id.keyboardcardlayout);
        card.setBackgroundResource(R.drawable.corners);

        card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Issuepage.class);
                startActivity(intent);
            }
        });

   //     RecyclerView  recyclerView = (RecyclerView)findViewById(R.id.recyclerview);
   //     recyclerView.setLayoutManager(new GridLayoutManager(this,numberOfColumns));
      //  recyclerView.setAdapter(new Maincategoryissue(myissuelist,Category_front.this));

//        Mylistdata[] myissuelist = new Mylistdata[]{
//                new Mylistdata("DKYC Issue"),
//                new Mylistdata("Login Related Issue"),
//                new Mylistdata("Recharge Related Issue"),
//                new Mylistdata("Jio Pos Plus Download"),
//                new Mylistdata("MIS & Report"),
//                new Mylistdata("Process Related"),
//                new Mylistdata("Jio Pos Plus Utilities"),
//                new Mylistdata("Postpaid DKYC Related"),
//
//    };

    //    Maincategoryissue MYISSUELISTADAP = new Maincategoryissue(myissuelist,Category_front.this);
    //    recyclerView.setAdapter(MYISSUELISTADAP);

        }




    public void opensrform(View view) {
        Intent intent = new Intent(getApplicationContext(),SR_Form.class);
        startActivity(intent);

    }
    protected void onPause(){
        super.onPause();

    }
    @Override
    protected void onResume(){
        super.onResume();

    }
    @Override
    protected void onDestroy(){
        super.onDestroy();

    }



}