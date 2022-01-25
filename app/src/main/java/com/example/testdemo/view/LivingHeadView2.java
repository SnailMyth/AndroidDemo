package com.example.testdemo.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.blankj.utilcode.util.SizeUtils;
import com.example.testdemo.R;
import com.example.testdemo.utils.GetResourceUtil;
import com.example.testdemo.utils.SizeUtil;

/**
 * @author : myth_hai
 * @date : 2021/12/24
 * @time : 11:43
 * @description :
 */
public class LivingHeadView2 extends ConstraintLayout {

  public LivingHeadView2(Context context) {
    this(context, null);
  }

  public LivingHeadView2(Context context, AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public LivingHeadView2(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init(context, attrs);
  }

  private SoundSpectrumLinearView   mLiveView;
  private LinearSemicircleAlphaView mBottomView;
  private ViewGroup                 mHeadView;
  private boolean                   mIsLiving = true;

  private void init(Context context, AttributeSet attrs) {
    TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.LivingHeadView2);

    int waveHeight =
        typedArray.getDimensionPixelOffset(R.styleable.LivingHeadView2_living_wave_height,
            0);

    int headSize =
        typedArray.getDimensionPixelOffset(R.styleable.LivingHeadView2_lv_head_size,
            SizeUtils.dp2px(40));

    if (waveHeight == 0) {
      waveHeight = headSize / 2;
    }


    typedArray.recycle();
    mHeadView = new FrameLayout(context);
    ConstraintLayout.LayoutParams headLayoutParams =
        new ConstraintLayout.LayoutParams(headSize, headSize);
    headLayoutParams.topToTop = LayoutParams.PARENT_ID;
    headLayoutParams.bottomToBottom = LayoutParams.PARENT_ID;
    headLayoutParams.startToStart = LayoutParams.PARENT_ID;
    headLayoutParams.endToEnd = LayoutParams.PARENT_ID;
    addView(mHeadView, headLayoutParams);

    //底部背景
    mBottomView = new LinearSemicircleAlphaView(context);
    ConstraintLayout.LayoutParams layoutParamsView =
        new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_PARENT, waveHeight);
    layoutParamsView.bottomToBottom = LayoutParams.PARENT_ID;
    addView(mBottomView, layoutParamsView);

    //底部波浪
    mLiveView = new SoundSpectrumLinearView(context);
    ConstraintLayout.LayoutParams layoutParamsLiving =
        new ConstraintLayout.LayoutParams(SizeUtil.dip2px(getContext(),20), SizeUtil.dip2px(getContext(),24));
    mLiveView.setPadding(0, SizeUtil.dip2px(getContext(),6), 0, SizeUtil.dip2px(getContext(),6));
    layoutParamsLiving.bottomToBottom = LayoutParams.PARENT_ID;
    layoutParamsLiving.startToStart = LayoutParams.PARENT_ID;
    layoutParamsLiving.endToEnd = LayoutParams.PARENT_ID;
    addView(mLiveView, layoutParamsLiving);
  }

  /**
   * @param isLiving 是否正在直播
   */
  public void setLiving(boolean isLiving) {
    if (mIsLiving == isLiving) {
      return;
    }
    mIsLiving = isLiving;
    if (isLiving) {
      if (mLiveView != null) {
        mLiveView.setVisibility(VISIBLE);
        mLiveView.startPlay();
      }

      if (mBottomView != null) {
        mBottomView.setVisibility(VISIBLE);
      }
    } else {
      if (mLiveView != null) {
        mLiveView.setVisibility(GONE);
        mLiveView.stopPlay();
      }

      if (mBottomView != null) {
        mBottomView.setVisibility(GONE);
      }
    }
  }

  public void setHeadImg(String headImg) {
    setHeadImg(headImg, false);
  }

  public void setHeadImg(String headImg, boolean isShowViolationIcon) {
    setHeadImg(headImg, "", "", isShowViolationIcon);
  }

  public void setHeadImg(String headUrl, String pendantUrl, String pendantPath,
      boolean isShowViolationIcon) {
    if (mHeadView != null) {
      mHeadView.setBackgroundColor(GetResourceUtil.getColor(R.color.light_blue_900));
    }
  }

  @Override
  protected void onDetachedFromWindow() {
    super.onDetachedFromWindow();
  }

  @Override
  protected void onAttachedToWindow() {
    super.onAttachedToWindow();
    if (mIsLiving) {
      setLiving(true);
    }
  }
}
