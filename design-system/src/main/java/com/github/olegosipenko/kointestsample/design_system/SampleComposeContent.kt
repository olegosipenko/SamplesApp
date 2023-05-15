package com.github.olegosipenko.kointestsample.design_system

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun SampleComposeContent(
    modifier: Modifier = Modifier
) {
    LazyColumn {
        item {
            Button(onClick = {}) {
                Text(text = "sample button")
            }
            Button(onClick = {}) {
                Text(text = "second button")
            }
        }
    }
}

@Preview
@Composable
fun PreviewContent() {
    SampleComposeContent()
}