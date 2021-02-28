package com.egabruskiy.curiositywatcher.view.fullScreenGallery

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.egabruskiy.curiositywatcher.data.model.CuriosityImage
import com.egabruskiy.curiositywatcher.data.model.ResourceResult
import com.egabruskiy.curiositywatcher.repository.CuriosityImageRepository

class FullScreenGalleryViewModel(repository: CuriosityImageRepository) : ViewModel() {

     var position: Int = 0

        val offlineImages: LiveData<ResourceResult<MutableList<CuriosityImage>>> = repository.getOfflineImageList().asLiveData()
}