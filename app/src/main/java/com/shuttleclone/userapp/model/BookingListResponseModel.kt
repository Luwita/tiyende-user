package com.shuttleclone.userapp.model

import com.google.gson.annotations.SerializedName

data class BookingListResponseModel(

	@field:SerializedName("refund_alert")
	val refundAlert: String? = null,

	@field:SerializedName("data")
	val data: List<GetbookingData>? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("errorMessage")
	val errorMessage: String? = null,

	@field:SerializedName("status")
	val status: Boolean? = null
)

