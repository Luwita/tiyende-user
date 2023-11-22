package com.shuttleclone.userapp.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shuttleclone.userapp.BaseActivity
import com.shuttleclone.userapp.R
import com.shuttleclone.userapp.model.NewPackageModel
import com.shuttleclone.userapp.ui.adapter.ViewPackageAdapter
import com.shuttleclone.userapp.utils.Constants
import com.shuttleclone.userapp.utils.LocaleManager

class PackageActivity : BaseActivity(), View.OnClickListener, ViewPackageAdapter.onClickListener {
    /*variable declaration*/
    private var mRvOffer: RecyclerView? = null
    private var mPackageList: ArrayList<NewPackageModel>? = null
    private var mIvBack: ImageView? = null
    private var mAdapter: ViewPackageAdapter? = null

    override fun onResume() {
        super.onResume()
        LocaleManager().setLocale(this) //This method update the language when the activity orientation changes
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LocaleManager().setLocale(this) //This method update the language when the activity orientation changes
        setContentView(R.layout.activity_package)
        initLayouts()
        initializeListeners()
    }

    /* initialize listener */
    private fun initializeListeners() {
        mIvBack!!.setOnClickListener(this)
        mPackageList = ArrayList()
        mPackageList =
            intent.getSerializableExtra(Constants.intentdata.PACKAGE) as ArrayList<NewPackageModel>?
        mRvOffer!!.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        mAdapter = ViewPackageAdapter(this, mPackageList)
        mRvOffer!!.adapter = mAdapter
        mAdapter!!.setOnClickListener(this)
        RunLayoutAnimation(mRvOffer)
    }

    /* init layout */
    private fun initLayouts() {
        mIvBack = findViewById(R.id.ivBack)
        mRvOffer = findViewById(R.id.rvPackage)
    }

    /* onClick listener */
    override fun onClick(v: View) {
        if (v === mIvBack) onBackPressedDispatcher.onBackPressed()
    }

    /* onClick listener for item */
    override fun onClick(aPackageModel: NewPackageModel) {
        val intent = Intent(this, BusListActivity::class.java)
        intent.putExtra(Constants.intentdata.PACKAGE_NAME, aPackageModel.destination)
        startActivity(intent)
    }
}