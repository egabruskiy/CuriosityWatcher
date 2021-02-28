package com.egabruskiy.curiositywatcher.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize



@Entity(tableName = "images")
@Parcelize
data class CuriosityImage (
    @PrimaryKey val id: String,
    val url: String
) : Parcelable

