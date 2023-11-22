package com.shuttleclone.userapp.ui.activity

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shuttleclone.userapp.BaseActivity
import com.shuttleclone.userapp.R
import com.shuttleclone.userapp.model.PassesList
import com.shuttleclone.userapp.ui.adapter.PassAdapter
import com.shuttleclone.userapp.utils.LocaleManager

class PassActivity : BaseActivity() {
    var rvPasses :RecyclerView?=null
    var layPassAlert :LinearLayout?=null
    var layNoDataAvailable :LinearLayout?=null

    private val TAG="PassActivity"
    private var seatNo=""
    private var passesList: List<PassesList>? = ArrayList()
    private var routeId = ""
    private var pickupId = ""
    private var dropId = ""
    private var busId = ""
    private var has_return = ""
    private var bookingType = ""

    override fun onResume() {
        super.onResume()
        LocaleManager().setLocale(this) //This method update the language when the activity orientation changes
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LocaleManager().setLocale(this) //This method update the language when the activity orientation changes
        setContentView(R.layout.activity_pass)

        findViewById<View>(R.id.ivBack).setOnClickListener { onBackPressedDispatcher.onBackPressed() }

        rvPasses=findViewById(R.id.rvPasses)
        layPassAlert=findViewById(R.id.layPassAlert)
        layNoDataAvailable=findViewById(R.id.layNoDataAvailable)

        try {
            if (null!=intent&&intent.hasExtra("passlist")){
                passesList = intent.getSerializableExtra("passlist") as List<PassesList>
                if (passesList!!.size>0) {
                    hideView(layPassAlert)
                    showView(rvPasses)
                }else {
                    hideView(rvPasses)
                    hideView(layPassAlert)
                    showView(layNoDataAvailable)
                }
            }else {
                hideView(rvPasses)
                showView(layPassAlert)
            }
        }catch (e:Exception){
            hideView(rvPasses)
            hideView(layPassAlert)
            showView(layNoDataAvailable)
        }

        rvPasses?.apply {
            layoutManager=LinearLayoutManager(applicationContext)
            setHasFixedSize(true)
            adapter=PassAdapter(context,passesList)
        }

    }
}