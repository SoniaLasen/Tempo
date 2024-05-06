package com.example.tempo.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.tempo.viewModels.CronometroViewModel
import com.example.tempo.viewModels.CronosViewModel
import com.example.tempo.views.AddView
import com.example.tempo.views.EditView
import com.example.tempo.views.HomeView

@Composable
fun NavManager(cronometroViewModel: CronometroViewModel, cronosViewModel: CronosViewModel){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "Home"){
        composable("Home"){
            HomeView(navController, cronosViewModel)
        }
        composable("AddView"){
            AddView(navController, cronometroViewModel, cronosViewModel)
        }
        composable("EditView/{id}", arguments = listOf(
            navArgument("id"){type = NavType.LongType}
        )){
            val id = it.arguments?.getLong("id") ?: 0

        EditView(navController, cronometroViewModel, cronosViewModel, id)
        }
    }
}