package com.egabruskiy.curiositywatcher.repository



import com.egabruskiy.curiositywatcher.data.database.CuriosityImageDao
import com.egabruskiy.curiositywatcher.data.model.CuriosityImage
import com.egabruskiy.curiositywatcher.data.model.CuriosityImageResponse
import com.egabruskiy.curiositywatcher.data.model.ResourceResult
import com.egabruskiy.curiositywatcher.data.network.CuriosityImageRemote
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow



class CuriosityImageRepository(private val remote:CuriosityImageRemote,
private val dao:CuriosityImageDao) {



    suspend fun insertAll(list: MutableList<CuriosityImage>){
        dao.insertImages(list)
    }

    fun getLast20Images(): Flow<ResourceResult<MutableList<CuriosityImageResponse>>> = flow {
        emit(ResourceResult.loading())

        val images = remote.getLastImages()
    //we need only 20
    while (images.size>20){
        images.removeFirst()
    }
        emit(ResourceResult.successOrEmpty(images))
    }.catch { e ->
        emit(ResourceResult.error(e))
    }


    fun getOfflineImageList(): Flow<ResourceResult<MutableList<CuriosityImage>>> = flow {
        emit(ResourceResult.loading())
        val images = dao.getLastTwenty()
        emit(ResourceResult.successOrEmpty(images))
    }.catch { e ->
        emit(ResourceResult.error(e))
    }


}