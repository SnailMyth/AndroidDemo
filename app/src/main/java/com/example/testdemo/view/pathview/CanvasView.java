package com.example.testdemo.view.pathview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;
import com.example.testdemo.R;
import java.util.ArrayList;
import java.util.List;

public class CanvasView extends View {

  private int     mColorRes = Color.RED;
  private float[] pos;
  private float[] tan;

  public CanvasView(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
    mIconBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_redb);
    pos = new float[2];
    tan = new float[2];
  }

  @Override
  protected void onDraw(Canvas canvas) {
    canvas.drawBitmap(mBitmap, 0, 0, null);
  }

  private Path   mPath          = new Path();
  private Path   mPath2         = new Path();
  private Paint  mPaint         = new Paint();
  private Bitmap mBitmap;
  private Bitmap mIconBitmap;
  private Canvas mCanvas;
  private int    perLineIconNum = 0;//每条线的icon数
  private int    perLength      = 80;//间隔

  {
    mPaint.setAntiAlias(true);
    mPaint.setColor(mColorRes);
    mPaint.setStrokeCap(Paint.Cap.ROUND);
    mPaint.setStyle(Paint.Style.STROKE);
    mPaint.setStrokeWidth(7);
  }

  @Override
  protected void onSizeChanged(int w, int h, int oldw, int oldh) {
    super.onSizeChanged(w, h, oldw, oldh);
    mBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_4444);
    mCanvas = new Canvas(mBitmap);
  }

  @Override
  public boolean onTouchEvent(MotionEvent event) {
    if (!isEnabled()) {
      return false;
    }
    switch (event.getAction()) {
      case MotionEvent.ACTION_DOWN:
        mPath.moveTo(event.getX(), event.getY());
        mPath2.moveTo(event.getX(), event.getY());
        mCanvas.drawBitmap(mIconBitmap, event.getX() - mIconBitmap.getWidth() / 2f,
            event.getY() - mIconBitmap.getHeight(), mPaint);
        perLineIconNum = 1;
        break;
      case MotionEvent.ACTION_MOVE:
        mPath.lineTo(event.getX(), event.getY());
        mPath2.lineTo(event.getX(), event.getY());
        PathMeasure pathMeasure = new PathMeasure();
        pathMeasure.setPath(mPath2, false);
        float length = pathMeasure.getLength();

        if (length >= perLength * perLineIconNum) {
          pathMeasure.getPosTan(perLength * perLineIconNum, pos, tan);
          mCanvas.drawBitmap(mIconBitmap, pos[0] - mIconBitmap.getWidth() / 2f,
              pos[1] - mIconBitmap.getHeight(), mPaint);
          perLineIconNum++;
        }

        break;
      case MotionEvent.ACTION_UP:
        mPath.lineTo(event.getX(), event.getY());
        mPath2.lineTo(event.getX(), event.getY());

        PathMeasure upMeasure = new PathMeasure();
        upMeasure.setPath(mPath2, false);
        float lengthUp = upMeasure.getLength();
        if (lengthUp % perLength != 0) {
          upMeasure.getPosTan(lengthUp, pos, tan);
          mCanvas.drawBitmap(mIconBitmap, pos[0] - mIconBitmap.getWidth() / 2f,
              pos[1] - mIconBitmap.getHeight(), mPaint);
        }
        mPath2.reset();
        break;
    }

    //mCanvas.drawBitmap(mIconBitmap,);
    mCanvas.drawPath(mPath, mPaint);
    invalidate();
    return true;
  }

  public void cancelLast() {
    PathMeasure pathMeasure = new PathMeasure();
    pathMeasure.setPath(mPath, false);
  }

  public Path[] getPaths() {
    List<Path> paths = new ArrayList<>();
    PathMeasure pathMeasure = new PathMeasure();
    pathMeasure.setPath(mPath, false);
    Path path;
    do {
      path = new Path();
      path.rLineTo(0, 0);
      pathMeasure.getSegment(0, pathMeasure.getLength(), path, true);
      if (!path.isEmpty()) {
        paths.add(path);
      }
    } while (pathMeasure.nextContour());
    return paths.toArray(new Path[] {});
  }

  public void clear() {
    setPath(new Path());
  }

  public void setPath(Path path) {
    mPath = path;
    mBitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_4444);
    mCanvas = new Canvas(mBitmap);
    mCanvas.drawPath(mPath, mPaint);
    invalidate();
  }

  public void setLineWidth(int width) {
    mPaint.setStrokeWidth(width);
    invalidate();
  }
}
