package com.github.olegosipenko.kointestsample

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class KoinApp: Application() {
  override fun onCreate() {
    super.onCreate()
  }
}
