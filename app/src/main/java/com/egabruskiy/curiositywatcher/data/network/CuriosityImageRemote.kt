package com.egabruskiy.curiositywatcher.data.network

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.egabruskiy.curiositywatcher.data.model.CuriosityImageResponse

interface CuriosityImageRemote {

    suspend fun getLastImages(): MutableList<CuriosityImageResponse>

}