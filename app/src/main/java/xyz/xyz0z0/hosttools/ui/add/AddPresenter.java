package xyz.xyz0z0.hosttools.ui.add;

import android.util.Log;
import android.widget.Toast;
import androidx.annotation.NonNull;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import xyz.xyz0z0.hosttools.constants.NetErrorCode;
import xyz.xyz0z0.hosttools.model.ServiceInfo;
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


  public AddPresenter(@NonNull AddContract.View addServerView) {
    this.mAddServerView = addServerView;
    mCompositeDisposable = new CompositeDisposable();
    mAddServerView.setPresenter(this);

  }


  @Override public void submit(String veid, String apikey) {
    Disposable d = Network.getApiService()
        .getServiceInfo(veid, apikey)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Consumer<ServiceInfo>() {
          @Override public void accept(ServiceInfo serviceInfo) throws Exception {
            if (serviceInfo.getError() == NetErrorCode.SUCCESS) {

            } else {
              mAddServerView.showErrorDialog();
            }
            Log.d("cxg", "serviceInfo " + serviceInfo.getEmail());
            Log.d("cxg", "serviceInfo " + serviceInfo.getError());
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
