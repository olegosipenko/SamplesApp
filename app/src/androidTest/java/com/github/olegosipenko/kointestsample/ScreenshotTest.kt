package com.github.olegosipenko.kointestsample

import com.agoda.kakao.screen.Screen.Companion.onScreen
import com.github.olegosipenko.kointestsample.bootstrap.createRule
import com.karumi.shot.ScreenshotTest
import io.mockk.mockk
import org.junit.Rule
import org.junit.Test
import org.koin.dsl.module

class ScreenshotTest: ScreenshotTest {
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
  fun testScreenshot() {
//    fragmentRule.launch()

//    Thread.sleep(1000L)

    onScreen<EmailLoginFragmentTest.EmailLoginForm> {
      compareScreenshot(fragmentRule.activity)
    }
  }
}