package com.example.testdemo.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.blankj.utilcode.util.SizeUtils
import com.example.testdemo.R
import com.example.testdemo.adapter.FourAdapter
import com.example.testdemo.anim.ScaleInTransformer
import com.example.testdemo.base.BaseFragment
import com.example.testdemo.utils.SizeUtil

class FourFragment : BaseFragment() {
  private lateinit var mViewPager: ViewPager2
  override fun initData(savedInstanceState: Bundle?) {

  }

  private lateinit var mAdapter: FourAdapter

  override fun getLayoutId(): Int =
    R.layout.fragment_four

  override fun initView(view: View) {
    mViewPager = view.findViewById(R.id.view_pager)
    mAdapter = FourAdapter(mutableListOf(1, 2, 3, 4))
    mViewPager.adapter = mAdapter
    mViewPager.apply {
      offscreenPageLimit=1
      val recyclerView= getChildAt(0) as RecyclerView
      recyclerView.apply {
        val padding = resources.getDimensionPixelOffset(R.dimen.dp_20) +
            resources.getDimensionPixelOffset(R.dimen.dp_20)
        setPadding(0, 0, padding, 0)
        clipToPadding = false
      }
    }

    val compositePageTransformer = CompositePageTransformer()
    compositePageTransformer.addTransformer(ScaleInTransformer())
    compositePageTransformer.addTransformer(MarginPageTransformer(resources.getDimension(R.dimen.dp_10).toInt()))
    mViewPager.setPageTransformer(compositePageTransformer)
    mViewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
      override fun onPageSelected(position: Int) {

      }
    })
  }

  companion object {
    @JvmStatic fun newInstance() =
      FourFragment()
  }
}