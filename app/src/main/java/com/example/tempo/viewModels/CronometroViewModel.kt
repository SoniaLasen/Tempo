package com.example.tempo.viewModels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tempo.repository.CronosRepository
import com.example.tempo.state.CronoState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CronometroViewModel @Inject constructor(private val repository: CronosRepository): ViewModel() {
    var state by mutableStateOf(CronoState())//() en CronoState significan que de principio está vacío
        private set

    private var cronoJob by mutableStateOf<Job?>(null)//definimos aquí estas variables porque necesitamos tener un acceso
        //continuo que sería difícil de manejar desde el state.


    var tiempo by mutableLongStateOf(0L)
        private set

    fun getCronoById(id: Long){
        viewModelScope.launch(Dispatchers.IO) {
            repository.getCronosById(id).collect{
                if(it!=null){
                    tiempo = it.crono
                    state =state.copy(title = it.title)
                }
                else Log.d("Error", "El objeto es nulo")
            }
        }
    }

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