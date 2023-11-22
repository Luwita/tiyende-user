package com.shuttleclone.userapp.ui.activity

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.*
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.shuttleclone.userapp.BaseActivity
import com.shuttleclone.userapp.R
import com.shuttleclone.userapp.ViewModel.HomeFragmentViewModel
import com.shuttleclone.userapp.model.BookingRequestData
import com.shuttleclone.userapp.model.FareData
import com.shuttleclone.userapp.model.PassengerDetailsItem
import com.shuttleclone.userapp.utils.*
import com.razorpay.Checkout


class PassengerDetailActivity : BaseActivity(), View.OnClickListener {
    /*variable declaration*/
    private var mBtnBook: Button? = null
    private var mLlDynamicContent: LinearLayout? = null
    private var mCount = 0
    private var mSplited: ArrayList<String>? = null
    private var mEdEmail: EditText? = null
    private var mEdMobileNumber: EditText? = null
    private var mEdFirstName: EditText? = null
    private var ganderSpinner: Spinner? = null
    private var mEdAge: EditText? = null
    private var mIVBack: ImageView? = null
    private var mIvHome: ImageView? = null
    private var fareData: FareData? = null
    private val homeFragmentViewModel: HomeFragmentViewModel by viewModels()
    var allEdNames: ArrayList<EditText> = ArrayList()
    var allEdAges: ArrayList<EditText> = ArrayList()
    var allGander: ArrayList<Spinner> = ArrayList()
    var passengerDetailsItem: ArrayList<PassengerDetailsItem> = ArrayList()

    override fun onResume() {
        super.onResume()
        LocaleManager().setLocale(this) //This method update the language when the activity orientation changes
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LocaleManager().setLocale(this) //This method update the language when the activity orientation changes
        setContentView(R.layout.activity_passenger_detail)
        initLayouts()
        initializeListeners()
        handelViewModel()
        Checkout.preload(applicationContext)
    }


    /* init layout */
    private fun initLayouts() {
        mBtnBook = findViewById(R.id.btnBook)
        mLlDynamicContent = findViewById(R.id.llDynamicContent)
        mEdEmail = findViewById(R.id.edEmail)
        mEdMobileNumber = findViewById(R.id.edMobileNumber)
        mIVBack = findViewById(R.id.ivBack)
        mIvHome = findViewById(R.id.ivHome)


    }

    /* initialize listener */
    private fun initializeListeners() {
        mBtnBook!!.setOnClickListener(this)
        mIVBack!!.setOnClickListener(this)
        mIvHome!!.setOnClickListener(this)
        mBtnBook!!.stateListAnimator = null
        mCount = BookingActivity.mCountSeat
        setTypeFace(mEdMobileNumber)

        try {

            mEdEmail!!.setText(getPreference(this, Constants.EMAIL))
            mEdMobileNumber!!.setText(getPreference(this, Constants.PHONE_NO))


            if (intent != null) {
                fareData = intent.getSerializableExtra("bookingFare") as FareData
                mSplited = intent.getSerializableExtra("seatNo") as ArrayList<String>?

            }

            // mSplited = BookingActivity.mSb.toString().split(" ".toRegex()).toTypedArray()
            var i = 0
            while (i < mCount) {
                i++
                val view1 = layoutInflater.inflate(R.layout.item_passenger1, mLlDynamicContent, false)
                val mTvSeatNo = view1.findViewById<TextView>(R.id.tvSeatNo)
                val mRlHeading = view1.findViewById<RelativeLayout>(R.id.rlHeading)
                val mRlSubHeading = view1.findViewById<RelativeLayout>(R.id.rlSubHeading)
                val mIvIcon = view1.findViewById<ImageView>(R.id.ivIcon)
                mEdFirstName = view1.findViewById(R.id.edFirstName)
                mEdAge = view1.findViewById(R.id.edAge)
                ganderSpinner = view1.findViewById(R.id.ganderSpinner)
                setTypeFace(mEdAge)
                mRlHeading.setOnClickListener {
                    if (mRlSubHeading.visibility == View.VISIBLE) {
                        mIvIcon.setImageDrawable(resources.getDrawable(R.drawable.ic_keyboard_arrow_down_black))
                        hideView(mRlSubHeading)
                    } else {
                        mIvIcon.setImageDrawable(resources.getDrawable(R.drawable.ic_keyboard_arrow_up_black))
                        showView(mRlSubHeading)
                    }
                }
                allEdNames.add(mEdFirstName!!)
                allEdAges.add(mEdAge!!)
                allGander.add(ganderSpinner!!)

                mTvSeatNo.text = mSplited!![i - 1]
                mLlDynamicContent!!.addView(view1)

            }
        } catch (e: Exception) {
            mylog(TAG, "initializeListeners: Error=${e.localizedMessage} ")
        }
    }

    /* onClick listener */
    override fun onClick(v: View) {
        if (v === mBtnBook) {
            // if (validate()) {
//                 startActivity(PaymentActivity.class);

            try {
                passengerDetailsItem.clear()
                for (i in 0..allEdNames.size - 1) {
                    val name = allEdNames.get(i).text.toString()
                    val age = allEdAges.get(i).text.toString()
                    val gander = allGander.get(i).selectedItem.toString()
                    val seat = mSplited!![i]

                    mylog(TAG, "Name=" + allEdNames.get(i).text)
                    mylog(TAG, "age=" + allEdAges.get(i).text)
                    mylog(TAG, "gander=" + allGander.get(i).selectedItem)
                    mylog(TAG, "seat=" + mSplited!![i])
                    if (!name.equals("") && !age.equals("") && !gander.equals("") && !seat.equals("")) {
                        passengerDetailsItem.add(PassengerDetailsItem(seat, gander, name, age))
                    } else {
                        passengerDetailsItem.clear()
                        toast(this, getString(R.string.pls_fill_dtls))
                        break
                    }
                }

                if (passengerDetailsItem.size != 0)
                    createBookingRequest(passengerDetailsItem, fareData)
            } catch (e: java.lang.Exception) {
                mylog(TAG, "Error=${e.localizedMessage}")
            }


//            val i = Intent(this, ConfirmPaymentActivity::class.java)
//            startActivity(i)
            //  }
        } else if (v === mIVBack) {
            onBackPressedDispatcher.onBackPressed()
        } else if (v === mIvHome) {
            goHome(applicationContext)
        }
    }

    private fun createBookingRequest(passengerDetailsItem: ArrayList<PassengerDetailsItem>, fareData: FareData?) {
        try {
            LoadingDialog.showLoadingDialog(this, getString(R.string.pls_wait_loading))
            homeFragmentViewModel!!.createBooking(this, getPreference(this, Constants.TOKEN), BookingRequestData(passengerDetailsItem, "", fareData!!))
        } catch (e: java.lang.Exception) {
            mylog(TAG, "createBookingRequest: Error=${e.localizedMessage}")
        }
    }

    private fun handelViewModel() {
        homeFragmentViewModel!!.bookingResponse.observe(this, Observer {

            LoadingDialog.cancelLoading()
            try {
                if (it?.status!!) {
                    val intent = Intent(this, ConfirmPaymentActivity::class.java)

                    val bundle = Bundle()
                    bundle.putSerializable("bookingData", it.data!!)
                    bundle.putSerializable("bookingFare", fareData)
                    intent.putExtra("comingFrom", "Passenger")
                    intent.putExtras(bundle)
                    startActivity(intent)
                }
            } catch (e: Exception) {
                mylog(TAG, "handelViewModel: Error=${e.localizedMessage}")
            }

        })

    }

    /* validations */
    private fun validate(): Boolean {
        var flag = true
        if (TextUtils.isEmpty(mEdEmail!!.text)) {
            flag = false
            toast(this,getString(R.string.msg_email_id))
        } else if (TextUtils.isEmpty(mEdMobileNumber!!.text)) {
            flag = false
            toast(this,getString(R.string.msg_mobile_number))
        } else if (TextUtils.isEmpty(mEdFirstName!!.text)) {
            flag = false
            toast(this,getString(R.string.msg_name))
        } else if (TextUtils.isEmpty(mEdAge!!.text)) {
            flag = false
            toast(this,getString(R.string.ms_age))
        }
        return flag
    }

    companion object {
        private const val TAG = "PassengerDetailActivity"
    }
}