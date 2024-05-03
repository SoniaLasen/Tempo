package com.example.tempo.state

data class CronoState(
    val cronometroActivo: Boolean = false,
    val showSaveButton: Boolean = false,
    val showTextField: Boolean = false,
    val title: String = "" //está asociada a un textField, así que en ModelView hay que hacer su función onValue
)
