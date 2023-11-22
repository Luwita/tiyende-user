package com.shuttleclone.userapp.ui.activity

import android.app.AlertDialog
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.WindowManager
import android.widget.ImageView
import androidx.core.app.ActivityOptionsCompat
import com.shuttleclone.userapp.BaseActivity
import com.shuttleclone.userapp.R
import com.shuttleclone.userapp.ui.intro.IntroActivity
import com.shuttleclone.userapp.utils.*
import com.google.android.play.core.appupdate.AppUpdateManager
import com.google.android.play.core.appupdate.AppUpdateManagerFactory
import com.google.android.play.core.install.model.UpdateAvailability
import org.jsoup.Jsoup
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors


class SplashActivity : BaseActivity() {
    /*variable declaration*/
    private var mIVLogo: ImageView? = null
    var rootView: View? = null
    val TAG = "SplashActivity"
    private val appUpdateManager: AppUpdateManager by lazy { AppUpdateManagerFactory.create(this) }
    private var isUpdateStatusCalled=false

    override fun onResume() {
        super.onResume()
        LocaleManager().setLocale(this) //This method update the language when the activity orientation changes
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        LocaleManager().setLocale(this) //This method update the language when the activity orientation changes
        setContentView(R.layout.activity_splash)
        initLayouts()
        initializeListeners()
    }

    /* init layout */
    private fun initLayouts() {
        mIVLogo = findViewById(R.id.ivLogo)
        rootView = findViewById(R.id.rootView)
    }

    /* initialize listener */
    private fun initializeListeners() {

        try {
            if (getPreference(
                    this@SplashActivity,
                    Constants.TOKEN
                ) == "" && getPreference(this@SplashActivity, Constants.FirstTimeUser) == ""
            ) {
                openIntroSlider()
            } else {
                if (isNetworkAvailable(this@SplashActivity)) {
//                    AppUpdate(this@SplashActivity).execute()
                    checkForAppUpdate()

                } else toast(this@SplashActivity)
            }
        } catch (e: Exception) {
            mylog(TAG, "initializeListeners: Error=${e.localizedMessage}")
        }


    }

    private fun checkForAppUpdate() {

        val appUpdateInfoTask = appUpdateManager.appUpdateInfo
        appUpdateInfoTask.addOnSuccessListener { appUpdateInfo ->
            isUpdateStatusCalled=true
            if (appUpdateInfo.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE) {
                showUpdateDialog()
            }else goAhead()
        }

        if (!isUpdateStatusCalled) checkAppUpdate()


        /* // Creates instance of the manager.
            val appUpdateManager = FakeAppUpdateManager(this)
            val appUpdateInfoTask = appUpdateManager.appUpdateInfo
            appUpdateInfoTask.addOnSuccessListener { appUpdateInfo ->
                val version = appUpdateInfo.availableVersionCode()
                mylog(TAG,"IN APP Latest Version="+ version)
                toast(this,"IN APP Latest Version="+ version)
            }*/
    }

    private fun checkAppUpdate() {
        try {
            val executor: ExecutorService = Executors.newSingleThreadExecutor()
            val handler = Handler(Looper.getMainLooper())


            executor.execute(Runnable {
                //Background work here
                var playStoreVersion: String? = null
                try {
                    mylog(TAG, "checkAppUpdate: Url= https://play.google.com/store/apps/details?id=${getPackageName()}")
                    playStoreVersion =
                        Jsoup.connect("https://play.google.com/store/apps/details?id=${getPackageName()}&hl=in")
                            .timeout(300000)
                            .userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
                            .referrer("http://www.google.com")
                            .get()
                            .select(".hAyfc .htlgb")
                            .get(7)
                            .ownText()
                } catch (e: Exception) {
                    mylog(TAG, "doInBackground: " + e.localizedMessage).toString()
                    playStoreVersion = "0"
                }


                handler.post {
                    //UI Thread work here
                    try {

                        val currentVersion = getPackageManager()
                            .getPackageInfo(getPackageName(), 0).versionName;
                        mylog(
                            TAG,
                            "Current version =" + currentVersion + " playstore version=" + playStoreVersion
                        );
                        if (playStoreVersion != null && !playStoreVersion.isEmpty()) {

                            mylog(
                                TAG,
                                "Update =" + (currentVersion < playStoreVersion)
                            );

                            if (currentVersion < playStoreVersion) {

                                showUpdateDialog()
                            }
                            else goAhead()
                        }
                    } catch (e: Exception) {
                        mylog(TAG, "onPostExecute: " + e.localizedMessage).toString()
                    }

                }
            })


        } catch (e: Exception) {
            mylog(TAG, "checkAppUpdate: Error=${e.localizedMessage}")
        }
    }

    private fun showUpdateDialog() {
        try {
            val dialog = AlertDialog.Builder(this).setTitle(getString(R.string.update_available))
            dialog.setMessage(getString(R.string.new_version_with_updated_features_available_on_the_play_store_please_update_the_application_to_continue))
            dialog.setCancelable(false)
            dialog.setPositiveButton(
                getString(R.string.update)
            ) { dialog, which ->
                val intent = Intent(Intent.ACTION_MAIN)
                intent.addCategory(Intent.CATEGORY_HOME)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent)

                val appPackageName =
                    packageName // getPackageName() from Context or Activity object
                try {
                    startActivity(
                        Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse("market://details?id=$appPackageName")
                        )
                    )
                } catch (anfe: ActivityNotFoundException) {
                    startActivity(
                        Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse("https://play.google.com/store/apps/details?id=$appPackageName")
                        )
                    )
                }
            }
            dialog.show()
        }catch (e:Exception){
            mylog(TAG, "showUpdateDialog: Error=${e.localizedMessage}")
        }

    }


    private fun openIntroSlider() {
        startActivity(Intent(this, IntroActivity::class.java))
        finish()
    }

    private fun goAhead() {
        try {

            if (!isPreference(this@SplashActivity, Constants.IsUserRegistered)) {
                signInActivity(rootView!!)
            } else {
                explodeBaseActivity(rootView!!, this)
                /* if (!checkAndRequestPermissions(this)) {
                     val intent = Intent(this, PermissionActivity::class.java)
                     intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK);
                     startActivity(intent)
                 } else {
                     explodeBaseActivity(rootView!!, this)
                 }*/
            }

        } catch (e: Exception) {
            mylog(TAG, "otherCheck: Error=${e.localizedMessage}")
        }


    }


    fun signInActivity(view: View) {

        val login = SelectionActivity()
        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(this, view, "transition")
        val revealX = (view.x + view.width / 2).toInt()
        val revealY = (view.y + view.height / 2).toInt()
        val intent = Intent(this, SelectionActivity::class.java)
        intent.putExtra(login.EXTRA_CIRCULAR_REVEAL_X, revealX)
        intent.putExtra(login.EXTRA_CIRCULAR_REVEAL_Y, revealY)
//        ActivityCompat.startActivity(this, intent, options.toBundle())
        startActivity(intent)
        finish()
    }


    fun explodeBaseActivity(
        view: View,
        activity: SplashActivity
    ) {

        mylog(TAG, "explodeBaseActivity: called")
        
        //Change InitialActivity to DashboardActivity Activity after Suggest Route process is done

        val base = DashboardActivity()
        val options =
            ActivityOptionsCompat.makeSceneTransitionAnimation(activity, view, "transition")
        val revealX = (view.x + view.width / 2).toInt()
        val revealY = (view.y + view.height / 2).toInt()
        val intent = Intent(activity, DashboardActivity::class.java)
        intent.putExtra(base.EXTRA_CIRCULAR_REVEAL_X, revealX)
        intent.putExtra(base.EXTRA_CIRCULAR_REVEAL_Y, revealY)
//        ActivityCompat.startActivity(activity, intent, options.toBundle())
        startActivity(intent)
        finish()
    }


}