package com.egabruskiy.curiositywatcher.di

import com.egabruskiy.curiositywatcher.BuildConfig
import com.egabruskiy.curiositywatcher.data.network.CuriosityImageRemote
import com.egabruskiy.curiositywatcher.data.network.CuriosityImageRemoteImpl
import com.egabruskiy.curiositywatcher.data.network.NasaService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://api.nasa.gov/"

val networkModule = module {

    single { provideLoggingInterceptor() }

    single { provideOkHttpClient(get()) }

    single { provideRetrofit(get()) }

    single { provideNasaService(get()) }

    single<CuriosityImageRemote> { provideCuriosityImageRemoteImpl(get()) }
}

private fun provideLoggingInterceptor(): HttpLoggingInterceptor {

    return HttpLoggingInterceptor().apply {
        level = if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE

        }
    }
}

private fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
    return OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()
}

private fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

private fun provideNasaService(retrofit: Retrofit): NasaService {
    return retrofit.create(NasaService::class.java)
}

private fun provideCuriosityImageRemoteImpl(
    service: NasaService): CuriosityImageRemoteImpl {
    return CuriosityImageRemoteImpl(service)
}
