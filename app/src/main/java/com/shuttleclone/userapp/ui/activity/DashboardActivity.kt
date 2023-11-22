package com.shuttleclone.userapp.ui.activity

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.speech.RecognizerIntent
import android.util.Log
import android.view.View
import android.view.ViewAnimationUtils
import android.view.ViewTreeObserver
import android.view.animation.AccelerateInterpolator
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.viewModels
import com.google.android.gms.tasks.Task
import com.google.android.play.core.review.ReviewInfo
import com.google.android.play.core.review.ReviewManager
import com.google.android.play.core.review.ReviewManagerFactory
import com.google.gson.Gson
import com.shuttleclone.userapp.BaseActivity
import com.shuttleclone.userapp.R
import com.shuttleclone.userapp.ViewModel.HomeFragmentViewModel
import com.shuttleclone.userapp.ui.fragment.FragmentOffers
import com.shuttleclone.userapp.ui.fragment.HomeFragmentNewest
import com.shuttleclone.userapp.ui.fragment.MoreFragment
import com.shuttleclone.userapp.ui.fragment.MyBookingFragment
import com.shuttleclone.userapp.utils.Constants
import com.shuttleclone.userapp.utils.LoadingDialog
import com.shuttleclone.userapp.utils.LocaleManager
import com.shuttleclone.userapp.utils.alertDialog
import com.shuttleclone.userapp.utils.checkAndRequestPermissions
import com.shuttleclone.userapp.utils.getPreference
import com.shuttleclone.userapp.utils.isNetworkAvailable
import com.shuttleclone.userapp.utils.mylog
import com.shuttleclone.userapp.utils.savePreference
import com.shuttleclone.userapp.utils.toast


class DashboardActivity : BaseActivity(), View.OnClickListener {
    /*variable declaration*/
    private var mTvTitle: TextView? = null
    private var mIvNotification: ImageView? = null
    private var mIvHome: ImageView? = null
    private var mIvPackages: ImageView? = null
    private var mIvBooking: ImageView? = null
    private var mIvOther: ImageView? = null
    private var mHomeFragmentNewest=HomeFragmentNewest()
    private var mFragmentOffers=FragmentOffers()
    private var mMyBookingFragment= MyBookingFragment()
    private var mMoreFragment= MoreFragment()
    private var mLlHome: LinearLayout? = null
    private var mLllPackages: LinearLayout? = null
    private var mLlBooking: LinearLayout? = null
    private var mLlMore: LinearLayout? = null
    private var reviewManager: ReviewManager? = null
    private val homeFragmentViewModel: HomeFragmentViewModel by viewModels()


    var rootLayout: View? = null
    val TAG = "DashboardActivity"
    val EXTRA_CIRCULAR_REVEAL_X = "EXTRA_CIRCULAR_REVEAL_X"
    val EXTRA_CIRCULAR_REVEAL_Y = "EXTRA_CIRCULAR_REVEAL_Y"
    private var revealX = 0
    private var revealY = 0

    override fun onResume() {
        super.onResume()
        LocaleManager().setLocale(this) //This method update the language when the activity orientation changes
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LocaleManager().setLocale(this) //This method update the language when the activity orientation changes
        setContentView(R.layout.activity_dashboard)

        initLayouts()
        initializeListeners()
        setSelected(mIvHome)
        loadFragment(mHomeFragmentNewest)

        checkLocationPermission()

        try {
            if (intent != null) {
                val openMyBookings = intent.getStringExtra("openBookingList") + ""
                if (openMyBookings.equals("YES")) {
                    Handler(Looper.getMainLooper()).postDelayed({
                        updateUi()
                        if (!mMyBookingFragment!!.isVisible) {
                            mTvTitle!!.text = getString(R.string.title_my_tickets)
                            loadFragment(mMyBookingFragment)
                        }
                        setSelected(mIvBooking)
                        mIvBooking!!.setImageResource(R.drawable.ic_booking_fill)
                    }, 1000)

                }
            }

        } catch (e: Exception) {
            mylog(TAG, "onCreate: Error=${e.localizedMessage}")
        }

        // explode animation on activity start.
        explodeAnim(savedInstanceState, intent)

        getConfigSettings()
        viewModelConfigSettingsResponse()

        //        showAppRatePopup()
    }

    private fun checkLocationPermission() {
        try {
            Handler(Looper.getMainLooper()).postDelayed({
                if (!checkAndRequestPermissions(this)&&!this.isFinishing) {
                    val intent = Intent(this, PermissionActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent)
                }
            }, 4000)
        }catch (e:Exception){
            Log.i(TAG, "checkLocationPermission: Error=${e.localizedMessage}")}
    }

    private fun getConfigSettings() {
        try {
            if (isNetworkAvailable(this)) {
                homeFragmentViewModel?.let {
                    LoadingDialog.showLoadingDialog(this, getString(R.string.pls_wait_loading))
                    it.getConfigSettings(
                        this,
                        getPreference(this, Constants.TOKEN))
                }
            } else toast(this, getString(R.string.txt_Network))
        } catch (e: Exception) {
            mylog(TAG, "getConfigSettings: Error=" + e.localizedMessage)
        }
    }

    private fun viewModelConfigSettingsResponse() {
        try {
            homeFragmentViewModel?.let {
                it.getConfigSettings.observe(this,
                    androidx.lifecycle.Observer {
                        LoadingDialog.cancelLoading()
                        try {
                            mylog(TAG, "viewModelConfigSettingsResponse: Response=${Gson().toJson(it)}")

                            if (it == null)
                                return@Observer


                            if (it.status!! && it.data != null) {
                                savePreference(
                                    this@DashboardActivity,
                                    Constants.COMMON_DATA,
                                    Gson().toJson(it.data)
                                )
                                savePreference(
                                    this@DashboardActivity,
                                    Constants.DEFAULT_COUNTRY,
                                    it.data.defaultCountry
                                )
                                savePreference(
                                    this@DashboardActivity,
                                    Constants.DEFAULT_CURRENCY,
                                    it.data.defaultCurrency
                                )
                            } else alertDialog(
                                this@DashboardActivity,
                                it.message.toString()
                            )

                        } catch (e: java.lang.Exception) {
                            alertDialog(this, e.localizedMessage)
                        }

                    })
            }
        } catch (e: java.lang.Exception) {
            mylog(TAG, "viewModelConfigSettingsResponse: Error=" + e.localizedMessage)
        }

    }


    private fun showAppRatePopup() {
        try {
            val dialog = Dialog(this)
            dialog.setContentView(R.layout.rate_app_dialog)
            dialog.setCancelable(true)
            dialog.window?.setBackgroundDrawable(ColorDrawable(0))

            dialog.findViewById<View>(R.id.btnYes).setOnClickListener {
                if (dialog.isShowing) dialog.dismiss()
                reviewManager = ReviewManagerFactory.create(this);
                initPlayStoreRating()
            }
            dialog.findViewById<View>(R.id.btnNo).setOnClickListener {
                if (dialog.isShowing) dialog.dismiss()
            }
            dialog.setCancelable(false)
            dialog.setCanceledOnTouchOutside(false)
            dialog.show()

        } catch (e: Exception) {
            mylog(TAG, "showAppRatePopup: Error=${e.localizedMessage}")
        }
    }


    private fun initPlayStoreRating() {
        try {
            val request: Task<ReviewInfo> = reviewManager!!.requestReviewFlow()
            request.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Getting the ReviewInfo object
                    val reviewInfo: ReviewInfo = task.result
                    val flow: Task<Void> = reviewManager!!.launchReviewFlow(this, reviewInfo)
                    flow.addOnCompleteListener { task1 ->

                    }
                }
            }
        } catch (e: Exception) {
            mylog(TAG, "initPlayStoreRating: Error=${e.localizedMessage}")
        }
    }

    /* init layout */
    @SuppressLint("ClickableViewAccessibility")
    fun initLayouts() {
        mTvTitle = findViewById(R.id.tvTitle)
        mIvNotification = findViewById(R.id.ivNotification)
        mLlHome = findViewById(R.id.llHome)
        mLllPackages = findViewById(R.id.llPackage)
        mLlBooking = findViewById(R.id.llBooking)
        mLlMore = findViewById(R.id.llMore)
        mIvHome = findViewById(R.id.ivHome)
        mIvPackages = findViewById(R.id.ivPackages)
        mIvBooking = findViewById(R.id.ivBooking)
        mIvOther = findViewById(R.id.ivMore)
        rootLayout = findViewById(R.id.rlMain)
        mTvTitle!!.setText(getString(R.string.home))

//        mHomeFragmentNewest = HomeFragmentNewest()
//        mFragmentOffers = FragmentOffers()
//        mMyBookingFragment = MyBookingFragment()
//        mMoreFragment = MoreFragment()
    }

    /* initialize listener */
    fun initializeListeners() {
        mIvNotification!!.setOnClickListener(this)
        mLlHome!!.setOnClickListener(this)
        mLllPackages!!.setOnClickListener(this)
        mLlBooking!!.setOnClickListener(this)
        mLlMore!!.setOnClickListener(this)
        SetNotificationImage(mIvNotification)
    }

    /* set selected item in bottom navigation */
    private fun setSelected(mBarImg: ImageView?) {
        mBarImg!!.background = resources.getDrawable(R.drawable.bg_tint_icon)
    }

    /* Update UI */
    private fun updateUi() {
        mIvHome!!.setImageResource(R.drawable.ic_home)
        mIvHome!!.background = null
        mIvPackages!!.setImageResource(R.drawable.ic_package)
        mIvPackages!!.background = null
        mIvBooking!!.setImageResource(R.drawable.ic_booking)
        mIvBooking!!.background = null
        mIvOther!!.setImageResource(R.drawable.ic_fill)
        mIvOther!!.background = null
    }

    /* onBack press */
    override fun onBackPressed() {
        onBackPressedDispatcher.onBackPressed()
    }

    /* onClick listener */
    override fun onClick(v: View) {
        try {
            if (v === mIvNotification) {
                startActivity(AppNotificationsActivity::class.java)
                return
            }
            updateUi()
            when (v.id) {
                R.id.llHome -> {
                    if (!mHomeFragmentNewest!!.isVisible) {
                        mTvTitle!!.text = getString(R.string.home)
                        loadFragment(mHomeFragmentNewest)
                    }
                    setSelected(mIvHome)
                    mIvHome!!.setImageResource(R.drawable.ic_home_fill)
                }

                R.id.llPackage -> {
                    if (!mFragmentOffers!!.isVisible) {
                        mTvTitle!!.text = FragmentOffers.mTitle
                        loadFragment(mFragmentOffers)
                    }
                    setSelected(mIvPackages)
                    mIvPackages!!.setImageResource(R.drawable.ic_package_fill)
                }

                R.id.llBooking -> {
                    if (!mMyBookingFragment!!.isVisible) {
                        mTvTitle!!.text = getString(R.string.title_my_tickets)
                        loadFragment(mMyBookingFragment)
                    }
                    setSelected(mIvBooking)
                    mIvBooking!!.setImageResource(R.drawable.ic_booking_fill)
                }

                R.id.llMore -> {
                    if (!mMoreFragment!!.isVisible) {
                        mTvTitle!!.text = getString(R.string.title_more)
                        loadFragment(mMoreFragment)
                    }
                    setSelected(mIvOther)
                    mIvOther!!.setImageResource(R.drawable.ic_more_fill2)
                }
            }
        } catch (e: Exception) {
            mylog(TAG, "onClick: Error=${e.localizedMessage}")
            loadFragment(HomeFragmentNewest())
            updateUi()
        }
    }

    /* get Activity result */
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            101 -> {
                if (resultCode == RESULT_OK && null != data) {
                    val result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
                    try {
                        if (mHomeFragmentNewest!!.isVisible) {
                            mHomeFragmentNewest!!.ChangeDestination(result!![0])
                        } else {
                            loadFragment(mHomeFragmentNewest)
                        }
                    } catch (e: Exception) {
                        mylog(TAG, "onActivityResult: Error=${e.localizedMessage}")
                        loadFragment(HomeFragmentNewest())
                        updateUi()
                    }

                }
            }
        }
    }

    // explode animation on activity start.
    private fun explodeAnim(savedInstanceState: Bundle?, intent: Intent) {
        if (savedInstanceState == null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP &&
            intent.hasExtra(EXTRA_CIRCULAR_REVEAL_X) &&
            intent.hasExtra(EXTRA_CIRCULAR_REVEAL_Y)
        ) {
            rootLayout?.setVisibility(View.INVISIBLE)
            revealX = intent.getIntExtra(EXTRA_CIRCULAR_REVEAL_X, 0)
            revealY = intent.getIntExtra(EXTRA_CIRCULAR_REVEAL_Y, 0)
            val viewTreeObserver: ViewTreeObserver = rootLayout!!.getViewTreeObserver()
            if (viewTreeObserver.isAlive) {
                viewTreeObserver.addOnGlobalLayoutListener(object :
                    ViewTreeObserver.OnGlobalLayoutListener {
                    override fun onGlobalLayout() {
                        revealActivity(revealX, revealY)
                        rootLayout!!.getViewTreeObserver().removeOnGlobalLayoutListener(this)
                    }
                })
            }
        } else {
            rootLayout!!.setVisibility(View.VISIBLE)
        }
    }

    // explode animation.
    protected fun revealActivity(x: Int, y: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val finalRadius = (Math.max(
                rootLayout!!.getWidth(),
                rootLayout!!.getHeight()
            ) * 1.1f)

            // create the animator for this view (the start radius is zero)
            val circularReveal =
                ViewAnimationUtils.createCircularReveal(rootLayout, x, y, 0f, finalRadius)
            circularReveal.duration = 800
            circularReveal.interpolator = AccelerateInterpolator()

            // make the view visible and start the animation
            rootLayout!!.setVisibility(View.VISIBLE)
            circularReveal.start()
        } else {
            finish()
        }
    }
}