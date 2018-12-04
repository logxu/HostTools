package xyz.xyz0z0.hosttools.ui.add;

import android.database.sqlite.SQLiteConstraintException;
import androidx.annotation.NonNull;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import xyz.xyz0z0.hosttools.R;
import xyz.xyz0z0.hosttools.constants.NetErrorCode;
import xyz.xyz0z0.hosttools.data.DataRepository;
import xyz.xyz0z0.hosttools.database.ServiceInfo;
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

  private DataRepository mDataRepository;


  public AddPresenter(@NonNull AddContract.View addServerView) {
    this.mAddServerView = addServerView;
    mCompositeDisposable = new CompositeDisposable();
    // serviceInfoDao = ((MvpApp) (((AddServerActivity) mAddServerView).getApplication())).getDb().serviceInfoDao();
    mAddServerView.setPresenter(this);
    mDataRepository = DataRepository.getDefault();
  }


  @Override public void submit(String veid, String apikey) {
    mAddServerView.showLoadingDialog(R.string.base_loading_text);
    Disposable d = Network.getApiService()
        .getServiceInfo(veid, apikey)
        .flatMap(new Function<ServiceInfoResponse, ObservableSource<Long>>() {
          @Override public ObservableSource<Long> apply(ServiceInfoResponse response) throws Exception {
            if (response.getError() == NetErrorCode.SUCCESS) {
              ServiceInfo info = new ServiceInfo(Integer.parseInt(veid), apikey, response);
              return mDataRepository.addServer(info);
            } else {
              return Observable.just(0L);
            }
          }
        })
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Consumer<Long>() {
          @Override public void accept(Long aLong) throws Exception {
            mAddServerView.dismissLoadingDialog();
            if (aLong > 0) {
              mAddServerView.showToast(R.string.add_server_success);
              mAddServerView.exitActivity();
            } else {
              mAddServerView.showToast(R.string.add_server_fail);
            }
          }
        }, new Consumer<Throwable>() {
          @Override public void accept(Throwable throwable) throws Exception {
            mAddServerView.dismissLoadingDialog();
            if (throwable instanceof SQLiteConstraintException) {
              mAddServerView.showErrorDialog(R.string.add_server_repeat_fail, null);
            } else {
              mAddServerView.showErrorDialog(R.string.add_server_fail, null);
            }
            throwable.printStackTrace();
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
