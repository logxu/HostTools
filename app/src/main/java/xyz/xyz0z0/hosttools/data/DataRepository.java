package xyz.xyz0z0.hosttools.data;

import androidx.annotation.NonNull;
import com.tencent.mmkv.MMKV;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import java.util.List;
import xyz.xyz0z0.hosttools.data.db.DatabaseHelper;
import xyz.xyz0z0.hosttools.data.db.DatabaseSource;
import xyz.xyz0z0.hosttools.data.db.base.RoomDb;
import xyz.xyz0z0.hosttools.data.db.base.ServiceInfo;
import xyz.xyz0z0.hosttools.data.db.base.ServiceInfoDao;
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
  private ServiceInfoDao infoDao;
  @NonNull
  private CompositeDisposable mCompositeDisposable;
  private List<ServiceInfo> serviceInfoList;
  private MMKV mmkv;


  private DataRepository() {
    mmkv = MMKV.defaultMMKV();
    mApiHelper = new ApiHelper();
    mPreferencesHelper = new PreferencesHelper(getAppContext(), DEFAULT_PREFS);
    mDatabaseHelper = new DatabaseHelper(RoomDb.getDatabase(getAppContext()));
    RoomDb db = RoomDb.getDatabase(getAppContext());
    infoDao = db.serviceInfoDao();
    mCompositeDisposable = new CompositeDisposable();
    queryAllServer();
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





  public boolean getServerExists() {
    return mmkv.getBoolean("is_server_exist", false);
  }


  public List<ServiceInfo> getServiceInfoList() {
    return serviceInfoList;
  }


  public void queryAllServer() {
    Disposable d = infoDao.queryAllInfo()
        .subscribeOn(Schedulers.io())
        .subscribe(new Consumer<List<ServiceInfo>>() {
          @Override public void accept(List<ServiceInfo> serviceInfos) throws Exception {
            serviceInfoList = serviceInfos;
            if (serviceInfoList.size() > 0) {
              mmkv.putBoolean("is_server_exist", true);
            }
          }
        }, new Consumer<Throwable>() {
          @Override public void accept(Throwable throwable) throws Exception {
            throwable.printStackTrace();
          }
        });
    mCompositeDisposable.add(d);
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
}
