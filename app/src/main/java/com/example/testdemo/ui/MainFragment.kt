package com.example.testdemo.ui

import android.Manifest
import android.app.role.RoleManager
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build.VERSION
import android.os.Build.VERSION_CODES
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.navigation.fragment.NavHostFragment
import com.example.testdemo.R
import com.example.testdemo.base.BaseFragment
import com.example.testdemo.utils.AbsGradeUtil
import com.hjq.shape.view.ShapeTextView

class MainFragment : BaseFragment() {


  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
  }

  override fun initData(savedInstanceState: Bundle?) {
  }

  override fun getLayoutId() = R.layout.fragment_main

  override fun initView(view: View) {
    view.findViewById<ShapeTextView>(R.id.bt1)
        .setOnClickListener {
          show(R.id.action_to_First)
        }
    view.findViewById<ShapeTextView>(R.id.bt2)
        .setOnClickListener {
          show(R.id.action_to_Second)
        }
    view.findViewById<ShapeTextView>(R.id.bt3)
        .setOnClickListener {
          show(R.id.action_to_Third)
        }
    view.findViewById<Button>(R.id.bt4)
        .setOnClickListener {
          show(R.id.action_to_Four)
        }
    view.findViewById<Button>(R.id.bt5)
        .setOnClickListener {
          show(R.id.action_to_Five)
        }
    view.findViewById<Button>(R.id.bt6)
        .setOnClickListener {
          show(R.id.action_to_Six)
        }
    view.findViewById<Button>(R.id.bt7)
        .setOnClickListener {
          show(R.id.action_to_Motion)
        }
  }


  private fun show(id: Int) {
    NavHostFragment.findNavController(this@MainFragment)
        .navigate(id)
  }
}