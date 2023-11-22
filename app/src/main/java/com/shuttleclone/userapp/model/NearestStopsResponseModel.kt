package com.shuttleclone.userapp.model

import com.google.gson.annotations.SerializedName

data class NearestStopsResponseModel(

	@field:SerializedName("data")
	val data: List<NearestStopsItem>? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: Boolean? = null
)

data class NearestStopsItem(

	@field:SerializedName("lng")
	val lng: Any? = null,

	@field:SerializedName("near_distance")
	val nearDistance: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("type")
	val type: String? = null,

	@field:SerializedName("lat")
	val lat: Any? = null
)
