package com.github.olegosipenko.kointestsample

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension

@Composable
fun EmailLoginFragmentView(clickListener: (String, String) -> Unit) {
    var emailState by remember { mutableStateOf(TextFieldValue()) }
    var passwordState by remember { mutableStateOf(TextFieldValue()) }
    ConstraintLayout(
        modifier = Modifier.fillMaxSize()
    ) {
        val (email, password, button) = createRefs()

        createVerticalChain(email, password, button, chainStyle = ChainStyle.Packed)

        Image(
            painter = painterResource(id = R.drawable.scenic),
            contentDescription = "scenery background",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            Color.White
                        )
                    )
                )
        )
        TextField(
            modifier = Modifier
                .constrainAs(email) {
                    top.linkTo(parent.top)
                    bottom.linkTo(password.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .semantics { testTag = "email-field" },
            label = {
                Text(text = "E-mail")
            },
            value = emailState,
            onValueChange = {
                emailState = it
            }
        )
        TextField(
            modifier = Modifier
                .constrainAs(password) {
                    top.linkTo(email.bottom)
                    bottom.linkTo(button.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .semantics { testTag = "password-field" },
            label = {
                Text(text = "password")
            },
            value = passwordState,
            onValueChange = {
                passwordState = it
            }
        )
        Button(
            modifier = Modifier
                .constrainAs(button) {
                    width = Dimension.fillToConstraints
                    start.linkTo(email.start)
                    end.linkTo(email.end)
                    top.linkTo(password.bottom)
                    bottom.linkTo(parent.bottom)
                }
                .semantics { testTag = "button" },
            onClick = {
                clickListener.invoke(
                    emailState.text, passwordState.text
                )
            }
        ) {
            Text(text = "Login")
        }
    }
}

@Preview
@Composable
fun PreviewFragment() {
    EmailLoginFragmentView { _, _ -> }
}
