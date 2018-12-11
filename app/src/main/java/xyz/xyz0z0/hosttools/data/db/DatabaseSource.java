package xyz.xyz0z0.hosttools.data.db;

import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Single;
import java.util.List;
import xyz.xyz0z0.hosttools.data.db.base.ServiceInfo;

public interface DatabaseSource {

  Maybe<Long> addServer(ServiceInfo serviceInfos);

  Flowable<List<ServiceInfo>> getAllService();

  Maybe<Long> updateServer(ServiceInfo serviceInfo);

}
