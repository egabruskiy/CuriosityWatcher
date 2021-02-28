package com.egabruskiy.curiositywatcher.data.model

import com.google.gson.annotations.SerializedName


//{"id":424926,"sol":1000,
//    "camera":{"id":22,"name":"MAST","rover_id":5,"full_name":"Mast Camera"},
//    "img_src":"http://mars.jpl.nasa.gov/msl-raw-images/msss/01000/mcam/1000ML0044631200305217E01_DXXX.jpg",
//    "earth_date":"2015-05-30",
//    "rover":{"id":5,"name":"Curiosity","landing_date":"2012-08-06","launch_date":"2011-11-26","status":"active"}


data class NasaResponse(
        @SerializedName("photos") val photos: MutableList<CuriosityImageResponse>)

data class CuriosityImageResponse(
    @SerializedName("img_src") val url: String,
    @SerializedName("id") val id: String,
    @SerializedName("earth_date") val earth_date: String)
