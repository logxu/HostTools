package xyz.xyz0z0.hosttools;

import android.app.Application;
import xyz.xyz0z0.hosttools.data.DataManager;
import xyz.xyz0z0.hosttools.data.DefaultPrefsHelper;

/**
 * Created by chengxg
 * on 2018/11/23
 */
public class MvpApp extends Application {

  DataManager dataManager;


  @Override public void onCreate() {
    super.onCreate();

    DefaultPrefsHelper defaultPrefsHelper = new DefaultPrefsHelper(getApplicationContext());
    dataManager = new DataManager(defaultPrefsHelper);
  }


  public DataManager getDataManager() {
    return dataManager;
  }

}
