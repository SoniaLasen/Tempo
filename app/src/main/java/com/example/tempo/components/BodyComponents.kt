package com.example.tempo.components

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tempo.R

@Composable
fun MainTitle(title: String) {
    Text(text = title,
        fontSize = 50.sp,
        color = Color.White,
        fontWeight = FontWeight.Bold,
        fontFamily = FontFamily(
        Font(R.font.satisfy),
    ))
}

@Composable
fun MainTextField(value: String, onValueChange: (String) -> Unit, label: String) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(text = label) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 30.dp)
            .padding(bottom = 15.dp)

    )
}

//componente para el cronómetro
@SuppressLint("DefaultLocale")
@Composable //aunque es un composable, la función se pone con la primera letra en minúscula ya
// que devuelve un valor, en este caso un String en forma de cronómetro
fun formatTiempo(tiempo: Long): String{
    val segundos = (tiempo/1000) % 60
    val minutos = (tiempo/1000/60) % 60
    val horas = tiempo/1000/3600

    return String.format("%02d:%02d:%02d", horas, minutos, segundos)
}

@Composable
fun CronosCard(titulo:String, crono: String, onClick: () -> Unit){
//crono es el tiempo en formato String después de pasarlo por formatTiempo
    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 10.dp)
        .clickable { onClick() }//ojo, en un clickable el onClick tiene que ir con el constructor ().
    ){
        Column(modifier = Modifier.padding(15.dp)) {
            Text(text = titulo,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
                )
            Row {
                Icon(painter = painterResource(id = R.drawable.cronometro),
                    contentDescription ="",
                    tint = Color.Gray
                    )
                Text(text = crono, fontSize = 20.sp)
            }
            HorizontalDivider(modifier = Modifier.fillMaxWidth(),
                thickness = 1.dp,
                color = MaterialTheme.colorScheme.primary
                )
        }
    }
}

