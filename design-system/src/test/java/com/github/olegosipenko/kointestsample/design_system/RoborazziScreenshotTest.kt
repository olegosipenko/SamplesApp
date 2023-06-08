package com.github.olegosipenko.kointestsample.design_system

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onRoot
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.github.takahirom.roborazzi.RoborazziOptions
import com.github.takahirom.roborazzi.RoborazziRule
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.GraphicsMode

@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
class RoborazziScreenshotTest {
    @get:Rule(order = 0)
    val composeRule = createAndroidComposeRule<ComponentActivity>()

    @get:Rule(order = 1)
    val roborazzi = RoborazziRule(
        composeRule,
        composeRule.onRoot(),
        options = RoborazziRule.Options(
            roborazziOptions = RoborazziOptions(
                recordOptions = RoborazziOptions.RecordOptions(
                    applyDeviceCrop = true,
                    pixelBitConfig = RoborazziOptions.PixelBitConfig.Argb8888,
                )
            )
        )
    )

    @Test
    fun testComposable() {
        composeRule.setContent {
            SampleComposeContent()
        }
    }
}
