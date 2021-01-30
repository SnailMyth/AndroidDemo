package com.example.testdemo.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.testdemo.data.UserBean;
import org.jetbrains.annotations.NotNull;

public class ThirdAdapter extends BaseQuickAdapter<UserBean, BaseViewHolder> {

  public ThirdAdapter(int layoutResId) {
    super(layoutResId);
  }

  @Override
  protected void convert(@NotNull BaseViewHolder baseViewHolder, UserBean userBean) {

  }
}
