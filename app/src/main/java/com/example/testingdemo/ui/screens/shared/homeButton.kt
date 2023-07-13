package com.example.testingdemo.ui.screens.shared

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun HomeButton(
  buttonText: String,
  eventHandler: () -> Unit,
) {
  Button(
    onClick = {
      eventHandler()
    },
    modifier = Modifier.testTag("buttonUserCard")
  ) {
    Text(
      text = buttonText,
      modifier = Modifier.testTag("buttonTextUserCard")
    )
  }
}

@Preview
@Composable
private fun PreviewHomeButton() {
  HomeButton(buttonText = "Click me") {}
}