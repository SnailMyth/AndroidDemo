package com.example.testdemo.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import org.jetbrains.annotations.NotNull;

public abstract class BaseFragment extends Fragment {

  protected Activity mActivity;
  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    if (getLayoutId() == -1) {
      return null;
    }
    return inflater.inflate(getLayoutId(), container, false);
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    initView(view);
    initData(savedInstanceState);
  }

  @Override
  public void onAttach(Context context) {
    super.onAttach(context);
    mActivity = (Activity) context;
  }

  protected abstract void initView(@NotNull View view);

  protected abstract void initData(Bundle savedInstanceState);

  protected abstract int getLayoutId();
}


