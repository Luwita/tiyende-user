package com.shuttleclone.userapp.ui.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.Html
import android.text.InputType
import android.text.TextUtils
import android.text.TextWatcher
import android.text.method.LinkMovementMethod
import android.util.Log
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import com.google.android.gms.auth.api.identity.GetPhoneNumberHintIntentRequest
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.messaging.FirebaseMessaging
import com.google.gson.Gson
import com.hbb20.CountryCodePicker
import com.shuttleclone.userapp.BaseActivity
import com.shuttleclone.userapp.R
import com.shuttleclone.userapp.ViewModel.HomeFragmentViewModel
import com.shuttleclone.userapp.utils.*
import com.shuttleclone.userapp.utils.Constants.ByPassLogin
import com.shuttleclone.userapp.utils.Constants.COUNTRY_CODE
import org.json.JSONObject
import java.util.concurrent.TimeUnit


class SignInActivity : BaseActivity(), View.OnClickListener {
    /*variable declaration*/
    private var mBtnContinue: Button? = null
    private var mEdMobileNumber: EditText? = null
    private var mIvFacebook: ImageView? = null
    private var mIvGoogle: ImageView? = null
    private var mTvCountyCode: TextView? = null
    private var mTvTermPolicy: TextView? = null
    private val TAG = "SignInActivity"
    private var mCcp: CountryCodePicker? = null
    private var countryDetails: String = ""
    private var verificationIntent: Intent? = null
    private val homeFragmentViewModel: HomeFragmentViewModel by viewModels()

    // create instance of firebase auth
    lateinit var auth: FirebaseAuth


    private lateinit var callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks

    private val phoneNumberHintIntentResultLauncher: ActivityResultLauncher<IntentSenderRequest> =
        registerForActivityResult(
            ActivityResultContracts.StartIntentSenderForResult()
        ) { result ->
            try {
                if (result.data == null)
                    result

                val phoneNumber =
                    Identity.getSignInClient(this).getPhoneNumberFromIntent(result.data)
                val phone = phoneNumber.removePrefix(mCcp!!.selectedCountryCodeWithPlus)
                mEdMobileNumber!!.setText(phone)
            } catch (e: Exception) {
                mylog(TAG, result.data?.dataString.toString())

            }
        }


    override fun onResume() {
        super.onResume()
        LocaleManager().setLocale(this) //This method update the language when the activity orientation changes
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        LocaleManager().setLocale(this) //This method update the language when the activity orientation changes
        setContentView(R.layout.activity_sign_in)
        initLayouts()
        initializeListeners()
        fCMToken
        auth = FirebaseAuth.getInstance()
        initializeFirebaseAuth()
        // set this to remove reCaptcha web
//        auth.firebaseAuthSettings.setAppVerificationDisabledForTesting(true);

        savePreference(this, Constants.DEVICE_ID, getDeviceId(applicationContext))

//        initGoogleClient()

        viewModelUserRegisterResponse()
        viewModelUserPhoneNoResponse()
    }

    private fun viewModelUserPhoneNoResponse() {
        try {
            homeFragmentViewModel?.let {
                it.validatePhone.observe(this,
                    androidx.lifecycle.Observer {
                        LoadingDialog.cancelLoading()
                        try {
                            mylog(TAG, "viewModelUserPhoneNoResponse: Response=${Gson().toJson(it)}")

                            if (it == null)
                                return@Observer

                            if (it.isStatus) mBtnContinue!!.isEnabled=true
                            else alertDialog(this@SignInActivity, it.message)

                        } catch (e: java.lang.Exception) {
                            alertDialog(this, e.localizedMessage)
                            LoadingDialog.cancelLoading()
                        }

                    })
            }
        } catch (e: java.lang.Exception) {
            mylog(TAG, "viewModelUserPhoneNoResponse: Error=" + e.localizedMessage)
            LoadingDialog.cancelLoading()
        }

    }

    private fun initGoogleClient() {
        val request: GetPhoneNumberHintIntentRequest =
            GetPhoneNumberHintIntentRequest.builder().build()

        Identity.getSignInClient(this)
            .getPhoneNumberHintIntent(request)
            .addOnSuccessListener {
                phoneNumberHintIntentResultLauncher.launch(
                    IntentSenderRequest.Builder(it.intentSender).build()
                )
            }
            .addOnFailureListener {
                mylog(TAG, it.message.toString())
            }

    }

    private fun initializeFirebaseAuth() {
        // Callback function for Phone Auth
        callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            // This method is called when the verification is completed
            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                mylog("GFG", "onVerificationCompleted Success")
                mylog("GFG", "onVerificationCompleted credential=${Gson().toJson(credential)}")

                LoadingDialog.cancelLoading()
                phoneAuthCredential = credential
                if (verificationIntent != null) {
                    verificationIntent!!.putExtra("fbVerificationId", "")
                    verificationIntent!!.putExtra("verification_complete", true)
                    startActivity(verificationIntent)
                }
            }

            // Called when verification is failed add log statement to see the exception
            override fun onVerificationFailed(e: FirebaseException) {
                mylog("GFG", "onVerificationFailed  =${e.localizedMessage}")
                LoadingDialog.cancelLoading()
                alertDialog(this@SignInActivity,"${e.localizedMessage}")
                mBtnContinue!!.isEnabled=true
            }

            override fun onCodeSent(
                verificationId: String,
                token: PhoneAuthProvider.ForceResendingToken
            ) {

                resendToken = token
                LoadingDialog.cancelLoading()

                mylog(TAG,"resendToken=$resendToken")

                if (verificationIntent != null) {
                    verificationIntent!!.putExtra("fbVerificationId", verificationId)
                    verificationIntent!!.putExtra("verification_complete", false)
                    startActivity(verificationIntent)
                }
            }
        }
    }

    private fun sendVerificationCode(number: String) {
        mylog("GFG", "number=$number")
        val options = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber(number)
            .setTimeout(0L, TimeUnit.SECONDS)
            .setActivity(this)
            .setCallbacks(callbacks)
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }

    private fun viewModelUserRegisterResponse() {
        try {
            homeFragmentViewModel?.let {
                it.registerUser.observe(this,
                    androidx.lifecycle.Observer {
//                        LoadingDialog.cancelLoading()
                        try {
                            mylog(TAG, "viewModelUserRegisterResponse: Response=${Gson().toJson(it)}")

                            if (it == null){
                                LoadingDialog.cancelLoading()
                                return@Observer
                            }


                            if (it.isStatus) {
                                mylog(TAG, "onSuccess: OTP=" + it.otp)
                                savePreference(
                                    this@SignInActivity,
                                    Constants.TOKEN,
                                    "Bearer " + it.token
                                )
                                savePreference(
                                    this@SignInActivity,
                                    Constants.csrfTOKEN,
                                    it.csrfToken
                                )

                                verificationIntent =
                                    Intent(this@SignInActivity, VerificationActivity::class.java)
                                verificationIntent!!.putExtra("otp", it.otp)
                                verificationIntent!!.putExtra(
                                    "country_code",
                                    mCcp!!.selectedCountryCodeWithPlus
                                )
                                verificationIntent!!.putExtra("phone", mCcp!!.selectedCountryCodeWithPlus + "${mEdMobileNumber!!.text}")
                                verificationIntent!!.putExtra("country_details", countryDetails)

                                sendVerificationCode(mCcp!!.selectedCountryCodeWithPlus + "${mEdMobileNumber!!.text}")

                            } else{
                                mBtnContinue!!.isEnabled=true
                                LoadingDialog.cancelLoading()
                                alertDialog(this@SignInActivity, it)
                            }


                        } catch (e: java.lang.Exception) {
                            alertDialog(this, e.localizedMessage)
                            LoadingDialog.cancelLoading()
                            mBtnContinue!!.isEnabled=true
                        }

                    })
            }
        } catch (e: java.lang.Exception) {
            mylog(TAG, "viewModelUserRegisterResponse: Error=" + e.localizedMessage)
            LoadingDialog.cancelLoading()
            mBtnContinue!!.isEnabled=true
        }

    }

    // Get new FCM registration token
    private val fCMToken: Unit get() {
            FirebaseMessaging.getInstance().token
                .addOnCompleteListener(OnCompleteListener { task ->
                    try {
                        if (!task.isSuccessful) {
                            mylog(TAG, "Fetching FCM registration token failed="+task.exception)
                            return@OnCompleteListener
                        }

                        // Get new FCM registration token
                        val token = task.result
                        savePreference(this@SignInActivity, Constants.deviceToken, token)

                        // Log and toast
                        mylog(TAG, "TOKEN=$token")
                    } catch (e: Exception) {
                        mylog(TAG, "Error=: ${e.localizedMessage}")
                    }
                })
        }

    /* init layout */
    private fun initLayouts() {
        mEdMobileNumber = findViewById(R.id.edMobileNumber)
        mBtnContinue = findViewById(R.id.btnContinue)
        mIvFacebook = findViewById(R.id.ivFacebook)
        mIvGoogle = findViewById(R.id.ivGoogle)
        mCcp = findViewById(R.id.ccp)
        mTvCountyCode = findViewById(R.id.tvCounty_Code)

        mTvTermPolicy = findViewById(R.id.tvTermsPolicy)

        mBtnContinue!!.isEnabled=false

        //Comment those lines before giving the apk for testing
//        mTvCountyCode!!.visibility=View.GONE
//        mCcp!!.visibility=View.VISIBLE

        //Un-Comment this line before giving the apk for testing
        mCcp!!.setCountryForPhoneCode(COUNTRY_CODE)

        if (ByPassLogin) {
            mEdMobileNumber!!.inputType = InputType.TYPE_CLASS_TEXT
        }

        mEdMobileNumber!!.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                mBtnContinue!!.isEnabled = mEdMobileNumber!!.getText().toString().length >= 10

                /*if (mEdMobileNumber!!.getText().toString().length >= 10) {
                    mBtnContinue!!.isEnabled=true
                    LoadingDialog.showLoadingDialog(this@SignInActivity, getString(R.string.pls_wait_loading))
                    homeFragmentViewModel!!.validatePhoneNO(
                        this@SignInActivity,
                        mEdMobileNumber!!.getText().toString(),
                        mCcp!!.selectedCountryCode
                    )
                } else {
                    mBtnContinue!!.isEnabled=false
                }*/
            }

            override fun afterTextChanged(editable: Editable) {}
        })

    }

    /* initialize listener */
    @SuppressLint("ClickableViewAccessibility")
    private fun initializeListeners() {
        mBtnContinue!!.setOnClickListener(this)
        mIvFacebook!!.setOnClickListener(this)
        mIvGoogle!!.setOnClickListener(this)
        mBtnContinue!!.stateListAnimator = null


        mTvTermPolicy!!.setText(Html.fromHtml(getString(R.string.txt_term_and_policy)))
        mTvTermPolicy!!.setMovementMethod(LinkMovementMethod.getInstance())

    }

    /* validation */
    private fun validate(): Boolean {
        var flag = true
        if (TextUtils.isEmpty(mEdMobileNumber!!.text)) {
            flag = false
            toast(this,getString(R.string.msg_mobile_number))
        }
        return flag
    }

    /* onClick listener */
    override fun onClick(v: View) {
        if (v === mBtnContinue) {
            vibratePhone(this)
            if (!isNetworkAvailable(baseContext)) {
                toast(this@SignInActivity, getString(R.string.txt_Network))
                return
            }

            if (!validate())
                return

            val countryOjb = JSONObject()
            countryOjb.put("country_name", mCcp!!.selectedCountryName)
            countryOjb.put("country_with_plus", mCcp!!.selectedCountryCodeWithPlus)
            countryOjb.put("country_name_code", mCcp!!.selectedCountryNameCode)
            countryOjb.put("country_code", mCcp!!.selectedCountryCode)

            Log.i(TAG, "onClick: countryOjb=$countryOjb")

            countryDetails = countryOjb.toString()


            if (!ByPassLogin) {
                homeFragmentViewModel?.let {
                    mBtnContinue!!.isEnabled=false
                    LoadingDialog.showLoadingDialog(this, getString(R.string.pls_wait_loading))
                    it.registerUser(
                        this,
                        mCcp!!.selectedCountryCode,
                        mEdMobileNumber!!.text.toString().trim { it <= ' ' },
                        getPreference(this, Constants.DEVICE_ID),
                        countryDetails
                    )
                }
            } else {
                savePreference(
                    this@SignInActivity,
                    Constants.TOKEN,
                    "Bearer " + mEdMobileNumber!!.text.toString()
                )
                savePreference(
                    this@SignInActivity,
                    Constants.csrfTOKEN, mEdMobileNumber!!.text.toString()
                )
                val intent = Intent(this@SignInActivity, VerificationActivity::class.java)
                startActivity(intent)
            }
        }
    }

    companion object{
        lateinit var resendToken: PhoneAuthProvider.ForceResendingToken
        lateinit var phoneAuthCredential: PhoneAuthCredential
    }
}