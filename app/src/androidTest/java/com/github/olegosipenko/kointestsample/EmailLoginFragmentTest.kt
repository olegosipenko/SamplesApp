package com.github.olegosipenko.kointestsample

import com.agoda.kakao.edit.KEditText
import com.agoda.kakao.screen.Screen
import com.agoda.kakao.text.KButton
import io.mockk.mockk

//@RunWith(AndroidJUnit4ClassRunner::class)
class EmailLoginFragmentTest {

  private val fragmentViewModel: EmailLoginFragmentViewModel =
    mockk(relaxed = true)
  private val fragment = EmailLoginFragment()

//  @get:Rule
//  val fragmentRule = createRule(fragment, module {
//    single(override = true) {
//      fragmentViewModel
//    }
//  })

//  @Test
//  fun testBasicInvocation() {
//    onScreen<EmailLoginForm> {
//      emailField { typeText(EMAIL) }
//      passwordField { typeText(PASSWORD) }
//      loginButton.click()
//      verify {
//        fragmentViewModel.loginWithCredentials(EMAIL, PASSWORD)
//      }
//    }
//  }
  class EmailLoginForm: Screen<EmailLoginForm>() {
    val emailField = KEditText { withId(R.id.textFieldEmail) }
    val passwordField = KEditText { withId(R.id.textFieldPassword) }
    val loginButton = KButton { withId(R.id.buttonLogin) }
  }
}

private const val EMAIL = "some@email.com"
private const val PASSWORD = "password"