package com.example.testingdemo.ui.screens.userlist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.example.testingdemo.data.UserData
import com.example.testingdemo.ui.screens.usercard.UserCardScreen
import com.example.testingdemo.ui.screens.usercard.semantics.userCardId

@Composable
fun UserListScreen(userData: List<UserData>) {
  val sort = rememberSaveable {
    mutableStateOf(Sort.ID)
  }

  val sortedData = remember(sort.value) {
    mutableStateOf(
      when (sort.value) {
        Sort.ID -> userData.sortedBy { it.userId }
        Sort.NAME -> userData.sortedBy { it.userName }
      }
    )
  }

  Column(
    modifier = Modifier.fillMaxSize()
  ) {
    LazyColumn(
      modifier = Modifier.weight(1f).testTag("userListCards"),
      verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
      items(sortedData.value) {
        UserCardScreen(
          userData = it,
          modifier = Modifier.semantics { userCardId = it.userId }
        )
      }
    }
    SortBottomBar(sort) { selectedSort ->
      sort.value = selectedSort
    }
  }
}

@Composable
private fun SortBottomBar(sort: State<Sort>, sortHandler: (Sort) -> Unit) {
  Column(
    modifier = Modifier.padding(8.dp)
  ) {
    Text(
      text = "Sort by:"
    )
    Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.SpaceEvenly
    ) {
      SortRadioButton(
        sort,
        Sort.ID,
        "ID"
      ) {
        sortHandler(Sort.ID)
      }
      SortRadioButton(
        sort,
        Sort.NAME,
        "Name"
      ) {
        sortHandler(Sort.NAME)
      }
    }
  }
}

@Composable
private fun SortRadioButton(
  sort: State<Sort>,
  sortType: Sort,
  radioText: String,
  sortSelected: () -> Unit) {
  Column(
    horizontalAlignment = Alignment.Start
  ) {
    RadioButton(
      selected = sort.value == sortType,
      onClick = {
        sortSelected()
      },
      modifier = Modifier.testTag("sortRadioButton${sortType.ordinal}")
    )
    Text(radioText)
  }
}

enum class Sort {
  ID, NAME
}