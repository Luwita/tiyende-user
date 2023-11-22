package com.shuttleclone.userapp.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class UserRegisterResponseModel (
    @SerializedName("flag")
    val flag: Int = 0,

    @SerializedName("csrfToken")
    val csrfToken: String? = null,

    @SerializedName("otp")
    val otp: Int = 0,

    @SerializedName("title")
    val title: String? = null,

    @field:SerializedName("errorMessage")
    val errorMessage: String? = null,

    @SerializedName("status")
    val isStatus: Boolean = false,

    @SerializedName("token")
    val token: String? = null
): Serializable