package com.example.tempo.repository

import com.example.tempo.model.Cronos
import com.example.tempo.room.CronosDatabaseDAO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class CronosRepository @Inject constructor(private val cronosDatabaseDAO: CronosDatabaseDAO) {
    //Cuando sólo hay una línea de código en una función, podemos sustituir las llaves por un =
    suspend fun addCrono (crono: Cronos) = cronosDatabaseDAO.insert(crono)
    suspend fun updateCrono (crono: Cronos) = cronosDatabaseDAO.update(crono)
    suspend fun deleteCrono (crono: Cronos) = cronosDatabaseDAO.delete(crono)

    fun getAllCronos() : Flow<List<Cronos>> = cronosDatabaseDAO.getCronos().flowOn(Dispatchers.IO).conflate()
    fun getCronosById(id: Long) : Flow<Cronos> = cronosDatabaseDAO.getCronosById(id).flowOn(Dispatchers.IO).conflate()

}