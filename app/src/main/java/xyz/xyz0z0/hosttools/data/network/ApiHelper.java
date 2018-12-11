package xyz.xyz0z0.hosttools.data.network;

import io.reactivex.Observable;
import xyz.xyz0z0.hosttools.data.network.model.ServiceInfoResponse;
import xyz.xyz0z0.hosttools.data.network.base.Network;

public class ApiHelper implements ApiHelperSource {

  @Override
  public Observable<ServiceInfoResponse> getServiceInfo(int id, String key) {
    return Network.getApiService().getServiceInfo(id, key);
  }
}
