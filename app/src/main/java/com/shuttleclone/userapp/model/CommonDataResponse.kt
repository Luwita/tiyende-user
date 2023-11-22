package com.shuttleclone.userapp.model

import com.google.gson.annotations.SerializedName

data class CommonDataResponse(

	@field:SerializedName("data")
	val data: CommonData? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("errorMessage")
	val errorMessage: String? = null,

	@field:SerializedName("status")
	val status: Boolean? = null
)

data class CommonData(

	@field:SerializedName("privacy_policy_url")
	val privacyPolicyUrl: String? = null,

	@field:SerializedName("referral_policy")
	val referralPolicy: String? = null,

	@field:SerializedName("apple_key_id")
	val appleKeyId: String? = null,

	@field:SerializedName("timezone")
	val timezone: String? = null,

	@field:SerializedName("fee")
	val fee: String? = null,

	@field:SerializedName("payments")
	val payments: Payments? = null,

	@field:SerializedName("apple_key")
	val appleKey: String? = null,

	@field:SerializedName("otp_validation_via")
	val otpValidationVia: Boolean? = null,

	@field:SerializedName("api_base_url")
	val apiBaseUrl: String? = null,

	@field:SerializedName("terms")
	val terms: String? = null,

	@field:SerializedName("background_location_update_interval")
	val backgroundLocationUpdateInterval: Int? = null,

	@field:SerializedName("logo")
	val logo: String? = null,

	@field:SerializedName("refund_amount")
	val refundAmount: Int? = null,

	@field:SerializedName("google_key")
	val googleKey: String? = null,

	@field:SerializedName("email")
	val email: String? = null,

	@field:SerializedName("default_payment")
	val defaultPayment: String? = null,

	@field:SerializedName("driver_online_location_update_interval")
	val driverOnlineLocationUpdateInterval: Int? = null,

	@field:SerializedName("address")
	val address: String? = null,

	@field:SerializedName("time_format")
	val timeFormat: String? = null,

	@field:SerializedName("refund_type")
	val refundType: String? = null,

	@field:SerializedName("refund_contents")
	val refundContents: String? = null,

	@field:SerializedName("tax")
	val tax: String? = null,

	@field:SerializedName("term_conditions_url")
	val termConditionsUrl: String? = null,

	@field:SerializedName("phone")
	val phone: String? = null,

	@field:SerializedName("apple_team_id")
	val appleTeamId: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("date_format")
	val dateFormat: String? = null,

	@field:SerializedName("default_currency")
	val defaultCurrency: String? = null,

	@field:SerializedName("default_country")
	val defaultCountry: String? = null
)

data class Payments(

	@field:SerializedName("is_enabled")
	val isEnabled: String? = null,

	@field:SerializedName("mode")
	val mode: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("currency")
	val currency: String? = null,

	@field:SerializedName("secret")
	val secret: String? = null,

	@field:SerializedName("key")
	val key: String? = null
)
