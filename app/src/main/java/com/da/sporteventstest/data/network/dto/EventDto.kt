package com.da.sporteventstest.data.network.dto

import com.google.gson.annotations.SerializedName

data class EventDto (
    @SerializedName("id")
    var id : String,
    @SerializedName("title")
    var title : String,
    @SerializedName("subtitle")
    var subtitle : String,
    @SerializedName("date")
    var date : String,
    @SerializedName("imageUrl")
    var imageUrl : String,
    @SerializedName("videoUrl")
    var videoUrl : String
)
