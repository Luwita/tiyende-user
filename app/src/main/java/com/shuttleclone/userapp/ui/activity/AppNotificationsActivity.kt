package com.shuttleclone.userapp.ui.activity

import com.shuttleclone.userapp.BaseActivity
import android.os.Bundle
import android.view.View
import android.widget.AbsListView
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shuttleclone.userapp.R
import com.shuttleclone.userapp.utils.*
import com.google.gson.Gson
import com.shuttleclone.userapp.ViewModel.HomeFragmentViewModel
import com.shuttleclone.userapp.model.NotificationsDataItem
import com.shuttleclone.userapp.ui.adapter.NotificationsAdapter
import java.lang.Exception

class AppNotificationsActivity : BaseActivity(), View.OnClickListener {
    private val TAG = "AppNotificationsActivity"
    private var mRvAppNotifictaions: RecyclerView? = null
    private var mLayNoDataAvailable: LinearLayout? = null
    private var mIvBack: ImageView? = null
    private var mIvDeleteAll: ImageView? = null
    private var notificationsAdapter: NotificationsAdapter? = null
    private val homeFragmentViewModel: HomeFragmentViewModel by viewModels()
    private var offSet = 0
    private var limit = 10
    private var loadingBookingItems = false
    private var mNotificationDataList: ArrayList<NotificationsDataItem> = ArrayList()


    override fun onResume() {
        super.onResume()
        LocaleManager().setLocale(this) //This method update the language when the activity orientation changes
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LocaleManager().setLocale(this) //This method update the language when the activity orientation changes
        setContentView(R.layout.activity_app_notifications)
        initLayouts()
        initializeListeners()
        getNotifications()
        viewModelNotifications()
    }

    /* initialize listener */
    private fun initializeListeners() {
        mIvBack!!.setOnClickListener(this)
        mIvDeleteAll!!.setOnClickListener(this)
        mRvAppNotifictaions!!.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        notificationsAdapter = NotificationsAdapter(this@AppNotificationsActivity)
        mRvAppNotifictaions!!.adapter = notificationsAdapter
        mRvAppNotifictaions!!.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val visibleItemCount = layoutManager.childCount
                val totalItemCount = layoutManager.itemCount
                val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

                if (dy > 0 && loadingBookingItems && (visibleItemCount + firstVisibleItemPosition >= totalItemCount)) {
                    // Load more data here
                    loadingBookingItems = false
                    offSet += 1
                    getNotifications()
                }

            }

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                    loadingBookingItems = true
                }
            }
        })
    }

    private fun viewModelNotifications() {
        try {
            homeFragmentViewModel?.let {
                it.appNotifications.observe(this,
                    androidx.lifecycle.Observer {
                        LoadingDialog.cancelLoading()
                        try {

                            if (it == null)
                                return@Observer

                            if (it.status!!&&it.data!=null&&it.data!!.size!=0) {

                                if (it.data.size == 0 && offSet == 0)
                                    mNotificationDataList!!.clear()

                                mNotificationDataList!!.addAll(it.data)


                                notificationsAdapter!!.setData(mNotificationDataList)
                                RunLayoutAnimation(mRvAppNotifictaions)
                                mLayNoDataAvailable!!.isVisible=false
                            }else{
                                mLayNoDataAvailable!!.isVisible=true
                                alertDialog(this, it.message.toString())
                            }

                        } catch (e: Exception) {
                            alertDialog(this, e.localizedMessage)
                        }

                    })
            }

            homeFragmentViewModel?.let {
                it.clearNotification.observe(this,
                    androidx.lifecycle.Observer {
                        LoadingDialog.cancelLoading()
                        try {

                            if (it == null)
                                return@Observer

                            if (it.isStatus) {
                                mNotificationDataList!!.clear()
                                notificationsAdapter!!.setData(mNotificationDataList)
                                RunLayoutAnimation(mRvAppNotifictaions)
                                mLayNoDataAvailable!!.isVisible=true
                            }else{
                                mLayNoDataAvailable!!.isVisible=true
                                alertDialog(this, it.message.toString())
                            }

                        } catch (e: Exception) {
                            alertDialog(this, e.localizedMessage)
                        }

                    })
            }
        } catch (e: Exception) {
            mylog(TAG, "clearNotification: Error=" + e.localizedMessage)
        }

    }


    private fun getNotifications() {
        try {
            if (isNetworkAvailable(this)) {
                homeFragmentViewModel?.let {
                    LoadingDialog.showLoadingDialog(this, getString(R.string.pls_wait_loading))
                    it.fetchNotification(
                        this,
                        getPreference(this, Constants.TOKEN),
                        limit,offSet
                    )
                }

            } else toast(this, getString(R.string.txt_Network))
        } catch (e: java.lang.Exception) {
            mylog(TAG, "getNotifications: Error=" + e.localizedMessage)
        }

    }

    /* init layout */
    private fun initLayouts() {
        mIvBack = findViewById(R.id.ivBack)
        mRvAppNotifictaions = findViewById(R.id.rvAppNotifications)
        mLayNoDataAvailable = findViewById(R.id.layNoDataAvailable)
        mIvDeleteAll = findViewById(R.id.ivDeleteAll)
    }

    /* onClick listener */
    override fun onClick(v: View) {
        if (v === mIvBack)  onBackPressedDispatcher.onBackPressed()
        if (v === mIvDeleteAll)  deleteNotifications()
    }

    private fun deleteNotifications() {
        try {
            if (isNetworkAvailable(this)) {
                homeFragmentViewModel?.let {
                    LoadingDialog.showLoadingDialog(this, getString(R.string.pls_wait_loading))
                    it.clearNotifications(
                        this,
                        getPreference(this, Constants.TOKEN)
                    )
                }

            } else toast(this, getString(R.string.txt_Network))
        } catch (e: java.lang.Exception) {
            mylog(TAG, "deleteNotifications: Error=" + e.localizedMessage)
        }

    }
}