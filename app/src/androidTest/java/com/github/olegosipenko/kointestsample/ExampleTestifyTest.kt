package com.github.olegosipenko.kointestsample

import androidx.test.ext.junit.runners.AndroidJUnit4
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dev.testify.ScreenshotRule
import dev.testify.TestifyFeatures
import dev.testify.annotation.ScreenshotInstrumentation
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class ExampleTestifyTest {
    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val screenshotRule = ScreenshotRule(MainActivity::class.java)

    @Before
    fun init() {
        TestifyFeatures.GenerateDiffs.setEnabled(true)
        hiltRule.inject()
    }

    @ScreenshotInstrumentation
    @Test
    fun testify() {
        screenshotRule.assertSame()
    }
}