package com.example.testdemo.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

import com.example.testdemo.R;
import org.jetbrains.annotations.NotNull;

public class StringAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
  public StringAdapter() {
    super(R.layout.adapter_item_str);
  }

  @Override
  protected void convert(@NotNull BaseViewHolder baseViewHolder, String s) {
    baseViewHolder.setText(R.id.item_string_tv, s);
  }
}
