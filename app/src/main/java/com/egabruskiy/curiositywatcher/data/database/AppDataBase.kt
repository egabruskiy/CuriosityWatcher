package com.egabruskiy.curiositywatcher.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.egabruskiy.curiositywatcher.data.model.CuriosityImage


@Database(entities = [CuriosityImage::class], version = 1, exportSchema = false)
abstract class AppDataBase : RoomDatabase() {

    abstract fun CuriosityImageDao():CuriosityImageDao
}