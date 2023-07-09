package com.example.testingdemo.ui.screens.interopcard

import androidx.compose.ui.test.assertContentDescriptionEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.example.testingdemo.R
import com.example.testingdemo.data.UserData
import org.junit.Rule
import org.junit.Test

class InteropCardScreenTest {
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
      InteropCardScreen(userData = userData)
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
  }
}