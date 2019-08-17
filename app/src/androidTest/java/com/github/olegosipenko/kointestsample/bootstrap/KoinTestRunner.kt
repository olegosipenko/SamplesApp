package com.github.olegosipenko.kointestsample.bootstrap

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner

class KoinTestRunner: AndroidJUnitRunner() {
  override fun newApplication(
    cl: ClassLoader?, className: String?, context: Context?
  ): Application {
    return super.newApplication(
      cl, KoinTestApp::class.java.name, context
    )
  }
}