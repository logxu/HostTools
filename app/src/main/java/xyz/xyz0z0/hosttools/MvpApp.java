package xyz.xyz0z0.hosttools;

import android.app.Application;
import android.content.Context;
import com.tencent.mmkv.MMKV;
import xyz.xyz0z0.hosttools.data.DataRepository;

/**
 * Created by chengxg
 * on 2018/11/23
 */
public class MvpApp extends Application {

  private static MvpApp mInstance;


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
    MMKV.initialize(this);

  }

}
