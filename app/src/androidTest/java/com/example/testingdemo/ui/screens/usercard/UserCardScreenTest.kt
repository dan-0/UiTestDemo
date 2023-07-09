package com.example.testingdemo.ui.screens.usercard

import androidx.compose.ui.test.assertContentDescriptionEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import com.example.testingdemo.data.UserData
import org.junit.Rule
import org.junit.Test

class UserCardScreenTest {
  @get:Rule
  val composeTestRule = createComposeRule()

  @Test
  fun displayed() {
    val userData = UserData(
      userId = 0,
      userName = "Dan Lowe",
      userImageUrl = "stub",
      species = "Human"
    )
    composeTestRule.setContent {
      UserCardScreen(userData = userData)
    }

    composeTestRule.onNodeWithTag("userCardImage")
      .assertExists()
      .assertContentDescriptionEquals("User profile photo for ${userData.userName}")

    composeTestRule.onNodeWithTag("userCardNameLabel")
      .assertIsDisplayed()
      .assertTextEquals("User name:")

    composeTestRule.onNodeWithTag("userCardNameValue")
      .assertIsDisplayed()
      .assertTextEquals(userData.userName)

    composeTestRule.onNodeWithTag("userCardSpeciesLabel")
      .assertIsDisplayed()
      .assertTextEquals("Species:")

    composeTestRule.onNodeWithTag("userCardSpeciesValue")
      .assertIsDisplayed()
      .assertTextEquals(userData.species)
  }
}