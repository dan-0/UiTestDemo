package com.example.testingdemo.ui.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.testingdemo.ui.screens.shared.HomeButton

@Composable
fun HomeScreen(eventHandler: (HomeEvent) -> Unit) {
  Column(
    modifier = Modifier.fillMaxSize(),
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Center
  ) {
    HomeButton(buttonText = "User Card") {
      eventHandler(HomeEvent.GoToUserCard)
    }
    HomeButton(buttonText = "Interop User Card") {
      eventHandler(HomeEvent.GoToInteropUserCard)
    }
    HomeButton(buttonText = "User List") {
      eventHandler(HomeEvent.GoToUserList)
    }
  }
}

@Preview(showBackground = true)
@Composable
private fun PreviewHomeScreen() {
  HomeScreen { }
}

sealed class HomeEvent {
  object GoToUserCard : HomeEvent()
  object GoToInteropUserCard : HomeEvent()
  object GoToUserList : HomeEvent()
}