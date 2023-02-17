package com.daffodil.androidboilerplate.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;

public class NetworkReachability {

    private final ConnectivityManager mConnectivityManager;

    public NetworkReachability(Context context) {
        mConnectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
    }

    public boolean isConnected() {
        NetworkInfo activeNetwork = mConnectivityManager.getActiveNetworkInfo();
        return activeNetwork != null &&
                activeNetwork.isConnected();
    }

    public boolean isConnectedWifi(){
        NetworkInfo activeNetwork = mConnectivityManager.getActiveNetworkInfo();
        Network network = mConnectivityManager.getActiveNetwork();
        NetworkCapabilities capabilities = mConnectivityManager.getNetworkCapabilities(network);
        return capabilities != null && activeNetwork != null && activeNetwork.isConnected() && capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI);
    }

    public boolean isConnectedMobile(){
        NetworkInfo activeNetwork = mConnectivityManager.getActiveNetworkInfo();
        Network network = mConnectivityManager.getActiveNetwork();
        NetworkCapabilities capabilities = mConnectivityManager.getNetworkCapabilities(network);
        return capabilities != null && activeNetwork != null && activeNetwork.isConnected() && capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR);
    }
}
