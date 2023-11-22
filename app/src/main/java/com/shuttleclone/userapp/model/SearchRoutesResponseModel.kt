package com.shuttleclone.userapp.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class SearchRoutesResponseModel(

	@field:SerializedName("data")
	val data: RoutesData? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("errorMessage")
	val errorMessage: String? = null,

	@field:SerializedName("status")
	val status: Boolean? = null
):Serializable

data class RoutesItem(

	@field:SerializedName("bus_details")
	val busDetails: BusDetails? = null,

	@field:SerializedName("drop_stop_name")
	val dropStopName: String? = null,

	@field:SerializedName("busScheduleId")
	val busScheduleId: String? = null,

	@field:SerializedName("route_name")
	val routeName: String? = null,

	@field:SerializedName("route_busId")
	val routeBusId: String? = null,

	@field:SerializedName("drop_stop_id")
	val dropStopId: String? = null,

	@field:SerializedName("pickup_stop_departure_time")
	val pickupStopDepartureTime: String? = null,

	@field:SerializedName("routeId")
	val routeId: String? = null,

	@field:SerializedName("route_bus_timetable")
	val routeBusTimetable: List<String>? = null,

	@field:SerializedName("drop_stop_departure_time")
	val dropStopDepartureTime: String? = null,

	@field:SerializedName("drop_stop_arrival_time")
	val dropStopArrivalTime: String? = null,

	@field:SerializedName("total_of_stops")
	val totalOfStops: Int? = null,

	@field:SerializedName("pickup_stop_id")
	val pickupStopId: String? = null,

	@field:SerializedName("pickup_stop_name")
	val pickupStopName: String? = null,

	@field:SerializedName("pickup_stop_arrival_time")
	val pickupStopArrivalTime: String? = null

):Serializable

data class BusDetails(

	@field:SerializedName("amenities")
	val amenities: List<String>? = null,

	@field:SerializedName("code")
	val code: String? = null,

	@field:SerializedName("reg_no")
	val regNo: String? = null,

	@field:SerializedName("chassis_no")
	val chassisNo: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("brand")
	val brand: String? = null,

	@field:SerializedName("model_no")
	val modelNo: String? = null
):Serializable

data class RoutesData(

	@field:SerializedName("date")
	val date: String? = null,

	@field:SerializedName("getnearestData")
	val routes: List<RoutesItem>? = null
):Serializable
