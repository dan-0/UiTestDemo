package com.example.testingdemo.ui.screens.usercard

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.testingdemo.data.UserData

@Composable
fun UserCardScreen(userData: UserData) {
  Card(
    modifier = Modifier.padding(16.dp),
  ) {
    Column(
      modifier = Modifier.padding(8.dp).fillMaxWidth(),
    ) {
      AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
          .data(userData.userImageUrl)
          .build(),
        contentDescription = "User profile photo for ${userData.userName}",
        modifier = Modifier
          .heightIn(max = 200.dp)
          .testTag("userCardImage")
      )
      Column {
        Text(
          text = "User name:",
          style = MaterialTheme.typography.titleSmall,
          modifier = Modifier.testTag("userCardNameLabel")
        )
        Text(
          text = userData.userName,
          style = MaterialTheme.typography.bodyLarge,
          modifier = Modifier.testTag("userCardNameValue")
        )
      }
      Column {
        Text(
          text = "Species:",
          style = MaterialTheme.typography.titleSmall,
          modifier = Modifier.testTag("userCardSpeciesLabel")
        )
        Text(
          text = userData.species,
          style = MaterialTheme.typography.bodyLarge,
          modifier = Modifier.testTag("userCardSpeciesValue")
        )
      }
    }
  }
}