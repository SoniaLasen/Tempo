package com.example.tempo.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tempo.state.CronoState
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class CronometroViewModel: ViewModel() {
    var state by mutableStateOf(CronoState())//() en CronoState significan que de principio está vacío
        private set

    private var cronoJob by mutableStateOf<Job?>(null)//definimos aquí estas variables porque necesitamos tener un acceso
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
            showTextField = false,
            title = ""
        )
    }

    fun showTextField(){
        state = state.copy(
            showTextField = true
        )
    }

    fun cronos(){
        if(state.cronometroActivo){
            cronoJob?.cancel()
            cronoJob = viewModelScope.launch {
                while(true) {
                    delay(1000)
                    tiempo += 1000 //tiempo se define aquí, porque no deja hacer esto desde un copy,
                    //no puede definirse en la dataclass.
                }
            }//le decimos que cronoJob es una corrutina que se ejecuta en el hilo principal
        }else{
            cronoJob?.cancel()
        }
    }
}