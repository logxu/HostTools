package xyz.xyz0z0.hosttools.ui.base;

/**
 * Created by chengxg
 * on 2018/11/23
 */
public interface MvpPresenter<V extends MvpView> {
  void onAttach(V mvpView);
}
