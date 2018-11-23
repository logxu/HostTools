package xyz.xyz0z0.hosttools.data;

/**
 * Created by chengxg
 * on 2018/11/23
 */
public class DataManager {

  DefaultPrefsHelper mDefaultPrefsHelper;


  public DataManager(DefaultPrefsHelper defaultPrefsHelper) {
    this.mDefaultPrefsHelper = defaultPrefsHelper;
  }


  public void setDataExists() {
    mDefaultPrefsHelper.setDataExists(true);
  }


  public Boolean getDataExists() {
    return mDefaultPrefsHelper.isDataExists();
  }
}
