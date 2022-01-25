package com.example.testdemo.ui;

import android.os.Bundle;
import android.view.View;
import com.example.testdemo.R;
import com.example.testdemo.base.BaseFragment;
import com.example.testdemo.view.LivingHeadView2;
import org.jetbrains.annotations.NotNull;

public class SixFragment extends BaseFragment implements View.OnClickListener {

  @Override
  protected void initView(@NotNull View view) {

  }

  @Override
  protected void initData(Bundle savedInstanceState) {

  }

  @Override
  protected int getLayoutId() {
    return R.layout.fragment_six;
  }

  @Override
  public void onClick(View view) {
    if (view.getId() == R.id.bt_start) {

    }
  }
}