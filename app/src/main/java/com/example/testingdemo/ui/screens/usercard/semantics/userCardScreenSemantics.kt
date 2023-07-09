package com.example.testingdemo.ui.screens.usercard.semantics

import androidx.compose.ui.semantics.SemanticsPropertyKey
import androidx.compose.ui.semantics.SemanticsPropertyReceiver

val UserCardIdKey = SemanticsPropertyKey<Long>("UserCardId")
var SemanticsPropertyReceiver.userCardId by UserCardIdKey