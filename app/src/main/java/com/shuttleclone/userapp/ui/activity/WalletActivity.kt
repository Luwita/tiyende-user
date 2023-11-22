package com.shuttleclone.userapp.ui.activity

import android.app.Activity
import android.app.Dialog
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.*
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import com.shuttleclone.userapp.BaseActivity
import com.shuttleclone.userapp.R
import com.shuttleclone.userapp.utils.*
import com.google.firebase.dynamiclinks.ktx.dynamicLinks
import com.google.firebase.dynamiclinks.ktx.shortLinkAsync
import com.google.firebase.ktx.Firebase
import com.google.gson.Gson
import com.razorpay.PaymentData
import com.shuttleclone.userapp.ViewModel.HomeFragmentViewModel
import com.shuttleclone.userapp.model.CommonData
import com.shuttleclone.userapp.model.PaymentGatewayResponseModel

class WalletActivity : BaseActivity(), View.OnClickListener {
    private val TAG = "WalletActivity"

    /*variable declaration*/
    private var mIvBack: ImageView? = null
    private var mIvFaceBook: ImageView? = null
    private var mIvWhatsapp: ImageView? = null
    private var mIvGoogle: ImageView? = null
    private var mIvTwitter: ImageView? = null
    private var mIvNotification: ImageView? = null
    private var edAmount: EditText? = null
    private var tvWalletBalance: TextView? = null
    private var tvResponse: TextView? = null
    private var mTvWalletHistory: TextView? = null
    private var btnAdd1: Button? = null
    private var btnAdd2: Button? = null
    private var btnAdd3: Button? = null
    private var btnAddMoney: Button? = null
    private var paymentData: PaymentData? = null
    private var commonData: CommonData? = null
    private var paymentName = "Razorpay"
    private val homeFragmentViewModel: HomeFragmentViewModel by viewModels()

    override fun onResume() {
        super.onResume()
        LocaleManager().setLocale(this) //This method update the language when the activity orientation changes
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LocaleManager().setLocale(this) //This method update the language when the activity orientation changes
        setContentView(R.layout.activity_wallet)

        commonData = getCommonDataDetails(this@WalletActivity)
        commonData?.let {
            try {
                paymentName = it.defaultPayment.toString()
            } catch (e: Exception) {
                Log.i(TAG, "onCreate: Commdata fetch=${e.localizedMessage}")
            }
        }

        initLayouts()
        initializeListeners()
        checkWalletBalance()
        viewModelResponseCheckWalletBalance()
        viewModelResponseInitiateWalletBalance()
    }

    private fun viewModelResponseInitiateWalletBalance() {
        try {
            homeFragmentViewModel?.let {
                it.initiateWalletRecharge.observe(this,
                    androidx.lifecycle.Observer {
                        LoadingDialog.cancelLoading()
                        try {
                            mylog(TAG, "addMoney: Response=${Gson().toJson(it)}")

                            if (it == null)
                                return@Observer

                            if (it.isStatus!!) {

                                if (it.link != "") {
                                    val intent = Intent(
                                        this@WalletActivity,
                                        WebViewPayment::class.java
                                    )
                                    intent.putExtra("gateway_url", it.link)
                                    intent.putExtra("paymentName", paymentName)
                                    walletAddPaymentLauncher.launch(intent)
                                } else alertDialog(this@WalletActivity, it)

                            } else {
                                alertDialog(this@WalletActivity, it.message.toString())
                                tvWalletBalance!!.text = "$defaultCurrency 0.0"
                            }

                        } catch (e: Exception) {
                            alertDialog(this, e.localizedMessage)
                        }

                    })
            }
        } catch (e: java.lang.Exception) {
            mylog(TAG, "viewModelResponseInitiateWalletBalance: Error=" + e.localizedMessage)
        }

    }

    private fun viewModelResponseCheckWalletBalance() {
        try {
            homeFragmentViewModel!!.checkWalletBalance.observe(
                this,
                androidx.lifecycle.Observer {
                    LoadingDialog.cancelLoading()
                    try {
                        if (it == null)
                            return@Observer

                        if (it.status!!) {
//                            toast(this, it.message)
                            tvWalletBalance!!.text = defaultCurrency + it.data!!.amount
                        } else {
                            alertDialog(this@WalletActivity, it.message.toString())
                            tvWalletBalance!!.text = "$defaultCurrency 0.0"
                        }
                    } catch (e: Exception) {
                        alertDialog(this, e.localizedMessage)
                    }

                })

        } catch (e: java.lang.Exception) {
            mylog(TAG, "viewModelResponseCheckWalletBalance: Error=" + e.localizedMessage)
        }

    }

    private fun checkWalletBalance() {
        if (isNetworkAvailable(this)) {
            homeFragmentViewModel?.let {
                LoadingDialog.showLoadingDialog(this, getString(R.string.pls_wait_loading))
                it.checkWalletBalance(
                    this,
                    getPreference(this, Constants.TOKEN)
                )
            }
        } else toast(this, getString(R.string.txt_Network))
    }

    /* initialize listener */
    private fun initializeListeners() {
        mIvBack!!.setOnClickListener(this)
        mIvFaceBook!!.setOnClickListener(this)
        mIvWhatsapp!!.setOnClickListener(this)
        mIvGoogle!!.setOnClickListener(this)
        mIvTwitter!!.setOnClickListener(this)
        mIvNotification!!.setOnClickListener(this)
        btnAdd1!!.setOnClickListener(this)
        btnAdd2!!.setOnClickListener(this)
        btnAdd3!!.setOnClickListener(this)
        btnAddMoney!!.setOnClickListener(this)
        mTvWalletHistory!!.setOnClickListener(this)
        SetNotificationImage(mIvNotification)

        tvResponse!!.setOnClickListener {
            val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clip = ClipData.newPlainText("Response", tvResponse!!.text.toString())
            clipboard.setPrimaryClip(clip)

            toast(this, "Response Copied")
        }
    }

    /* init layout */
    private fun initLayouts() {
        mIvBack = findViewById(R.id.ivBack)
        mIvFaceBook = findViewById(R.id.ivFaceBook)
        mIvWhatsapp = findViewById(R.id.ivWhatsapp)
        mIvGoogle = findViewById(R.id.ivGoogle)
        mIvTwitter = findViewById(R.id.ivTwitter)
        mIvNotification = findViewById(R.id.ivNotification)
        edAmount = findViewById(R.id.edAmount)
        tvWalletBalance = findViewById(R.id.tvWalletBalance)
        btnAdd1 = findViewById(R.id.btnAdd1)
        btnAdd2 = findViewById(R.id.btnAdd2)
        btnAdd3 = findViewById(R.id.btnAdd3)
        btnAddMoney = findViewById(R.id.btnAddMoney)
        tvResponse = findViewById(R.id.tvResponse)
        mTvWalletHistory = findViewById(R.id.tvWalletHistory)
        btnAdd1!!.text = "$defaultCurrency" + 100
        btnAdd2!!.text = "$defaultCurrency" + 200
        btnAdd3!!.text = "$defaultCurrency" + 500
        tvWalletBalance!!.text = "$defaultCurrency 0.0"
    }

    /* onClick listener */
    override fun onClick(v: View) {
        if (v === mIvBack) {
            this!!.hideKeyboard(findViewById(R.id.rootView))
            onBackPressedDispatcher.onBackPressed()
        } else if (v === mIvFaceBook) createDynamicLink("Default") else if (v === mIvWhatsapp) createDynamicLink(
            "Whatsapp"
        ) else if (v === mIvGoogle) createDynamicLink("Default") else if (v === mIvTwitter) createDynamicLink(
            "Default"
        )
        else if (v === btnAdd1) edAmount!!.setText("100") else if (v === btnAdd2) edAmount!!.setText(
            "200"
        ) else if (v === btnAdd3) edAmount!!.setText("500")
        else if (v === btnAddMoney) addMoney()
        else if (v === mIvNotification) startActivity(AppNotificationsActivity::class.java)
        else if (v === mTvWalletHistory) startActivity(TransactionHistoryActivity::class.java)
    }

    private fun addMoney() {
        try {
            if (!edAmount!!.text.toString().isEmpty()) {

                val amount = edAmount!!.text.toString().trim().toFloat()
                if (amount >= 50) {
                    if (isNetworkAvailable(this)) {
                        homeFragmentViewModel?.let {
                            LoadingDialog.showLoadingDialog(this, getString(R.string.pls_wait_loading))
                            it.initiateWalletRecharge(
                                this,
                                getPreference(this, Constants.TOKEN),
                                "wallet",
                                paymentName,
                                amount
                            )
                        }
                    } else toast(this, getString(R.string.txt_Network))
                } else {
                    edAmount!!.error =
                        "${getString(R.string.you_can_add_minimum)} $defaultCurrency 50"
                    edAmount!!.requestFocus()
                }
            } else edAmount!!.error = getString(R.string.please_enter_amount)
        } catch (e: Exception) {
            mylog(TAG, "addMoney: Error=" + e.localizedMessage)
            LoadingDialog.cancelLoading()
        }
    }

    private var walletAddPaymentLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                // There are no request codes
                try {

                    val data: Intent? = result.data
                    Log.i(TAG, "payment data response:= ${data?.getStringExtra("response")}")

                    if (null != data && data.hasExtra("response")) {
                        Log.i(TAG, "payment data response:= ${data?.getStringExtra("response")}")

                        val payResponse = Gson().fromJson(
                            data.getStringExtra("response").toString(),
                            PaymentGatewayResponseModel::class.java
                        )
                        edAmount!!.text.clear()
                        checkWalletBalance()

                        successFailureDialog(this, payResponse.success, payResponse.message!!)

                    } else if (null != data && data.hasExtra("status") && data.hasExtra("message")) successFailureDialog(
                        this,
                        false,
                        data.getStringExtra("message").toString()
                    )
                    else successFailureDialog(this, false, getString(R.string.txt_Something_wrong))

                } catch (e: Exception) {
                    successFailureDialog(this, false, getString(R.string.txt_Something_wrong))
                }
            }
        }

    fun successFailureDialog(context: Context?, isSuccess: Boolean?, msg: String) {
        try {

            val dialog = Dialog(context!!)
            if (isSuccess!!) dialog.setContentView(R.layout.dialog_success)
            else dialog.setContentView(R.layout.dialog_failure)
            dialog.setCancelable(true)
            dialog.window?.setBackgroundDrawable(ColorDrawable(0))
            dialog.findViewById<TextView>(R.id.tvMsg).text = msg
            dialog.findViewById<View>(R.id.btnClose).setOnClickListener {
                if (dialog.isShowing) dialog.dismiss()
            }
            dialog.setCancelable(false)
            dialog.setCanceledOnTouchOutside(false)
            dialog.show()

        } catch (e: Exception) {
            mylog(TAG, "successFailureDialog: Error=${e.localizedMessage}")
        }
    }


    private fun createDynamicLink(shareOn: String) {

        vibratePhone(this)

        val sharelink = "https://ferriuser.page.link/?" +
                "link=http://shuttle.theferri.com?referral=" + getPreference(
            this,
            Constants.REFERRAL_CODE
        ) +
                "&apn=" + packageName +
                "&st=" + "User Share & Earn" +
                "&sd=" + "User joins, your earn 20, Rider joins, your earn 20" +
                "&si=" + "http://shuttle.theferri.com/uploads/ferri-shuttle.png"


        Firebase.dynamicLinks.shortLinkAsync {
            longLink = Uri.parse(sharelink)
        }.addOnSuccessListener { result ->
            // Short link created
            val shortLink = result.shortLink
            val flowchartLink = result.previewLink
            mylog(TAG, "shortLink: " + shortLink)

            mylog(TAG, "createDynamicLink: shortLink=$shortLink")
            mylog(TAG, "createDynamicLink: flowchartLink=$flowchartLink")
            shareInviteLink(shortLink.toString(), shareOn)

        }.addOnFailureListener {
            mylog(
                TAG,
                "createDynamicLink: something went wrong in shortLinkTask create error=${it.localizedMessage}"
            )
            shareInviteLink("", shareOn)
        }

        /* FirebaseDynamicLinks.getInstance().createDynamicLink()
                 .setLink(Uri.parse(sharelink))
                 .setDomainUriPrefix("ferriuser.page.link")
                 .buildShortDynamicLink()
                 .addOnCompleteListener(this) { task ->
                     if (task.isSuccessful) {
                         // Short link created
                         val shortLink = task.result.shortLink
                         val flowchartLink = task.result.previewLink
                         mylog(TAG, "createDynamicLink: shortLink=$shortLink")
                         shareInviteLink(shortLink.toString(), shareOn)
                     } else {
                         mylog(TAG, "createDynamicLink: error=${task.exception?.localizedMessage}")
                     }
                 }*/
    }


    fun shareInviteLink(linkUrl: String?, shareOn: String) {
        var shareMessage = ""
        try {

            shareMessage =
                "\n\nHello there, \nI’m loving this app,\nTake a ride with ${getString(R.string.app_name)} today. \n\n" +
                        "(CLICK HERE & INSTALL NOW !)   \n\n" + getInvitationMessage(linkUrl)

            mylog(TAG, "shareInviteLink: shareMessage=$shareMessage")
            val shareIntent = Intent(Intent.ACTION_SEND)
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.app_name))
            shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage)
            if (shareOn.equals("Whatsapp")) shareIntent.setPackage("com.whatsapp")
            val bm = BitmapFactory.decodeResource(resources, R.drawable.ic_app_logo)
            val path = MediaStore.Images.Media.insertImage(contentResolver, bm, "", null)
            val screenshotUri = Uri.parse(path)

            shareIntent.putExtra(Intent.EXTRA_STREAM, screenshotUri)
            shareIntent.type = "image/*"

            startActivity(Intent.createChooser(shareIntent, "Share with"))

        } catch (e: Exception) {
            mylog(TAG, "shareInviteLink: Error=${e.localizedMessage}")
            shareWithNoImage(shareMessage, shareOn)
        }
    }

    private fun shareWithNoImage(shareMessage: String, shareOn: String) {
        try {
            val shareIntent = Intent(Intent.ACTION_SEND)
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.app_name))
            shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage)
            if (shareOn.equals("Whatsapp")) shareIntent.setPackage("com.whatsapp")
            shareIntent.type = "text/plain"
            startActivity(Intent.createChooser(shareIntent, "Share with"))
        } catch (e: Exception) {
            mylog(TAG, "shareWithNoImage: Error=${e.localizedMessage}")
        }
    }

    private fun getInvitationMessage(linkUrl: String?): Any? {

        if (linkUrl != null) {
            val playStoreLink =
                linkUrl + "\n"
            val tst = "Get benefits with this referral code: "
            if (!getPreference(this, Constants.REFERRAL_CODE).equals("")) {
                return playStoreLink + tst + getPreference(this, Constants.REFERRAL_CODE)
            }
            return playStoreLink
        } else {
            val playStoreLink =
                "https://play.google.com/store/apps/details?id=" + packageName + "\n"
            val tst = "Get benefits with this referral code: "
            if (!getPreference(this, Constants.REFERRAL_CODE).equals("")) {
                return playStoreLink + tst + getPreference(this, Constants.REFERRAL_CODE)
            }
            return playStoreLink
        }
    }


}