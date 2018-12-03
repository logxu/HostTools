package xyz.xyz0z0.hosttools;

import android.app.Application;
import android.content.Context;
import xyz.xyz0z0.hosttools.data.DataManager;
import xyz.xyz0z0.hosttools.data.DefaultPrefsHelper;
import xyz.xyz0z0.hosttools.database.RoomDb;

/**
 * Created by chengxg
 * on 2018/11/23
 */
public class MvpApp extends Application {

  private static MvpApp mInstance;
  DataManager dataManager;
  RoomDb roomDb;


  public static Context getAppContext() {
    return mInstance.getApplicationContext();
  }


  public static MvpApp getAppInstance() {
    return mInstance;
  }


  @Override public void onCreate() {
    super.onCreate();
    if (mInstance == null) {
      mInstance = this;
    }
    DefaultPrefsHelper defaultPrefsHelper = new DefaultPrefsHelper(getApplicationContext());
    dataManager = new DataManager(defaultPrefsHelper);
    roomDb = RoomDb.getDatabase(this);

  }


  public DataManager getDataManager() {
    return dataManager;
  }


  public RoomDb getDb() {
    return roomDb;
  }

}
