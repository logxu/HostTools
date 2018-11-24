package xyz.xyz0z0.hosttools.ui.add;

import xyz.xyz0z0.hosttools.ui.base.MvpView;

/**
 * Created by chengxg
 * on 2018/11/24
 */
public interface AddMvpView extends MvpView {

  void openMainActivity();

  void onBackButtonClick();

  void onSubmitButtonClick();

  void showLoadingDialog();

  void showSuccessDialog();

  void showErrorDialog();

}
