package com.example.tempo.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
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
import com.example.tempo.components.MainTextField
import com.example.tempo.components.MainTitle
import com.example.tempo.components.formatTiempo
import com.example.tempo.model.Cronos
import com.example.tempo.viewModels.CronometroViewModel
import com.example.tempo.viewModels.CronosViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditView(
    navController: NavController,
    cronometroViewModel: CronometroViewModel,
    cronosViewModel: CronosViewModel,
    id: Long
) {
    Scaffold(topBar = {
        CenterAlignedTopAppBar(
            title = { MainTitle(title = "Editar") },
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
        ContentEditView(it, navController, cronometroViewModel, cronosViewModel, id)
    }
}

@Composable
fun ContentEditView(
    it: PaddingValues,
    navController: NavController,
    cronometroViewModel: CronometroViewModel,
    cronosViewModel: CronosViewModel,
    id: Long
) {

    val state = cronometroViewModel.state

    LaunchedEffect(state.cronometroActivo) {//ejecutamos esta corrutina cuando el cronometroActivo sea true
        cronometroViewModel.cronos()
    }

    LaunchedEffect(Unit) {
        cronometroViewModel.getCronoById(id)
    }

    Column(
        modifier = Modifier
            .padding(it)
            .padding(top = 30.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Text(
            text = formatTiempo(
                tiempo = cronometroViewModel.tiempo
            ),
            fontSize = 50.sp,
            fontWeight = FontWeight.Bold
        )
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.padding(vertical = 16.dp)//este padding tb actúa como un margen en la parte superior e inferior
        ) {
            CircleButton(
                icon = painterResource(id = R.drawable.play),
                enabled = !state.cronometroActivo
            ) {
                cronometroViewModel.iniciar()
            }

            CircleButton(
                icon = painterResource(id = R.drawable.pause),
                enabled = state.cronometroActivo
            ) {
                cronometroViewModel.pausar()
            }
        }

            MainTextField(
                value = state.title,
                onValueChange = { cronometroViewModel.onValue(it) },
                "Title"
            )

            Button(onClick = {
                cronosViewModel.updateCrono(
                    Cronos(
                        id= id,
                        title = state.title,
                        crono = cronometroViewModel.tiempo
                    )
                )
                cronometroViewModel.detener()
                navController.popBackStack()

            }) {
                Text(text = "Guardar")
            }
        DisposableEffect(Unit) {
            onDispose {
                cronometroViewModel.detener()
            }
        }

    }
}