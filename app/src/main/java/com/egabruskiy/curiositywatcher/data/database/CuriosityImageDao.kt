package com.egabruskiy.curiositywatcher.data.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.egabruskiy.curiositywatcher.data.model.CuriosityImage



@Dao
interface CuriosityImageDao {

    @Query("SELECT * FROM images where deleted = 0  LIMIT 20")
    suspend fun getLastTwenty(): MutableList<CuriosityImage>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertImages( images: MutableList<CuriosityImage>)


    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateImage( image: CuriosityImage)



}