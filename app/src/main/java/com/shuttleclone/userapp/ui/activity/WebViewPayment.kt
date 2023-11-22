package com.shuttleclone.userapp.ui.activity

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.View
import android.webkit.JavascriptInterface
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.google.gson.Gson
import com.shuttleclone.userapp.BaseActivity
import com.shuttleclone.userapp.R
import com.shuttleclone.userapp.utils.LoadingDialog
import com.shuttleclone.userapp.utils.LocaleManager
import com.shuttleclone.userapp.utils.mylog
import org.json.JSONObject

class WebViewPayment : BaseActivity(), View.OnClickListener {

    private var mIvBack: ImageView? = null
    private var mIvNotification: ImageView? = null
    private var mWebViewPayment: WebView? = null
    private val TAG = "WebViewPayment"
    private var gatewayUrl = "gatewayUrl"
    private var paymentName = "paymentName"
    private var mAlertDialog: Dialog? = null


    override fun onResume() {
        super.onResume()
        LocaleManager().setLocale(this) //This method update the language when the activity orientation changes
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LocaleManager().setLocale(this) //This method update the language when the activity orientation changes
        setContentView(R.layout.activity_webview_payment)

        val extras = intent.extras
        if (extras != null) {
            gatewayUrl = extras.getString("gateway_url").toString()
            paymentName = extras.getString("paymentName").toString()
        }

        initLayouts()
        initializeListeners()
    }

    /* initialize listener */
    private fun initializeListeners() {
        mIvBack!!.setOnClickListener(this)
        mIvNotification!!.setOnClickListener(this)
    }

    /* init layout */
    private fun initLayouts() {
        mIvBack = findViewById(R.id.ivBack)
        mWebViewPayment = findViewById(R.id.webView_payment)
        mIvNotification = findViewById(R.id.ivNotification)
        SetNotificationImage(mIvNotification)
        setUpWebViewClient()
    }

    override fun onBackPressed() {
        alertDialog(this, getString(R.string.you_sure_wanna_cancel))
    }

    fun alertDialog(mContext: Context, alert: Any) {
        try {
            if (mAlertDialog == null)
                mAlertDialog = Dialog(mContext)

            mAlertDialog!!.setContentView(R.layout.alert_dailog_layout)
            mAlertDialog!!.setCancelable(true)
            mAlertDialog!!.window?.setBackgroundDrawable(ColorDrawable(0))

            mAlertDialog!!.findViewById<TextView>(R.id.tvMsg).text = Gson().toJson(alert)
            val btnOkay = mAlertDialog!!.findViewById<Button>(R.id.btnOkay)
            val btnNo = mAlertDialog!!.findViewById<View>(R.id.btnNo)

            btnNo.visibility = View.VISIBLE
            btnOkay.text = getString(R.string.text_yes)
            btnOkay.setOnClickListener {
                if (mAlertDialog!!.isShowing) mAlertDialog!!.dismiss()
                finish()
            }
            btnNo.setOnClickListener {
                if (mAlertDialog!!.isShowing) mAlertDialog!!.dismiss()
            }
            mAlertDialog!!.setCancelable(false)
            mAlertDialog!!.setCanceledOnTouchOutside(false)

            if (!mAlertDialog!!.isShowing)
                mAlertDialog!!.show()

        } catch (e: java.lang.Exception) {
            mylog(TAG, "alertDialog: Error=${e.localizedMessage}")
        }
    }

    private fun setUpWebViewClient() {
        val webSettings: WebSettings = mWebViewPayment!!.settings
        webSettings.javaScriptEnabled = true
        webSettings.loadWithOverviewMode = true
        webSettings.useWideViewPort = true

        mWebViewPayment!!.addJavascriptInterface(WebAppInterface(this@WebViewPayment), "android")
        mWebViewPayment!!.loadUrl(gatewayUrl)

        mylog("OUTPUT IS ", "url=$gatewayUrl")

        try {

            mWebViewPayment!!.webViewClient = object : WebViewClient() {
                override fun onPageStarted(view: WebView, url: String, favicon: Bitmap?) {
                    super.onPageStarted(view, url, favicon)
                    LoadingDialog.showLoadingDialog(
                        this@WebViewPayment,
                        getString(R.string.pls_wait_payment_in_progress)
                    )
                    Log.i("PAYMENT_URL", "onPageStarted: url=$url")
                }

                override fun onPageFinished(view: WebView, url: String) {
                    if (paymentName.equals("Paymob")) {
                        if (url.contains("acceptance/iframes") || url.contains("payments/verify"))
                            LoadingDialog.cancelLoading()
                    }else LoadingDialog.cancelLoading()

                    Log.i("PAYMENT_URL", "onPageFinished: url=$url")
//                    if (url.contentEquals("payments/verify"))

                    mWebViewPayment!!.loadUrl("javascript:android.paymentResponse(document.getElementById('data').innerHTML);")
                }

                override fun onReceivedError(
                    view: WebView?,
                    request: WebResourceRequest?,
                    error: WebResourceError?
                ) {
                    super.onReceivedError(view, request, error)
                    Log.i("PAYMENT_URL", "onReceivedError:")
                    LoadingDialog.cancelLoading()
//                    alertDialog(this@WebViewPayment,error!!)
                }
            }


        } catch (e: Exception) {
            Log.i(TAG, "setUpWebViewClient: Error=${e.localizedMessage}")
            alertDialog(this, e.localizedMessage)
        }
    }


    /* onClick listener */
    override fun onClick(v: View) {
        if (v === mIvBack) alertDialog(this, getString(R.string.you_sure_wanna_cancel))
        else if (v === mIvNotification) startActivity(AppNotificationsActivity::class.java)
    }

    class WebAppInterface(var mContext: WebViewPayment) {
        @JavascriptInterface
        fun paymentResponse(data: String) {
            mylog("WebViewPayment", "paymentResponse: data=$data")
            val intent = Intent()
            val response: JSONObject
            LoadingDialog.cancelLoading()
            try {
                response = JSONObject(data)
                mylog("WebViewPayment", "response=$response")
//                {"status":true,"success":true,"payment_id":"141973253","message":"payment verified successfully.","verification":"success"}
                if (response != null) {
                    var status = false
                    var success = false
                    var statusMessage: String? = ""
                    if (response.has("status")) status = response.getBoolean("status")
                    if (response.has("success")) success = response.getBoolean("success")
                    if (response.has("message")) statusMessage = response.getString("message")
                    try {

                        if (response.has("status")&&response.has("message")&&response.has("success")){
                            intent.putExtra("status", status)
                            intent.putExtra("success", success)
                            intent.putExtra("message", statusMessage)
                            intent.putExtra("response", response.toString())
                        }else if (response.has("status")&&response.has("message")){
                            intent.putExtra("status", status)
                            intent.putExtra("message", statusMessage)
                            intent.putExtra("response", response.toString())
                        }else {
                            intent.putExtra("status", false)
                            intent.putExtra("message", mContext.getString(R.string.txt_Something_wrong))
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    } finally {
                        intent.putExtra("response", response.toString())
                        mContext.setResult(RESULT_OK, intent)
                        mContext.finish()
                    }
                }
            } catch (e: Exception) {
                mylog("WebViewPayment", "error=" + e.localizedMessage)
                intent.putExtra("status", false)
                intent.putExtra("message", mContext.getString(R.string.txt_Something_wrong))
                mContext.setResult(RESULT_OK, intent)
                mContext.finish()
            }
        }
    }

}