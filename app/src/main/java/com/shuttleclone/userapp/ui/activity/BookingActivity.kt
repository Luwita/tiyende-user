package com.shuttleclone.userapp.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.shuttleclone.userapp.BaseActivity
import com.shuttleclone.userapp.R
import com.shuttleclone.userapp.ViewModel.HomeFragmentViewModel
import com.shuttleclone.userapp.model.BusSeatsItem
import com.shuttleclone.userapp.model.EdgeItem
import com.shuttleclone.userapp.model.EmptyItem
import com.shuttleclone.userapp.model.FareData
import com.shuttleclone.userapp.model.PassesList
import com.shuttleclone.userapp.model.SeatModel
import com.shuttleclone.userapp.model.SeatType
import com.shuttleclone.userapp.ui.adapter.SeatAdapter
import com.shuttleclone.userapp.utils.Constants
import com.shuttleclone.userapp.utils.Constants.BOOKED
import com.shuttleclone.userapp.utils.Constants.BOOKING_DATE
import com.shuttleclone.userapp.utils.Constants.BOOKING_END_DATE
import com.shuttleclone.userapp.utils.Constants.EMPTY
import com.shuttleclone.userapp.utils.Constants.IsCheckedOffice
import com.shuttleclone.userapp.utils.LoadingDialog
import com.shuttleclone.userapp.utils.LocaleManager
import com.shuttleclone.userapp.utils.alertDialog
import com.shuttleclone.userapp.utils.getPreference
import com.shuttleclone.userapp.utils.goHome
import com.shuttleclone.userapp.utils.isPreference
import com.shuttleclone.userapp.utils.menu.AbstractItem
import com.shuttleclone.userapp.utils.mylog
import com.shuttleclone.userapp.utils.savePreference
import com.shuttleclone.userapp.utils.toast
import java.io.Serializable

class BookingActivity : BaseActivity(), View.OnClickListener {

    private var TAG = "BookingActivity"
    private var mSeatNo = 0
    private var mSeatNoSleeper = 0
    private var mRvViewSeats: RecyclerView? = null
    private var mIvAvailable: ImageView? = null
    private var mIcBook: ImageView? = null
    private var mIcSelect: ImageView? = null
    private var mIvLadies: ImageView? = null
    private var mIvHome: ImageView? = null
    private var mLinear: LinearLayout? = null
    private var mLlDack: LinearLayout? = null
    private var mLlDynamic: LinearLayout? = null
    private var paymentProcessDialog: BottomSheetDialog? = null
    private var mAbstractItemsList: MutableList<AbstractItem>? = null
    private var mBusSeatsList: MutableList<BusSeatsItem>? = null
    private var mSeatModelsItemsList: MutableList<SeatModel>? = null
    private var mBtnBook: Button? = null
    private var mTvLower: TextView? = null
    private var mTvUpper: TextView? = null
    private var mIvBack: ImageView? = null
    private val homeFragmentViewModel: HomeFragmentViewModel by viewModels()

    private var mSelectedSeats: ArrayList<String>? = ArrayList()
    private var passesList: List<PassesList>? = ArrayList()

    private var stops = 0
    private var seatPrice = 0
    private var tax = 0.0

    companion object {
        /*variable declaration*/
        @JvmField
        var routeId = ""

        @JvmField
        var routeTimetableId = ""

        @JvmField
        var pickupId = ""

        @JvmField
        var dropId = ""

        @JvmField
        var busId = ""

        @JvmField
        var has_return = ""

        @JvmField
        var bookingDate = ""

        @JvmField
        var bookingEndDate = ""

        @JvmField
        var bookingType = ""

        @JvmField
        var mCountSeat = 0

        @JvmField
        var mTotal = 0

        @JvmField
        var mSb: StringBuffer? = null

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LocaleManager().setLocale(this) //This method update the language when the activity orientation changes
        setContentView(R.layout.activity_booking)
        initLayouts()
        initializeLayouts()
        handelViewModel()

    }

    override fun onResume() {
        super.onResume()
        LocaleManager().setLocale(this) //This method update the language when the activity orientation changes
    }

    private fun handelViewModel() {
        homeFragmentViewModel!!.getRouteFare.observe(this, androidx.lifecycle.Observer {
            LoadingDialog.cancelLoading()
            try {
                if (it == null)
                    return@Observer

                if (it.status!!) {
                    try {
                        selectPaymentProcess(it.data)
                    } catch (e: Exception) {
                        mylog(TAG, e.localizedMessage)
                    }
                } else alertDialog(this, it.message.toString())
            } catch (e: Exception) {
                alertDialog(this, e.localizedMessage)
            }
        })

        homeFragmentViewModel!!.busSeatsResponse.observe(this, androidx.lifecycle.Observer {
            LoadingDialog.cancelLoading()
            try {
                if (it == null)
                    return@Observer

                if (it.status!! && it.data != null) {

                    seatPrice = (it.data!!.final_total_fare).toString().toInt()
                    tax = (it.data!!.taxAmount).toString().toDouble()
                    initializeSeats(it.data!!.buslayout!!.combineSeats)

                    if (bookingType.equals("office")) {
                        passesList = it.data.passesResponseModel
                        savePreference(this, Constants.OFFICE_PICKUP_ADD, it.data!!.pickupName)
                        savePreference(this, Constants.OFFICE_DROP_ADD, it.data!!.dropName)
                        savePreference(this, Constants.OFFICE_BOOKING_DATE, it.data!!.createdDate)
                        savePreference(this, Constants.OFFICE_PICKUP_TIME, it.data!!.pickupTime)
                        savePreference(this, Constants.OFFICE_DROP_TIME, it.data!!.dropTime)
                        savePreference(this, Constants.OFFICE_BUS_NAME, it.data!!.busName)
                        savePreference(this, Constants.WALLET_BALANCE, it.data!!.userWalletAmount)
                    }

                } else alertDialog(this, it.message.toString())

            } catch (e: Exception) {
                alertDialog(this, e.localizedMessage)
            }
        })
    }

    /* initialize */
    private fun initializeLayouts() {
        mBtnBook!!.setOnClickListener(this)
        mTvLower!!.setOnClickListener(this)
        mTvUpper!!.setOnClickListener(this)
        mIvBack!!.setOnClickListener(this)
        mIvHome!!.setOnClickListener(this)
        mBtnBook!!.stateListAnimator = null
        val mCount = stops
        var i = 0
        while (i < mCount) {
            i++
            val view1 = layoutInflater.inflate(R.layout.layout_hold, mLlDynamic, false)
            val mIvHold = view1.findViewById<ImageView>(R.id.ivHold)
            mIvHold.setOnClickListener { toast(this,getString(R.string.text_city)) }
            mLlDynamic!!.addView(view1)
        }
    }

    /* bind view ids */
    private fun initLayouts() {

        mBtnBook = findViewById(R.id.btnBook)
        mLinear = findViewById(R.id.llOffer)
        mIvAvailable = findViewById(R.id.ivAvailable)
        mIvLadies = findViewById(R.id.icLadies)
        mIcBook = findViewById(R.id.icBook)
        mIcSelect = findViewById(R.id.icSelect)
        mIvHome = findViewById(R.id.ivHome)
        mAbstractItemsList = ArrayList()
        mBusSeatsList = ArrayList()
        mSeatModelsItemsList = ArrayList()
        mSb = StringBuffer()
        mRvViewSeats = findViewById(R.id.rvSeat)
        mLlDack = findViewById(R.id.lvDack)
        mTvLower = findViewById(R.id.tvLower)
        mTvUpper = findViewById(R.id.tvUpper)
        mLlDynamic = findViewById(R.id.llDynamicContent)
        mIvBack = findViewById(R.id.ivBack)

        try {
            if (intent != null) {
                routeId = intent.getStringExtra("routeId").toString()
                routeTimetableId = intent.getStringExtra("route_timetableId").toString()
                pickupId = intent.getStringExtra("pickupId").toString()
                dropId = intent.getStringExtra("dropId").toString()
                busId = intent.getStringExtra("busId").toString()
                stops = intent.getIntExtra("stops",0)
                bookingType = intent.getStringExtra("bookingType") + ""
                has_return = intent.getStringExtra("has_return") + ""
                bookingDate = getPreference(this, BOOKING_DATE) + ""
                bookingEndDate = getPreference(this, BOOKING_END_DATE) + ""
                mylog(TAG, "initLayouts: bookingDate=$bookingDate")

                if (busId != "" && routeId != ""&& routeTimetableId != "" && pickupId != "" && dropId != "") {
                    LoadingDialog.showLoadingDialog(this, getString(R.string.pls_wait_loading))
                    homeFragmentViewModel!!.busSeats(
                        this,
                        getPreference(this, Constants.TOKEN),
                        busId,
                        routeId,
                        routeTimetableId,
                        pickupId,
                        dropId,
                        bookingType,
                        has_return,
                        bookingDate,
                        bookingEndDate
                    )
                } else toast(this, getString(R.string.txt_Something_wrong))

                savePreference(this, Constants.ROUTE_ID, routeId)
                savePreference(this, Constants.ROUTE_TIMETABLE_ID, routeTimetableId)
                savePreference(this, Constants.PICKUP_ID, pickupId)
                savePreference(this, Constants.DROP_ID, dropId)
                savePreference(this, Constants.BUS_ID, busId)
                savePreference(this, Constants.BOOKING_TYPE, bookingType)
                savePreference(this, Constants.HAS_RETURN, has_return)

            }

        } catch (e: Exception) {
            mylog(TAG, "onCreate: error=${e.localizedMessage}")
        }
    }


    /* initialize seats */
    private fun initializeSeats(combineSeats: List<List<BusSeatsItem>>?) {
        try {
            val mCOLUMNS = combineSeats!!.size
            mylog(TAG, "SIze=${mCOLUMNS}")
            mIvAvailable!!.setColorFilter(ContextCompat.getColor(this, R.color.view_color))
            mIcBook!!.setColorFilter(ContextCompat.getColor(this, R.color.dark_gray))
            mIcSelect!!.setColorFilter(ContextCompat.getColor(this, R.color.colorPrimary))
            mIvLadies!!.setColorFilter(ContextCompat.getColor(this, R.color.pink))

            if (mCOLUMNS != 0) {

                for (i in 0..combineSeats!![0].size - 1) {

                    for (j in 0..mCOLUMNS - 1) {

                        if (combineSeats!![j].size != 0) {
                            mBusSeatsList!!.add(combineSeats!![j][i])

                            when (combineSeats!![j][i].seatStatus) {
                                EMPTY -> {
                                    if (combineSeats!![j][i].isladies!!) {
                                        mSeatModelsItemsList!!.add(SeatModel(SeatType.LADIES))
                                    } else mSeatModelsItemsList!!.add(SeatModel(SeatType.EMPTY))
                                }

                                BOOKED -> {
                                    mSeatModelsItemsList!!.add(SeatModel(SeatType.BOOKED))
                                }
                            }
                            mAbstractItemsList!!.add(EdgeItem(combineSeats!![j][i].seatNo))
                        } else {
                            mBusSeatsList!!.add(BusSeatsItem())
                            mAbstractItemsList!!.add(EmptyItem(mSeatModelsItemsList))
                            mSeatModelsItemsList!!.add(SeatModel(SeatType.EMPTY))
                        }

                    }

                }


                mylog(TAG, "SIze2=${mBusSeatsList!!.size}")

                for (i in 0..mBusSeatsList!!.size) {
                    val mManager = GridLayoutManager(this, mCOLUMNS)
                    mRvViewSeats!!.layoutManager = mManager
                    val adapter = SeatAdapter({ count, label ->
                        if (count == 0) {
                            hideView(mLinear)
                            mSelectedSeats!!.clear()
                            mSb!!.setLength(0)
                        } else {
                            mCountSeat = count
                            showView(mLinear)
                            mSb!!.append("$label ")
                            getSelectedSeats(count - 1, label)

                            val totalTax = tax * count
                            mTotal = (seatPrice * count)

                            val mTvTotalPrice = findViewById<TextView>(R.id.txtTicketPrice)
                            val mTxTax = findViewById<TextView>(R.id.txTax)
                            mTvTotalPrice.text = String.format("%s%s",
                                defaultCurrency,
                                String.format("%.2f", (mTotal - totalTax)))

                            if (totalTax > 0)
                                mTxTax.text =
                                    String.format("%s%s", defaultCurrency, totalTax.toString())
                            else mTxTax.visibility = View.GONE

                            (findViewById<View>(R.id.tvTotal) as TextView).text =
                                String.format("%s%s", defaultCurrency, (mTotal).toString())

                        }
                    }, mAbstractItemsList, this, mSeatModelsItemsList)
                    mRvViewSeats!!.adapter = adapter
                }
            } else toast(this, getString(R.string.txt_Something_wrong))
        } catch (e: Exception) {
            mylog(TAG, "initializeSeats: Error=${e.localizedMessage}")
        }
        mSeatNo = 0
        mSeatNoSleeper = 0
    }

    private fun getSelectedSeats(count: Int, label: String) {
        try {

            if (mSelectedSeats!!.contains(label)) {
                mSelectedSeats!!.remove(label)
            } else
                mSelectedSeats!!.add(count, label)

        } catch (e: Exception) {
            mylog(TAG, "getSelectedSeats: error=${e.localizedMessage}")
        }

    }


    /* on click listener */
    override fun onClick(v: View) {
        if (v === mBtnBook) generateFare() else if (v === mTvUpper) {
            mTvUpper!!.background = resources.getDrawable(R.drawable.bg_leftswitch_select)
            mTvUpper!!.setTextColor(resources.getColor(R.color.white))
            mTvLower!!.setTextColor(resources.getColor(R.color.textheader))
            mTvLower!!.background = resources.getDrawable(R.drawable.bg_rightswitch)
        } else if (v === mTvLower) {
            mTvLower!!.background = resources.getDrawable(R.drawable.bg_rightswitch_select)
            mTvLower!!.setTextColor(resources.getColor(R.color.white))
            mTvUpper!!.setTextColor(resources.getColor(R.color.textheader))
            mTvUpper!!.background = resources.getDrawable(R.drawable.bg_leftswitch)
        } else if (v === mIvBack)  onBackPressedDispatcher.onBackPressed() else if (v === mIvHome) goHome(
            applicationContext
        )
    }

    private fun generateFare() {
        try {
            mylog(TAG, "mSelectedSeats mSelectedSeats=" + mSelectedSeats.toString())
            mylog(TAG, "mSelectedSeats mSb=" + mSb.toString())

            var bookedSeats=""
            for (seat in mSelectedSeats!!){
                bookedSeats+=seat+" "
            }
             bookedSeats=(bookedSeats.trim()).replace(" ",",")

            mylog(TAG, "mSelectedSeats bookedSeats=$bookedSeats")

            if (busId != "" && routeId != ""&& routeTimetableId != "" && pickupId != "" && dropId != "") {
                LoadingDialog.showLoadingDialog(this, getString(R.string.pls_wait_loading))
                homeFragmentViewModel!!.getRouteFare(
                    this,
                    getPreference(this, Constants.TOKEN),
                    busId,
                    routeId,
                    routeTimetableId,
                    pickupId,
                    dropId,
                    bookedSeats,
                    getPreference(this, Constants.BOOKING_DATE),
                    has_return
                )
            } else toast(this, getString(R.string.txt_Something_wrong))

        } catch (e: Exception) {
            mylog(TAG, e.localizedMessage)
        }
    }


    private fun selectPaymentProcess(data: FareData?) {
        val view: View = layoutInflater.inflate(R.layout.layout_payment_process_type, null)
        paymentProcessDialog = BottomSheetDialog(this, R.style.CustomBottomSheetDialogTheme)

        if (!isPreference(
                this,
                IsCheckedOffice
            )
        ) view.findViewById<LinearLayout>(R.id.layBuyPass).visibility = View.GONE
        else if (mSelectedSeats!!.size > 1) view.findViewById<LinearLayout>(R.id.layBuyPass).visibility =
            View.GONE


        view.findViewById<TextView>(R.id.tvTotalFare).setText(
            "${getString(R.string.txt_proceed_to_pay)} " + defaultCurrency + mTotal + " ${
                getString(R.string.txt_for_this_ride)
            }"
        )

        val bundle = Bundle()
        if (data != null) {
            bundle.putSerializable("bookingFare", data!!)
        }

        view.findViewById<LinearLayout>(R.id.layPayPerRide).setOnClickListener {
            paymentProcessDialog?.dismiss()
            LocaleManager().setLocale(this)
            if (isPreference(this, IsCheckedOffice)) {
                val intent = Intent(this, ConfirmPaymentActivity::class.java)
                intent.putExtra("comingFrom", "Booking")
                intent.putExtra("seatNo", mSelectedSeats.toString())
                intent.putExtras(bundle)
                startActivity(intent)

            } else {
                if (mCountSeat > 1) {
                    val intent = Intent(this, PassengerDetailActivity::class.java)
                    intent.putExtra("seatNo", mSelectedSeats)
                    intent.putExtras(bundle)
                    startActivity(intent)
                } else {
                    val intent = Intent(this, ConfirmPaymentActivity::class.java)
                    intent.putExtra("comingFrom", "Booking")
                    intent.putExtra("seatNo", mSelectedSeats.toString())
                    intent.putExtras(bundle)
                    startActivity(intent)
                }
            }


        }

        view.findViewById<LinearLayout>(R.id.layBuyPass).setOnClickListener {
            savePreference(this, Constants.SEAT_NO, mSelectedSeats.toString())
            LocaleManager().setLocale(this)
            val intent = Intent(this, PassActivity::class.java)
            intent.putExtra("passlist", passesList as Serializable)
            startActivity(intent)
        }

        paymentProcessDialog?.setContentView(view)
        paymentProcessDialog?.setCancelable(false)
        paymentProcessDialog?.setCanceledOnTouchOutside(true)
        paymentProcessDialog?.show()

    }


}