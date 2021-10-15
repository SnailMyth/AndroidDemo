package com.example.testdemo.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import androidx.annotation.NonNull;
import com.example.testdemo.R;
import com.example.testdemo.utils.LogUtil;
import com.example.testdemo.utils.SizeUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MvpAnimView extends SurfaceView implements SurfaceHolder.Callback {

  private List<ParticleBean> mBgViews;
  private SurfaceHolder      mSurfaceHolder;
  private Paint              mPaint;
  private int[]              speeds  = new int[] { 10, 30, 50, 100 };
  private int[]              yspeeds = new int[] { 2, 10, 16, 20 };
  private int[]              colors  = new int[] {
      R.color.liji_material_blue_500, R.color.colorAccent,
      R.color.colorFFCF31, R.color.colorFC6076
  };

  private int    oriention = 2;//1left 2.right
  //绘图的Canvas
  private Canvas mCanvas;

  private int maxX;
  private int maxY;
  private int maxViewNum = 50;

  private Runnable mRunnable;
  private Thread   mThread;
  private boolean  isSurfaceCreated = false;
  private boolean  canRun           = false;

  private static final String TAG = "myth";

  public MvpAnimView(Context context) {
    super(context);
    initView(context, null);
  }

  public MvpAnimView(Context context, AttributeSet attrs) {
    super(context, attrs);
    initView(context, attrs);
  }

  public MvpAnimView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    initView(context, attrs);
  }

  private void initView(Context context, AttributeSet attrs) {
    if (attrs != null) {
      TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MvpView);
      oriention = typedArray.getInt(R.styleable.MvpView_mvp_orientation, 1);
      typedArray.recycle();
    }
    initViews();
    setZOrderOnTop(true);
    mSurfaceHolder = getHolder();
    mSurfaceHolder.setFormat(PixelFormat.TRANSLUCENT);
    mSurfaceHolder.addCallback(this);
    setFocusable(true);
    setFocusableInTouchMode(true);
    mPaint = new Paint();
    mPaint.setStyle(Paint.Style.FILL);
    mPaint.setAntiAlias(true);
    mRunnable = new Runnable() {
      @Override
      public void run() {
        while (canRun) {
          long start = System.currentTimeMillis();
          drawViews();
          long end = System.currentTimeMillis();
          if (end - start < 50) {
            try {
              Thread.sleep(50 - (end - start));
            } catch (InterruptedException e) {
              e.printStackTrace();
            }
          }
        }
      }
    };
  }

  private void initViews() {
    int radiusMax = SizeUtil.dip2px(getContext(), 8f);
    int radiusMin = SizeUtil.dip2px(getContext(), 5f);
    int widthMax = SizeUtil.dip2px(getContext(), 20f);
    int widthMin = SizeUtil.dip2px(getContext(), 8f);
    int heightMax = SizeUtil.dip2px(getContext(), 20f);
    int heightMin = SizeUtil.dip2px(getContext(), 5f);

    mBgViews = new ArrayList<>(maxViewNum);
    LogUtil.debugInfo("myth", "初始化 views===============================================开始");
    for (int i = 0; i < maxViewNum; i++) {
      int shape = (i % 4 == 0) ? 2 : 1;
      int color = new Random().nextInt(4);
      ParticleBean particleBean = new ParticleBean(0, 0, colors[color]);
      particleBean.setXSpeed(new Random().nextInt(50) + 30);
      particleBean.setDelayValue(maxViewNum, i, 5);
      particleBean.setYSpeed(yspeeds[color]);
      if (shape == 2) {
        particleBean.setOval(new Random().nextInt(radiusMax - radiusMin) + radiusMin);
        LogUtil.debugInfo("myth", "drawViews: 圆" + particleBean);
        particleBean.setAngle(new Random().nextInt(180));
      } else {
        LogUtil.debugInfo("myth", "drawViews:矩形 " + particleBean);
        particleBean.setRec(new Random().nextInt(widthMax - widthMin) + widthMin,
            new Random().nextInt(heightMax - heightMin) + heightMin);
        particleBean.setAngle(new Random().nextInt(90) - 45);
      }

      mBgViews.add(particleBean);
    }
    LogUtil.debugInfo("myth", "初始化 views==============================================結束");
  }

  private void drawViews() {
    try {
      mCanvas = mSurfaceHolder.lockCanvas();
      mCanvas.drawColor(Color.WHITE, PorterDuff.Mode.CLEAR);
      //初始化画布并在画布上画一些东西
      int height = getHeight();
      int width = getWidth();
      if (height == 0 && width == 0) return;
      for (int i = 0; i < maxViewNum; i++) {
        ParticleBean bean = mBgViews.get(i);
        if (bean.getX() == 0 && bean.getY() == 0) {

          mBgViews.get(i).setY(height / 2);
          mBgViews.get(i).setX(width);
        } else {
          if (bean.getShowDelay() <= 0) {
            mPaint.setColor(getContext().getColor(bean.getColor()));

            mCanvas.save();
            float degrees = (float) Math.toDegrees(Math.atan(height * 1.00d / width));
            if (bean.getAngle() > Math.abs(degrees)) {
              bean.setAngle(new Random().nextInt((int) degrees));
            }
            mCanvas.rotate(bean.getAngle());
            if (bean.getShape() == 1) {
              //矩形
              if (oriention == 2) {
                mCanvas.drawRect(bean.getX(), bean.getY(), bean.getX() + bean.getWidth(),
                    bean.getY() + bean.getHeight(), mPaint);
              } else {
                mCanvas.drawRect(width - bean.getX() - bean.getWidth(), bean.getY(),
                    width - bean.getX(),
                    bean.getY() + bean.getHeight(), mPaint);
              }
            } else {
              //圆
              if (oriention == 2) {
                mCanvas.drawCircle(bean.getX(), bean.getY(), bean.getRadius(), mPaint);
              } else {
                mCanvas.drawCircle(width - bean.getX() - bean.getRadius() * 2, bean.getY(),
                    bean.getRadius(), mPaint);
              }
            }
            mCanvas.restore();
            mBgViews.get(i).setX(bean.getNextX());
            //mBgViews.get(i).setY(bean.getNextY());

            int newX = bean.getX() + bean.getXSpeed();
            int newY;
            if ((new Random().nextInt(10) & 2) == 0) {
              newY = bean.getY() + bean.getYSpeed();
            } else {
              newY = bean.getY() - bean.getYSpeed();
            }
            if ((newX + bean.getWidth()) >= width) {
              newX = 0;
            }

            if ((newY + bean.getHeight()) >= height || newY <= 0) {
              newY = height / 2;
            }

            mBgViews.get(i).setNextX(newX);
            mBgViews.get(i).setNextY(newY);
          } else {
            mBgViews.get(i).setShowDelay(bean.getShowDelay() - bean.getXSpeed() / 50);
          }
        }
      }
    } catch (Exception e) {

    } finally {
      //判断画布是否为空，从而避免黑屏情况
      if (mCanvas != null) {
        mSurfaceHolder.unlockCanvasAndPost(mCanvas);
      }
    }
  }

  @Override
  public void surfaceCreated(@NonNull SurfaceHolder holder) {
    isSurfaceCreated = true;
  }

  @Override
  public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {

  }

  @Override
  public void surfaceDestroyed(@NonNull SurfaceHolder holder) {
    canRun = false;
    isSurfaceCreated = false;
  }

  public void play() {
    if (mThread == null || mThread.isInterrupted() || !mThread.isAlive()) {
      mThread = new Thread(mRunnable);
      canRun = true;
      mThread.start();
    }
  }
}
