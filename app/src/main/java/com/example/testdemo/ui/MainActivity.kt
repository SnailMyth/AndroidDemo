package com.example.testdemo.ui

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.testdemo.R
import com.example.testdemo.R.id
import com.example.testdemo.R.layout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(layout.activity_main)
    checkPermissions()
    val toolbar = findViewById<Toolbar>(id.toolbar)
    setSupportActionBar(toolbar)
    val fab = findViewById<FloatingActionButton>(id.fab)
    fab.setOnClickListener { view ->
      Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
          .setAction("Action", null)
          .show()
    }

  }

  private val PERMISSION_REQ_ID_RECORD_AUDIO = 22

  private fun checkPermissions() {
    if (checkSelfPermission(Manifest.permission.RECORD_AUDIO, PERMISSION_REQ_ID_RECORD_AUDIO)) {
      initAgoraEngineAndJoinChannel();
    }
  }

  private fun initAgoraEngineAndJoinChannel() {

  }

  fun checkSelfPermission(
    permission: String,
    requestCode: Int
  ): Boolean {
    if (ContextCompat.checkSelfPermission(
            this,
            permission
        )
        != PackageManager.PERMISSION_GRANTED
    ) {
      ActivityCompat.requestPermissions(
          this, arrayOf(permission),
          requestCode
      )
      return false
    }
    return true
  }


  override fun onCreateOptionsMenu(menu: Menu): Boolean {
    // Inflate the menu; this adds items to the action bar if it is present.
    menuInflater.inflate(R.menu.menu_main, menu)
    return true
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    val id = item.itemId
    return if (id == R.id.action_settings) {
      true
    } else super.onOptionsItemSelected(item)
  }
}