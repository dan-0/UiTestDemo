package com.example.testingdemo.ui.screens.shared

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import com.example.testingdemo.ui.screens.shared.HomeButton
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

class HomeButtonTest {
  @get:Rule
  val composeTestRule = createComposeRule()

  @Test
  fun displayed() {
    composeTestRule.setContent {
      HomeButton(buttonText = "Hello") {}
    }

    composeTestRule.onNodeWithTag("buttonUserCard")
      .assertIsDisplayed()
      // Note, this works because of the merged tree
      .assertTextEquals("Hello")

    // Note this requires using the unmerged tree
    composeTestRule.onNodeWithTag("buttonTextUserCard", true)
      .assertIsDisplayed()
      .assertTextEquals("Hello")
  }

  @Test
  fun clicked() {
    var result: String? = null
    composeTestRule.setContent {
      HomeButton(buttonText = "Hello") {
        result = "Clicked"
      }
    }

    composeTestRule.onNodeWithTag("buttonUserCard")
      .performClick()

    Assert.assertEquals(result, "Clicked")
  }
}