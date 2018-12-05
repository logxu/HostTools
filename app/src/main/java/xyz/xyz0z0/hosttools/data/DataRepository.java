package xyz.xyz0z0.hosttools.data;

import androidx.annotation.NonNull;
import com.tencent.mmkv.MMKV;
import io.reactivex.ObservableSource;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import java.util.List;
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
  private ServiceInfoDao infoDao;
  @NonNull
  private CompositeDisposable mCompositeDisposable;
  private List<ServiceInfo> serviceInfoList;
  private MMKV mmkv;


  private DataRepository() {
    mmkv = MMKV.defaultMMKV();
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


  public ObservableSource<Long> addServer(ServiceInfo info) {
    return infoDao.insert(info).toObservable();
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

}
