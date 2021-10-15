package com.example.testdemo.ui;

import android.app.role.RoleManager;
import android.os.Bundle;
import android.telephony.CellInfo;
import android.telephony.PhoneStateListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.fragment.NavHostFragment;
import com.example.testdemo.R;
import com.example.testdemo.base.BaseFragment;
import com.example.testdemo.base.CommonNetWork;
import com.example.testdemo.base.Resource;
import com.example.testdemo.data.ArticleBean;
import com.example.testdemo.utils.LogUtil;
import com.example.testdemo.view.MvpAnimView;
import com.example.testdemo.view.RangeBar;
import java.util.List;
import org.jetbrains.annotations.NotNull;

public class FirstFragment extends BaseFragment {

  private RangeBar mRangeBar;
  private Button   bt;


  @Override
  protected void initView(@NotNull View view) {
    mRangeBar = (RangeBar) view.findViewById(R.id.rangeBar);
    mRangeBar.setOnRangeSelectedListener(new RangeBar.OnRangeSelectedListener() {
      @Override
      public void onRangeSelected(int left, int right) {
        Toast.makeText(getContext(), "left= " + left + " right= " + right, Toast.LENGTH_SHORT)
            .show();
      }
    });
    view.findViewById(R.id.button_res).setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        mRangeBar.setRange(0, 6);
      }
    });

    initAnim();
    MvpAnimView mvpAnimView1 = view.findViewById(R.id.mvp_view1);
    //MvpAnimView mvpAnimView2 = view.findViewById(R.id.mvp_view2);
    mvpAnimView1.play();
    //mvpAnimView2.play();
  }

  @Override
  protected void initData(Bundle savedInstanceState) {

  }

  @Override
  protected int getLayoutId() {
    return R.layout.fragment_first;
  }

  private void initAnim() {

  }
}