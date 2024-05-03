package com.example.tempo.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.tempo.state.CronoState
import kotlinx.coroutines.Job

class CronometroViewModel {
    var state by mutableStateOf(CronoState())//() en CronoState significan que de principio está vacío
        private set

    var cronoJob by mutableStateOf<Job?>(null)//definimos aquí estas variables porque necesitamos tener un acceso
        //continuo que sería difícil de manejar desde el state.
        private set

    var tiempo by mutableLongStateOf(0L)
        private set

    fun onValue(value: String){
        state = state.copy(title = value)
    }

    fun iniciar(){
        state = state.copy(
            cronometroActivo = true)
    }

    fun pausar(){
        state = state.copy(
            cronometroActivo = false,
            showSaveButton = true
        )
    }

    fun detener(){
        cronoJob?.cancel()
        tiempo = 0
        state = state.copy(
            cronometroActivo = false,
            showSaveButton = false,
            showTextField = false
        )
    }

    fun showTextField(){
        state = state.copy(
            showTextField = true
        )
    }
}