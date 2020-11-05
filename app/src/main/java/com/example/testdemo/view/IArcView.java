package com.example.testdemo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

public class IArcView extends View {
  private static String TAG = "自定义绘图";

  public IArcView(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public IArcView(Context context) {
    super(context);
  }

  @Override
  protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    setMeasuredDimension(widthMeasureSpec, heightMeasureSpec);
    super.onMeasure(widthMeasureSpec, heightMeasureSpec);
  }

  @Override
  protected void onDraw(Canvas canvas) {
    float width = getWidth();
    float height = getHeight();
    super.onDraw(canvas);
    Paint paint = new Paint();
    paint.setColor(StringToColor("#394263"));
    paint.setStrokeWidth(1);
    paint.setAntiAlias(true);
    paint.setStyle(Paint.Style.FILL);
    RectF rectF = new RectF(0, 0, width, height);
    RectF bottom = new RectF(0, height/2, width, height);
    // 画弧形
    canvas.drawArc(rectF, -180, 180, false, paint);
    // 画底部矩形
    canvas.drawRect(bottom,paint);
    //圆弧描边
    paint.setColor(StringToColor("#646e91"));
    paint.setStyle(Paint.Style.STROKE);
    canvas.drawArc(rectF, -180, 180, false, paint);
  }

  /**
   * #颜色转16进制颜色
   *
   * @param str {String} 颜色
   * @return
   */
  private int StringToColor(String str) {
    return 0xff000000 | Integer.parseInt(str.substring(2), 16);
  }
}