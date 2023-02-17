package com.daffodil.androidboilerplate.network;


import java.io.IOException;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * This class is used to set certain values in header,
 * before making api requests
 */
public class DefaultInterceptor implements Interceptor {

    private final NetworkReachability mReachability;
    private final HashMap<String, String> mMap;
    /**
     * This method require certain information before adding it to the api header
     *
     * @param reachability      This provides a check for network availability
     */
    DefaultInterceptor(NetworkReachability reachability, HashMap<String,String> map) {
        mReachability = reachability;
        mMap = map;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        if (!mMap.isEmpty()){
            Headers.Builder builder = request.headers().newBuilder();
            builder.set("Accept", "application/json");
            for (Map.Entry<String, String> entry : mMap.entrySet()) {
                builder.add(entry.getKey(), entry.getValue());
            }
            Headers moreHeaders = builder.build();
            request = request.newBuilder().headers(moreHeaders).build();
        }

        Response response;
        if (mReachability.isConnected()) {
            response = chain.proceed(request);
            return response;
        } else {
            throw new UnknownHostException();
        }
    }
}
