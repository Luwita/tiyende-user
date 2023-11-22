package com.shuttleclone.userapp.ui.activity

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PolylineOptions
import com.shuttleclone.userapp.BaseActivity
import com.shuttleclone.userapp.R
import com.shuttleclone.userapp.ViewModel.HomeFragmentViewModel
import com.shuttleclone.userapp.ui.adapter.ViewStopsAdapter
import com.shuttleclone.userapp.utils.Constants
import com.shuttleclone.userapp.utils.LoadingDialog
import com.shuttleclone.userapp.utils.LocaleManager
import com.shuttleclone.userapp.utils.alertDialog
import com.shuttleclone.userapp.utils.getMarkerIconFromDrawable
import com.shuttleclone.userapp.utils.getPreference
import com.shuttleclone.userapp.utils.goHome
import com.shuttleclone.userapp.utils.mylog
import com.shuttleclone.userapp.utils.toast


class BusRoutesActivity : BaseActivity(), OnMapReadyCallback {

    private var TAG = "BusRoutesActivity"
    private val homeFragmentViewModel: HomeFragmentViewModel by viewModels()
    private var tvRouteStopTitle: TextView? = null
    private var rvBusRoutesStops: RecyclerView? = null
    private var routeId = ""
    private var pickupId = ""
    private var dropId = ""
    private var mapView: MapView? = null
    val markers: MutableList<Marker> = ArrayList()
    private var map: GoogleMap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LocaleManager().setLocale(this) //This method update the language when the activity orientation changes
        setContentView(R.layout.activity_bus_routes)


        findViewById<View>(R.id.ivBack).setOnClickListener { finish() }
        findViewById<View>(R.id.ivHome).setOnClickListener { goHome(applicationContext) }

//        tvRouteStopTitle = findViewById(R.id.tvRouteStopTitle)
        rvBusRoutesStops = findViewById(R.id.rvBusRoutesStops)

        try {
            if (intent != null) {
                routeId = intent.getStringExtra("routeId").toString()
                pickupId = intent.getStringExtra("pickupId").toString()
                dropId = intent.getStringExtra("dropId").toString()
            }
        } catch (e: Exception) {
            mylog(TAG, "onCreate: error=${e.localizedMessage}")
        }


        mapView = findViewById<View>(R.id.map) as MapView
        mapView!!.onCreate(savedInstanceState)
        mapView!!.getMapAsync(this)

    }

    override fun onMapReady(gmap: GoogleMap) {
        map = gmap
        map!!.uiSettings.isMapToolbarEnabled = false
        map!!.uiSettings.isZoomControlsEnabled = false
        map!!.setMapStyle(MapStyleOptions.loadRawResourceStyle(this, R.raw.map_style))
        if (routeId != "" && pickupId != "" && dropId != "") {
            LoadingDialog.showLoadingDialog(this, getString(R.string.pls_wait_loading))
            homeFragmentViewModel!!.routeStops(
                this,
                getPreference(this, Constants.TOKEN),
                routeId,
                pickupId,
                dropId
            )
        } else toast(this, getString(R.string.txt_Something_wrong))

        homeFragmentViewModel!!.routeStops.observe(this, Observer {
            LoadingDialog.cancelLoading()

            try {
                if (it == null)
                    return@Observer

                if (it.status!! && it.data!!.size != 0) {

                    val stops = it.data

                    val polylineOptions = PolylineOptions()

                    for (i in 0 until stops.size) {
                        markers.add(
                            createMarker(
                                stops.get(i)!!.lat!!,
                                stops.get(i)!!.lng!!,
                                stops.get(i)!!.name,
                                stops.get(i)!!.name,
                                R.drawable.bus_stop_pin
                            )!!
                        )
                        polylineOptions.add(LatLng(stops.get(i)!!.lat!!, stops.get(i)!!.lng!!))
                    }


                    val builder = LatLngBounds.Builder()
                    for (marker in markers) {
                        builder.include(marker.position)
                    }
                    val bounds = builder.build()
                    val cu = CameraUpdateFactory.newLatLngBounds(bounds, 100)
                    map!!.moveCamera(cu)
                    map!!.animateCamera(cu, 2000, null);

                    polylineOptions
                        .width(5f)
                        .color(Color.RED)
                    map!!.addPolyline(polylineOptions)

                    rvBusRoutesStops!!.apply {
                        layoutManager = LinearLayoutManager(
                            this@BusRoutesActivity,
                            RecyclerView.VERTICAL,
                            false
                        )
                        adapter = ViewStopsAdapter(this@BusRoutesActivity, stops)
                        setHasFixedSize(true)
                    }
                    RunLayoutAnimation(rvBusRoutesStops)

                } else alertDialog(this, it.message.toString())

            } catch (e: Exception) {
                alertDialog(this, e.localizedMessage)
            }
        })
    }


    protected fun createMarker(
        latitude: Double,
        longitude: Double,
        title: String?,
        snippet: String?,
        iconResID: Int
    ): Marker? {
        return map!!.addMarker(
            MarkerOptions()
                .position(LatLng(latitude, longitude))
                .anchor(0.5f, 0.5f)
                .title(title)
                .icon(getMarkerIconFromDrawable(getDrawable(iconResID)!!))
        )
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
        super.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView!!.onLowMemory()
    }

}