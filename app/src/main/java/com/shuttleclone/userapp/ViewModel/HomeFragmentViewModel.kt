package com.shuttleclone.userapp.ViewModel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shuttleclone.userapp.R
import com.shuttleclone.userapp.retrofitRepository.ApiCallBack
import com.shuttleclone.userapp.retrofitRepository.RetrofitClient
import com.shuttleclone.userapp.model.*
import com.shuttleclone.userapp.utils.*
import com.google.gson.Gson
import com.shuttleclone.userapp.retrofitRepository.ApiCalls
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import okhttp3.MultipartBody
import okhttp3.RequestBody

class HomeFragmentViewModel : ViewModel() {
    private val TAG = "HomeFragmentViewModel"

    val searchLocationList: MutableLiveData<MutableList<SearchedDataItem>?> by lazy { MutableLiveData() }
    val savedSearchLocationList: MutableLiveData<MutableList<SearchedDataItem>?> by lazy { MutableLiveData() }
    val searchedRoutes: MutableLiveData<SearchRoutesResponseModel?> by lazy { MutableLiveData() }
    val routeStops: MutableLiveData<RouteStopsResponseModel?> by lazy { MutableLiveData() }
    val trackRoutes: MutableLiveData<TripTrackingStatusResponse?> by lazy { MutableLiveData() }
    val busSeatsResponse: MutableLiveData<BusSeatsResponseModel?> by lazy { MutableLiveData() }
    val exploreRoutes: MutableLiveData<ExploreRoutesResponseModel?> by lazy { MutableLiveData() }
    val getRouteFare: MutableLiveData<GeneratedFareResponseModel?> by lazy { MutableLiveData() }
    val bookingResponse: MutableLiveData<BookingResponseModel?> by lazy { MutableLiveData() }
    val paymentResponse: MutableLiveData<PaymentInitiateDataResponse?> by lazy { MutableLiveData() }
    val initiatePayment: MutableLiveData<InitiatePaymentResponseModel?> by lazy { MutableLiveData() }
    val initiateTripPayment: MutableLiveData<InitiatePaymentResponseModel?> by lazy { MutableLiveData() }
    val initiatePassPayment: MutableLiveData<InitiatePaymentResponseModel?> by lazy { MutableLiveData() }
    val bookingList: MutableLiveData<BookingListResponseModel?> by lazy { MutableLiveData() }
    val addOfficeRideAddress: MutableLiveData<UserProfileUpdateResponse?> by lazy { MutableLiveData() }
    val updateUserProfile: MutableLiveData<UserProfileUpdateResponse?> by lazy { MutableLiveData() }
    val getUserProfileDetails: MutableLiveData<UserProfileUpdateResponse?> by lazy { MutableLiveData() }
    val setReminder: MutableLiveData<DefaultResponse?> by lazy { MutableLiveData() }
    val cancelBooking: MutableLiveData<DefaultResponse?> by lazy { MutableLiveData() }
    val isDataSaved: MutableLiveData<Boolean> by lazy { MutableLiveData() }
    val offers: MutableLiveData<OffersResponseModel?> by lazy { MutableLiveData() }
    val clearNotification: MutableLiveData<DefaultResponse?> by lazy { MutableLiveData() }
    val appNotifications: MutableLiveData<NotificationDataResponse?> by lazy { MutableLiveData() }
    val checkWalletBalance: MutableLiveData<WalletBalanceResponseModel?> by lazy { MutableLiveData() }
    val initiateWalletRecharge: MutableLiveData<InitiatePaymentResponseModel?> by lazy { MutableLiveData() }
    val getBookingHistory: MutableLiveData<BookingTransactionHistoryResponse?> by lazy { MutableLiveData() }
    val logOutUser: MutableLiveData<DefaultResponse?> by lazy { MutableLiveData() }
    val getConfigSettings: MutableLiveData<CommonDataResponse?> by lazy { MutableLiveData() }
    val helpAndSupport: MutableLiveData<DefaultResponse?> by lazy { MutableLiveData() }
    val getReferDetails: MutableLiveData<ReferCodeModel?> by lazy { MutableLiveData() }
    val suggestRoutes: MutableLiveData<DefaultResponse?> by lazy { MutableLiveData() }
    val getWalletHistory: MutableLiveData<WalletHistoryResponseModel?> by lazy { MutableLiveData() }
    val registerUser: MutableLiveData<UserRegisterResponseModel?> by lazy { MutableLiveData() }
    val verifyUser: MutableLiveData<UserVerificationResponseModel?> by lazy { MutableLiveData() }
    val validatePhone: MutableLiveData<DefaultResponse?> by lazy { MutableLiveData() }
    val updateLanguage: MutableLiveData<DefaultResponse?> by lazy { MutableLiveData() }
    val fetchNearestStops: MutableLiveData<MutableList<SearchedDataItem>?> by lazy { MutableLiveData() }

    private val apiClient : ApiCalls by lazy { RetrofitClient.getClient() }

    fun searchLocation(mContext: Context?, token: String?, location: String?, limit: String?) {
        if (isNetworkAvailable(mContext!!)) {
            apiClient.searchLocation(token, location, limit)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<SearchLocationResponseModel?>() {
                    override fun onSuccess(response: SearchLocationResponseModel) {
                        try {
                            mylog(TAG, "onResponse: 1" + Gson().toJson(response))
                            if (response.isStatus) searchLocationList.postValue(response.data)
                            else searchLocationList.postValue(null)
                        } catch (e: Exception) {
                            mylog(TAG, "onResponse:searchLocation Error=" + e.localizedMessage)
                            searchLocationList.postValue(null)
                        }
                    }

                    override fun onError(e: Throwable) {
                        searchLocationList.postValue(null)
                        if (e.localizedMessage.equals(Constants.Unauthorized) || e.localizedMessage.equals(
                                Constants.Forbidden
                            )
                        )
                            checkToken(
                                mContext,
                                ApiCallBack { success ->
                                    if (success) searchLocation(
                                        mContext,
                                        getPreference(mContext, Constants.TOKEN),
                                        location,
                                        limit
                                    )
                                    else sessionExpireDialog(mContext)
                                })

                    }

                })

        } else toast(mContext, mContext.getString(R.string.txt_Network))
    }

    fun getSavedSearchLocation(
        mContext: Context?,
        token: String?,
        location: String?,
        limit: String?
    ) {
        if (isNetworkAvailable(mContext!!)) {
            apiClient.getSearchLocation(token, location, limit)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<SearchLocationResponseModel?>() {
                    override fun onSuccess(response: SearchLocationResponseModel) {
                        try {
                            mylog(TAG, "onResponse: 2" + Gson().toJson(response))
                            if (response.isStatus) savedSearchLocationList.postValue(response.data)
                            else savedSearchLocationList.postValue(
                                null
                            )
                        } catch (e: Exception) {
                            mylog(
                                TAG,
                                "onResponse:getSavedSearchLocation Error=" + e.localizedMessage
                            )
                            savedSearchLocationList.postValue(null)
                        }
                    }

                    override fun onError(e: Throwable) {
                        savedSearchLocationList.postValue(null)
                        if (e.localizedMessage.equals(Constants.Unauthorized) || e.localizedMessage.equals(
                                Constants.Forbidden
                            )
                        )
                            checkToken(
                                mContext,
                                ApiCallBack { success ->
                                    if (success) getSavedSearchLocation(
                                        mContext,
                                        getPreference(mContext, Constants.TOKEN),
                                        location,
                                        limit
                                    )
                                    else sessionExpireDialog(mContext)
                                })

                    }

                })

        } else toast(mContext, mContext.getString(R.string.txt_Network))
    }

    fun saveSearchLocation(
        mContext: Context?,
        token: String?,
        locationList: SaveLocationRequestModel?
    ) {
        if (isNetworkAvailable(mContext!!)) {
            apiClient.saveSearchLocationData(token, locationList)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<DefaultResponse?>() {
                    override fun onSuccess(response: DefaultResponse) {
                        isDataSaved.postValue(
                            try {
                                mylog(TAG, "onResponse: " + Gson().toJson(response))
                                response.isStatus
                            } catch (e: Exception) {
                                false
                            }
                        )
                    }

                    override fun onError(e: Throwable) {
                        isDataSaved.postValue(false)
                        if (e.localizedMessage.equals(Constants.Unauthorized) || e.localizedMessage.equals(
                                Constants.Forbidden
                            )
                        )
                            checkToken(
                                mContext,
                                ApiCallBack { success ->
                                    if (success) saveSearchLocation(
                                        mContext,
                                        getPreference(mContext, Constants.TOKEN),
                                        locationList
                                    )
                                    else sessionExpireDialog(mContext)
                                })

                    }

                })

        } else toast(mContext, mContext.getString(R.string.txt_Network))
    }

    fun getOffers(
        mContext: Context?,
        token: String?
    ) {
        if (isNetworkAvailable(mContext!!)) {
            apiClient.getOffers(token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<OffersResponseModel?>() {
                    override fun onSuccess(response: OffersResponseModel) {
                        mylog(TAG, "offersResponse: Response=${Gson().toJson(response)}")
                        if (response.status!!) {
                            offers.postValue(response)
                        } else {
                            offers.postValue(response)
                        }
                    }

                    override fun onError(e: Throwable) {
                        mylog(TAG, "onError: offersResponse=" + e.localizedMessage)
                        if (e.localizedMessage.equals(Constants.Unauthorized) || e.localizedMessage.equals(
                                Constants.Forbidden
                            )
                        )
                            checkToken(
                                mContext,
                                ApiCallBack { success ->
                                    if (success) getOffers(
                                        mContext,
                                        getPreference(mContext, Constants.TOKEN)
                                    )
                                    else {
                                        offers.postValue(null)
                                        sessionExpireDialog(mContext)
                                    }
                                })
                        else {
                            offers.postValue(null)
                            alertDialog(mContext, e.localizedMessage)
                        }
                    }

                })

        } else toast(mContext, mContext.getString(R.string.txt_Network))
    }

    fun searchRoutes(
        mContext: Context?,
        token: String?,
        pickUpLat: String,
        pickUpLng: String,
        dropLat: String,
        dropLng: String,
        currentDate: String,
        currentTime: String,
        endDate: String,
        type: String,
        pickUpId: String,
        dropUpId: String,
        has_return: String
    ) {
        if (isNetworkAvailable(mContext!!)) {

            apiClient
                .searchRoutes(
                    token, pickUpLat, pickUpLng, dropLat, dropLng, currentDate, currentTime,
                    endDate, type,pickUpId,dropUpId, has_return
                )
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<SearchRoutesResponseModel?>() {
                    override fun onSuccess(response: SearchRoutesResponseModel) {
                        mylog(TAG, "searchRoutes: Response=${Gson().toJson(response)}")
                        if (response.status!!) {
                            searchedRoutes.postValue(response)
                        } else {
                            searchedRoutes.postValue(response)
                        }
                    }

                    override fun onError(e: Throwable) {
                        mylog(TAG, "onError: searchRoutes=" + e.localizedMessage)
                        if (e.localizedMessage.equals(Constants.Unauthorized) || e.localizedMessage.equals(
                                Constants.Forbidden
                            )
                        )
                            checkToken(
                                mContext,
                                ApiCallBack { success ->
                                    if (success) searchRoutes(
                                        mContext,
                                        getPreference(mContext, Constants.TOKEN),
                                        pickUpLat,
                                        pickUpLng,
                                        dropLat,
                                        dropLng,
                                        currentDate,
                                        currentTime,
                                        endDate,
                                        type,
                                        pickUpId,
                                        dropUpId,
                                        has_return
                                    )
                                    else {
                                        searchedRoutes.postValue(null)
                                        sessionExpireDialog(mContext)
                                    }
                                })
                        else {
                            searchedRoutes.postValue(null)
                            alertDialog(mContext, e.localizedMessage)
                        }
                    }
                })
        } else toast(mContext, mContext.getString(R.string.txt_Network))

    }

    fun addOfficeRideAddress(
        mContext: Context?,
        token: String?,
        home_lat: String,
        home_lng: String,
        home_address: String,
        home_timing: String,
        office_lat: String,
        office_lng: String,
        office_address: String,
        office_timing: String
    ) {
        if (isNetworkAvailable(mContext!!)) {

            apiClient.addOfficeRideAddress(
                token,
                home_lat,
                home_lng,
                home_address,
                home_timing,
                office_lat,
                office_lng,
                office_address,
                office_timing
            )
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<UserProfileUpdateResponse?>() {
                    override fun onSuccess(response: UserProfileUpdateResponse) {
                        mylog(TAG, "addOfficeRideAddress: Response=${Gson().toJson(response)}")
                        if (response.isStatus) {
                            addOfficeRideAddress.postValue(response)
                        } else {
                            addOfficeRideAddress.postValue(response)
                        }
                    }

                    override fun onError(e: Throwable) {
                        mylog(TAG, "onError: addOfficeRideAddress=" + e.localizedMessage)
                        if (e.localizedMessage.equals(Constants.Unauthorized) || e.localizedMessage.equals(
                                Constants.Forbidden
                            )
                        )
                            checkToken(
                                mContext,
                                ApiCallBack { success ->
                                    if (success) addOfficeRideAddress(
                                        mContext,
                                        getPreference(mContext, Constants.TOKEN),
                                        home_lat,
                                        home_lng,
                                        home_address,
                                        home_timing,
                                        office_lat,
                                        office_lng,
                                        office_address,
                                        office_timing
                                    )
                                    else {
                                        addOfficeRideAddress.postValue(null)
                                        sessionExpireDialog(mContext)
                                    }
                                })
                        else {
                            addOfficeRideAddress.postValue(null)
                            alertDialog(mContext, e.localizedMessage)
                        }
                    }
                })
        } else toast(mContext, mContext.getString(R.string.txt_Network))

    }


    fun getProfileDetails(
        mContext: Context?,
        token: String?
    ) {
        if (isNetworkAvailable(mContext!!)) {
            apiClient.getProfileDetails(token)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<UserProfileUpdateResponse?>() {
                    override fun onSuccess(response: UserProfileUpdateResponse) {
                        mylog(TAG, "getProfileDetails: Response=${Gson().toJson(response)}")
                        if (response.isStatus!!) {
                            getUserProfileDetails.postValue(response)
                        } else {
                            getUserProfileDetails.postValue(response)
                        }
                    }

                    override fun onError(e: Throwable) {
                        mylog(TAG, "onError: getProfileDetails=" + e.localizedMessage)
                        if (e.localizedMessage.equals(Constants.Unauthorized) || e.localizedMessage.equals(
                                Constants.Forbidden
                            )
                        )
                            checkToken(
                                mContext,
                                ApiCallBack { success ->
                                    if (success) getProfileDetails(
                                        mContext,
                                        getPreference(mContext, Constants.TOKEN)
                                    )
                                    else {
                                        getUserProfileDetails.postValue(null)
                                        sessionExpireDialog(mContext)
                                    }
                                })
                        else {
                            getUserProfileDetails.postValue(null)
                            alertDialog(mContext, e.localizedMessage)
                        }
                    }
                })
        } else toast(mContext, mContext.getString(R.string.txt_Network))

    }


    fun updateUserProfile(
        mContext: Context?,
        token: String?,
        body: MultipartBody.Part?,
        fName: RequestBody,
        lName: RequestBody,
        gender: RequestBody,
        email: RequestBody,
        referedby: RequestBody,
        socialid: RequestBody,
        mode: RequestBody,
        deviceToken: RequestBody,
        homeAdd: RequestBody,
        homeLat: RequestBody,
        homeLng: RequestBody,
        officeAdd: RequestBody,
        officeLat: RequestBody,
        officeLng: RequestBody,
        homeLeaveTime: RequestBody,
        officeLeaveTime: RequestBody
    ){
        if (isNetworkAvailable(mContext!!)) {

            apiClient.updateUser(
                token,
                body,
                fName,
                lName,
                gender,
                email,
                referedby,
                socialid,
                mode,
                deviceToken,
                homeAdd,
                homeLat,
                homeLng,
                officeAdd,
                officeLat,
                officeLng,
                homeLeaveTime,
                officeLeaveTime
            )
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<UserProfileUpdateResponse?>() {
                    override fun onSuccess(response: UserProfileUpdateResponse) {
                        mylog(TAG, "updateUserProfile: Response=${Gson().toJson(response)}")
                        if (response.isStatus) {
                            updateUserProfile.postValue(response)
                        } else {
                            updateUserProfile.postValue(response)
                        }
                    }

                    override fun onError(e: Throwable) {
                        mylog(TAG, "onError: updateUserProfile=" + e.localizedMessage)
                        if (e.localizedMessage.equals(Constants.Unauthorized) || e.localizedMessage.equals(
                                Constants.Forbidden
                            )
                        )
                            checkToken(
                                mContext,
                                ApiCallBack { success ->
                                    if (success) updateUserProfile(mContext,
                                        getPreference(mContext, Constants.TOKEN),
                                        body,
                                        fName,
                                        lName,
                                        gender,
                                        email,
                                        referedby,
                                        socialid,
                                        mode,
                                        deviceToken,
                                        homeAdd,
                                        homeLat,
                                        homeLng,
                                        officeAdd,
                                        officeLat,
                                        officeLng,
                                        homeLeaveTime,
                                        officeLeaveTime
                                    )
                                    else {
                                        updateUserProfile.postValue(null)
                                        sessionExpireDialog(mContext)
                                    }
                                })
                        else {
                            updateUserProfile.postValue(null)
                            alertDialog(mContext, e.localizedMessage)
                        }
                    }
                })
        } else toast(mContext, mContext.getString(R.string.txt_Network))

    }


    fun initiatePassPayment(
        mContext: Context?,
        token: String?,
        type: String,
        payment_name: String,
        bus_id: String,
        route_id: String,
        routeTimetableId: String,
        pickup_stop_id: String,
        drop_stop_id: String,
        pass_id: String,
        pass_no_of_rides: String,
        pass_amount: String,
        seat_no: String,
        has_return: String,
        payment_mode: String,
        bookingDate: String
    ) {
        if (isNetworkAvailable(mContext!!)) {

            apiClient.initiatePassPayment(
                token,
                type,
                payment_name,
                bus_id,
                route_id,
                routeTimetableId,
                pickup_stop_id,
                drop_stop_id,
                pass_id,
                pass_no_of_rides,
                pass_amount,
                seat_no,
                has_return,
                payment_mode,
                bookingDate
            )
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<InitiatePaymentResponseModel?>() {
                    override fun onSuccess(response: InitiatePaymentResponseModel) {
                        mylog(TAG, "initiatePassPayment: Response=${Gson().toJson(response)}")
                        if (response.isStatus!!) {
                            initiatePassPayment.postValue(response)
                        } else {
                            initiatePassPayment.postValue(response)
                        }
                    }

                    override fun onError(e: Throwable) {
                        mylog(TAG, "onError: initiatePassPayment=" + e.localizedMessage)
                        if (e.localizedMessage.equals(Constants.Unauthorized) || e.localizedMessage.equals(
                                Constants.Forbidden
                            )
                        )
                            checkToken(
                                mContext,
                                ApiCallBack { success ->
                                    if (success) initiatePassPayment(
                                        mContext,
                                        getPreference(mContext, Constants.TOKEN),
                                        type,
                                        payment_name,
                                        bus_id,
                                        route_id,
                                        routeTimetableId,
                                        pickup_stop_id,
                                        drop_stop_id,
                                        pass_id,
                                        pass_no_of_rides,
                                        pass_amount,
                                        seat_no,
                                        has_return,
                                        payment_mode,
                                        bookingDate
                                    )
                                    else {
                                        initiatePassPayment.postValue(null)
                                        sessionExpireDialog(mContext)
                                    }
                                })
                        else {
                            initiatePassPayment.postValue(null)
                            alertDialog(mContext, e.localizedMessage)
                        }
                    }
                })
        } else toast(mContext, mContext.getString(R.string.txt_Network))

    }

    fun routeStops(
        mContext: Context?,
        token: String?,
        id: String,
        pickup_stop_id: String,
        drop_stop_id: String
    ) {
        if (isNetworkAvailable(mContext!!)) {
            apiClient.routeStops(token, id, pickup_stop_id, drop_stop_id)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<RouteStopsResponseModel?>() {
                    override fun onSuccess(response: RouteStopsResponseModel) {
                        mylog(TAG, "routeStops: Response=${Gson().toJson(response)}")
                        if (response.status!!) {
                            routeStops.postValue(response)
                        } else {
                            routeStops.postValue(response)
                        }
                    }

                    override fun onError(e: Throwable) {
                        mylog(TAG, "onError: routeStops=" + e.localizedMessage)
                        if (e.localizedMessage.equals(Constants.Unauthorized) || e.localizedMessage.equals(
                                Constants.Forbidden
                            )
                        )
                            checkToken(
                                mContext,
                                ApiCallBack { success ->
                                    if (success) routeStops(
                                        mContext,
                                        getPreference(mContext, Constants.TOKEN),
                                        id,
                                        pickup_stop_id,
                                        drop_stop_id
                                    )
                                    else {
                                        routeStops.postValue(null)
                                        sessionExpireDialog(mContext)
                                    }
                                })
                        else {
                            routeStops.postValue(null)
                            alertDialog(mContext, e.localizedMessage)
                        }
                    }
                })
        } else toast(mContext, mContext.getString(R.string.txt_Network))

    }

    fun trackRoutes(
        mContext: Context?,
        token: String?,
        pnr_no: String?
    ) {
        if (isNetworkAvailable(mContext!!)) {
            apiClient.tripTracking(token, pnr_no)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<TripTrackingStatusResponse?>() {
                    override fun onSuccess(response: TripTrackingStatusResponse) {
                        mylog(TAG, "trackRoutes: Response=${Gson().toJson(response)}")
                        if (response.status!!) {
                            trackRoutes.postValue(response)
                        } else {
                            trackRoutes.postValue(response)
                        }
                    }

                    override fun onError(e: Throwable) {
                        mylog(TAG, "onError: trackRoutes=" + e.localizedMessage)
                        if (e.localizedMessage.equals(Constants.Unauthorized) || e.localizedMessage.equals(
                                Constants.Forbidden
                            )
                        )
                            checkToken(
                                mContext,
                                ApiCallBack { success ->
                                    if (success) trackRoutes(
                                        mContext,
                                        getPreference(mContext, Constants.TOKEN),
                                        pnr_no
                                    )
                                    else {
                                        trackRoutes.postValue(null)
                                        sessionExpireDialog(mContext)
                                    }
                                })
                        else {
                            trackRoutes.postValue(null)
                            alertDialog(mContext, e.localizedMessage)
                        }
                    }
                })
        } else toast(mContext, mContext.getString(R.string.txt_Network))

    }


    fun busSeats(
        mContext: Context?,
        token: String?,
        bus_id: String,
        route_id: String,
        routeTimetableId: String,
        pickup_stop_id: String,
        drop_stop_id: String,
        type: String,
        has_return: String,
        currentDate: String,
        endDate: String
    ) {
        if (isNetworkAvailable(mContext!!)) {
            apiClient.busSeatsLayout(
                token,
                bus_id,
                route_id,
                routeTimetableId,
                pickup_stop_id,
                drop_stop_id,
                type,
                has_return,
                currentDate,
                endDate
            )
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<BusSeatsResponseModel?>() {
                    override fun onSuccess(response: BusSeatsResponseModel) {
                        mylog(TAG, "busSeats: Response=${Gson().toJson(response)}")
                        if (response.status!!) {
                            busSeatsResponse.postValue(response)
                        } else {
                            busSeatsResponse.postValue(response)
                        }
                    }

                    override fun onError(e: Throwable) {
                        mylog(TAG, "onError: busSeats=" + e.localizedMessage)
                        if (e.localizedMessage.equals(Constants.Unauthorized) || e.localizedMessage.equals(
                                Constants.Forbidden
                            )
                        )
                            checkToken(
                                mContext,
                                ApiCallBack { success ->
                                    if (success) busSeats(
                                        mContext,
                                        getPreference(mContext, Constants.TOKEN),
                                        bus_id,
                                        route_id,
                                        routeTimetableId,
                                        pickup_stop_id,
                                        drop_stop_id,
                                        type,
                                        has_return,
                                        currentDate,
                                        endDate
                                    )
                                    else {
                                        busSeatsResponse.postValue(null)
                                        sessionExpireDialog(mContext)
                                    }
                                })
                        else {
                            busSeatsResponse.postValue(null)
                            alertDialog(mContext, e.localizedMessage)
                        }
                    }
                })
        } else toast(mContext, mContext.getString(R.string.txt_Network))

    }

    fun getRouteFare(
        mContext: Context?,
        token: String?,
        bus_id: String,
        route_id: String,
        routeTimetableId: String,
        pickup_stop_id: String,
        drop_stop_id: String,
        seat_no: String,
        start_date: String,
        has_return: String
    ) {
        if (isNetworkAvailable(mContext!!)) {
            mylog(TAG, "getRouteFare: seat_no=${seat_no}")
            apiClient.getRouteFare(
                token,
                bus_id,
                route_id,
                routeTimetableId,
                pickup_stop_id,
                drop_stop_id,
                seat_no,
                start_date,
                has_return
            )
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<GeneratedFareResponseModel?>() {
                    override fun onSuccess(response: GeneratedFareResponseModel) {
                        mylog(TAG, "getRouteFare: Response=${Gson().toJson(response)}")
                        if (response.status!!) {
                            getRouteFare.postValue(response)
                        } else {
                            getRouteFare.postValue(response)
                        }
                    }

                    override fun onError(e: Throwable) {
                        mylog(TAG, "onError: getRouteFare=" + e.localizedMessage)
                        if (e.localizedMessage.equals(Constants.Unauthorized) || e.localizedMessage.equals(
                                Constants.Forbidden
                            )
                        )
                            checkToken(
                                mContext,
                                ApiCallBack { success ->
                                    if (success) getRouteFare(
                                        mContext,
                                        getPreference(mContext, Constants.TOKEN),
                                        bus_id,
                                        route_id,
                                        routeTimetableId,
                                        pickup_stop_id,
                                        drop_stop_id,
                                        seat_no,
                                        start_date,
                                        has_return
                                    )
                                    else {
                                        getRouteFare.postValue(null)
                                        sessionExpireDialog(mContext)
                                    }
                                })
                        else {
                            getRouteFare.postValue(null)
                            alertDialog(mContext, e.localizedMessage)
                        }
                    }
                })
        } else toast(mContext, mContext.getString(R.string.txt_Network))

    }

    fun createBooking(mContext: Context?, token: String?, bookingData: BookingRequestData?) {
        if (isNetworkAvailable(mContext!!)) {
            mylog(TAG, "createBooking: Request=${Gson().toJson(bookingData)}")
            apiClient.createBooking(token, bookingData)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<BookingResponseModel?>() {
                    override fun onSuccess(response: BookingResponseModel) {
                        mylog(TAG, "createBooking: Response=${Gson().toJson(response)}")
                        if (response.status!!) {
                            bookingResponse.postValue(response)
                        } else {
                            bookingResponse.postValue(response)
                        }
                    }

                    override fun onError(e: Throwable) {
                        mylog(TAG, "onError: createBooking=" + e.localizedMessage)
                        if (e.localizedMessage.equals(Constants.Unauthorized) || e.localizedMessage.equals(
                                Constants.Forbidden
                            )
                        )
                            checkToken(
                                mContext,
                                ApiCallBack { success ->
                                    if (success) createBooking(
                                        mContext,
                                        getPreference(mContext, Constants.TOKEN),
                                        bookingData
                                    )
                                    else {
                                        bookingResponse.postValue(null)
                                        sessionExpireDialog(mContext)
                                    }
                                })
                        else {
                            bookingResponse.postValue(null)
                            alertDialog(mContext, e.localizedMessage)
                        }
                    }
                })
        } else toast(mContext, mContext.getString(R.string.txt_Network))

    }

    fun payRouteFee(
        mContext: Context?,
        token: String?,
        pnr_no: String,
        amount: String,
        payment_mode: String,
        bookingDate: String
    ) {
        if (isNetworkAvailable(mContext!!)) {
            apiClient.payRouteFee(token, pnr_no, amount, payment_mode, bookingDate)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<PaymentInitiateDataResponse?>() {
                    override fun onSuccess(response: PaymentInitiateDataResponse) {
                        mylog(TAG, "payRouteFee: Response=${Gson().toJson(response)}")
                        if (response.status!!) {
                            paymentResponse.postValue(response)
                        } else {
                            paymentResponse.postValue(response)
                        }
                    }

                    override fun onError(e: Throwable) {
                        mylog(TAG, "onError: payRouteFee=" + e.localizedMessage)
                        if (e.localizedMessage.equals(Constants.Unauthorized) || e.localizedMessage.equals(
                                Constants.Forbidden
                            )
                        )
                            checkToken(
                                mContext,
                                ApiCallBack { success ->
                                    if (success) payRouteFee(
                                        mContext,
                                        getPreference(mContext, Constants.TOKEN),
                                        pnr_no,
                                        amount,
                                        payment_mode,
                                        bookingDate
                                    )
                                    else {
                                        paymentResponse.postValue(null)
                                        sessionExpireDialog(mContext)
                                    }
                                })
                        else {
                            paymentResponse.postValue(null)
                            alertDialog(mContext, e.localizedMessage)
                        }
                    }
                })
        } else toast(mContext, mContext.getString(R.string.txt_Network))

    }

    fun initiateWalletRecharge(
        mContext: Context?,
        token: String?,
        type: String,
        payment_name: String,
        amount: Float
    ) {
        if (isNetworkAvailable(mContext!!)) {
            apiClient
                .initiateWalletRechargePayment(token, type, payment_name, amount)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<InitiatePaymentResponseModel?>() {
                    override fun onSuccess(response: InitiatePaymentResponseModel) {
                        mylog(TAG, "initiateWalletRecharge: Response=${Gson().toJson(response)}")
                        if (response.isStatus!!) {
                            initiateWalletRecharge.postValue(response)
                        } else {
                            initiateWalletRecharge.postValue(response)
                        }
                    }

                    override fun onError(e: Throwable) {
                        mylog(TAG, "onError: initiateWalletRecharge=" + e.localizedMessage)
                        if (e.localizedMessage.equals(Constants.Unauthorized) || e.localizedMessage.equals(
                                Constants.Forbidden
                            )
                        )
                            checkToken(
                                mContext,
                                ApiCallBack { success ->
                                    if (success) initiateWalletRecharge(
                                        mContext,
                                        getPreference(mContext, Constants.TOKEN),
                                        type,
                                        payment_name,
                                        amount
                                    )
                                    else {
                                        initiateWalletRecharge.postValue(null)
                                        sessionExpireDialog(mContext)
                                    }
                                })
                        else {
                            initiateWalletRecharge.postValue(null)
                            alertDialog(mContext, e.localizedMessage)
                        }
                    }
                })
        } else toast(mContext, mContext.getString(R.string.txt_Network))

    }
    fun getBookingHistory(
        mContext: Context?,
        token: String?,
        offSet: Int,
        limit: Int
    ) {
        if (isNetworkAvailable(mContext!!)) {
            apiClient
                .getBookingHistory(token, offSet,limit)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<BookingTransactionHistoryResponse?>() {
                    override fun onSuccess(response: BookingTransactionHistoryResponse) {
                        mylog(TAG, "getBookingHistory: Response=${Gson().toJson(response)}")
                        if (response.status!!) {
                            getBookingHistory.postValue(response)
                        } else {
                            getBookingHistory.postValue(response)
                        }
                    }

                    override fun onError(e: Throwable) {
                        mylog(TAG, "onError: getBookingHistory=" + e.localizedMessage)
                        if (e.localizedMessage.equals(Constants.Unauthorized) || e.localizedMessage.equals(
                                Constants.Forbidden
                            )
                        )
                            checkToken(
                                mContext,
                                ApiCallBack { success ->
                                    if (success) getBookingHistory(
                                        mContext,
                                        getPreference(mContext, Constants.TOKEN),offSet,limit
                                    )
                                    else {
                                        getBookingHistory.postValue(null)
                                        sessionExpireDialog(mContext)
                                    }
                                })
                        else {
                            getBookingHistory.postValue(null)
                            alertDialog(mContext, e.localizedMessage)
                        }
                    }
                })
        } else toast(mContext, mContext.getString(R.string.txt_Network))

    }

    fun initiateTripPayment(
        mContext: Context?,
        token: String?,
        type: String,
        payment_name: String,
        amount: Float,
        payment_mode: String,
        pnr_no: String,
    ) {
        if (isNetworkAvailable(mContext!!)) {
            apiClient
                .initiateTripPayment(token, type, payment_name, amount, payment_mode, pnr_no)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<InitiatePaymentResponseModel?>() {
                    override fun onSuccess(response: InitiatePaymentResponseModel) {
                        mylog(TAG, "initiateTripPayment: Response=${Gson().toJson(response)}")
                        if (response.isStatus!!) {
                            initiateTripPayment.postValue(response)
                        } else {
                            initiateTripPayment.postValue(response)
                        }
                    }

                    override fun onError(e: Throwable) {
                        mylog(TAG, "onError: initiateTripPayment=" + e.localizedMessage)
                        if (e.localizedMessage.equals(Constants.Unauthorized) || e.localizedMessage.equals(
                                Constants.Forbidden
                            )
                        )
                            checkToken(
                                mContext,
                                ApiCallBack { success ->
                                    if (success) initiateTripPayment(
                                        mContext,
                                        getPreference(mContext, Constants.TOKEN),
                                        type,
                                        payment_name,
                                        amount,
                                        payment_mode,
                                        pnr_no
                                    )
                                    else {
                                        initiateTripPayment.postValue(null)
                                        sessionExpireDialog(mContext)
                                    }
                                })
                        else {
                            initiateTripPayment.postValue(null)
                            alertDialog(mContext, e.localizedMessage)
                        }
                    }
                })
        } else toast(mContext, mContext.getString(R.string.txt_Network))

    }

    fun setReminder(
        mContext: Context?,
        token: String?,
        pnr_no: String,
        every: ArrayList<String>,
        time: String
    ) {
        if (isNetworkAvailable(mContext!!)) {
            apiClient.setReminder(token, pnr_no, every, time)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<DefaultResponse?>() {
                    override fun onSuccess(response: DefaultResponse) {
                        mylog(TAG, "setReminder: Response=${Gson().toJson(response)}")
                        if (response.isStatus!!) {
                            setReminder.postValue(response)
                        } else {
                            setReminder.postValue(response)
                        }
                    }

                    override fun onError(e: Throwable) {
                        mylog(TAG, "onError: setReminder=" + e.localizedMessage)
                        if (e.localizedMessage.equals(Constants.Unauthorized) || e.localizedMessage.equals(
                                Constants.Forbidden
                            )
                        )
                            checkToken(
                                mContext,
                                ApiCallBack { success ->
                                    if (success) setReminder(
                                        mContext,
                                        getPreference(mContext, Constants.TOKEN),
                                        pnr_no,
                                        every,
                                        time
                                    )
                                    else {
                                        setReminder.postValue(null)
                                        sessionExpireDialog(mContext)
                                    }
                                })
                        else {
                            setReminder.postValue(null)
                            alertDialog(mContext, e.localizedMessage)
                        }
                    }
                })
        } else toast(mContext, mContext.getString(R.string.txt_Network))

    }

    fun exploreRoutes(mContext: Context?, token: String?) {
        if (isNetworkAvailable(mContext!!)) {
            apiClient.exploreRoutes(token)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<ExploreRoutesResponseModel?>() {
                    override fun onSuccess(response: ExploreRoutesResponseModel) {
                        mylog(TAG, "exploreRoutes: Response=${Gson().toJson(response)}")
                        if (response.status!!) {
                            exploreRoutes.postValue(response)
                        } else {
                            exploreRoutes.postValue(response)
                        }
                    }

                    override fun onError(e: Throwable) {
                        mylog(TAG, "onError: exploreRoutes=" + e.localizedMessage)
                        if (e.localizedMessage.equals(Constants.Unauthorized) || e.localizedMessage.equals(
                                Constants.Forbidden
                            )
                        )
                            checkToken(
                                mContext,
                                ApiCallBack { success ->
                                    if (success) exploreRoutes(
                                        mContext,
                                        getPreference(mContext, Constants.TOKEN)
                                    )
                                    else {
                                        exploreRoutes.postValue(null)
                                        sessionExpireDialog(mContext)
                                    }
                                })
                        else {
                            exploreRoutes.postValue(null)
                            alertDialog(mContext, e.localizedMessage)
                        }
                    }
                })
        } else toast(mContext, mContext.getString(R.string.txt_Network))

    }

    fun getBookings(
        mContext: Context?,
        token: String?,
        offset: Int?,
        limit: Int?,
        travelStatus: String?
    ) {
        if (isNetworkAvailable(mContext!!)) {
            apiClient.getBookingList(token, offset, limit, travelStatus)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<BookingListResponseModel?>() {
                    override fun onSuccess(response: BookingListResponseModel) {
                        mylog(TAG, "getBookings: Response=${Gson().toJson(response)}")
                        if (response.status!!) {
                            bookingList.postValue(response)
                        } else {
                            bookingList.postValue(response)
                        }
                    }

                    override fun onError(e: Throwable) {
                        mylog(TAG, "onError: getBookings=" + e.localizedMessage)
                        if (e.localizedMessage.equals(Constants.Unauthorized) || e.localizedMessage.equals(
                                Constants.Forbidden
                            )
                        )
                            checkToken(
                                mContext,
                                ApiCallBack { success ->
                                    if (success) getBookings(
                                        mContext,
                                        getPreference(mContext, Constants.TOKEN),
                                        offset,
                                        limit,
                                        travelStatus
                                    )
                                    else {
                                        bookingList.postValue(null)
                                        sessionExpireDialog(mContext)
                                    }
                                })
                        else {
                            bookingList.postValue(null)
                            alertDialog(mContext, e.localizedMessage)
                        }
                    }
                })
        } else toast(mContext, mContext.getString(R.string.txt_Network))

    }

    fun cancelBookings(mContext: Context?, token: String?, pnr_no: String, currentDate: String?) {
        if (isNetworkAvailable(mContext!!)) {
            apiClient.cancelBooking(token, pnr_no, currentDate)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<DefaultResponse?>() {
                    override fun onSuccess(response: DefaultResponse) {
                        mylog(TAG, "cancelBookings: Response=${Gson().toJson(response)}")
                        if (response.isStatus!!) {
                            cancelBooking.postValue(response)
                        } else {
                            cancelBooking.postValue(response)
                        }
                    }

                    override fun onError(e: Throwable) {
                        mylog(TAG, "onError: cancelBookings=" + e.localizedMessage)
                        if (e.localizedMessage.equals(Constants.Unauthorized) || e.localizedMessage.equals(
                                Constants.Forbidden
                            )
                        )
                            checkToken(
                                mContext,
                                ApiCallBack { success ->
                                    if (success) cancelBookings(
                                        mContext,
                                        getPreference(mContext, Constants.TOKEN),
                                        pnr_no,
                                        currentDate
                                    )
                                    else {
                                        cancelBooking.postValue(null)
                                        sessionExpireDialog(mContext)
                                    }
                                })
                        else {
                            cancelBooking.postValue(null)
                            alertDialog(mContext, e.localizedMessage)
                        }
                    }
                })
        } else toast(mContext, mContext.getString(R.string.txt_Network))

    }

    fun clearNotifications(mContext: Context?, token: String?) {
        if (isNetworkAvailable(mContext!!)) {
            apiClient.clearNotifications(token)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<DefaultResponse?>() {
                    override fun onSuccess(response: DefaultResponse) {
                        mylog(TAG, "clearNotification: Response=${Gson().toJson(response)}")
                        if (response.isStatus!!) {
                            clearNotification.postValue(response)
                        } else {
                            clearNotification.postValue(response)
                        }
                    }

                    override fun onError(e: Throwable) {
                        mylog(TAG, "onError: clearNotification=" + e.localizedMessage)
                        if (e.localizedMessage.equals(Constants.Unauthorized) || e.localizedMessage.equals(
                                Constants.Forbidden
                            )
                        )
                            checkToken(
                                mContext,
                                ApiCallBack { success ->
                                    if (success) clearNotifications(
                                        mContext,
                                        getPreference(mContext, Constants.TOKEN)
                                    )
                                    else {
                                        clearNotification.postValue(null)
                                        sessionExpireDialog(mContext)
                                    }
                                })
                        else {
                            clearNotification.postValue(null)
                            alertDialog(mContext, e.localizedMessage)
                        }
                    }
                })
        } else toast(mContext, mContext.getString(R.string.txt_Network))

    }

    fun fetchNotification(mContext: Context?, token: String?, perPage: Int, page: Int?) {
        if (isNetworkAvailable(mContext!!)) {
            apiClient.getNotifications(token, perPage, page)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<NotificationDataResponse?>() {
                    override fun onSuccess(response: NotificationDataResponse) {
                        mylog(TAG, "fetchNotification: Response=${Gson().toJson(response)}")
                        if (response.status!!) {
                            appNotifications.postValue(response)
                        } else {
                            appNotifications.postValue(response)
                        }
                    }

                    override fun onError(e: Throwable) {
                        mylog(TAG, "onError: fetchNotification=" + e.localizedMessage)
                        if (e.localizedMessage.equals(Constants.Unauthorized) || e.localizedMessage.equals(
                                Constants.Forbidden
                            )
                        )
                            checkToken(
                                mContext,
                                ApiCallBack { success ->
                                    if (success) fetchNotification(
                                        mContext,
                                        getPreference(mContext, Constants.TOKEN),
                                        perPage,
                                        page
                                    )
                                    else {
                                        appNotifications.postValue(null)
                                        sessionExpireDialog(mContext)
                                    }
                                })
                        else {
                            appNotifications.postValue(null)
                            alertDialog(mContext, e.localizedMessage)
                        }
                    }
                })
        } else toast(mContext, mContext.getString(R.string.txt_Network))

    }
    fun checkWalletBalance(mContext: Context?, token: String?) {
        if (isNetworkAvailable(mContext!!)) {
            apiClient.checkWalletBalance(token)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<WalletBalanceResponseModel?>() {
                    override fun onSuccess(response: WalletBalanceResponseModel) {
                        mylog(TAG, "checkWalletBalance: Response=${Gson().toJson(response)}")
                        if (response.status!!) {
                            checkWalletBalance.postValue(response)
                        } else {
                            checkWalletBalance.postValue(response)
                        }
                    }

                    override fun onError(e: Throwable) {
                        mylog(TAG, "onError: checkWalletBalance=" + e.localizedMessage)
                        if (e.localizedMessage.equals(Constants.Unauthorized) || e.localizedMessage.equals(
                                Constants.Forbidden
                            )
                        )
                            checkToken(
                                mContext,
                                ApiCallBack { success ->
                                    if (success) checkWalletBalance(
                                        mContext,
                                        getPreference(mContext, Constants.TOKEN))
                                    else {
                                        checkWalletBalance.postValue(null)
                                        sessionExpireDialog(mContext)
                                    }
                                })
                        else {
                            checkWalletBalance.postValue(null)
                            alertDialog(mContext, e.localizedMessage)
                        }
                    }
                })
        } else toast(mContext, mContext.getString(R.string.txt_Network))

    }

    fun getConfigSettings(
        mContext: Context?,
        token: String
    ) {
        if (isNetworkAvailable(mContext!!)) {
            apiClient.getConfigSettings(token)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<CommonDataResponse?>() {
                    override fun onSuccess(response: CommonDataResponse) {
                        mylog(TAG, "getConfigSettings: Response=${Gson().toJson(response)}")
                        if (response.status!!) {
                            getConfigSettings.postValue(response)
                        } else {
                            getConfigSettings.postValue(response)
                        }
                    }

                    override fun onError(e: Throwable) {
                        mylog(TAG, "onError: getConfigSettings=" + e.localizedMessage)
                        if (e.localizedMessage.equals(Constants.Unauthorized) || e.localizedMessage.equals(
                                Constants.Forbidden
                            )
                        )
                            checkToken(
                                mContext,
                                ApiCallBack { success ->
                                    if (success) getConfigSettings(
                                        mContext,
                                        getPreference(mContext, Constants.TOKEN)
                                    )
                                    else {
                                        getConfigSettings.postValue(null)
                                        sessionExpireDialog(mContext)
                                    }
                                })
                        else {
                            getConfigSettings.postValue(null)
                            alertDialog(mContext, e.localizedMessage)
                        }
                    }
                })
        } else toast(mContext, mContext.getString(R.string.txt_Network))

    }
    fun getReferDetails(
        mContext: Context?,
        token: String
    ) {
        if (isNetworkAvailable(mContext!!)) {
            apiClient.getReferDetails(token)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<ReferCodeModel?>() {
                    override fun onSuccess(response: ReferCodeModel) {
                        mylog(TAG, "getReferDetails: Response=${Gson().toJson(response)}")
                        if (response.status!!) {
                            getReferDetails.postValue(response)
                        } else {
                            getReferDetails.postValue(response)
                        }
                    }

                    override fun onError(e: Throwable) {
                        mylog(TAG, "onError: getReferDetails=" + e.localizedMessage)
                        if (e.localizedMessage.equals(Constants.Unauthorized) || e.localizedMessage.equals(
                                Constants.Forbidden
                            )
                        )
                            checkToken(
                                mContext,
                                ApiCallBack { success ->
                                    if (success) getReferDetails(
                                        mContext,
                                        getPreference(mContext, Constants.TOKEN)
                                    )
                                    else {
                                        getReferDetails.postValue(null)
                                        sessionExpireDialog(mContext)
                                    }
                                })
                        else {
                            getReferDetails.postValue(null)
                            alertDialog(mContext, e.localizedMessage)
                        }
                    }
                })
        } else toast(mContext, mContext.getString(R.string.txt_Network))

    }
    fun fetchNearestStops(
        mContext: Context?,
        token: String,
        lat: String,
        lng: String
    ) {
        if (isNetworkAvailable(mContext!!)) {
            apiClient.fetchNearestStops(token,lat,lng)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<SearchLocationResponseModel?>() {
                    override fun onSuccess(response: SearchLocationResponseModel) {
                        mylog(TAG, "fetchNearestStops: Response=${Gson().toJson(response)}")
                        if (response.isStatus!!) {
                            fetchNearestStops.postValue(response.data)
                        } else {
                            fetchNearestStops.postValue(response.data)
                        }
                    }

                    override fun onError(e: Throwable) {
                        mylog(TAG, "onError: fetchNearestStops=" + e.localizedMessage)
                        if (e.localizedMessage.equals(Constants.Unauthorized) || e.localizedMessage.equals(Constants.Forbidden))
                            checkToken(
                                mContext,
                                ApiCallBack { success ->
                                    if (success) fetchNearestStops(
                                        mContext,
                                        getPreference(mContext, Constants.TOKEN),lat,lng
                                    )
                                    else {
                                        fetchNearestStops.postValue(null)
                                        sessionExpireDialog(mContext)
                                    }
                                })
                        else {
                            fetchNearestStops.postValue(null)
                            alertDialog(mContext, e.localizedMessage)
                        }
                    }
                })
        } else toast(mContext, mContext.getString(R.string.txt_Network))

    }
    fun helpSupport(
        mContext: Context?,
        token: String,
        contact: String,
        helpemail: String,
        description: String
    ) {
        if (isNetworkAvailable(mContext!!)) {
            apiClient.getHelp(token,contact,helpemail,description)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<DefaultResponse?>() {
                    override fun onSuccess(response: DefaultResponse) {
                        mylog(TAG, "helpAndSupport: Response=${Gson().toJson(response)}")
                        if (response.isStatus!!) {
                            helpAndSupport.postValue(response)
                        } else {
                            helpAndSupport.postValue(response)
                        }
                    }

                    override fun onError(e: Throwable) {
                        mylog(TAG, "onError: helpAndSupport=" + e.localizedMessage)
                        if (e.localizedMessage.equals(Constants.Unauthorized) || e.localizedMessage.equals(Constants.Forbidden))
                            checkToken(
                                mContext,
                                ApiCallBack { success ->
                                    if (success) helpSupport(
                                        mContext,
                                        getPreference(mContext, Constants.TOKEN),contact,helpemail,description
                                    )
                                    else {
                                        helpAndSupport.postValue(null)
                                        sessionExpireDialog(mContext)
                                    }
                                })
                        else {
                            helpAndSupport.postValue(null)
                            alertDialog(mContext, e.localizedMessage)
                        }
                    }
                })
        } else toast(mContext, mContext.getString(R.string.txt_Network))

    }

    fun suggestRoute(
        mContext: Context,
        token: String,
        officeAddress: String,
        officeLat: String,
        officeLng: String,
        homeAddress: String,
        homeLat: String,
        homeLng: String,
        pickupCity: String,
        pickupState: String,
        dropCity: String,
        dropState: String
    ){
        if (isNetworkAvailable(mContext)) {
            apiClient.suggestRoute(token,
                officeAddress,
                officeLat,
                officeLng,
                homeAddress,
                homeLat,
                homeLng,pickupCity,pickupState,dropCity,dropState)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<DefaultResponse?>() {
                    override fun onSuccess(response: DefaultResponse) {
                        mylog(TAG, "suggestRoutes: Response=${Gson().toJson(response)}")
                        if (response.isStatus!!) {
                            suggestRoutes.postValue(response)
                        } else {
                            suggestRoutes.postValue(response)
                        }
                    }

                    override fun onError(e: Throwable) {
                        mylog(TAG, "onError: suggestRoutes=" + e.localizedMessage)
                        if (e.localizedMessage.equals(Constants.Unauthorized) || e.localizedMessage.equals(
                                Constants.Forbidden
                            )
                        )
                            checkToken(
                                mContext,
                                ApiCallBack { success ->
                                    if (success) suggestRoute(
                                        mContext,
                                        token,
                                        officeAddress,
                                        officeLat,
                                        officeLng,
                                        homeAddress,
                                        homeLat,
                                        homeLng,pickupCity,pickupState,dropCity,dropState)
                                    else {
                                        suggestRoutes.postValue(null)
                                        sessionExpireDialog(mContext)
                                    }
                                })
                        else {
                            suggestRoutes.postValue(null)
                            alertDialog(mContext, e.localizedMessage)
                        }
                    }
                })
        } else toast(mContext, mContext.getString(R.string.txt_Network))

    }

    fun getWalletHistory(
        mContext: Context?,
        token: String?
    ) {
        if (isNetworkAvailable(mContext!!)) {
            apiClient.getWalletHistory(token)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<WalletHistoryResponseModel?>() {
                    override fun onSuccess(response: WalletHistoryResponseModel) {
                        mylog(TAG, "getWalletHistory: Response=${Gson().toJson(response)}")
                        if (response.status!!) {
                            getWalletHistory.postValue(response)
                        } else {
                            getWalletHistory.postValue(response)
                        }
                    }

                    override fun onError(e: Throwable) {
                        mylog(TAG, "onError: getWalletHistory=" + e.localizedMessage)
                        if (e.localizedMessage.equals(Constants.Unauthorized) || e.localizedMessage.equals(
                                Constants.Forbidden
                            )
                        )
                            checkToken(
                                mContext,
                                ApiCallBack { success ->
                                    if (success) getWalletHistory(
                                        mContext,
                                        getPreference(mContext, Constants.TOKEN)
                                    )
                                    else {
                                        getWalletHistory.postValue(null)
                                        sessionExpireDialog(mContext)
                                    }
                                })
                        else {
                            getWalletHistory.postValue(null)
                            alertDialog(mContext, e.localizedMessage)
                        }
                    }
                })
        } else toast(mContext, mContext.getString(R.string.txt_Network))

    }

    fun registerUser(
        mContext: Context?,
        countyCode: String?,
        phoneNo: String,
        deviceId: String,
        countryDetails: String)
    {
        if (isNetworkAvailable(mContext!!)) {
            apiClient.registerUser(countyCode,phoneNo,deviceId,countryDetails,getPreference(mContext,Constants.LANGUAGE))
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<UserRegisterResponseModel?>() {
                    override fun onSuccess(response: UserRegisterResponseModel) {
                        mylog(TAG, "registerUser: Response=${Gson().toJson(response)}")
                        if (response.isStatus!!) {
                            registerUser.postValue(response)
                        } else {
                            registerUser.postValue(response)
                        }
                    }

                    override fun onError(e: Throwable) {
                        mylog(TAG, "onError: registerUser=" + e.localizedMessage)
                        registerUser.postValue(null)
                        alertDialog(mContext, e.localizedMessage)
                    }
                })
        } else toast(mContext, mContext.getString(R.string.txt_Network))

    }


    fun verifyUser(
        mContext: Context?,
        token: String,
        deviceToken: String,
        deviceType: Int,
        otp: Int,
        mobileVerified: Boolean,
        deviceDetails: String)
    {
        if (isNetworkAvailable(mContext!!)) {
            apiClient.verifyUser(token,deviceToken,deviceType,otp,mobileVerified,deviceDetails)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<UserVerificationResponseModel?>() {
                    override fun onSuccess(response: UserVerificationResponseModel) {
                        mylog(TAG, "verifyUser: Response=${Gson().toJson(response)}")
                        if (response.isStatus!!) {
                            verifyUser.postValue(response)
                        } else {
                            verifyUser.postValue(response)
                        }
                    }

                    override fun onError(e: Throwable) {
                        mylog(TAG, "onError: verifyUser=" + e.localizedMessage)
                        if (e.localizedMessage.equals(Constants.Unauthorized) || e.localizedMessage.equals(
                                Constants.Forbidden
                            )
                        )
                            checkToken(
                                mContext,
                                ApiCallBack { success ->
                                    if (success) verifyUser(mContext,getPreference(mContext, Constants.TOKEN)
                                        ,deviceToken,deviceType,otp,mobileVerified,deviceDetails)
                                    else {
                                        verifyUser.postValue(null)
                                        sessionExpireDialog(mContext)
                                    }
                                })
                        else {
                            verifyUser.postValue(null)
                            alertDialog(mContext, e.localizedMessage)
                        }
                    }
                })
        } else toast(mContext, mContext.getString(R.string.txt_Network))

    }

    fun logOutUser(
        mContext: Context?,
        token: String?,
        csrfToken: String?
    ) {
        if (isNetworkAvailable(mContext!!)) {
            apiClient.logOutUser(token,csrfToken)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<DefaultResponse?>() {
                    override fun onSuccess(response: DefaultResponse) {
                        mylog(TAG, "logOutUser: Response=${Gson().toJson(response)}")
                        if (response.isStatus!!) {
                            logOutUser.postValue(response)
                        } else {
                            logOutUser.postValue(response)
                        }
                    }

                    override fun onError(e: Throwable) {
                        mylog(TAG, "onError: logOutUser=" + e.localizedMessage)
                        if (e.localizedMessage.equals(Constants.Unauthorized) || e.localizedMessage.equals(
                                Constants.Forbidden
                            )
                        )
                            checkToken(
                                mContext,
                                ApiCallBack { success ->
                                    if (success) logOutUser(
                                        mContext,
                                        getPreference(mContext, Constants.TOKEN),
                                        csrfToken
                                    )
                                    else {
                                        logOutUser.postValue(null)
                                        sessionExpireDialog(mContext)
                                    }
                                })
                        else {
                            logOutUser.postValue(null)
                            alertDialog(mContext, e.localizedMessage)
                        }
                    }
                })
        } else toast(mContext, mContext.getString(R.string.txt_Network))

    }
    fun updateLanguage(
        mContext: Context?,
        token: String,
        language: String
    ) {
        if (isNetworkAvailable(mContext!!)) {
            apiClient.updateLanguage(token,language)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<DefaultResponse?>() {
                    override fun onSuccess(response: DefaultResponse) {
                        mylog(TAG, "updateLanguage: Response=${Gson().toJson(response)}")
                        if (response.isStatus!!) {
                            updateLanguage.postValue(response)
                        } else {
                            updateLanguage.postValue(response)
                        }
                    }

                    override fun onError(e: Throwable) {
                        mylog(TAG, "onError: updateLanguage=" + e.localizedMessage)
                        if (e.localizedMessage.equals(Constants.Unauthorized) || e.localizedMessage.equals(
                                Constants.Forbidden
                            )
                        )
                            checkToken(
                                mContext,
                                ApiCallBack { success ->
                                    if (success) updateLanguage(
                                        mContext,
                                        getPreference(mContext, Constants.TOKEN),
                                        language
                                    )
                                    else {
                                        updateLanguage.postValue(null)
                                        sessionExpireDialog(mContext)
                                    }
                                })
                        else {
                            updateLanguage.postValue(null)
                            alertDialog(mContext, e.localizedMessage)
                        }
                    }
                })
        } else toast(mContext, mContext.getString(R.string.txt_Network))

    }
    fun validatePhoneNO(
        mContext: Context?,
        phone: String?,
        countryCode: String?
    ) {
        if (isNetworkAvailable(mContext!!)) {
            apiClient.validatedPhoneNo(phone,countryCode)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<DefaultResponse?>() {
                    override fun onSuccess(response: DefaultResponse) {
                        mylog(TAG, "validatedPhoneNo: Response=${Gson().toJson(response)}")
                        validatePhone.postValue(response)
                    }

                    override fun onError(e: Throwable) {
                        mylog(TAG, "onError: validatedPhoneNo=" + e.localizedMessage)
                        validatePhone.postValue(null)
                        alertDialog(mContext, e.localizedMessage)
                    }
                })
        } else toast(mContext, mContext.getString(R.string.txt_Network))

    }


    override fun onCleared() {
        super.onCleared()
    }

    fun checkToken(mContext: Context, apiCallBack: ApiCallBack) {
        apiClient.refreshToken(
            getPreference(mContext, Constants.PHONE_NO),
            getPreference(mContext, Constants.csrfTOKEN),
            Constants.OnMODEL
        ).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableSingleObserver<RefreshTokenModel?>() {
                override fun onSuccess(response: RefreshTokenModel) {
                    mylog(TAG, "onSuccess: response=" + Gson().toJson(response))
                    if (response.status!!) {
                        savePreference(mContext, Constants.csrfTOKEN, response.data?.csrfToken)
                        savePreference(mContext, Constants.TOKEN, "Bearer " + response.data?.token)

                        apiCallBack.onResponse(true)

                    } else apiCallBack.onResponse(false)
                }

                override fun onError(e: Throwable) {
                    mylog(TAG, "onError: checkToken Error=" + e.localizedMessage)
                    toast(mContext, e.message)
                    apiCallBack.onResponse(false)
                }
            })

    }



}