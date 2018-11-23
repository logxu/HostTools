package xyz.xyz0z0.hosttools.ui.splash;

import xyz.xyz0z0.hosttools.ui.base.MvpPresenter;

/**
 * Created by chengxg
 * on 2018/11/23
 */
public interface SplashMvpPresenter<V extends SplashMvpView> extends MvpPresenter<V> {
  void decideNextActivity();
}
