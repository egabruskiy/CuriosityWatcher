package com.egabruskiy.curiositywatcher.data.network

import com.egabruskiy.curiositywatcher.data.model.CuriosityImageResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber

class CuriosityImageRemoteImpl(
    private val service: NasaService
    ): CuriosityImageRemote{



    override suspend fun getLastImages()
    : MutableList<CuriosityImageResponse> = withContext(Dispatchers.Default) {

        val response = service.getLastImages()

        response.photos


    }
}