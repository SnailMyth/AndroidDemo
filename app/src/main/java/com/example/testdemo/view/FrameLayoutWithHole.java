package com.example.testdemo.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.example.testdemo.R;

public class FrameLayoutWithHole extends FrameLayout {
  private Bitmap  mEraserBitmap;
  private Canvas  mEraserCanvas;
  private Paint   mEraser;
  private RectF   mHoleRecF;
  private float   mDensity;
  private Context mContext;

  private float mRadius;
  private int   mBackgroundColor;
  private float mRx;//默认在中心位置
  private float mRy;
  private float mWidth;
  private float mHeight;

  public FrameLayoutWithHole(@NonNull Context context) {
    super(context);
    mContext = context;
  }

  public FrameLayoutWithHole(@NonNull Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
    mContext = context;
    TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.FrameLayoutWithHole);
    mBackgroundColor = ta.getColor(R.styleable.FrameLayoutWithHole_hole_out_background, -1);
    mRadius = ta.getDimension(R.styleable.FrameLayoutWithHole_hole_radius, 0);
    mWidth = ta.getDimension(R.styleable.FrameLayoutWithHole_hole_width, 110);
    mHeight = ta.getDimension(R.styleable.FrameLayoutWithHole_hole_height, 20);
    mRx = ta.getDimension(R.styleable.FrameLayoutWithHole_hole_x, 0);
    mRy = ta.getDimension(R.styleable.FrameLayoutWithHole_hole_y, 0);
    init();
    ta.recycle();
  }

  private void init() {
    setWillNotDraw(false);

    Point size = new Point();
    size.x = mContext.getResources().getDisplayMetrics().widthPixels;
    size.y = mContext.getResources().getDisplayMetrics().heightPixels;


    mRx = mRx != 0 ? mRx : size.x >> 1;
    mRy = mRy != 0 ? mRy : size.y >> 1;

    mRadius = mRadius != 0 ? mRadius : 10;


    mBackgroundColor = mBackgroundColor != -1 ? mBackgroundColor : Color.parseColor("#55000000");

    mEraserBitmap = Bitmap.createBitmap(size.x, size.y, Bitmap.Config.ARGB_8888);
    mEraserCanvas = new Canvas(mEraserBitmap);

    mEraser = new Paint();
    mEraser.setColor(0xFFFFFFFF);
    mEraser.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
    mEraser.setFlags(Paint.ANTI_ALIAS_FLAG);
    mHoleRecF = new RectF(mRx - mWidth / 2, mRy - mHeight / 2, mRx + mWidth / 2, mRy + mHeight / 2);
  }

  @Override
  protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
    mEraserBitmap.eraseColor(Color.TRANSPARENT);
    mEraserCanvas.drawColor(mBackgroundColor);

    mEraserCanvas.drawRoundRect(
        mHoleRecF,
        mRadius,
        mRadius, mEraser);

    canvas.drawBitmap(mEraserBitmap, 0, 0, null);
  }
}