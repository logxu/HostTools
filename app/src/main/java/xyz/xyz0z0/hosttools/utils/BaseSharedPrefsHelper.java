/*
 *    Copyright (C) 2017 MINDORKS NEXTGEN PRIVATE LIMITED
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package xyz.xyz0z0.hosttools.utils;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

public class BaseSharedPrefsHelper {

  public static final String DEFAULT_PREFS = "DEFAULT_PREFS";
  public static final String EMPTY_STRING = "";

  private SharedPreferences mSharedPreferences;


  public BaseSharedPrefsHelper(Context context) {
    mSharedPreferences = context.getSharedPreferences(DEFAULT_PREFS, MODE_PRIVATE);
  }


  public BaseSharedPrefsHelper(Context context, String name) {
    mSharedPreferences = context.getSharedPreferences(name, MODE_PRIVATE);
  }


  public void putInt(String key, int value) {
    mSharedPreferences.edit().putInt(key, value).apply();
  }


  public int getInt(String key) {
    return mSharedPreferences.getInt(key, 0);
  }


  public void putBoolean(String key, boolean value) {
    mSharedPreferences.edit().putBoolean(key, value).apply();
  }


  public boolean getBoolean(String key) {
    return mSharedPreferences.getBoolean(key, false);
  }


  public void putString(String key, String value) {
    mSharedPreferences.edit().putString(key, value).apply();
  }


  public String getString(String key) {
    return mSharedPreferences.getString(key, EMPTY_STRING);
  }


  public String getString(String key, String defValue) {
    return mSharedPreferences.getString(key, defValue);
  }


  public void clear() {
    mSharedPreferences.edit().clear().apply();
  }

}
