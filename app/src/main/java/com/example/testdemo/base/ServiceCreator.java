package com.example.testdemo.base;

import com.example.testdemo.utils.LogUtil;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by chengjuechao on 2018/12/24.
 * 添加公参记得UploadAddPartUtil这个类也要添加
 */
public class ServiceCreator<T> {
  private final static String BASE_URL="https://wanandroid.com/";
  private static ServiceCreator mInstance;
  private static Retrofit                                        mRetrofit;

  private ServiceCreator() {
    OkHttpClient.Builder builder = new OkHttpClient.Builder();
      HttpLoggingInterceptor loggingInterceptor =
          new HttpLoggingInterceptor(message -> LogUtil.debugLongInfo("http", message));
      builder.addInterceptor(loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY));
    OkHttpClient okHttpClient = builder
        .retryOnConnectionFailure(false)//弱环境防止重复提交
        .connectTimeout(15000L, TimeUnit.MILLISECONDS)
        .readTimeout(15000L, TimeUnit.MILLISECONDS)
        .dns(new Ipv4DnsSelector())
        .build();
    mRetrofit = new Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build();
  }

  public T create(Class<T> serviceClass) {
    return mRetrofit.create(serviceClass);
  }

  public static ServiceCreator getInstance() {
    if (mInstance == null) {
      synchronized (ServiceCreator.class) {
        if (mInstance == null) {
          mInstance = new ServiceCreator();
        }
      }
    }
    return mInstance;
  }
}
