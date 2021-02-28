package com.egabruskiy.curiositywatcher.di

import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

val appModule = module {

    single { Dispatchers.Default }
}
