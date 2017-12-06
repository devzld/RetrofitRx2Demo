package com.zld.retrofitrx.utils;

import com.zld.retrofitrx.AppConfig;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by lingdong on 2017/12/6.
 */

public class RetrofitFactory {

    private static Object Object = new Object();

    private volatile static Retrofit retrofit;

    public static Retrofit getRetrofit(){
        synchronized (Object){
            if(retrofit == null){
                OkHttpClient.Builder builder = new OkHttpClient.Builder()
                        .connectTimeout(30, TimeUnit.SECONDS)
                        .readTimeout(30,TimeUnit.SECONDS)
                        .writeTimeout(30,TimeUnit.SECONDS)
                        .retryOnConnectionFailure(true);
                retrofit = new Retrofit.Builder()
                        .baseUrl(AppConfig.BASE_URL)
                        .client(builder.build())
                        .addConverterFactory(GsonConverterFactory.create())
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .build();
            }
            return retrofit;
        }
    }


}
