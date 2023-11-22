package com.shuttleclone.userapp.ui.activity

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import com.shuttleclone.userapp.BaseActivity
import com.shuttleclone.userapp.R
import com.shuttleclone.userapp.utils.LocaleManager

class SettingActivity : BaseActivity(), View.OnClickListener {
    /*variable declaration*/
    private var mIvBack: ImageView? = null
    private var mIvNotification: ImageView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LocaleManager().setLocale(this) //This method update the language when the activity orientation changes
        setContentView(R.layout.activity_setting)
        initLayouts()
        initializeListeners()
    }

    override fun onResume() {
        super.onResume()
        LocaleManager().setLocale(this) //This method update the language when the activity orientation changes
    }

    /* initialize listener */
    private fun initializeListeners() {
        mIvBack!!.setOnClickListener(this)
        mIvNotification!!.setOnClickListener(this)
        SetNotificationImage(mIvNotification)
    }

    /* init layout */
    private fun initLayouts() {
        mIvBack = findViewById(R.id.ivBack)
        mIvNotification = findViewById(R.id.ivNotification)
    }

    /* onClick listener */
    override fun onClick(v: View) {
        if (v === mIvBack) {
            onBackPressedDispatcher.onBackPressed()
        } else if (v === mIvNotification) {
            startActivity(AppNotificationsActivity::class.java)
        }
    }
}