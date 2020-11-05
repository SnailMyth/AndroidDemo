package com.example.testdemo.utils;

import android.graphics.Rect;
import android.os.Build;
import android.view.MotionEvent;
import android.view.TouchDelegate;
import android.view.View;
import androidx.annotation.NonNull;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : myth_hai
 * @date : 2020/10/6 15:40
 * @description : TouchDelegateComposite  多个相同view点击组合
 */
public class TouchDelegateComposite extends TouchDelegate {
  private static final Rect                USELESS_RECT = new Rect();
  private final        List<TouchDelegate> mDelegates;

  public TouchDelegateComposite(@NonNull View view, int viewNum) {
    super(USELESS_RECT, view);
    mDelegates = new ArrayList<TouchDelegate>(viewNum);
  }

  public void addDelegate(@NonNull TouchDelegate delegate) {
    mDelegates.add(delegate);
  }

  @Override
  public boolean onTouchEvent(MotionEvent event) {
    if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
      final float x = event.getX();
      final float y = event.getY();
      for (TouchDelegate delegate : mDelegates) {
        event.setLocation(x, y);
        if (delegate.onTouchEvent(event)) {
          return true;
        }
      }
      return false;
    } else {
      boolean res = false;
      float x = event.getX();
      float y = event.getY();
      for (TouchDelegate delegate : mDelegates) {
        event.setLocation(x, y);
        res = delegate.onTouchEvent(event) || res;
      }
      return res;
    }
  }
}