package com.shuttleclone.userapp.ui.activity

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.shuttleclone.userapp.BaseActivity
import com.shuttleclone.userapp.R
import com.shuttleclone.userapp.ViewModel.HomeFragmentViewModel
import com.shuttleclone.userapp.ui.adapter.BookingTransactionHistoryAdapter
import com.shuttleclone.userapp.utils.Constants
import com.shuttleclone.userapp.utils.LoadingDialog
import com.shuttleclone.userapp.utils.LocaleManager
import com.shuttleclone.userapp.utils.alertDialog
import com.shuttleclone.userapp.utils.getPreference
import com.shuttleclone.userapp.utils.isNetworkAvailable
import com.shuttleclone.userapp.utils.mylog
import com.shuttleclone.userapp.utils.toast

class BookingHistoryActivity : BaseActivity(), View.OnClickListener {
    private val TAG = "BookingHistoryActivity"
    private var rvBookingTransHistory: RecyclerView? = null
    private var mLayNoDataAvailable: LinearLayout? = null
    private var mIvBack: ImageView? = null
    private var offSet = 0
    private var limit = 10
    private var loadingBookingItems = false
    private val homeFragmentViewModel: HomeFragmentViewModel by viewModels()

    override fun onResume() {
        super.onResume()
        LocaleManager().setLocale(this) //This method update the language when the activity orientation changes
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LocaleManager().setLocale(this) //This method update the language when the activity orientation changes
        setContentView(R.layout.activity_booking_trans_history)
        initLayouts()
        initializeListeners()
        getBookingHistory()
        viewModelBookingHistoryResponse()
    }

    private fun viewModelBookingHistoryResponse() {
        try {
            homeFragmentViewModel?.let {
                it.getBookingHistory.observe(this,
                    androidx.lifecycle.Observer {
                        try {
                            mylog(TAG, "viewModelBookingHistoryResponse: Response=${Gson().toJson(it)}")

                            if (it == null)
                                return@Observer

                            if (it.status!!&& it.data!!.isNotEmpty()) {
                                rvBookingTransHistory!!.adapter = BookingTransactionHistoryAdapter(
                                    this@BookingHistoryActivity,
                                    it.data!!,
                                    it.data[0].bookingDetails!!,
                                    "BOOKING_DTL"
                                )
                                RunLayoutAnimation(rvBookingTransHistory)
                                mLayNoDataAvailable!!.isVisible=false
                            }else {
                                alertDialog(this, it.message.toString())
                                mLayNoDataAvailable!!.isVisible=true
                            }


                        } catch (e: Exception) {
                            alertDialog(this, e.localizedMessage)
                        }finally {
                            LoadingDialog.cancelLoading()
                        }

                    })
            }
        } catch (e: java.lang.Exception) {
            mylog(TAG, "viewModelBookingHistoryResponse: Error=" + e.localizedMessage)
        }

    }

    /* initialize listener */
    private fun initializeListeners() {
        mIvBack!!.setOnClickListener(this)

        val llmComplete = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        rvBookingTransHistory!!.layoutManager = llmComplete

       /* rvBookingTransHistory!!.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val currentItems = llmComplete!!.getChildCount()
                val totalItems = llmComplete.getItemCount()
                val scrollOutItems = llmComplete.findFirstVisibleItemPosition()

                if (dy > 0 && loadingBookingItems && (currentItems + scrollOutItems == totalItems)) {
                    loadingBookingItems = false
                    offSet = totalItems + 1
                    getBookingHistory()
                }
            }

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                    loadingBookingItems = true
                }
            }
        })*/

    }

    private fun getBookingHistory() {
        try {
            if (isNetworkAvailable(this)) {

                homeFragmentViewModel?.let {
                    LoadingDialog.showLoadingDialog(this, getString(R.string.pls_wait_loading))
                    it.getBookingHistory(
                        this,
                        getPreference(this, Constants.TOKEN),
                        offSet,limit
                    )
                }

            } else toast(this, getString(R.string.txt_Network))
        } catch (e: java.lang.Exception) {
            mylog(TAG, "BookingTransactionHistory: Error=" + e.localizedMessage)
        }

    }

    /* init layout */
    private fun initLayouts() {
        mIvBack = findViewById(R.id.ivBack)
        rvBookingTransHistory = findViewById(R.id.rvBookingTransHistory)
        mLayNoDataAvailable = findViewById(R.id.layNoDataAvailable)
    }

    /* onClick listener */
    override fun onClick(v: View) {
        if (v === mIvBack)  onBackPressedDispatcher.onBackPressed()
    }
}