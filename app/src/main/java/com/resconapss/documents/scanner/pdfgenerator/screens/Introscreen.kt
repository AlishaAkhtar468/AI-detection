package com.resconapss.documents.scanner.pdfgenerator.screens

import android.annotation.SuppressLint
import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

  @SuppressLint("SuspiciousIndentation")
  @Composable
fun Introscreen(activity:Activity) {

  val  navController = rememberNavController()

    NavHost(navController = navController, startDestination = "Onboarding") {

        composable("Onboarding") {
            OnboardingScreen(navController = navController, context =activity )
        }
    }
}
