package xyz.xyz0z0.hosttools.ui.splash;

import xyz.xyz0z0.hosttools.base.MvpPresenter;
import xyz.xyz0z0.hosttools.base.MvpView;

/**
 * Created by chengxg
 * on 2018/12/4
 */
public interface SplashContract {

  interface View extends MvpView<Presenter> {
    void openMainActivity();
    void openAddServerActivity();
  }

  interface Presenter extends MvpPresenter{
    void decideNextActivity();
  }

}
