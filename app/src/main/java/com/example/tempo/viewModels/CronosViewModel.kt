package com.example.tempo.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tempo.model.Cronos
import com.example.tempo.repository.CronosRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CronosViewModel @Inject constructor(private val repository: CronosRepository): ViewModel() {
    private val _cronosList = MutableStateFlow<List<Cronos>>(emptyList())
    val cronosList = _cronosList.asStateFlow()//para que esta variable sea de sólo lectura, ya que es la que vamos a usar en las Views

    init {
        viewModelScope.launch (Dispatchers.IO) {
            repository.getAllCronos().collect{
                if (it.isNullOrEmpty()){
                    _cronosList.value = emptyList()
                }else{
                    _cronosList.value = it//it es la lista con todos los datos que le hayamos metido
                }
            }
        }
    }

    fun addCrono(crono: Cronos) = viewModelScope.launch { repository.addCrono(crono) }
    fun updateCrono(crono: Cronos) = viewModelScope.launch { repository.updateCrono(crono) }
    fun deleteCrono(crono: Cronos) = viewModelScope.launch { repository.deleteCrono(crono) }
}