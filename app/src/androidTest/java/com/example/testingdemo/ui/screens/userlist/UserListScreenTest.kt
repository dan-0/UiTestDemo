package com.example.testingdemo.ui.screens.userlist

import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollToIndex
import androidx.compose.ui.test.printToLog
import com.example.testingdemo.data.UserData
import com.example.testingdemo.data.userData
import com.example.testingdemo.ui.screens.usercard.semantics.UserCardIdKey
import org.junit.Rule
import org.junit.Test

class UserListScreenTest {
  @get:Rule
  val composeTestRule = createComposeRule()

  @Test
  fun initialDisplay() {
    composeTestRule.setContent {
      // Use a shuffled data set to confirm UI sorting behavior
      UserListScreen(userData = userData.shuffled())
    }
    val cardsNode = composeTestRule.onNodeWithTag("userListCards")

    assertOrder(USER_DATA_BY_ID, cardsNode)
  }

  @Test
  fun sortedByName() {
    composeTestRule.setContent {
      // Use a shuffled data set to confirm UI sorting behavior
      UserListScreen(userData = userData.shuffled())
    }
    composeTestRule.onNodeWithTag("sortRadioButton${Sort.NAME.ordinal}")
      .performClick()

    val cardsNode = composeTestRule.onNodeWithTag("userListCards")

    assertOrder(USER_DATA_BY_NAME, cardsNode)
  }

  @Test
  fun sortSwitching() {
    composeTestRule.setContent {
      // Use a shuffled data set to confirm UI sorting behavior
      UserListScreen(userData = userData.shuffled())
    }
    val cardsNode = composeTestRule.onNodeWithTag("userListCards")
    assertOrder(USER_DATA_BY_ID, cardsNode)

    composeTestRule.onNodeWithTag("sortRadioButton${Sort.NAME.ordinal}")
      .performClick()
    assertOrder(USER_DATA_BY_NAME, cardsNode)

    composeTestRule.onNodeWithTag("sortRadioButton${Sort.ID.ordinal}")
      .performClick()

    assertOrder(USER_DATA_BY_ID, cardsNode)
  }

  private fun assertOrder(
    expectedOrder: List<UserData>,
    cardsNode: SemanticsNodeInteraction
  ) {
    expectedOrder.forEachIndexed { index, userData ->
      cardsNode.performScrollToIndex(index)
      try {
        composeTestRule.onNode(SemanticsMatcher.expectValue(UserCardIdKey, userData.userId))
          .assertExists()
      } catch (e: Throwable) {
        composeTestRule.onNodeWithTag("userListCards").printToLog("NOT_FOUND")
        throw e
      }
    }
  }

  companion object {
    private val USER_DATA_BY_NAME = userData.sortedBy { it.userName }
    private val USER_DATA_BY_ID = userData.sortedBy { it.userId }
  }
}