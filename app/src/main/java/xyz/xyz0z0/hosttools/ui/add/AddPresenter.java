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
import xyz.xyz0z0.hosttools.net.response.ServiceInfoResponse;

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
        .map(new Function<ServiceInfoResponse, Boolean>() {
          @Override public Boolean apply(ServiceInfoResponse serviceInfoResponse) {
            if (serviceInfoResponse.getError() == NetErrorCode.SUCCESS) {
              ServiceInfo info = new ServiceInfo(Integer.parseInt(veid), apikey, serviceInfoResponse);
              long[] result = serviceInfoDao.insert(info);
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
    mCompositeDisposable.add(d);
  }


  @Override public void subscribe() {

  }


  @Override public void unsubscribe() {
    mCompositeDisposable.clear();
  }
}
