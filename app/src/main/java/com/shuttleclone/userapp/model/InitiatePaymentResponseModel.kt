package com.shuttleclone.userapp.model

import com.google.gson.annotations.SerializedName

data class InitiatePaymentResponseModel(
    @SerializedName("message")
    val message: String? = null,

    @SerializedName("link")
    val link: String? = null,

    @SerializedName("status")
    val isStatus: Boolean = false,

    @field:SerializedName("errorMessage")
    val errorMessage: String? = null,

)