package com.example.testdemo.ui

import android.app.Service
import android.os.Bundle
import android.telephony.CellInfo
import android.telephony.PhoneStateListener
import android.telephony.TelephonyManager
import android.view.View
import android.widget.TextView
import androidx.navigation.fragment.NavHostFragment
import com.blankj.utilcode.util.LogUtils
import com.example.testdemo.R
import com.example.testdemo.base.BaseFragment
import com.example.testdemo.utils.LogUtil
import com.example.testdemo.utils.PermissionManger

class MotionMainFragment : BaseFragment() {

  override fun initData(savedInstanceState: Bundle?) {

  }

  override fun getLayoutId(): Int = R.layout.fragment_motion_layout

  override fun initView(view: View) {

    view.findViewById<TextView>(R.id.motion_1)
      .setOnClickListener {
        show(R.id.action_to_Simple)
      }


  }

  private fun show(id: Int) {
    NavHostFragment.findNavController(this@MotionMainFragment)
      .navigate(id)
  }

}