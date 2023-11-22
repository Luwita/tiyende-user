package com.shuttleclone.userapp.ui.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.os.CountDownTimer
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.activity.viewModels
import com.google.android.gms.auth.api.phone.SmsRetriever
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.auth.PhoneAuthProvider.ForceResendingToken
import com.google.gson.Gson
import com.shuttleclone.userapp.BaseActivity
import com.shuttleclone.userapp.R
import com.shuttleclone.userapp.ViewModel.HomeFragmentViewModel
import com.shuttleclone.userapp.model.UserDetail
import com.shuttleclone.userapp.ui.activity.SignInActivity.Companion.phoneAuthCredential
import com.shuttleclone.userapp.ui.activity.SignInActivity.Companion.resendToken
import com.shuttleclone.userapp.utils.*
import com.shuttleclone.userapp.utils.Constants.ByPassLogin
import com.shuttleclone.userapp.utils.broadCastReceivers.SmsBroadcastReceiver
import java.util.concurrent.TimeUnit
import java.util.regex.Matcher
import java.util.regex.Pattern


class VerificationActivity : BaseActivity(), View.OnClickListener {
    /*variable declaration*/
    private var mEdDigit1: EditText? = null
    private var mEdDigit2: EditText? = null
    private var mEdDigit3: EditText? = null
    private var mEdDigit4: EditText? = null
    private var mEdDigit5: EditText? = null
    private var mEdDigit6: EditText? = null
    private var mLlVerify: LinearLayout? = null
    private var mTvResend: TextView? = null
    private var mTvTimer: TextView? = null
    private var mIvBack: ImageView? = null
    private lateinit var mEds: Array<EditText?>
    private var otp = ""
    private var phone = ""
    private var countryCode = "91"
    private var countryDetails = ""
    private var isMobileVerified = false
    private var userDetail: UserDetail? = null
    private val TAG = "VerificationActivity"
    var smsBroadcastReceiver: SmsBroadcastReceiver? = null
    private val homeFragmentViewModel: HomeFragmentViewModel by viewModels()


    // get reference of the firebase auth
    lateinit var auth: FirebaseAuth

    // we will use this to match the sent otp from firebase
    lateinit var fbVerificationId: String
    private var isVerificationComplete = false

    //    private var resendToken: ForceResendingToken? = null
    private lateinit var callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks

    override fun onResume() {
        super.onResume()
        LocaleManager().setLocale(this) //This method update the language when the activity orientation changes
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LocaleManager().setLocale(this) //This method update the language when the activity orientation changes
        setContentView(R.layout.activity_verification)

        auth = FirebaseAuth.getInstance()

        initLayouts()
        initializeListeners()
        val intent = intent
        if (intent != null) {
            if (!ByPassLogin) {
                otp = "${intent.getStringExtra("otp")}"
                phone = "${intent.getStringExtra("phone")}"
                countryCode = intent.getStringExtra("country_code").toString()
                countryDetails = intent.getStringExtra("country_details").toString()
            }

            // get fbVerificationId from the intent
            fbVerificationId = intent.getStringExtra("fbVerificationId").toString()
            isVerificationComplete = intent.getBooleanExtra("verification_complete", false)

            initializeFirebaseAuth()

            if (isVerificationComplete && (null != phoneAuthCredential))
                verifyWithFbOTP("123456", phoneAuthCredential)

        }
        startSmsUserConsent()

        viewModelUserVerificationResponse()
    }

    private fun startSmsUserConsent() {
        val client = SmsRetriever.getClient(this)
        client.startSmsUserConsent(null).addOnSuccessListener { }.addOnFailureListener { }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQ_USER_CONSENT) {
            if (resultCode == RESULT_OK && data != null) {
                val message = data.getStringExtra(SmsRetriever.EXTRA_SMS_MESSAGE)
                getOtpFromMessage(message.toString())
            }
        }
    }

    private fun getOtpFromMessage(message: String) {
        mEdDigit1!!.setText("")
        mEdDigit2!!.setText("")
        mEdDigit3!!.setText("")
        mEdDigit4!!.setText("")
        mEdDigit5!!.setText("")
        mEdDigit6!!.setText("")

        // This will match any 4 digit number in the message
        val pattern: Pattern = Pattern.compile("(|^)\\d{6}")
        val matcher: Matcher = pattern.matcher(message)
        if (matcher.find()) {

            try {
                mEdDigit1!!.setText(matcher.group(0)[0] + "")
                mEdDigit2!!.setText(matcher.group(0)[1] + "")
                mEdDigit3!!.setText(matcher.group(0)[2] + "")
                mEdDigit4!!.setText(matcher.group(0)[3] + "")
                mEdDigit5!!.setText(matcher.group(0)[4] + "")
                mEdDigit6!!.setText(matcher.group(0)[5] + "")

            } catch (e: Exception) {
                mylog(TAG, "getOtpFromMessage: Error=" + e.localizedMessage)
            }


        }
    }

    /* init layout */
    private fun initLayouts() {
        mEdDigit1 = findViewById(R.id.edDigit1)
        mEdDigit2 = findViewById(R.id.edDigit2)
        mEdDigit3 = findViewById(R.id.edDigit3)
        mEdDigit4 = findViewById(R.id.edDigit4)
        mEdDigit5 = findViewById(R.id.edDigit5)
        mEdDigit6 = findViewById(R.id.edDigit6)

        mLlVerify = findViewById(R.id.llVerify)
        mTvResend = findViewById(R.id.tvResend)
        mTvTimer = findViewById(R.id.tvTimer)
        mEds = arrayOf(mEdDigit1, mEdDigit2, mEdDigit3, mEdDigit4, mEdDigit5, mEdDigit6)
        mIvBack = findViewById(R.id.ivBack)
    }

    /* initialize listener */
    private fun initializeListeners() {
        mIvBack!!.setOnClickListener(this)
        mEdDigit1!!.setOnKeyListener(PinOnKeyListener(0))
        mEdDigit2!!.setOnKeyListener(PinOnKeyListener(1))
        mEdDigit3!!.setOnKeyListener(PinOnKeyListener(2))
        mEdDigit4!!.setOnKeyListener(PinOnKeyListener(3))
        mEdDigit5!!.setOnKeyListener(PinOnKeyListener(4))
        mEdDigit6!!.setOnKeyListener(PinOnKeyListener(5))

        mEdDigit1!!.addTextChangedListener(CodeTextWatcher(0))
        mEdDigit2!!.addTextChangedListener(CodeTextWatcher(1))
        mEdDigit3!!.addTextChangedListener(CodeTextWatcher(2))
        mEdDigit4!!.addTextChangedListener(CodeTextWatcher(3))
        mEdDigit5!!.addTextChangedListener(CodeTextWatcher(4))
        mEdDigit6!!.addTextChangedListener(CodeTextWatcher(5))
        mLlVerify!!.setOnClickListener(this)

        startTimer()

        mEdDigit1!!.requestFocus()


        /*mEdDigit6!!.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                hideKeyboard()
                if (validate()) {
                    verifyOTP(edtOTP.toInt())
                }
                true
            } else false
        }*/

        mTvResend!!.setOnClickListener {
            // Firebase Resend OTP
            if (resendToken == null) {
                Toast.makeText(this, "resendToken is null", Toast.LENGTH_SHORT).show()
                mTvResend!!.visibility=View.GONE
                return@setOnClickListener
            }

            startTimer()
            resendVerificationCode(phone, resendToken!!)
        }
    }

    fun hideKeyboard() {
        val view = this.currentFocus
        if (view != null) {
            val inputManager = this
                .getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            inputManager.hideSoftInputFromWindow(
                view.windowToken,
                InputMethodManager.HIDE_NOT_ALWAYS
            )
        }
    }

    /*private fun reSendOTP() {
        try {
            if (isNetworkAvailable(this)) {
                mylog(TAG, "verifyOTP: OTP=$otp")
                LoadingDialog.showLoadingDialog(this, getString(R.string.pls_wait_loading))
                RetrofitClient.getClient().resendOtp(
                    getPreference(this, Constants.TOKEN),
                    userDetail!!.phone.toString() + "",
                    countryCode,
                    countryDetails
                ).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(object : DisposableSingleObserver<DefaultResponse?>() {
                        override fun onSuccess(defaultResponse: DefaultResponse) {
                            mylog(TAG, "onSuccess: MSG=" + defaultResponse.message)
                            mylog(TAG, "onSuccess: Status=" + defaultResponse.isStatus)
                            LoadingDialog.cancelLoading()
                            if (defaultResponse.isStatus) {
                                //  toast(this@VerificationActivity, "Your Otp is " + defaultResponse.otp)
                                toast(
                                    this@VerificationActivity,
                                    "Your OTP is ${defaultResponse.otp}"
                                )
                                startTimer()
                            } else alertDialog(this@VerificationActivity, defaultResponse.message)
                        }

                        override fun onError(e: Throwable) {
                            mylog(TAG, "onError: verifyOTP Error=" + e.localizedMessage)
                            toast(applicationContext, e.message)
                            LoadingDialog.cancelLoading()
                        }
                    })
            } else toast(this, getString(R.string.txt_Network))
        } catch (e: Exception) {
            mylog(TAG, "reSendOTP: Error=" + e.localizedMessage)
            LoadingDialog.cancelLoading()
        }
    }*/

    private fun startTimer() {
        try {
            hideView(mTvResend)
            showView(mTvTimer)

            object : CountDownTimer(60000, 1000) {
                // adjust the milli seconds here
                @SuppressLint("DefaultLocale")
                override fun onTick(millisUntilFinished: Long) {
                    mTvTimer!!.text = String.format(
                        "%d ${getString(R.string.second_left)}",
                        TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) -
                                TimeUnit.MINUTES.toSeconds(
                                    TimeUnit.MILLISECONDS.toMinutes(
                                        millisUntilFinished
                                    )
                                )
                    )
                }

                override fun onFinish() {
                    hideView(mTvTimer)
                    showView(mTvResend)
                }
            }.start()
        } catch (e: Exception) {
            mylog(TAG, "startTimer: Error=" + e.localizedMessage)
        }
    }

    /* onClick listener */
    override fun onClick(v: View) {
        if (v === mIvBack) {
            onBackPressedDispatcher.onBackPressed()
        }
        if (v === mLlVerify) {
            if (validate()) {
                if (!ByPassLogin) {
                    if (fbVerificationId != "") {
                        val credential: PhoneAuthCredential =
                            PhoneAuthProvider.getCredential(fbVerificationId, edtOTP)
                        verifyWithFbOTP(edtOTP, credential)
                    } else {
                        toast(this, getString(R.string.txt_Something_wrong))
                        finish()
                    }
                } else {
                    savePreference(this@VerificationActivity, Constants.IsUserRegistered, true)
                    startActivity(DashboardActivity::class.java)
                    finishAffinity()
                }
            }
        }
    }


    private fun verifyOTP(OTP: Int) {
        try {
            if (isNetworkAvailable(this)) {
                mylog(TAG, "verifyOTP: OTP=$otp")

                homeFragmentViewModel?.let {
                    LoadingDialog.showLoadingDialog(this, getString(R.string.pls_wait_loading))
                    it.verifyUser(this,
                        getPreference(this, Constants.TOKEN),
                        getPreference(this, Constants.deviceToken),
                        Constants.DEVICE_TYPE,
                        OTP, isMobileVerified,
                        getDeviceDetails()
                    )
                }

              /*  LoadingDialog.showLoadingDialog(this, getString(R.string.pls_wait_loading))
                RetrofitClient.getClient().verifyUser(
                    getPreference(this, Constants.TOKEN),
                    getPreference(this, Constants.deviceToken),
                    Constants.DEVICE_TYPE,
                    OTP, isMobileVerified,
                    getDeviceDetails()
                ).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(object :
                        DisposableSingleObserver<UserVerificationResponseModel?>() {
                        override fun onSuccess(userVerificationResponseModel: UserVerificationResponseModel) {
                            mylog(TAG, "onSuccess: MSG=" + userVerificationResponseModel.message)
                            mylog(
                                TAG,
                                "onSuccess: Status=" + userVerificationResponseModel.isStatus
                            )
                            LoadingDialog.cancelLoading()
                            if (userVerificationResponseModel.isStatus) {
                                toast(
                                    this@VerificationActivity,
                                    userVerificationResponseModel.message
                                )
                                userDetail = userVerificationResponseModel.userDetail
                                checkUserDetails()
                            } else alertDialog(
                                this@VerificationActivity,
                                userVerificationResponseModel.message.toString()
                            )
                        }

                        override fun onError(e: Throwable) {
                            mylog(TAG, "onError: verifyOTP Error=" + e.localizedMessage)
                            toast(applicationContext, e.localizedMessage)
                            LoadingDialog.cancelLoading()
                        }
                    })*/
            } else toast(this, getString(R.string.txt_Network))
        } catch (e: Exception) {
            mylog(TAG, "verifyOTP: Error=" + e.localizedMessage)
            LoadingDialog.cancelLoading()
        }
    }


    private fun initializeFirebaseAuth() {
        callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                LoadingDialog.cancelLoading()
                mylog(TAG, "onVerificationCompleted Success")
                mylog(TAG, "onVerificationCompleted credential=${Gson().toJson(credential)}")
                phoneAuthCredential = credential
                verifyWithFbOTP("123456", phoneAuthCredential)

            }

            override fun onVerificationFailed(e: FirebaseException) {
                mylog(TAG, "onVerificationFailed  $e")
                LoadingDialog.cancelLoading()
                alertDialog(this@VerificationActivity,"${e.localizedMessage}")
            }

            override fun onCodeSent(verificationId: String, token: ForceResendingToken) {
                mylog(TAG, "onCodeSent: $verificationId")
                fbVerificationId = verificationId
                resendToken = token
                LoadingDialog.cancelLoading()
            }
        }
    }

    private fun viewModelUserVerificationResponse() {
        try {
            homeFragmentViewModel?.let {
                it.verifyUser.observe(this,
                    androidx.lifecycle.Observer {
                        LoadingDialog.cancelLoading()
                        try {
                            mylog(TAG, "viewModelUserRegisterResponse: Response=${Gson().toJson(it)}")

                            if (it == null)
                                return@Observer


                            LoadingDialog.cancelLoading()
                            if (it.isStatus) {
                                toast(
                                    this@VerificationActivity,
                                    it.message
                                )
                                userDetail = it.userDetail
                                checkUserDetails()
                            } else alertDialog(
                                this@VerificationActivity,
                                it.message.toString()
                            )


                        } catch (e: Exception) {
                            alertDialog(this, e.localizedMessage)
                            LoadingDialog.cancelLoading()
                        }

                    })
            }
        } catch (e: java.lang.Exception) {
            mylog(TAG, "viewModelUserVerificationResponse: Error=" + e.localizedMessage)
            LoadingDialog.cancelLoading()
        }

    }


    private fun verifyWithFbOTP(otp: String, credential: PhoneAuthCredential) {
        LoadingDialog.showLoadingDialog(this, getString(R.string.pls_wait_loading))
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    isMobileVerified = true
                    LoadingDialog.cancelLoading()
                    verifyOTP(otp.toInt())
                } else {
                    isMobileVerified = false
                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
                        // The verification code entered was invalid
                        Toast.makeText(this, getString(R.string.invalid_otp), Toast.LENGTH_SHORT).show()
                        LoadingDialog.cancelLoading()
                    }
                }
            }

    }


    private fun resendVerificationCode(phoneNumber: String, token: ForceResendingToken) {
        LoadingDialog.showLoadingDialog(this@VerificationActivity,getString(R.string.pls_wait_loading))
        val options = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber(phoneNumber)
            .setTimeout(0L, TimeUnit.SECONDS)
            .setActivity(this)
            .setCallbacks(callbacks)
            .setForceResendingToken(token)
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }

    private fun checkUserDetails() {
        try {
            if (userDetail != null) {
                if (userDetail!!.firstname.toString().trim { it <= ' ' } != ""
                    && userDetail!!.lastname != ""
                    && userDetail!!.email.toString().trim { it <= ' ' } != ""
                ) {
                    savePreference(this@VerificationActivity, Constants.IsUserRegistered, true)
                    savePreference(
                        this@VerificationActivity,
                        Constants.PHONE_NO,
                        userDetail!!.phone.toString() + ""
                    )
                    savePreference(
                        this@VerificationActivity,
                        Constants.EMAIL,
                        userDetail!!.email + ""
                    )
                    savePreference(
                        this@VerificationActivity,
                        Constants.GANDER,
                        userDetail!!.gender + ""
                    )
                    savePreference(
                        this@VerificationActivity,
                        Constants.USER_NAME,
                        userDetail!!.firstname + " " + userDetail!!.lastname
                    )

                    startActivity(DashboardActivity::class.java)
                    finishAffinity()
                } else {
                    savePreference(
                        this@VerificationActivity,
                        Constants.IsUserUpdatingFirstTime,
                        true
                    )
                    savePreference(
                        this@VerificationActivity,
                        Constants.PHONE_NO,
                        userDetail!!.phone.toString() + ""
                    )
                    val intent = Intent(this, ProfileSettingsActivity::class.java)
                    intent.putExtra("phone", userDetail!!.phone)
                    startActivity(intent)
                    finishAffinity()
                }
            } else toast(this, "User Details are Null.")
        } catch (e: Exception) {
            mylog(TAG, "checkUserDetails: Errror=" + e.localizedMessage)
        }
    }

    /* Validation */
    private fun validate(): Boolean {
        var flag = true
        if (TextUtils.isEmpty(mEdDigit1!!.text)) {
            flag = false
            toast(this,getString(R.string.msg_code))
        } else if (TextUtils.isEmpty(mEdDigit2!!.text)) {
            flag = false
            toast(this,getString(R.string.msg_code))
        } else if (TextUtils.isEmpty(mEdDigit3!!.text)) {
            flag = false
            toast(this,getString(R.string.msg_code))
        } else if (TextUtils.isEmpty(mEdDigit4!!.text)) {
            flag = false
            toast(this,getString(R.string.msg_code))
        } else if (TextUtils.isEmpty(mEdDigit5!!.text)) {
            flag = false
            toast(this,getString(R.string.msg_code))
        } else if (TextUtils.isEmpty(mEdDigit6!!.text)) {
            flag = false
            toast(this,getString(R.string.msg_code))
        }
        return flag
    }

    private val edtOTP: String
        private get() {
            val otp =
                mEdDigit1!!.text.toString() + mEdDigit2!!.text.toString() + mEdDigit3!!.text.toString() + mEdDigit4!!.text.toString() + mEdDigit5!!.text.toString() + mEdDigit6!!.text.toString()
            mylog(TAG, "getEdtOTP: otp==$otp")
            return otp
        }

    /* back space key handler*/
    inner class PinOnKeyListener internal constructor(private val mCurrentIndex: Int) :
        View.OnKeyListener {
        override fun onKey(v: View, keyCode: Int, event: KeyEvent): Boolean {
            if (keyCode == KeyEvent.KEYCODE_DEL && event.action == KeyEvent.ACTION_DOWN) {
                if (mEds[mCurrentIndex]!!.text.toString().isEmpty() && mCurrentIndex != 0) {
                    mEds[mCurrentIndex - 1]!!.requestFocus()
                }
            }
            return false
        }
    }

    /* implement TextWatcher class*/
    inner class CodeTextWatcher internal constructor(private val mCurrentIndex: Int) : TextWatcher {
        private var mIsFirst = false
        private var mIsLast = false
        private var mNewString = ""
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            mNewString = s.subSequence(start, start + count).toString().trim { it <= ' ' }
        }

        override fun afterTextChanged(s: Editable) {
            var text = mNewString
            if (text.length > 1) text = text[0].toString()
            mEds[mCurrentIndex]!!.removeTextChangedListener(this)
            mEds[mCurrentIndex]!!.setText(text)
            mEds[mCurrentIndex]!!.setSelection(text.length)
            mEds[mCurrentIndex]!!.addTextChangedListener(this)
            if (text.length == 1) moveToNext() else if (text.length == 0) moveToPrevious()
        }

        private fun moveToNext() {
            if (!mIsLast) mEds[mCurrentIndex + 1]!!.requestFocus()
            if (isAllEditTextsFilled && mIsLast) {
                mEds[mCurrentIndex]!!.clearFocus()
                hideKeyboard()
            }
        }

        private fun moveToPrevious() {
            if (!mIsFirst) mEds[mCurrentIndex - 1]!!.requestFocus()
        }

        private val isAllEditTextsFilled: Boolean
            private get() {
                for (editText in mEds) if (editText!!.text.toString()
                        .trim { it <= ' ' }.length == 0
                ) return false
                return true
            }

        private fun hideKeyboard() {
            if (currentFocus != null) {
                val inputMethodManager =
                    getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
            }
        }

        init {
            if (mCurrentIndex == 0) mIsFirst =
                true else if (mCurrentIndex == mEds.size - 1) mIsLast = true
        }
    }


    private fun registerBroadcastReceiver() {
        smsBroadcastReceiver = SmsBroadcastReceiver()
        smsBroadcastReceiver!!.smsBroadcastReceiverListener =
            object : SmsBroadcastReceiver.SmsBroadcastReceiverListener {
                override fun onSuccess(intent: Intent) {
                    startActivityForResult(intent, REQ_USER_CONSENT)
                }

                override fun onFailure() {}
            }
        val intentFilter = IntentFilter(SmsRetriever.SMS_RETRIEVED_ACTION)
        registerReceiver(smsBroadcastReceiver, intentFilter)
    }


    override fun onStart() {
        super.onStart()
        registerBroadcastReceiver()
    }

    override fun onStop() {
        super.onStop()
        unregisterReceiver(smsBroadcastReceiver)
    }

    companion object {
        private const val REQ_USER_CONSENT = 200
    }
}