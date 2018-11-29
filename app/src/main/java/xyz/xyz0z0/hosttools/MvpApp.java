package xyz.xyz0z0.hosttools;

import android.app.Application;
import xyz.xyz0z0.hosttools.data.DataManager;
import xyz.xyz0z0.hosttools.data.DefaultPrefsHelper;
import xyz.xyz0z0.hosttools.database.RoomDb;

/**
 * Created by chengxg
 * on 2018/11/23
 */
public class MvpApp extends Application {

  DataManager dataManager;
  RoomDb roomDb;


  @Override public void onCreate() {
    super.onCreate();

    DefaultPrefsHelper defaultPrefsHelper = new DefaultPrefsHelper(getApplicationContext());
    dataManager = new DataManager(defaultPrefsHelper);
    roomDb = RoomDb.getDatabase(this);

  }


  public DataManager getDataManager() {
    return dataManager;
  }

  public RoomDb getDb(){
    return roomDb;
  }

}
