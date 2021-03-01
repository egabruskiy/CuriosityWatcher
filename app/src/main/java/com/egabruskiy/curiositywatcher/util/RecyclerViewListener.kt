package com.egabruskiy.curiositywatcher.util

import com.egabruskiy.curiositywatcher.data.model.CuriosityImage


interface RecyclerViewListener {
    fun onClick( position: Int)
    fun onLongClick( image: CuriosityImage)
}