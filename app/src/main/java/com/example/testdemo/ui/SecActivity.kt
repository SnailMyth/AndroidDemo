package com.example.testdemo.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.testdemo.R
import kotlinx.android.synthetic.main.activity_sec.litePager
import com.example.testdemo.view.litepager.LitePager.*;
import kotlinx.android.synthetic.main.activity_sec.status

class SecActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_sec)
    val ivTemp = findViewById<ImageView>(R.id.temp_iv_1)
//    ivTemp.translationX = ScreenUtils.getScreenWidth(this).toFloat()
    ivTemp.setOnClickListener(View.OnClickListener {
      startActivity(Intent(this@SecActivity, MainActivity::class.java))
    })

    initLitePager()
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

  fun onItemSelected(view: View) {
//    toast(
//        "${(((view as ViewGroup).getChildAt(0) as ViewGroup)
//            .getChildAt(1) as TextView).text} selected"
//    )
  }

  fun toast(str: String) {
    Toast.makeText(this, str, Toast.LENGTH_SHORT)
        .show()
  }
}