package com.jio.ranshjmd.Bottomnavigationbar;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.mlkit.common.model.DownloadConditions;
import com.google.mlkit.nl.translate.TranslateLanguage;
import com.google.mlkit.nl.translate.Translation;
import com.google.mlkit.nl.translate.Translator;
import com.google.mlkit.nl.translate.TranslatorOptions;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import com.jio.ranshjmd.Common.Datacontainer;
import com.jio.ranshjmd.R;

public class Fragment_LanguageSelector extends Fragment {

    String[] lang_list = new String[] {
            "English",
            "Hindi"
    };

    String targetlanguage = "";
    TranslatorOptions options;
    public static Translator englishGermanTranslator;


    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_languageselector,container,false );

        TextView currentLanguage = view.findViewById(R.id.current_language);
        currentLanguage.setText("Your Current Language is : " + Datacontainer.getlanguage);
        final ListView ListView = (ListView) view.findViewById(R.id.lang_listview);
        final List<String> fruits_list = new ArrayList<String>(Arrays.asList(lang_list));

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>
                (Fragment_LanguageSelector.this.getActivity(), android.R.layout.simple_list_item_1, fruits_list);
        ListView.setAdapter(arrayAdapter);

        ListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Datacontainer.getlanguage = (String) parent.getItemAtPosition(position);
                if (position == 0){
                    Datacontainer.customtoast("Langauge Selected as: English",Fragment_LanguageSelector.this.getActivity(),R.drawable.check_icon);
                    setlocale(Fragment_LanguageSelector.this.getActivity(),"en");
                    getFragmentManager().beginTransaction().detach(Fragment_LanguageSelector.this).attach(Fragment_LanguageSelector.this).commit();
                }else if(position == 1) {
                    targetlanguage = "Hindi";
                    configurelanguage();
                    Datacontainer.customtoast("Langauge Selected as: Hindi", Fragment_LanguageSelector.this.getActivity(),R.drawable.check_icon);
                }else if(position == 2) {
                    targetlanguage = "Marathi";
                    configurelanguage();
                    Datacontainer.customtoast("Langauge Selected as: Marathi", Fragment_LanguageSelector.this.getActivity(),R.drawable.check_icon);
                }
            }
        });

        return view;
    }
    public void configurelanguage() {
        ProgressDialog progressDialog = new ProgressDialog(Fragment_LanguageSelector.this.getActivity());
        progressDialog.setMessage("This may take several minutes depending upon network speed"); // Setting Message
        progressDialog.setTitle("Configuring resource language"); // Setting Title
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER); // Progress Dialog Style Spinner
        progressDialog.show(); // Display Progress Dialog
        progressDialog.setCancelable(false);

        if (targetlanguage == "English") {
            setlocale(Fragment_LanguageSelector.this.getActivity(),"en");
            ///to be excuted
        } else{
            switch (targetlanguage) {
                case "Hindi":
                    options = new TranslatorOptions.Builder()
                            .setSourceLanguage(TranslateLanguage.ENGLISH)
                            .setTargetLanguage(TranslateLanguage.HINDI)
                            .build();
                    setlocale(Fragment_LanguageSelector.this.getActivity(),"hi");


                    break;
                case "Marathi":
                    options = new TranslatorOptions.Builder()
                            .setSourceLanguage(TranslateLanguage.ENGLISH)
                            .setTargetLanguage(TranslateLanguage.MARATHI)
                            .build();
                    break;


            }

           englishGermanTranslator =
                    Translation.getClient(options);

            DownloadConditions conditions = new DownloadConditions.Builder()
                    .build();

            englishGermanTranslator.downloadModelIfNeeded(conditions).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {
                    progressDialog.dismiss();
                    Intent i = new Intent(Fragment_LanguageSelector.this.getActivity(),MainHomePage.class);
                    startActivity(i);
                    getFragmentManager().beginTransaction().detach(Fragment_LanguageSelector.this).attach(Fragment_LanguageSelector.this).commit();

                }
            });
        }
    }

    public void setlocale(Activity activity,String Langcode){
        Locale locale = new Locale(Langcode);
        Locale.setDefault(locale);
        Resources resources = activity.getResources();
        Configuration config = resources.getConfiguration();
        config.setLocale(locale);
        resources.updateConfiguration(config,resources.getDisplayMetrics());
    }


}
