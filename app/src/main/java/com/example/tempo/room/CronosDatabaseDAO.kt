package com.example.tempo.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.tempo.model.Cronos
import kotlinx.coroutines.flow.Flow


@Dao //Data Access Observer
interface CronosDatabaseDAO { //crud - create(insert), read(query), upload, delete
    @Query("SELECT * FROM cronos")// bajar toodo lo que tenga la base de datos
    fun getCronos(): Flow<List<Cronos>> //lo guardamos en una lista del tipo Cronos, que es una tabla, con flow para que sea en tiempo real

    @Query("SELECT * FROM cronos WHERE id = :id")//bajar un registro según el id
    fun getCronosById(id: Long): Flow<Cronos>

    @Insert(onConflict = OnConflictStrategy.REPLACE) //si hay algún conflicto a la hora de guardar, reemplaza
    suspend fun insert(crono: Cronos)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(crono: Cronos)

    @Delete
    suspend fun delete(crono: Cronos)
}
