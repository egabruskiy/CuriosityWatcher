package com.egabruskiy.curiositywatcher.view.maingallery

import androidx.lifecycle.*
import com.egabruskiy.curiositywatcher.util.Util
import com.egabruskiy.curiositywatcher.data.model.CuriosityImage
import com.egabruskiy.curiositywatcher.data.model.CuriosityImageResponse
import com.egabruskiy.curiositywatcher.data.model.ResourceResult
import com.egabruskiy.curiositywatcher.repository.CuriosityImageRepository
import kotlinx.coroutines.launch

class MainGalleryViewModel (private val repository:CuriosityImageRepository): ViewModel() {


    private val loadTrigger = MutableLiveData(Unit)

    val networkStatus : MutableLiveData<Boolean> =  MutableLiveData()

    fun changeNetworkStatus(boolean: Boolean){
        networkStatus.value = boolean
    }

    fun refreshImageList() {
        loadTrigger.value = Unit
    }

    val images: LiveData<ResourceResult<MutableList<CuriosityImageResponse>>> = loadTrigger.switchMap {
        loadData()
    }

    private fun loadData(): LiveData<ResourceResult<MutableList<CuriosityImageResponse>>>   {
       return repository.getLast20Images().asLiveData()
    }

    val offlineImages: LiveData<ResourceResult<MutableList<CuriosityImage>>> = repository.getOfflineImageList().asLiveData()


    fun saveImagesLocal(list: MutableList<CuriosityImageResponse>){
        viewModelScope.launch {
            repository.insertAll(Util.convertResponseImageList(list))
        }
    }




}