package com.shuttleclone.userapp.ui.activity

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Paint
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.InputFilter
import android.text.InputFilter.AllCaps
import android.util.Log
import android.view.View
import android.widget.*
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.shuttleclone.userapp.BaseActivity
import com.shuttleclone.userapp.R
import com.shuttleclone.userapp.ViewModel.HomeFragmentViewModel
import com.shuttleclone.userapp.model.*
import com.shuttleclone.userapp.ui.activity.PassDetailsActivity.Companion.passDetails
import com.shuttleclone.userapp.utils.*
import com.google.gson.Gson
import com.razorpay.Checkout
import org.json.JSONObject


class ConfirmPaymentActivity : BaseActivity() {

    private val TAG = "ConfirmPaymentActivity"
    private var COMING_FROM = ""
    private var fareData: FareData? = null
    private var bookingData: BookingData? = null
    private val homeFragmentViewModel: HomeFragmentViewModel by viewModels()
    var passengerDetailsItem: ArrayList<PassengerDetailsItem> = ArrayList()

    private var tvBookingDate: TextView? = null
    private var tvSlocation: TextView? = null
    private var tvDlocation: TextView? = null
    private var tvStartTime: TextView? = null
    private var tvEndTime: TextView? = null
    private var tvBusDtl: TextView? = null
    private var tvSeatNo: TextView? = null
    private var tvBaseDiscountFare: TextView? = null
    private var checkboxWallet: CheckBox? = null
    private var tvWalletBalance: TextView? = null
    private var edPromoCode: EditText? = null
    private var btnApplyPromoCode: Button? = null
    private var tvTotalFare: TextView? = null
    private var btnConfirmPay: Button? = null

    private var layRemoveCode: LinearLayout? = null
    private var layApplyCode: LinearLayout? = null
    private var tvPromoCode: TextView? = null
    private var btnRemovePromoCode: Button? = null

    private var totalFare = 0
    private var seatNO = ""
    private var paymentMode = "UPI"
    private var online = "ONLINE"
    private var cash = "CASH"
    private var paymentType = "WALLET" //WALLET or booking
    private var paymentName = "Razorpay" //Paymob or razorpay
    private var WALLET = "WALLET"
    private var RAZPAY = "UPI"
    private var FREE = "FREE"
    private var pnrNumber = ""
    private var walletBalance = 0
    private var promoCode = ""
    private var discountAmount = "0"
    private var bookingDate = ""
    override fun onResume() {
        super.onResume()
        LocaleManager().setLocale(this) //This method update the language when the activity orientation changes
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LocaleManager().setLocale(this) //This method update the language when the activity orientation changes
        setContentView(R.layout.activity_confirm_payment)
        Checkout.preload(applicationContext)
        findViewById<View>(R.id.ivBack).setOnClickListener { onBackPressedDispatcher.onBackPressed() }

        val commonData = getCommonDataDetails(this@ConfirmPaymentActivity)
        commonData?.let {
            try {
                paymentName = it.defaultPayment.toString()
            } catch (e: Exception) {
                Log.i(TAG, "onCreate: Commdata fetch=${e.localizedMessage}")
            }
        }

        initLayouts()
        initializeListeners()

        handelBookingResponseViewModel()
        handelPaymentResponseViewModel()
        handelPassPaymentResponseViewModel()
        handelTripPaymentResponseViewModel()

        try {
            if (intent != null) {
                COMING_FROM = intent.getStringExtra("comingFrom").toString()
                mylog(TAG, "onCreate: COMING_FROM=" + COMING_FROM)
                bookingDate = getPreference(this, Constants.BOOKING_DATE)
                if (COMING_FROM.equals("Booking")) {
                    paymentType = "trip"
                    fareData = intent.getSerializableExtra("bookingFare") as FareData
                    seatNO = intent.getStringExtra("seatNo").toString()
                    passengerDetailsItem.add(
                        PassengerDetailsItem(
                            seatNO,
                            getPreference(this, Constants.GANDER),
                            getPreference(this, Constants.USER_NAME),
                            ""
                        )
                    )
                    createBookingRequest(passengerDetailsItem, fareData, "")
                } else if (COMING_FROM.equals("Pass")) {
                    paymentType = "pass"
                    seatNO = intent.getStringExtra("seatNo").toString()
                    showPassData()
                } else {
                    paymentType = "trip"
                    bookingData = intent.getSerializableExtra("bookingData") as BookingData
                    fareData = intent.getSerializableExtra("bookingFare") as FareData
                    addPassengerDetails(bookingData!!.persistedPassenger)
                    showData(bookingData!!)
                }
            }
        } catch (e: Exception) {
            mylog(TAG, "initLayouts: Error=${e.localizedMessage} ")
        }

    }


    private fun addPassengerDetails(passengers: List<PersistedPassengerItem?>?) {
        try {
            for (pas in passengers!!) {
                passengerDetailsItem.add(
                    PassengerDetailsItem(
                        pas?.seat,
                        pas?.gender,
                        pas?.fullname,
                        pas?.age
                    )
                )
            }
        } catch (e: Exception) {
            mylog(TAG, "addPassengerDetails: Error=${e.localizedMessage}")
        }
    }

    private fun showPassData() {
        try {
            tvBookingDate!!.text =
                convertDateToBeautify(getPreference(this, Constants.OFFICE_BOOKING_DATE))
            tvSlocation!!.text = getPreference(this, Constants.OFFICE_PICKUP_ADD)
            tvDlocation!!.text = getPreference(this, Constants.OFFICE_DROP_ADD)
            tvStartTime!!.text = getPreference(this, Constants.OFFICE_PICKUP_TIME)
            tvEndTime!!.text = getPreference(this, Constants.OFFICE_DROP_TIME)
            tvBusDtl!!.text = getPreference(this, Constants.OFFICE_BUS_NAME)
            tvSeatNo!!.text = seatNO
            tvWalletBalance!!.text = defaultCurrency + getPreference(this, Constants.WALLET_BALANCE)
            tvTotalFare!!.text = defaultCurrency + passDetails!!.totalFare
            totalFare = passDetails!!.totalFare!!.toInt()
            walletBalance = getPreference(this, Constants.WALLET_BALANCE).toInt()
            hideView(layApplyCode)
        } catch (e: Exception) {
            mylog(TAG, "showPassData: Error=${e.localizedMessage}")
        }
    }

    private fun initiatePassPayment() {
        try {
            paymentMode = online
            if (isNetworkAvailable(this!!)) {
                LoadingDialog.showLoadingDialog(this, getString(R.string.pls_wait_loading))
                homeFragmentViewModel!!.initiatePassPayment(
                    this,
                    getPreference(this, Constants.TOKEN),
                    paymentType,
                    paymentName,
                    getPreference(this, Constants.BUS_ID),
                    getPreference(this, Constants.ROUTE_ID),
                    getPreference(this, Constants.ROUTE_TIMETABLE_ID),
                    getPreference(this, Constants.PICKUP_ID),
                    getPreference(this, Constants.DROP_ID),
                    passDetails!!.passId.toString(),
                    passDetails!!.passNoOfRides.toString(),
                    passDetails!!.totalFare.toString(),
                    seatNO,
                    getPreference(this, Constants.HAS_RETURN),
                    paymentMode,
                    bookingDate
                )
            } else toast(this)
        } catch (e: java.lang.Exception) {
            mylog(TAG, "initiatePassPayment: Error=${e.localizedMessage}")
        }
    }


    private fun initializeListeners() {
        btnConfirmPay!!.clickWithThrottle {
            if (checkData()) {
                if (totalFare>0) {
                    if (!COMING_FROM.equals("Pass"))
                        if (paymentMode == WALLET)
                            payRideFee()
                        else initiateTripPayment()
                    else initiatePassPayment()
                }else payRideFee()
            }
        }

        btnRemovePromoCode!!.clickWithThrottle {
            hideView(layRemoveCode)
            showView(layApplyCode)
            promoCode = ""
            edPromoCode!!.setText("")
            createBookingRequest(passengerDetailsItem, fareData, "")
        }

        btnApplyPromoCode!!.clickWithThrottle {
            promoCode = edPromoCode!!.text.toString()
            if (!promoCode.equals(""))
                createBookingRequest(passengerDetailsItem, fareData, promoCode)
            else {
                edPromoCode!!.error = getString(R.string.field_not_empty)
                edPromoCode!!.requestFocus()
            }
        }

        checkboxWallet!!.setOnCheckedChangeListener { compoundButton, isChecked ->
            if (isChecked) paymentMode = WALLET
            else paymentMode = RAZPAY
        }
    }

    private fun payRideFee() {
        try {
            if (isNetworkAvailable(this!!)) {
                LoadingDialog.showLoadingDialog(this, getString(R.string.pls_wait_loading))
                homeFragmentViewModel!!.payRouteFee(
                    this,
                    getPreference(this, Constants.TOKEN),
                    pnrNumber,
                    totalFare.toString(),
                    paymentMode,
                    bookingDate
                )
            } else toast(this)
        } catch (e: java.lang.Exception) {
            mylog(TAG, "payRideFee: Error=${e.localizedMessage}")
        }
    }

    private fun initiateTripPayment() {
        try {
            if (isNetworkAvailable(this!!)) {
                LoadingDialog.showLoadingDialog(this, getString(R.string.pls_wait_loading))
                paymentMode = online

                homeFragmentViewModel!!.initiateTripPayment(
                    this,
                    getPreference(this, Constants.TOKEN),
                    paymentType,
                    paymentName,
                    totalFare.toFloat(),
                    paymentMode,
                    pnrNumber
                )
            } else toast(this)
        } catch (e: java.lang.Exception) {
            mylog(TAG, "payRideFee: Error=${e.localizedMessage}")
        }
    }

    private fun checkData(): Boolean {
        if (paymentMode.equals(WALLET)) {
            return if (totalFare > walletBalance) {
                toast(this, getString(R.string.no_sufficient_wallet_balance))
                false
            } else true
        }

        if (!COMING_FROM.equals("Pass"))
            if (pnrNumber.equals("")) return false
        return true
    }

    private fun initLayouts() {
        tvBookingDate = findViewById(R.id.tvBookingDate)
        tvSlocation = findViewById(R.id.tvSlocation)
        tvDlocation = findViewById(R.id.tvDlocation)
        tvStartTime = findViewById(R.id.tvStartTime)
        tvEndTime = findViewById(R.id.tvEndTime)
        tvBusDtl = findViewById(R.id.tvBusDtl)
        tvSeatNo = findViewById(R.id.tvSeatNo)
        checkboxWallet = findViewById(R.id.checkboxWallet)
        tvWalletBalance = findViewById(R.id.tvWalletBalance)
        edPromoCode = findViewById(R.id.edPromoCode)
        btnApplyPromoCode = findViewById(R.id.btnApplyPromoCode)
        tvTotalFare = findViewById(R.id.tvTotalFare)
        tvBaseDiscountFare = findViewById(R.id.tvBaseDiscountFare)
        btnConfirmPay = findViewById(R.id.btnConfirmPay)
        layRemoveCode = findViewById(R.id.layRemoveCode)
        layApplyCode = findViewById(R.id.layApplyCode)
        tvPromoCode = findViewById(R.id.tvPromoCode)
        btnRemovePromoCode = findViewById(R.id.btnRemovePromoCode)
        edPromoCode!!.filters = arrayOf<InputFilter>(AllCaps())
        tvTotalFare!!.text = "$defaultCurrency 0.0"

    }


    private fun createBookingRequest(
        passengerDetailsItem: ArrayList<PassengerDetailsItem>,
        fareData: FareData?,
        offer_code: String?
    ) {
        try {
            if (isNetworkAvailable(this!!)) {
                LoadingDialog.showLoadingDialog(this, getString(R.string.pls_wait_loading))
                homeFragmentViewModel!!.createBooking(
                    this,
                    getPreference(this, Constants.TOKEN),
                    BookingRequestData(passengerDetailsItem, offer_code, fareData!!)
                )
            } else toast(this)
        } catch (e: java.lang.Exception) {
            mylog(TAG, "createBookingRequest: Error=${e.localizedMessage}")
        }
    }

    private fun handelTripPaymentResponseViewModel() {
        homeFragmentViewModel!!.initiateTripPayment.observe(this, Observer {
            LoadingDialog.cancelLoading()
            try {
                if (it == null)
                    return@Observer

                if (it.isStatus!!) {

                    val intent = Intent(this, WebViewPayment::class.java)
                    intent.putExtra("gateway_url", it.link)
                    payTripLauncher.launch(intent)

                } else alertDialog(this, it.message.toString())

            } catch (e: Exception) {
                alertDialog(this, e.localizedMessage)
            }
        })
    }

    private fun handelPassPaymentResponseViewModel() {

        homeFragmentViewModel!!.initiatePassPayment.observe(this, Observer {
            LoadingDialog.cancelLoading()
            try {
                if (it == null)
                    return@Observer

                if (it.isStatus!!) {

                    val intent = Intent(this, WebViewPayment::class.java)
                    intent.putExtra("gateway_url", it.link)
                    payTripLauncher.launch(intent)

                } else alertDialog(this, it.message.toString())

            } catch (e: Exception) {
                alertDialog(this, e.localizedMessage)
            }
        })
    }

    private fun handelPaymentResponseViewModel() {

        homeFragmentViewModel!!.paymentResponse.observe(this, Observer {
            LoadingDialog.cancelLoading()
            try {
                if (it == null)
                    return@Observer

                if (it.status!!) {
                    when (it.data!!.paymentMode) {
                        WALLET, FREE -> {
                            paymentResponse(it.status, it.message!!)
                        }

                        RAZPAY -> {
                            makePayment(it.data!!)
                        }
                    }

                } else alertDialog(this, it.message.toString())

            } catch (e: Exception) {
                alertDialog(this, e.localizedMessage)
            }
        })
    }

    private fun handelBookingResponseViewModel() {
        homeFragmentViewModel!!.bookingResponse.observe(this, Observer {
            LoadingDialog.cancelLoading()
            try {
                if (it == null)
                    return@Observer

                if (it?.status!!) {
                    showData(it.data!!)
                } else {
                    alertDialog(this, it.message.toString())
                    edPromoCode!!.setText("")
                }

            } catch (e: Exception) {
                alertDialog(this, e.localizedMessage)
            }
        })
    }

    var payTripLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                // There are no request codes
                try {

                    val data: Intent? = result.data

                    if (null != data && data.hasExtra("response")) {
                        Log.i(TAG, "payment data response:= ${data?.getStringExtra("response")}")

                        val payResponse = Gson().fromJson(
                            data.getStringExtra("response").toString(),
                            PaymentGatewayResponseModel::class.java
                        )

                        paymentResponse(payResponse.success!!, payResponse.message!!)

                    } else if (null != data && data.hasExtra("status") && data.hasExtra("message")) paymentResponse(
                        false,
                        data.getStringExtra("message").toString()
                    )
                    else paymentResponse(false, getString(R.string.txt_Something_wrong))
                } catch (e: Exception) {
                    successFailureDialog(this, false, getString(R.string.txt_Something_wrong))
                }
            }
        }

    private fun paymentResponse(status: Boolean, message: String) {
        successFailureDialog(this@ConfirmPaymentActivity, status, message)
    }

    fun makePayment(data: InitiateData) {
        try {
            // initialize Razorpay account.
            val checkout = Checkout()
            checkout.setKeyID(data.paymentSettings!!.key)

            // set image
            checkout.setImage(R.drawable.ic_app_logo)
            val options = JSONObject()
            options.put("name", data.paymentSettings!!.textName)
            options.put("description", data.paymentSettings!!.description)
            options.put("image", data.paymentSettings!!.logo)
            options.put("order_id", data.orderId);//from response of step 3.
            options.put("theme.color", data.paymentSettings!!.themeColor)
            options.put("currency", data.paymentSettings!!.currency)
            options.put("amount", data.amount) //pass amount in currency subunits
            options.put("prefill.email", data.prefill!!.email)
            options.put("prefill.contact", data.prefill!!.contact)
            options.put("send_sms_hash", true)
            options.put("allow_rotation", false)
            val retryObj = JSONObject()
            retryObj.put("enabled", false)
            retryObj.put("max_count", 0)
            options.put("retry", retryObj)

            checkout.open(this, options)

        } catch (e: Exception) {
            mylog(TAG, "makePayment: Error=" + e.localizedMessage)
        }
    }

    private fun showData(bookingData: BookingData) {
        try {
            mylog(TAG, "showData: bookingData=${Gson().toJson(bookingData)}")
            if (bookingData != null) {
                val details = bookingData.getbookingData!!
                tvBookingDate!!.text = convertDateToBeautify(details.startDate!!)
                tvSlocation!!.text = details.pickupName
                tvDlocation!!.text = details.dropName
                tvStartTime!!.text = details.startTime
                tvEndTime!!.text = details.dropTime
                tvBusDtl!!.text = details.busName
                tvSeatNo!!.text = details.seatNos.toString()
                tvWalletBalance!!.text = defaultCurrency + bookingData.walletBalance
                tvTotalFare!!.text = defaultCurrency + details.finalTotalFare
                totalFare = details.finalTotalFare!!.toInt()
                walletBalance = bookingData.walletBalance!!
                pnrNumber = details.pnrNo!!
                discountAmount = details.discount.toString()

                if (discountAmount != "0" && discountAmount != "0.0") {
                    showView(tvBaseDiscountFare)
                    tvBaseDiscountFare!!.text = defaultCurrency + details.finalTotalFare
                    tvBaseDiscountFare!!.paintFlags =
                        tvBaseDiscountFare!!.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG

                    try {
                        val amount = details.finalTotalFare.toInt() - details.discount!!.toInt()
                        tvTotalFare!!.text = "$defaultCurrency$amount"
                        totalFare = amount
                        if (totalFare == 0) {
                            checkboxWallet!!.isEnabled = false
                            paymentMode = FREE
                        }
                    } catch (e: Exception) {
                        mylog(TAG, "showData: discount Error=${e.localizedMessage}")
                    }

                    if (!promoCode.equals("")) {
                        hideView(layApplyCode)
                        showView(layRemoveCode)
                        tvPromoCode!!.text = "${getString(R.string.txt_applied)}: $promoCode"
                    }
                } else {
                    paymentMode = RAZPAY
                    checkboxWallet!!.isEnabled = true
                    hideView(tvBaseDiscountFare)
                    if (!promoCode.equals("")) {
                        toast(this, getString(R.string.promo_code_is_not_valid))
                        promoCode = ""
                        edPromoCode!!.setText("")
                    }
                }

            }
        } catch (e: Exception) {
            mylog(TAG, "showData: Error=${e.localizedMessage}")
        }

    }

    fun successFailureDialog(context: Context?, isSuccess: Boolean?, msg: String) {
        try {

            val dialog = Dialog(context!!)
            if (isSuccess!!) dialog.setContentView(R.layout.dialog_success)
            else dialog.setContentView(R.layout.dialog_failure)
            dialog.setCancelable(true)
            dialog.window?.setBackgroundDrawable(ColorDrawable(0))
            dialog.findViewById<TextView>(R.id.tvMsg).text = msg
            dialog.findViewById<View>(R.id.btnClose).setOnClickListener {
                if (isSuccess) {
                    val i = Intent(this, DashboardActivity::class.java)
                    i.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
                    i.putExtra("openBookingList", "YES")
                    startActivity(i)
                }

                if (dialog.isShowing) dialog.dismiss()
            }
            dialog.setCancelable(false)
            dialog.setCanceledOnTouchOutside(false)
            dialog.show()

        } catch (e: Exception) {
            mylog(TAG, "successFailureDialog: Error=${e.localizedMessage}")
        }
    }

}