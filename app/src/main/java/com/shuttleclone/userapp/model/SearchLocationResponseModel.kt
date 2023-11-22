package com.shuttleclone.userapp.model

import com.google.gson.annotations.SerializedName

data class SearchLocationResponseModel (
    @SerializedName("data")
    val data: MutableList<SearchedDataItem>? = null,

    @SerializedName("message")
    val message: String? = null,

    @field:SerializedName("errorMessage")
    val errorMessage: String? = null,

    @SerializedName("status")
    val isStatus: Boolean = false,
)