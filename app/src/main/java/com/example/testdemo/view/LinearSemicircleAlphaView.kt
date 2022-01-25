package com.example.testdemo.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Paint
import android.graphics.Path
import android.graphics.Path.Direction
import android.graphics.Shader.TileMode.CLAMP
import android.util.AttributeSet
import android.view.View
import com.example.testdemo.R
import com.example.testdemo.utils.CommonUtils
import com.example.testdemo.utils.GetResourceUtil
import com.example.testdemo.utils.LogUtil

/**
 * @author : myth_hai
 * @date : 2021/12/24
 * @time : 11:46
 * @description :
 */
class LinearSemicircleAlphaView : View {

  constructor(context: Context?) : super(context) {
    init(context, null)
  }

  constructor(
    context: Context?,
    attrs: AttributeSet?
  ) : super(context, attrs) {
    init(context, attrs)
  }

  constructor(
    context: Context?,
    attrs: AttributeSet?,
    defStyleAttr: Int
  ) : super(
    context,
    attrs,
    defStyleAttr
  ) {
    init(context, attrs)
  }

  /**
   * 最大竖线的数量,单边，不算中线
   * 所有的线 = maxLineCount * 2 + 1
   */

  private var mWidth = 0f
  private var mHeight = 0f

  private var isAutoPlay = true

  private var duration = 1500//动画时间

  private var isPlaying = false

  private var timeSpeed: Long = 80

  private val mPaint = Paint()
  private val mPath = Path()

  private lateinit var purpleLinear: LinearGradient
  private lateinit var blueLinear: LinearGradient

  private var curIndex = 0
  private fun init(
    context: Context?,
    attrs: AttributeSet?
  ) {
    mPaint.strokeCap = Paint.Cap.ROUND
    mPaint.isAntiAlias = true
    mPaint.style = Paint.Style.FILL

    context?.let {
      attrs?.let {
        val ty = context.obtainStyledAttributes(it, R.styleable.LinearSemicircleAlphaView)

        duration = ty.getInt(
          R.styleable.LinearSemicircleAlphaView_lsav_duration, 2000
        )
        ty.recycle()
      }
    }
  }

  override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
    super.onSizeChanged(w, h, oldw, oldh)
    mWidth = w.toFloat()
    mHeight = h.toFloat()

    purpleLinear = LinearGradient(
      mWidth / 2,
      mHeight,
      mWidth / 2,
      0f,
      GetResourceUtil.getColor(R.color.colora542ff_80),
      0,
      CLAMP
    )
    blueLinear = LinearGradient(
      mWidth / 2,
      mHeight,
      mWidth / 2,
      0f,
      GetResourceUtil.getColor(R.color.color7281ff_80),
      0,
      CLAMP
    )
    mPaint.shader = purpleLinear
    mPath.reset()
    mPath.addCircle(mWidth / 2, mHeight - mWidth / 2, mWidth / 2, Direction.CCW)
  }

  private fun log(str: String) {
    LogUtil.debugInfo("myth", str)
  }

  private var currentTime = 0f
  private var needChange = false

  override fun onDraw(canvas: Canvas?) {

    canvas?.let {
      it.save()
      it.clipPath(mPath)
      if (isPlaying) {
        if (needChange) {
          if (curIndex == 1) {
            mPaint.shader = purpleLinear
            curIndex = 0
          } else {
            mPaint.shader = blueLinear
            curIndex = 1
          }
          needChange = false
        }

        var tempAlpha = if (currentTime < duration / 2) {
          currentTime / (duration / 2)
        } else {
          1 - (currentTime - duration / 2) / (duration / 2)
        }

        if (tempAlpha < 0.3f) {
          tempAlpha = 0.3f
        }

        alpha = tempAlpha

        it.drawPaint(mPaint)

        currentTime += timeSpeed
        if (currentTime >= duration) {
          currentTime = 0f
          needChange = true
        }
        postInvalidateDelayed(timeSpeed)
      } else {
        alpha = 0f
      }
      it.restore()
    }
  }

  public fun startPlay() {
    if (isPlaying) return
    postDelayed({
      isPlaying = true
      invalidate()
    }, timeSpeed)
  }

  fun stopPlay() {
    stopPlay(false)
  }

  private fun stopPlay(reset: Boolean = false) {
    isPlaying = false
    if (reset) {
      invalidate()
    }
  }

  override fun onAttachedToWindow() {
    super.onAttachedToWindow()
    if (isAutoPlay) {
      startPlay()
    }
  }
}


