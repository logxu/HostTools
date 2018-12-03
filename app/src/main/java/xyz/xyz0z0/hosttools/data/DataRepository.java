package xyz.xyz0z0.hosttools.data;

import io.reactivex.Maybe;
import io.reactivex.disposables.CompositeDisposable;
import xyz.xyz0z0.hosttools.database.RoomDb;
import xyz.xyz0z0.hosttools.database.ServiceInfo;
import xyz.xyz0z0.hosttools.database.ServiceInfoDao;

import static xyz.xyz0z0.hosttools.MvpApp.getAppContext;

/**
 * Created by chengxg
 * on 2018/12/3
 */
public class DataRepository {

  private static volatile DataRepository INSTANCE;
  private CompositeDisposable disposables;
  private ServiceInfoDao infoDao;


  private DataRepository() {
    RoomDb db = RoomDb.getDatabase(getAppContext());
    infoDao = db.serviceInfoDao();
  }


  public static DataRepository getDefault() {
    if (INSTANCE == null) {
      synchronized (DataRepository.class) {
        if (INSTANCE == null) {
          INSTANCE = new DataRepository();
        }
      }
    }
    return INSTANCE;
  }


  public Maybe<Long> addServer(ServiceInfo info) {
    return infoDao.insert(info);
  }

}
