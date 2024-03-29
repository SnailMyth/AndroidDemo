/*
 * Copyright 2017 JessYan
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.testdemo.utils;

import android.text.TextUtils;
import android.util.Log;

/**
 * 日志工具类
 * Created by JessYan on 2015/11/23.
 */
public class LogUtil {

  private LogUtil() {
    throw new IllegalStateException("you can't instantiate me!");
  }

  public static final String  DEFAULT_TAG = "FanJiaoLive";

  public static void debugInfo(String tag, String msg) {
    Log.d(tag, msg);
  }

  public static void debugInfo(String msg) {
    debugInfo(DEFAULT_TAG, msg);
  }

  public static void warnInfo(String tag, String msg) {
    Log.w(tag, msg);
  }

  public static void warnInfo(String msg) {
    warnInfo(DEFAULT_TAG, msg);
  }

  public static void errorInfo(String tag, String msg) {
    Log.e(tag, msg);
  }

  public static void errorInfo(String msg) {
    errorInfo(DEFAULT_TAG, msg);
  }

  /**
   * 所以这里使用自己分节的方式来输出足够长度的message
   *
   * @param str void
   */
  public static void debugLongInfo(String tag, String str) {
    str = str.trim();
    int index = 0;
    int maxLength = 3500;
    String sub;
    while (index < str.length()) {
      // java的字符不允许指定超过总的长度end
      if (str.length() <= index + maxLength) {
        sub = str.substring(index);
      } else {
        sub = str.substring(index, index + maxLength);
      }

      index += maxLength;
      Log.d(tag, sub.trim());
    }
  }

  public static void debugLongInfo(String str) {
    debugLongInfo(DEFAULT_TAG, str);
  }
}
