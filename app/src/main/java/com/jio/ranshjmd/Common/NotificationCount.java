package com.jio.ranshjmd.Common;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.jio.ranshjmd.R;

public class NotificationCount {
    private TextView notificationNumber;

    private final int MAX_NUMBER = 99;
    private final int MIN_NUMBER = 0;
    private int notification_number_counter =0;

    public NotificationCount(View view){
        notificationNumber = view.findViewById(R.id.textView);

    }

    public void increament(){
        if(notification_number_counter>MAX_NUMBER){
            Log.d("Counter","Maximum number reached");
        }
        else{
          notification_number_counter++;
            notificationNumber.setVisibility(View.VISIBLE);
            notificationNumber.setText(String.valueOf(notification_number_counter));
        }
    }
}
