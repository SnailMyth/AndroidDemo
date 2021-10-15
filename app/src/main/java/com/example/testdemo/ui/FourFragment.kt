package com.example.testdemo.ui

import android.animation.ValueAnimator
import android.animation.ValueAnimator.AnimatorUpdateListener
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.method.ScrollingMovementMethod
import android.util.Log
import android.view.View
import android.view.View.OnClickListener
import android.widget.FrameLayout
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.example.testdemo.R
import com.example.testdemo.adapter.FourAdapter
import com.example.testdemo.anim.ScaleInTransformer
import com.example.testdemo.base.BaseFragment
import com.example.testdemo.utils.SizeUtil
import com.example.testdemo.view.DraweeSpan.Builder
import com.example.testdemo.view.DraweeTextView
import com.example.testdemo.view.Roll3DView

class FourFragment : BaseFragment() {
  private lateinit var mViewPager: ViewPager2
  private lateinit var mLayout: FrameLayout
  private lateinit var mCameraView: Roll3DView
  private lateinit var textile: DraweeTextView
  private var height: Int = 0
  override fun initData(savedInstanceState: Bundle?) {

  }

  private lateinit var mAdapter: FourAdapter

  override fun getLayoutId(): Int =
    R.layout.fragment_four

  override fun initView(view: View) {
    mViewPager = view.findViewById(R.id.view_pager)
    mLayout = view.findViewById(R.id.temp_layout_2)
    height = SizeUtil.dip2px(context, 200f)
    mAdapter = FourAdapter(mutableListOf(1, 2, 3, 4))
    mViewPager.adapter = mAdapter
    mViewPager.apply {
      offscreenPageLimit = 1
      val recyclerView = getChildAt(0) as RecyclerView
      recyclerView.apply {
        val padding = resources.getDimensionPixelOffset(R.dimen.dp_20) +
            resources.getDimensionPixelOffset(R.dimen.dp_20)
        setPadding(padding, 0, padding, 0)
        clipToPadding = false
      }
    }

    val compositePageTransformer = CompositePageTransformer()
    compositePageTransformer.addTransformer(ScaleInTransformer())
    compositePageTransformer.addTransformer(
        MarginPageTransformer(
            resources.getDimension(R.dimen.dp_10)
                .toInt()
        )
    )
    mViewPager.setPageTransformer(compositePageTransformer)
    mViewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
      override fun onPageSelected(position: Int) {

      }
    })

//    mCameraView = view.findViewById(R.id.cameraView)
//    mCameraView.startAnimation()
    startAnimation()

    textile  = view.findViewById<DraweeTextView>(R.id.text1);
    textile.setMovementMethod(ScrollingMovementMethod.getInstance());
    textile.setText(buildText())
    textile.setOnClickListener {
      OnClickListener() { v ->
        {
          if (v.getTag() == "") {
            textile.setText(buildText());
            v.setTag(null);
          } else {
            textile.setText(buildText2());
            v.setTag("");
          }
        }
      }
    }
  }

  companion object {
    @JvmStatic fun newInstance() =
      FourFragment()
  }

  private fun startAnimation() {
    val animator = ValueAnimator.ofFloat(0f, 10f)
    animator.addUpdateListener(AnimatorUpdateListener { valueAnimator: ValueAnimator ->
      val degree = valueAnimator.animatedValue as Float
      val layoutParams = mLayout.layoutParams
      Log.d(
          "myth", "startAnimation: height->" + height + ",cur->" + (height * (degree / 10)).toInt()
      )
      layoutParams.height = (height * (degree / 10)).toInt()
      mLayout.layoutParams = layoutParams
    })

    animator.setDuration(1000)
    animator.start()
  }

  private fun buildText(): CharSequence? {
    val builder = SpannableStringBuilder()
    builder.append("2333333333333")
    builder.append("\nwh=50, margin=0:www")
    var start = builder.length
    builder.append("[img]")
    builder.setSpan(
        Builder("http://img.yo9.com/24fe1ed09fbc11e59d8700163e00043c")
            .setLayout(50, 50)
            .build(),
        start, builder.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
    )
    builder.append("www")
    builder.append("\nwh=100, margin=8, align baseline:")
    start = builder.length
    builder.append("[img]")
    builder.setSpan(
        Builder("http://img.yo9.com/24fe1ed09fbc11e59d8700163e00043c", true)
            .setLayout(100, 100)
            .setMargin(8)
            .build(),
        start, builder.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
    )
    builder.append("www")
    builder.append("\nwh=100, margin=4, 4, 8，webp:")
    start = builder.length
    builder.append("[img]")
    builder.setSpan(
        Builder("http://img.yo9.com/25126a209fbc11e59d8700163e00043c@100w.webp")
            .setLayout(100, 100)
            .setMargin(4, 4, 8)
            .build(),
        start, builder.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
    )
    builder.append("www")
    builder.append("\nwh=150, margin=0:www")
    start = builder.length
    builder.append("[img]")
    builder.setSpan(
        Builder("http://img.yo9.com/250c9dc09fbc11e59d8700163e00043c")
            .setLayout(150, 150)
            .build(),
        start, builder.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
    )
    builder.append("www")
    builder.append("\n\n")
    start = builder.length
    builder.append("[emotion:tv_cheers]")
    var span = Builder("http://static.yo9.com/web/emotions/tv_cheers.png")
        .build()
    builder.setSpan(span, start, builder.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
    builder.append("bilibili- ( ゜- ゜)つロ 乾杯~\n")
    builder.append("why I so diao")
    start = builder.length
    builder.append("[img]")
    var placeHolder: Drawable? = ColorDrawable(Color.RED)
    span = Builder("http://img.yo9.com/c82aa6c003d311e6ac3c00163e000cde@320w_720h.jpg")
        .setLayout(360, 720)
        .setPlaceHolderImage(placeHolder)
        .build()
    builder.setSpan(span, start, builder.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
    builder.append("sad sad")
    start = builder.length
    builder.append("[emotion:tv_sad]")
    placeHolder = resources.getDrawable(R.mipmap.ic_launcher)
    span = Builder("http://static.yo9.com/web/emotions/tv_sad.png")
        .setLayout(150, 150)
        .setPlaceHolderImage(placeHolder)
        .build()
    builder.setSpan(span, start, builder.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
    builder.append("\n\n")
    builder.append("This is a gif, margin=10:")
    start = builder.length
    builder.append("[gif:d559f520246811e69a4a00163e000cdb]")
    placeHolder = ColorDrawable(Color.BLUE)
    builder.setSpan(
        Builder("http://img.yo9.com/d559f520246811e69a4a00163e000cdb")
            .setPlaceHolderImage(placeHolder)
            .setLayout(200, 197)
            .setMargin(10)
            .setShowAnimaImmediately(true)
            .build(),
        start, builder.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
    )
    builder.append("\nDisabled animation")
    start = builder.length
    builder.append("[gif:d559f520246811e69a4a00163e000cdb]")
    placeHolder = ColorDrawable(Color.BLUE)
    builder.setSpan(
        Builder("http://img.yo9.com/d559f520246811e69a4a00163e000cdb")
            .setPlaceHolderImage(placeHolder)
            .setLayout(200, 197)
            .setShowAnimaImmediately(false)
            .build(),
        start, builder.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
    )
    return builder
  }

  fun buildText2(): CharSequence? {
    val builder = SpannableStringBuilder()
    builder.append("Reset text in same DraweeTextView~~~~")
    var start = builder.length
    builder.append("[img]")
    builder.setSpan(
        Builder("http://img.yo9.com/24fe1ed09fbc11e59d8700163e00043c")
            .setLayout(50, 50)
            .build(),
        start, builder.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
    )
    builder.append("\n\n")
    builder.append("This is a gif, margin=10:")
    start = builder.length
    builder.append("[gif:d559f520246811e69a4a00163e000cdb]")
    val placeHolder: Drawable = ColorDrawable(Color.BLUE)
    builder.setSpan(
        Builder("http://img.yo9.com/d559f520246811e69a4a00163e000cdb")
            .setPlaceHolderImage(placeHolder)
            .setLayout(200, 197)
            .setMargin(10)
            .setShowAnimaImmediately(true)
            .build(),
        start, builder.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
    )
    builder.append("\n\n")
    start = builder.length
    builder.append("[emotion:tv_cheers]")
    builder.setSpan(
        Builder("http://static.yo9.com/web/emotions/tv_cheers.png").build()
        , start, builder.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
    )
    builder.append("bilibili- ( ゜- ゜)つロ 乾杯~\n")
    return builder
  }

}