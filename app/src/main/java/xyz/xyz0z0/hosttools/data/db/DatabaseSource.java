package xyz.xyz0z0.hosttools.data.db;

import io.reactivex.Maybe;
import xyz.xyz0z0.hosttools.data.db.base.ServiceInfo;

public interface DatabaseSource {

  Maybe<Long> addServer(ServiceInfo serviceInfos);

}
