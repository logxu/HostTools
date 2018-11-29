package xyz.xyz0z0.hosttools.ui.add;

import androidx.annotation.NonNull;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import xyz.xyz0z0.hosttools.MvpApp;
import xyz.xyz0z0.hosttools.constants.NetErrorCode;
import xyz.xyz0z0.hosttools.database.ServiceInfo;
import xyz.xyz0z0.hosttools.database.ServiceInfoDao;
import xyz.xyz0z0.hosttools.net.Network;

/**
 * Created by chengxg
 * on 2018/11/24
 */
public class AddPresenter implements AddContract.Presenter {

  @NonNull
  private final AddContract.View mAddServerView;

  @NonNull
  private CompositeDisposable mCompositeDisposable;

  private ServiceInfoDao serviceInfoDao;


  public AddPresenter(@NonNull AddContract.View addServerView) {
    this.mAddServerView = addServerView;
    mCompositeDisposable = new CompositeDisposable();
    serviceInfoDao = ((MvpApp) (((AddServerActivity) mAddServerView).getApplication())).getDb().serviceInfoDao();
    mAddServerView.setPresenter(this);

  }


  @Override public void submit(String veid, String apikey) {
    Disposable d = Network.getApiService()
        .getServiceInfo(veid, apikey)
        .map(new Function<ServiceInfo, Boolean>() {
          @Override public Boolean apply(ServiceInfo serviceInfo) {
            if (serviceInfo.getError() == NetErrorCode.SUCCESS) {
              serviceInfo.setVeId(Integer.parseInt(veid));
              // KeyInfo keyInfo = new KeyInfo(Integer.parseInt(veid), apikey);
              long[] result = serviceInfoDao.insert(serviceInfo);
              return result.length == 1;
            } else {
              return false;
            }
          }
        })
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Consumer<Boolean>() {
          @Override public void accept(Boolean aBoolean) throws Exception {
            if (aBoolean) {
              mAddServerView.showSuccessDialog();
            } else {
              mAddServerView.showErrorDialog();
            }
          }
        }, new Consumer<Throwable>() {
          @Override public void accept(Throwable throwable) throws Exception {
            throwable.printStackTrace();
            mAddServerView.showErrorDialog();
          }
        });
    // .subscribe(new Consumer<ServiceInfo>() {
    //   @Override public void accept(ServiceInfo serviceInfo) throws Exception {
    //     if (serviceInfo.getError() == NetErrorCode.SUCCESS) {
    //       KeyInfo keyInfo = new KeyInfo(Integer.parseInt(veid), apikey);
    //       keyInfoDao.insert(keyInfo);
    //     } else {
    //       mAddServerView.showErrorDialog();
    //     }
    //     Log.d("cxg", "serviceInfo " + serviceInfo.getEmail());
    //     Log.d("cxg", "serviceInfo " + serviceInfo.getError());
    //   }
    // }, new Consumer<Throwable>() {
    //   @Override public void accept(Throwable throwable) throws Exception {
    //     throwable.printStackTrace();
    //     mAddServerView.showErrorDialog();
    //   }
    // });
    mCompositeDisposable.add(d);
  }


  @Override public void subscribe() {

  }


  @Override public void unsubscribe() {
    mCompositeDisposable.clear();
  }
}
