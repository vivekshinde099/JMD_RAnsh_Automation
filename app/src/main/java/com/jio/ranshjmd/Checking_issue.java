package com.jio.ranshjmd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.StatFs;
import android.provider.Settings;
import android.text.Html;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.mdminfo.mdmsdk.MDMSDK;

import java.text.DecimalFormat;

import com.jio.ranshjmd.Bottomnavigationbar.MainHomePage;
import com.jio.ranshjmd.Common.Datacontainer;
import com.jio.ranshjmd.Common.Utils;
import com.jio.ranshjmd.RequestAPI.AutoResTicketPostData;
import com.jio.ranshjmd.RequestAPI.CreateTicketPostdata;
import com.jio.ranshjmd.Ticketresults.Ticket_NotResolved;
import com.jio.ranshjmd.databinding.ActivityMainBinding;


import static android.view.View.GONE;
public class Checking_issue extends AppCompatActivity {

    long bytesAvailable, megAvailable;


    //----------------------ProgressBar Initialize-----------------------------------------------

    int intValue = 0;
    int queryresolved = 0;
    int autoresolvercounter = 0;
    Handler handler = new Handler();
    TextView finalticketid;
    int counter = 0;
    Context context;

    DatabaseReference myRef;
    //-----------------------Issue Text Coming from issuepage-----------------------------------

    TextView issuepagetxt;
    TextView steptext1, steptext2, steptext3, steptext4, steptext5, steptext6, steptext7, steptext8, steptext9;
    TextView checkingissue1, checkingissue2, checkingissue3, checkingissue4, checkingissue5, checkingissue6, checkingissue7, checkingissue8, checkingissue9;
    RelativeLayout layout1, layout2, layout3, layout4, layout5, layout6, layout7, layout8, layout9;
    TextView checkingissuedes1, checkingissuedes2, checkingissuedes3, checkingissuedes4, checkingissuedes5, checkingissuedes6, checkingissuedes7, checkingissuedes8, checkingissuedes9;
    Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn10, btn11, btn12, btn13;
    String Issuetitle;

    WifiManager wifiManager;
    TextView WiFi_Status_TextView;
    TextView WiFi_onstate;
    Switch Wifi_switch;
    String xyz;
    String folder, folder1, folder2, folder3, folder4;
    String[] folderlist = {"Jpos_plus_", "Jpos_plus_TxnId", "Jpos_plus_TxnRcpt", "Jpos_plus_Util", "RPOS"};
    PackageInfo versioname;
    long maxid;
    String phonenumber;
    String BTTICKETID;


    SharedPreferences getshared;
    TextView titletxt;
    RelativeLayout linearLayout;
    String closetackbysearchbtn;
    TextView searchbar;
    String textsearch, nprm, nmob, nemail, nname;

    //------------------------------------------------------------------------------------------

    ActivityMainBinding binding;
    MDMSDK.Response response = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checking_issue);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_round_arrow_back_ios_24);
        getSupportActionBar().setTitle("");

        Datacontainer.sharedprefrence(getApplicationContext());

        linearLayout = (RelativeLayout) findViewById(R.id.formlayouttoshow);

        searchbar = findViewById(R.id.seachbartext);
        closetackbysearchbtn = getIntent().getExtras().getString("Close");
        searchbar.setText(closetackbysearchbtn);
        textsearch = searchbar.getText().toString().toLowerCase();


        titletxt = findViewById(R.id.titletxt);


        StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
        bytesAvailable = statFs.getBlockSizeLong() * statFs.getAvailableBlocksLong();

        megAvailable = bytesAvailable / (1024 * 1024 * 1024);
        {
            layout1 = (RelativeLayout) findViewById(R.id.unique1);
            layout2 = (RelativeLayout) findViewById(R.id.unique2);
            layout3 = (RelativeLayout) findViewById(R.id.unique3);
            layout4 = (RelativeLayout) findViewById(R.id.unique4);
            layout5 = (RelativeLayout) findViewById(R.id.unique5);
            layout6 = (RelativeLayout) findViewById(R.id.twobtn1);
            layout7 = (RelativeLayout) findViewById(R.id.twobtn2);
            layout8 = (RelativeLayout) findViewById(R.id.unique6);
            layout9 = (RelativeLayout) findViewById(R.id.unique7);

            checkingissuedes1 = (TextView) layout1.findViewById(R.id.checkingissuedes1);
            checkingissuedes2 = (TextView) layout2.findViewById(R.id.checkingissuedes2);
            checkingissuedes3 = (TextView) layout3.findViewById(R.id.checkingissuedes3);
            checkingissuedes4 = (TextView) layout4.findViewById(R.id.checkingissuedes4);
            checkingissuedes5 = (TextView) layout5.findViewById(R.id.checkingissuedes5);
            checkingissuedes6 = (TextView) layout6.findViewById(R.id.checkingissuedes6);
            checkingissuedes7 = (TextView) layout7.findViewById(R.id.checkingissuedes7);
            checkingissuedes8 = (TextView) layout8.findViewById(R.id.checkingissuedes8);
            checkingissuedes9 = (TextView) layout9.findViewById(R.id.checkingissuedes9);
            checkingissue1 = (TextView) layout1.findViewById(R.id.checkingissue1);
            checkingissue2 = (TextView) layout2.findViewById(R.id.checkingissue2);
            checkingissue3 = (TextView) layout3.findViewById(R.id.checkingissue3);
            checkingissue4 = (TextView) layout4.findViewById(R.id.checkingissue4);
            checkingissue5 = (TextView) layout5.findViewById(R.id.checkingissue5);
            checkingissue6 = (TextView) layout6.findViewById(R.id.checkingissue6);
            checkingissue7 = (TextView) layout7.findViewById(R.id.checkingissue7);
            checkingissue8 = (TextView) layout8.findViewById(R.id.checkingissue8);
            checkingissue9 = (TextView) layout9.findViewById(R.id.checkingissue9);
            steptext1 = (TextView) layout1.findViewById(R.id.step1txt);
            steptext2 = (TextView) layout2.findViewById(R.id.step2txt);
            steptext3 = (TextView) layout3.findViewById(R.id.step3txt);
            steptext4 = (TextView) layout4.findViewById(R.id.step4txt);
            steptext5 = (TextView) layout5.findViewById(R.id.step5txt);
            steptext6 = (TextView) layout6.findViewById(R.id.step6txt);
            steptext7 = (TextView) layout7.findViewById(R.id.step7txt);
            steptext8 = (TextView) layout8.findViewById(R.id.step8txt);
            steptext9 = (TextView) layout9.findViewById(R.id.step9txt);

            btn1 = (Button) layout1.findViewById(R.id.button1);
            btn2 = (Button) layout2.findViewById(R.id.button2);
            btn3 = (Button) layout3.findViewById(R.id.button3);
            btn4 = (Button) layout4.findViewById(R.id.button4);
            btn5 = (Button) layout5.findViewById(R.id.button5);
            btn6 = (Button) layout6.findViewById(R.id.buttonyes6);
            btn7 = (Button) layout6.findViewById(R.id.buttonno6);
            btn8 = (Button) layout7.findViewById(R.id.buttonyes7);
            btn9 = (Button) layout7.findViewById(R.id.buttonno7);
            btn10 = (Button) layout8.findViewById(R.id.button8);
            btn11 = (Button) layout9.findViewById(R.id.button9);
            btn12 = (Button) layout9.findViewById(R.id.button10);
            btn13 = (Button) layout9.findViewById(R.id.button11);
        }
        getshared = getSharedPreferences("Data", MODE_PRIVATE);
        nprm = getshared.getString("strprm", "PRM");
        nmob = getshared.getString("strmob", "Mobile Number");
        nemail = getshared.getString("stremail", "Email ID");
        nname = getshared.getString("strname", "Name");

        Handler handler = new Handler();
        setContentView(this, R.layout.activity_checking_issue);
        binding = ActivityMainBinding.inflate(getLayoutInflater());

        versioname = Utils.getversionname("com.jio.jpp1", getPackageManager());

        issuepagetxt = (TextView) findViewById(R.id.issuenametxt);
        String value = getIntent().getExtras().getString("A");
        issuepagetxt.setText(value);
        xyz = issuepagetxt.getText().toString().toLowerCase();


        if (xyz.contains("504 gateway timeout")) {
            titletxt.setText("504 gateway timeout");
            checkingissue1.setText(Html.fromHtml("Click below to log a complaint ."));
            directlycreateticket();
        } else if (xyz.contains("404 app config not found")) {
            titletxt.setText("404 app config not found");
            checkingissue1.setText(Html.fromHtml("Click below to log a complaint ."));
            directlycreateticket();
        } else if (xyz.contains("gst table does not exist. do you want to try again?")) {
            titletxt.setText("Gst Table Does not exist. Do you want to try again?");
            checkingissue1.setText("If Onboarding time is between 24 to 48 hours - then kindly wait till 48 hours .");
            checkingissuedes1.setText(Html.fromHtml("If Onboarding is more than 48Hrs Kindly perform below steps"));

            textid1();
            btn1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    layout2.setVisibility(View.VISIBLE);
                    steptext2.setText("Step 2");
                    checkingissue2.setText(R.string.rpos_jpos_folder_delete);
                    //


                    textid2();
                    btn2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Datacontainer.deletefolderusingpath();


                            if (!Datacontainer.folderdeletesuccess) {
                                Toast.makeText(getApplicationContext(), "No Folder Found", Toast.LENGTH_SHORT).show();
                                checkingissuedes2.setText("No folder's found");
                                layout3.setVisibility(View.VISIBLE);
                                steptext3.setText("Step 3");
                                checkingissue3.setVisibility(GONE);
                                checkingissuedes3.setText(R.string.clear_cache_clear_data);

                                textid3();
                                btn3.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        openapplicationsetting();
                                        Toast.makeText(Checking_issue.this, "Kindly click on Storage to do clear cache/Data", Toast.LENGTH_LONG).show();
                                        layout4.setVisibility(View.VISIBLE);
                                        steptext4.setText("Step 4");
                                        checkingissue4.setText(R.string.data_delta_sync);
                                        checkingissuedes4.setVisibility(GONE);
                                        btn4.setVisibility(View.VISIBLE);


                                        textid4();
                                        btn4.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                openapplicationfordatasync();
                                                Runnable r = new Runnable() {
                                                    public void run() {
                                                        trytologin_check();
                                                    }
                                                };
                                                handler.postDelayed(r, 2000);
                                            }
                                        });


                                    }
                                });
                            } else {
                                Toast.makeText(getApplicationContext(), "Deleted", Toast.LENGTH_SHORT).show();

                                checkingissuedes2.setText("RPOS/JPOS folder's deleted");
                                layout3.setVisibility(View.VISIBLE);
                                steptext3.setText("Step 3");
                                checkingissue3.setVisibility(GONE);
                                checkingissuedes3.setText(R.string.clear_cache_clear_data);

                                textid3();
                                btn3.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {

                                        openapplicationsetting();
                                        Toast.makeText(Checking_issue.this, "Kindly click on Storage to do clear cache/Data", Toast.LENGTH_LONG).show();
                                        layout4.setVisibility(View.VISIBLE);
                                        steptext4.setText("Step 4");
                                        checkingissue4.setText(R.string.data_delta_sync);
                                        checkingissuedes4.setVisibility(GONE);
                                        btn4.setVisibility(View.VISIBLE);

                                        textid4();
                                        btn4.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                openapplicationfordatasync();
                                                Runnable r = new Runnable() {
                                                    public void run() {
                                                        trytologin_check();
                                                    }
                                                };
                                                handler.postDelayed(r, 2000);
                                            }
                                        });
                                    }
                                });
                            }
                        }
                    });
                }
            });

        }

        //---------------------------------------------Jio POS Plus - Payment deducted, EJ not generated-------------//
        else if (xyz.contains("payment deducted, ej not generated")) {
            titletxt.setText("Payment deducted, EJ not generated");
            btn1.setVisibility(View.VISIBLE);
            layout1.setVisibility(View.VISIBLE);
            steptext1.setText("Step 1");
            checkingissue1.setText("Login in Jio POS Plus and click on “Online Order (MASP)” option. Enter the customer’s mobile no. which was used previously to place the order.");
            checkingissuedes1.setText("Complete the billing process by sending a Payment Link to the customer but ensure that customer does not click on Payment Link again. (As user has already completed the payment process)");
            checkingissue1.setVisibility(View.VISIBLE);
            textid1();
            btn1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    layout2.setVisibility(View.VISIBLE);
                    steptext2.setText("Step 2");
                    checkingissue2.setText("Click on “Check Status” option in Jio POS Plus to retrieve the Order Status.");
                    checkingissuedes2.setText("If previous transaction was completed successfully, then EJ ticket will be generated. In case article price is now changed in Jio POS Plus then you might face an error as – “Amount mismatch between Online payment and Article amount” after trying to click on “Check Status”.");
                    textid2();
                    btn2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            layout3.setVisibility(View.VISIBLE);
                            steptext3.setText("Step 3");
                            checkingissue3.setText("If you are getting error as “Payment Not Found” while clicking on “Check Status” option, then inform customer if payment is deducted then he will get the refund, You can also raise OC for this.");
                            checkingissuedes3.setText("If customer wants to complete this transaction, then please re-initiate the transaction.");
                            textid3();
                            btn3.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Runnable r = new Runnable() {
                                        public void run() {
                                            trytologin_check();
                                        }
                                    };
                                    handler.postDelayed(r, 1000);
                                }
                            });
                        }
                    });
                }
            });
        }


        //---------------------------------------------Jio Phone Next - You cannot sell this device from device sale-------//
        else if (xyz.contains("you cannot sell this device from device sale")) {
            titletxt.setText("You cannot sell this device from device sale");
            btn1.setVisibility(View.VISIBLE);
            layout1.setVisibility(View.VISIBLE);
            steptext1.setText("Step 1");
            checkingissue1.setText("Kindly add EAN Number of the Jio Phone Next device in Jio POS Plus by following below process:");
            checkingissuedes1.setText("EAN number is available on every Jio Phone Next box. Go to Jio POS Plus>>Menu>>Utilities>>Missing Data Utility>>select Product option from dropdown menu and enter the EAN number to added.");

            textid1();
            btn1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    layout2.setVisibility(View.VISIBLE);
                    steptext2.setText("Step 2");
                    checkingissue2.setText("Click below button to perform basic troubleshooting like:");
                    checkingissuedes2.setText("Deleting RPOS folders \\nReinstalling JIO POS plus");
                    textid2();
                    btn2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            masterTroubleshooting();
                            layout3.setVisibility(View.VISIBLE);
                            steptext3.setText("Step 3");
                            checkingissue3.setText("Click below to refresh policies.");
                            textid3();
                            btn3.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    refreshPolicies(view);
                                    Runnable r = new Runnable() {
                                        public void run() {
                                            trytologin_check();
                                        }
                                    };
                                    handler.postDelayed(r, 3000);
                                }
                            });
                        }
                    });
                }
            });
        }

        //---------------------------------------------Tablet is not getting charged through POS stand------------------//
        else if (xyz.contains("tablet is not getting charged through pos stand")) {
            titletxt.setText("Tablet is not getting charged through POS stand");
            btn1.setVisibility(View.VISIBLE);
            layout1.setVisibility(View.VISIBLE);
            steptext1.setText("Step 1");
            checkingissue1.setText("Make sure electrical power socket is working and the switch is turned on, Connect the POS stand to electrical power socket with working cable.");
            checkingissuedes1.setText("Make sure the POS stand power switch is turned ON, Hold the tablet properly and ensure it is properly docked.");
            textid1();
            btn1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    layout2.setVisibility(View.VISIBLE);
                    steptext2.setText("Step 2");
                    checkingissue2.setText("When properly docked - it should blink blue LED light on the POS stand.");
                    checkingissuedes2.setText("Connect the tablet charging cable to recommended USB port (labelled as tablet power only). If the RED LED light glows on the tablet it means that the tablet is getting charged via the POS stand.");
                    textid2();
                    btn2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            layout3.setVisibility(View.VISIBLE);
                            steptext3.setText("Step 3");
                            checkingissue3.setText("If the charging LED light is not showing – connect a different USB cable and check, If the charging LED is glowing, then power on by pressing power button.");
                            checkingissuedes2.setText("If there is no charging LED indicator – try charging the tablet through charging adapter, If the tablet is still not getting charged - possibly issue with the USB port on the POS stand. Replace the POS stand with the help of JMD lead / officer.");
                            textid3();
                            btn3.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Runnable r = new Runnable() {
                                        public void run() {
                                            trytologin_check();
                                        }
                                    };
                                    handler.postDelayed(r, 1000);
                                }
                            });
                        }
                    });
                }
            });
        }

        //---------------------------------------------Cursor visible on tablet screen------------------//
        else if (xyz.contains("cursor visible on tablet screen")) {
            titletxt.setText("Cursor visible on tablet screen");
            btn1.setVisibility(View.VISIBLE);
            layout1.setVisibility(View.VISIBLE);
            steptext1.setText("Step 1");
            checkingissue1.setText("Ensure there is proper network connectivity at your end, Kindly  click on 3 dots in top right corner of the tablet, and then click on Refresh.");

            textid1();
            btn1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    layout2.setVisibility(View.VISIBLE);
                    steptext2.setText("Step 2");
                    checkingissue2.setText("Kindly switch OFF the tablet and switch it ON again and re-try.");
                    //checkingissuedes2.setText(" restart the device and check again.");
                    textid2();
                    btn2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Runnable r = new Runnable() {
                                public void run() {
                                    trytologin_check();
                                }
                            };
                            handler.postDelayed(r, 1000);

                        }
                    });
                }
            });
        }
        //-------------------------------------Port is faulty / STB not powering ON / Remote is not working-----------------


        else if (xyz.contains("stb hardware related issues")) {
            titletxt.setText("STB Hardware related Issues");
            btn1.setVisibility(View.VISIBLE);
            layout1.setVisibility(View.VISIBLE);
            steptext1.setText("Step 1");
            checkingissue1.setText("Cable /Port faulty – \n A.switch the HDMI cable with monitor port and connect again.\n");
            checkingissuedes1.setText("B.connect the HDMI cable with some other device and check. If cable is working fine then check the port and confirm if there is any damage. \nC.Capture the details whether cable is faulty or STB port is faulty as per the above checks");
            textid1();
            btn1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    layout2.setVisibility(View.VISIBLE);
                    steptext2.setText("Step 2");
                    checkingissue2.setText("STB not powering ON – \n A.Ensure that power cable is connected to the switch and it is ON.\n");
                    checkingissuedes2.setText("B.If red and green light is reflecting on STB then it is getting switched ON, \nC.If no lights are getting visible on STB then Powering ON issue is there.");
                    textid2();
                    btn2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            layout3.setVisibility(View.VISIBLE);
                            steptext3.setText("Step 3");
                            checkingissue3.setText("Remote not working –\n A.Ensure battery is inserted in the remote \n");
                            checkingissuedes3.setText("B.Red light should bling on the top while pressing any button on remote.");
                            textid3();
                            btn3.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Runnable r = new Runnable() {
                                        public void run() {
                                            trytologin_check();
                                        }
                                    };
                                    handler.postDelayed(r, 1000);
                                }
                            });
                        }
                    });
                }
            });
        }

        //--------------------------------------------Jio Phone Next - Your loan application is under process. Please try after sometime------------------//
        else if (xyz.contains("your loan application is under process. please try after sometime")) {
            titletxt.setText("Your loan application is under process. Please try after sometime");
            btn1.setVisibility(View.VISIBLE);
            layout1.setVisibility(View.VISIBLE);
            steptext1.setText("Step 1");
            checkingissue1.setText("Loan application approval process requires few seconds in order to be completed. Kindly click on Retry after 30 seconds and check again, issue will be resolved.");
            checkingissue1.setVisibility(View.VISIBLE);
            textid1();
            btn1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Runnable r = new Runnable() {
                        public void run() {
                            trytologin_check();
                        }
                    };
                    handler.postDelayed(r, 1000);
                }
            });
        }

        //------------------------- Failed to configure RPOS.db--------------------------------------------

        else if (xyz.contains("failed to configure rpos.db")) {
            titletxt.setText("Failed to configure RPOS.db");
            btn1.setVisibility(View.VISIBLE);
            layout1.setVisibility(View.VISIBLE);
            steptext1.setText("Step 1");
            checkingissue1.setText("Need to Delete Cache and Data of Jio Pos Plus application,Please click on below button");
            checkingissue1.setVisibility(View.VISIBLE);
            textid1();
            btn1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openapplicationsetting();
                    layout2.setVisibility(View.VISIBLE);
                    steptext2.setText("Step 1");
                    checkingissue2.setText("Need to do Data/Delta Sync from Jio Pos Plus application,Please click on below button");
                    btn2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            openapplicationfordatasync();
                            Runnable r = new Runnable() {
                                public void run() {
                                    trytologin_check();
                                }
                            };
                            handler.postDelayed(r, 1000);

                        }
                    });

                }
            });
        }

        //--------------------------------------------Printer Pairing Issue------------------//
//        else if (xyz.contains("printer pairing issue")) {
//            titletxt.setText("Printer Pairing Issue");
//            btn1.setVisibility(View.VISIBLE);
//            layout1.setVisibility(View.VISIBLE);
//            steptext1.setText("Step 1");
//            checkingissue1.setText("Ensure that Bluetooth is on for both printer and device.");
//            checkingissuedes2.setText("Restart the Bluetooth of Paired device, Try to Reconnect");
//            checkingissue1.setVisibility(View.VISIBLE);
//            textid1();
//            btn1.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Runnable r = new Runnable() {
//                        public void run() {
//                            trytologin_check();
//                        }
//                    };
//                    handler.postDelayed(r, 1000);
//                }
//            });
//        }

        //--------------------------------------------Printer Pairing Issue------------------//
        else if (xyz.contains("print not coming from printer")) {
            titletxt.setText("Print not coming from printer");
            btn1.setVisibility(View.VISIBLE);
            layout1.setVisibility(View.VISIBLE);
            steptext1.setText("Step 1");
            checkingissue1.setText("Make sure printer is turned ON, Also Check printer is connected to device or stand.");
            checkingissuedes2.setText("Check Paper roll and any jam issues, Hold and press power button for 3 sec if the printer prints test page,Check printer is not set for offline mode.");
            checkingissue1.setVisibility(View.VISIBLE);
            textid1();
            btn1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Runnable r = new Runnable() {
                        public void run() {
                            trytologin_check();
                        }
                    };
                    handler.postDelayed(r, 1000);
                }
            });
        }

        //-----------------------------------TV Display is Not Working---------------------------------------//

        else if (xyz.contains("tv display is not working")) {
            titletxt.setText("TV Display is Not Working");
            btn1.setVisibility(View.VISIBLE);
            layout1.setVisibility(View.VISIBLE);
            steptext1.setText("Step 1");
            checkingissue1.setText("Check power is connected and switch ON");
            checkingissuedes1.setText("Check if VGA/HDMI/Input cable is properly connected and is working, Please restart the device.");
            textid1();
            btn1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Runnable r = new Runnable() {
                        public void run() {
                            trytologin_check();
                        }
                    };
                    handler.postDelayed(r, 1000);
                }
            });
        }

//-----------------------------------Tablet over heating issue---------------------------------------//

        else if (xyz.contains("tablet over heating issue")) {
            titletxt.setText("Tablet over heating issue");
            btn1.setVisibility(View.VISIBLE);
            layout1.setVisibility(View.VISIBLE);
            steptext1.setText("Step 1");
            checkingissue1.setText("Turn off the tablet for sometime and then restart, Please check and close all open applications.");
            checkingissuedes1.setText("Do not overcharge the tablet, once charging is done please remove from adaptor, Also Check for free memory on the device");
            textid1();
            btn1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Runnable r = new Runnable() {
                        public void run() {
                            trytologin_check();
                        }
                    };
                    handler.postDelayed(r, 1000);
                }
            });
        }


        //---------------------------------------------Jio Phone Next - JIO POS Plus - Number is not eligible------------//

        else if (xyz.contains("number is not eligible")) {
            titletxt.setText("Number is not eligible");
            btn1.setVisibility(View.VISIBLE);
            layout1.setVisibility(View.VISIBLE);
            steptext1.setText("Step 1");
            checkingissue1.setText("Ensure that you have completed the Lead Generation Process for Jio Phone Next. If lead is not generated, then kindly generate the lead 1st.");
            checkingissue1.setVisibility(View.VISIBLE);
            textid1();
            btn1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    layout2.setVisibility(View.VISIBLE);
                    steptext2.setText("Step 2");
                    checkingissue2.setText("Ensure to enter customer's correct mobile number which was used to generate the lead.");
                    checkingissuedes2.setText("If issue still persists, kindly contact your respective JMD Officers/JMD Lead.");
                    textid2();
                    btn2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Runnable r = new Runnable() {
                                public void run() {
                                    trytologin_check();
                                }
                            };
                            handler.postDelayed(r, 1000);
                        }
                    });
                }
            });
        }

        //---------------------------------------------Jio Phone Next - We are processing your data. Please wait for some time and retry-----//

        else if (xyz.contains("we are processing your data. please wait for some time and retry")) {
            titletxt.setText("We are processing your data. Please wait for some time and retry");
            btn1.setVisibility(View.VISIBLE);
            layout1.setVisibility(View.VISIBLE);
            steptext1.setText("Step 1");
            checkingissue1.setText("Kindly click on E-mandate option after 2 minutes and check again, issue will be resolved.");
            checkingissue1.setVisibility(View.VISIBLE);
            textid1();
            btn1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Runnable r = new Runnable() {
                        public void run() {
                            trytologin_check();
                        }
                    };
                    handler.postDelayed(r, 1000);
                }
            });
        }

        //--------------------------------------------Jio Phone Next - Screen goes blank after selecting Bundle Phone option-------//

        else if (xyz.contains("screen goes blank after selecting bundle phone option")) {
            titletxt.setText("Screen goes blank after selecting Bundle Phone option");
            btn1.setVisibility(View.VISIBLE);
            layout1.setVisibility(View.VISIBLE);
            steptext1.setText("Step 1");
            checkingissue1.setText("Kindly use latest version of Jio POS Plus in 10-inch tablet only.");
            checkingissue1.setVisibility(View.VISIBLE);
            textid1();
            btn1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Runnable r = new Runnable() {
                        public void run() {
                            trytologin_check();
                        }
                    };
                    handler.postDelayed(r, 1000);
                }
            });
        }


        //--------------------------------------------Jio Phone Next- Your loan application is rejected - APPROVAL CRITERIA NOT MET---------------//

        else if (xyz.contains("your loan application is rejected - approval criteria not met")) {
            titletxt.setText("Your loan application is rejected - APPROVAL CRITERIA NOT MET");
            btn1.setVisibility(View.VISIBLE);
            layout1.setVisibility(View.VISIBLE);
            steptext1.setText("Step 1");
            checkingissue1.setText("In case of any queries related to loan application, please contact your respective Business Team.");
            checkingissue1.setVisibility(View.VISIBLE);
            textid1();
            btn1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Runnable r = new Runnable() {
                        public void run() {
                            trytologin_check();
                        }
                    };
                    handler.postDelayed(r, 1000);
                }
            });
        }

        //------------------------------------------Jio Phone Next - 504 gateway time out--------------------//

        else if (xyz.contains("504 gateway time out")) {
            titletxt.setText("504 gateway time out");
            btn1.setVisibility(View.VISIBLE);
            layout1.setVisibility(View.VISIBLE);
            steptext1.setText("Step 1");
            checkingissue1.setText("KYC process requires few minutes in order to be completed, click on Try Again option after 2 minutes and check again, issue will be resolved.");
            checkingissue1.setVisibility(View.VISIBLE);
            textid1();
            btn1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Runnable r = new Runnable() {
                        public void run() {
                            trytologin_check();
                        }
                    };
                    handler.postDelayed(r, 1000);
                }
            });
        }


        //---------------------------------------------Jio Phone Next- KYC failed--------------------------//

        else if (xyz.contains("kyc failed")) {
            titletxt.setText("KYC failed");
            btn1.setVisibility(View.VISIBLE);
            layout1.setVisibility(View.VISIBLE);
            steptext1.setText("Step 1");
            checkingissue1.setText("KYC process requires few minutes in order to be completed, click on retry option and initiate the process once again.");
            checkingissue1.setVisibility(View.VISIBLE);
            textid1();
            btn1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Runnable r = new Runnable() {
                        public void run() {
                            trytologin_check();
                        }
                    };
                    handler.postDelayed(r, 1000);
                }
            });
        }


        //---------------------------------------------Jio Phone Next- Some technical error occurred-------------------//

        else if (xyz.contains("some technical error occurred")) {
            titletxt.setText("Some technical error occurred");
            btn1.setVisibility(View.VISIBLE);
            layout1.setVisibility(View.VISIBLE);
            steptext1.setText("Step 1");
            checkingissue1.setText("KYC process requires few minutes in order to be completed, click on Try Again option after 2 minutes and check again, issue will be resolved.");
            checkingissue1.setVisibility(View.VISIBLE);
            textid1();
            btn1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Runnable r = new Runnable() {
                        public void run() {
                            trytologin_check();
                        }
                    };
                    handler.postDelayed(r, 1000);
                }
            });
        }

        //---------------------------------------------Jio Pos Plus - Amount requested does not match online payment--------//

        else if (xyz.contains("amount requested does not match online payment")) {
            titletxt.setText("Amount requested does not match online payment");
            btn1.setVisibility(View.VISIBLE);
            layout1.setVisibility(View.VISIBLE);
            steptext1.setText("Step 1");
            checkingissue1.setText("Kindly perform billing through latest / updated version of JIO POS Plus from Tablet.");
            checkingissuedes1.setText("To update Jio Pos, kindly click on 3 dots option on tab and click on refresh option.");
            textid1();
            btn1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Runnable r = new Runnable() {
                        public void run() {
                            trytologin_check();
                        }
                    };
                    handler.postDelayed(r, 1000);
                }
            });
        }
        //---------------------------------------------Jio POS Plus - OTP could not be received to Agent-------------//
        else if (xyz.contains("otp could not be received")) {
            titletxt.setText("OTP could not be received");
            btn1.setVisibility(View.VISIBLE);
            layout1.setVisibility(View.VISIBLE);
            steptext1.setText("Step 1");
            checkingissue1.setText("Ensure the number entered in MASP is same which is used in Jio Mart Digital application.");
            //checkingissuedes1.setText("To check kindly go to JPP menu >> Settings >> Register Your Mobile.");
            checkingissue1.setVisibility(View.VISIBLE);
            textid1();
            btn1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    layout2.setVisibility(View.VISIBLE);
                    steptext2.setText("Step 2");
                    checkingissue2.setText("Login credentials of Jio Mart Digital and Jio Pos Plus has to be same.");
                    //checkingissuedes2.setText("If Incorrect then change the number to Sim 1 Jio number and click on send OTP and enter OTP received.");
                    textid2();
                    btn2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Runnable r = new Runnable() {
                                public void run() {
                                    trytologin_check();
                                }
                            };
                            handler.postDelayed(r, 1000);

                        }
                    });
                }
            });
        }


        //---------------------------------------------Jio POS Plus - You cannot void selected article--------------------------//
        else if (xyz.contains("you cannot void selected article")) {
            titletxt.setText("You cannot void selected article");
            btn1.setVisibility(View.VISIBLE);
            layout1.setVisibility(View.VISIBLE);
            steptext1.setText("Step 1");
            checkingissue1.setText("Certain article which are bundled with parent article cannot be deleted in POS.");
            checkingissue1.setVisibility(View.VISIBLE);
            textid1();
            btn1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    layout2.setVisibility(View.VISIBLE);
                    steptext2.setText("Step 2");
                    checkingissue2.setText("If you still want to delete the article, then kindly get in touch with JMD Officer/JMD Lead.");
                    textid2();
                    btn2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Runnable r = new Runnable() {
                                public void run() {
                                    trytologin_check();
                                }
                            };
                            handler.postDelayed(r, 1000);
                        }
                    });
                }
            });
        }


        //---------------------------------------------Jio POS Plus – Failed to get Response------------------------------//
        else if (xyz.contains("failed to get response")) {
            titletxt.setText("Failed to get Response");
            btn1.setVisibility(View.VISIBLE);
            layout1.setVisibility(View.VISIBLE);
            steptext1.setText("Step 1");
            checkingissue1.setText("Press Cancel option available on the error message.");
            checkingissue1.setVisibility(View.VISIBLE);
            textid1();
            btn1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    layout2.setVisibility(View.VISIBLE);
                    steptext2.setText("Step 2");
                    checkingissue2.setText("Click below button to perform basic troubleshooting like:");
                    checkingissuedes2.setText("Deleting RPOS folders \\nReinstalling JIO POS plus");
                    textid2();
                    btn2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            masterTroubleshooting();
                            layout3.setVisibility(View.VISIBLE);
                            steptext3.setText("Step 3");
                            checkingissue3.setText("Click below to refresh policies.");
                            textid3();
                            btn3.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    refreshPolicies(view);
                                    Runnable r = new Runnable() {
                                        public void run() {
                                            trytologin_check();
                                        }
                                    };
                                    handler.postDelayed(r, 3000);
                                }
                            });
                        }
                    });
                }
            });
        }
        //--------------------------------------------Jio POS Plus - MOP not visible---------------------------------------//
        else if (xyz.contains("mop not visible")) {
            titletxt.setText("MOP not visible");
            btn1.setVisibility(View.VISIBLE);
            layout1.setVisibility(View.VISIBLE);
            steptext1.setText("Step 1");
            checkingissue1.setText("As of now, payment via link and cash are the only payment options available in Jio POS Plus.");
            checkingissuedes1.setText("Cash option is not available for GT(Partners) users.");
            checkingissue1.setVisibility(View.VISIBLE);
            textid1();
            btn1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    layout2.setVisibility(View.VISIBLE);
                    steptext2.setText("Step 2");
                    checkingissue2.setText("If payment via link or cash option is not visible,");
                    checkingissuedes2.setText("Click below button to perform basic troubleshooting like:\nDeleting RPOS folders... \nReinstalling JIO POS plus...");
                    textid2();
                    btn2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            masterTroubleshooting();
                            layout3.setVisibility(View.VISIBLE);
                            steptext3.setText("Step 2");
                            // checkingissue3.setText("Need to do Data/Delta Sync from Jio Pos Plus application,Please click on below button");
                            checkingissuedes3.setText("Click below to refresh policies.");
                            textid3();
                            btn3.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    refreshPolicies(v);
                                    Runnable r = new Runnable() {
                                        public void run() {
                                            trytologin_check();
                                        }
                                    };
                                    handler.postDelayed(r, 3000);
                                }
                            });

                        }
                    });
                }
            });
        }

        //--------------------------------------------- Jio POS Plus - Article prize 0 (Zero)---------------------------//
        else if (xyz.contains("article price 0 (zero)")) {
            titletxt.setText("Article price 0 (Zero)");
            btn1.setVisibility(View.VISIBLE);
            layout1.setVisibility(View.VISIBLE);
            steptext1.setText("Step 1");
            checkingissue1.setText("Need to Delete Cache and Data of Jio Pos Plus application,Please click on below button");
            checkingissue1.setVisibility(View.VISIBLE);
            textid1();
            btn1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openapplicationsetting();
                    layout2.setVisibility(View.VISIBLE);
                    steptext2.setText("Step 2");
                    checkingissue2.setText("Need to do Data/Delta Sync from Jio Pos Plus application,Please click on below button");

                    textid2();
                    btn2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            openapplicationfordatasync();
                            Runnable r = new Runnable() {
                                public void run() {
                                    trytologin_check();
                                }
                            };
                            handler.postDelayed(r, 1000);
                        }
                    });
                }
            });
        }


        //--------------------------------------------Jio POS Plus - Tax Details not configured-----------------------------//

        else if (xyz.contains("tax details not configured")) {
            titletxt.setText("Tax Details not configured");
            layout1.setVisibility(View.VISIBLE);
            steptext1.setText("Step 1");
            checkingissue1.setText("Click below button to perform basic troubleshooting like:");
            checkingissue1.setVisibility(View.VISIBLE);
            checkingissuedes1.setText("Deleting RPOS folders \nReinstalling JIO POS plus");
            textid1();
            btn1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    masterTroubleshooting();
                    layout2.setVisibility(View.VISIBLE);
                    steptext2.setText("Step 2");
                    // checkingissue2.setText("Need to do Data/Delta Sync from Jio Pos Plus application,Please click on below button");
                    checkingissuedes2.setText("Click below to refresh policies.");
                    textid2();
                    btn2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            refreshPolicies(view);
                            Runnable r = new Runnable() {
                                public void run() {
                                    trytologin_check();
                                }
                            };
                            handler.postDelayed(r, 3000);
                        }
                    });
                }
            });
        }


        //-----------------------------------------“Error in Call” after completion of payment by customer----------------------//

        else if (xyz.contains("error in call after completion of payment by customer")) {
            titletxt.setText("Error in Call after completion of payment by customer");
            btn1.setVisibility(View.VISIBLE);
            layout1.setVisibility(View.VISIBLE);
            steptext1.setText("Step 1");
            checkingissue1.setText("Kindly click on Check Status once again to confirm, If you still face the same issue kindly click on below button.");
            checkingissue1.setVisibility(View.VISIBLE);
            textid1();
            btn1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Runnable r = new Runnable() {
                        public void run() {
                            trytologin_check();
                        }
                    };
                    handler.postDelayed(r, 1000);
                }
            });
        }


        //-----------------------------------------“Error In Call” While sending the Payment Link----------------------//
        else if (xyz.contains("error In call while sending the payment link")) {
            titletxt.setText("Error in call while sending the payment link");
            btn1.setVisibility(View.VISIBLE);
            layout1.setVisibility(View.VISIBLE);
            steptext1.setText("Step 1");
            checkingissue1.setText("Need to Delete Cache and Data of Jio Pos Plus application,Please click on below button");
            checkingissue1.setVisibility(View.VISIBLE);
            textid1();
            btn1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openapplicationsetting();
                    layout2.setVisibility(View.VISIBLE);
                    steptext2.setText("Step 2");
                    checkingissue2.setText("Now click on 3 button option on right hand upper corner on main page of tab >> click on refresh option >> App will update automatically.");
                    textid2();
                    btn2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Runnable r = new Runnable() {
                                public void run() {
                                    trytologin_check();
                                }
                            };
                            handler.postDelayed(r, 1000);
                        }
                    });

                }
            });
        }

        //---------------------------------------------Jio POS Plus - Pincode not found---------------------------------------//

        else if (xyz.contains("pincode not found")) {
            titletxt.setText("Pincode Not Found");
            layout1.setVisibility(View.VISIBLE);
            steptext1.setText("Step 1");
            checkingissue1.setText("Click below button to perform basic troubleshooting like:");
            checkingissue1.setVisibility(View.VISIBLE);
            checkingissuedes1.setText("Deleting RPOS folders \nReinstalling JIO POS plus");
            textid1();
            btn1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    masterTroubleshooting();
                    layout2.setVisibility(View.VISIBLE);
                    steptext2.setText("Step 2");
                    // checkingissue2.setText("Need to do Data/Delta Sync from Jio Pos Plus application,Please click on below button");
                    checkingissuedes2.setText("Click below to refresh policies.");
                    textid2();
                    btn2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            refreshPolicies(view);
                            Runnable r = new Runnable() {
                                public void run() {
                                    trytologin_check();
                                }
                            };
                            handler.postDelayed(r, 3000);
                        }
                    });
                }
            });
        }


        //---------------------------------------------Jio POS Plus - Item Not Found------------------------------------------//

        else if (xyz.contains("item not found")) {
            titletxt.setText("Item Not Found");
            btn1.setVisibility(View.VISIBLE);
            layout1.setVisibility(View.VISIBLE);
            steptext1.setText("Step 1");
            checkingissue1.setText("Click below button to perform basic troubleshooting like:");
            checkingissue1.setVisibility(View.VISIBLE);
            checkingissuedes1.setText("Deleting RPOS folders \nReinstalling JIO POS plus");
            textid1();
            btn1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   masterTroubleshooting();
                    layout2.setVisibility(View.VISIBLE);
                    steptext2.setText("Step 2");
                    // checkingissue2.setText("Need to do Data/Delta Sync from Jio Pos Plus application,Please click on below button");
                    checkingissuedes2.setText("Click below to refresh policies.");
                    textid2();
                    btn2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            refreshPolicies(view);
                            Runnable r = new Runnable() {
                                public void run() {
                                    trytologin_check();
                                }
                            };
                            handler.postDelayed(r, 3000);

                        }
                    });
                }
            });
        } else if (xyz.contains("agent authentication failed")) {
            titletxt.setText("Agent Authentication Failed from OID");
            steptext1.setText("Step 1");
            checkingissue1.setVisibility(GONE);
            checkingissuedes1.setText(Html.fromHtml("Click on below to reset Password :"));
            // checkingissuedes1.setMovementMethod(LinkMovementMethod.getInstance());
            btn1.setVisibility(View.VISIBLE);
            btn1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    show_dialog_webview();
                    layout6.setVisibility(View.VISIBLE);
                    steptext6.setText("Step 2");
                    checkingissue6.setVisibility(GONE);
                    checkingissuedes6.setText("Have you reset the Password ?");

                    textid6();
                    btn6.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Runnable r = new Runnable() {
                                public void run() {
                                    trytologin_check();
                                }
                            };
                            handler.postDelayed(r, 1000);
                        }
                    });
                    textid6();
                    btn7.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(getApplicationContext(), "Kindly click on above button to reset Password", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            });
        }
        //--------------------------------------Session Expired -------------------------------

        else if (xyz.contains("session expired")) {
            titletxt.setText("Session Expired");
            steptext1.setText("Step 1");
            checkingissue1.setText("login in JioMart Digital application in any one device (Either in Tablet or in Touch Screen TV) with your credentials and check.");
            checkingissuedes1.setText(Html.fromHtml("Click on below to reset Password :"));
            // checkingissuedes1.setMovementMethod(LinkMovementMethod.getInstance());
            btn1.setVisibility(View.VISIBLE);
            btn1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    show_dialog_webview();
                    layout6.setVisibility(View.VISIBLE);
                    steptext6.setText("Step 2");
                    checkingissue6.setVisibility(GONE);
                    checkingissuedes6.setText("Have you reset the Password ?");

                    textid6();
                    btn6.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            onButtonShowPopupWindowClick();
                        }
                    });
                    textid6();
                    btn7.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(getApplicationContext(), "Kindly click on above button to reset Password", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            });
        } else if (xyz.contains("user account is locked/disabled")) {
            titletxt.setText("User account is locked/disabled");
            steptext1.setText("Step 1");
            checkingissue1.setText("Ensure you are using correct credentials and there is no space used while entering the ID or Password.");
            checkingissuedes1.setText(Html.fromHtml("Click on below to reset Password :"));
            // checkingissuedes1.setMovementMethod(LinkMovementMethod.getInstance());
            btn1.setVisibility(View.VISIBLE);
            btn1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    show_dialog_webview();
                    layout6.setVisibility(View.VISIBLE);
                    steptext6.setText("Step 2");
                    checkingissue6.setVisibility(GONE);
                    checkingissuedes6.setText("Have you reset the Password ?");

                    textid6();
                    btn6.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Runnable r = new Runnable() {
                                public void run() {
                                    trytologin_check();
                                }
                            };
                            handler.postDelayed(r, 1000);
                        }
                    });
                    textid6();
                    btn7.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(getApplicationContext(), "Kindly click on above button to reset Password", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            });
        }
//
//            else if (xyz.contains("unable to login in jmd")){
//                titletxt.setText("Unable to login in JMD");
//                steptext1.setText("Step 1");
//                checkingissue1.setText("Ensure you are using correct credentials and there is no space used while entering the ID or Password.");
//                checkingissuedes1.setText(Html.fromHtml("Click on below to reset Password :"));
//                // checkingissuedes1.setMovementMethod(LinkMovementMethod.getInstance());
//                btn1.setVisibility(View.VISIBLE);
//                btn1.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        show_dialog_webview();
//                        layout6.setVisibility(View.VISIBLE);
//                        steptext6.setText("Step 2");
//                        checkingissue6.setVisibility(GONE);
//                        checkingissuedes6.setText("Have you reset the Password ?");
//
//                        textid6();
//                        btn6.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//                                onButtonShowPopupWindowClick();
//                            }
//                        });
//                        textid6();
//                        btn7.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//                                Toast.makeText(getApplicationContext(), "Kindly click on above button to reset Password", Toast.LENGTH_SHORT).show();
//                            }
//                        });
//                    }
//                });
//            }

        else if (xyz.contains("invalid user credentials")) {
            titletxt.setText("Invalid User Credentials");
            steptext1.setText("Step 1");
            checkingissue1.setText("Ensure you are using correct credentials and there is no space used while entering the ID or Password.");
            checkingissuedes1.setText(Html.fromHtml("Click on below to reset Password :"));
            // checkingissuedes1.setMovementMethod(LinkMovementMethod.getInstance());
            btn1.setVisibility(View.VISIBLE);
            btn1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    show_dialog_webview();
                    layout6.setVisibility(View.VISIBLE);
                    steptext6.setText("Step 2");
                    checkingissue6.setVisibility(GONE);
                    checkingissuedes6.setText("Have you reset the Password ?");

                    textid6();
                    btn6.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Runnable r = new Runnable() {
                                public void run() {
                                    trytologin_check();
                                }
                            };
                            handler.postDelayed(r, 1000);
                        }
                    });
                    textid6();
                    btn7.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(getApplicationContext(), "Kindly click on above button to reset Password", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            });
        } else if (xyz.contains("macid cannot be left blank")) {
            titletxt.setText("MACID cannot be left blank");
            wifi_related();
        } else if (xyz.contains("tender not configured")) {
            titletxt.setText("Tender not configured");
            layout1.setVisibility(GONE);
            layout2.setVisibility(View.VISIBLE);
            steptext2.setText("Step 1");
            checkingissue2.setText(R.string.rpos_jpos_folder_delete);


            textid2();
            btn2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Datacontainer.deletefolderusingpath();
                    if (!Datacontainer.folderdeletesuccess) {
                        Toast.makeText(getApplicationContext(), "No Folder Found", Toast.LENGTH_SHORT).show();
                        checkingissuedes2.setText("No folder's found");
                        layout3.setVisibility(View.VISIBLE);
                        steptext3.setText("Step 2");
                        checkingissue3.setVisibility(GONE);
                        checkingissuedes3.setText(R.string.clear_cache_clear_data);


                        textid3();
                        btn3.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                openapplicationsetting();
                                Toast.makeText(Checking_issue.this, "Kindly click on Storage to do clear cache/Data", Toast.LENGTH_LONG).show();
                                Runnable r = new Runnable() {
                                    public void run() {
                                        trytologin_check();
                                    }
                                };
                                handler.postDelayed(r, 4000);
                            }
                        });
                    } else {
                        Toast.makeText(getApplicationContext(), "Deleted", Toast.LENGTH_SHORT).show();
                        checkingissuedes2.setText("RPOS/JPOS folder's deleted");
                        layout3.setVisibility(View.VISIBLE);
                        steptext3.setText("Step 2");
                        checkingissue3.setVisibility(GONE);
                        checkingissuedes3.setText(R.string.clear_cache_clear_data);


                        textid3();
                        btn3.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                openapplicationsetting();
                                Toast.makeText(Checking_issue.this, "Kindly click on Storage to do clear cache/Data", Toast.LENGTH_LONG).show();
                                Runnable r = new Runnable() {
                                    public void run() {
                                        trytologin_check();
                                    }
                                };
                                handler.postDelayed(r, 4000);
                            }
                        });
                    }
                }
            });
        } else if (xyz.contains("error code 12 , failed to connect to server")) {
            titletxt.setText("Error code 12 , Failed to connect to server");
            layout1.setVisibility(GONE);
            layout2.setVisibility(View.VISIBLE);

            steptext2.setText("Step 1");
            checkingissue2.setText(R.string.rpos_jpos_folder_delete);

            textid2();
            btn2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Datacontainer.deletefolderusingpath();

                    if (!Datacontainer.folderdeletesuccess) {
                        Toast.makeText(getApplicationContext(), "No Folder Found", Toast.LENGTH_SHORT).show();
                        checkingissuedes2.setText("No folder's found");
                        layout3.setVisibility(View.VISIBLE);
                        steptext3.setText("Step 2");
                        checkingissue3.setVisibility(GONE);
                        checkingissuedes3.setText(R.string.clear_cache_clear_data);

                        textid3();
                        btn3.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                openapplicationsetting();
                                Toast.makeText(Checking_issue.this, "Kindly click on Storage to do clear cache/Data", Toast.LENGTH_LONG).show();
                                Runnable r = new Runnable() {
                                    public void run() {
                                        trytologin_check();
                                    }

                                };
                                handler.postDelayed(r, 4000);
                            }
                        });
                    } else {
                        Toast.makeText(getApplicationContext(), "Deleted", Toast.LENGTH_SHORT).show();
                        checkingissuedes2.setText("RPOS/JPOS folder's deleted");
                        layout3.setVisibility(View.VISIBLE);
                        steptext3.setText("Step 2");
                        checkingissue3.setVisibility(GONE);
                        checkingissuedes3.setText(R.string.clear_cache_clear_data);

                        textid3();
                        btn3.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                openapplicationsetting();
                                Toast.makeText(Checking_issue.this, "Kindly click on Storage to do clear cache/Data", Toast.LENGTH_LONG).show();
                                Runnable r = new Runnable() {
                                    public void run() {
                                        trytologin_check();
                                    }
                                };
                                handler.postDelayed(r, 4000);
                            }
                        });
                    }
                }
            });

        }

//        else if (xyz.contains("504 gateway timeout / 404 app config not found")){
//            titletxt.setText("504 gateway timeout/404 app config not found");
//            checkingissue1.setText(Html.fromHtml("Click below to log a complaint ."));
//            directlycreateticket();
//
//        }
        else if (xyz.contains("session expired")) {
            titletxt.setText("Session Expired");
            layout1.setVisibility(GONE);
            layout2.setVisibility(View.VISIBLE);

            steptext2.setText("Step 1");
            checkingissue2.setVisibility(GONE);
            checkingissuedes2.setText("Kindly switch OFF mobile data and then turn ON the mobile data .");


            textid2();
            btn2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    layout3.setVisibility(View.VISIBLE);
                    steptext3.setText("Step 2");
                    checkingissue3.setVisibility(GONE);
                    checkingissuedes3.setText(R.string.clear_cache_clear_data);


                    textid3();
                    btn3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            openapplicationsetting();
                            Toast.makeText(Checking_issue.this, "Kindly click on Storage to do clear cache/Data", Toast.LENGTH_LONG).show();
                            Runnable r = new Runnable() {
                                public void run() {
                                    trytologin_check();
                                }
                            };
                            handler.postDelayed(r, 4000);
                        }
                    });
                }
            });
        }
        //---------------------------------------------Failed reason : validation failed------------------//

        else if (xyz.contains("failed reason : validation failed")) {
            titletxt.setText("Failed reason : validation failed");
            btn1.setVisibility(View.VISIBLE);
            layout1.setVisibility(View.VISIBLE);
            steptext1.setText("Step 1");
            checkingissue1.setText("Kindly enter correct building name, it should have more than 2 characters in “Building name” text field");
            checkingissue1.setVisibility(View.VISIBLE);
            textid1();
            btn1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String information = getHardwareAndSoftwareInfo();
                    layout2.setVisibility(View.VISIBLE);
                    steptext2.setText("Step 2");
                    checkingissue2.setText("Ensure you are entering numbers or characters as per the column requirement");
                    checkingissuedes2.setText("Ensure that you should not change the auto populated pin code in the address");
                    textid2();
                    btn2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Runnable r = new Runnable() {
                                public void run() {
                                    trytologin_check();
                                }
                            };
                            handler.postDelayed(r, 1000);
                        }
                    });
                }
            });
        }

        //----------------------------------Jio Pos Plus - online order masp process------------------------------------//

        else if (xyz.contains("online order masp process")) {
            titletxt.setText("Online Order MASP Process");
            btn1.setVisibility(View.VISIBLE);
            layout1.setVisibility(View.VISIBLE);
            steptext1.setText("Step 1");
            checkingissue1.setText("Login in JIO POS Plus application and go to Menu >> Select option “Online Order MASP”");
            checkingissuedes1.setText("Either enter the Order ID generated in JioMart Digital or enter the mobile no. which is used to place the order in JioMart Digital, then click on Search option.");
            checkingissue1.setVisibility(View.VISIBLE);
            textid1();
            btn1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    layout2.setVisibility(View.VISIBLE);
                    steptext2.setText("Step 2");
                    checkingissue2.setText("Order details will be visible, select the Order ID and enter the OTP sent on Customer mobile no. then click on validate OTP.");
                    checkingissuedes2.setText("Click on ‘Add to Cart’ option once order details are confirmed by customer.");
                    textid2();
                    btn2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            layout3.setVisibility(View.VISIBLE);
                            steptext3.setText("Step 3");
                            checkingissue3.setText("You will get the Delivery Information on the screen like Shipping location, Date of Delivery and Time, Click on done button.");
                            checkingissuedes3.setText("Tenders will be visible to you, click on check out option. Enter Salesman ID and click on done");
                            textid3();
                            btn3.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    layout4.setVisibility(View.VISIBLE);
                                    steptext4.setText("Step 4");
                                    checkingissue4.setText("Once again order confirmation OTP will be sent to customer, enter the OTP and click on Validate OTP option.");
                                    checkingissuedes4.setText("Customer Detail page will appear, enter customer details - name, e-mail ID, address, and then tap on Accept.");
                                    textid4();
                                    btn4.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            layout5.setVisibility(View.VISIBLE);
                                            steptext5.setText("Step 5");
                                            checkingissue5.setText("‘Complete Action’ Option will be prompted, select the appropriate choice as per customer - “Home Delivery” or “Store Pick Up”");
                                            checkingissuedes5.setText("Select the MOP as Cash or Payment via link as per customer’s choice. Once payment is successfully done, Order will be placed for delivery to customer.");
                                            textid5();
                                            btn5.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    Runnable r = new Runnable() {
                                                        public void run() {
                                                            trytologin_check();
                                                        }
                                                    };
                                                    handler.postDelayed(r, 1000);
                                                }
                                            });
                                        }
                                    });
                                }
                            });
                        }
                    });
                }
            });
        } else if (xyz.contains("connection refused.")) {
            connectionrefused();
        } else if (xyz.contains("empty response from server")) {
            titletxt.setText("Empty response from server");
            MOP_RELATED_ISSUE();
        } else if (xyz.contains("failed for online transaction,response:internal ser")) {
            titletxt.setText("Failed for online transaction,Response:Internal ser");
            MOP_RELATED_ISSUE();
        } else if (xyz.contains("failed for online transaction,response:card declined")) {
            titletxt.setText("Failed for online transaction,Response:Card declined");
            MOP_RELATED_ISSUE();
        } else if (xyz.contains("invalid transaction id")) {
            titletxt.setText("Invalid Transaction ID");
            MOP_RELATED_ISSUE();
        } else if (xyz.contains("store code mis-match")) {
            storecodemismatch();
        } else if (xyz.contains("product price mismatch")) {
            productpricemismatch();
        } else if (xyz.contains("ssl validation failed/pinning failed/handshake error")) {
            titletxt.setText("SSL Validation Failed/Pinning failed/Handshake error");
            layout1.setVisibility(GONE);
            layout2.setVisibility(View.VISIBLE);
            steptext2.setText("Step 1");
            checkingissue2.setVisibility(GONE);
            checkingissuedes2.setText(Html.fromHtml("Kindly switch off WiFi data . <br> Kindly Login with Jio Data . <br> Jio mobile number registered with Jio Pos Plus should be in Sim slot 1 ."));
            btn2.setVisibility(View.VISIBLE);

            textid2();
            btn2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    layout3.setVisibility(View.VISIBLE);
                    steptext3.setText("Step 2");
                    checkingissue3.setText("Kindly turn ON and OFF the mobile data");
                    checkingissuedes3.setText("Click below to check network connectivity :");
                    btn3.setVisibility(View.VISIBLE);

                    textid3();
                    btn3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            show_dialog_webview_nw();
                            layout4.setVisibility(View.VISIBLE);
                            steptext4.setText("Step 3");
                            checkingissue4.setText(R.string.clear_cache_clear_data);
                            checkingissuedes4.setVisibility(GONE);
                            btn4.setVisibility(View.VISIBLE);

                            textid4();
                            btn4.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    openapplicationsetting();
                                    Toast.makeText(Checking_issue.this, "Kindly click on Storage to do clear cache/Data", Toast.LENGTH_LONG).show();
                                    Runnable r = new Runnable() {
                                        public void run() {
                                            trytologin_check();
                                        }
                                    };
                                    handler.postDelayed(r, 3000);
                                }
                            });
                        }
                    });
                }
            });
        } else if (xyz.contains("i want to know my pos id")) {
            titletxt.setText("I want to know my POS ID");
            checkingissue1.setText(Html.fromHtml("If you want to know POS ID :<br> Click below"));
            directlycreateticket();
        } else if (xyz.contains("error in latlong time")) {
            titletxt.setText("Error in Latlong Time");
            latlong();
        } else if (xyz.contains("location not fetched please check gps")) {
            titletxt.setText("Location not fetched please check GPS");
            latlong();
        } else if (xyz.contains("ledger / order status")) {
            titletxt.setText("Ledger / Order Status");
            checkingissue1.setText("Note: This option is accessible to Shop Owner (Primary User) only");
            checkingissuedes2.setText("1. Login in Jio pos plus > Go to Menu > Jio other sales > click on performance dashboard, you will get 4 options available under performance dashboard.");

            btn1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    layout2.setVisibility(View.VISIBLE);
                    steptext2.setText("Step 2");
                    checkingissue2.setText("A)Summary : This option gives you a details of sim activation, device sales and recharge information for last full month, last month till date and this month till date (till yesterday)." +
                            "\nB)Performance : From this option you can compare the sales performance of your last from current month.");
                    checkingissuedes2.setText("C)Earnings : This option gives you an information about your earnings through sim activation, devices sales and recharges, details available only for current month and previous 2 months." +
                            "\nD)Ledger: This option gives you POS Transaction Details containing Opening Balance (OB), closing Balance (CB),Credit (CR) and Debit(DR) along with Transaction details for all Transaction, details are available for Last 30 Days and you can select 7 Days Range at a time.");
                    btn2.setText("Submit");
                    textid2();
                    btn2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            onButtonShowPopupWindowClick();
                        }
                    });
                }
            });
        } else if (xyz.contains("payment not found")) {
            titletxt.setText("Payment not found");
            checkingissue1.setText("Kindly complete the payment, through link sent on registered mobile no. within 20 minutes.");
            checkingissuedes1.setText("If fails to complete the payment within 20 minutes, resend the payment link to customer on registered mobile no.");

            btn1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    layout2.setVisibility(View.VISIBLE);
                    steptext2.setText("Step 2");
                    checkingissue2.setText("If payment is completed withing 20 minutes and the money is deducted from customer's end, Then inform customer that refund will be provided from respective bank, As we have not received the payment. ");
                    textid2();
                    btn2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            onButtonShowPopupWindowClick();
                        }
                    });
                }
            });
        } else if (xyz.contains("add to cart button not enabled")) {
            titletxt.setText("Add to cart button not enabled");
            checkingissue1.setText("Enter the correct 10 digit mobile number.\nKindly enter the Pin code of your area, where you want product to be delivered");
            checkingissuedes1.setText("Add cart option will be enabled only if delivery is available in that area");

            btn1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Runnable r = new Runnable() {
                        public void run() {
                            trytologin_check();
                        }
                    };
                    handler.postDelayed(r, 1000);

                }
            });
        } else if (xyz.contains("unable to compare products")) {
            titletxt.setText("Unable to compare products");
            checkingissue1.setText("Ensure that you are comparing the products with same category");
            checkingissuedes1.setText("You can only compare 3 products at a time");

            btn1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    layout2.setVisibility(View.VISIBLE);
                    steptext2.setText("Step 2");
                    checkingissue2.setText("You can reset the cart and change the product to compare");
                    textid2();
                    btn2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            onButtonShowPopupWindowClick();
                        }
                    });
                }
            });
        } else if (xyz.contains("pin code not available")) {
            titletxt.setText("Pin code not available");
            checkingissue1.setText("Ensure that you have entered the correct pincode.");
            checkingissuedes1.setText("Need to Delete Cache and Data of JioMart Digital application,Please click on below button");

            btn1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openapplicationsettingJMD();
                    layout2.setVisibility(View.VISIBLE);
                    steptext2.setText("Step 2");
                    checkingissue2.setText("Kindly try to place an order with same article.");
                    checkingissuedes2.setText("If still issue persist, Kindly try to place an order with different article.");

                    textid2();
                    btn2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            layout3.setVisibility(View.VISIBLE);
                            steptext3.setText("Step 3");
                            checkingissue3.setText("If issue is resolved with another article it means previous article is not deliverable at the current pincode.");
                            checkingissuedes3.setText("If issue persist with both the articles then kindly contact JMD Officer/JMD Lead");

                            textid3();

                            btn3.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    Runnable r = new Runnable() {
                                        public void run() {
                                            trytologin_check();
                                        }
                                    };
                                    handler.postDelayed(r, 1000);
                                }
                            });

                        }
                    });
                }
            });
        } else if (xyz.contains("tablet battery getting drained (fast discharge)")) {
            titletxt.setText("Tablet Battery getting drained (Fast Discharge)");
            checkingissue1.setText("Check and disable the Screen Always on display mode");
            checkingissuedes1.setText("Go to Settings – Display Settings – Brightness – Disable 'Always ON' mode");

            btn1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    layout2.setVisibility(View.VISIBLE);
                    steptext2.setText("Step 2");
                    checkingissue2.setText("Turn off Live wallpaper : \n Go to Settings – Display Settings – Wallpaper – Disable Live Wallpaper");
                    checkingissuedes2.setText("Close open apps : \n If issue persists, Kindly connect with Lenovo Service Center to do a factory reset");
                    textid2();
                    btn2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            onButtonShowPopupWindowClick();
                        }
                    });
                }
            });
        } else if (xyz.contains("order pending in jiomart digital")) {
            titletxt.setText("Order pending in JioMart Digital");
            checkingissue1.setText("Check if payment is successful or not for the product.");
            checkingissuedes1.setText("If Payment is not done – Kindly complete the payment for the product and check the status.");

            btn1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    layout2.setVisibility(View.VISIBLE);
                    steptext2.setText("Step 2");
                    checkingissue2.setText("If Payment is done – Kindly connect with Customer Care - dial 1800 890 1988 - select option 3 and then select Option 2(Jio Mart Customer Care Helpline) for further checks");
                    textid2();
                    btn2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Runnable r = new Runnable() {
                                public void run() {
                                    trytologin_check();
                                }
                            };
                            handler.postDelayed(r, 2000);
                        }
                    });
                }
            });
        } else if (xyz.contains("touch screen not working")) {
            titletxt.setText("Touch screen not working");
            checkingissue1.setText("Ensure there are no physical damages on the screen also Check if Firmware is updated for STB, if it is not updated,");
            checkingissuedes2.setText("STB main screen will be visible and JioMart Digital will not show on screen. Then kindly connect with state IT team to update the firmware.");

            btn1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    layout2.setVisibility(View.VISIBLE);
                    steptext2.setText("Step 2");
                    checkingissue2.setText("If firmware is updated then,Kindly check if the USB Cable is connected properly from TV USB Slot to STB USB Slot,Please connect the cable properly.");
                    checkingissuedes2.setText("Check if the Touch Screen and Finger is clean");
                    btn2.setText("Next");
                    textid2();
                    btn2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            layout3.setVisibility(View.VISIBLE);
                            steptext3.setText("Step 3");
                            checkingissue3.setText("Touch screen requires human finger to interact hence do not use Pen or any other device to give touch input," +
                                    "also do not use any hand gloves while giving touch inputs");
                            checkingissuedes3.setText("If the touch screen does not work even after connecting USB to the square connector. Click on Next and Try the steps below :");
                            btn3.setText("Next");
                            textid3();
                            btn3.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    layout4.setVisibility(View.VISIBLE);
                                    steptext4.setText("Step 4");
                                    checkingissue4.setText("Click (touch) button next to power (led) - 3 vertical lines (Menu), \n Go to 'OSD setup', \n Select Touch Enable => On, \n AT drawing screen can be used for free hand drawing to confirm calibration. ");
                                    checkingissuedes4.setText("If Still issue persist click on below :");
                                    btn4.setText("Next");
                                    textid4();
                                    btn4.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            onButtonShowPopupWindowClick();
                                        }
                                    });

                                }
                            });


                        }
                    });
                }
            });
        } else if (xyz.contains("order placement process through jiomart digital")) {
            titletxt.setText("Order Placement Process through JioMart Digital");
            checkingissue1.setText("Search and select the product which customer wishes to buy and click on more details of the product.");
            checkingissuedes2.setText("Enter customer’s pin code and check if delivery is available or not.");

            btn1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    layout2.setVisibility(View.VISIBLE);
                    steptext2.setText("Step 2");
                    checkingissue2.setText("If it shows “Available at this pin code” then click on “Add to Cart” option.");
                    checkingissuedes2.setText("Now click on “Go to cart” option, Enter customer’s mobile no. and click on “Pay Now” button");
                    btn2.setText("Next");
                    textid2();
                    btn2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            layout3.setVisibility(View.VISIBLE);
                            steptext3.setText("Step 3");
                            checkingissue3.setText("Then “Pay at Counter” box will be visible,click on confirm.");
                            checkingissuedes3.setText("Once clicked on confirm, Order will be sent to POS application for billing and Order id will be shown on JioMart Digital screen.");
                            btn3.setText("Next");
                            textid3();
                            btn3.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    layout4.setVisibility(View.VISIBLE);
                                    steptext4.setText("Step 4");
                                    checkingissue4.setText("Kindly complete the transaction through POS application now.");
                                    btn4.setText("Next");
                                    textid4();
                                    btn4.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            onButtonShowPopupWindowClick();
                                        }
                                    });

                                }
                            });


                        }
                    });
                }
            });
        } else if (xyz.contains("app is stuck on blue screen")) {
            titletxt.setText("App is stuck on blue screen");
            checkingissue1.setText("Ensure network connectivity is good in your store and JioMart Digital is connected to the internet");
            checkingissuedes1.setText("If proper network connectivity, then logout the JioMart Digital and re-login once and check.");

            btn1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    layout2.setVisibility(View.VISIBLE);
                    steptext2.setText("Step 2");
                    checkingissue2.setText("Check and confirm is this issue persist on one particular page or on entire application");
                    checkingissuedes2.setText("If issue still persist, then click below : ");
                    btn2.setText("Next");
                    textid2();
                    btn2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            onButtonShowPopupWindowClick();

                        }
                    });
                }
            });
        } else if (xyz.contains("error checking out inventory. please try after sometime.")) {
            titletxt.setText("Error checking out inventory. Please try after sometime.");
            checkingissue1.setText("Need to Delete Cache and Data of Jio Pos Plus application,Please click on below button");
            checkingissuedes2.setText("If same issue persist check whether user is not able to search a single product or any product in JioMart Digital");

            btn1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openapplicationsettingJMD();
                    layout2.setVisibility(View.VISIBLE);
                    steptext2.setText("Step 2");
                    checkingissue2.setText("If issue is for single product, then it might be possible that the product is not available in stock and hence result is not showing in JioMart Digital.");
                    checkingissuedes2.setText("If you are not able to search any product in JioMart Digital, then click below : ");
                    btn2.setText("Next");
                    textid2();
                    btn2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            onButtonShowPopupWindowClick();

                        }
                    });
                }
            });
        } else if (xyz.contains("web page not available")) {
            titletxt.setText("Web page not available");
            checkingissue1.setText("Ensure that internet connectivity is good");
            checkingissuedes1.setText("Disconnect and re-connect the WiFi  Connectivity.");

            btn1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    layout2.setVisibility(View.VISIBLE);
                    steptext2.setText("Step 2");
                    checkingissue2.setText("To connect WiFi, Click on three dots option at the Top right hand side of the tablet, then click on WiFi connection option.");
                    checkingissuedes2.setText("Then enter WiFi username and password or connect with Mobile Hotspot");
                    btn2.setText("Next");
                    textid2();
                    btn2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            layout3.setVisibility(View.VISIBLE);
                            steptext3.setText("Step 3");
                            checkingissue3.setText("Need to Delete Cache and Data of JioMart Digital application,Please click on below button");
                            //checkingissuedes3.setText("Kindly long press on JMD Application and click on Clear App Data.");
                            btn3.setText("Next");
                            textid3();
                            btn3.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    openapplicationsettingJMD();
                                    Runnable r = new Runnable() {
                                        public void run() {
                                            trytologin_check();
                                        }
                                    };
                                    handler.postDelayed(r, 1000);

                                }
                            });


                        }
                    });
                }
            });
        } else if (xyz.contains("unable to click on buy now option")) {
            titletxt.setText("Unable to click on Buy Now option");
            checkingissue1.setText("Ensure, That the product which customer has selected to buy, Should be available for delivery on his pin code.");
            //checkingissuedes1.setText("If pin code entered by you in JioMart Digital after selecting the product is showing “Delivery not available to the location”, then that delivery cannot be done on your location as of now.");

            btn1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    layout2.setVisibility(View.VISIBLE);
                    steptext2.setText("Step 2");
                    checkingissue2.setText("If pin code is entered correctly, delivery is also available on that location, then ensure mobile no. of customer is entered in JioMart Digital is correct before clicking on Buy Now Option.");
                    checkingissuedes2.setText("If issue still persist, Then click below : ");
                    btn2.setText("Next");
                    textid2();
                    btn2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            onButtonShowPopupWindowClick();

                        }
                    });
                }
            });
        } else if (xyz.contains("unable to search product in jiomart digital.")) {
            titletxt.setText("Unable to search product in JioMart Digital.");
            checkingissue1.setText("Ensure that internet connectivity is good, To check the internet connectivity click on below button.");
            checkingissuedes2.setText("Please check the Ethernet connection once");

            btn1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    show_dialog_webview_nw();
                    layout2.setVisibility(View.VISIBLE);
                    steptext2.setText("Step 2");
                    checkingissue2.setText("If network connectivity is proper , then logout the JioMart Digital and re-login once and check");
                    checkingissuedes2.setText("If you are still facing the same issue then, Check whether you are able to search a single product or all the product in JioMart Digital");
                    btn2.setText("Next");
                    textid2();
                    btn2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            layout3.setVisibility(View.VISIBLE);
                            steptext3.setText("Step 3");
                            checkingissue3.setText("If issue is for single product, then it might be possible that the product is not available in stock and hence result is not showing on JioMart Digital.");
                            checkingissuedes3.setText("If not able to search any product in JioMart Digital,then click below :");
                            btn3.setText("Next");
                            textid3();
                            btn3.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    onButtonShowPopupWindowClick();
                                }
                            });


                        }
                    });
                }
            });
        } else if (xyz.contains("something went wrong,response:no data")) {
            titletxt.setText("Something went wrong,Response:No Data");
            btn1.setVisibility(View.VISIBLE);
            layout1.setVisibility(View.VISIBLE);
            steptext1.setText("Step 1");
            checkingissue1.setText("Click below button to perform basic troubleshooting like:");
            checkingissue1.setVisibility(View.VISIBLE);
            checkingissuedes1.setText("Deleting RPOS folders \nReinstalling JIO POS plus");
            textid1();
            btn1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    masterTroubleshooting();
                    layout2.setVisibility(View.VISIBLE);
                    steptext2.setText("Step 2");
                    // checkingissue2.setText("Need to do Data/Delta Sync from Jio Pos Plus application,Please click on below button");
                    checkingissuedes2.setText("Click below to refresh policies.");
                    textid2();
                    btn2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            refreshPolicies(view);
                            Runnable r = new Runnable() {
                                public void run() {
                                    trytologin_check();
                                }
                            };
                            handler.postDelayed(r, 3000);
                        }
                    });
                }
            });
        } else if (xyz.contains("user is not present in oiam")) {
            titletxt.setText("User is not present in OIAM");
            checkingissue1.setText("Ensure you are entering correct pos id while changing your password.");
            checkingissuedes1.setText("Click below to login in Jio Pos Plus after changing your password.");
            btn1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boolean isInstalled = Utils.isPackageInstalled("com.jio.jpp1", getPackageManager());
                    if (isInstalled) {
                        Intent intent = getPackageManager().getLaunchIntentForPackage("com.jio.jpp1");
                        startActivity(intent);
                    } else {
                        Intent intent1 = getPackageManager().getLaunchIntentForPackage("com.ril.rposcentral");
                        startActivity(intent1);
                    }
                    Toast.makeText(getApplicationContext(), "Try to login with POS ID (076)", Toast.LENGTH_LONG).show();

                    Runnable r = new Runnable() {
                        public void run() {
                            trytologin_check();
                        }
                    };
                    handler.postDelayed(r, 1000);
                }
            });
        }
        //---------------------------------------------Jio Pos Plus - No data found, while entering OTP----------------//

        else if (xyz.contains("no data found, while entering otp")) {
            titletxt.setText("No data found, while entering OTP");
            btn1.setVisibility(View.VISIBLE);
            layout1.setVisibility(View.VISIBLE);
            steptext1.setText("Step 1");
            checkingissue1.setText("Click below button to perform basic troubleshooting like:");
            checkingissue1.setVisibility(View.VISIBLE);
            checkingissuedes1.setText("Deleting RPOS folders \nReinstalling JIO POS plus");
            textid1();
            btn1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    masterTroubleshooting();
                    layout2.setVisibility(View.VISIBLE);
                    steptext2.setText("Step 2");
                    // checkingissue2.setText("Need to do Data/Delta Sync from Jio Pos Plus application,Please click on below button");
                    checkingissuedes2.setText("Click below to refresh policies.");
                    textid2();
                    btn2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            refreshPolicies(view);
                            Runnable r = new Runnable() {
                                public void run() {
                                    trytologin_check();
                                }
                            };
                            handler.postDelayed(r, 3000);
                        }
                    });
                }
            });
        }

        //---------------------------------Order cancellation process------------------------//
        else if (xyz.contains("order cancellation process")) {
            titletxt.setText("Order cancellation process");
            btn1.setVisibility(View.VISIBLE);
            layout1.setVisibility(View.VISIBLE);
            steptext1.setText("Step 1");
            checkingissue1.setText("You can guide customer to download Jiomart App or visit Website: www.jiomart.com with registered mobile number, customer can initiate the Cancellation by pressing “Cancel” button against the order");
            checkingissue1.setVisibility(View.VISIBLE);
            textid1();
            btn1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String information = getHardwareAndSoftwareInfo();
                    layout2.setVisibility(View.VISIBLE);
                    steptext2.setText("Step 2");
                    checkingissue2.setText("If customer is unable to download Jiomart App or visit website – You can log in on behalf of customer and initiate cancellation process by taking customer consent & OTP");
                    textid2();
                    btn2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Runnable r = new Runnable() {
                                public void run() {
                                    trytologin_check();
                                }
                            };
                            handler.postDelayed(r, 1000);
                        }
                    });

                }
            });

        }

        //---------------------------------Amount refund process------------------------//
        else if (xyz.contains("amount refund process")) {
            titletxt.setText("Amount refund process");
            btn1.setVisibility(View.VISIBLE);
            layout1.setVisibility(View.VISIBLE);
            steptext1.setText("Step 1");
            checkingissue1.setText("Online Payment – Auto refund will happen as per bank's refund policies (From the date of cancellation or from the date of return pick up of the product)");
            checkingissue1.setVisibility(View.VISIBLE);
            textid1();
            btn1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String information = getHardwareAndSoftwareInfo();
                    layout2.setVisibility(View.VISIBLE);
                    steptext2.setText("Step 2");
                    checkingissue2.setText("Cash payment – You need to raise OC for the same, incase access to raise OC is not there for you, then you can contact JC team to raise OC.");
                    textid2();
                    btn2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Runnable r = new Runnable() {
                                public void run() {
                                    trytologin_check();
                                }
                            };
                            handler.postDelayed(r, 1000);
                        }
                    });
                }
            });
        }
        //-----------------------------buffering/ssl pinning failed ---------------------------

        else if (xyz.contains("buffering/redirecting to login screen/ssl pinning failed")) {
            titletxt.setText("Buffering/Redirecting to Login screen/SSL pinning failed");
            layout1.setVisibility(GONE);
            btn2.setVisibility(View.VISIBLE);
            layout2.setVisibility(View.VISIBLE);
            steptext2.setText("Step 1");
            Toast.makeText(getApplicationContext(), "Make sure the internal Storage is free up to 4GB", Toast.LENGTH_LONG).show();
            btn2.setText("Get Info");
            checkingissue2.setVisibility(View.VISIBLE);
            checkingissuedes2.setVisibility(View.VISIBLE);
            textid2();
            btn2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String information = getHardwareAndSoftwareInfo();
                    layout3.setVisibility(View.VISIBLE);
                    steptext3.setText("Step 2");

                    checkingissue3.setText("Kindly turn OFF and ON the WiFi Connection by clicking on 3 dots option from homepage of JMD Tab.");
                    checkingissuedes3.setText("Click below to check network connectivity :");
                    textid3();
                    btn3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            show_dialog_webview_nw();
                            layout4.setVisibility(View.VISIBLE);
                            steptext4.setText("Step 3");
                            checkingissue4.setText(R.string.clear_cache_clear_data);
                            checkingissuedes4.setText("Kindly give all the permission for Jio Pos Plus App :");
                            btn4.setVisibility(View.VISIBLE);

                            textid4();
                            btn4.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    openapplicationsetting();
                                    //Toast.makeText(Checking_issue.this, "Kindly click on Storage to do clear cache/Data", Toast.LENGTH_LONG).show();
                                    Runnable r = new Runnable() {
                                        public void run() {
                                            trytologin_check();
                                        }
                                    };
                                    handler.postDelayed(r, 1000);

                                }
                            });
                        }
                    });
                    checkingissue2.setText(information);
                    checkingissuedes2.setText(getMemoryInfo());
                }
            });
        }


//----------------------------------------------------------------------------------------------------------


        else {
            Intent intent = new Intent(Checking_issue.this, SR_Form.class);
            startActivity(intent);
            Toast.makeText(this, "Sorry!! we are unable to find, Kindly raise manual ticket", Toast.LENGTH_SHORT).show();
            finishAffinity();
        }

        textid1();


    }

    private void setContentView(Checking_issue checking_issue, int activity_checking_issue) {

    }


    private void storecodemismatch() {
        boolean isInstalledGT = Utils.isPackageInstalled("com.jio.jpp1", getPackageManager());
        boolean isInstalledRR = Utils.isPackageInstalled("com.ril.rposcentral", getPackageManager());
        if (isInstalledGT) {
            titletxt.setText("Store Code Mis-Match");
            layout1.setVisibility(GONE);
            layout6.setVisibility(View.VISIBLE);

            steptext6.setText("Step 1");
            btn6.setVisibility(View.VISIBLE);
            checkingissue6.setText("Kindly turn OFF and Turn ON the WiFi through three dots option on the home page of the scalefusion and try to login again.");
            checkingissuedes6.setText("Is the issue resolved ?");
            //wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
            btn6.setText("Yes");
            textid6();
            btn6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Runnable r = new Runnable() {
                        public void run() {
                            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Checking_issue.this);

                            alertDialogBuilder.setCancelable(false);
                            alertDialogBuilder.setMessage("Now try to login JIOPOSPlus and Check")
                                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dismissdailogbox();
                                        }
                                    });
                            AlertDialog alert = alertDialogBuilder.create();
                            alert.show();
                        }
                    };
                    handler.postDelayed(r, 2000);
                }
            });
            btn7.setVisibility(View.VISIBLE);
            btn7.setText("No");
            textid6();
            btn7.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    layout7.setVisibility(View.VISIBLE);
                    steptext7.setText("Step 2");
                    checkingissue7.setText(Html.fromHtml("Do you know the device PRM ID ?"));
                    checkingissuedes7.setText(Html.fromHtml("If you know, Then kindly login with that PRM's POS ID and go to <b>JPP>>Menu>>Utility>>Click on Manage Device>>Delete the MAC ID of your device.</b>"));
                    btn8.setVisibility(View.VISIBLE);
                    btn9.setVisibility(GONE);

                    btn8.setText("Next");
                    textid7();
                    btn8.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            layout8.setVisibility(View.VISIBLE);
                            steptext8.setText("Step 3");
                            checkingissue8.setText(Html.fromHtml("If you don't know the device PRM ID "));
                            checkingissuedes8.setText(Html.fromHtml("Then kindly contact nearest JC . <br> In JC , contact with Customer service desk ."));

                            btn10.setText("Finish");
                            textid8();
                            btn10.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Runnable r = new Runnable() {
                                        public void run() {
                                            trytologin_check();
                                        }
                                    };
                                    handler.postDelayed(r, 1000);
                                }
                            });
                        }
                    });
                }
            });
        }
        if (isInstalledRR) {
            titletxt.setText("RR - Store Code Mis-Match");
            layout1.setVisibility(GONE);
            layout6.setVisibility(View.VISIBLE);

            steptext6.setText("Step 1");
            checkingissue6.setVisibility(GONE);
            btn6.setVisibility(View.VISIBLE);
            checkingissuedes6.setText("This Issue occurs if the employee is mapped incorrectly , or device is transferred from one store to another. Check the Store_Code shown against the device_store , is it your current store ?");
            //wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
            btn6.setText("Yes");
            textid6();
            btn6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    btn7.setVisibility(GONE);
                    layout8.setVisibility(View.VISIBLE);
                    steptext8.setText("Step 2");
                    checkingissue8.setText(Html.fromHtml("Ensure your store code is updated with your employee id on HRMS portal ."));
                    checkingissuedes8.setText(Html.fromHtml("If yes , then click below to raise a ticket with HRMS portal screenshot ,showing your current store's code ."));
                    btn10.setVisibility(View.VISIBLE);


                    btn10.setText("Finish");
                    textid8();
                    btn10.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            Runnable r = new Runnable() {
                                public void run() {
                                    trytologin_check();

                                }

                            };

                            handler.postDelayed(r, 1000);
                        }
                    });

                }
            });
            btn7.setText("No");
            btn7.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    btn6.setVisibility(GONE);
                    layout8.setVisibility(View.VISIBLE);
                    steptext8.setText("Step 2");
                    checkingissue8.setText(Html.fromHtml("This device belongs to the store which is shown against device store .<br>" +
                            " Request any employee from device store to log in and then Unregister the device ."));
                    checkingissuedes8.setText(Html.fromHtml("Once device is unregistered then try to login with your Employee ID and Password.\n" +
                            "While Unregistering the device if you get an error as Contact_to_HO then click below button ."));
                    btn10.setVisibility(View.VISIBLE);
                    btn10.setText("Finish");
                    textid8();
                    btn10.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            Runnable r = new Runnable() {
                                public void run() {
                                    trytologin_check();
                                }
                            };

                            handler.postDelayed(r, 1000);
                        }
                    });
                }
            });

        }
    }

    private void productpricemismatch() {
        boolean isInstalledGT = Utils.isPackageInstalled("com.jio.jpp1", getPackageManager());
        boolean isInstalledRR = Utils.isPackageInstalled("com.ril.rposcentral", getPackageManager());
        if (isInstalledRR) {
            titletxt.setText("Product Price Mismatch");
            layout1.setVisibility(GONE);
            layout5.setVisibility(View.VISIBLE);
            steptext5.setText("Step 1");
            btn5.setVisibility(View.VISIBLE);
            checkingissue5.setText("Kindly check whether promo code for this particular product is applied or not in the POS.");
            checkingissuedes5.setText("IF not - then take correct promo code from business team and follow the process to add. Go to Menu >Utilities > missing data utility > select promo and enter the promo code.\n " +
                    "If Promo code already added, then call on 18008933951 and select option 1 Or user can also raise a ticket at servicedesk.ril.com under Promo and pricing category.");
            btn5.setText("Next");
            textid5();
            btn5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Runnable r = new Runnable() {
                        public void run() {
                            trytologin_check();
                        }
                    };
                    handler.postDelayed(r, 1000);
                }
            });
        }

        if (isInstalledGT) {
            titletxt.setText("Product Price Mismatch");
            layout1.setVisibility(View.VISIBLE);
            steptext1.setText("Step 1");
            checkingissue1.setText("Click below button to perform basic troubleshooting like:");
            checkingissue1.setVisibility(View.VISIBLE);
            checkingissuedes1.setText("Deleting RPOS folders \nReinstalling JIO POS plus");
            textid1();
            btn1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    masterTroubleshooting();
                    layout2.setVisibility(View.VISIBLE);
                    steptext2.setText("Step 2");
                    // checkingissue2.setText("Need to do Data/Delta Sync from Jio Pos Plus application,Please click on below button");
                    checkingissuedes2.setText("Click below to refresh policies.");
                    textid2();
                    btn2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            refreshPolicies(view);
                            Runnable r = new Runnable() {
                                public void run() {
                                    trytologin_check();
                                }
                            };
                            handler.postDelayed(r, 3000);
                        }
                    });
                }
            });
        }

    }


    private void connectionrefused() {

        boolean isInstalledGT = Utils.isPackageInstalled("com.jio.jpp1", getPackageManager());
        boolean isInstalledRR = Utils.isPackageInstalled("com.ril.rposcentral", getPackageManager());

        if (isInstalledGT) {

            titletxt.setText("Connection refused. Please check your network connection.");
            layout1.setVisibility(GONE);
            layout2.setVisibility(View.VISIBLE);

            steptext2.setText("Step 1");
            checkingissue2.setText(R.string.rpos_jpos_folder_delete);

            textid2();
            btn2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Datacontainer.deletefolderusingpath();

                    if (!Datacontainer.folderdeletesuccess) {
                        Toast.makeText(getApplicationContext(), "No Folder Found", Toast.LENGTH_SHORT).show();
                        checkingissuedes2.setText("No folders found");
                        layout3.setVisibility(View.VISIBLE);
                        steptext3.setText("Step 2");
                        checkingissue3.setVisibility(GONE);
                        checkingissuedes3.setText(R.string.clear_cache_clear_data);

                        textid3();
                        btn3.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                openapplicationsetting();
                                Toast.makeText(Checking_issue.this, "Kindly click on Storage to do clear cache/Data", Toast.LENGTH_LONG).show();
                                layout4.setVisibility(View.VISIBLE);
                                //layout3.setVisibility(View.VISIBLE);
                                checkingissue4.setVisibility(View.VISIBLE);
                                steptext4.setText("Step 3");
                                checkingissue4.setText("Kindly check whether Apps mentioned below are installed in your device ,If available then kindly Uninstall .");
                                checkingissuedes4.setText(Html.fromHtml("1. Jio Pos EKYC Library,<br> 2. M-Assist Lite application ,<br>3. Jio Pos FTTx application ,<br>4. Jio Pos Digital application"));
                                textid4();
                                btn4.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {

                                        layout5.setVisibility(View.VISIBLE);
                                        steptext5.setText("Step 4");
                                        //layout3.setVisibility(View.VISIBLE);
                                        checkingissue5.setText("Kindly turn ON and OFF the mobile data");
                                        checkingissuedes5.setText("Click below to check network connectivity :");
                                        textid5();
                                        btn5.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                show_dialog_webview_nw();
                                                Runnable r = new Runnable() {
                                                    public void run() {
                                                        trytologin_check();
                                                    }
                                                };
                                                handler.postDelayed(r, 1000);
                                            }
                                        });
                                    }
                                });
                            }
                        });
                    } else {
                        Toast.makeText(getApplicationContext(), "Deleted", Toast.LENGTH_SHORT).show();
                        checkingissuedes2.setText("RPOS/JPOS folders deleted");
                        layout3.setVisibility(View.VISIBLE);
                        steptext3.setText("Step 2");
                        checkingissue3.setVisibility(GONE);
                        checkingissuedes3.setText(R.string.clear_cache_clear_data);

                        textid3();
                        btn3.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                openapplicationsetting();
                                Toast.makeText(Checking_issue.this, "Kindly click on Storage to do clear cache/Data", Toast.LENGTH_LONG).show();
                                layout4.setVisibility(View.VISIBLE);
                                //layout3.setVisibility(View.VISIBLE);
                                checkingissue4.setVisibility(View.VISIBLE);
                                steptext4.setText("Step 3");
                                checkingissue4.setText("Kindly check whether Apps mentioned below are installed in your device ,If available then kindly Uninstall .");
                                checkingissuedes4.setText(Html.fromHtml("1. Jio Pos EKYC Library,<br> 2. M-Assist Lite application ,<br>3. Jio Pos FTTx application ,<br>4. Jio Pos Digital application"));

                                textid4();
                                btn4.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {

                                        Intent intent = new Intent(Intent.ACTION_DELETE);
                                        intent.setData(Uri.parse("package:com.airwatch.androidagent"));
                                        startActivity(intent);
                                        layout5.setVisibility(View.VISIBLE);
                                        steptext5.setText("Step 4");
                                        //layout3.setVisibility(View.VISIBLE);
                                        checkingissue5.setText("Kindly turn ON and OFF the mobile data");
                                        checkingissuedes5.setText("Click below to check network connectivity :");
                                        textid5();
                                        btn5.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                show_dialog_webview_nw();
                                                Runnable r = new Runnable() {
                                                    public void run() {
                                                        trytologin_check();
                                                    }
                                                };
                                                handler.postDelayed(r, 1000);
                                            }
                                        });
                                    }
                                });
                            }
                        });
                    }
                }
            });
        }
        if (isInstalledRR) {
            titletxt.setText("connection refused.");
            checkingissue1.setText("Uninstall Jio POS Plus(GT Version)/ JIO POS eKYC Library application.");
            btn1.setVisibility(View.VISIBLE);

            btn1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boolean checkdevice1 = Utils.isPackageInstalled("com.jio.jpp1", getPackageManager());
                    boolean checkdevice2 = Utils.isPackageInstalled("com.jpp.lib", getPackageManager());


                    if (checkdevice1) {
                        Intent intent = new Intent(Intent.ACTION_DELETE);
                        intent.setData(Uri.parse("package:com.jio.jpp1"));
                        startActivity(intent);
                    }
                    if (checkdevice2) {
                        Intent intent = new Intent(Intent.ACTION_DELETE);
                        intent.setData(Uri.parse("package:com.jpp.lib"));
                        startActivity(intent);
                    }

                    layout2.setVisibility(View.VISIBLE);
                    steptext2.setText("Step 2");
                    checkingissuedes2.setText(R.string.clear_cache_clear_data);
                    btn2.setVisibility(View.VISIBLE);

                    textid2();
                    btn2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            openapplicationsetting();
                            Runnable r = new Runnable() {
                                public void run() {
                                    trytologin_check();

                                }
                            };
                            handler.postDelayed(r, 1000);
                        }
                    });

                }
            });
        }
    }

    private String getMemoryInfo() {
        Context context = getApplicationContext();
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);

        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        activityManager.getMemoryInfo(memoryInfo);

        DecimalFormat twoDecimalFormate = new DecimalFormat("#.##");

        String finalvalue = " ";
        long totalMemory = memoryInfo.totalMem;
        double kb = totalMemory / 1024.0;
        double mb = totalMemory / 1048576.0;
        double gb = totalMemory / 1073741824.0;
        double tb = totalMemory / 1099511627776.0;

        if (tb > 1) {
            finalvalue = twoDecimalFormate.format(tb).concat(" TB");
        } else if (gb > 1) {
            finalvalue = twoDecimalFormate.format(gb).concat(" GB");
        } else if (mb > 1) {
            finalvalue = twoDecimalFormate.format(mb).concat(" MB");
        } else if (kb > 1) {
            finalvalue = twoDecimalFormate.format(kb).concat(" KB");
        } else {
            finalvalue = twoDecimalFormate.format(totalMemory).concat(" Bytes");
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("RAM: ").append(finalvalue).append("\n").
                append("Available Internal/External free Space: " + megAvailable + "GB");
        return stringBuilder.toString();

    }

    private String getHardwareAndSoftwareInfo() {

        return "MODEL: " + " " + Build.MODEL + "\n" +
                "MANUFACTURER: " + " " + Build.MANUFACTURER + "\n" +
                "BRAND: " + " " + Build.BRAND + "\n" +
                "VERSIONCODE: " + " " + Build.VERSION.RELEASE;

    }

    public void show_dialog_webview() {
        LayoutInflater inflater = (LayoutInflater)
                getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.webview_layout, null);

        // create the popup window
        int width = LinearLayout.LayoutParams.MATCH_PARENT;
        int height = LinearLayout.LayoutParams.MATCH_PARENT;
        boolean focusable = true; // lets taps outside the popup also dismiss it
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

        popupWindow.showAtLocation(linearLayout, Gravity.CENTER, 0, 0);
        View v = popupWindow.getContentView();

        WebView webView = (WebView) v.findViewById(R.id.reset_password_webview);

        webView.setWebViewClient(new WebViewClient());
        //webView.loadUrl("https://www.youtube.com/watch?v=TUXui5ItBkM");
        webView.loadUrl("https://idm.jioconnect.com/oam-forgot-password/forgotPassword.jsp?resource_url=https%253A%252F%252Fidm.jioconnect.com%252Fhome.html");

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        ImageView imageView = v.findViewById(R.id.close_popup);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();

            }
        });

    }


    public void show_dialog_webview_nw()
    {
        LayoutInflater inflater = (LayoutInflater)
                getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.webview_layout, null);

        // create the popup window
        int width = LinearLayout.LayoutParams.MATCH_PARENT;
        int height = LinearLayout.LayoutParams.MATCH_PARENT;
        boolean focusable = true; // lets taps outside the popup also dismiss it
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

        popupWindow.showAtLocation(linearLayout, Gravity.CENTER, 0, 0);
        View v =popupWindow.getContentView();

        WebView webView = (WebView) v.findViewById(R.id.reset_password_webview);

        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("https://www.youtube.com/watch?v=l7L7uAHIUdA");
        //webView.loadUrl("https://idm.jioconnect.com/oam-forgot-password/forgotPassword.jsp?resource_url=https%253A%252F%252Fidm.jioconnect.com%252Fhome.html");

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        ImageView imageView = v.findViewById(R.id.close_popup);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();

            }
        });

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

    public void dismissdailogbox() {

        Issuetitle = issuepagetxt.getText().toString();

        SharedPreferences getshared = getSharedPreferences("Data", MODE_PRIVATE);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setCancelable(false);

        alertDialogBuilder.setMessage("Does your issue resolved?")
                .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Datacontainer.pleasewait_popup(linearLayout,Checking_issue.this);

                        String nprm = getshared.getString("strprm", "PRM");
                        String nmob = getshared.getString("strmob", "Mobile Number");
                        String nemail = getshared.getString("stremail", "Email ID");
                        String nname = getshared.getString("strname", "Name");


                        AutoResTicketPostData.postdata(Datacontainer.getDate(), nemail, Issuetitle, nmob, nname, nprm, "Auto - Resolved", "", "", "", Checking_issue.this, new APICallBack() {
                            @Override
                            public void onResponse(boolean success) {
                                if (!success){
                                    Toast.makeText(Checking_issue.this, "Unable to create ticket, Kindly Re-try", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });


                    }

                });

        alertDialogBuilder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        AlertDialog alert = alertDialogBuilder.create();
        alert.show();

    }

    public void textid1(){

        if(checkingissuedes1.getText().toString().contains("LINK1"))
        {
            Datacontainer.model(null,checkingissuedes1,Checking_issue.this,"<br><a href =\"https://idm.jioconnect.com/oam-forgot-password/forgotPassword.jsp?resource_url=https%253A%252F%252Fidm.jioconnect.com%252Fhome.html\">Password Reset</a>");
        }else if (checkingissuedes1.getText().toString().contains("LINK2")){
            Datacontainer.model(null,checkingissuedes1,Checking_issue.this,"<a href =\"https://youtu.be/l7L7uAHIUdA\">Click here</a>");
        }else if(checkingissuedes1.getText().toString().contains("LINK3")){
            Datacontainer.model(null,checkingissuedes1,Checking_issue.this,"<a href =\"https://play.google.com/store/apps/details?id=com.adobe.reader\">Click here</a>");
        }else{
            Datacontainer.model(null,checkingissuedes1,Checking_issue.this,"");

        }

        Datacontainer.model(null,checkingissue1,Checking_issue.this,"");
        Datacontainer.model(null,steptext1,Checking_issue.this,"");
        Datacontainer.model(btn1,null,Checking_issue.this,"");

    }
    public void textid2(){

        if(checkingissuedes2.getText().toString().contains("LINK1"))
        {
            Datacontainer.model(null,checkingissuedes2,Checking_issue.this,"<br><a href =\"https://idm.jioconnect.com/oam-forgot-password/forgotPassword.jsp?resource_url=https%253A%252F%252Fidm.jioconnect.com%252Fhome.html\">Password Reset</a>");
        }else if (checkingissuedes2.getText().toString().contains("LINK2")){
            Datacontainer.model(null,checkingissuedes2,Checking_issue.this,"<a href =\"https://youtu.be/l7L7uAHIUdA\">Click here</a>");
        }else if(checkingissuedes2.getText().toString().contains("LINK3")){
            Datacontainer.model(null,checkingissuedes2,Checking_issue.this,"<a href =\"https://play.google.com/store/apps/details?id=com.adobe.reader\">Click here</a>");
        }else{
            Datacontainer.model(null,checkingissuedes2,Checking_issue.this,"");

        }

        Datacontainer.model(null,checkingissue2,Checking_issue.this,"");
        Datacontainer.model(null,steptext2,Checking_issue.this,"");
        Datacontainer.model(btn2,null,Checking_issue.this,"");
    }
    public void textid3(){
        if(checkingissuedes3.getText().toString().contains("LINK1"))
        {
            Datacontainer.model(null,checkingissuedes3,Checking_issue.this,"<br><a href =\"https://idm.jioconnect.com/oam-forgot-password/forgotPassword.jsp?resource_url=https%253A%252F%252Fidm.jioconnect.com%252Fhome.html\">Password Reset</a>");

        }else if (checkingissuedes3.getText().toString().contains("LINK2")){
            Datacontainer.model(null,checkingissuedes3,Checking_issue.this,"<a href =\"https://youtu.be/l7L7uAHIUdA\">Click here</a>");
        }else if(checkingissuedes3.getText().toString().contains("LINK3")){
            Datacontainer.model(null,checkingissuedes3,Checking_issue.this,"<a href =\"https://play.google.com/store/apps/details?id=com.adobe.reader\">Click here</a>");
        }else{
            Datacontainer.model(null,checkingissuedes3,Checking_issue.this,"");

        }

        Datacontainer.model(null,checkingissue3,Checking_issue.this,"");
        Datacontainer.model(null,steptext3,Checking_issue.this,"");
        Datacontainer.model(btn3,null,Checking_issue.this,"");


    }
    public void textid4(){
        if(checkingissuedes4.getText().toString().contains("LINK1"))
        {
            Datacontainer.model(null,checkingissuedes4,Checking_issue.this,"<br><a href =\"https://idm.jioconnect.com/oam-forgot-password/forgotPassword.jsp?resource_url=https%253A%252F%252Fidm.jioconnect.com%252Fhome.html\">Password Reset</a>");
        }else if (checkingissuedes4.getText().toString().contains("LINK2")){
            Datacontainer.model(null,checkingissuedes4,Checking_issue.this,"<a href =\"https://youtu.be/l7L7uAHIUdA\">Click here</a>");
        }else if(checkingissuedes4.getText().toString().contains("LINK3")){
            Datacontainer.model(null,checkingissuedes4,Checking_issue.this,"<a href =\"https://play.google.com/store/apps/details?id=com.adobe.reader\">Click here</a>");
        }else{
            Datacontainer.model(null,checkingissuedes4,Checking_issue.this,"");

        }

        Datacontainer.model(null,checkingissue4,Checking_issue.this,"");
        Datacontainer.model(null,steptext4,Checking_issue.this,"");
        Datacontainer.model(btn4,null,Checking_issue.this,"");
    }
    public void textid5(){
        if(checkingissuedes5.getText().toString().contains("LINK1"))
        {
            Datacontainer.model(null,checkingissuedes5,
                    Checking_issue.this,
                    "<br><a href =\"https://idm.jioconnect.com/oam-forgot-password/forgotPassword.jsp?resource_url=https%253A%252F%252Fidm.jioconnect.com%252Fhome.html\">Password Reset</a>");
        }else if (checkingissuedes5.getText().toString().contains("LINK2")){
            Datacontainer.model(null,checkingissuedes5,Checking_issue.this,"<a href =\"https://youtu.be/l7L7uAHIUdA\">Click here</a>");
        }else if(checkingissuedes5.getText().toString().contains("LINK3")){
            Datacontainer.model(null,checkingissuedes5,Checking_issue.this,"<a href =\"https://play.google.com/store/apps/details?id=com.adobe.reader\">Click here</a>");
        }else{
            Datacontainer.model(null,checkingissuedes5,Checking_issue.this,"");

        }

        Datacontainer.model(null,checkingissue5,Checking_issue.this,"");
        Datacontainer.model(null,steptext5,Checking_issue.this,"");
        Datacontainer.model(btn5,null,Checking_issue.this,"");
    }
    public void textid6(){

        if(checkingissuedes6.getText().toString().contains("LINK1"))
        {
            Datacontainer.model(null,checkingissuedes6,Checking_issue.this,"<br><a href =\"https://idm.jioconnect.com/oam-forgot-password/forgotPassword.jsp?resource_url=https%253A%252F%252Fidm.jioconnect.com%252Fhome.html\">Password Reset</a>");
        }else if (checkingissuedes6.getText().toString().contains("LINK2")){
            Datacontainer.model(null,checkingissuedes6,Checking_issue.this,"<a href =\"https://youtu.be/l7L7uAHIUdA\">Click here</a>");
        }else if(checkingissuedes6.getText().toString().contains("LINK3")){
            Datacontainer.model(null,checkingissuedes6,Checking_issue.this,"<a href =\"https://play.google.com/store/apps/details?id=com.adobe.reader\">Click here</a>");
        }else{
            Datacontainer.model(null,checkingissuedes6,Checking_issue.this,"");

        }

        Datacontainer.model(null,checkingissue6,Checking_issue.this,"");
        Datacontainer.model(null,steptext6,Checking_issue.this,"");
        Datacontainer.model(btn6,null,Checking_issue.this,"");
        Datacontainer.model(btn7,null,Checking_issue.this,"");
    }
    public void textid7(){
        if(checkingissuedes7.getText().toString().contains("LINK1"))
        {
            Datacontainer.model(null,checkingissuedes7,Checking_issue.this,"<br><a href =\"https://idm.jioconnect.com/oam-forgot-password/forgotPassword.jsp?resource_url=https%253A%252F%252Fidm.jioconnect.com%252Fhome.html\">Password Reset</a>");
        }else if (checkingissuedes7.getText().toString().contains("LINK2")){
            Datacontainer.model(null,checkingissuedes7,Checking_issue.this,"<a href =\"https://youtu.be/l7L7uAHIUdA\">Click here</a>");
        }else if(checkingissuedes7.getText().toString().contains("LINK3")){
            Datacontainer.model(null,checkingissuedes7,Checking_issue.this,"<a href =\"https://play.google.com/store/apps/details?id=com.adobe.reader\">Click here</a>");
        }else{
            Datacontainer.model(null,checkingissuedes7,Checking_issue.this,"");
        }

        Datacontainer.model(null,checkingissue7,Checking_issue.this,"");
        Datacontainer.model(null,steptext7,Checking_issue.this,"");
        Datacontainer.model(btn8,null,Checking_issue.this,"");
        Datacontainer.model(btn9,null,Checking_issue.this,"");
    }
    public void textid8(){

        if(checkingissuedes8.getText().toString().contains("LINK1"))
        {
            Datacontainer.model(null,checkingissuedes8,Checking_issue.this,"<br><a href =\"https://idm.jioconnect.com/oam-forgot-password/forgotPassword.jsp?resource_url=https%253A%252F%252Fidm.jioconnect.com%252Fhome.html\">Password Reset</a>");
        }else if (checkingissuedes8.getText().toString().contains("LINK2")){
            Datacontainer.model(null,checkingissuedes8,Checking_issue.this,"<a href =\"https://youtu.be/l7L7uAHIUdA\">Click here</a>");
        }else if(checkingissuedes8.getText().toString().contains("LINK3")){
            Datacontainer.model(null,checkingissuedes8,Checking_issue.this,"<a href =\"https://play.google.com/store/apps/details?id=com.adobe.reader\">Click here</a>");
        }else{
            Datacontainer.model(null,checkingissuedes8,Checking_issue.this,"");
        }

        Datacontainer.model(null,checkingissue8,Checking_issue.this,"");
        Datacontainer.model(null,steptext8,Checking_issue.this,"");
        Datacontainer.model(btn10,null,Checking_issue.this,"");
    }

    public void textid9(){

        if(checkingissuedes9.getText().toString().contains("LINK1"))
        {
            Datacontainer.model(null,checkingissuedes9,Checking_issue.this,"<br><a href =\"https://idm.jioconnect.com/oam-forgot-password/forgotPassword.jsp?resource_url=https%253A%252F%252Fidm.jioconnect.com%252Fhome.html\">Password Reset</a>");
        }else if (checkingissuedes9.getText().toString().contains("LINK2")){
            Datacontainer.model(null,checkingissuedes9,Checking_issue.this,"<a href =\"https://youtu.be/l7L7uAHIUdA\">Click here</a>");
        }else if(checkingissuedes9.getText().toString().contains("LINK3")){
            Datacontainer.model(null,checkingissuedes9,Checking_issue.this,"<a href =\"https://play.google.com/store/apps/details?id=com.adobe.reader\">Click here</a>");
        }else{
            Datacontainer.model(null,checkingissuedes9,Checking_issue.this,"");
        }

        Datacontainer.model(null,checkingissue9,Checking_issue.this,"");
        Datacontainer.model(null,steptext9,Checking_issue.this,"");
//        Datacontainer.model(btn11,null,Checking_issue.this,"");
//        Datacontainer.model(btn12,null,Checking_issue.this,"");
//        Datacontainer.model(btn13,null,Checking_issue.this,"");
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

            String packageName = "com.jio.jpp1";
            handleResponse(getApplicationContext(),MDMSDK.clearAppData(getApplicationContext(),packageName));
//            MDMSDK.Response response = MDMSDK.getMDMInfo(Context);
//            Intent intent = new Intent();
//            intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
//            Uri uri = Uri.fromParts("package", "com.jio.jpp1", null);
//            intent.setData(uri);
//            startActivity(intent);

        }else {
            String packageName = "com.ril.rposcentral";
            handleResponse(getApplicationContext(),MDMSDK.clearAppData(getApplicationContext(),packageName));
//            Intent intent = new Intent();
//            intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
//            Uri uri = Uri.fromParts("package", "com.ril.rposcentral", null);
//            intent.setData(uri);
//            startActivity(intent);
        }
    }



    public static void handleResponse(Context context, MDMSDK.Response response) {
        if (response != null) {
            if (response.apiResult() == MDMSDK.APIResult.ACTION_NOT_SUPPORTED) {
                Toast.makeText(context, "This feature is not supported", Toast.LENGTH_LONG).show();
            } else if (response.apiResult() == MDMSDK.APIResult.NOT_AUTHORIZED) {
                Toast.makeText(context, "This app does not have the permission to call MDM APIs.", Toast.LENGTH_LONG).show();
            } else if (response.apiResult() == MDMSDK.APIResult.DEVICE_CURRENTLY_UNMANAGED) {
                Toast.makeText(context, "The device is currently not Locked/Managed. Action cannot be performed at this time.",
                        Toast.LENGTH_LONG).show();
            } else if (response.apiResult() == MDMSDK.APIResult.SUCCESS) {
                Toast.makeText(context, "Cache/Data Cleared.",
                        Toast.LENGTH_LONG).show();
            }
        }
    }

    public static void handleResponse1(Context context, MDMSDK.Response response) {
        if (response != null) {
            if (response.apiResult() == MDMSDK.APIResult.ACTION_NOT_SUPPORTED) {
                Toast.makeText(context, "This feature is not supported", Toast.LENGTH_LONG).show();
            } else if (response.apiResult() == MDMSDK.APIResult.NOT_AUTHORIZED) {
                Toast.makeText(context, "This app does not have the permission to call MDM APIs.", Toast.LENGTH_LONG).show();
            } else if (response.apiResult() == MDMSDK.APIResult.DEVICE_CURRENTLY_UNMANAGED) {
                Toast.makeText(context, "The device is currently not Locked/Managed. Action cannot be performed at this time.",
                        Toast.LENGTH_LONG).show();
            } else if (response.apiResult() == MDMSDK.APIResult.SUCCESS) {
                Toast.makeText(context, "Application Removed Successfully.",
                        Toast.LENGTH_LONG).show();
            }
        }
    }

    public void refreshPolicies(View view){

        response = MDMSDK.refreshPolicies(view.getContext());
        if (response != null) {
            if (response.apiResult() == MDMSDK.APIResult.ACTION_NOT_SUPPORTED) {
                Toast.makeText(view.getContext(), "Policy can be refreshed once in 15 minutes, Try after 15 minutes.", Toast.LENGTH_LONG).show();
            } else if (response.apiResult() == MDMSDK.APIResult.NOT_AUTHORIZED) {
                Toast.makeText(view.getContext(), "This app does not have the permission to call MDM APIs.", Toast.LENGTH_LONG).show();
            } else if (response.apiResult() == MDMSDK.APIResult.DEVICE_CURRENTLY_UNMANAGED) {
                Toast.makeText(view.getContext(), "The device is currently not Locked/Managed. Action cannot be performed at this time.",
                        Toast.LENGTH_LONG).show();
            } else if (response.apiResult() == MDMSDK.APIResult.SUCCESS) {
                Toast.makeText(view.getContext(), "Policies Refresh Done...",
                        Toast.LENGTH_LONG).show();
            }
        }
//        Toast.makeText(context, "Refreshing Policies, Please Wait....",
//                Toast.LENGTH_LONG).show();
    }

    public void uninstallApplicatios() {
        boolean isInstalledGT = Utils.isPackageInstalled("com.jio.jpp1", getPackageManager());
        boolean isInstalledRR = Utils.isPackageInstalled("com.ril.rposcentral", getPackageManager());
        if (isInstalledGT) {

            String packageName = "com.jio.jpp1";
            handleResponse1(getApplicationContext(), MDMSDK.uninstallApplication(getApplicationContext(), packageName));
        } else if (isInstalledRR) {
            String packageName = "com.ril.rposcentral";
            handleResponse1(getApplicationContext(), MDMSDK.uninstallApplication(getApplicationContext(), packageName));
        }
    }

    private void openapplicationsettingJMD(){
        boolean isInstalled = Utils.isPackageInstalled("com.fynd.jiomart", getPackageManager());
        if (isInstalled){

            String packageName = "com.fynd.jiomart";
            handleResponse(getApplicationContext(),MDMSDK.clearAppData(getApplicationContext(),packageName));
//            Intent intent = new Intent();
//            intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
//            Uri uri = Uri.fromParts("package", "com.fynd.jiomart", null);
//            intent.setData(uri);
//            startActivity(intent);

        }else {
//            Intent intent = new Intent();
//            intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
//            Uri uri = Uri.fromParts("pa
//
//
//
//            ckage", "com.ril.rposcentral", null);
//            intent.setData(uri);
//            startActivity(intent);
        }
    }

    public void masterTroubleshooting(){
        boolean isInstalledGT = Utils.isPackageInstalled("com.jio.jpp1", getPackageManager());
        boolean isInstalledRR = Utils.isPackageInstalled("com.ril.rposcentral", getPackageManager());
        if(isInstalledGT) {
            Datacontainer.deletefolderusingpath();
            if (!Datacontainer.folderdeletesuccess) {
                Toast.makeText(getApplicationContext(), "No Folder Found", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), "Deleted", Toast.LENGTH_SHORT).show();
            }

            uninstallApplicatios();

        }
        if(isInstalledRR){
            Datacontainer.deletefolderusingpath();
            if (!Datacontainer.folderdeletesuccess) {
                Toast.makeText(getApplicationContext(), "No Folder Found", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), "Deleted", Toast.LENGTH_SHORT).show();
            }

            uninstallApplicatios();
        }
    }

    private void MOP_RELATED_ISSUE(){
        layout1.setVisibility(GONE);

        layout3.setVisibility(View.VISIBLE);
        steptext3.setText("Step 1");
        checkingissue3.setText(R.string.clear_cache_clear_data);
        btn3.setVisibility(View.VISIBLE);

        textid3();
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openapplicationsetting();
                layout7.setVisibility(View.VISIBLE);
                steptext7.setText("Step 2");
                checkingissue7.setText(R.string.MOP_for__first_time);
                btn8.setVisibility(View.VISIBLE);
                btn8.setText("Yes");
                btn9.setVisibility(View.VISIBLE);
                btn9.setText("Nope");
                textid7();
            }
        });

        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(getApplicationContext(), "Kindly connect with with your SPOC to configure the UPI", Toast.LENGTH_LONG).show();
                layout8.setVisibility(View.VISIBLE);
                steptext8.setText("Step 3");
//                checkingissue8.setText(R.string.MOP_second_line);
                checkingissuedes8.setText("Kindly contact with with your _SPOC to configure the UPI");


                textid8();
                btn10.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Runnable r = new Runnable() {
                            public void run() {
                                trytologin_check();
                            }
                        };
                        handler.postDelayed(r, 1000);
                    }
                });
            }
        });

        textid7();
        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                layout8.setVisibility(View.VISIBLE);
                steptext8.setText("Step 3");
                checkingissue8.setText(R.string.MOP_second_line);
                checkingissuedes8.setText(R.string.MOP_third_line);


                textid8();
                btn10.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Runnable r = new Runnable() {
                            public void run() {
                                trytologin_check();
                            }
                        };
                        handler.postDelayed(r, 1000);
                    }
                });
            }
        });
    }

    private void wifi_related(){
        layout1.setVisibility(GONE);
        layout2.setVisibility(View.VISIBLE);

        btn2.setVisibility(GONE);
        steptext2.setText("Step 1");
        checkingissue2.setText("Kindly turn ON the WiFi , and do not connect with any other network . Kindly log in with Jio mobile data .");

        textid2();
        wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        //    WiFi_Status_TextView = (TextView) findViewById(R.id.WiFi_Status_TextView);
        Wifi_switch = (Switch) layout2.findViewById(R.id.Wifi_switch);
        Wifi_switch.setVisibility(View.VISIBLE);
        //    WiFi_onstate=(TextView)findViewById(R.id.WiFi_onstate);


        if (wifiManager.isWifiEnabled()) {
            checkingissuedes2.setText("Wifi Is Currently ON");
            Datacontainer.model(null,checkingissuedes2,Checking_issue.this,"");
            Wifi_switch.setChecked(true);
            Toast.makeText(getApplicationContext(), "Kindly Do not connect with other device,and try to login JPP", Toast.LENGTH_LONG).show();
            Runnable r = new Runnable() {
                public void run() {
                    trytologin_check();

                }

            };
            handler.postDelayed(r, 5000);
        } else {
            checkingissuedes2.setText("Wifi Is Currently OFF");
            Wifi_switch.setChecked(false);
            Datacontainer.model(null,checkingissuedes2,Checking_issue.this,"");

        }


        Wifi_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {


                    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.R)
                        wifiManager.setWifiEnabled(true);
                    else {
                        Intent panelIntent = new Intent(Settings.Panel.ACTION_WIFI);
                        startActivityForResult(panelIntent, 1);
                    }
                    checkingissuedes2.setText("Wifi Is Currently ON");
                    Datacontainer.model(null,checkingissuedes2,Checking_issue.this,"");
                    layout3.setVisibility(View.VISIBLE);
                    steptext3.setVisibility(GONE);
                    checkingissuedes3.setText(Html.fromHtml("Note: If you are not able to turn ON the WiFi , click on Below Button to enable the WiFi"));
                    textid3();
                    btn2.setVisibility(GONE);
                    Toast.makeText(getApplicationContext(), "Kindly Do not connect with other device,and try to login JPP", Toast.LENGTH_LONG).show();
                    btn3.setText("Enable");
                    btn3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            try {
                                Intent intent = new Intent(Intent.ACTION_MAIN, null);
                                intent.addCategory(Intent.CATEGORY_LAUNCHER);
                                ComponentName cn = new ComponentName("com.android.settings", "com.android.settings.wifi.WifiSettings");
                                intent.setComponent(cn);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);

                            } catch (ActivityNotFoundException ignored) {
                                startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
                            }
                            if (isChecked) {
                                Wifi_switch.setChecked(true);
                                checkingissuedes2.setText("Wifi Is Currently ON");
                                Datacontainer.model(null,checkingissuedes2,Checking_issue.this,"");
                                Runnable r = new Runnable() {
                                    public void run() {
                                        onButtonShowPopupWindowClick();
                                    }
                                };
                                handler.postDelayed(r, 4000);


                            }
                        }
                    });

                } else {
                    wifiManager.setWifiEnabled(false);
                    checkingissuedes2.setText("Wifi Is Currently OFF");
                    Datacontainer.model(null,checkingissuedes2,Checking_issue.this,"");
                    layout3.setVisibility(View.VISIBLE);
                    steptext2.setVisibility(GONE);
                    checkingissue3.setText(Html.fromHtml("<B>Click Below to turn ON Wifi manually"));
                    checkingissuedes3.setVisibility(GONE);
                    btn3.setText("Enable");
                    textid3();
                    btn3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            try {
                                Intent intent = new Intent(Intent.ACTION_MAIN, null);
                                intent.addCategory(Intent.CATEGORY_LAUNCHER);
                                ComponentName cn = new ComponentName("com.android.settings", "com.android.settings.wifi.WifiSettings");
                                intent.setComponent(cn);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                            } catch (ActivityNotFoundException ignored) {
                                startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
                            }
                            if (isChecked) {
                                Wifi_switch.setChecked(true);
                                checkingissuedes2.setText("Wifi Is Currently ON");
                                Datacontainer.model(null,checkingissuedes2,Checking_issue.this,"");
                                Runnable r = new Runnable() {
                                    public void run() {
                                        onButtonShowPopupWindowClick();
                                    }
                                };
                                handler.postDelayed(r, 4000);
                            }
                        }
                    });
                    Runnable r = new Runnable() {
                        public void run() {
                            trytologin_check();

                        }

                    };
                    handler.postDelayed(r, 9000);
                }
            }

        });
    }

    private void reload(){
        finish();
        startActivity(getIntent());
        overridePendingTransition(0, 0);
    }

    private void latlong(){

        checkingissue1.setText("Ensure device is not connected to wi-fi, use Jio data only.");
        checkingissuedes1.setText("Click below to check network connectivity :");
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show_dialog_webview_nw();
                layout2.setVisibility(View.VISIBLE);
                steptext2.setText("Step 2");
                checkingissue2.setVisibility(GONE);
                checkingissuedes2.setText("Kindly turn Automatic_Time_Zone ON and Set Time Format as 24 Hours \n Click on Below To Set Device Time ");
                textid2();
                btn2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            Intent intent = new Intent();
                            startActivity(new Intent(Settings.ACTION_DATE_SETTINGS));
                            startActivity(intent);

                        } catch (Exception e) {
                            Toast.makeText(getApplicationContext(),
                                    "Kindly set device TIME='AUTOMATICALLY' and FORMAT='24 Hours' ",
                                    Toast.LENGTH_LONG).show();
                        }

                        layout3.setVisibility(View.VISIBLE);
                        steptext3.setText("Step 3");
                        checkingissue3.setText("Kindly enable Device Location Service and set it on High Accuracy.");
                        checkingissuedes3.setText("Click below to change settings");
                        textid3();
                        btn3.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                                startActivity(intent);
                                layout4.setVisibility(View.VISIBLE);
                                steptext4.setText("Step 4");
                                checkingissue4.setText("Kindly update Google Maps service from Playstore and fetch current location.");
                                checkingissuedes4.setText(R.string.clear_cache_clear_data);
                                textid4();
                                btn4.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        openapplicationsetting();
                                        Toast.makeText(Checking_issue.this, "Kindly click on Storage to do clear cache/Data", Toast.LENGTH_LONG).show();
                                        layout5.setVisibility(View.VISIBLE);
                                        steptext5.setText("Step 5");
                                        checkingissue5.setText(R.string.data_delta_sync);
                                        textid5();
                                        btn5.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                openapplicationfordatasync();
                                                Runnable r = new Runnable() {
                                                    public void run() {
                                                        trytologin_check();
                                                    }
                                                };

                                                handler.postDelayed(r, 1000);
                                            }
                                        });
                                    }
                                });
                            }
                        });
                    }
                });
            }
        });

    }

    private void directlycreateticket(){

        Issuetitle = titletxt.getText().toString();
        btn1.setVisibility(View.VISIBLE);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    CreateTicketPostdata.postdata("", "Team 0", "", Datacontainer.getDate(), "R-Ansh", "", nmob, nprm, Datacontainer.getDate()+"\nCreated by RAnsh", "open", Issuetitle, "", "",Checking_issue.this, new APICallBack() {
                        @Override
                        public void onResponse(boolean success) {
                            if (!success){
                                Toast.makeText(Checking_issue.this, "Unable to create ticket, Kindly Re-try", Toast.LENGTH_SHORT).show();
                            }else if(success){
                                String agentname = "RANSH";
                                CreateTicketPostdata.postlogdata(Datacontainer.getintid, Datacontainer.getDate(), "Created by RAnsh",agentname, "open", Checking_issue.this, new APICallBack() {
                                    @Override
                                    public void onResponse(boolean succ) {
                                        if (succ){

                                            Intent intent = new Intent(Checking_issue.this, Ticket_NotResolved.class);
                                            intent.putExtra("Ticket_no", Datacontainer.getintid );
                                            intent.putExtra("Title",Issuetitle);
                                            intent.putExtra("Mobilenumber",nmob);
                                            intent.putExtra("Resolved", "notresolved");
                                            startActivity(intent);
                                            finish();
                                            Toast.makeText(Checking_issue.this, "Thankyou for using RAnsh, will connect you soon.", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });

                            }
                        }
                    });
                }catch (Exception e){
                    Log.e("mylog", "Error: " + e.getMessage());
                }
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id =item.getItemId();
        if (id == android.R.id.home){
            if (textsearch.equals("close")){
                Intent intent = new Intent(Checking_issue.this,MainHomePage.class);
                startActivity(intent);
            }else{
                this.finish();
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressLint("Range")
    public void trytologin_check(){
        LayoutInflater inflater = (LayoutInflater)
                getSystemService(LAYOUT_INFLATER_SERVICE);
        View trytologin_popupView = inflater.inflate(R.layout.fragment_tryto_login, null);

        // create the popup window
        int width = LinearLayout.LayoutParams.MATCH_PARENT;
        int height = LinearLayout.LayoutParams.MATCH_PARENT;
        boolean focusable = false; // lets taps outside the popup also dismiss it
        final PopupWindow trytologinpopupWindow = new PopupWindow(trytologin_popupView, width, height, focusable);
        linearLayout = (RelativeLayout) findViewById(R.id.formlayouttoshow);
        trytologinpopupWindow.showAtLocation(linearLayout, Gravity.CENTER, 0, 0);

        View v =trytologinpopupWindow.getContentView();

        ImageView imageView = v.findViewById(R.id.close_popup);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                trytologinpopupWindow.dismiss();
                linearLayout.setAlpha(1f);
            }
        });


        linearLayout.setAlpha(0.1f);

        Button onclickposbtn = v.findViewById(R.id.open_pos);
        onclickposbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                trytologinpopupWindow.dismiss();
                onButtonShowPopupWindowClick();
            }
        });
    }

    public void onButtonShowPopupWindowClick(){

        queryresolved++;

        LayoutInflater inflater = (LayoutInflater)
                getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.fragemnt_doesissueresolved, null);

        // create the popup window
        int width = LinearLayout.LayoutParams.MATCH_PARENT;
        int height = LinearLayout.LayoutParams.MATCH_PARENT;
        boolean focusable = false; // lets taps outside the popup also dismiss it
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

        popupWindow.showAtLocation(linearLayout, Gravity.CENTER, 0, 0);

        View v =popupWindow.getContentView();

        linearLayout.setAlpha(0.1f);

        Issuetitle = titletxt.getText().toString();
        SharedPreferences getshared = getSharedPreferences("Data", MODE_PRIVATE);

        Button yesbtn = v.findViewById(R.id.issue_resolved);
        yesbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Datacontainer.pleasewait_popup(linearLayout,Checking_issue.this);

                String nprm = getshared.getString("strprm", "PRM");
                String nmob = getshared.getString("strmob", "Mobile Number");
                String nemail = getshared.getString("stremail", "Email ID");
                String nname = getshared.getString("strname", "Name");

                try {
                    AutoResTicketPostData.postdata(Datacontainer.getDate(), nemail, Issuetitle, nmob, nname, nprm, "Auto - Resolved", "", "", "", Checking_issue.this, new APICallBack() {
                        @Override
                        public void onResponse(boolean success) {
                            if (!success){
                                Toast.makeText(Checking_issue.this, "Unable to create ticket, Kindly Re-try", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }catch (Exception e){
                    Log.e("mylog", "Error: " + e.getMessage());
                }
            }
        });
        Button nobtn = v.findViewById(R.id.issue_notresolved);
        nobtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    CreateTicketPostdata.postdata("", "Team 0", "", Datacontainer.getDate(), "R-Ansh", "", nmob, nprm, Datacontainer.getDate()+"\nCreated by RAnsh", "open", Issuetitle, "", "",Checking_issue.this, new APICallBack() {
                        @Override
                        public void onResponse(boolean success) {
                            if (!success){
                                Toast.makeText(Checking_issue.this, "Unable to create ticket, Kindly Re-try", Toast.LENGTH_SHORT).show();
                            }else if(success){
                                String agentname = "RANSH";
                                CreateTicketPostdata.postlogdata(Datacontainer.getintid,  Datacontainer.getDate(),"Created by RAnsh",agentname, "open", Checking_issue.this, new APICallBack() {
                                    @Override
                                    public void onResponse(boolean succ) {
                                        if (succ){
                                            Intent intent = new Intent(Checking_issue.this, Ticket_NotResolved.class);
                                            intent.putExtra("Ticket_no", Datacontainer.getintid );
                                            intent.putExtra("Title",Issuetitle);
                                            intent.putExtra("Mobilenumber",nmob);
                                            intent.putExtra("Resolved", "notresolved");
                                            startActivity(intent);
                                            finish();
                                            Toast.makeText(Checking_issue.this, "Thankyou for using RAnsh, will connect you soon.", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });

                            }
                        }
                    });
                }catch (Exception e){
                    Log.e("mylog", "Error: " + e.getMessage());
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (textsearch.equals("close")){
            Intent intent = new Intent(Checking_issue.this,MainHomePage.class);
            startActivity(intent);
        }else if(queryresolved>0){

        }else{
            this.finish();
        }
    }





}