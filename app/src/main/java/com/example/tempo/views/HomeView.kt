package com.example.tempo.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.tempo.R
import com.example.tempo.components.CronosCard
import com.example.tempo.components.FloatButton
import com.example.tempo.components.MainTitle
import com.example.tempo.components.formatTiempo
import com.example.tempo.viewModels.CronosViewModel
import me.saket.swipe.SwipeAction
import me.saket.swipe.SwipeableActionsBox

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeView(navController: NavController, cronosViewModel: CronosViewModel){
    Scaffold (topBar = {
        CenterAlignedTopAppBar(
            navigationIcon = {
                Image(
                    painter = painterResource(id = R.drawable.tempo),
                    contentDescription = "logo de la app",
                    modifier = Modifier
                        .size(55.dp)
                        .offset(x = 5.dp)
                        .clip(RoundedCornerShape(20.dp))
                )
            },
            title = { MainTitle(title = "Tempo") },
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary,
                titleContentColor = Color.White
            )

        )
    },
        floatingActionButton = {
            FloatButton {
                navController.navigate("AddView")
            }
        }
        ){
        ContentHomeView(it, navController, cronosViewModel)
    }
}

@Composable
fun ContentHomeView(it: PaddingValues, navController: NavController, cronosViewModel: CronosViewModel){
    Column(
        modifier = Modifier.padding(it)
    ) {
        val cronosList by cronosViewModel.cronosList.collectAsState()

        LazyColumn {

            items(cronosList){item -> //también se puede quitar el 'item .>' y
                //trabajar con el it que viene por defecto en la lambda
                val delete = SwipeAction(
                    icon = rememberVectorPainter(Icons.Default.Delete),
                    background = Color.Red,
                    onSwipe = {cronosViewModel.deleteCrono(item)}
                )
                SwipeableActionsBox(endActions = listOf(delete), swipeThreshold = 75.dp) {
                    CronosCard(titulo = item.title, crono = formatTiempo(tiempo = item.crono) ) {
                        navController.navigate("EditView/${item.id}")
                    }
                }
            }
        }
    }
}
