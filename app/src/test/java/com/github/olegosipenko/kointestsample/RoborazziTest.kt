package com.github.olegosipenko.kointestsample

import android.view.View
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.github.takahirom.roborazzi.captureRoboImage
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.GraphicsMode

@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
class RoborazziTest {
    @get:Rule(order = 0)
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun testRoborazzi() {
        activityRule.scenario.onActivity {
            it.findViewById<View>(android.R.id.content)
                .captureRoboImage("src/test/snapshots/images/test.png")
        }
    }
}
