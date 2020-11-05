package com.example.testdemo.ui;

import android.os.Bundle;
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
import com.example.testdemo.view.RangeBar;

public class FirstFragment extends Fragment {

  private RangeBar mRangeBar;
  private Button   bt;

  @Override
  public View onCreateView(
      LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState
  ) {
    // Inflate the layout for this fragment
    return inflater.inflate(R.layout.fragment_first, container, false);
  }

  public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    bt = view.findViewById(R.id.button_first);
    bt.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        NavHostFragment.findNavController(FirstFragment.this)
            .navigate(R.id.action_FirstFragment_to_SecondFragment);
      }
    });

    mRangeBar = (RangeBar) view.findViewById(R.id.rangeBar);
    mRangeBar.setOnRangeSelectedListener(new RangeBar.OnRangeSelectedListener() {
      @Override
      public void onRangeSelected(int left, int right) {
        Toast.makeText(getContext(), "left= " + left + " right= " + right, Toast.LENGTH_SHORT)
            .show();
      }
    });
    view.findViewById(R.id.button_res).setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {
        mRangeBar.setRange(0, 6);
      }
    });

    initAnim();
  }

  private void initAnim() {

  }
}