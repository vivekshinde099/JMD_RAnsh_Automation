package com.jio.ranshjmd.Common;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

public class Utils {
    public static boolean isPackageInstalled(String packageName, PackageManager packageManager) {
        try {
            return packageManager.getApplicationInfo(packageName, 0).enabled;
        }
        catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }

    public static PackageInfo getversionname(String packagename, PackageManager packageManager ) {

        try{
            return packageManager.getPackageInfo(packagename,0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }




}