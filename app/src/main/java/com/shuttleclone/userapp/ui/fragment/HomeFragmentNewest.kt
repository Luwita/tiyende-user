package com.shuttleclone.userapp.ui.fragment

import android.Manifest
import android.annotation.TargetApi
import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.content.Context.LOCATION_SERVICE
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.view.inputmethod.EditorInfo
import android.widget.*
import android.widget.TextView.OnEditorActionListener
import androidx.cardview.widget.CardView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shuttleclone.userapp.BaseActivity
import com.shuttleclone.userapp.AppDb.models.RecentSearchData
import com.shuttleclone.userapp.ViewModel.RecentSearchViewModel
import com.shuttleclone.userapp.R
import com.shuttleclone.userapp.ViewModel.HomeFragmentViewModel
import com.shuttleclone.userapp.model.*
import com.shuttleclone.userapp.ui.activity.BusListActivity
import com.shuttleclone.userapp.ui.activity.OffersActivity
import com.shuttleclone.userapp.ui.adapter.NewOfferAdapter
import com.shuttleclone.userapp.ui.adapter.RecentSearchAdapter
import com.shuttleclone.userapp.ui.events.SetLocationsEvent
import com.shuttleclone.userapp.utils.*
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.gson.Gson
import com.shuttleclone.userapp.ui.activity.ProfileSettingsActivity
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import java.util.*
import kotlin.collections.ArrayList

class HomeFragmentNewest : Fragment(), View.OnClickListener, RecentSearchAdapter.onClickListener,
    LocationListener {
    /*variable declaration*/
    private val TAG = "HomeFragmentNewest"
    private var mEdFromCity: TextView? = null
    private var mEdToCity: TextView? = null
    private var mValue = 0
    private var isSwap = false
    private var isCallingForSecondTime = false
    private var mView: View? = null
    private var pickUpData: SearchedDataItem? = null
    private var dropData: SearchedDataItem? = null
    private var temp: SearchedDataItem? = null
    private var mRadioGroupBookingType: RadioGroup? = null
    private var mNewOfferList: ArrayList<OffersData>? = null
    private var mRecentSearchList: ArrayList<RecentSearchData> ? = null
    private var officeRecord: UpdatedProfileData? = null
    private var mRvNewOffer: RecyclerView? = null
    private var mRvRecentSearch: RecyclerView? = null
    private var mLayRecentSearch: RelativeLayout? = null
    private var mTvClearSearchData: TextView? = null
    private var mIvSwap: ImageView? = null
    private var mIvDescrease: ImageView? = null
    private var mIvIncrease: ImageView? = null
    private var mSearch: ImageView? = null
    private var mTvAC: TextView? = null
    private var mTvNonAc: TextView? = null
    private var mTvSleeper: TextView? = null
    private var mTvSeater: TextView? = null
    private var mTvCount: TextView? = null
    private var mPbLoadData: ProgressBar? = null

    private var tvHomeAddress: TextView? = null
    private var tvOfficeAddress: TextView? = null
    private var tvLeavingHomeAt: TextView? = null
    private var ll_booking_date: TextView? = null
    private var tvBookingDate: TextView? = null
    private var tvLeavingOfficeAt: TextView? = null
    private var checkboxBookReturn: CheckBox? = null
    private var btnfindRoutes: Button? = null
    private var fabSwipeAddress: FloatingActionButton? = null

    private var mCvlayOfficeRide: CardView? = null
    private var mCvlayOneWayRide: CardView? = null
    private var mTvViewNewOffers: TextView? = null
    private var mLL_Offers: RelativeLayout? = null
    private var btnFindInstantRoute: Button? = null
    private var mDepartDateCalendar: Calendar? = null
    private var mRecentSearchAdapter: RecentSearchAdapter? = null
    private var mEdDepartDate: TextView? = null
    private var homeFragmentViewModel: HomeFragmentViewModel? = null
    private var recentSearchViewModel: RecentSearchViewModel ? = null
    private var searchFragment: SearchLocationDialogFragment? = null


    private var officeAddress = ""
    private var officeLat = ""
    private var officeLng = ""
    private var homeAddress = ""
    private var homeLat = ""
    private var homeLng = ""
    private var homeLeaveTime = ""
    private var officeLeaveTime = ""

    private lateinit var locationManager: LocationManager
    private val locationPermissionCode = 2


    private var currentDate = ""
    private var currentTime = ""
    private var endDate = ""
    private val date = OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
        mDepartDateCalendar!![Calendar.YEAR] = year
        mDepartDateCalendar!![Calendar.MONTH] = monthOfYear
        mDepartDateCalendar!![Calendar.DAY_OF_MONTH] = dayOfMonth
        updateLabel()
    }
    private var mIsAcSelected = false
    private var mIsNonAcSelected = false
    private var mIsSeaterSelected = false
    private var mIsSleeperSelected = false
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    private lateinit var busIntent: Intent
    var eventBus = EventBus.getDefault()

    /* create view */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, null)

        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this)
            eventBus = EventBus.getDefault()
        }
        homeFragmentViewModel = ViewModelProvider(this).get(HomeFragmentViewModel::class.java)
        recentSearchViewModel = ViewModelProvider(this).get(RecentSearchViewModel::class.java)


        initView(view)
        setListener()
        handelViewModel()
        viewModelOffersResponse()
        viewModelResponseGetProfileDetails()

        if (mDepartDateCalendar == null) {
            mDepartDateCalendar = Calendar.getInstance()
        }

        updateLabel()
        setUpFusedLocationClient()

//        getLocation()


        return view
    }

    private fun setUpFusedLocationClient() {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())
        if (ActivityCompat.checkSelfPermission(
                requireActivity(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                requireActivity(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
//            getLocation()
            fusedLocationClient.lastLocation
                .addOnSuccessListener { location: Location? ->
                    try {
                        if (location != null) {
                            savePreference(requireContext(),Constants.USER_LATITUDE,location.latitude.toString())
                            savePreference(requireContext(),Constants.USER_LONGITUDE,location.longitude.toString())
                            mylog(TAG, location.toString())
                            mEdFromCity!!.text =
                                getLocationName(
                                    location!!.latitude,
                                    location.longitude,
                                    requireActivity()
                                )
                        }
                    } catch (e: Exception) {
                        mylog(TAG, "onCreateView: Error=${e.localizedMessage}")
                    }
                }
        }

    }

    private fun fetchRecentSearchData() {
        recentSearchViewModel?.let { viewModel ->
            viewModel.getAllRecentSearchData()
                .observe(viewLifecycleOwner) { data ->
                    data?.let { list ->
                        if (list.isNotEmpty()) {
                            mRecentSearchList = data as ArrayList<RecentSearchData>
                            setRecentSearchRecord(mRecentSearchList!!)
                        }else {
                            if (null==mRecentSearchList)
                                mRecentSearchList = ArrayList()
                            else {
                                mRecentSearchList!!.clear()
                                setRecentSearchRecord(mRecentSearchList!!)
                            }
                        }
                    }

                }
        }
    }

    private fun viewModelOffersResponse() {

        if (isNetworkAvailable(requireContext())) {
            homeFragmentViewModel?.let {
                LoadingDialog.showLoadingDialog(
                    requireContext(),
                    getString(R.string.pls_wait_loading)
                )
                it.getOffers(
                    requireActivity(),
                    getPreference(activity, Constants.TOKEN)
                )
            }

        } else toast(requireContext(), getString(R.string.txt_Network))


        homeFragmentViewModel?.let {
            it.offers.observe(
                viewLifecycleOwner,
                androidx.lifecycle.Observer {
                    LoadingDialog.cancelLoading()
                    if (it == null)
                        return@Observer

                    if (it.status!!) {
                        if (it.data!!.size > 0) {
                            mLL_Offers!!.visibility = View.VISIBLE
                            mNewOfferList = it.data
                            setOfferAdapter()
                        }
                    } else alertDialog(requireActivity(), it.message.toString())
                })
        }

    }

    private fun getLocation() {
        locationManager = requireActivity().getSystemService(LOCATION_SERVICE) as LocationManager
        if ((ContextCompat.checkSelfPermission(
                requireActivity(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED)
        ) {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 5f, this)
        }

    }

    override fun onLocationChanged(location: Location) {
        if (location != null) {
            mylog(TAG, location.toString())
            mEdFromCity!!.text =
                getLocationName(location!!.latitude, location.longitude, requireActivity())
        }
    }

    private fun handelViewModel() {

        homeFragmentViewModel!!.searchedRoutes.observe(
            viewLifecycleOwner,
            androidx.lifecycle.Observer {
                LoadingDialog.cancelLoading()
                try {

                    if (it == null)
                        return@Observer

                    if (it!!.status!! && it.data != null) {
                        it.data.routes?.let { routes ->
                            if (routes.isNotEmpty()) {
                                val bundle = Bundle()
                                bundle.putSerializable("routes", it.data)
                                if (isCallingForSecondTime) {
                                    isCallingForSecondTime = false
                                    busIntent.putExtra("show_next_day",true)
                                }
                                busIntent.putExtras(bundle)
                                startActivity(busIntent)
                            } else {
                                if (!isCallingForSecondTime) {
                                    isCallingForSecondTime = true
                                    searchRouts()
                                } else {
                                    isCallingForSecondTime = false
                                    alertDialog(requireActivity(), getString(R.string.route_not_available))
                                }
                            }
                        } ?: {
                            if (!isCallingForSecondTime) {
                                isCallingForSecondTime = true
                                searchRouts()
                            } else {
                                isCallingForSecondTime = false
                                alertDialog(requireActivity(), getString(R.string.route_not_available))
                            }
                        }
                    } else {
                        if (!isCallingForSecondTime) {
                            isCallingForSecondTime = true
                            searchRouts()
                        } else {
                            isCallingForSecondTime = false
                            alertDialog(requireActivity(), it.message.toString())
                        }
                    }

                } catch (e: Exception) {
                    alertDialog(requireActivity(), e.localizedMessage)
                }

            })


    }

    private fun setRecentSearchRecord(recentSearchRecord: List<RecentSearchData>) {
        try {
            mylog(TAG, "setRecentSearchRecord: Called")
            mylog(TAG, "setRecentSearchRecord: DATA=${Gson().toJson(recentSearchRecord)}")
            if (recentSearchRecord.size != 0) {
                mLayRecentSearch!!.visibility = View.VISIBLE
                mRecentSearchAdapter =
                    RecentSearchAdapter(requireActivity(), recentSearchRecord)
                mRvRecentSearch!!.layoutManager =
                    LinearLayoutManager(activity, RecyclerView.HORIZONTAL, false)
                mRvRecentSearch!!.adapter = mRecentSearchAdapter
                mRecentSearchAdapter!!.setOnClickListener(this)
            } else {
                mLayRecentSearch!!.visibility = View.GONE
            }

        } catch (e: Exception) {
            mylog(TAG, "setRecentSearchRecord: Error=${e.localizedMessage}")
        }

    }


    /* update label */
    private fun updateLabel() {
        mEdDepartDate!!.text =
            Constants.DateFormat.DAY_MONTH_YEAR_FORMATTER.format(mDepartDateCalendar!!.time)
    }

    /* set adapter */
    private fun setOfferAdapter() {
        mRvNewOffer!!.layoutManager =
            LinearLayoutManager(activity, RecyclerView.HORIZONTAL, false)
        mRvNewOffer!!.adapter = NewOfferAdapter(requireActivity(), mNewOfferList!!)
    }

    /* set listener */
    private fun setListener() {
        mSearch!!.setOnClickListener(this)
        mTvViewNewOffers!!.setOnClickListener(this)
        btnFindInstantRoute!!.setOnClickListener(this)
        mTvAC!!.setOnClickListener(this)
        mTvNonAc!!.setOnClickListener(this)
        mTvSleeper!!.setOnClickListener(this)
        mTvSeater!!.setOnClickListener(this)
        mIvSwap!!.setOnClickListener(this)
        mIvDescrease!!.setOnClickListener(this)
        mIvIncrease!!.setOnClickListener(this)
        mEdDepartDate!!.setOnClickListener(this)
        mEdFromCity!!.setOnClickListener(this)
        mEdToCity!!.setOnClickListener(this)
        btnfindRoutes!!.setOnClickListener(this)
        mTvClearSearchData!!.setOnClickListener(this)
        mEdFromCity!!.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(
                s: CharSequence,
                start: Int,
                before: Int,
                count: Int
            ) {
                if (mEdFromCity!!.length() > 0) {
                    mView!!.setBackgroundColor(resources.getColor(R.color.colorPrimary))
                    mView!!.alpha = 0.2f
                    // homeFragmentViewModel.searchLocation(UtilMethods.getPreference(getContext(),Constants.TOKEN),mEdFromCity.getText().toString(),"10");
                } else {
                    mView!!.setBackgroundColor(resources.getColor(R.color.view_color))
                }
            }

            override fun beforeTextChanged(
                s: CharSequence,
                start: Int,
                count: Int,
                after: Int
            ) {
            }

            override fun afterTextChanged(s: Editable) {}
        })
        mEdToCity!!.setOnEditorActionListener(OnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                //  if (validate()) {
                mFrom = mEdFromCity!!.text.toString()
                mTo = mEdToCity!!.text.toString()
                val intent = Intent(activity, BusListActivity::class.java)
                intent.putExtra(
                    Constants.intentdata.TRIP_KEY,
                    mEdFromCity!!.text.toString() + " To " + mEdToCity!!.text.toString()
                )
                startActivity(intent)
                //  }
                return@OnEditorActionListener true
            }
            false
        })

        mRadioGroupBookingType!!.setOnCheckedChangeListener { radioGroup, isCheckedID ->
            when (isCheckedID) {
                R.id.radioBtnOneWay -> {
                    savePreference(context, Constants.IsCheckedOffice, false)
                    mCvlayOfficeRide!!.visibility = View.GONE
                    mCvlayOneWayRide!!.visibility = View.VISIBLE
                    mLayRecentSearch!!.visibility = View.VISIBLE
                    bookingType = "oneway"
                    has_return = "1"
                    isSwap = false
                    fetchRecentSearchData()
                }

                R.id.radioBtnOfficeRide -> {
                    savePreference(context, Constants.IsCheckedOffice, true)
                    mCvlayOfficeRide!!.visibility = View.VISIBLE
                    mCvlayOneWayRide!!.visibility = View.GONE
                    mLayRecentSearch!!.visibility = View.GONE
                    bookingType = "office"
                    has_return = "1"
                    checkboxBookReturn!!.isChecked = false
                    isSwap = false
                    getProfileDetails()
                }
            }
        }

        checkboxBookReturn!!.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) has_return = "2"
            else has_return = "1"
        }
    }

    /* init view */
    private fun initView(view: View) {
        mRvNewOffer = view.findViewById(R.id.rvNewOffers)
        mTvViewNewOffers = view.findViewById(R.id.tvViewallNewOffer)
        mLL_Offers = view.findViewById(R.id.ll_offers)
        mIvSwap = view.findViewById(R.id.ivSwap)
        mSearch = view.findViewById(R.id.btnSearch)
        mTvAC = view.findViewById(R.id.tvAc)
        mTvNonAc = view.findViewById(R.id.tvNonAc)
        mTvSleeper = view.findViewById(R.id.tvSleeper)
        mTvSeater = view.findViewById(R.id.tvSeater)
        mEdDepartDate = view.findViewById(R.id.edOneWay)
        mEdFromCity = view.findViewById(R.id.edFromCity)
        mEdToCity = view.findViewById(R.id.edToCity)
        mIvDescrease = view.findViewById(R.id.ivDescrease)
        mIvIncrease = view.findViewById(R.id.ivIncrease)
        mTvCount = view.findViewById(R.id.tvCount)
        mView = view.findViewById(R.id.view2)
        btnFindInstantRoute = view.findViewById(R.id.btnFindInstantRoute)
        mRvRecentSearch = view.findViewById(R.id.rvRecentSearch)
        mLayRecentSearch = view.findViewById(R.id.layRecentSearch)
        mTvClearSearchData = view.findViewById(R.id.tvClearSearchData)
        mCvlayOfficeRide = view.findViewById(R.id.cvlayOfficeRide)
        mCvlayOneWayRide = view.findViewById(R.id.cvlayOneWayRide)
        mRadioGroupBookingType = view.findViewById(R.id.radioGroupBookingType)

        tvHomeAddress = view.findViewById(R.id.tvHomeAddress)
        tvOfficeAddress = view.findViewById(R.id.tvOfficeAddress)
        tvLeavingHomeAt = view.findViewById(R.id.tvLeavingHomeAt)
        tvLeavingOfficeAt = view.findViewById(R.id.tvLeavingOfficeAt)
        checkboxBookReturn = view.findViewById(R.id.checkboxBookReturn)
        btnfindRoutes = view.findViewById(R.id.btnfindRoutes)
        fabSwipeAddress = view.findViewById(R.id.fabSwipeAddress)
        mPbLoadData = view.findViewById(R.id.pbLoadData)

        fabSwipeAddress!!.clickWithThrottle { swipeAddress() }

        mIvDescrease!!.setVisibility(View.INVISIBLE)

        if (isPreference(context, Constants.IsCheckedOffice)) {
            mRadioGroupBookingType!!.check(R.id.radioBtnOfficeRide)
            mCvlayOfficeRide!!.visibility = View.VISIBLE
            mCvlayOneWayRide!!.visibility = View.GONE
            mLayRecentSearch!!.visibility = View.GONE
            bookingType = "office"
            has_return = "1"
            checkboxBookReturn!!.isChecked = false
            getProfileDetails()
        } else {
            mRadioGroupBookingType!!.check(R.id.radioBtnOneWay)
            mCvlayOfficeRide!!.visibility = View.GONE
            mCvlayOneWayRide!!.visibility = View.VISIBLE
            mLayRecentSearch!!.visibility = View.VISIBLE
            bookingType = "oneway"
            has_return = "1"
            fetchRecentSearchData()
        }
    }

    private fun swipeAddress() {
        try {
            if (!isSwap && officeRecord != null) {
                vibratePhone(requireActivity())
                isSwap = true
                tvOfficeAddress!!.setText(officeRecord!!.homeAddress)
                tvHomeAddress!!.setText(officeRecord!!.officeAddress)
                tvLeavingHomeAt!!.setText(officeRecord!!.officeTiming)
                tvLeavingOfficeAt!!.setText(officeRecord!!.homeTiming)

                homeAddress = officeRecord!!.officeAddress.toString()
                officeAddress = officeRecord!!.homeAddress.toString()
                homeLat = officeRecord!!.officeLat.toString()
                homeLng = officeRecord!!.officeLng.toString()
                officeLat = officeRecord!!.homeLat.toString()
                officeLng = officeRecord!!.homeLng.toString()
                officeLeaveTime = officeRecord!!.homeTiming.toString()
                homeLeaveTime = officeRecord!!.officeTiming.toString()
            } else {
                vibratePhone(requireActivity())
                isSwap = false
                tvOfficeAddress!!.setText(officeRecord!!.officeAddress)
                tvHomeAddress!!.setText(officeRecord!!.homeAddress)
                tvLeavingHomeAt!!.setText(officeRecord!!.homeTiming)
                tvLeavingOfficeAt!!.setText(officeRecord!!.officeTiming)

                officeAddress = officeRecord!!.officeAddress.toString()
                homeAddress = officeRecord!!.homeAddress.toString()
                officeLat = officeRecord!!.officeLat.toString()
                officeLng = officeRecord!!.officeLng.toString()
                homeLat = officeRecord!!.homeLat.toString()
                homeLng = officeRecord!!.homeLng.toString()
                homeLeaveTime = officeRecord!!.homeTiming.toString()
                officeLeaveTime = officeRecord!!.officeTiming.toString()
            }
        } catch (e: Exception) {
            mylog(TAG, "swipeAddress: Error=${e.localizedMessage}")
        }
    }


    /* onClick listener */
    override fun onClick(v: View) {
        when (v.id) {
            R.id.edOneWay -> {
                val datePickerDialogs = DatePickerDialog(
                    requireActivity(),
                    date,
                    mDepartDateCalendar!!.get(Calendar.YEAR),
                    mDepartDateCalendar!![Calendar.MONTH],
                    mDepartDateCalendar!![Calendar.DAY_OF_MONTH]
                )
                datePickerDialogs.datePicker.minDate = Date().time
                datePickerDialogs.show()
            }

            R.id.ivSwap -> {
                mylog(TAG, "onClick: ivSwap Called isSwap=$isSwap")
                mylog(TAG, "onClick: ivSwap Called pickUpData=${pickUpData?.title}")
                mylog(TAG, "onClick: ivSwap Called dropData=${dropData?.title}")
                if (pickUpData != null && dropData != null) {
                    vibratePhone(requireContext())

                    var temp = pickUpData
                    pickUpData = dropData
                    dropData = temp
                    val startRotateAnimation =
                        AnimationUtils.loadAnimation(context, R.anim.anim_rotate)
                    mIvSwap!!.startAnimation(startRotateAnimation)
                    if (isSwap) {
                        setPickUpDropData(pickUpData!!, dropData!!)
                        isSwap = false
                    } else {
                        setPickUpDropData(pickUpData!!, dropData!!)
                        isSwap = true
                    }

                }
            }

            R.id.ivDescrease -> {
                mValue = mValue - 1
                mTvCount!!.text = mValue.toString()
                if (mValue <= 1) {
                    (Objects.requireNonNull(requireActivity()) as BaseActivity).invisibleView(
                        mIvDescrease
                    )
                } else {
                    (activity as BaseActivity?)!!.showView(mIvDescrease)
                    mTvCount!!.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0)
                    mTvCount!!.setTextColor(resources.getColor(R.color.colorPrimary))
                }
            }

            R.id.ivIncrease -> {
                mValue = mValue + 1
                if (mValue < 1) {
                    mValue = 1
                } else {
                    if (mValue == 1) (activity as BaseActivity?)!!.invisibleView(
                        mIvDescrease
                    ) else (Objects.requireNonNull(
                        requireActivity()
                    ) as BaseActivity).showView(mIvDescrease)
                    mTvCount!!.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0)
                    mTvCount!!.text = mValue.toString()
                    mTvCount!!.setTextColor(resources.getColor(R.color.colorPrimary))
                }
            }

            R.id.tvAc -> mIsAcSelected = if (!mIsAcSelected) {
                enable(v as TextView)
                true
            } else {
                disable(v as TextView)
                false
            }

            R.id.tvNonAc -> mIsNonAcSelected = if (!mIsNonAcSelected) {
                enable(v as TextView)
                true
            } else {
                disable(v as TextView)
                false
            }

            R.id.tvSleeper -> mIsSleeperSelected = if (!mIsSleeperSelected) {
                enable(v as TextView)
                true
            } else {
                disable(v as TextView)
                false
            }

            R.id.tvSeater -> mIsSeaterSelected = if (!mIsSeaterSelected) {
                enable(v as TextView)
                true
            } else {
                disable(v as TextView)
                false
            }

            R.id.btnSearch -> {
                //    if (validate()) {
                mFrom = mEdFromCity!!.text.toString()
                mTo = mEdToCity!!.text.toString()
                val intent = Intent(activity, BusListActivity::class.java)
                intent.putExtra(Constants.intentdata.TRIP_KEY, mFrom + " To " + mTo)
                startActivity(intent)
            }

            R.id.btnfindRoutes -> {
                vibratePhone(requireContext())
                if (validateOffice())
                    searchRouts()
                else {
                    val intent = Intent(requireActivity(), ProfileSettingsActivity::class.java)
                    startActivity(intent)
                }

            }

            R.id.btnFindInstantRoute -> {

                vibratePhone(requireContext())
                if (validate())
                    searchRouts()

            }

            R.id.tvViewallNewOffer -> {
                vibratePhone(requireContext())
                if (mNewOfferList != null) {
                    val i = Intent(activity, OffersActivity::class.java)
                    i.putExtra(Constants.intentdata.OFFER, mNewOfferList)
                    startActivity(i)
                }
            }

            R.id.edFromCity -> {
                searchFragment =
                    SearchLocationDialogFragment(
                        requireContext(),
                        Constants.SEARCH_FOR_ROUTS
                    )
                if (!searchFragment!!.isVisible) {
                    searchFragment!!.show(requireFragmentManager(), "SearchFragment")
                }
            }

            R.id.edToCity -> {
                searchFragment =
                    SearchLocationDialogFragment(
                        requireContext(),
                        Constants.SEARCH_FOR_ROUTS
                    )
                if (!searchFragment!!.isVisible) {
                    searchFragment!!.show(requireFragmentManager(), "SearchFragment")
                }
            }

            R.id.tvClearSearchData -> {
                recentSearchViewModel!!.deleteAllRecentSearchData()
                fetchRecentSearchData()
            }
        }
    }

    private fun viewModelResponseGetProfileDetails() {
        try {
            homeFragmentViewModel?.let {
                it.getUserProfileDetails.observe(viewLifecycleOwner,
                    androidx.lifecycle.Observer { userData ->
                        LoadingDialog.cancelLoading()
                        try {
                            mylog(
                                TAG,
                                "viewModelResponseProfileDetails: Response=${Gson().toJson(userData)}"
                            )

                            if (userData == null)
                                return@Observer

                            if (userData.isStatus) {
                                try {

                                    userData.data?.let { data ->
                                        officeRecord = data

                                        tvOfficeAddress!!.text = data.officeAddress
                                        tvHomeAddress!!.text = data.homeAddress
                                        tvLeavingHomeAt!!.text = data.homeTiming
                                        tvLeavingOfficeAt!!.text = data.officeTiming

                                        officeAddress = data.officeAddress.toString()
                                        homeAddress = data.homeAddress.toString()
                                        officeLat = data.officeLat.toString()
                                        officeLng = data.officeLng.toString()
                                        homeLat = data.homeLat.toString()
                                        homeLng = data.homeLng.toString()
                                        homeLeaveTime = data.homeTiming.toString()
                                        officeLeaveTime = data.officeTiming.toString()
                                    }
                                } catch (e: Exception) {
                                    mylog(
                                        TAG,
                                        "onSuccess:getProfileDetails Error=" + e.localizedMessage
                                    )
                                }
                            } else alertDialog(requireActivity(), userData.message.toString())

                        } catch (e: Exception) {
                            alertDialog(requireActivity(), e.localizedMessage)
                        }

                    })
            }
        } catch (e: java.lang.Exception) {
            mylog(TAG, "viewModelResponseProfileDetails: Error=" + e.localizedMessage)
        }

    }

    private fun getProfileDetails() {
        try {
            if (isNetworkAvailable(requireContext())) {
                homeFragmentViewModel?.let {
                    LoadingDialog.showLoadingDialog(
                        requireContext(),
                        getString(R.string.pls_wait_loading)
                    )
                    it.getProfileDetails(
                        requireContext(),
                        getPreference(requireContext(), Constants.TOKEN)
                    )
                }
            } else toast(requireContext(), getString(R.string.txt_Network))
        } catch (e: Exception) {
            mylog(TAG, "getProfileDetails: Error=" + e.localizedMessage)
            LoadingDialog.cancelLoading()
        }
    }

    /* validation */
    private fun validate(): Boolean {
        var flag = true
        if (TextUtils.isEmpty(mEdFromCity!!.text)) {
            flag = false
            toast(
                requireActivity(),
                getString(
                    R.string.msg_from
                )
            )
        } else if (TextUtils.isEmpty(mEdToCity!!.text)) {
            flag = false
            toast(
                requireActivity(),
                getString(
                    R.string.msg_to
                )
            )
        } else if (dropData == null) {
            flag = false
            mEdToCity!!.text = ""
            toast(
                requireActivity(),
                getString(
                    R.string.msg_to
                )
            )
        }
        return flag
    }

    private fun validateOffice(): Boolean {
        var flag = true
        if (homeAddress.equals("") && officeAddress.equals("")) {
            flag = false
            toast(requireContext(), getString(R.string.msg_office_ride_address_required))
        } else if (homeLeaveTime.equals("") && officeLeaveTime.equals("")) {
            flag = false
            toast(requireContext(), getString(R.string.msg_office_ride_address_required))
        }
        return flag
    }

    /* disable textview */
    private fun disable(textView: TextView) {
        textView.setTextColor(resources.getColor(R.color.textchild))
        for (drawable in textView.compoundDrawables) {
            if (drawable != null) {
                drawable.colorFilter = PorterDuffColorFilter(
                    ContextCompat.getColor(
                        textView.context,
                        R.color.textchild
                    ), PorterDuff.Mode.SRC_IN
                )
            }
        }
    }

    /* enable textview */
    private fun enable(textView: TextView) {
        textView.setTextColor(resources.getColor(R.color.startcolor))
        for (drawable in textView.compoundDrawables) {
            if (drawable != null) {
                drawable.colorFilter = PorterDuffColorFilter(
                    ContextCompat.getColor(
                        textView.context,
                        R.color.startcolor
                    ), PorterDuff.Mode.SRC_IN
                )
            }
        }
    }

    /* onClick listener */
    override fun onClick(recentSearchData: RecentSearchData) {

        pickUpLat = recentSearchData.pickUpLat
        pickUpLng = recentSearchData.pickUpLng
        dropLat = recentSearchData.dropLat
        dropLng = recentSearchData.dropLng
        pickUpAddress = recentSearchData.pickUpAddress
        dropAddress = recentSearchData.dropAddress
        pickStopUpId = recentSearchData.pickUpId
        dropStopId = recentSearchData.dropId
        bookingType = "oneway"
        has_return = "1"

        searchRouts()
    }

    /* change destination */
    fun ChangeDestination(result: String?) {
        if (mEdFromCity!!.length() == 0) {
            mEdFromCity!!.text = result
        } else {
            mEdToCity!!.text = result
        }
    }

    fun setLocationData(pickUpData: SearchedDataItem, dropData: SearchedDataItem) {
        try {
            mylog(TAG, "setLocationData: " + pickUpData.title)
            mylog(TAG, "setLocationData: " + dropData.title)
            mEdFromCity!!.text = pickUpData.title
            mEdToCity!!.text = dropData.title
            val intent = Intent(activity, BusListActivity::class.java)
            intent.putExtra(
                Constants.intentdata.TRIP_KEY,
                mEdFromCity!!.text.toString() + " To " + mEdToCity!!.text.toString()
            )
            startActivity(intent)
        } catch (e: Exception) {
            mylog(TAG, "setLocationData: ")
        }
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onEventMainThread(pusher: SetLocationsEvent) {
        try {

            mylog(TAG, "setLocationData: pickUpData=${Gson().toJson(pusher.pickUpData)}")
            mylog(TAG, "setLocationData: dropData=${Gson().toJson(pusher.dropData)}")

            setPickUpDropData(pusher.pickUpData, pusher.dropData)

            insertRecentSearchRecord()

        } catch (e: Exception) {
            mylog(TAG, "setLocationData: error=${e.localizedMessage}")
        }
    }

    private fun setPickUpDropData(
        pickUpData: SearchedDataItem,
        dropData: SearchedDataItem
    ) {
        try {
            mEdFromCity!!.text = pickUpData.title
            mEdToCity!!.text = dropData.title
            pickUpAddress = pickUpData.title
            dropAddress = dropData.title
            pickUpLat = pickUpData!!.locationLatitude.toString()
            pickUpLng = pickUpData!!.locationLongitude.toString()
            dropLat = dropData!!.locationLatitude.toString()
            dropLng = dropData!!.locationLongitude.toString()
            pickStopUpId = pickUpData!!.id.toString()
            dropStopId = dropData!!.id.toString()

            this.pickUpData = pickUpData
            this.dropData = dropData

            vibratePhone(requireContext())
            if (validate())
                searchRouts()

        } catch (e: Exception) {
            mylog(TAG, "setPickUpDropData: Error=${e.localizedMessage}")
        }
    }

    private fun insertRecentSearchRecord() {
        try {
            if (null!=pickUpData && null!=dropData) {
                currentDate = convertDateToBeautify(Calendar.getInstance().time)
                var isDataAvailable = false

                if (mRecentSearchList!!.size != 0)
                    for (data in mRecentSearchList!!) {
                        if (pickUpAddress == data.pickUpAddress && dropAddress == data.dropAddress) {
                            isDataAvailable = true
                            break
                        }
                    }

                if (!isDataAvailable) {
                    recentSearchViewModel!!.insert(
                        RecentSearchData(
                            pickUpLat,
                            pickUpLng,
                            dropLat,
                            dropLng,
                            pickUpAddress,
                            dropAddress,
                            currentDate,
                            dropData!!.id,
                            pickUpData!!.id
                        )
                    )
                }


            }
        } catch (e: Exception) {
            mylog(TAG, "insertRecentSearchRecord: Error=${e.localizedMessage}")
        }
    }

    private fun searchRouts() {

        try {

            busIntent = Intent(activity, BusListActivity::class.java)


            if (bookingType.equals("oneway")) {

                busIntent.putExtra(Constants.intentdata.FROM, pickUpAddress)
                busIntent.putExtra(Constants.intentdata.TO, dropAddress)
                currentTime = convertTimeToBeautify(Calendar.getInstance().time)

            } else {
                busIntent.putExtra(Constants.intentdata.FROM, homeAddress)
                busIntent.putExtra(Constants.intentdata.TO, officeAddress)

                pickUpLat = homeLat
                pickUpLng = homeLng
                dropLat = officeLat
                dropLng = officeLng

                try {
                    if (!isCallingForSecondTime) {
                        currentTime = if (!convertTo24HourFormat(homeLeaveTime).equals(""))
                            convertTo24HourFormat(homeLeaveTime)
                        else convertTimeToBeautify(Calendar.getInstance().time)
                    }else currentTime="00:00"
                }catch (e:Exception){
                    currentTime = convertTimeToBeautify(Calendar.getInstance().time)
                    Log.i(TAG, "searchRouts: time conversion error")
                }
            }


            currentDate = if (isCallingForSecondTime) convertDateToBeautify(increaseDaysInCalendar(1).time)
            else convertDateToBeautify(Calendar.getInstance().time)

            savePreference(activity, Constants.BOOKING_DATE, currentDate)
            savePreference(activity, Constants.BOOKING_TIME, currentTime)
            endDate = calculateNext3rdDate(Calendar.getInstance())
            savePreference(activity, Constants.BOOKING_END_DATE, endDate)

            mylog(TAG, currentDate+currentTime + bookingType)

            LoadingDialog.showLoadingDialog(activity, getString(R.string.pls_wait_loading))


          /*  // Show date Picker
            val currentDatePicker = Calendar.getInstance()
            val datePickerDialog = DatePickerDialog(
                requireActivity(),
                { _, year, monthOfYear, dayOfMonth ->
                    // Do something with the selected date
                    Log.i(TAG, "searchRouts: $year-$monthOfYear-$dayOfMonth")
                    
                },
                currentDatePicker.get(Calendar.YEAR),
                currentDatePicker.get(Calendar.MONTH),
                currentDatePicker.get(Calendar.DAY_OF_MONTH)
            )

            // Disable all previous dates
            datePickerDialog.datePicker.minDate = currentDatePicker.timeInMillis
            // Show the date picker dialog
            datePickerDialog.show()*/

            homeFragmentViewModel!!.searchRoutes(
                requireActivity(),
                getPreference(activity, Constants.TOKEN),
                pickUpLat,
                pickUpLng,
                dropLat,
                dropLng,
                currentDate,
                currentTime,
                endDate,
                bookingType,
                pickStopUpId,
                dropStopId,
                has_return
            )

        } catch (e: Exception) {
            mylog(TAG, "searchRouts: error=${e.localizedMessage}")
        }
    }


    override fun onResume() {
        super.onResume()
//        mEdToCity!!.text = getString(R.string.hint_drop_location)
    }


    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }

    companion object {

        @JvmField
        var mFrom: String? = null

        @JvmField
        var mTo: String? = null

        @JvmField
        var pickUpAddress = ""

        @JvmField
        var pickUpLat = ""

        @JvmField
        var pickUpLng = ""

        @JvmField
        var pickStopUpId = ""

        @JvmField
        var dropAddress = ""

        @JvmField
        var dropLat = ""

        @JvmField
        var dropLng = ""

        @JvmField
        var dropStopId = ""

        @JvmField
        var bookingType = ""

        @JvmField
        var has_return = "1"
    }

}