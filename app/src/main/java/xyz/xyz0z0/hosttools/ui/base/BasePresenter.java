package xyz.xyz0z0.hosttools.ui.base;

import xyz.xyz0z0.hosttools.data.DataManager;

/**
 * Created by chengxg
 * on 2018/11/23
 */
public class BasePresenter<V extends MvpView> implements MvpPresenter<V> {

  DataManager mDataManager;
  private V mMvpView;


  public BasePresenter(DataManager dataManager) {
    this.mDataManager = dataManager;
  }


  @Override public void onAttach(V mvpView) {
    mMvpView = mvpView;
  }


  public V getMvpView() {
    return mMvpView;
  }


  public DataManager getDataManager() {
    return mDataManager;
  }
}
