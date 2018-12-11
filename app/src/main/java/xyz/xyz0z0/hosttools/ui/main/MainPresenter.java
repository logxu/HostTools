package xyz.xyz0z0.hosttools.ui.main;

import android.util.Log;
import androidx.annotation.NonNull;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import xyz.xyz0z0.hosttools.constants.NetErrorCode;
import xyz.xyz0z0.hosttools.data.DataRepository;
import xyz.xyz0z0.hosttools.data.DataSource;
import xyz.xyz0z0.hosttools.data.db.base.ServiceInfo;
import xyz.xyz0z0.hosttools.data.network.model.ServiceInfoResponse;

/**
 * Created by chengxg
 * on 2018/12/10
 */
public class MainPresenter implements MainContract.Presenter {

  @NonNull
  private final MainContract.View mMainView;
  @NonNull
  private CompositeDisposable mCompositeDisposable;
  @NonNull
  private DataSource mDataRepository;
  private List<ServiceInfo> mServiceInfos;


  public MainPresenter(@NonNull MainContract.View mainView) {
    this.mMainView = mainView;
    mCompositeDisposable = new CompositeDisposable();
    mMainView.setPresenter(this);
    mDataRepository = DataRepository.getDefault();
  }


  @Override
  public void loadDbData() {
    Disposable d = mDataRepository.getAllService()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Consumer<List<ServiceInfo>>() {
          @Override public void accept(List<ServiceInfo> serviceInfos) throws Exception {
            mServiceInfos = serviceInfos;
            mMainView.showData(serviceInfos);
            Log.d("cxgxx", "loadDbData " + serviceInfos.size());
          }
        }, new Consumer<Throwable>() {
          @Override public void accept(Throwable throwable) throws Exception {

          }
        });
    mCompositeDisposable.add(d);
  }


  @Override public void refresh() {



    for (ServiceInfo serviceInfo : mServiceInfos) {
      Disposable d = mDataRepository.getServiceInfo(serviceInfo.getVeId(), serviceInfo.getApiKey())
          .flatMap(new Function<ServiceInfoResponse, ObservableSource<Integer>>() {
            @Override public ObservableSource<Integer> apply(ServiceInfoResponse serviceInfoResponse) throws Exception {
              if (serviceInfoResponse.getError() == NetErrorCode.SUCCESS) {
                ServiceInfo info = new ServiceInfo(serviceInfo.getVeId(), serviceInfo.getApiKey(), serviceInfoResponse);
                return mDataRepository.updateServer(info).toObservable();
              } else {
                return Observable.just(0);
              }
            }
          })
          .subscribeOn(Schedulers.io())
          .observeOn(AndroidSchedulers.mainThread())
          .subscribe(new Consumer<Integer>() {
            @Override public void accept(Integer aLong) throws Exception {
              Log.d("cxgxx", "aLong " + aLong);

            }
          }, new Consumer<Throwable>() {
            @Override public void accept(Throwable throwable) throws Exception {
              throwable.printStackTrace();
            }
          });
      mCompositeDisposable.add(d);
    }
  }


  @Override public void subscribe() {
    loadDbData();
  }


  @Override public void unsubscribe() {

  }
}
