package com.shuttleclone.userapp.ui.activity

import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import android.os.Build
import com.shuttleclone.userapp.BaseActivity
import android.os.Bundle
import android.text.Html
import android.text.method.LinkMovementMethod
import android.view.View
import android.view.ViewAnimationUtils
import android.view.ViewTreeObserver
import android.view.animation.AccelerateInterpolator
import android.widget.TextView
import androidx.activity.viewModels
import com.google.gson.Gson
import com.shuttleclone.userapp.BuildConfig
import com.shuttleclone.userapp.R
import com.shuttleclone.userapp.ViewModel.HomeFragmentViewModel
import com.shuttleclone.userapp.utils.*
import java.lang.Exception

class InitialActivity : BaseActivity(), View.OnClickListener {
    /*variable declaration*/

    private var tvSuggestRoutes: TextView? = null
    private var tvProfileSettings: TextView? = null
    private var tvHelp: TextView? = null
    private var tvLogout: TextView? = null
    private var tvAppVersion: TextView? = null
    private var tvSiteLink: TextView? = null
    private var tvPoweredBy: TextView? = null
    val TAG = "InitialActivity"
    var rootLayout: View? = null

    val EXTRA_CIRCULAR_REVEAL_X = "EXTRA_CIRCULAR_REVEAL_X"
    val EXTRA_CIRCULAR_REVEAL_Y = "EXTRA_CIRCULAR_REVEAL_Y"
    private var revealX = 0
    private var revealY = 0
    private val homeFragmentViewModel: HomeFragmentViewModel by viewModels()
    override fun onResume() {
        super.onResume()
        LocaleManager().setLocale(this) //This method update the language when the activity orientation changes
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LocaleManager().setLocale(this) //This method update the language when the activity orientation changes
        setContentView(R.layout.activity_initial)
        initLayouts()
        initializeListeners()

        // explode animation on activity start.
        explodeAnim(savedInstanceState, intent)

        viewModelLogOutResponse()
    }

    /* initialize listener */
    private fun initializeListeners() {
        tvSuggestRoutes!!.setOnClickListener(this)
        tvProfileSettings!!.setOnClickListener(this)
        tvHelp!!.setOnClickListener(this)
        tvLogout!!.setOnClickListener(this)
        tvSiteLink!!.setOnClickListener(this)
//        tvPoweredBy!!.setOnClickListener(this)
    }

    /* init layout */
    private fun initLayouts() {
        rootLayout = findViewById(R.id.rlMain)
        tvSuggestRoutes = findViewById(R.id.tvSuggestRoutes)
        tvProfileSettings = findViewById(R.id.tvProfileSettings)
        tvHelp = findViewById(R.id.tvHelp)
        tvLogout = findViewById(R.id.tvLogout)
        tvAppVersion = findViewById(R.id.tvAppVersion)
        tvSiteLink = findViewById(R.id.tvSiteLink)
        tvPoweredBy = findViewById(R.id.tvPoweredBy)

        tvAppVersion!!.setText("${getString(R.string.text_version)} ${BuildConfig.VERSION_NAME}")
        tvPoweredBy!!.setText(
            Html.fromHtml(getString(R.string.text_with_link))
        )
        tvPoweredBy!!.setMovementMethod(LinkMovementMethod.getInstance())
    }

    /* onClick listener */
    override fun onClick(v: View) {
        if (v === tvSuggestRoutes) startActivity(SuggestRouteActivity::class.java)
        else if (v === tvProfileSettings) startActivity(ProfileSettingsActivity::class.java)
        else if (v === tvHelp) startActivity(HelpActivity::class.java)
        else if (v === tvSiteLink) startActivity(
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse("http://shuttle.theferri.com/index.html")
            )
        )
        else if (v === tvPoweredBy) startActivity(
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://www.sourcemonster.in")
            )
        )
        else if (v === tvLogout) {
            val builder = AlertDialog.Builder(this)
            builder.setTitle(getString(R.string.text_confirmation))
                .setMessage(getString(R.string.msg_logout))
            builder.setPositiveButton(
                getString(R.string.text_yes)
            ) { dialog, id -> logOut() }
            builder.setNegativeButton(
                getString(R.string.text_no)
            ) { dialog, id -> dialog.cancel() }
            val alert = builder.create()
            alert.show()
        }
    }

    private fun viewModelLogOutResponse() {
        try {
            homeFragmentViewModel?.let {
                it.logOutUser.observe(this,
                    androidx.lifecycle.Observer {
                        LoadingDialog.cancelLoading()
                        try {
                            mylog(TAG, "viewModelLogOutResponse: Response=${Gson().toJson(it)}")

                            if (it == null)
                                return@Observer

                            if (it.isStatus) {
                                toast(this@InitialActivity, it.message)
                                savePreference(this@InitialActivity, Constants.FirstTimeUser, "NO")
                                savePreference(this@InitialActivity, Constants.csrfTOKEN, "")
                                savePreference(this@InitialActivity, Constants.TOKEN, "")
                                savePreference(this@InitialActivity, Constants.IsUserRegistered,false)
                                savePreference(this@InitialActivity,Constants.REGISTER_WITH_SOCIAL,false)
                                savePreference(
                                    this@InitialActivity,
                                    Constants.SOCIAL_USER_DETAILS,
                                    ""
                                )
                                startActivity(
                                    Intent(
                                        this@InitialActivity,
                                        SelectionActivity::class.java
                                    )
                                )
                                finish()
                            } else alertDialog(this@InitialActivity, it.message.toString())

                        } catch (e: Exception) {
                            alertDialog(this@InitialActivity, e.localizedMessage)
                        }

                    })
            }
        } catch (e: Exception) {
            mylog(TAG, "viewModelLogOutResponse: Error=" + e.localizedMessage)
        }

    }


    private fun logOut() {
        try {
            if (isNetworkAvailable(this)) {

                homeFragmentViewModel?.let {
                    LoadingDialog.showLoadingDialog(this, getString(R.string.pls_wait_loading))
                    it.logOutUser(
                        this,
                        getPreference(this, Constants.TOKEN),
                        getPreference(this, Constants.csrfTOKEN)
                    )
                }

            } else toast(this, getString(R.string.txt_Network))
        } catch (e: Exception) {
            mylog(TAG, "logOutUser: Errord=" + e.localizedMessage)
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