package com.jio.ranshjmd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.jio.ranshjmd.Adapters.Subcategoryissue;
import com.jio.ranshjmd.Bottomnavigationbar.MainHomePage;
import com.jio.ranshjmd.Models.Mysubcategorymodal;

public class Subcategory_back extends AppCompatActivity {


    TextView textView,hiddentext;
    TextView button;
    private ArrayAdapter<String> mAdapter;
    Mysubcategorymodal[] mysubissuelist;
    Subcategoryissue MYISSUELISTADAP;

    Handler handler;
    Runnable r;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_issue);
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_round_arrow_back_ios_24);
        getSupportActionBar().setTitle("");

//        Datacontainer.session_expired(Subcategory_back.this);


        button = (TextView) findViewById(R.id.btnissuename);
        hiddentext = (TextView)findViewById(R.id.textissuenamehidden);

        String value = getIntent().getExtras().getString("Issue_name");
        button.setText(value);
        String adaptervalue = button.getText().toString();


//        Datacontainer.model(button,null,Subcategory_back.this,"");
        RecyclerView  recyclerView = (RecyclerView)findViewById(R.id.issue_listview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        switch (adaptervalue){
            case "JIO POS Plus - Login Related Issue" :
                mysubissuelist = new Mysubcategorymodal[]{
                        new Mysubcategorymodal("Agent Authentication Failed from OID",Subcategory_back.this),
                        new Mysubcategorymodal("Store Code Mis-Match",Subcategory_back.this),
                        new Mysubcategorymodal("Buffering/Redirecting to Login screen/SSL pinning failed",Subcategory_back.this),
                        new Mysubcategorymodal("User is not present in OIAM",Subcategory_back.this),
                        new Mysubcategorymodal("I want to know my POS ID",Subcategory_back.this),
                        new Mysubcategorymodal("connection refused.",Subcategory_back.this),
                        new Mysubcategorymodal("Others",Subcategory_back.this),
                };
                MYISSUELISTADAP = new Subcategoryissue(mysubissuelist, Subcategory_back.this);
                recyclerView.setAdapter(MYISSUELISTADAP);
                break;

            case "JIO MART Digital - Login Related Issue" :
                mysubissuelist = new Mysubcategorymodal[]{
                        new Mysubcategorymodal("Session Expired",Subcategory_back.this),
                        new Mysubcategorymodal("Web page not available",Subcategory_back.this),
                        new Mysubcategorymodal("504 gateway timeout",Subcategory_back.this),
                        new Mysubcategorymodal("App is stuck on blue screen",Subcategory_back.this),
                        new Mysubcategorymodal("404 app config not found",Subcategory_back.this),
                        new Mysubcategorymodal("Invalid User Credentials",Subcategory_back.this),
                        new Mysubcategorymodal("User account is locked/disabled",Subcategory_back.this),
                        new Mysubcategorymodal("Others",Subcategory_back.this),
                };
                MYISSUELISTADAP = new Subcategoryissue(mysubissuelist, Subcategory_back.this);
                recyclerView.setAdapter(MYISSUELISTADAP);
                break;

            case "JIO POS Plus - Process Related Issue" :
                mysubissuelist = new Mysubcategorymodal[]{
                        new Mysubcategorymodal("Ledger / Order Status",Subcategory_back.this),
                        new Mysubcategorymodal("Something went wrong,Response:No Data",Subcategory_back.this),
                        new Mysubcategorymodal("No data found, while entering OTP",Subcategory_back.this),
                        new Mysubcategorymodal("Order cancellation process",Subcategory_back.this),
                        new Mysubcategorymodal("Amount refund process",Subcategory_back.this),
                        new Mysubcategorymodal("Failed reason : validation failed",Subcategory_back.this),
                        new Mysubcategorymodal("Online Order MASP Process",Subcategory_back.this),
                        new Mysubcategorymodal("You cannot void selected article",Subcategory_back.this),
                        new Mysubcategorymodal("OTP could not be received",Subcategory_back.this),
                        new Mysubcategorymodal("Amount requested does not match online payment",Subcategory_back.this),
                        new Mysubcategorymodal("Some technical error occurred",Subcategory_back.this),
                        new Mysubcategorymodal("KYC failed",Subcategory_back.this),
                        new Mysubcategorymodal("Your loan application is rejected - APPROVAL CRITERIA NOT MET",Subcategory_back.this),
                        new Mysubcategorymodal("Screen goes blank after selecting Bundle Phone option",Subcategory_back.this),
                        new Mysubcategorymodal("We are processing your data. Please wait for some time and retry",Subcategory_back.this),
                        new Mysubcategorymodal("Number is not eligible",Subcategory_back.this),
                        new Mysubcategorymodal("Your loan application is under process. Please try after sometime",Subcategory_back.this),
                        new Mysubcategorymodal("You cannot sell this device from device sale",Subcategory_back.this),
                        new Mysubcategorymodal("Failed to configure RPOS.db",Subcategory_back.this),
                        new Mysubcategorymodal("Others",Subcategory_back.this),




                };
                MYISSUELISTADAP = new Subcategoryissue(mysubissuelist, Subcategory_back.this);
                recyclerView.setAdapter(MYISSUELISTADAP);
                break;

            case "JIO MART Digital -  Process Related Issue" :
                mysubissuelist = new Mysubcategorymodal[]{

                        new Mysubcategorymodal("Pin code not available",Subcategory_back.this),
                        new Mysubcategorymodal("Add to cart button not enabled",Subcategory_back.this),
                        new Mysubcategorymodal("Order pending in JioMart Digital",Subcategory_back.this),
                        new Mysubcategorymodal("Order Placement Process through JioMart Digital",Subcategory_back.this),
                        new Mysubcategorymodal("Unable to search product in JioMart Digital.",Subcategory_back.this),
                        new Mysubcategorymodal("Error checking out inventory. Please try after sometime.",Subcategory_back.this),
                        new Mysubcategorymodal("Unable to click on Buy Now option",Subcategory_back.this),
                        new Mysubcategorymodal("Unable to compare products",Subcategory_back.this),
                        new Mysubcategorymodal("Others",Subcategory_back.this),
                };
                MYISSUELISTADAP = new Subcategoryissue(mysubissuelist, Subcategory_back.this);
                recyclerView.setAdapter(MYISSUELISTADAP);
                break;

            case "JIO POS Plus - Payment Related Issue" :
                mysubissuelist = new Mysubcategorymodal[]{

                        new Mysubcategorymodal("Payment not found",Subcategory_back.this),
                        new Mysubcategorymodal("product price mismatch",Subcategory_back.this),
                        new Mysubcategorymodal("Payment deducted, EJ not generated",Subcategory_back.this),
                        new Mysubcategorymodal("Item Not Found",Subcategory_back.this),
                        new Mysubcategorymodal("Pincode Not Found",Subcategory_back.this),
                        new Mysubcategorymodal("Error in call while sending the payment link",Subcategory_back.this),
                        new Mysubcategorymodal("Error in Call after completion of payment by customer",Subcategory_back.this),
                        new Mysubcategorymodal("Tax Details not configured",Subcategory_back.this),
                        new Mysubcategorymodal("Article price 0 (Zero)",Subcategory_back.this),
                        new Mysubcategorymodal("MOP not visible",Subcategory_back.this),
                        new Mysubcategorymodal("Failed to get Response",Subcategory_back.this),
                        new Mysubcategorymodal("Others",Subcategory_back.this),

                };
                MYISSUELISTADAP = new Subcategoryissue(mysubissuelist, Subcategory_back.this);
                recyclerView.setAdapter(MYISSUELISTADAP);
                break;

            case "Hardware Related Issue" :
                mysubissuelist = new Mysubcategorymodal[]{
                        new Mysubcategorymodal("Touch screen not working",Subcategory_back.this),
                        new Mysubcategorymodal("TV Display is Not Working",Subcategory_back.this),
                        new Mysubcategorymodal("Tablet Battery getting drained (Fast Discharge)",Subcategory_back.this),
                        new Mysubcategorymodal("Tablet over heating issue",Subcategory_back.this),
                        new Mysubcategorymodal("Tablet is not getting charged through POS stand",Subcategory_back.this),
                        new Mysubcategorymodal("Cursor visible on tablet screen",Subcategory_back.this),
                        new Mysubcategorymodal("STB Hardware related Issues",Subcategory_back.this),
                        new Mysubcategorymodal("Print not coming from printer",Subcategory_back.this),
                        new Mysubcategorymodal("Others",Subcategory_back.this),

                };
                MYISSUELISTADAP = new Subcategoryissue(mysubissuelist, Subcategory_back.this);
                recyclerView.setAdapter(MYISSUELISTADAP);
                break;

//            case "Scale Fusion Related Issue" :
//                mysubissuelist = new Mysubcategorymodal[]{
//
//                        new Mysubcategorymodal("Others",Subcategory_back.this),
//                };
//                MYISSUELISTADAP = new Subcategoryissue(mysubissuelist, Subcategory_back.this);
//                recyclerView.setAdapter(MYISSUELISTADAP);
//                break;


        }

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


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id =item.getItemId();
        if (id == android.R.id.home){
            Intent intent = new Intent(Subcategory_back.this, MainHomePage.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Subcategory_back.this, MainHomePage.class);
        startActivity(intent);
        super.onBackPressed();
    }
}