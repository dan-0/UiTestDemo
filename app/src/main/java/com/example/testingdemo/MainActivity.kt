package com.example.testingdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.testingdemo.data.userData
import com.example.testingdemo.ui.screens.home.HomeEvent
import com.example.testingdemo.ui.screens.home.HomeScreen
import com.example.testingdemo.ui.screens.usercard.UserCardScreen
import com.example.testingdemo.ui.theme.TestingDemoTheme

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      TestingDemoTheme {
        // A surface container using the 'background' color from the theme
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
          val navController = rememberNavController()

          NavHost(navController = navController, startDestination = "home") {
            composable("home") {
              HomeScreen {
                val destination = when (it) {
                  HomeEvent.GoToUserCard -> "usercard"
                  HomeEvent.GoToInteropUserCard -> "iteropusercard"
                  HomeEvent.GoToUserList -> "userlist"
                }
                navController.navigate(destination)
              }
            }
            composable("usercard") {
              val user = userData.first()
              UserCardScreen(userData = user)
            }
            composable("iteropusercard") {

            }
            composable("userlist") {

            }

          }
        }
      }
    }
  }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
  Text(
    text = "Hello $name!",
    modifier = modifier
  )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
  TestingDemoTheme {
    Greeting("Android")
  }
}