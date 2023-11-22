package com.shuttleclone.userapp.model

import com.google.gson.annotations.SerializedName

data class ExploreRoutesResponseModel(

	@field:SerializedName("data")
	val data: List<RoutesDataItem>? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("errorMessage")
	val errorMessage: String? = null,

	@field:SerializedName("status")
	val status: Boolean? = null
)

data class StopsList(

	@field:SerializedName("arrival_time")
	val arrivalTime: String? = null,

	@field:SerializedName("lng")
	val lng: Any? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("stopId")
	val stopId: String? = null,

	@field:SerializedName("files")
	val images: List<String>? = null,

	@field:SerializedName("departure_time")
	val departureTime: String? = null,

	@field:SerializedName("lat")
	val lat: Any? = null
)

data class RoutesDataItem(

	@field:SerializedName("routeId")
	val routeId: String? = null,

	@field:SerializedName("route_title")
	val routeTitle: String? = null,

	@field:SerializedName("_id")
	val id: String? = null,

	@field:SerializedName("stops")
	val stops: List<StopsList>? = null
)
