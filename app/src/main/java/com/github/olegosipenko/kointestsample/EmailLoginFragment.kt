package com.github.olegosipenko.kointestsample

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.github.olegosipenko.kointestsample.databinding.FragmentEmailLoginBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EmailLoginFragment: Fragment() {

  private val fragmentViewModel: EmailLoginFragmentViewModel by viewModels()
  private var _viewBinding: FragmentEmailLoginBinding? = null
  private val viewBinding get() = requireNotNull(_viewBinding)

  override fun onCreateView(inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    _viewBinding = FragmentEmailLoginBinding.inflate(inflater, container, false)
    return viewBinding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    viewBinding.composeView.apply {
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

  private fun onLoginClick(email: String, password: String) {
    fragmentViewModel.loginWithCredentials(email, password)
  }

  override fun onDestroyView() {
    super.onDestroyView()
    _viewBinding = null
  }
}
