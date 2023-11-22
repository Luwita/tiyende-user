package com.shuttleclone.userapp.model

import com.google.gson.annotations.SerializedName

class SearchedDataItem(
    @field:SerializedName("title") val title: String,
    @field:SerializedName("location_address") val locationAddress: String,
    @field:SerializedName("location_latitude") val locationLatitude: Double,
    @field:SerializedName("location_longitude") val locationLongitude: Double,
    @field:SerializedName("id") val id: String,
    @field:SerializedName("state") val state: String,
    @field:SerializedName("city") val city: String,
    @field:SerializedName("type") val type: String,
    @field:SerializedName("near_distance") var nearDistance: String
)