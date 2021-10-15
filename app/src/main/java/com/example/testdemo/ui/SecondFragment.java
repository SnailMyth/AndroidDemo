package com.example.testdemo.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.view.WindowInsetsAnimation;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.slidingpanelayout.widget.SlidingPaneLayout;
import com.example.testdemo.view.GradientProgressView;
import com.example.testdemo.R;

public class SecondFragment extends Fragment {

  private SlidingPaneLayout sLayout;
  private View mLeftView;
  private View mMainView;

  @Override
  public View onCreateView(
      LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState
  ) {
    // Inflate the layout for this fragment
    return inflater.inflate(R.layout.fragment_second, container, false);
  }

  public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    initSlide(view);
    GradientProgressView pb = view.findViewById(R.id.textview_second);
    //GradientProgressView pb2 = view.findViewById(R.id.textview_second_2);
    pb.setProgress(20);
  }

  private void initSlide(@NonNull View view) {
    sLayout = view.findViewById(R.id.sec_slide_layout);
    mLeftView = sLayout.getChildAt(0);
    mMainView = sLayout.getChildAt(1);
    sLayout.setPanelSlideListener(new SlidingPaneLayout.PanelSlideListener() {
      @Override
      public void onPanelSlide(@NonNull View panel, float slideOffset) {
        //滑动窗格的位置更改时调用
        //设置侧面栏缩放
        mLeftView.setPivotX(-mLeftView.getWidth() / 6.0f);
        mLeftView.setPivotY(mLeftView.getHeight() / 2.0f);
        mLeftView.setScaleX(0.7f + 0.3f * slideOffset);
        mLeftView.setScaleY(0.7f + 0.3f * slideOffset);

        mMainView.setPivotX(mMainView.getWidth());
        mMainView.setPivotY(mMainView.getHeight());

        mMainView.setScaleX(1f - 0.1f * slideOffset);
        mMainView.setScaleY(1f - 0.1f * slideOffset);

        //右边设置个阴影的效果
        mMainView.setElevation(6.0f * slideOffset);
      }

      @Override
      public void onPanelOpened(@NonNull View panel) {

      }

      @Override
      public void onPanelClosed(@NonNull View panel) {

      }
    });
  }
}