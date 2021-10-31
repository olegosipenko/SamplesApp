package com.github.olegosipenko.kointestsample

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowInsetsController
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.WindowCompat

class MainActivity: AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    WindowCompat.setDecorFitsSystemWindows(window, false)
    window.statusBarColor = Color.TRANSPARENT
    window.setStatusBarDarkIcons(true)
  }

  @Suppress("DEPRECATION")
  fun Window.setStatusBarDarkIcons(dark: Boolean) {
    when {
      Build.VERSION_CODES.R <= Build.VERSION.SDK_INT -> insetsController?.setSystemBarsAppearance(
        if (dark) WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS else 0,
        WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
      )
      Build.VERSION_CODES.M <= Build.VERSION.SDK_INT -> decorView.systemUiVisibility = if (dark) {
        decorView.systemUiVisibility or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
      } else {
        decorView.systemUiVisibility and View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv()
      }
      else -> if (dark) {
        // dark status bar icons not supported on API level below 23, set status bar
        // color to black to keep icons visible
        statusBarColor = Color.BLACK
      }
    }
  }
}
