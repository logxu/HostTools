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
import xyz.xyz0z0.hosttools.data.db.base.ServiceInfo;
import xyz.xyz0z0.hosttools.data.network.model.ServiceInfoResponse;

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
    mAddServerView.setPresenter(this);
    mDataRepository = DataRepository.getDefault();
  }


  @Override public void submit(int id, String key) {
    mAddServerView.showLoadingDialog(R.string.base_loading_text);
    Disposable d = mDataRepository.getServiceInfo(id, key)
        .flatMap(new Function<ServiceInfoResponse, ObservableSource<Long>>() {
          @Override public ObservableSource<Long> apply(ServiceInfoResponse serviceInfoResponse) throws Exception {
            if (serviceInfoResponse.getError() == NetErrorCode.SUCCESS) {
              ServiceInfo info = new ServiceInfo(id, key, serviceInfoResponse);
              return mDataRepository.addServer(info).toObservable();
            } else {
              return Observable.just(0L);
            }
          }
        })
        .map(new Function<Long, Boolean>() {
          @Override public Boolean apply(Long aLong) {
            if (aLong > 0) {
              mDataRepository.setServerExists(true);
              return true;
            }
            return false;
          }
        })
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Consumer<Boolean>() {
          @Override public void accept(Boolean b) throws Exception {
            mAddServerView.dismissLoadingDialog();
            if (b) {
              mAddServerView.showToast(R.string.add_server_success);
              mAddServerView.openMainActivity();
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
              mAddServerView.showErrorDialog(R.string.add_server_error, null);
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
