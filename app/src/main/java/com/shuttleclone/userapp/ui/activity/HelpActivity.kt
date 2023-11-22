package com.shuttleclone.userapp.ui.activity

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.activity.viewModels
import com.google.gson.Gson
import com.shuttleclone.userapp.BaseActivity
import com.shuttleclone.userapp.R
import com.shuttleclone.userapp.ViewModel.HomeFragmentViewModel
import com.shuttleclone.userapp.utils.Constants
import com.shuttleclone.userapp.utils.LoadingDialog
import com.shuttleclone.userapp.utils.LocaleManager
import com.shuttleclone.userapp.utils.alertDialog
import com.shuttleclone.userapp.utils.getPreference
import com.shuttleclone.userapp.utils.isNetworkAvailable
import com.shuttleclone.userapp.utils.mylog
import com.shuttleclone.userapp.utils.toast
import java.util.regex.Pattern

class HelpActivity : BaseActivity(), View.OnClickListener {
    /*variable declaration*/
    private var mIvBack: ImageView? = null
    private var mIvNotification: ImageView? = null
    private var mEdContact: EditText? = null
    private var mEdEmail: EditText? = null
    private var mEdMessage: EditText? = null
    private var mBtnSubmit: Button? = null
    private val TAG = "HelpActivity"
    private val homeFragmentViewModel: HomeFragmentViewModel by viewModels()

    override fun onResume() {
        super.onResume()
        LocaleManager().setLocale(this) //This method update the language when the activity orientation changes
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LocaleManager().setLocale(this) //This method update the language when the activity orientation changes
        setContentView(R.layout.activity_help)
        initLayouts()
        initialzeListeners()

        helpAndSupportViewModel()
    }

    private fun helpAndSupportViewModel() {
        try {
            homeFragmentViewModel?.let {
                it.helpAndSupport.observe(this,
                    androidx.lifecycle.Observer {
                        try {
                            mylog(TAG, "helpAndSupportViewModel: Response=${Gson().toJson(it)}")

                            if (it == null)
                                return@Observer

                            if (it.isStatus) {
                                toast(this@HelpActivity, it.message)
                                mEdContact!!.setText("")
                                mEdEmail!!.setText("")
                                mEdMessage!!.setText("")
                            } else alertDialog(this@HelpActivity, it.message.toString())

                        } catch (e: Exception) {
                            alertDialog(this, e.localizedMessage)
                        }finally {
                            LoadingDialog.cancelLoading()
                        }

                    })
            }
        } catch (e: java.lang.Exception) {
            mylog(TAG, "helpAndSupportViewModel: Error=" + e.localizedMessage)
        }

    }

    /* initialize listener */
    private fun initialzeListeners() {
        mIvBack!!.setOnClickListener(this)
        mBtnSubmit!!.setOnClickListener(this)
        mIvNotification!!.setOnClickListener(this)
        setTypeFace(mEdContact)
    }

    /* init layout */
    private fun initLayouts() {
        mIvBack = findViewById(R.id.ivBack)
        mEdContact = findViewById(R.id.edContact)
        mEdEmail = findViewById(R.id.edEmail)
        mEdMessage = findViewById(R.id.edMessage)
        mIvNotification = findViewById(R.id.ivNotification)
        mBtnSubmit = findViewById(R.id.btnSubmit)
        mBtnSubmit!!.stateListAnimator = null
        SetNotificationImage(mIvNotification)
    }

    /* onClick listener */
    override fun onClick(v: View) {
        if (v === mIvBack) onBackPressedDispatcher.onBackPressed() else if (v === mBtnSubmit) {
            if (validate()) {
                help
            }
        } else if (v === mIvNotification) startActivity(AppNotificationsActivity::class.java)
    }

    private val help: Unit
        private get() {
            try {
                if (isNetworkAvailable(this)) {
                    homeFragmentViewModel?.let {
                        LoadingDialog.showLoadingDialog(this, getString(R.string.pls_wait_loading))
                        it.helpSupport(
                            this,
                            getPreference(this, Constants.TOKEN),
                            mEdContact!!.text.toString(),
                            mEdEmail!!.text.toString(),
                            mEdMessage!!.text.toString()
                        )
                    }
                } else toast(this, getString(R.string.txt_Network))
            } catch (e: Exception) {
                mylog(TAG, "helpSupport: Error=" + e.localizedMessage)
                LoadingDialog.cancelLoading()
            }
        }

    /* validations */
    private fun validate(): Boolean {
        var flag = true
        if (mEdContact!!.text.toString() == "" && mEdContact!!.text.toString().length != 10) {
            flag = false
            toast(this,getString(R.string.msg_valid_mobile_number))
        } else if (mEdEmail!!.text.toString() == "" && !isEmailValid(mEdEmail!!.text.toString())) {
            flag = false
            toast(this,getString(R.string.msg_email_valid))
        } else if (mEdMessage!!.text.toString() == "") {
            flag = false
            toast(this,getString(R.string.msg_description))
        }
        return flag
    }

    companion object {
        fun isEmailValid(email: String?): Boolean {
            val expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$"
            val pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE)
            val matcher = pattern.matcher(email)
            return matcher.matches()
        }
    }
}