package com.github.olegosipenko.kointestsample

import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.agoda.kakao.edit.KEditText
import com.agoda.kakao.screen.Screen
import com.agoda.kakao.screen.Screen.Companion.onScreen
import com.agoda.kakao.text.KButton
import com.github.olegosipenko.kointestsample.bootstrap.createRule
import io.mockk.mockk
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.dsl.module

@RunWith(AndroidJUnit4ClassRunner::class)
class EmailLoginFragmentTest {

  private val fragmentViewModel: EmailLoginFragmentViewModel =
    mockk(relaxed = true)
  private val fragment = EmailLoginFragment()

  @get:Rule
  val fragmentRule = createRule(fragment, module {
    single(override = true) {
      fragmentViewModel
    }
  })

  @Test
  fun testBasicInvocation() {
    onScreen<EmailLoginForm> {

    }
  }
  class EmailLoginForm: Screen<EmailLoginForm>() {
    val emailField = KEditText { withId(R.id.textFieldEmail) }
    val passwordField = KEditText { withId(R.id.textFieldPassword) }
    val loginButton = KButton { withId(R.id.buttonLogin) }
  }
}

private const val EMAIL = "some@email.com"
private const val PASSWORD = "password"