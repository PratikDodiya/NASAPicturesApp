package com.pd.nasapicturesapp.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class ImageResponse(
    var imageData: ArrayList<ImageData>
)

@Parcelize
data class ImageData(
    @SerializedName("copyright") val copyright: String = "",
    @SerializedName("date") val date: String = "",
    @SerializedName("explanation") val explanation: String = "",
    @SerializedName("hdurl") val hdurl: String = "",
    @SerializedName("media_type") val media_type: String = "",
    @SerializedName("service_version") val service_version: String = "",
    @SerializedName("title") val title: String = "",
    @SerializedName("url") val url: String = ""
) : Parcelable