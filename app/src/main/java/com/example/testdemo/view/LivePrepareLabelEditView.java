package com.example.testdemo.view;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.example.testdemo.R;
import com.example.testdemo.utils.SizeUtil;

public class LivePrepareLabelEditView extends FrameLayout {

  private              TextView mTextView;
  private static final String   TAG = "LivePrepareLabelEditView";

  public LivePrepareLabelEditView(Context context) {
    super(context);
    init(context, null);
  }

  public LivePrepareLabelEditView(Context context, AttributeSet attrs) {
    super(context, attrs);
    init(context, attrs);
  }

  public LivePrepareLabelEditView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init(context, attrs);
  }

  private void init(Context context, AttributeSet attrs) {
    setWillNotDraw(false);
    mTextView = new TextView(getContext());
    FrameLayout.LayoutParams params =
        new LayoutParams(SizeUtil.dip2px(getContext(), 134), SizeUtil.dip2px(getContext(), 40));;
    mTextView.setText("拖至此区域删除");
    mTextView.setGravity(Gravity.CENTER);
    mTextView.setTextColor(Color.WHITE);
    mTextView.setBackgroundResource(R.drawable.bg_8white_20cir);
    addView(mTextView,params);
  }


  @Override protected void onLayout(boolean changed, int l, int t, int r, int b) {
    if (mTextView != null) {
      mTextView.layout(100, 200, 100 + SizeUtil.dip2px(getContext(), 134),
          200 + SizeUtil.dip2px(getContext(), 40));
    }
  }
}
