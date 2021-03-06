package com.example.testdemo.utils;

import android.animation.ValueAnimator;
import androidx.annotation.NonNull;
import java.lang.reflect.Field;

public class ValueAnimatorUtil {


  //低端机  动画时长缩放 被关闭
  /**
   * 重置动画缩放时长
   */
  public static void resetDurationScale() {
    try {
      getField().setFloat(null, 1);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private static float getDurationScale() {
    try {
      return getField().getFloat(null);
    } catch (Exception e) {
      e.printStackTrace();
      return -1;
    }
  }

  @NonNull
  private static Field getField() throws NoSuchFieldException {
    @SuppressWarnings("JavaReflectionMemberAccess")
    Field field = ValueAnimator.class.getDeclaredField("sDurationScale");
    field.setAccessible(true);
    return field;
  }
}
