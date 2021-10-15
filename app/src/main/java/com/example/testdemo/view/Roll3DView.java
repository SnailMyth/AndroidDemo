package com.example.testdemo.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.example.testdemo.R;
import com.example.testdemo.utils.GetResourceUtil;

public class Roll3DView extends FrameLayout {

  private ValueAnimator animator;
  private float         degree;
  private int           width;
  private int           height;
  private Rect          mRect;

  public Roll3DView(Context context) {
    this(context, null);
    init();
  }

  private void init() {
    mRect = new Rect();
  }

  public Roll3DView(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
    init();
  }

  @Override
  protected void onSizeChanged(int w, int h, int oldw, int oldh) {
    super.onSizeChanged(w, h, oldw, oldh);
    width = w;
    height = h;
  }

  @Override
  protected void dispatchDraw(Canvas canvas) {
    if (width > 0) {
      canvas.clipRect(0, 0, width, height * (degree / 10));
    }
    super.dispatchDraw(canvas);
  }

  public void startAnimation() {
    animator = ValueAnimator.ofFloat(0, 10);
    animator.addUpdateListener(valueAnimator -> {
      degree = (float) valueAnimator.getAnimatedValue();
      invalidate();
    });
    animator.setDuration(1000);
    animator.start();
  }
}
