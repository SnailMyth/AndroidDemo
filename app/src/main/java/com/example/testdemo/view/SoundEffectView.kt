package com.example.testdemo.view

import android.content.Context
import android.graphics.*
import android.graphics.Paint.Cap.ROUND
import android.graphics.Paint.Join
import android.graphics.Paint.Style.STROKE
import android.util.AttributeSet
import android.view.View
import com.example.testdemo.R
import com.example.testdemo.utils.GetResourceUtil

/**
 *     author : myz
 *     time   : 2021/02/22 11:17
 *     desc   :
 *     link   :
 *     version: 1.0
 */
class SoundEffectView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val colorStart = GetResourceUtil.getColor(R.color.color1AFFFF)
    private val colorEnd = GetResourceUtil.getColor(R.color.colorE2B1FF)

    private var isUp = true

    private val volumeList: MutableList<VolumeBean> = mutableListOf()

    private var ringDegree = 0f
        set(value) {
            if (value >= 360) {
                field -= 360
            } else {
                field = value
            }
        }

    private val bgDrawable by lazy {
        GetResourceUtil.getDrawable(R.drawable.bg_sound_effect_background)
            .apply { this.alpha = 122 }
    }

    private val weaveStartEndMargin = 10

    private var maxWeaveHeight = 0f

    private var maxWeaveWidth = 0f

    private var controlPoint1 = PointF()
    private var controlPoint2 = PointF()
    private var controlPoint3 = PointF()
    private var controlPoint4 = PointF()

    private var lineSize = 0f
    private var lineGap = 0f
    private var linearGradient1: LinearGradient? = null
    private var linearGradient2: LinearGradient? = null

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        initValues(w, h)
    }

    private fun initValues(width: Int, height: Int) {
        maxWeaveWidth = width - weaveStartEndMargin * 2f
        maxWeaveHeight = height / 8f
        controlPoint1.set(maxWeaveWidth / 16f + weaveStartEndMargin, height / 2f - maxWeaveHeight)
        controlPoint2.set(
            maxWeaveWidth * 3 / 16f + weaveStartEndMargin,
            height / 2f + maxWeaveHeight
        )
        controlPoint3.set(
            maxWeaveWidth * 13 / 16f + weaveStartEndMargin,
            height / 2f - maxWeaveHeight
        )
        controlPoint4.set(
            maxWeaveWidth * 15 / 16f + weaveStartEndMargin,
            height / 2f + maxWeaveHeight
        )
        lineSize = maxWeaveWidth / 40f
        lineGap = (maxWeaveWidth / 2f - 8 * lineSize) / 7
        linearGradient1 = LinearGradient(
            -width / 2f + weaveStartEndMargin, height / 2f, width / 2f - weaveStartEndMargin,
            height / 2f,
            intArrayOf(colorStart, Color.WHITE, colorEnd), null, Shader.TileMode.CLAMP
        )
        linearGradient2 = LinearGradient(
            weaveStartEndMargin.toFloat(), height / 2f, width.toFloat() - weaveStartEndMargin,
            height / 2f,
            intArrayOf(colorStart, Color.WHITE, colorEnd), null, Shader.TileMode.CLAMP
        )
    }


    private val mPaint: Paint by lazy {
        Paint().apply {
            this.style = STROKE
            this.strokeWidth = lineSize
            this.isAntiAlias = true
            this.strokeCap = ROUND
            this.strokeJoin = Join.ROUND
            this.xfermode = PorterDuffXfermode(PorterDuff.Mode.LIGHTEN)
        }
    }
    private val path = Path()

    private fun move() {
        if (isUp) {
            if (controlPoint1.y <= height / 2f - maxWeaveHeight) {
                isUp = false
            } else {
                controlPoint1.y -= maxWeaveHeight / 2
                controlPoint2.y += maxWeaveHeight / 2
                controlPoint3.y -= maxWeaveHeight / 2
                controlPoint4.y += maxWeaveHeight / 2
            }
        } else {
            if (controlPoint1.y >= height / 2f + maxWeaveHeight) {
                isUp = true
            } else {
                controlPoint1.y += maxWeaveHeight / 2
                controlPoint2.y -= maxWeaveHeight / 2
                controlPoint3.y += maxWeaveHeight / 2
                controlPoint4.y -= maxWeaveHeight / 2
            }
        }
        if (volumeList.isNotEmpty()) {
            for (i in volumeList.size - 1 downTo 0) {
                if (volumeList[i].x + lineGap + lineSize > maxWeaveWidth * 3 / 4f + weaveStartEndMargin) {
                    volumeList.removeAt(i)
                } else {
                    volumeList[i].x = volumeList[i].x + lineGap + lineSize
                }
            }
        }
    }

    init {
        background = bgDrawable
//        alpha = 0f
    }

    fun addVolume(volume: Int) {
        if (volume > 5) {
            alpha = if (alpha + 0.4 > 1f) {
                1f
            } else {
                alpha + 0.4f
            }
            move()
            invalidate()
            val res = if (volume > 100) {
                100
            } else {
                volume
            }
            volumeList.add(
                VolumeBean(
                    maxWeaveWidth / 4f + weaveStartEndMargin,
                    (res / 100f) * (measuredHeight - paddingTop - paddingBottom)
                )
            )
        } else {
            alpha = if (alpha - 0.4 < 0f) {
                0f
            } else {
                alpha - 0.4f
            }
            invalidate()
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        if (alpha == 0f) {
            return
        }
        mPaint.shader = linearGradient1
        canvas.translate(width / 2f, height / 2f)
        canvas.rotate(ringDegree)
        canvas.drawCircle(
            0f, 0f, width / 2f - lineSize / 2f, mPaint
        )
        canvas.rotate(-ringDegree)
        canvas.translate(-width / 2f, -height / 2f)
        ringDegree += 10f
        mPaint.shader = linearGradient2
        path.reset()
        path.moveTo(weaveStartEndMargin.toFloat(), height / 2f)
        path.quadTo(
            controlPoint1.x, controlPoint1.y, maxWeaveWidth / 8f + weaveStartEndMargin, height / 2f
        )
        path.quadTo(
            controlPoint2.x, controlPoint2.y, maxWeaveWidth / 4f + weaveStartEndMargin, height / 2f
        )
        path.lineTo(maxWeaveWidth * 3 / 4f + weaveStartEndMargin, height / 2f)
        path.quadTo(
            controlPoint3.x,
            controlPoint3.y,
            maxWeaveWidth * 7 / 8f + weaveStartEndMargin,
            height / 2f
        )
        path.quadTo(
            controlPoint4.x,
            controlPoint4.y,
            maxWeaveWidth + weaveStartEndMargin,
            height / 2f
        )
        canvas.drawPath(path, mPaint)
        volumeList.forEach {
            canvas.drawLine(
                it.x, height / 2 - it.height / 2f,
                it.x, height / 2 + it.height / 2f, mPaint
            )
        }
    }

    data class VolumeBean(
        var x: Float = 0f,
        var height: Float = 0f
    )

}