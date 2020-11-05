package com.example.testdemo.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import androidx.annotation.IntDef;
import androidx.annotation.Nullable;
import com.example.testdemo.R;
import com.example.testdemo.utils.SizeUtil;

/**
 * @author : myth_hai
 * @date : 2020/9/16 13:04
 * @description : GradientProgressView 渐变进度条
 */
public class GradientProgressView extends View {

  private final static String TAG      = "GradientProgressView";
  private              int    colorStart;
  private              int    colorEnd;
  private              int    colorBack;
  private              String text;
  private              int    textColor;
  private              float  textSize;
  private              float  round;
  private              int    max      = 100;
  private              int    textMode = 1;
  private              int    progress = 0;
  private              int    width;
  private              int    height;
  private              int    textWidth;

  public static final int START  = 1;
  public static final int CENTER = 2;
  public static final int END    = 3;
  public static final int FRONT  = 4;
  public static final int OUT    = 5;

  @IntDef({ START, CENTER, END, FRONT, OUT })
  public @interface TEXT_MODE {
  }

  //默认画笔
  private Paint mPaint     = new Paint(Paint.ANTI_ALIAS_FLAG);
  private Paint mPaintBack = new Paint(Paint.ANTI_ALIAS_FLAG);
  private Paint mPaintText = new Paint();

  private RectF          mPbGroundRect;//pb矩形
  private RectF          mPbGroundOvalRect;//宽度小于半圆的弧形
  private RectF          mBackGroundRect;//背景矩形
  private LinearGradient backGradient;
  private Path           mPath;

  public GradientProgressView(Context context) {
    super(context);
    init(context, null);
  }

  public GradientProgressView(Context context,
      @Nullable AttributeSet attrs) {
    super(context, attrs);
    init(context, attrs);
  }

  public GradientProgressView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init(context, attrs);
  }

  public GradientProgressView(Context context, @Nullable AttributeSet attrs, int defStyleAttr,
      int defStyleRes) {
    super(context, attrs, defStyleAttr, defStyleRes);
    init(context, attrs);
  }

  private void init(Context context, @Nullable AttributeSet attrs) {
    //获取自定义属性
    if (attrs != null) {
      TypedArray typedArray =
          context.obtainStyledAttributes(attrs, R.styleable.GradientProgressView);
      colorStart = typedArray.getColor(R.styleable.GradientProgressView_startColor,
          getResources().getColor(R.color.colorFFCF31));
      colorEnd = typedArray.getColor(R.styleable.GradientProgressView_endColor,
          getResources().getColor(R.color.colorFF6740));

      colorBack = typedArray.getColor(R.styleable.GradientProgressView_backColor,
          getResources().getColor(R.color.colorEED1D1));
      round = typedArray.getDimension(R.styleable.GradientProgressView_pbRound,
          SizeUtil.dip2px(context, 8));

      text = typedArray.getString(R.styleable.GradientProgressView_pbText);
      textColor = typedArray.getColor(R.styleable.GradientProgressView_pbTextColor,
          getResources().getColor(R.color.colorBlack));
      textSize =
          typedArray.getDimensionPixelSize(R.styleable.GradientProgressView_pbTextSize,
              SizeUtil.sp2px(getContext(),10));
      textMode = typedArray.getInt(R.styleable.GradientProgressView_pbMode, 1);
      typedArray.recycle();
    }
    //必须加，否则onTouchEvent只响应ACTION_DOWN
    setClickable(true);
    //设置抗锯齿
    mPaint.setAntiAlias(true);
    //设置防抖动
    mPaint.setDither(true);
    mPaint.setStyle(Paint.Style.FILL);
    mPath = new Path();
    mPaintText.setAntiAlias(true);
    mPaintText.setDither(true);
  }

  @Override
  protected void onSizeChanged(int w, int h, int oldw, int oldh) {
    super.onSizeChanged(w, h, oldw, oldh);
    width = w;
    height = h;
  }

  void calculateSize() {
    Rect rect = new Rect();
    mPaintText.setTextSize(textSize);
    mPaintText.setColor(textColor);
    switch (textMode) {
      case 1://start
      case 4://front
        mPaintText.setTextAlign(Paint.Align.LEFT);
        break;
      case 2://center
        mPaintText.setTextAlign(Paint.Align.CENTER);
        break;
      case 3://end
      case 5:
        mPaintText.setTextAlign(Paint.Align.RIGHT);
        break;
    }

    if (!TextUtils.isEmpty(text)) {
      mPaintText.getTextBounds(text, 0, text.length(), rect);
      textWidth = rect.width();//文字宽度
      textWidth += 10;// TODO: 2020/9/16 临时写死加10的边界
    } else {
      textWidth = 0;
    }

    int pbw = width * progress / 100;
    if (textMode == 4) {
      //front
      pbw = ((width - textWidth) * progress / 100);
      mPbGroundRect = new RectF(textWidth, 0, textWidth + pbw, height);
      mBackGroundRect = new RectF(textWidth, 0, width, height);
      backGradient =
          new LinearGradient(textWidth, 0, width, 0, new int[] { colorStart, colorEnd }, null,
              Shader.TileMode.CLAMP);
    } else if (textMode == 5) {
      pbw = ((width - textWidth) * progress / 100);
      mPbGroundRect = new RectF(0, 0, pbw, height);
      mBackGroundRect = new RectF(0, 0, width - textWidth, height);
      backGradient =
          new LinearGradient(0, 0, width - textWidth, 0, new int[] { colorStart, colorEnd },
              null,
              Shader.TileMode.CLAMP);
    } else {
      mPbGroundRect = new RectF(0, 0, pbw, height);
      mBackGroundRect = new RectF(0, 0, width, height);
      backGradient =
          new LinearGradient(0, 0, width, 0, new int[] { colorStart, colorEnd }, null,
              Shader.TileMode.CLAMP);
    }
  }

  @Override
  protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);

    if (round > height / 2f) {
      round = height / 2f;
    }

    calculateSize();
    drawBack(canvas);
    if (!TextUtils.isEmpty(text)) {
      drawText(canvas);
    }
  }

  private void drawText(Canvas canvas) {
    //绘制文字
    int textStartX = canvas.getWidth() / 2;
    switch (textMode) {
      case 1://start
        mPaintText.setTextAlign(Paint.Align.LEFT);
        textStartX = (int) round;
        break;
      case 2://center
        mPaintText.setTextAlign(Paint.Align.CENTER);
        break;
      case 3://end
        mPaintText.setTextAlign(Paint.Align.RIGHT);
        textStartX = (int) (width - round);
        break;
      case 4://front
        textStartX = 0;
        mPaintText.setTextAlign(Paint.Align.LEFT);
        break;
      case 5://end
        mPaintText.setTextAlign(Paint.Align.RIGHT);
        textStartX = width;
        break;
    }

    Paint.FontMetricsInt fontMetrics = mPaintText.getFontMetricsInt();
    float baseline = mPbGroundRect.top
        + (mPbGroundRect.bottom - mPbGroundRect.top - fontMetrics.bottom + fontMetrics.top) / 2
        - fontMetrics.top;
    canvas.drawText(text, textStartX, baseline, mPaintText);
  }

  private void drawBack(Canvas canvas) {
    //画背景底色
    mPaintBack.setColor(colorBack);
    mPaint.setShader(backGradient);

    //绘制背景 圆角矩形
    if (mBackGroundRect != null) {
      canvas.drawRoundRect(mBackGroundRect, round, round, mPaintBack);
    }

    if (null != mPbGroundRect) {
      if (mPbGroundRect.width() <= round) {
        canvas.save();
        mPath.addRoundRect(mBackGroundRect, round, round, Path.Direction.CW);
        canvas.clipPath(mPath);
        canvas.drawCircle(0, height / 2f, mPbGroundRect.width(), mPaint);
        canvas.restore();
        //canvas.drawArc(mPbGroundRect, 90, 180, true, mPaint);//画圆弧，这个时候，绘制没有经过圆心
      } else if (mPbGroundRect.width() < 2 * round) {
        //小于一个圆
        canvas.save();
        RectF cir = new RectF(0, 0, height, height);
        mPath.addArc(cir, 90, 180);
        mPath.quadTo(height, height / 2f, round, height);
        mPath.close();
        canvas.clipPath(mPath);
        canvas.drawRect(0, 0, mPbGroundRect.width(), height, mPaint);
        canvas.restore();
      } else {
        canvas.drawRoundRect(mPbGroundRect, round, round, mPaint);
      }
    }
  }

  public void setText(String text) {
    if (!TextUtils.isEmpty(text)) {
      this.text = text;
      postInvalidate();
    }
  }

  public void setTextAndProgress(String text, int progress) {
    if (!TextUtils.isEmpty(text)) {
      this.text = text;
    }
    if (progress > max) {
      progress = max;
    }
    this.progress = progress;
    postInvalidate();
  }

  public void setProgress(int progress) {
    if (progress > max) {
      progress = max;
    }
    this.progress = progress;
    postInvalidate();
  }

  public void setTextMode(@TEXT_MODE int textMode) {
    this.textMode = textMode;
  }
}

