package com.example.testdemo.ui.motion

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.telephony.TelephonyManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testdemo.R
import com.example.testdemo.adapter.StringAdapter
import com.example.testdemo.base.BaseFragment
import kotlin.reflect.jvm.internal.impl.protobuf.LazyStringArrayList

/**
 * An example full-screen fragment that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
class SimpleMotionFragment : BaseFragment() {


  var mAdapter: StringAdapter = StringAdapter()
  private lateinit var mRv:RecyclerView;

  override fun initData(savedInstanceState: Bundle?) {

    val temp:MutableList<String> = mutableListOf()
    for (i:Int in 0..40){
      temp.add("测试$i")
    }
    mAdapter.setNewInstance(temp)

    val telephonyManager = context?.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
  }

  override fun getLayoutId(): Int = R.layout.view_custom_charge


  override fun initView(view: View) {
//    mRv = view.findViewById(R.id.recyclerview)
//    mRv.layoutManager = LinearLayoutManager(context)
//    mRv.adapter = mAdapter
  }

}