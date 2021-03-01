package com.egabruskiy.curiositywatcher.di

import android.app.Application
import androidx.room.Room
import com.egabruskiy.curiositywatcher.data.database.AppDataBase
import com.egabruskiy.curiositywatcher.data.database.CuriosityImageDao
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

private const val DATABASE_NAME = "curiosity_db"

val databaseModule = module {


    single { provideAppDatabase(androidApplication()) }

    single { provideCuriosityImageDao(get()) }

}

private fun provideAppDatabase(application: Application): AppDataBase {
    return Room.databaseBuilder(application, AppDataBase::class.java, DATABASE_NAME)
        .build()
}

private fun provideCuriosityImageDao(database: AppDataBase): CuriosityImageDao {
    return database.CuriosityImageDao()
}
