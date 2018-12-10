package xyz.xyz0z0.hosttools.ui.main;

import java.util.List;
import xyz.xyz0z0.hosttools.base.MvpPresenter;
import xyz.xyz0z0.hosttools.base.MvpView;
import xyz.xyz0z0.hosttools.database.ServiceInfo;

/**
 * Created by chengxg
 * on 2018/12/10
 */
public interface MainContract {

  interface View extends MvpView<Presenter> {

    void initView();

    void showData(List<ServiceInfo> data);
  }


  interface Presenter extends MvpPresenter {

    void loadDbData();

    void refresh();

  }

}
