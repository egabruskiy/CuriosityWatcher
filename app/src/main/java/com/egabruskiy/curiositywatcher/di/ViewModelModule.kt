package com.egabruskiy.curiositywatcher.di

import com.egabruskiy.curiositywatcher.repository.CuriosityImageRepository
import com.egabruskiy.curiositywatcher.view.fullScreenGallery.FullScreenGalleryViewModel
import com.egabruskiy.curiositywatcher.view.maingallery.MainGalleryViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel { provideMainGalleryViewModel(get()) }
    viewModel { provideFullScreenGalleryViewModel(get()) }
}

private fun provideMainGalleryViewModel(repository: CuriosityImageRepository): MainGalleryViewModel {
    return MainGalleryViewModel(repository)
}
private fun provideFullScreenGalleryViewModel(repository: CuriosityImageRepository): FullScreenGalleryViewModel {
    return FullScreenGalleryViewModel(repository)
}
