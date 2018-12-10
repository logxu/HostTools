package xyz.xyz0z0.hosttools.data.prefs;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferencesHelper implements PreferencesSource {

  public static final String DEFAULT_PREFS = "DEFAULT_PREFS";
  public static final String EMPTY_STRING = "";
  private final SharedPreferences mPrefs;
  private final String PREF_KEY_IS_SERVER_EXISTS = "is_server_exists";


  public PreferencesHelper(Context context,
                           String prefFileName) {
    mPrefs = context.getSharedPreferences(prefFileName, Context.MODE_PRIVATE);
  }


  @Override public boolean isServerExists() {
    return mPrefs.getBoolean(PREF_KEY_IS_SERVER_EXISTS, false);
  }


  @Override public void setServerExists(boolean b) {
    mPrefs.edit().putBoolean(PREF_KEY_IS_SERVER_EXISTS, b).apply();
  }
}
