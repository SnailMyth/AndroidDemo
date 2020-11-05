package com.example.testdemo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import org.jetbrains.annotations.NotNull;

public class SurfaceViewTemplate extends SurfaceView implements SurfaceHolder.Callback, Runnable {
  private static final String TAG = "SurfaceViewTemplate";

  private SurfaceHolder mSurfaceHolder;
  //绘图的Canvas
  private Canvas        mCanvas;
  //子线程标志位
  private boolean       mIsDrawing;

  private int x = 0, y = 0;
  private Paint mPaint;
  private Path  mPath;
  private float mTheta = 0f;

  public SurfaceViewTemplate(Context context) {
    this(context, null);
    init(context, null);
  }

  public SurfaceViewTemplate(Context context, AttributeSet attrs) {
    this(context, attrs, 0);
    init(context, attrs);
  }

  public SurfaceViewTemplate(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init(context, attrs);
  }

  private void init(Context context, AttributeSet attrs) {
    mSurfaceHolder = getHolder();
    mSurfaceHolder.setFormat(PixelFormat.TRANSPARENT);
    //注册回调方法
    mSurfaceHolder.addCallback(this);
    //设置一些参数方便后面绘图
    setFocusable(true);
    setKeepScreenOn(true);
    setFocusableInTouchMode(true);

    mPaint = new Paint();
    mPaint.setColor(Color.RED);
    mPaint.setStyle(Paint.Style.STROKE);
    mPaint.setAntiAlias(true);
    mPaint.setStrokeWidth(5);
    mPath = new Path();
    //路径起始点(0, 100)
    mPath.moveTo(0, 100);
  }

  @Override
  public void surfaceCreated(@NotNull SurfaceHolder holder) {
    Log.d(TAG, "SurfaceView:创建 ");
    //创建
    mIsDrawing = true;
    //开启子线程
    new Thread(this).start();
  }

  @Override
  public void surfaceChanged(@NotNull SurfaceHolder holder, int format, int width, int height) {
    Log.d(TAG, "SurfaceView:改变 ");
    //改变
  }

  @Override
  public void surfaceDestroyed(@NotNull SurfaceHolder holder) {
    //销毁
    Log.d(TAG, "SurfaceView:销毁 ");
    mIsDrawing = false;
  }

  @Override
  public void run() {
    //子线程
    while (mIsDrawing) {
      drawSomething();
      // 振幅
      int amplitude = 50;
      int height = getHeight();
      // 波长
      int width = getWidth();
      int index = 0;

      mPath.reset();
      mPath.moveTo(0, 0);
      while (index <= width) {
        float endY = (float) (Math.sin((float) index / (float) width * 2f * Math.PI + mTheta)
            * (float) amplitude + height - amplitude);
        mPath.lineTo(index, endY);
        index++;
      }
      mPath.lineTo(index - 1, 0);
      mPath.close();


      mTheta += 0.05;
      if (mTheta >= 2f * Math.PI) {
        mTheta -= (2f * Math.PI);
      }
    }
  }

  //绘图逻辑
  private void drawSomething() {
    try {
      //获得canvas对象
      mCanvas = mSurfaceHolder.lockCanvas();
      //绘制路径
      mCanvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
      mCanvas.drawPath(mPath, mPaint);
    } catch (Exception ignored) {

    } finally {
      if (mCanvas != null) {
        //释放canvas对象并提交画布
        mSurfaceHolder.unlockCanvasAndPost(mCanvas);
      }
    }
  }
}
