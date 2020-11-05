package com.example.testdemo.view

import android.content.Context
import android.graphics.Camera
import android.graphics.Canvas
import android.graphics.Paint
import android.text.TextPaint
import android.util.AttributeSet
import android.view.View
import com.example.testdemo.R

/**
 * TODO: document your custom view class.
 */
class CustomView : View {

  var mCamera = Camera()

  private lateinit var textPaint: TextPaint
  private var textWidth: Float = 0f
  private var textHeight: Float = 0f

  constructor(context: Context) : super(context) {
    init(null, 0)
  }

  constructor(
    context: Context,
    attrs: AttributeSet
  ) : super(context, attrs) {
    init(attrs, 0)
  }

  constructor(
    context: Context,
    attrs: AttributeSet,
    defStyle: Int
  ) : super(context, attrs, defStyle) {
    init(attrs, defStyle)
  }

  private fun init(
    attrs: AttributeSet?,
    defStyle: Int
  ) {
    // Load attributes
    val a = context.obtainStyledAttributes(
        attrs, R.styleable.CustomView, defStyle, 0
    )

    a.recycle()

    textPaint = TextPaint().apply {
      flags = Paint.ANTI_ALIAS_FLAG
      textAlign = Paint.Align.LEFT
    }

    // Update TextPaint and text measurements from attributes
    invalidateTextPaintAndMeasurements()
  }

  private fun invalidateTextPaintAndMeasurements() {
    textPaint.let {

    }
  }

  override fun onDraw(canvas: Canvas) {
    super.onDraw(canvas)

  }
}