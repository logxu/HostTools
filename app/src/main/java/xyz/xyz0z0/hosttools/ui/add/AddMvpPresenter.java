package xyz.xyz0z0.hosttools.ui.add;

import xyz.xyz0z0.hosttools.ui.base.MvpPresenter;

/**
 * Created by chengxg
 * on 2018/11/24
 */
public interface AddMvpPresenter<V extends AddMvpView> extends MvpPresenter<V> {

  void btnBackListener();

  void btnSubmitListener();

}
