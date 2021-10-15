package com.example.testdemo.utils;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import com.example.testdemo.AppContext;

/**
 * Created by chengjuechao on 2019/1/7.
 */
public class GetResourceUtil {

  public static int getColor(int resId) {
    return AppContext.Context.getResources().getColor(resId);
  }

  public static int getColor(Context context, int resId) {
    if (context == null) {
      return getColor(resId);
    }
    return context.getResources().getColor(resId);
  }

  public static ColorStateList getColorStateList(int resId) {
    return AppContext.Context.getResources().getColorStateList(resId);
  }

  public static ColorStateList getColorStateList(Context context, int resId) {
    if (context == null) {
      return getColorStateList(resId);
    }
    return context.getResources().getColorStateList(resId);
  }

  public static String getString(int resId) {
    return AppContext.Context.getResources().getString(resId);
  }

  public static String getString(int resId, Object... formatArgs) {
    return AppContext.Context.getResources().getString(resId, formatArgs);
  }

  public static String[] getStringArray(int resId) {
    return AppContext.Context.getResources().getStringArray(resId);
  }

  public static String getString(Context context, int resId) {
    if (context == null) {
      return getString(resId);
    }
    return context.getString(resId);
  }

  public static String getString(Context context, int resId, Object... formatArgs) {
    if (context == null) {
      return getString(resId, formatArgs);
    }
    return context.getString(resId, formatArgs);
  }

  public static Drawable getDrawable(int resId) {
    return AppContext.Context.getResources().getDrawable(resId);
  }

  public static Drawable getDrawable(Context context, int resId) {
    if (context == null) {
      return getDrawable(resId);
    }
    return context.getResources().getDrawable(resId);
  }

  public static float getDimension(int resId) {
    return AppContext.Context.getResources().getDimension(resId);
  }

  public static float getDimension(Context context,int resId) {
    if (context == null) {
      return getDimension(resId);
    }
    return context.getResources().getDimension(resId);
  }

  public static int getDimensionPixelSize(int resId) {
    return AppContext.Context.getResources().getDimensionPixelSize(resId);
  }

  public static int getDimensionPixelSize(Context context, int resId) {
    if (context == null) {
      return getDimensionPixelSize(resId);
    }
    return context.getResources().getDimensionPixelSize(resId);
  }

  public static int getDimensionPixelOffset(int resId) {
    return AppContext.Context.getResources().getDimensionPixelOffset(resId);
  }

  public static int getDimensionPixelOffset(Context context, int resId) {
    if (context == null) {
      return getDimensionPixelOffset(resId);
    }
    return context.getResources().getDimensionPixelOffset(resId);
  }

  public static AssetFileDescriptor openRawResourceFd(Context context, int resId) {
    if (context == null) {
      return AppContext.Context.getResources().openRawResourceFd(resId);
    }
    return context.getResources().openRawResourceFd(resId);
  }

  public static Resources getResources() {
    return AppContext.Context.getResources();
  }

  public static Resources getResources(Context context) {
    if (context == null) {
      return getResources();
    }
    return context.getResources();
  }

  public static int getResourcesId(int resId) {
    return resId;
  }

  //获取随机色块资源
  public static int getRandomColorLump(Context context) {
    if (context == null) {
      context = AppContext.Context;
    }
    int i = (int) (Math.random() * 7) + 1;//1-7
    return context.getResources()
        .getIdentifier("bg_color_lump" + i, "drawable", context.getPackageName());
  }
}
