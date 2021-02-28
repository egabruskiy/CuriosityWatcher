package com.egabruskiy.curiositywatcher.di

import com.egabruskiy.curiositywatcher.data.database.CuriosityImageDao
import com.egabruskiy.curiositywatcher.data.network.CuriosityImageRemote
import com.egabruskiy.curiositywatcher.repository.CuriosityImageRepository
import org.koin.dsl.module

val repositoryModule = module {

    single { provideCuriosityImageRepository(get(), get()) }
}

private fun provideCuriosityImageRepository(
    remote: CuriosityImageRemote,
    dao: CuriosityImageDao
): CuriosityImageRepository {
    return CuriosityImageRepository(remote,dao)
}
