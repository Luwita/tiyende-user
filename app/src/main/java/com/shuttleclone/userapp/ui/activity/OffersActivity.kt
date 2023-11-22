package com.shuttleclone.userapp.ui.activity

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shuttleclone.userapp.BaseActivity
import com.shuttleclone.userapp.R
import com.shuttleclone.userapp.model.OffersData
import com.shuttleclone.userapp.ui.adapter.ViewOfferAdapter
import com.shuttleclone.userapp.utils.Constants
import com.shuttleclone.userapp.utils.LocaleManager

class OffersActivity : BaseActivity(), View.OnClickListener {
    /*variable declaration*/
    private var mRvOffer: RecyclerView? = null
    private var mOfferList: ArrayList<OffersData>? = null
    private var mIvBack: ImageView? = null

    override fun onResume() {
        super.onResume()
        LocaleManager().setLocale(this) //This method update the language when the activity orientation changes
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LocaleManager().setLocale(this) //This method update the language when the activity orientation changes
        setContentView(R.layout.activity_offers)
        initLayouts()
        initializeListeners()
    }

    /* initialize listener */
    private fun initializeListeners() {
        mIvBack!!.setOnClickListener(this)
        mOfferList =
            intent.getSerializableExtra(Constants.intentdata.OFFER) as ArrayList<OffersData>?
        mRvOffer!!.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        mRvOffer!!.adapter = ViewOfferAdapter(this@OffersActivity, mOfferList!!)
        RunLayoutAnimation(mRvOffer)
    }

    /* init layout */
    private fun initLayouts() {
        mIvBack = findViewById(R.id.ivBack)
        mRvOffer = findViewById(R.id.rvOffer)
    }

    /* onClick listener */
    override fun onClick(v: View) {
        if (v === mIvBack) onBackPressedDispatcher.onBackPressed()
    }
}