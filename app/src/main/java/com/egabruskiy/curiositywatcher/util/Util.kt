package com.egabruskiy.curiositywatcher.util

import com.egabruskiy.curiositywatcher.data.model.CuriosityImage
import com.egabruskiy.curiositywatcher.data.model.CuriosityImageResponse

class Util {

    companion object{

        fun convertResponseImageList(responseList: MutableList<CuriosityImageResponse>):MutableList<CuriosityImage>{
                val curiosityImages: MutableList<CuriosityImage> = mutableListOf()

            for(image:CuriosityImageResponse in responseList){
                curiosityImages.add(CuriosityImage(image.id,image.url))
            }

            return curiosityImages
        }

    }
}