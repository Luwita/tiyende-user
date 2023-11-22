package com.shuttleclone.userapp.ui.activity

import com.shuttleclone.userapp.BaseActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shuttleclone.userapp.R
import com.shuttleclone.userapp.ui.adapter.WalletHistoryAdapter
import com.shuttleclone.userapp.utils.*
import com.google.gson.Gson
import com.shuttleclone.userapp.ViewModel.HomeFragmentViewModel

class TransactionHistoryActivity : BaseActivity(), View.OnClickListener {
    private val TAG = "TransactionHistoryActivity"
    private var mRvWalletHistory: RecyclerView? = null
    private var mLayNoDataAvailable: LinearLayout? = null
    private var mIvBack: ImageView? = null
    private val homeFragmentViewModel: HomeFragmentViewModel by viewModels()


    override fun onResume() {
        super.onResume()
        LocaleManager().setLocale(this) //This method update the language when the activity orientation changes
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LocaleManager().setLocale(this) //This method update the language when the activity orientation changes
        setContentView(R.layout.activity_wallet_history)
        initLayouts()
        initializeListeners()
        getWalletHistory()
        viewModelTransactionHistoryResponse()
    }

    /* initialize listener */
    private fun initializeListeners() {
        mIvBack!!.setOnClickListener(this)
        mRvWalletHistory!!.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

    }

    private fun viewModelTransactionHistoryResponse() {
        try {
            homeFragmentViewModel?.let {
                it.getWalletHistory.observe(this,
                    androidx.lifecycle.Observer {
                        LoadingDialog.cancelLoading()
                        try {
                            mylog(TAG, "viewModelTransactionHistoryResponse: Response=${Gson().toJson(it)}")

                            if (it == null)
                                return@Observer

                            if (it.status!!&&null!=it.data&&it.data!!.size!=0) {
                                mRvWalletHistory!!.adapter = WalletHistoryAdapter(
                                    this@TransactionHistoryActivity,
                                    it.data!!
                                )
                                RunLayoutAnimation(mRvWalletHistory)
                                mLayNoDataAvailable!!.isVisible=false
                            }else {
                                mLayNoDataAvailable!!.isVisible = true
                                alertDialog(this, it.message.toString())
                            }
                            LoadingDialog.cancelLoading()

                        } catch (e: java.lang.Exception) {
                            alertDialog(this, e.localizedMessage)
                        }

                    })
            }
        } catch (e: java.lang.Exception) {
            mylog(TAG, "viewModelTransactionHistoryResponse: Error=" + e.localizedMessage)
            LoadingDialog.cancelLoading()
        }

    }

    private fun getWalletHistory() {
        try {
            if (isNetworkAvailable(this!!)) {
                homeFragmentViewModel?.let {
                    LoadingDialog.showLoadingDialog(this, getString(R.string.pls_wait_loading))
                    it.getWalletHistory(
                        this,
                        getPreference(this, Constants.TOKEN)
                    )
                }
            } else toast(this, getString(R.string.txt_Network))
        } catch (e: Exception) {
            mylog(TAG, "getWalletHistory: Error=${e.localizedMessage}")
        }
    }

    /* init layout */
    private fun initLayouts() {
        mIvBack = findViewById(R.id.ivBack)
        mRvWalletHistory = findViewById(R.id.rvWalletHistory)
        mLayNoDataAvailable = findViewById(R.id.layNoDataAvailable)
    }

    /* onClick listener */
    override fun onClick(v: View) {
        if (v === mIvBack) onBackPressedDispatcher.onBackPressed()
    }
}