package xyz.xyz0z0.hosttools.ui.add;

import xyz.xyz0z0.hosttools.base.BasePresenter;
import xyz.xyz0z0.hosttools.base.BaseView;

/**
 * Created by chengxg
 * on 2018/11/24
 */
public interface AddContract {

  interface View extends BaseView<Presenter>{

    void openMainActivity();

    void onBackButtonClick();

    void onSubmitButtonClick();

    void showLoadingDialog();

    void showSuccessDialog();

    void showErrorDialog();

  }

  interface Presenter extends BasePresenter{

    void submit();

  }
}
