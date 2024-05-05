package com.example.tempo.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.tempo.R
import com.example.tempo.components.CircleButton
import com.example.tempo.components.MainIconButton
import com.example.tempo.components.MainTitle
import com.example.tempo.components.formatTiempo
import com.example.tempo.viewModels.CronometroViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddView(navController: NavController, cronometroViewModel: CronometroViewModel) {
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
        ContentAddView(it, navController, cronometroViewModel)
    }
}

@Composable
fun ContentAddView(it: PaddingValues, navController: NavController, cronometroViewModel: CronometroViewModel) {

    val state = cronometroViewModel.state

    LaunchedEffect(state.cronometroActivo) {//ejecutamos esta corrutina cuando el cronometroActivo sea true
        cronometroViewModel.cronos()
    }

    Column(
        modifier = Modifier
            .padding(it)
            .padding(top = 30.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Text(text = formatTiempo(
            tiempo = cronometroViewModel.tiempo),
            fontSize = 50.sp,
            fontWeight = FontWeight.Bold
            )
        Row (
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.padding(vertical = 16.dp)//este padding tb actúa como un margen en la parte superior e inferior
        ){
            CircleButton(icon = painterResource(id = R.drawable.play), enabled = !state.cronometroActivo) {
                cronometroViewModel.iniciar()
            }

            CircleButton(icon = painterResource(id = R.drawable.pause), enabled = state.cronometroActivo) {
                cronometroViewModel.pausar()
            }

            CircleButton(icon = painterResource(id = R.drawable.stop), enabled = !state.cronometroActivo) {
                cronometroViewModel.detener()
            }

            CircleButton(icon = painterResource(id = R.drawable.save), enabled = state.showSaveButton) {
                cronometroViewModel.showTextField()
            }
        }
    }
}
