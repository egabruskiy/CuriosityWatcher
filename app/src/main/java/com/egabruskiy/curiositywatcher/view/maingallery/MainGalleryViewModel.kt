package com.egabruskiy.curiositywatcher.view.maingallery

import androidx.lifecycle.*
import com.egabruskiy.curiositywatcher.util.Util
import com.egabruskiy.curiositywatcher.data.model.CuriosityImage
import com.egabruskiy.curiositywatcher.data.model.CuriosityImageResponse
import com.egabruskiy.curiositywatcher.data.model.ResourceResult
import com.egabruskiy.curiositywatcher.repository.CuriosityImageRepository
import kotlinx.coroutines.launch
import timber.log.Timber

class MainGalleryViewModel (private val repository:CuriosityImageRepository): ViewModel() {

    init {
        updateRepository()
    }

    private val loadTrigger = MutableLiveData(Unit)

    val networkStatus : MutableLiveData<Boolean> =  MutableLiveData()

    fun changeNetworkStatus(boolean: Boolean){
        networkStatus.value = boolean
    }

    fun updateImageList() {
        updateRepository()
        loadTrigger.value = Unit
    }

    val offlineImages :LiveData<ResourceResult<MutableList<CuriosityImage>>> = loadTrigger.switchMap {
        loadOfflineImages()
    }

    private fun loadOfflineImages():LiveData<ResourceResult<MutableList<CuriosityImage>>>{
        return repository.getOfflineImageList().asLiveData()
    }


    private fun updateRepository(){
        viewModelScope.launch {
            repository.updateRepository()
        }
    }


    fun updateImage(image:CuriosityImage){
        viewModelScope.launch {
            repository.updateImage(image)
        }
    }




}