package com.shuttleclone.userapp.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class UserVerificationResponseModel : Serializable {
    @SerializedName("flag")
    val flag = 0

    @SerializedName("csrfToken")
    val csrfToken: String? = null

    @SerializedName("userDetail")
    val userDetail: UserDetail? = null

    @SerializedName("otp")
    val otp = 0

    @SerializedName("message")
    val message: String? = null

    @SerializedName("errorMessage")
    val errorMessage: String? = null

    @SerializedName("status")
    val isStatus = false

    @SerializedName("token")
    val token: String? = null
}