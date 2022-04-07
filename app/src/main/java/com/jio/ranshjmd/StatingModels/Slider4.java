package com.jio.ranshjmd.StatingModels;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.jio.ranshjmd.Common.Datacontainer;
import com.jio.ranshjmd.R;

public class Slider4 extends Fragment {

    CheckBox checkBox;
    TextView textView,textView1,textView2;
    RelativeLayout linearLayout;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View rootview = inflater.inflate(R.layout.activity_slider4, container, false);
//Checkbox
//        checkBox = (CheckBox) rootview.findViewById(R.id.simpleCheckBox);
//        boolean checkbooxchecked = checkBox.isChecked();
//        checkcheckbox(checkbooxchecked);
//
//        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                checkcheckbox(buttonView.isChecked());
//            }
//        });
//Textview


        return rootview;
    }

    private void checkcheckbox(boolean ischecked){
        if (ischecked) {
            Datacontainer.checkbox = "Yes";
        }else{
            Datacontainer.checkbox = "No";
        }
    }

    @Override
    public void onResume() {
        super.onResume();

    }

}