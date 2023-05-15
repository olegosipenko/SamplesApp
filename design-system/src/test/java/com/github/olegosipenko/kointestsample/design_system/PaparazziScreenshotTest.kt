package com.github.olegosipenko.kointestsample.design_system

import androidx.constraintlayout.widget.ConstraintLayout
import app.cash.paparazzi.DeviceConfig
import app.cash.paparazzi.Paparazzi
import com.android.resources.LayoutDirection
import com.android.resources.ScreenOrientation
import com.android.resources.UiMode
import org.junit.Rule
import org.junit.Test

class PaparazziScreenshotTest {
    @get:Rule
    val paparazzi = Paparazzi(
        deviceConfig = DeviceConfig.PIXEL_2.copy(
            orientation = ScreenOrientation.LANDSCAPE,
            uiMode = UiMode.TELEVISION,
            layoutDirection = LayoutDirection.RTL,
        ),
        theme = "android:Theme.Material.Light.NoActionBar"
    )

    @Test
    fun testXml() {
        val view = paparazzi.inflate<ConstraintLayout>(R.layout.xml_layout_sample)
        paparazzi.snapshot(view)
    }

    @Test
    fun testCompose() {
        paparazzi.snapshot {
            SampleComposeContent()
        }
    }
}
