package com.example.tempo.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.tempo.viewModels.CronometroViewModel
import com.example.tempo.views.AddView
import com.example.tempo.views.EditView
import com.example.tempo.views.HomeView

@Composable
fun NavManager(cronometroViewModel: CronometroViewModel){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "Home"){
        composable("Home"){
            HomeView(navController)
        }
        composable("AddView"){
            AddView(navController, cronometroViewModel)
        }
        composable("EditView"){
            EditView(navController)
        }
    }
}