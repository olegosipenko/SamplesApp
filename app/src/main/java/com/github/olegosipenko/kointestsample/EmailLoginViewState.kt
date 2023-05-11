package com.github.olegosipenko.kointestsample

sealed class EmailLoginViewState {
  object INITIAL: EmailLoginViewState()
  object LOADING: EmailLoginViewState()
  object SUCCESS: EmailLoginViewState()
}
