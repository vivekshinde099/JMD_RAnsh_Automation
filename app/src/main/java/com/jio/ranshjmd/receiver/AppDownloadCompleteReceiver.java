package com.jio.ranshjmd.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.text.TextUtils;
import android.widget.Toast;

public class AppDownloadCompleteReceiver extends BroadcastReceiver {

    public static final String ACTION = "mdm.intent.action.APP_DOWNLOAD_COMPLETED_SCHEDULED_TO_INSTALL_ON_REBOOT";

    @Override
    public void onReceive(Context context, Intent intent) {
        if (TextUtils.equals(intent.getAction(), "mdm.intent.action.APP_DOWNLOAD_COMPLETED_SCHEDULED_TO_INSTALL_ON_REBOOT")) {
            Toast.makeText(context, "App download completed & scheduled to install/update after device reboot.", Toast.LENGTH_LONG).show();
        }
    }

    public IntentFilter getIntentFilter() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(ACTION);
        return filter;
    }
}
