package com.example.testingdemo.ui.screens.home

import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.filterToOne
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.hasTextExactly
import androidx.compose.ui.test.junit4.createComposeRule
import com.example.testingdemo.ui.screens.home.HomeScreen
import org.junit.Rule
import org.junit.Test

class HomeScreenTest {
  @get:Rule
  val composeTestRule = createComposeRule()

  @Test
  fun displayed() {
    composeTestRule.setContent {
      HomeScreen{}
    }

    val nodes = composeTestRule.onAllNodes(hasTestTag("buttonUserCard"))

    val userCardButton = nodes[0]
    val interopButton = nodes[1]
    val userListButton = nodes[2]

    userCardButton
      .assertIsDisplayed()
      .assertTextEquals("User Card")

    interopButton
      .assertIsDisplayed()
      .assertTextEquals("Interop User Card")

    userListButton
      .assertIsDisplayed()
      .assertTextEquals("User List")

    // Alternatively...
    nodes.filterToOne(hasTextExactly("User Card"))
      .assertIsDisplayed()

    nodes.filterToOne(hasTextExactly("Interop User Card"))
      .assertIsDisplayed()

    nodes.filterToOne(hasTextExactly("User List"))
      .assertIsDisplayed()

    // Verify expected count
    nodes.assertCountEquals(3)
  }
}

