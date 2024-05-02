package com.example.tempo.di

import android.content.Context
import androidx.room.Room
import com.example.tempo.room.CronosDatabase
import com.example.tempo.room.CronosDatabaseDAO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)//la inyección de dependencias usa el patrón singleton
object AppModule {

    @Singleton
    @Provides

    fun providesCronosDao(cronoDatabase: CronosDatabase) : CronosDatabaseDAO{
        return cronoDatabase.cronosDAO()
    }

    @Singleton
    @Provides
    fun providesCronosDatabase (@ApplicationContext context: Context) : CronosDatabase{
        return Room.databaseBuilder(
            context,
            CronosDatabase::class.java,
            "cronos_db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }
}