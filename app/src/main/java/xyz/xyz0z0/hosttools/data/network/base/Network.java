package xyz.xyz0z0.hosttools.data.network.base;

import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by chengxg
 * on 2018/11/26
 */
public class Network {

  private static ApiService apiService;
  private static OkHttpClient okHttpClient = new OkHttpClient.Builder()
      .connectTimeout(10, TimeUnit.SECONDS)
      .writeTimeout(10, TimeUnit.SECONDS)
      .readTimeout(10, TimeUnit.SECONDS)
      .build();
  private static Converter.Factory gsonConverterFactory = GsonConverterFactory.create();
  private static CallAdapter.Factory rxJavaCallAdapterFactory = RxJava2CallAdapterFactory.create();


  public static ApiService getApiService() {
    if (apiService == null) {
      Retrofit retrofit = new Retrofit.Builder()
          .client(okHttpClient)
          .baseUrl("https://api.64clouds.com/v1/")
          .addConverterFactory(gsonConverterFactory)
          .addCallAdapterFactory(rxJavaCallAdapterFactory)
          .build();
      apiService = retrofit.create(ApiService.class);
    }
    return apiService;
  }

}
