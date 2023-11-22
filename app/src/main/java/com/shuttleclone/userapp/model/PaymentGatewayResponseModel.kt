package com.shuttleclone.userapp.model

import com.google.gson.annotations.SerializedName

data class PaymentGatewayResponseModel(

	@field:SerializedName("success")
	val success: Boolean? = null,

	@field:SerializedName("payment_id")
	val paymentId: String? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("verification")
	val verification: String? = null,

	@field:SerializedName("status")
	val status: Boolean? = null
)
