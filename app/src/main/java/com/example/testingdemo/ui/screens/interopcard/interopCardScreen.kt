package com.example.testingdemo.ui.screens.interopcard

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidViewBinding
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.testingdemo.data.UserData
import com.example.testingdemo.databinding.UserCardDataBinding

@Composable
fun InteropCardScreen(userData: UserData) {
  Card(
    modifier = Modifier.padding(16.dp),
  ) {
    Column(
      modifier = Modifier
        .padding(8.dp)
        .fillMaxWidth(),
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
      AndroidViewBinding(factory = UserCardDataBinding::inflate) {
        userCardNameValue.text = userData.userName
        userCardSpeciesValue.text = userData.species
      }
    }
  }
}