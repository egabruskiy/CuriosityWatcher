package com.egabruskiy.curiositywatcher.data.network

import com.egabruskiy.curiositywatcher.data.model.NasaResponse
import retrofit2.http.GET



interface NasaService {
    @GET("mars-photos/api/v1/rovers/curiosity/photos?sol=1000&api_key=AlprPlXQtZfWLdbFhftfx0tUJwxNzHMlXTvuo7Q9")
    suspend fun getLastImages(): NasaResponse
}