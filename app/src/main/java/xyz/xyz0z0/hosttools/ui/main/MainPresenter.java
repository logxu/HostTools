package xyz.xyz0z0.hosttools.ui.main;

import androidx.annotation.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import java.util.List;
import xyz.xyz0z0.hosttools.data.DataRepository;
import xyz.xyz0z0.hosttools.database.ServiceInfo;

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
  private DataRepository mDataRepository;


  public MainPresenter(@NonNull MainContract.View mainView) {
    this.mMainView = mainView;
    mCompositeDisposable = new CompositeDisposable();
    mMainView.setPresenter(this);
    mDataRepository = DataRepository.getDefault();
  }


  @Override
  public void loadDbData() {
    List<ServiceInfo> data = mDataRepository.getServiceInfoList();
    mMainView.showData(data);
  }


  @Override public void refresh() {

  }


  @Override public void subscribe() {
    loadDbData();
  }


  @Override public void unsubscribe() {

  }
}
