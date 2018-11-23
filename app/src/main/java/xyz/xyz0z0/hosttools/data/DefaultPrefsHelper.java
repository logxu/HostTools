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

package xyz.xyz0z0.hosttools.data;

import android.content.Context;
import xyz.xyz0z0.hosttools.utils.BaseSharedPrefsHelper;

public class DefaultPrefsHelper {

  public static final String EMPTY_STRING = "";

  private BaseSharedPrefsHelper helper;


  public DefaultPrefsHelper(Context context) {
    helper = new BaseSharedPrefsHelper(context);
  }


  public boolean isDataExists() {
    return helper.getBoolean("IS_DATA_EXISTS");
  }


  public void setDataExists(boolean value) {
    helper.putBoolean("IS_DATA_EXISTS", value);
  }
}