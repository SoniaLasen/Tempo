package com.example.tempo.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.tempo.model.Cronos


@Database(entities = [Cronos::class], version = 1, exportSchema = false)
abstract class CronosDatabase: RoomDatabase() {
    abstract fun cronosDAO() : CronosDatabaseDAO
}