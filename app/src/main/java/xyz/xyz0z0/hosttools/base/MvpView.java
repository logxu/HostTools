package xyz.xyz0z0.hosttools.base;

/**
 * Created by chengxg
 * on 2018/11/24
 */
public interface MvpView<T> {

  void setPresenter(T presenter);

  void showLoadingDialog(int resId);

  void dismissLoadingDialog();

  void showSuccessDialog(String msg);

  void showErrorDialog(String msg);

}
