package com.example.testdemo.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;
import com.example.testdemo.R;
import org.jetbrains.annotations.Nullable;

/**
 * Created by chengjuechao on 2018/6/8.
 */
public class DoubleProgressBar extends View {
  private final int   MAX_PRO = 10000;
  private       Paint mPaint;
  private       Path  mPath;
  private       RectF mRectF;
  private       int   mWidth, mHavePro, mFreezePro;
  private LinearGradient mLinearGradient;
  private float          mProWidth;
  private boolean        mIsAnimation;//是否启用动画
  private ValueAnimator  mAnimator;

  public DoubleProgressBar(Context context) {
    this(context, null);
  }

  public DoubleProgressBar(Context context, @Nullable AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public DoubleProgressBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init(context, attrs);
  }

  private void init(Context context, @Nullable AttributeSet attrs) {
    mPaint = new Paint();
    mPath = new Path();
    mRectF = new RectF();
    mPaint.setAntiAlias(true);
    TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.DoubleProgressBar);
    mHavePro = array.getInteger(R.styleable.DoubleProgressBar_haveProgress, 0);
    mFreezePro = array.getInteger(R.styleable.DoubleProgressBar_freezeProgress, 0);
    array.recycle();
  }

  @Override
  protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    super.onMeasure(widthMeasureSpec,
        MeasureSpec.makeMeasureSpec((int) dip2px(41), MeasureSpec.EXACTLY));
  }

  @Override
  protected void onSizeChanged(int w, int h, int oldw, int oldh) {
    super.onSizeChanged(w, h, oldw, oldh);
    mWidth = w;
    mLinearGradient = new LinearGradient(dip2px(15), dip2px(26),
        mWidth - dip2px(15), dip2px(26),
        getResources().getColor(R.color.colorFF9A44), getResources().getColor(R.color.colorFC6076),
        Shader.TileMode.CLAMP);
    mProWidth = mWidth - dip2px(30);//进度条总长度
  }

  @Override
  protected void onDraw(Canvas canvas) {
    mPaint.setStyle(Paint.Style.FILL_AND_STROKE);

    //绘制灰色背景
    canvas.drawColor(getResources().getColor(R.color.colorF2));

    //绘制顶部白色部分
    mPaint.setColor(getResources().getColor(R.color.white));
    canvas.drawRect(0, 0, mWidth, dip2px(11), mPaint);

    //绘制三角形
    mPaint.setColor(getResources().getColor(R.color.colorF2));
    mPath.moveTo(mWidth - dip2px(17), dip2px(11));
    mPath.lineTo(mWidth - dip2px(29), dip2px(11));
    mPath.lineTo(mWidth - dip2px(23), 0);
    mPath.close();
    canvas.drawPath(mPath, mPaint);

    //裁剪进度条画布 并填充白色
    mPath.reset();
    mRectF.set(dip2px(15), dip2px(20), mWidth - dip2px(15), dip2px(32));
    mPath.addRoundRect(mRectF, dip2px(6), dip2px(6), Path.Direction.CW);
    canvas.clipPath(mPath);
    canvas.drawColor(Color.WHITE);
    canvas.save();

    //绘制进度条边框
    mPaint.setColor(getResources().getColor(R.color.colorE5));
    mPaint.setStyle(Paint.Style.STROKE);
    mPaint.setStrokeWidth(dip2px(1));
    canvas.drawPath(mPath, mPaint);

    //绘制冻结进度条
    mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
    mPaint.setColor(getResources().getColor(R.color.colorE5));
    drawFreezePro(mFreezePro, canvas);

    //绘制已完成进度条
    mPaint.setShader(mLinearGradient);
    drawHavePro(mHavePro, canvas);
    mPaint.setShader(null);//不设置为null  会全部渐变

    //绘制文字部分
    mPaint.setShader(null);//不设置为null  会全部渐变
    int p = (int) (((float) mHavePro / MAX_PRO) * 1000);//计算百分比 保留一位小数
    float num = (float) p / 10;
    if (num == 100f) {
      mPaint.setColor(Color.WHITE);
    } else {
      mPaint.setColor(getResources().getColor(R.color.color9));
    }
    mPaint.setStrokeWidth(1);
    mPaint.setTextSize(dip2px(11));
    Paint.FontMetricsInt fontMetrics = mPaint.getFontMetricsInt();
    float textWidth = mPaint.measureText(num + "%");//拿到文字宽度
    float textHeight = fontMetrics.leading + fontMetrics.ascent + fontMetrics.descent;//文字的高度
    canvas.drawText(num + "%", mWidth - dip2px(20) - textWidth, dip2px(26) - textHeight / 2,
        mPaint);
  }

  //进度冻结进度
  private void drawFreezePro(int progress, Canvas canvas) {
    if (mPath == null || progress == 0) {
      return;
    }
    mPath.reset();
    float percent = (float) progress / MAX_PRO;//百分比
    float pro = percent * mProWidth;//进度条宽度

    if (pro <= dip2px(6)) {
      //宽度小于半圆的时候  由于裁剪了画布  直接画矩形就好
      mPath.addRect(dip2px(15), dip2px(20), dip2px(15) + pro, dip2px(32), Path.Direction.CW);
      canvas.drawPath(mPath, mPaint);
    } else if (pro <= dip2px(12)) {
      //宽度在一个圆之内  先绘制半圆矩形
      float a = pro - dip2px(6);
      mPath.addRect(dip2px(15), dip2px(20), dip2px(21), dip2px(32), Path.Direction.CCW);
      mPath.moveTo(dip2px(21), dip2px(20));
      //绘制二阶贝塞尔曲线
      mPath.quadTo(dip2px(21) + 2 * a, dip2px(26), dip2px(21), dip2px(32));
      mPath.close();
      canvas.drawPath(mPath, mPaint);
    } else {
      //绘制进度条底色
      mRectF.set(dip2px(15), dip2px(20), dip2px(15) + pro, dip2px(32));
      mPath.addRoundRect(mRectF, dip2px(6), dip2px(6), Path.Direction.CW);
      canvas.clipPath(mPath);
      canvas.drawColor(getResources().getColor(R.color.colorE5));
      //绘制花纹
      mPaint.setColor(getResources().getColor(R.color.colorF2));
      float l = 0;
      while (l < pro) {
        mPath.reset();
        mPath.moveTo(dip2px(15) + l, dip2px(32));
        mPath.lineTo(dip2px(15) + l + dip2px(6), dip2px(20));
        mPath.lineTo(dip2px(15) + l + dip2px(10), dip2px(20));
        mPath.lineTo(dip2px(15) + l + dip2px(4), dip2px(32));
        mPath.close();
        l += dip2px(10);
        canvas.drawPath(mPath, mPaint);
      }
      canvas.restore();
    }
  }

  //绘制已完成进度
  private void drawHavePro(int progress, Canvas canvas) {
    if (mPath == null || progress == 0) {
      return;
    }
    mPath.reset();
    float percent = (float) progress / MAX_PRO;//百分比
    float pro = percent * mProWidth;//进度条宽度

    if (pro <= dip2px(6)) {
      //宽度小于半圆的时候  由于裁剪了画布  直接画矩形就好
      mPath.addRect(dip2px(14), dip2px(19), dip2px(15) + pro, dip2px(33), Path.Direction.CW);
    } else if (pro <= dip2px(12)) {
      //宽度在一个圆之内  先绘制半圆矩形
      float a = pro - dip2px(6);
      mPath.addRect(dip2px(14), dip2px(19), dip2px(21), dip2px(33), Path.Direction.CCW);
      mPath.moveTo(dip2px(21), dip2px(19));
      //绘制二阶贝塞尔曲线
      mPath.quadTo(dip2px(21) + 2 * a, dip2px(26), dip2px(21), dip2px(33));
      mPath.close();
    } else {
      //绘制进度条底色
      mRectF.set(dip2px(15), dip2px(20), dip2px(15) + pro, dip2px(32));
      mPath.addRoundRect(mRectF, dip2px(6), dip2px(6), Path.Direction.CW);
    }
    canvas.drawPath(mPath, mPaint);
  }

  private float dip2px(float dpValue) {
    float scale = getResources().getDisplayMetrics().density;
    return dpValue * scale + 0.5f;
  }

  public void setProgress(int havePro, int freezePro) {
    mHavePro = havePro;
    mFreezePro = freezePro;
    invalidate();
  }

  public void setProgress(float havePro, float freezePro) {
    if (havePro >= 1) {
      mHavePro = MAX_PRO;
      mFreezePro = MAX_PRO;
      if (mIsAnimation) {
        startAnimation();
      } else {
        invalidate();
      }
      return;
    }
    if (freezePro <= 0) {
      freezePro = 0.01f;
    }
    freezePro += havePro;
    if (freezePro > 1) {
      freezePro = 1;
    }
    mHavePro = (int) (havePro * MAX_PRO);
    mFreezePro = (int) (freezePro * MAX_PRO);
    if (mIsAnimation) {
      startAnimation();
    } else {
      invalidate();
    }
  }

  public void setAnimation(boolean animation) {
    mIsAnimation = animation;
  }

  private void startAnimation() {
    final int havePro = mHavePro;
    mAnimator = ValueAnimator.ofInt(0, mFreezePro);
    mAnimator.setDuration(1500);
    mAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
      @Override public void onAnimationUpdate(ValueAnimator valueAnimator) {

        int pro = (int) valueAnimator.getAnimatedValue();
        if (pro <= havePro) {
          mHavePro = pro;
        } else {
          mHavePro = havePro;
        }
        mFreezePro = pro;
        invalidate();
      }
    });
    mAnimator.start();
  }

  public void stopAnimation() {
    if (mAnimator != null && mAnimator.isRunning()) {
      mAnimator.cancel();
    }
  }
}
