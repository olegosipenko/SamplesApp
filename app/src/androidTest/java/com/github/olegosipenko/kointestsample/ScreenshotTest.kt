package com.github.olegosipenko.kointestsample

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.karumi.shot.ScreenshotTest
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import io.github.kakaocup.compose.node.element.ComposeScreen
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@HiltAndroidTest
@RunWith(AndroidJUnit4ClassRunner::class)
class ScreenshotTest : ScreenshotTest {
    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun testScreenshot() {
        ComposeScreen.onComposeScreen<EmailLoginFragmentTest.EmailLoginForm>(composeTestRule) {
            compareScreenshot(composeTestRule.activity)
        }
    }
}
