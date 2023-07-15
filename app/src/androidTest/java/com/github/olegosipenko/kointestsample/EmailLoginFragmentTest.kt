package com.github.olegosipenko.kointestsample

import androidx.compose.ui.test.SemanticsNodeInteractionsProvider
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.quickbird.snapshot.AndroidFileSnapshotTest
import com.quickbird.snapshot.FileSnapshotting
import com.quickbird.snapshot.composeScreenshot
import dagger.hilt.android.testing.BindValue
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import io.github.kakaocup.compose.node.element.ComposeScreen
import io.github.kakaocup.compose.node.element.ComposeScreen.Companion.onComposeScreen
import io.github.kakaocup.compose.node.element.KNode
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@HiltAndroidTest
@RunWith(AndroidJUnit4ClassRunner::class)
class EmailLoginFragmentTest : AndroidFileSnapshotTest() {
    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @BindValue
    @JvmField
    val fragmentViewModel: EmailLoginFragmentViewModel = mockk(relaxed = true)

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun testBasicInvocation() {
        onComposeScreen<EmailLoginForm>(composeTestRule) {
            runTest {
                FileSnapshotting.composeScreenshot
                    .snapshotToFilesDir(composeTestRule, record = true)
            }
            emailField { performTextInput(EMAIL) }
            passwordField { performTextInput(PASSWORD) }
            loginButton { performClick() }

            verify {
                fragmentViewModel.loginWithCredentials(EMAIL, PASSWORD)
            }
        }
    }

    class EmailLoginForm(
        semanticsProvider: SemanticsNodeInteractionsProvider
    ) : ComposeScreen<EmailLoginForm>(semanticsProvider) {
        val emailField: KNode = child { hasTestTag("email-field") }
        val passwordField: KNode = child { hasTestTag("password-field") }
        val loginButton: KNode = child { hasTestTag("button") }
    }
}

private const val EMAIL = "some@email.com"
private const val PASSWORD = "password"