package com.shuttleclone.userapp.ui.activity

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shuttleclone.userapp.BaseActivity
import com.shuttleclone.userapp.R
import com.shuttleclone.userapp.model.BookingDataItem
import com.shuttleclone.userapp.model.BookingDetailsItem
import com.shuttleclone.userapp.ui.adapter.BookingTransactionHistoryAdapter
import com.shuttleclone.userapp.utils.LocaleManager
import com.shuttleclone.userapp.utils.toast

class BookedPassDetailsActivity : BaseActivity(), View.OnClickListener {
    private val TAG = "BookedPassDetailsActivity"
    private var rvBookingTransHistory: RecyclerView? = null
    private var mIvBack: ImageView? = null

    override fun onResume() {
        super.onResume()
        LocaleManager().setLocale(this) //This method update the language when the activity orientation changes
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LocaleManager().setLocale(this) //This method update the language when the activity orientation changes
        setContentView(R.layout.activity_booked_pass_dtls)
        initLayouts()
        initializeListeners()

        try {
            if (intent!=null){
                val allData=intent.getSerializableExtra("allData") as List<BookingDataItem>
                val passData=intent.getSerializableExtra("passData") as List<BookingDetailsItem>
                if (passData!=null&&passData.size!=0)
                  showPassDetails(allData,passData)
            }
        }catch (e:Exception){
            toast(this,getString(R.string.txt_Something_wrong))
        }

    }

    private fun showPassDetails(
        allData: List<BookingDataItem>,
        passData: List<BookingDetailsItem>
    ) {
        rvBookingTransHistory!!.adapter = BookingTransactionHistoryAdapter(
            this@BookedPassDetailsActivity,
            allData!!,
            passData!!,
            "FOR_PASS_DTL"
        )
        RunLayoutAnimation(rvBookingTransHistory)
    }

    /* initialize listener */
    private fun initializeListeners() {
        mIvBack!!.setOnClickListener(this)
        rvBookingTransHistory!!.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

    }

    /* init layout */
    private fun initLayouts() {
        mIvBack = findViewById(R.id.ivBack)
        rvBookingTransHistory = findViewById(R.id.rvBookingTransHistory)
    }

    /* onClick listener */
    override fun onClick(v: View) {
        if (v === mIvBack)  onBackPressedDispatcher.onBackPressed()
    }
}