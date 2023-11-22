package com.shuttleclone.userapp.ui.activity

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shuttleclone.userapp.BaseActivity
import com.shuttleclone.userapp.R
import com.shuttleclone.userapp.ViewModel.HomeFragmentViewModel
import com.shuttleclone.userapp.model.RoutesDataItem
import com.shuttleclone.userapp.ui.adapter.BusRoutesAdapter
import com.shuttleclone.userapp.ui.adapter.RouteStopsAdapter
import com.shuttleclone.userapp.utils.*
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog

class ExploreActivity : BaseActivity() {
    private var TAG = "ExploreActivity"
    private val homeFragmentViewModel: HomeFragmentViewModel by viewModels()
    private var routesRecyclerView: RecyclerView? = null
    private var busRoutesAdapter: BusRoutesAdapter? = null
    private var notRoutesFoundLayout: LinearLayout? = null

    override fun onResume() {
        super.onResume()
        LocaleManager().setLocale(this) //This method update the language when the activity orientation changes
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LocaleManager().setLocale(this) //This method update the language when the activity orientation changes
        setContentView(R.layout.activity_explore)


        findViewById<View>(R.id.ivBack).setOnClickListener { finish() }
        findViewById<View>(R.id.ivHome).setOnClickListener { goHome(applicationContext) }
        routesRecyclerView = findViewById(R.id.rvBusRoutes)
        notRoutesFoundLayout = findViewById(R.id.llRoutesNotFound)

        routesRecyclerView!!.setLayoutManager(
            LinearLayoutManager(
                this,
                RecyclerView.VERTICAL,
                false
            )
        )

    }

    override fun onStart() {
        super.onStart()
        homeFragmentViewModel!!.exploreRoutes(this, getPreference(this, Constants.TOKEN))

        LoadingDialog.showLoadingDialog(this, getString(R.string.pls_wait_loading))
        homeFragmentViewModel!!.exploreRoutes.observe(this, Observer {
            LoadingDialog.cancelLoading()
            try {
                if (it==null) {
                    notRoutesFoundLayout!!.visibility = View.VISIBLE
                    return@Observer
                }

                if (it.status!! && it.data!!.size != 0) {
                    routesRecyclerView!!.apply {
                        layoutManager = LinearLayoutManager(
                            this@ExploreActivity,
                            RecyclerView.VERTICAL,
                            false
                        )
                        adapter = BusRoutesAdapter(this@ExploreActivity, it.data)
                        setHasFixedSize(true)
                    }
                    RunLayoutAnimation(routesRecyclerView)
                    notRoutesFoundLayout!!.visibility = View.GONE
                } else {
                    notRoutesFoundLayout!!.visibility = View.VISIBLE
                    alertDialog(this, it.message.toString())
                }
            } catch (e: Exception) {
                alertDialog(this, e.localizedMessage)
            }
        })
    }

    fun viewRouteStops(stopsData: RoutesDataItem) {
        try {
            val view: View = layoutInflater.inflate(R.layout.route_stops_layout, null)
            val dialog = BottomSheetDialog(this, R.style.CustomBottomSheetDialogTheme)

            view.findViewById<TextView>(R.id.tvTitle).text = stopsData.routeTitle + getString(R.string.stops)
            view.findViewById<ImageView>(R.id.imgCancel).setOnClickListener { dialog.dismiss() }
            val rvBusRouteStops = view.findViewById<RecyclerView>(R.id.rvBusRouteStops)


            rvBusRouteStops.apply {
                layoutManager =
                    LinearLayoutManager(this@ExploreActivity, RecyclerView.VERTICAL, false)
                adapter = RouteStopsAdapter(this@ExploreActivity, stopsData!!.stops)
                setHasFixedSize(true)
            }
            RunLayoutAnimation(rvBusRouteStops)
            dialog.setOnShowListener { dialog ->
                val d = dialog as BottomSheetDialog
                val bottomSheet =
                    d.findViewById<View>(R.id.design_bottom_sheet) as FrameLayout?
                val behavior = BottomSheetBehavior.from<View?>(bottomSheet!!)
                behavior.state = BottomSheetBehavior.STATE_EXPANDED

                behavior.setBottomSheetCallback(object :
                    BottomSheetBehavior.BottomSheetCallback() {
                    override fun onStateChanged(bottomSheet: View, newState: Int) {
                        if (newState == BottomSheetBehavior.STATE_DRAGGING) {
                            behavior.state = BottomSheetBehavior.STATE_EXPANDED
                        }
                    }
                    override fun onSlide(bottomSheet: View, slideOffset: Float) {}
                })
            }

            dialog.setContentView(view)
            dialog.setCancelable(false)
            dialog.setCanceledOnTouchOutside(true)
            dialog.show()
        } catch (e: Exception) {
            mylog(TAG, "viewRouteStops: Error=" + e.localizedMessage)
        }
    }

}