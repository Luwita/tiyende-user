package com.shuttleclone.userapp.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class UserDetail : Serializable {
    @SerializedName("firstname")
    val firstname: String? = null

    @SerializedName("gender")
    val gender: String? = null

    @SerializedName("phone")
    val phone: Long = 0

    @SerializedName("email")
    val email: String? = null

    @SerializedName("lastname")
    val lastname: String? = null
}