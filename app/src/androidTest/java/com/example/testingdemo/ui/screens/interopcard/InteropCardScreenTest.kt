package com.example.testingdemo.ui.screens.interopcard

import androidx.compose.foundation.layout.Box
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId
import androidx.compose.ui.test.assertContentDescriptionEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import androidx.test.uiautomator.By
import androidx.test.uiautomator.UiDevice
import com.example.testingdemo.R
import com.example.testingdemo.data.UserData
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class InteropCardScreenTest {
  @get:Rule
  val composeTestRule = createComposeRule()

  @OptIn(ExperimentalComposeUiApi::class)
  @Test
  fun displayed() {
    val userData = UserData(
      userId = 0,
      userName = "Dan Lowe",
      userImageUrl = "stub",
      species = "Human"
    )
    composeTestRule.setContent {
      Box(
        modifier = Modifier.semantics {
          testTagsAsResourceId = true
        }
      ) {
        InteropCardScreen(userData = userData)
      }
    }

    composeTestRule.onNodeWithTag("userCardImage")
      .assertExists()
      .assertContentDescriptionEquals("User profile photo for ${userData.userName}")

    // Note using Espresso because we have an Android View
    onView(withId(R.id.user_card_name_label))
      .check(matches(withText("User name:")))
    onView(withId(R.id.user_card_name_value))
      .check(matches(withText(userData.userName)))
    onView(withId(R.id.user_card_species_label))
      .check(matches(withText("Species:")))
    onView(withId(R.id.user_card_species_value))
      .check(matches(withText(userData.species)))

    val device = UiDevice.getInstance(getInstrumentation())
    val userCardImage = device.findObject(By.res("userCardImage"))
    assertEquals(userCardImage.contentDescription, "User profile photo for ${userData.userName}")
  }
}