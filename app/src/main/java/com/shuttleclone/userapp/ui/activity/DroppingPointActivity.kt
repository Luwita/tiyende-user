package com.shuttleclone.userapp.ui.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shuttleclone.userapp.BaseActivity
import com.shuttleclone.userapp.R
import com.shuttleclone.userapp.model.DroppingModel
import com.shuttleclone.userapp.ui.adapter.DroppingAdapter
import com.shuttleclone.userapp.utils.Constants
import com.shuttleclone.userapp.utils.LocaleManager
import com.shuttleclone.userapp.utils.goHome

class DroppingPointActivity() : BaseActivity(), View.OnClickListener {
    private var mTvPickup: TextView? = null
    private var mTvDropping: TextView? = null
    private var mRvPickup: RecyclerView? = null
    private var mRvDropping: RecyclerView? = null
    private var mPickUpList: MutableList<DroppingModel>? = null
    private var mDroppingList: MutableList<DroppingModel>? = null
    private var mPickupAdapter: DroppingAdapter? = null
    private var mDroppingAdapter: DroppingAdapter? = null
    private var mIvBack: ImageView? = null
    private var mIvHome: ImageView? = null

    override fun onResume() {
        super.onResume()
        LocaleManager().setLocale(this) //This method update the language when the activity orientation changes
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LocaleManager().setLocale(this) //This method update the language when the activity orientation changes
        setContentView(R.layout.activity_dropping_point)
        initLayouts()
        initializeListeners()
    }

    /* initialize listener */
    private fun initializeListeners() {
        mTvPickup!!.setOnClickListener(this)
        mTvDropping!!.setOnClickListener(this)
        mIvBack!!.setOnClickListener(this)
        mIvHome!!.setOnClickListener(this)
        mRvPickup!!.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        mRvDropping!!.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        mPickUpList = ArrayList()
        mDroppingList = ArrayList()
        mPickUpList!!.add(
            DroppingModel(
                getString(R.string.lbl_pickup1),
                getString(R.string.lbl_location1),
                getString(R.string.lbl_duration1)
            )
        )
        mPickUpList!!.add(
            DroppingModel(
                getString(R.string.lbl_pickup2),
                getString(R.string.lbl_location1),
                getString(R.string.lbl_duration1)
            )
        )
        mDroppingList!!.add(
            DroppingModel(
                getString(R.string.lbl_dropping1),
                getString(R.string.lbl_location1),
                getString(R.string.lbl_duration1)
            )
        )
        mDroppingList!!.add(
            DroppingModel(
                getString(R.string.lbl_droppin2),
                getString(R.string.lbl_location1),
                getString(R.string.lbl_duration1)
            )
        )
        mPickupAdapter = DroppingAdapter(this, mPickUpList)
        mDroppingAdapter = DroppingAdapter(this, mDroppingList)
        mRvPickup!!.adapter = mPickupAdapter
        mRvDropping!!.adapter = mDroppingAdapter
        mPickupAdapter!!.setOnClickListener(object : DroppingAdapter.onClickListener {
            override fun onClick(i1: Int, name: String) {
                Handler(Looper.myLooper()!!).postDelayed(
                    Runnable {
                        mTvDropping!!.background =
                            resources.getDrawable(R.drawable.bg_rightswitch_select)
                        mTvDropping!!.setTextColor(resources.getColor(R.color.white))
                        mTvPickup!!.setTextColor(resources.getColor(R.color.textheader))
                        mTvPickup!!.background = resources.getDrawable(R.drawable.bg_leftswitch)
                        hideView(mRvPickup)
                        showView(mRvDropping)
                        mPickup = name
                    },
                    500
                )
            }
        })
        mDroppingAdapter!!.setOnClickListener(object : DroppingAdapter.onClickListener {
            override fun onClick(i: Int, name: String) {
                Handler(Looper.myLooper()!!).postDelayed(
                    object : Runnable {
                        override fun run() {
                            mDropping = name
                            //                                                  startActivity(PassengerDetailActivity.class);
                            val i = Intent(baseContext, BookingActivity::class.java)
                            i.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                            i.putExtra(
                                Constants.intentdata.TRAVELLERNAME,
                                getString(R.string.app_name)
                            )
                            i.putExtra(
                                Constants.intentdata.TYPECOACH,
                                getString(R.string.lbl_type1)
                            )
                            i.putExtra(
                                Constants.intentdata.PRICE, getString(R.string.lbl_price2).replace(
                                    defaultCurrency, ""
                                )
                            )
                            i.putExtra(Constants.intentdata.HOLD, getString(R.string.lbl_hold))
                            startActivity(i)
                        }
                    },
                    500
                )
            }
        })
        RunLayoutAnimation(mRvPickup)
        RunLayoutAnimation(mRvDropping)
    }

    /* init layout */
    private fun initLayouts() {
        mTvPickup = findViewById(R.id.tvPickup)
        mTvDropping = findViewById(R.id.tvDropping)
        mRvPickup = findViewById(R.id.rvPickup)
        mRvDropping = findViewById(R.id.rvDropping)
        mIvBack = findViewById(R.id.ivBack)
        mIvHome = findViewById(R.id.ivHome)
    }

    /* onClick listener */
    override fun onClick(v: View) {
        if (v === mTvPickup) {
            mTvPickup!!.background = resources.getDrawable(R.drawable.bg_leftswitch_select)
            mTvPickup!!.setTextColor(resources.getColor(R.color.white))
            mTvDropping!!.setTextColor(resources.getColor(R.color.textheader))
            mTvDropping!!.background = resources.getDrawable(R.drawable.bg_rightswitch)
            hideView(mRvDropping)
            showView(mRvPickup)
            RunLayoutAnimation(mRvPickup)
        } else if (v === mTvDropping) {
            mTvDropping!!.background = resources.getDrawable(R.drawable.bg_rightswitch_select)
            mTvDropping!!.setTextColor(resources.getColor(R.color.white))
            mTvPickup!!.setTextColor(resources.getColor(R.color.textheader))
            mTvPickup!!.background = resources.getDrawable(R.drawable.bg_leftswitch)
            hideView(mRvPickup)
            showView(mRvDropping)
            RunLayoutAnimation(mRvDropping)
        } else if (v === mIvBack) onBackPressedDispatcher.onBackPressed() else if (v === mIvHome) goHome(applicationContext)
    }

    companion object {
        /*variable declaration*/
        var mPickup: String? = null
        var mDropping: String? = null
    }
}