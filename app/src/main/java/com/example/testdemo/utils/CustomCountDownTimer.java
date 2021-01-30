package com.example.testdemo.utils;

import android.os.CountDownTimer;

public class CustomCountDownTimer extends CountDownTimer {
  private OnCountDownListener mOnCountDownListener;

  public CustomCountDownTimer(long millisInFuture, long countDownInterval) {
    super(millisInFuture, countDownInterval);
  }

  @Override
  public void onTick(long l) {
    if (mOnCountDownListener != null) {
      mOnCountDownListener.onTick(l);
    }
  }

  @Override
  public void onFinish() {
    if (mOnCountDownListener != null) {
      mOnCountDownListener.onFinish();
    }
  }

  public void setOnCountDownListener(OnCountDownListener onCountDownListener) {
    mOnCountDownListener = onCountDownListener;
  }

  public interface OnCountDownListener {

    void onTick(long time);

    void onFinish();
  }
}
