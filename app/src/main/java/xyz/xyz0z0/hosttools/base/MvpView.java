package xyz.xyz0z0.hosttools.base;

import android.content.DialogInterface;

/**
 * Created by chengxg
 * on 2018/11/24
 */
public interface MvpView<T> {

  void setPresenter(T presenter);

  void showLoadingDialog(int resId);

  void dismissLoadingDialog();

  void showToast(int resId);

  void showErrorDialog(int resId,DialogInterface.OnClickListener listener);



}
