package com.example.testdemo.adapter;

import android.graphics.Color;
import android.view.View;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.testdemo.R;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class FourAdapter extends BaseQuickAdapter<Integer, BaseViewHolder> {

  public FourAdapter(
      @Nullable List<Integer> data) {
    super(R.layout.item_page, data);
  }

  @Override
  protected void convert(@NotNull BaseViewHolder holder, Integer integer) {
    String[] colors = new String[] { "#CCFF99", "#41F1E5", "#8D41F1", "#FF99CC" };
    holder.setText(R.id.tvNum,integer.toString());

    View view = holder.getView(R.id.tvNum);
    view.setBackgroundColor(Color.parseColor(colors[integer - 1]));


  }
}
