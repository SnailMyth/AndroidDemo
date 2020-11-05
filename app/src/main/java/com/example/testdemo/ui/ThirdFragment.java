package com.example.testdemo.ui;

import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.blankj.utilcode.util.SpanUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.example.testdemo.R;
import com.example.testdemo.view.layout.DragConstraintLayout;

public class ThirdFragment extends Fragment {

  public static final String               TAG = "ThirdFragment";
  private             TextView             mTextView2;
  private             DragConstraintLayout mLayout;

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
    mLayout.setDragView(mTextView2);
    mTextView2.setOnClickListener(v -> {

    });
  }
}