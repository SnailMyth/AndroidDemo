package com.example.testdemo.ui

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.fragment.NavHostFragment
import com.example.testdemo.R
import com.example.testdemo.base.BaseFragment

class MainFragment : BaseFragment() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
  }

  override fun initData(savedInstanceState: Bundle?) {

  }

  override fun getLayoutId() = R.layout.fragment_main

  override fun initView(view: View) {
    view.findViewById<Button>(R.id.bt1)
        .setOnClickListener {
          show(R.id.action_to_First)
        }
    view.findViewById<Button>(R.id.bt2)
        .setOnClickListener {
          show(R.id.action_to_Second)
        }
    view.findViewById<Button>(R.id.bt3)
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
  }


  private fun show(id: Int) {
    NavHostFragment.findNavController(this@MainFragment)
        .navigate(id)
  }
}