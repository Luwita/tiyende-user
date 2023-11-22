package com.shuttleclone.userapp.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.viewModels
import com.shuttleclone.userapp.R
import com.shuttleclone.userapp.ui.events.SuggestRouteLocationEvent
import com.shuttleclone.userapp.ui.fragment.SearchLocationDialogFragment
import com.shuttleclone.userapp.utils.*
import com.shuttleclone.userapp.utils.Constants.SUGGEST_DROP
import com.shuttleclone.userapp.utils.Constants.SUGGEST_PICK
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.*
import com.google.gson.Gson
import com.shuttleclone.userapp.ViewModel.HomeFragmentViewModel
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class SuggestRouteActivity : AppCompatActivity(), OnMapReadyCallback {

    private val TAG = "SuggestRouteActivity"
    private var mapView: MapView? = null
    private var map: GoogleMap? = null
    val markers: MutableList<Marker> = ArrayList(2)
    private var mEdFromCity: TextView? = null
    private var mEdToCity: TextView? = null
    private var officeAddress = ""
    private var officeLat = ""
    private var officeLng = ""
    private var homeAddress = ""
    private var homeLat = ""
    private var homeLng = ""
    private var pickup_city = ""
    private var pickup_state = ""
    private var drop_city = ""
    private var drop_state = ""
    private val homeFragmentViewModel: HomeFragmentViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LocaleManager().setLocale(this) //This method update the language when the activity orientation changes
        setContentView(R.layout.activity_suggest_route)

        findViewById<View>(R.id.ivBack).setOnClickListener { finish() }

        mapView = findViewById<View>(R.id.map) as MapView
        mapView!!.onCreate(savedInstanceState)
        mapView!!.getMapAsync(this)
        mEdFromCity = findViewById(R.id.edFromCity)
        mEdToCity = findViewById(R.id.edToCity)

        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this)
        }

        mEdFromCity!!.clickWithThrottle {
            val searchFragment = SearchLocationDialogFragment(this!!, Constants.SUGGEST_PICK)
            if (!searchFragment!!.isVisible) {
                searchFragment!!.show(supportFragmentManager!!, "SearchAddressFragment")
            }
        }
        mEdToCity!!.clickWithThrottle {
            val searchFragment = SearchLocationDialogFragment(this!!, Constants.SUGGEST_DROP)
            if (!searchFragment!!.isVisible) {
                searchFragment!!.show(supportFragmentManager!!, "SearchAddressFragment")
            }
        }

        findViewById<Button>(R.id.btnSubmit)!!.clickWithThrottle {
            updateSuggestedRoute()
        }

        viewModelSuggestRoutesResponse()
    }

    private fun viewModelSuggestRoutesResponse() {
        try {
            homeFragmentViewModel?.let {
                it.suggestRoutes.observe(this,
                    androidx.lifecycle.Observer {
                        LoadingDialog.cancelLoading()
                        try {
                            mylog(TAG, "viewModelSuggestRoutesResponse: Response=${Gson().toJson(it)}")

                            if (it == null)
                                return@Observer

                            toast(this@SuggestRouteActivity, it.message)

                            if (it.isStatus) {
                                finish()
                            }

                        } catch (e: java.lang.Exception) {
                            alertDialog(this, e.localizedMessage)
                        }

                    })
            }
        } catch (e: java.lang.Exception) {
            mylog(TAG, "viewModelSuggestRoutesResponse: Error=" + e.localizedMessage)
            LoadingDialog.cancelLoading()
        }

    }


    private fun updateSuggestedRoute() {
        try {
            if (!officeAddress.equals("") && !homeAddress.equals(""))
                if (officeAddress != homeAddress)
                    if (isNetworkAvailable(this!!)) {
                        homeFragmentViewModel?.let {
                            LoadingDialog.showLoadingDialog(this, getString(R.string.pls_wait_loading))
                            it.suggestRoute(
                                this,
                                getPreference(this, Constants.TOKEN),
                                officeAddress,
                                officeLat,
                                officeLng,
                                homeAddress,
                                homeLat,
                                homeLng,pickup_city,pickup_state,drop_city,drop_state
                            )
                        }
                    } else toast(this, getString(R.string.txt_Network))
                else toast(this, getString(R.string.please_check_pick_up_and_drop_locations_could_not_be_same))
            else toast(this, getString(R.string.please_add_pick_up_and_drop_locations_first))
        } catch (e: Exception) {
            mylog(TAG, "updateSuggestedRoute: Error=${e.localizedMessage}")
        }
    }

    override fun onMapReady(gmap: GoogleMap) {
        map = gmap
        map!!.uiSettings.isMapToolbarEnabled = false
        map!!.uiSettings.isZoomControlsEnabled = false
    }


    protected fun createMarker(
        latitude: Double,
        longitude: Double,
        title: String?,
        iconResID: BitmapDescriptor
    ): Marker? {
        return map!!.addMarker(
            MarkerOptions()
                .position(LatLng(latitude, longitude))
                .title(title)
                .icon(iconResID)
        )
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onEventMainThread(pusher: SuggestRouteLocationEvent) {
        try {
            mylog(TAG, "SuggestRouteLocationEvent: " + pusher.locationData.title)
            mylog(TAG, "SuggestRouteLocationEvent: " + pusher.updateFor)
            if (pusher.updateFor == SUGGEST_PICK) {
                officeAddress = pusher.locationData.title
                officeLat = pusher.locationData.locationLatitude.toString()
                officeLng = pusher.locationData.locationLongitude.toString()
                val zone= getZoneName(pusher.locationData.locationLatitude,pusher.locationData.locationLongitude,this)
                pickup_city =zone!!.split("_")[0]
                pickup_state = zone!!.split("_")[1]
                mEdFromCity!!.setText(officeAddress)
                updateOnMap(officeAddress, officeLat, officeLng, SUGGEST_PICK)
            } else if (pusher.updateFor == SUGGEST_DROP) {
                homeAddress = pusher.locationData.title
                homeLat = pusher.locationData.locationLatitude.toString()
                homeLng = pusher.locationData.locationLongitude.toString()
                val zone= getZoneName(pusher.locationData.locationLatitude,pusher.locationData.locationLongitude,this)
                drop_city = zone!!.split("_")[0]
                drop_state = zone!!.split("_")[1]
                mEdToCity!!.setText(homeAddress)
                updateOnMap(homeAddress, homeLat, homeLng, SUGGEST_DROP)
            }

        } catch (e: Exception) {
            mylog(TAG, "setLocationData:Error=${e.localizedMessage} ")
        }
    }

    private fun updateOnMap(address: String, lat: String, lng: String, updateFor: String) {
        try {
            when (updateFor) {
                SUGGEST_PICK -> {
                    if (markers.size >= 1)
                        markers[0].remove()

                    markers.add(
                        0,
                        createMarker(
                            lat.toDouble(),
                            lng.toDouble(),
                            address,
                            BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)
                        )!!
                    )
                }
                SUGGEST_DROP -> {
                    if (markers.size >= 2)
                        markers[1].remove()

                    markers.add(
                        1,
                        createMarker(
                            lat.toDouble(),
                            lng.toDouble(),
                            address,
                            BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)
                        )!!
                    )
                }

            }
            mylog(TAG, "markers.size: =${markers.size}")
            if (markers.size >= 2) {
                val builder = LatLngBounds.Builder()
                for (marker in markers) {
                    builder.include(marker.position)
                }
                val bounds = builder.build()
                map!!.setPadding(10, 200, 10, 110)
                map!!.animateCamera(CameraUpdateFactory.newLatLngBounds(bounds, 100), 2000, null);
            } else map!!.animateCamera(
                CameraUpdateFactory.newLatLng(markers[0].position),
                1000,
                null
            );

        } catch (e: Exception) {
            mylog(TAG, "updateOnMap: Error=${e.localizedMessage}")
        }
    }

    override fun onStart() {
        super.onStart()
        mapView!!.onStart()
    }


    override fun onResume() {
        super.onResume()
        mapView!!.onResume()
        LocaleManager().setLocale(this)
    }

    override fun onStop() {
        super.onStop()
        mapView!!.onStop()
    }

    override fun onPause() {
        mapView!!.onPause()
        super.onPause()
    }

    override fun onDestroy() {
        mapView!!.onDestroy()
        EventBus.getDefault().unregister(this)
        super.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView!!.onLowMemory()
    }

}