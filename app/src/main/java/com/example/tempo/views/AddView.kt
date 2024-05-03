package com.example.tempo.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.example.tempo.components.MainIconButton
import com.example.tempo.components.MainTitle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddView(navController: NavController) {
    Scaffold(topBar = {
        CenterAlignedTopAppBar(
            title = { MainTitle(title = "Añadir") },
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary,
                titleContentColor = Color.White
            ),
            navigationIcon = {
                MainIconButton(icon = Icons.AutoMirrored.Filled.ArrowBack) {
                    navController.popBackStack()
                }
            }

        )
    }

    ) {
        ContentAddView(it, navController)
    }
}

@Composable
fun ContentAddView(it: PaddingValues, navController: NavController) {
    Column(
        modifier = Modifier.padding(it)
    ) {

    }
}