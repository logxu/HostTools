package xyz.xyz0z0.hosttools.ui.splash;

import xyz.xyz0z0.hosttools.data.DataManager;
import xyz.xyz0z0.hosttools.ui.base.BasePresenter;

/**
 * Created by chengxg
 * on 2018/11/23
 */
public class SplashPresenter<V extends SplashMvpView> extends BasePresenter<V> implements SplashMvpPresenter<V> {

  public SplashPresenter(DataManager dataManager) {
    super(dataManager);
  }


  @Override public void decideNextActivity() {
    if (getDataManager().getDataExists()) {
      getMvpView().openMainActivity();
    } else {
      getMvpView().openAddServerActivity();
    }


  }
}
