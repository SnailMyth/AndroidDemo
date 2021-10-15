package com.example.testdemo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.util.AttributeSet;
import com.example.testdemo.R;
import com.example.testdemo.utils.GetResourceUtil;
import com.example.testdemo.utils.SizeUtil;

/**
 * @author : myth_hai
 * @date : 2021/4/19 15:26
 * @description : PkHaemalStrandView
 */
public class PkHaemalStrandView extends android.view.View {
  private Paint                 mPaint;
  private android.graphics.Path mPath;
  private float                 mLeftValue = 0, mRightValue = 0;

  public PkHaemalStrandView(Context context) {
    super(context);
    init(context);
  }

  public PkHaemalStrandView(Context context, AttributeSet attrs) {
    super(context, attrs);
    init(context);
  }

  public PkHaemalStrandView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init(context);
  }

  private void init(Context context) {
    mPaint = new Paint();
    mPath = new android.graphics.Path();
    mPaint.setAntiAlias(true);
  }

  @Override
  protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    super.onMeasure(widthMeasureSpec, heightMeasureSpec);
  }

  @Override
  protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
    //        canvas.save();
    //        mPath.reset();
    //先裁剪圆角矩形
    //        mPath.addRoundRect(0, 0, getMeasuredWidth(), getMeasuredHeight(), getMeasuredHeight() / 2, getMeasuredHeight() / 2, Path.Direction.CCW);
    //        canvas.clipPath(mPath);
    //先绘制左边色条
    drawLeftHeamalStrand(canvas);
    //在绘制右边色条
    drawRightHeamalStrand(canvas);
    //绘制花纹
    drawFigure(canvas);
    //        canvas.restore();
  }

  private void drawLeftHeamalStrand(Canvas canvas) {
    if (canvas == null) {
      return;
    }
    int x = centerX();
    if (x == 0) {
      return;
    }
    LinearGradient linearGradient = new LinearGradient(0, 0,
        x, getMeasuredHeight(),
        GetResourceUtil.getColor(R.color.colorFF2589),
        GetResourceUtil.getColor(R.color.colorFF788B), android.graphics.Shader.TileMode.CLAMP);
    mPaint.setShader(linearGradient);
    canvas.drawRect(0, 0, x, getMeasuredHeight(), mPaint);
    mPaint.setShader(null);
  }

  private void drawRightHeamalStrand(Canvas canvas) {
    if (canvas == null) {
      return;
    }
    int x = centerX();
    if (x == getMeasuredWidth()) {
      return;
    }
    LinearGradient linearGradient = new LinearGradient(x, 0,
        getMeasuredWidth(), getMeasuredHeight(),
        GetResourceUtil.getColor(R.color.color71D6F8),
        GetResourceUtil.getColor(R.color.color2575FB), android.graphics.Shader.TileMode.CLAMP);
    mPaint.setShader(linearGradient);
    canvas.drawRect(x, 0, getMeasuredWidth(), getMeasuredHeight(), mPaint);
    mPaint.setShader(null);
  }

  private void drawFigure(Canvas canvas) {
    int topX = 30;
    int bottomX = 10;
    int gap = SizeUtil.dip2px(getContext(), 8);
    int width = gap;
    mPaint.setColor(android.graphics.Color.WHITE);
    mPaint.setAlpha(76);
    while (topX < getMeasuredWidth() || bottomX < getMeasuredWidth()) {
      mPath.reset();
      mPath.moveTo(topX, 0);
      mPath.lineTo(topX + width, 0);
      mPath.lineTo(bottomX + width, getMeasuredHeight());
      mPath.lineTo(bottomX, getMeasuredHeight());
      mPath.close();
      canvas.drawPath(mPath, mPaint);
      topX += gap + width;
      bottomX += gap + width;
    }
    mPaint.setAlpha(255);
  }

  //返回左右中间点的X坐标
  private int centerX() {
    int defaultLength = 50;
    if (mLeftValue == mRightValue) {
      return getMeasuredWidth() / 2;
    }
    if (mLeftValue == 0) {
      return defaultLength;
    }
    if (mRightValue == 0) {
      return getMeasuredWidth() - defaultLength;
    }
    int x = (int) ((mLeftValue / (mLeftValue + mRightValue)) * getMeasuredWidth());
    if (x <= defaultLength) {
      x = defaultLength;
    }
    if (x > getMeasuredWidth() - defaultLength) {
      x = getMeasuredWidth() - defaultLength;
    }
    return x;
  }

  public void setValue(float leftValue, float rightValue) {
    mLeftValue = leftValue;
    mRightValue = rightValue;
    invalidate();
  }

  public float getLeftValue() {
    return mLeftValue;
  }

  public float getRightValue() {
    return mRightValue;
  }

  public void setValue(String leftValue, String rightValue) {
    try {
      mLeftValue = Float.parseFloat(leftValue);
    } catch (Exception e) {
      e.printStackTrace();
    }
    try {
      mRightValue = Float.parseFloat(rightValue);
    } catch (Exception e) {
      e.printStackTrace();
    }
    invalidate();
  }
}
