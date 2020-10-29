package com.github.olegosipenko.kointestsample

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class KoinApp: Application() {
  override fun onCreate() {
    super.onCreate()
    startKoin {
      androidLogger(Level.ERROR) // https://github.com/InsertKoinIO/koin/issues/871
      androidContext(this@KoinApp)
      modules(
        listOf(
          EmailLoginFragment.module
        )
      )
    }
  }
}
