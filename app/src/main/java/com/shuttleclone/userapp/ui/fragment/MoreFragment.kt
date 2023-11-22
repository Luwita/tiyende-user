package com.shuttleclone.userapp.ui.fragment

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.widget.TextView
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import com.shuttleclone.userapp.R
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.auth.api.signin.GoogleSignIn
import android.text.Html
import android.text.method.LinkMovementMethod
import com.shuttleclone.userapp.BaseActivity
import android.content.Intent
import android.content.DialogInterface
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.view.View
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.facebook.login.LoginManager
import com.shuttleclone.userapp.ui.activity.*
import com.shuttleclone.userapp.utils.*
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.gson.Gson
import com.shuttleclone.userapp.BuildConfig
import com.shuttleclone.userapp.ViewModel.HomeFragmentViewModel
import java.lang.Exception
import java.util.concurrent.Executor

class MoreFragment() : Fragment(), View.OnClickListener {
    private var mTvProfileSettings: TextView? = null
    private var mTvWallet: TextView? = null
    private var mTvCards: TextView? = null
    private var mTvReferEarn: TextView? = null
    private var mTvHelp: TextView? = null
    private var mTvLogout: TextView? = null
    private var mTvSetting: TextView? = null
    private var mTvTransactionHistory: TextView? = null
    private var mTvBookingHistory: TextView? = null
    private var mTvChangeLanguage: TextView? = null
    private var mTvPass: TextView? = null
    private var mTvPoweredBy: TextView? = null
    private var mTvSuggestRoutes: TextView? = null
    private var mTvExploreRoutes: TextView? = null
    private var mTvVersion: TextView? = null
    private var mTvTermsCondition: TextView? = null
    private var mTvPrivacyPolicy: TextView? = null
    private val mFlag = "1"
    private var mGoogleSignInClient: GoogleSignInClient? = null
    private var isRegisteredWithSocial = false
    private var selectedLanguage = ""

    private val homeFragmentViewModel: HomeFragmentViewModel by viewModels()


    /* create view */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_more, null)
        isRegisteredWithSocial = isPreference(context, Constants.REGISTER_WITH_SOCIAL)
        initLayouts(view)
        initializeListeners()
        initializeGoogleLogin()

        viewModelLogOutResponse()
        updateLanguageResponse()

        return view
    }
    private fun viewModelLogOutResponse() {
        try {
            homeFragmentViewModel?.let {
                it.logOutUser.observe(viewLifecycleOwner,
                    androidx.lifecycle.Observer {
                        LoadingDialog.cancelLoading()
                        try {
                            mylog(TAG, "viewModelLogOutResponse: Response=${Gson().toJson(it)}")

                            if (it == null)
                                return@Observer

                            if (it.isStatus) {
                                toast(context, it.message)
                                savePreference(context, Constants.FirstTimeUser, "NO")
                                savePreference(context, Constants.csrfTOKEN, "")
                                savePreference(context, Constants.TOKEN, "")
                                savePreference(context, Constants.IsUserRegistered, false)
                                savePreference(context, Constants.REGISTER_WITH_SOCIAL, false)
                                savePreference(context, Constants.SOCIAL_USER_DETAILS, "")
                                startActivity(Intent(activity, SelectionActivity::class.java))
                                requireActivity().finish()
                            } else alertDialog(requireContext(), it.message.toString())

                        } catch (e: Exception) {
                            alertDialog(requireContext(), e.localizedMessage)
                        }

                    })
            }
        } catch (e: Exception) {
            mylog(TAG, "viewModelLogOutResponse: Error=" + e.localizedMessage)
        }

    }

    private fun initializeGoogleLogin() {
        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient((context)!!, gso)
    }

    /* initialize listener */
    private fun initializeListeners() {
        mTvProfileSettings!!.setOnClickListener(this)
        mTvWallet!!.setOnClickListener(this)
        mTvCards!!.setOnClickListener(this)
        mTvReferEarn!!.setOnClickListener(this)
        mTvHelp!!.setOnClickListener(this)
        mTvLogout!!.setOnClickListener(this)
        mTvSetting!!.setOnClickListener(this)
        mTvPass!!.setOnClickListener(this)
        mTvExploreRoutes!!.setOnClickListener(this)
        mTvSuggestRoutes!!.setOnClickListener(this)
        mTvTransactionHistory!!.setOnClickListener(this)
        mTvBookingHistory!!.setOnClickListener(this)
        mTvChangeLanguage!!.setOnClickListener(this)
        mTvTermsCondition!!.setOnClickListener(this)
        mTvPrivacyPolicy!!.setOnClickListener(this)
    }

    /* init layout */
    private fun initLayouts(view: View) {
        mTvProfileSettings = view.findViewById(R.id.tvProfileSettings)
        mTvWallet = view.findViewById(R.id.tvWallet)
        mTvCards = view.findViewById(R.id.tvCards)
        mTvReferEarn = view.findViewById(R.id.tvReferEarn)
        mTvHelp = view.findViewById(R.id.tvHelp)
        mTvLogout = view.findViewById(R.id.tvLogout)
        mTvSetting = view.findViewById(R.id.tvSetting)
        mTvPass = view.findViewById(R.id.tvPass)
        mTvPoweredBy = view.findViewById(R.id.tvPoweredBy)
        mTvExploreRoutes = view.findViewById(R.id.tvExploreRoutes)
        mTvSuggestRoutes = view.findViewById(R.id.tvSuggestRoutes)
        mTvTransactionHistory = view.findViewById(R.id.tvTransactionHistory)
        mTvBookingHistory = view.findViewById(R.id.tvBookingHistory)
        mTvChangeLanguage = view.findViewById(R.id.tvChangeLanguage)
        mTvTermsCondition = view.findViewById(R.id.tvTermsCondition)
        mTvPrivacyPolicy = view.findViewById(R.id.tvPrivacyPolicy)

        mTvVersion = view.findViewById(R.id.tvAppVersion)
        mTvVersion!!.text =
            "${requireActivity().getString(R.string.text_version) + BuildConfig.VERSION_NAME}"
        /*String styledText = "This is <font color='blue'>simple</font>.";
        mTvPoweredBy.setText(Html.fromHtml(styledText), TextView.BufferType.SPANNABLE);*/
        mTvPoweredBy!!.setText(Html.fromHtml(getString(R.string.text_with_link)))
        mTvPoweredBy!!.setMovementMethod(LinkMovementMethod.getInstance())

      /*mTvTermsCondition!!.setText(Html.fromHtml(getString(R.string.txt_terms_conditions)))
        mTvTermsCondition!!.setMovementMethod(LinkMovementMethod.getInstance())

        mTvPrivacyPolicy!!.setText(Html.fromHtml(getString(R.string.txt_privacy_policy)))
        mTvPrivacyPolicy!!.setMovementMethod(LinkMovementMethod.getInstance())*/

    }

    /* onClick listener */
    override fun onClick(v: View) {
        if (v === mTvProfileSettings) (requireActivity() as BaseActivity).startActivity(
            ProfileSettingsActivity::class.java
        ) else if (v === mTvWallet) (requireActivity() as BaseActivity).startActivity(
            WalletActivity::class.java
        ) else if (v === mTvCards) {
            val intent = Intent(activity, CardsActivity::class.java)
            intent.putExtra(Constants.intentdata.CARDFLAG, mFlag)
            startActivity(intent)
        } else if (v === mTvReferEarn) (requireActivity() as BaseActivity).startActivity(
            ReferEarnActivity::class.java
        ) else if (v === mTvHelp) (requireActivity() as BaseActivity).startActivity(
            HelpActivity::class.java
        ) else if (v === mTvSetting) (requireActivity() as BaseActivity).startActivity(
            SettingActivity::class.java
        ) else if (v === mTvPass) (requireActivity() as BaseActivity).startActivity(
            PassActivity::class.java
        ) else if (v === mTvExploreRoutes) (requireActivity() as BaseActivity).startActivity(
            ExploreActivity::class.java
        ) else if (v === mTvSuggestRoutes) (requireActivity() as BaseActivity).startActivity(
            SuggestRouteActivity::class.java
        ) else if (v === mTvTransactionHistory) (requireActivity() as BaseActivity).startActivity(
            TransactionHistoryActivity::class.java
        ) else if (v === mTvBookingHistory) (requireActivity() as BaseActivity).startActivity(
            BookingHistoryActivity::class.java
        ) else if (v === mTvChangeLanguage) updateLanguageDialog(requireContext())
        else if (v === mTvTermsCondition) {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.url_terms)))
            startActivity(intent)
        }
        else if (v === mTvPrivacyPolicy){
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.url_privacy)))
            startActivity(intent)
        }
        else if (v === mTvLogout) {
            val builder = AlertDialog.Builder(activity)
            builder.setTitle(getString(R.string.text_confirmation))
                .setMessage(getString(R.string.msg_logout))
            builder.setPositiveButton(getString(R.string.text_yes),
                DialogInterface.OnClickListener { dialog, id -> logOut() })
            builder.setNegativeButton(getString(R.string.text_no),
                object : DialogInterface.OnClickListener {
                    override fun onClick(dialog: DialogInterface, id: Int) {
                        dialog.cancel()
                    }
                })
            val alert = builder.create()
            alert.show()
        }

    }


    private var mLanguageChangeDialog: Dialog? = null
    private fun updateLanguageDialog(mContext: Context) {
        try {
            if (mLanguageChangeDialog == null)
                mLanguageChangeDialog = Dialog(mContext)

            mLanguageChangeDialog!!.setContentView(R.layout.language_update_dialog)
            mLanguageChangeDialog!!.setCancelable(true)
            mLanguageChangeDialog!!.window?.setBackgroundDrawable(ColorDrawable(0))


            val radioGroup = mLanguageChangeDialog!!.findViewById<RadioGroup>(R.id.rgLanguage)

            selectedLanguage = ""

            if (getPreference(requireContext(), Constants.LANGUAGE).equals("en")) {
                selectedLanguage = "English"
                radioGroup!!.check(R.id.rbEnglish)
            } else if(getPreference(requireContext(), Constants.LANGUAGE).equals("ar")){
                selectedLanguage="عربي"
                radioGroup!!.check(R.id.rbArabic)
            }

            radioGroup.setOnCheckedChangeListener { group, checkedId ->
                val selectedRadioButton =
                    mLanguageChangeDialog!!.findViewById<RadioButton>(checkedId)
                selectedLanguage = selectedRadioButton.text.toString()
            }

            mLanguageChangeDialog!!.findViewById<View>(R.id.btnConfirm).setOnClickListener {

                if (getPreference(requireContext(), Constants.LANGUAGE).equals("en") && selectedLanguage.equals("English")) {
                    toast(requireContext(), getString(R.string.this_language_is_already_set))
                    return@setOnClickListener
                } else if (getPreference(requireContext(), Constants.LANGUAGE).equals("ar") && selectedLanguage.equals("عربي")) {
                    toast(requireContext(), getString(R.string.this_language_is_already_set))
                    return@setOnClickListener
                }

                if (isNetworkAvailable((context)!!)) {
                    when (selectedLanguage) {
                        "English" -> {
                            homeFragmentViewModel?.let {
                                LoadingDialog.showLoadingDialog(context, getString(R.string.pls_wait_loading))
                                it.updateLanguage(requireContext(),
                                    getPreference(requireContext(),Constants.TOKEN),
                                    LocaleManager.ENGLISH)
                            }
                        }

                        "عربي" -> {

                            homeFragmentViewModel?.let {
                                LoadingDialog.showLoadingDialog(context, getString(R.string.pls_wait_loading))
                                it.updateLanguage(requireContext(),
                                    getPreference(requireContext(),Constants.TOKEN),
                                    LocaleManager.ARABIC)
                            }
                        }

                    }

                }else toast(context, getString(R.string.txt_Network))

            }

            mLanguageChangeDialog!!.setCancelable(true)
            mLanguageChangeDialog!!.setCanceledOnTouchOutside(true)

            if (!mLanguageChangeDialog!!.isShowing)
                mLanguageChangeDialog!!.show()

        } catch (e: Exception) {
            mylog(TAG, "alertDialog: Error=${e.localizedMessage}")
        }
    }

    private fun logOut() {
        try {
            if (isNetworkAvailable((context)!!)) {

                homeFragmentViewModel?.let {
                    LoadingDialog.showLoadingDialog(context, getString(R.string.pls_wait_loading))
                    it.logOutUser(
                        context,
                        getPreference(context, Constants.TOKEN),
                        getPreference(context, Constants.csrfTOKEN)
                    )
                }

                if (isRegisteredWithSocial) {
                    try {
                        if (GoogleSignIn.getLastSignedInAccount(requireContext()) != null) {
                            signOut()
                        }
                        LoginManager.getInstance().logOut()
                    } catch (e: Exception) {
                        mylog(TAG, "logOutUser: Errorx=" + e.localizedMessage)
                    }
                }

            } else toast(context, getString(R.string.txt_Network))
        } catch (e: Exception) {
            mylog(TAG, "logOutUser: error=" + e.localizedMessage)
        }
    }
    private fun updateLanguageResponse() {
        try {
            if (isNetworkAvailable((context)!!)) {

                homeFragmentViewModel?.let {
                    it.updateLanguage.observe(viewLifecycleOwner,
                        androidx.lifecycle.Observer {
                            LoadingDialog.cancelLoading()
                            try {
                                mylog(TAG, "updateLanguageResponse: Response=${Gson().toJson(it)}")

                                if (mLanguageChangeDialog!!.isShowing) mLanguageChangeDialog!!.dismiss()

                                if (it == null)
                                    return@Observer

                                toast(context,it.message)


                                if (it.isStatus) {
                                    when (selectedLanguage) {
                                        "English" -> {
                                            savePreference(
                                                requireContext(),
                                                Constants.LANGUAGE,
                                                LocaleManager.ENGLISH
                                            )

                                            requireActivity().startActivity(
                                                Intent(
                                                    requireActivity(),
                                                    SplashActivity::class.java
                                                )
                                            )
                                            requireActivity().finishAffinity()
                                        }

                                        "عربي" -> {
                                            savePreference(
                                                requireContext(),
                                                Constants.LANGUAGE,
                                                LocaleManager.ARABIC
                                            )
                                            requireActivity().startActivity(
                                                Intent(
                                                    requireActivity(),
                                                    SplashActivity::class.java
                                                )
                                            )
                                            requireActivity().finishAffinity()
                                        }

                                    }

                                } else alertDialog(requireContext(), it.message.toString())

                            } catch (e: Exception) {
                                alertDialog(requireContext(), e.localizedMessage)
                            }

                        })
                }


            } else toast(context, getString(R.string.txt_Network))
        } catch (e: Exception) {
            mylog(TAG, "logOutUser: error=" + e.localizedMessage)
        }
    }

    private fun signOut() {
        LoadingDialog.showLoadingDialog(context, getString(R.string.pls_wait_loading))
        mGoogleSignInClient!!.signOut()
            .addOnCompleteListener((this as Executor), object : OnCompleteListener<Void?> {
                override fun onComplete(task: Task<Void?>) {
                  LoadingDialog.cancelLoading()
                }
            })
        LoadingDialog.cancelLoading()
    }

    companion object {
        /*variable declaration*/
        private val TAG = "MoreFragment"
    }
}