package com.tulik15b.topgitrepositories.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class TGRepoCommonUtils {

    public static boolean isNetworkPresent(Context context) {
        boolean haveConnectedWifi = false;
        boolean haveConnectedMobile = false;

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] netInfo = cm.getAllNetworkInfo();

        for (NetworkInfo ni : netInfo) {
            if (ni.getTypeName().equalsIgnoreCase("WIFI") || ni.getTypeName().equalsIgnoreCase("MOBILE"))
                return true;

        }
        return false;
    }
}
