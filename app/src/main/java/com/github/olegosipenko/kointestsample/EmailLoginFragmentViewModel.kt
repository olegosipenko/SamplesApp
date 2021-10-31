package com.github.olegosipenko.kointestsample

import android.util.Log
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EmailLoginFragmentViewModel @Inject constructor() : ViewModel() {
    fun loginWithCredentials(email: String, password: String) {
        Log.d(this.javaClass.name, "login with:$email, $password")
    }
}
