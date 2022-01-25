package com.example.testdemo.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.example.testdemo.R
import com.example.testdemo.utils.LogUtil
import com.example.testdemo.utils.SizeUtil

/**
 * @author : myth_hai
 * @date : 2021/12/24
 * @time : 11:46
 * @description :
 */
class SoundSpectrumLinearView : View {

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

  private var mLineCount = 4

  private var mLineWidth = SizeUtil.dip2px(context, 5f)

  private var mWidth = 0

  private var lineStartPlayTime: IntArray? = null

  /**
   * 线之间的间隔
   */
  private var mLineGap = SizeUtil.dip2px(context, 4f).toFloat()

  private var isAutoPlay = true

  private var duration = 900//动画时间

  private var onceTime = 600//线条一次拉长缩小的时间

  private var timeInterval = 100

  private var currentTime: Int = 0//当前动画时间点

  private var isPlaying = false

  private var timeSpeed: Long = 30
  private var lineSpeed: Int = 45

  private val paint = Paint()

  private fun init(
    context: Context?,
    attrs: AttributeSet?
  ) {
    paint.color = Color.WHITE
    paint.strokeCap = Paint.Cap.ROUND
    paint.strokeWidth = mLineWidth / 2.toFloat()
    paint.isAntiAlias = true

    context?.let {
      attrs?.let {
        val ty = context.obtainStyledAttributes(it, R.styleable.SoundSpectrumLinearView)

        mLineWidth = ty.getDimension(
          R.styleable.SoundSpectrumLinearView_sslv_lineWidth, SizeUtil.dip2pxF(context, 5f)
        ).toInt()


        paint.strokeWidth = mLineWidth / 2.toFloat()

        mLineCount = ty.getInt(R.styleable.SoundSpectrumLinearView_sslv_line_count, 4)
        lineStartPlayTime = IntArray(mLineCount)
        onceTime = ty.getInt(R.styleable.SoundSpectrumLinearView_sslv_once_time, 1000)
        isAutoPlay = ty.getBoolean(R.styleable.SoundSpectrumLinearView_sslv_auto_play, false)
        if (onceTime > duration) {
          onceTime = duration
        }
        ty.recycle()
      }
    }

    log("mLineWidth${paint.strokeWidth}")
  }

  override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
    super.onSizeChanged(w, h, oldw, oldh)
    if (w != 0) {
      mWidth = measuredWidth
//      log("mWidth=>$mWidth,mLineWidth=>$mLineWidth,gap=>$mLineGap,paddingStart=>$paddingStart,paddingEnd=>$paddingEnd,w=>$w")
      calculateLineNum()
    }
  }

  private fun log(str: String) {
    LogUtil.debugInfo("myth", str)
  }

  //计算线条数量
  private fun calculateLineNum() {

    val centerWidth = mWidth - paddingStart - paddingEnd - mLineWidth

    mLineGap = (centerWidth / (mLineCount - 1)).toFloat()

    timeInterval = onceTime *5 /18
    log("timeInterval$timeInterval")
    lineStartPlayTime = IntArray(mLineCount)
    for (i in 0 until mLineCount) {
      //计算每个点开始动画的时间
      lineStartPlayTime!![i] = timeInterval * i
    }
    duration = lineStartPlayTime!![mLineCount - 1] + onceTime
  }

  override fun onDraw(canvas: Canvas?) {
    canvas?.let {

      val nanoTime = System.currentTimeMillis()
      lineStartPlayTime?.let { sizes ->
        var offsetX = paddingStart + mLineWidth / 2f
        for (i in sizes.indices) {
          val lineHeight = getLineHeight(sizes[i])
          //起始y坐标
          val startY = (measuredHeight - lineHeight) / 2f
          //绘制线
          it.drawLine(
            offsetX,
            startY,
            offsetX,
            startY + lineHeight,
            paint
          )
          //x偏移量
          offsetX += mLineGap
        }
      }

      if (currentTime >= duration) {
        //重置时间
        currentTime = 0
      }

      log("time=>${System.currentTimeMillis() - nanoTime}")
      if (isPlaying) {
        currentTime += lineSpeed
        postInvalidateDelayed(timeSpeed)
      }
    }
  }

  public fun startPlay() {
    if (isPlaying) return
    postDelayed({
      currentTime = timeSpeed.toInt()
      isPlaying = true
      invalidate()
    }, timeSpeed)
  }

  public fun stopPlay() {
    stopPlay(false)
  }

  public fun stopPlay(reset: Boolean = false) {
    isPlaying = false
    if (reset) {
      currentTime = 0
      invalidate()
    }
  }

  private fun getLineHeight(
    startTime: Int
  ): Int {
    var time = currentTime
    if (currentTime > duration) {
      time = currentTime % duration
    }

    var lineHeight = 0
    //当前一轮动画播放进度
    var playTime = time - startTime
    if (playTime >= onceTime || playTime <= 0) {
      //至少已经播放了一轮

      //还没有到开始时间
      lineHeight = mLineWidth / 4
    } else {
      var percent = 0f;
      if (playTime > (onceTime / 2)) {
        playTime -= (onceTime / 2)
        percent = 1 - playTime * 1f / (onceTime / 2)
      } else {
        percent = playTime * 1f / (onceTime / 2)
      }

      //线高
      lineHeight = ((measuredHeight - paddingTop - paddingBottom) * percent).toInt()

      if (lineHeight < (mLineWidth / 2)) {
        lineHeight = mLineWidth / 4
      }
    }

    return lineHeight
  }

  override fun onDetachedFromWindow() {
    super.onDetachedFromWindow()
  }

  override fun onAttachedToWindow() {
    super.onAttachedToWindow()
    if (isAutoPlay) {
      startPlay()
    }
  }
}


