package com.example.testdemo.anim;

import android.view.View;

public class AnimatorUtil {

  public static ScaleAnimator getScaleAnimator(float start, float end, View... views) {
    return new ScaleAnimator(start, end, views);
  }
}
