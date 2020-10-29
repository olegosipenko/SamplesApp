package com.github.olegosipenko.kointestsample.bootstrap

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.core.module.Module

class KoinTestApp: Application() {
  override fun onCreate() {
    super.onCreate()
    startKoin {
      androidLogger(Level.ERROR) // https://github.com/InsertKoinIO/koin/issues/871
      androidContext(this@KoinTestApp)
      modules(emptyList())
    }
  }

  internal fun injectModule(module: Module) {
    loadKoinModules(module)
  }
}