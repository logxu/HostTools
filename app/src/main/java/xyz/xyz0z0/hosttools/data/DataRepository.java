package xyz.xyz0z0.hosttools.data;

import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import java.util.List;
import xyz.xyz0z0.hosttools.data.db.DatabaseHelper;
import xyz.xyz0z0.hosttools.data.db.DatabaseSource;
import xyz.xyz0z0.hosttools.data.db.base.RoomDb;
import xyz.xyz0z0.hosttools.data.db.base.ServiceInfo;
import xyz.xyz0z0.hosttools.data.network.ApiHelper;
import xyz.xyz0z0.hosttools.data.network.ApiHelperSource;
import xyz.xyz0z0.hosttools.data.network.model.ServiceInfoResponse;
import xyz.xyz0z0.hosttools.data.prefs.PreferencesHelper;
import xyz.xyz0z0.hosttools.data.prefs.PreferencesSource;

import static xyz.xyz0z0.hosttools.MvpApp.getAppContext;
import static xyz.xyz0z0.hosttools.data.prefs.PreferencesHelper.DEFAULT_PREFS;

/**
 * Created by chengxg
 * on 2018/12/3
 */
public class DataRepository implements DataSource {

  private static volatile DataRepository INSTANCE;
  private final ApiHelperSource mApiHelper;
  private final PreferencesSource mPreferencesHelper;
  private final DatabaseSource mDatabaseHelper;


  private DataRepository() {
    mApiHelper = new ApiHelper();
    mPreferencesHelper = new PreferencesHelper(getAppContext(), DEFAULT_PREFS);
    mDatabaseHelper = new DatabaseHelper(RoomDb.getDatabase(getAppContext()));
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



  @Override public Observable<ServiceInfoResponse> getServiceInfo(String id, String key) {
    return mApiHelper.getServiceInfo(id, key);
  }


  @Override public boolean isServerExists() {
    return mPreferencesHelper.isServerExists();
  }


  @Override public void setServerExists(boolean b) {
    mPreferencesHelper.setServerExists(b);
  }


  @Override public Maybe<Long> addServer(ServiceInfo serviceInfos) {
    return mDatabaseHelper.addServer(serviceInfos);
  }


  @Override public Flowable<List<ServiceInfo>> getAllService() {
    return mDatabaseHelper.getAllService();
  }
}
