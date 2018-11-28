package xyz.xyz0z0.hosttools.net.api;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import xyz.xyz0z0.hosttools.model.ServiceInfo;

/**
 * Created by chengxg
 * on 2018/11/26
 */
public interface ApiService {
  //https://api.64clouds.com/v1/getServiceInfo?veid=61026&api_key=YOUR_API_KEY_HERE

  @GET("getServiceInfo")
  Observable<ServiceInfo> getServiceInfo(@Query("veid") String veId, @Query("api_key") String apiKey);
}