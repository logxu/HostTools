package xyz.xyz0z0.hosttools.data.network;

import io.reactivex.Observable;
import xyz.xyz0z0.hosttools.data.network.model.ServiceInfoResponse;

public interface ApiHelperSource {

  Observable<ServiceInfoResponse> getServiceInfo(int id, String key);

}
