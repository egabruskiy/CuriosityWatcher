package com.egabruskiy.curiositywatcher.data.database

import androidx.room.*
import com.egabruskiy.curiositywatcher.data.model.CuriosityImage



@Dao
interface CuriosityImageDao {

    @Query("SELECT * FROM images  LIMIT 20")
    suspend fun getLastTwenty(): MutableList<CuriosityImage>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertImages( images: MutableList<CuriosityImage>)



}