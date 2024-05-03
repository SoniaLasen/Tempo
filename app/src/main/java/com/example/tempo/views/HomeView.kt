package com.example.tempo.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.tempo.components.MainTitle
import com.example.tempo.R
import com.example.tempo.components.FloatButton

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeView(navController: NavController){
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
        ContentHomeView(it, navController)
    }
}

@Composable
fun ContentHomeView(it: PaddingValues, navController: NavController){
    Column(
        modifier = Modifier.padding(it)
    ) {

    }
}
