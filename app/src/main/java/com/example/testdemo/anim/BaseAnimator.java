package com.example.testdemo.anim;

import android.view.View;

/**
 * @author : myth_hai
 * @date : 2020/9/18 17:31
 * @description : AnimatorUtil
 */
public abstract class BaseAnimator {
  private AnimatorBuilder mBuilder;

  private View[] mViews;

  private ViewAnimatorListener.startListener mStartListener;
  private ViewAnimatorListener.endListener   mEndListener;

  public BaseAnimator(View... views) {
    mViews = views;
  }

  public abstract AnimatorBuilder prepare(View... targets);

  public void start() {
    if (mViews.length >= 1) {
      mViews[0].post(new Runnable() {
        @Override
        public void run() {
          if (mBuilder == null) {
            mBuilder = prepare(mViews);
            mBuilder.setStartListener(mStartListener);
            mBuilder.setEndListener(mEndListener);
          }
          mBuilder.start();
        }
      });
    }
  }

  public void setPercent(final float percent) {
    if (mViews.length >= 1) {
      mViews[0].post(new Runnable() {
        @Override
        public void run() {
          if (mBuilder == null) {
            mBuilder = prepare(mViews);
            mBuilder.setStartListener(mStartListener);
            mBuilder.setEndListener(mEndListener);
            mBuilder.setPercent(percent);
          } else {
            mBuilder.setPercent(percent);
          }
        }
      });
    }
  }

  public void pause() {
    if (mBuilder != null) {
      mBuilder.pause();
    }
  }

  public void resume() {
    if (mBuilder != null) {
      mBuilder.resume();
    }
  }

  public void cancel() {
    if (mBuilder != null) {
      mBuilder.cancel();
    }
  }

  public void setStartListener(ViewAnimatorListener.startListener startListener) {
    mStartListener = startListener;
  }

  public void setEndListener(ViewAnimatorListener.endListener endListener) {
    mEndListener = endListener;
  }
}
