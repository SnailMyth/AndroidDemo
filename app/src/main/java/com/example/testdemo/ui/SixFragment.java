package com.example.testdemo.ui;

import android.animation.Animator;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import com.blankj.utilcode.util.SnackbarUtils;
import com.example.testdemo.R;
import com.example.testdemo.base.BaseFragment;
import com.example.testdemo.utils.GetResourceUtil;
import com.example.testdemo.utils.SizeUtil;
import com.example.testdemo.view.MvpAnimView;
import com.example.testdemo.view.RangeBar;
import com.google.android.material.circularreveal.CircularRevealFrameLayout;
import com.google.android.material.textfield.TextInputLayout;
import org.jetbrains.annotations.NotNull;

public class SixFragment extends BaseFragment {

  @Override
  protected void initView(@NotNull View view) {
    TextInputLayout inputLayout = view.findViewById(R.id.dialog_radio_theme_edit_input_layout);
  }

  @Override
  protected void initData(Bundle savedInstanceState) {

  }

  @Override
  protected int getLayoutId() {
    return R.layout.fragment_six;
  }

}