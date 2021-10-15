package com.example.testdemo.utils;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;

public class SizeUtil {
  /**
   * dpתpx
   */
  public static int dip2px(Context context,float dpValue) {
    final float scale = context.getResources().getDisplayMetrics().density;
    return (int) (dpValue * scale + 0.5f);
  }

  public static float dip2pxF(Context context,float dpValue) {
    final float scale = context.getResources().getDisplayMetrics().density;
    return dpValue * scale + 0.5f;
  }

  /**
   * pxתdp
   */
  public static int px2dip(Context ctx, float pxValue) {
    final float scale = ctx.getResources().getDisplayMetrics().density;
    return (int) (pxValue / scale + 0.5f);
  }

  //屏幕宽高
  public static int getScreenHeight(Activity activity) {
    DisplayMetrics dm = new DisplayMetrics();
    activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
    return dm.heightPixels;
  }

  //屏幕高+状态栏高度
  public static int getScreenHeightAndBar(Activity activity) {
    DisplayMetrics dm = new DisplayMetrics();
    activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
    return dm.heightPixels + getStatusBarHeight(activity);
  }



  //屏幕宽高
  public static int getScreenWidth(Activity activity) {
    DisplayMetrics dm = new DisplayMetrics();
    activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
    return dm.widthPixels;
  }


  /**
   * 利用反射获取状态栏高度 * @return
   */

  public static int getStatusBarHeight(Activity activity) {
    int result = 0;
    //获取状态栏高度的资源
    int resourceId = activity.getResources().getIdentifier("status_bar_height", "dimen", "android");
    if (resourceId > 0) {
      result = activity.getResources().getDimensionPixelSize(resourceId);
    }
    return result;
  }

  /**
   * 利用反射获取状态栏高度 * @return
   */


  /**
   * 将sp值转换为px值，保证文字大小不变
   */
  public static int sp2px(Context context,float spValue) {
    final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
    return (int) (spValue * fontScale + 0.5f);
  }

  /**
   * 将sp值转换为px值，保证文字大小不变
   */
  public static float sp2pxF(Context context,float spValue) {
    final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
    return spValue * fontScale + 0.5f;
  }

  // View宽，高
  public static int[] getLocation(View v) {
    int[] loc = new int[4];
    int[] location = new int[2];
    v.getLocationOnScreen(location);
    loc[0] = location[0];
    loc[1] = location[1];
    int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
    int h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
    v.measure(w, h);

    loc[2] = v.getMeasuredWidth();
    loc[3] = v.getMeasuredHeight();

    //base = computeWH();
    return loc;
  }
}
