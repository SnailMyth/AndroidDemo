package com.example.testdemo.utils;

import android.graphics.Rect;
import android.view.TouchDelegate;
import android.view.View;

/**
 * @author : myth_hai
 * @date : 2020/10/6 15:28
 * @description : TouchDelegateUtil <p>
 * tips:1.注意post泄漏
 * 2.TouchDelegate 在9.0前后有变化
 * 3.多个相同
 * </p>
 */
public class TouchDelegateUtil {
  /**
   * 增加控件的可点击范围，最大范围只能是父布局所包含的的区域
   */
  public static void addDefaultScreenArea(final View view, final int top, final int bottom,
      final int left, final int right) { // 增大checkBox的可点击范围
    final View parent = (View) view.getParent();
    parent.post(new Runnable() {
      @Override
      public void run() {
        Rect bounds = new Rect();
        view.setEnabled(true);
        view.getHitRect(bounds);
        bounds.top -= top;
        bounds.bottom += bottom;
        bounds.left -= left;
        bounds.right += right;
        TouchDelegate touchDelegate = new TouchDelegate(bounds, view);
        if (view.getParent() instanceof View) {
          ((View) view.getParent()).setTouchDelegate(touchDelegate);
        }
      }
    });
  }

  /**
   * 还原View的触摸和点击响应范围,最小不小于View自身范围
   */
  public static void restoreViewTouchDelegate(final View view) {
    ((View) view.getParent()).post(new Runnable() {
      @Override
      public void run() {
        Rect bounds = new Rect();
        bounds.setEmpty();
        TouchDelegate touchDelegate = new TouchDelegate(bounds, view);
        if (view.getParent() instanceof View) {
          ((View) view.getParent()).setTouchDelegate(touchDelegate);
        }
      }
    });
  }
}