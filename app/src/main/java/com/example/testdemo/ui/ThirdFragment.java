package com.example.testdemo.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.example.testdemo.R;
import com.example.testdemo.utils.CustomCountDownTimer;
import com.example.testdemo.view.RingProgressBar;
import com.example.testdemo.view.layout.DragConstraintLayout;

public class ThirdFragment extends Fragment implements CustomCountDownTimer.OnCountDownListener {

  public static final String               TAG = "ThirdFragment";
  private             TextView             mTextView2;
  private             DragConstraintLayout mLayout;
  private             RingProgressBar      mRpb, mRpb1, mRpb2, mRpb3;
  private CustomCountDownTimer customCountDownTimer;

  @Override
  public View onCreateView(
      LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState
  ) {

    return inflater.inflate(R.layout.fragment_third, container, false);
  }

  public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    mTextView2 = view.findViewById(R.id.temp_tv_2);
    mLayout = view.findViewById(R.id.temp_layout_1);
    mRpb = view.findViewById(R.id.rpb);
    mRpb1 = view.findViewById(R.id.rpb1);
    mRpb1.setProgress(50);
    mRpb2 = view.findViewById(R.id.rpb2);
    mRpb2.setProgress(75);
    mRpb3 = view.findViewById(R.id.rpb3);
    mRpb3.setProgress(98);
    //mLayout.setDragView(mTextView2);
    mTextView2.setOnClickListener(v -> {

    });

    customCountDownTimer = new CustomCountDownTimer(10000, 100);
    mRpb.setMax(10000);
    customCountDownTimer.setOnCountDownListener(this);
    customCountDownTimer.start();
  }

  @Override
  public void onTick(long time) {
    int process = (int) (10000 - time);
    Log.d(TAG, "onTick: time->" + time + ",process->" + process);
    mRpb.setProgress(process);
  }

  @Override
  public void onFinish() {

  }

  @Override
  public void onDestroy() {
    if (null != customCountDownTimer) {
      customCountDownTimer.cancel();
      customCountDownTimer = null;
    }
    super.onDestroy();
  }
}