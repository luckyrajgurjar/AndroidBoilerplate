package com.daffodil.androidboilerplate.network

import android.util.Log
import com.daffodil.androidboilerplate.BuildConfig
import okhttp3.HttpUrl
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ServiceManager {

    private var sServiceManager: ServiceManager? = null
    private var mServiceApiEndPoint: String? = null
    private var mMainMap: HashMap<String, String>? = null

    /**
     * Gets the instance of the web services implementation.
     *
     * @return the singleton instance.
     */
    fun get(): ServiceManager {
        if (sServiceManager == null) {
            sServiceManager = ServiceManager()
        }
        return sServiceManager!!
    }

    fun initialize(url: String?, map: HashMap<String, String>?) {
        if (sServiceManager == null) {
            sServiceManager = ServiceManager()
        }
        mServiceApiEndPoint = url
        mMainMap = map
    }

    /**
     * Creates the services for a given HTTP Url, The request made with this will be cached.
     *
     * @param clazz the service class.
     * @param <T>   type of the service.
     * @return the created services implementation.
    </T> */
    fun <T> createService(
        clazz: Class<T>?,
        reachability: NetworkReachability?,
        map: HashMap<String, String>?
    ): T {
        var map = map
        if (map == null) {
            map = HashMap()
        }
        map.putAll(mMainMap!!)
        return createService(
            clazz,
            mServiceApiEndPoint!!.toHttpUrlOrNull(),
            reachability,
            map
        )
    }

    /**
     * Creates the services for a given HTTP Url, The request made with this are not cached.
     *
     * @param clazz the service class.
     * @param <T>   type of the service.
     * @return the created services implementation.
    </T> */
    fun <T> createService(clazz: Class<T>?): T {
        return createService(clazz, getRetrofit(mServiceApiEndPoint!!.toHttpUrlOrNull()!!))
    }

    /**
     * Creates the services for a given HTTP Url, useful when testing
     * through multiple endpoints and unit testing.
     *
     * @param clazz   the service class.
     * @param httpUrl the endpoint
     * @param <T>     type of the service.
     * @return the created services implementation.
    </T> */
    fun <T> createService(
        clazz: Class<T>?,
        httpUrl: HttpUrl?,
        reachability: NetworkReachability?,
        map: HashMap<String, String>
    ): T {
        val retrofit: Retrofit = getRetrofit(httpUrl!!, reachability, map)
        return retrofit.create(clazz)
    }

    fun <T> createService(clazz: Class<T>?, retrofit: Retrofit): T {
        return retrofit.create(clazz)
    }

    private fun getRetrofit(
        httpUrl: HttpUrl,
        reachability: NetworkReachability?,
        map: HashMap<String, String>
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(httpUrl)
            .client(createClient(reachability, map))
            .addConverterFactory(getConverter())
            .build()
    }

    private fun getRetrofit(httpUrl: HttpUrl): Retrofit {
        return Retrofit.Builder()
            .baseUrl(httpUrl)
            .client(createClient())
            .addConverterFactory(getConverter())
            .build()
    }

    private fun getConverter(): Converter.Factory? {
        return GsonConverterFactory.create()
    }

    private fun createClient(
        reachability: NetworkReachability?,
        map: HashMap<String, String>
    ): OkHttpClient? {
        val builder: OkHttpClient.Builder = OkHttpClient.Builder()
        builder.readTimeout(30, TimeUnit.SECONDS)
            builder.addInterceptor(DefaultInterceptor(reachability, map))
        if (BuildConfig.DEBUG) {
            val httpLoggingInterceptor = HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
                override fun log(message: String) {
                    Log.e("ServiceManager", message)
                }
            })
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            builder.addInterceptor(httpLoggingInterceptor)
        }
        return builder.build()
    }

    private fun createClient(): OkHttpClient? {
        return OkHttpClient.Builder().build()
    }

}
