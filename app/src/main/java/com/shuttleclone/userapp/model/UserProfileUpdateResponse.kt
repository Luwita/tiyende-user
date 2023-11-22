package com.shuttleclone.userapp.model

import com.google.gson.annotations.SerializedName

data class UserProfileUpdateResponse (
    @SerializedName("baseurl")
    val baseurl: String? = null,

    @SerializedName("data")
    val data: UpdatedProfileData? = null,

    @SerializedName("message")
    val message: String? = null,

    @field:SerializedName("errorMessage")
    val errorMessage: String? = null,

    @SerializedName("status")
    val isStatus: Boolean = false,
)