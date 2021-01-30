package com.example.testdemo.ui

import android.os.Bundle
import android.view.View
import com.example.testdemo.R
import com.example.testdemo.base.BaseFragment
import com.example.testdemo.view.litepager.LitePager.STATE_DRAGGING_BOTTOM
import com.example.testdemo.view.litepager.LitePager.STATE_DRAGGING_LEFT
import com.example.testdemo.view.litepager.LitePager.STATE_DRAGGING_RIGHT
import com.example.testdemo.view.litepager.LitePager.STATE_DRAGGING_TOP
import com.example.testdemo.view.litepager.LitePager.STATE_IDLE
import com.example.testdemo.view.litepager.LitePager.STATE_SETTLING_BOTTOM
import com.example.testdemo.view.litepager.LitePager.STATE_SETTLING_LEFT
import com.example.testdemo.view.litepager.LitePager.STATE_SETTLING_RIGHT
import com.example.testdemo.view.litepager.LitePager.STATE_SETTLING_TOP
import kotlinx.android.synthetic.main.fragment_five.litePager
import kotlinx.android.synthetic.main.fragment_five.status

class FiveFragment : BaseFragment() {




  override fun getLayoutId() = R.layout.fragment_five

  override fun initView(view: View) {
    initLitePager()
  }

  override fun initData(savedInstanceState: Bundle?) {

  }

  private fun initLitePager() {
    litePager.setOnItemSelectedListener { onItemSelected(it) }
    litePager.setOnScrollListener {
      status.text = getString(
          R.string.status, getString(
          when (it) {
            STATE_IDLE -> R.string.idle
            STATE_DRAGGING_LEFT -> R.string.dragging_left
            STATE_DRAGGING_RIGHT -> R.string.dragging_right
            STATE_DRAGGING_TOP -> R.string.dragging_top
            STATE_DRAGGING_BOTTOM -> R.string.dragging_bottom
            STATE_SETTLING_LEFT -> R.string.settling_left
            STATE_SETTLING_RIGHT -> R.string.settling_right
            STATE_SETTLING_TOP -> R.string.settling_top
            STATE_SETTLING_BOTTOM -> R.string.settling_bottom
            else -> return@setOnScrollListener
          }
      )
      )
    }

    litePager.addViews(
        R.layout.layout_card_view_0,
        R.layout.layout_card_view_1,
        R.layout.layout_card_view_2,
        R.layout.layout_card_view_3,
        R.layout.layout_card_view_4
    )
  }

  private fun onItemSelected(view: View) {

  }
}