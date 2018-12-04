package xyz.xyz0z0.hosttools.ui.add;

import xyz.xyz0z0.hosttools.base.MvpPresenter;
import xyz.xyz0z0.hosttools.base.MvpView;

/**
 * Created by chengxg
 * on 2018/11/24
 */
public interface AddContract {

  interface View extends MvpView<Presenter> {

    void openMainActivity();

    void onBackButtonClick();

    void onSubmitButtonClick();

    void exitActivity();

  }

  interface Presenter extends MvpPresenter {

    void submit(String veid,String apikey);

  }
}
