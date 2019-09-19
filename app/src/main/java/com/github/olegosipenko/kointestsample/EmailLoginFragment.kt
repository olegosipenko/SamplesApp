package com.github.olegosipenko.kointestsample

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_email_login.*
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.dsl.module

class EmailLoginFragment: Fragment() {

  private val fragmentViewModel: EmailLoginFragmentViewModel by viewModel()

  override fun onCreateView(inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? = inflater.inflate(R.layout.fragment_email_login, container, false)

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    buttonLogin.setOnClickListener {
      val email = textFieldEmail.text.trim().toString()
      val password = textFieldPassword.text.trim().toString()
      fragmentViewModel.loginWithCredentials(email, password)
    }
  }

  fun render(viewState: EmailLoginViewState) {
    when(viewState) {
      EmailLoginViewState.INITIAL -> renderInitial()
    }
  }

  private fun renderInitial() {
    // No op
  }

  companion object {
    val module = module {
      single {
        EmailLoginFragmentViewModel()
      }
    }
  }
}
