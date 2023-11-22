package com.shuttleclone.userapp.model

import com.google.gson.annotations.SerializedName

data class NotificationDataResponse(

	@field:SerializedName("data")
	val data: List<NotificationsDataItem>? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: Boolean? = null
)

data class NotificationsDataItem(

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("type")
	val type: String? = null,

	@field:SerializedName("content")
	val content: String? = null
)
