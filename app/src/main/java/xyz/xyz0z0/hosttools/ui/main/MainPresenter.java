package xyz.xyz0z0.hosttools.ui.main;

import androidx.annotation.NonNull;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import java.util.List;
import xyz.xyz0z0.hosttools.data.DataRepository;
import xyz.xyz0z0.hosttools.data.DataSource;
import xyz.xyz0z0.hosttools.data.db.base.ServiceInfo;

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
            mMainView.showData(serviceInfos);
          }
        }, new Consumer<Throwable>() {
          @Override public void accept(Throwable throwable) throws Exception {

          }
        });
    mCompositeDisposable.add(d);
  }


  @Override public void refresh() {

  }


  @Override public void subscribe() {
    loadDbData();
  }


  @Override public void unsubscribe() {

  }
}
