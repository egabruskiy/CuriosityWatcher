package com.egabruskiy.curiositywatcher.repository



import androidx.lifecycle.MutableLiveData
import com.egabruskiy.curiositywatcher.data.database.CuriosityImageDao
import com.egabruskiy.curiositywatcher.data.model.CuriosityImage
import com.egabruskiy.curiositywatcher.data.model.ResourceResult
import com.egabruskiy.curiositywatcher.data.network.CuriosityImageRemote
import com.egabruskiy.curiositywatcher.util.Util
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import timber.log.Timber


class CuriosityImageRepository(private val remote:CuriosityImageRemote,
                               private val curiosityImageDao:CuriosityImageDao) {





    suspend fun updateImage(image :CuriosityImage){
        curiosityImageDao.updateImage(image)

    }


    suspend fun updateRepository(){
        val images = remote.getLastImages()
        curiosityImageDao.insertImages(Util.convertResponseImageList(images))
    }


    fun getOfflineImageList(): Flow<ResourceResult<MutableList<CuriosityImage>>> = flow {
        emit(ResourceResult.loading())
        val images = curiosityImageDao.getLastTwenty()

        emit(ResourceResult.successOrEmpty(images))
    }.catch { e ->
        emit(ResourceResult.error(e))
    }



}