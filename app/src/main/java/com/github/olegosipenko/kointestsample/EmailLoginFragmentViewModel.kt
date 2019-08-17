package com.github.olegosipenko.kointestsample

import android.util.Log
import androidx.lifecycle.ViewModel

class EmailLoginFragmentViewModel: ViewModel() {
  fun loginWithCredentials(email: String, password: String) {
    Log.d(this.javaClass.name, "login with:$email, $password")
  }
}