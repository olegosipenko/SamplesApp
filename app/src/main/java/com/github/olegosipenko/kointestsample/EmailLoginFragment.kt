package com.github.olegosipenko.kointestsample

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_email_login.composeView

@AndroidEntryPoint
class EmailLoginFragment: Fragment() {

  private val fragmentViewModel: EmailLoginFragmentViewModel by viewModels()

  override fun onCreateView(inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? = inflater.inflate(R.layout.fragment_email_login, container, false)

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    composeView.apply {
      setViewCompositionStrategy(
        ViewCompositionStrategy.DisposeOnLifecycleDestroyed(viewLifecycleOwner)
      )
      setContent {
        MaterialTheme {
          EmailLoginFragmentView(::onLoginClick)
        }
      }
    }
  }

  fun onLoginClick(email: String, password: String) {
    fragmentViewModel.loginWithCredentials(email, password)
  }

  fun render(viewState: EmailLoginViewState) {
    when(viewState) {
      EmailLoginViewState.INITIAL -> renderInitial()
    }
  }

  private fun renderInitial() {
    // No op
  }
}
