package com.shuttleclone.userapp.ui.activity

import android.Manifest
import android.annotation.TargetApi
import android.app.TimePickerDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.provider.Settings
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.*
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.shuttleclone.userapp.BaseActivity
import com.shuttleclone.userapp.R
import com.shuttleclone.userapp.model.SocialLoginUserDetails
import com.shuttleclone.userapp.ui.events.UpdateLocationEvent
import com.shuttleclone.userapp.ui.fragment.SearchLocationDialogFragment
import com.shuttleclone.userapp.utils.*
import com.shuttleclone.userapp.utils.Constants.UPDATE_HOME
import com.shuttleclone.userapp.utils.Constants.UPDATE_OFFICE
import com.google.firebase.dynamiclinks.FirebaseDynamicLinks
import com.google.gson.Gson
import com.shuttleclone.userapp.ViewModel.HomeFragmentViewModel
import de.hdodenhof.circleimageview.CircleImageView
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.net.URL
import java.util.*


class ProfileSettingsActivity : BaseActivity(), View.OnClickListener {
    /*variable declaration*/
    private var mIvBack: ImageView? = null
    private var mIvNotification: ImageView? = null
    private var mIvAddProfile: ImageView? = null
    private var mIvProfileImage: CircleImageView? = null
    private var mReferralCard: CardView? = null
    private var mBtnSave: Button? = null
    private var mSpGender: Spinner? = null
    private val TAG = "ProfileSettingsActivity"
    private var phoneNo = ""
    private var officeAddress = ""
    private var officeLat = ""
    private var officeLng = ""
    private var homeAddress = ""
    private var homeLat = ""
    private var homeLng = ""
    private var homeLeaveTime = ""
    private var officeLeaveTime = ""
    private var mEdFirstName: EditText? = null
    private var mEdLastName: EditText? = null
    private var mTvHomeAddress: TextView? = null
    private var mTvOfficeAddress: TextView? = null
    private var mTvHomeTime: TextView? = null
    private var mTvOfficeTime: TextView? = null
    private var mEdEmail: EditText? = null
    private var mEdContact: EditText? = null
    private var mEdReferralCode: EditText? = null
    private var body: MultipartBody.Part? = null
    private var imageUri: Uri? = null
    private var socialLoginUserDetails: SocialLoginUserDetails? = null
    private var isRegisteredWithSocial = false
    var eventBus = EventBus.getDefault()
    private val homeFragmentViewModel: HomeFragmentViewModel by viewModels()


    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            openDialogBox()
        } else {
            showStoragePermissionSnackBar()
        }
    }

    override fun onResume() {
        super.onResume()
        LocaleManager().setLocale(this) //This method update the language when the activity orientation changes
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LocaleManager().setLocale(this) //This method update the language when the activity orientation changes
        setContentView(R.layout.activity_profile)
        isRegisteredWithSocial = isPreference(this, Constants.REGISTER_WITH_SOCIAL)
        if (isRegisteredWithSocial) {
            val socialData = getPreference(this, Constants.SOCIAL_USER_DETAILS)
            mylog(TAG, "onCreate: socialData=$socialData")
            socialLoginUserDetails = Gson().fromJson(socialData, SocialLoginUserDetails::class.java)
        }

        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this)
            eventBus = EventBus.getDefault()
        }

        initLayouts()
        initializeListeners()
    }

    /* initialize listener */
    private fun initializeListeners() {
        mIvBack!!.setOnClickListener(this)
        mBtnSave!!.setOnClickListener(this)
        mIvNotification!!.setOnClickListener(this)
        mIvAddProfile!!.setOnClickListener(this)
        mTvHomeAddress!!.setOnClickListener(this)
        mTvHomeTime!!.setOnClickListener(this)
        mTvOfficeAddress!!.setOnClickListener(this)
        mTvOfficeTime!!.setOnClickListener(this)
        mBtnSave!!.stateListAnimator = null
    }

    /* init layout */
    private fun initLayouts() {
        mIvBack = findViewById(R.id.ivBack)
        mBtnSave = findViewById(R.id.btnSave)
        mEdFirstName = findViewById(R.id.edFirstName)
        mEdLastName = findViewById(R.id.edLastName)
        mEdEmail = findViewById(R.id.edEmail)
        mEdContact = findViewById(R.id.edContact)
        mEdReferralCode = findViewById(R.id.edReferralCode)
        mReferralCard = findViewById(R.id.card3)
        mIvAddProfile = findViewById(R.id.ivAddProfile)
        mSpGender = findViewById(R.id.spGenger)
        mTvHomeAddress = findViewById(R.id.tvHomeAddress)
        mTvHomeTime = findViewById(R.id.tvHomeTime)
        mTvOfficeAddress = findViewById(R.id.tvOfficeAddress)
        mTvOfficeTime = findViewById(R.id.tvOfficeTime)
        mIvProfileImage = findViewById(R.id.ivProfileImage)
        mIvNotification = findViewById(R.id.ivNotification)
        SetNotificationImage(mIvNotification)
        val intent = intent
        if (intent != null) {
            phoneNo = intent.getLongExtra("phone", 0).toString()
            if (phoneNo != "0") {
                mEdContact!!.setEnabled(false)
                mEdContact!!.setText(phoneNo)
            }
        }

        mReferralCard!!.setVisibility(View.VISIBLE)
        getDynamicLinkData()

        if (isRegisteredWithSocial) {
            mEdEmail!!.setText(socialLoginUserDetails!!.email)
            mEdFirstName!!.setText(socialLoginUserDetails!!.fname)
            mEdLastName!!.setText(socialLoginUserDetails!!.lname)
            mEdContact!!.setEnabled(false)
            mylog(TAG, "initLayouts: isRegisteredWithSocial TRUVE")
            try {
                if (socialLoginUserDetails!!.photo != null && socialLoginUserDetails.toString() == "") {
                    Glide.with(applicationContext).load(socialLoginUserDetails!!.photo)
                        .placeholder(R.drawable.ic_profile).into(mIvProfileImage!!)
                    val url = URL(socialLoginUserDetails!!.photo)
                    val image =
                        BitmapFactory.decodeStream(url.openConnection().getInputStream())
                    convertBitmapToFile(image)
                }
            } catch (e: Exception) {
                mylog(TAG, e.localizedMessage)
            }
        } else getProfileDetails()

        viewModelResponseGetProfileDetails()
        viewModelResponseUpdateProfileDetails()
        viewModelResponseUpdateAddressDetails()
    }

    private fun viewModelResponseGetProfileDetails() {
        try {
            homeFragmentViewModel?.let {
                it.getUserProfileDetails.observe(this,
                    androidx.lifecycle.Observer { userData->
                        LoadingDialog.cancelLoading()
                        try {
                            mylog(TAG, "viewModelResponseProfileDetails: Response=${Gson().toJson(userData)}")

                            if (userData == null)
                                return@Observer

                            if (userData.isStatus) {
                                try {
                                    userData.data?.let {data ->

                                        mEdEmail!!.setText(data.email)
                                        mEdFirstName!!.setText(data.firstname)
                                        mEdLastName!!.setText(data.lastname)
                                        mEdContact!!.setText(data.phone)
                                        mEdContact!!.isEnabled = false


                                        mTvOfficeAddress!!.setText(data.officeAddress)
                                        mTvHomeAddress!!.setText(data.homeAddress)
                                        mTvHomeTime!!.setText(data.homeTiming)
                                        mTvOfficeTime!!.setText(data.officeTiming)


                                        savePreference(
                                            this@ProfileSettingsActivity,
                                            Constants.USER_NAME,
                                            data.firstname + " " + data.lastname
                                        )
                                        savePreference(
                                            this@ProfileSettingsActivity,
                                            Constants.EMAIL,
                                            data.email + ""
                                        )
                                        if (data.profilePic != null) {
                                            Glide.with(applicationContext)
                                                .load(data.profilePic)
                                                .placeholder(R.drawable.ic_profile)
                                                .into(mIvProfileImage!!)
                                        }
                                        if (data.gender == "Male") {
                                            mSpGender!!.setSelection(0);
                                        } else mSpGender!!.setSelection(1)
                                        savePreference(
                                            this@ProfileSettingsActivity,
                                            Constants.GANDER,
                                            data.gender + ""
                                        )

                                        officeAddress = data.officeAddress.toString()
                                        homeAddress = data.homeAddress.toString()
                                        officeLat = data.officeLat.toString()
                                        officeLng = data.officeLng.toString()
                                        homeLat = data.homeLat.toString()
                                        homeLng = data.homeLng.toString()
                                        homeLeaveTime = data.homeTiming.toString()
                                        officeLeaveTime = data.officeTiming.toString()

                                    }?: alertDialog(this, userData.toString())

                                } catch (e: Exception) {
                                    mylog(TAG, "onSuccess:viewModelResponseGetProfileDetails Error=" + e.localizedMessage)
                                }
                            } else alertDialog(this, userData.message.toString())

                        } catch (e: Exception) {
                            alertDialog(this, e.localizedMessage)
                            mylog(TAG, "onSuccess:viewModelResponseGetProfileDetails Error 2=" + e.localizedMessage)
                        }

                    })
            }
        } catch (e: java.lang.Exception) {
            mylog(TAG, "viewModelResponseProfileDetails: Error=" + e.localizedMessage)
        }

    }
    private fun viewModelResponseUpdateProfileDetails() {
        try {
            homeFragmentViewModel?.let {
                it.updateUserProfile.observe(this,
                    androidx.lifecycle.Observer {
                        LoadingDialog.cancelLoading()
                        try {
                            mylog(TAG, "UpdateProfileDetails: Response=${Gson().toJson(it)}")

                            if (it == null)
                                return@Observer

                            if (it.isStatus) {
                                toast(this@ProfileSettingsActivity, it.message)
                                if (isPreference(this@ProfileSettingsActivity, Constants.IsUserUpdatingFirstTime)) {
                                    savePreference(this@ProfileSettingsActivity, Constants.IsUserRegistered, true)
                                    savePreference(this@ProfileSettingsActivity, Constants.IsUserUpdatingFirstTime, false)
                                    startActivity(DashboardActivity::class.java)
                                    finishAffinity()
                                } else getProfileDetails()
                            }else alertDialog(this, it.message.toString())

                            mylog(TAG, "viewModelResponseProfileDetails: Response=${Gson().toJson(it)}")

                        } catch (e: Exception) {
                            alertDialog(this, e.localizedMessage)
                        }

                    })
            }
        } catch (e: java.lang.Exception) {
            mylog(TAG, "viewModelResponseUpdateProfileDetails: Error=" + e.localizedMessage)
        }

    }
    private fun viewModelResponseUpdateAddressDetails() {
        try {
            homeFragmentViewModel?.let {
                it.addOfficeRideAddress.observe(this,
                    androidx.lifecycle.Observer {
                        LoadingDialog.cancelLoading()
                        try {
                            mylog(TAG, "addOfficeRideAddress: Response=${Gson().toJson(it)}")

                            if (it == null)
                                return@Observer

                            if (it.isStatus) {
                                if (isPreference(this@ProfileSettingsActivity, Constants.IsUserUpdatingFirstTime)) {
                                    savePreference(this@ProfileSettingsActivity, Constants.IsUserRegistered, true)
                                    savePreference(this@ProfileSettingsActivity, Constants.IsUserUpdatingFirstTime, false)
                                    startActivity(DashboardActivity::class.java)
                                    finishAffinity()
                                } else getProfileDetails()
                            }else alertDialog(this, it.message.toString())

                            mylog(TAG, "viewModelResponseProfileDetails: Response=${Gson().toJson(it)}")

                        } catch (e: Exception) {
                            alertDialog(this, e.localizedMessage)
                        }

                    })
            }
        } catch (e: java.lang.Exception) {
            mylog(TAG, "viewModelResponseUpdateAddressDetails: Error=" + e.localizedMessage)
        }

    }

    private fun getDynamicLinkData() {
        try {

            FirebaseDynamicLinks.getInstance()
                .getDynamicLink(intent)
                .addOnSuccessListener(this) { pendingDynamicLinkData ->
                    // Get deep link from result (may be null if no link is found)
                    var deepLink: Uri? = null
                    if (pendingDynamicLinkData != null) {
                        deepLink = pendingDynamicLinkData.link
                    }


                    mylog(TAG, "dynamicLink: deepLink " + deepLink)

                    val userId = deepLink.toString().indexOf('=')
                        .let { if (it == -1) null else deepLink.toString().substring(it + 1) }
                    if (userId != null) {
                        mEdReferralCode!!.setText(userId)
                        mEdReferralCode!!.isEnabled = false
                    }
                    mylog(TAG, "dynamicLink :user id " + userId)
                }
                .addOnFailureListener(this) { e -> Log.w(TAG, "getDynamicLink:onFailure", e) }
        } catch (e: java.lang.Exception) {
            mylog(TAG, "getDynamicLinkData: error=${e.localizedMessage}")
        }
    }

    private fun getProfileDetails() {
        try {
            if (isNetworkAvailable(this)) {

                homeFragmentViewModel?.let {
                    LoadingDialog.showLoadingDialog(this, getString(R.string.pls_wait_loading))
                    it.getProfileDetails(
                        this,
                        getPreference(this, Constants.TOKEN)
                    )
                }
            } else toast(this, getString(R.string.txt_Network))
        } catch (e: Exception) {
            mylog(TAG, "getProfileDetails: Error=" + e.localizedMessage)
            LoadingDialog.cancelLoading()
        }
    }

    /* onClick listener */
    override fun onClick(v: View) {
        if (v === mIvBack) onBackPressedDispatcher.onBackPressed() else if (v === mIvNotification) startActivity(
            AppNotificationsActivity::class.java
        ) else if (v === mIvAddProfile) {
            checkRunTimePermission()
        } else if (v === mBtnSave) {
            if (validate()) {
                updateInformation()
            }
        } else if (v === mTvHomeAddress) {
            val searchFragment = SearchLocationDialogFragment(this!!, Constants.UPDATE_HOME)
            if (!searchFragment!!.isVisible) {
                searchFragment!!.show(supportFragmentManager!!, "SearchAddressFragment")
            }
        } else if (v === mTvHomeTime) {
            setTimeForRide(true)

        } else if (v === mTvOfficeAddress) {
            val searchFragment = SearchLocationDialogFragment(this!!, Constants.UPDATE_OFFICE)
            if (!searchFragment!!.isVisible) {
                searchFragment!!.show(supportFragmentManager!!, "SearchAddressFragment")
            }
        } else if (v === mTvOfficeTime) {
            setTimeForRide(false)
        }
    }

    private fun setTimeForRide(forHome: Boolean) {
        val mcurrentTime = Calendar.getInstance()
        var hour = mcurrentTime.get(Calendar.HOUR_OF_DAY)
        var minute = mcurrentTime.get(Calendar.MINUTE)

        TimePickerDialog(this, object : TimePickerDialog.OnTimeSetListener {
            override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minutes: Int) {

                hour = hourOfDay
                minute = minutes
                var timeSet = ""
                if (hour > 12) {
                    hour -= 12
                    timeSet = "PM"
                } else if (hour === 0) {
                    hour += 12
                    timeSet = "AM"
                } else if (hour === 12) {
                    timeSet = "PM"
                } else {
                    timeSet = "AM"
                }

                var min: String? = ""
                if (minute < 10) min = "0$minute" else min = java.lang.String.valueOf(minute)

                val mTime = StringBuilder().append(hour).append(':')
                    .append(min).append(" ").append(timeSet).toString()

                if (forHome) {
                    mTvHomeTime!!.setText(mTime)
                    homeLeaveTime = mTime
                }else{
                    mTvOfficeTime!!.setText(mTime)
                    officeLeaveTime = mTime
                }

            }
        }, hour, minute, false).show()

    }

    private fun checkRunTimePermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            when {
                ContextCompat.checkSelfPermission(this, Manifest.permission.READ_MEDIA_IMAGES)
                        == PackageManager.PERMISSION_GRANTED -> {
                    openDialogBox()
                }

                shouldShowRequestPermissionRationale(Manifest.permission.READ_MEDIA_IMAGES) -> {
                    showStoragePermissionSnackBar()
                }

                else -> {
                    // The registered ActivityResultCallback gets the result of this request
                    requestPermissionLauncher.launch(
                        Manifest.permission.READ_MEDIA_IMAGES
                    )
                }
            }

        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            when {
                ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                ) == PackageManager.PERMISSION_GRANTED -> {
                    openDialogBox()
                }

                shouldShowRequestPermissionRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE) -> {
                    showStoragePermissionSnackBar()
                }

                else -> {
                    // The registered ActivityResultCallback gets the result of this request
                    requestPermissionLauncher.launch(
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                    )
                }
            }
        }
    }

    private fun showStoragePermissionSnackBar() {
        Snackbar.make(
            findViewById(R.id.root_layout),
            getString(R.string.storage_permission_blocked_please_go_to_settings_and_allow_it),
            Snackbar.LENGTH_LONG
        ).setAction(getString(R.string.settings)) {
            // Responds to click on the action
            val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            val uri: Uri = Uri.fromParts("package", packageName, null)
            intent.data = uri
            startActivity(intent)
        }.show()
    }

    private fun openDialogBox() {
        try {
            val alertDialog = AlertDialog.Builder(this).create()
            alertDialog.setTitle(getString(R.string.set_profile))
            alertDialog.setMessage(getString(R.string.choose_any_option))
            alertDialog.setButton(
                AlertDialog.BUTTON_POSITIVE, getString(R.string.open_gallery)
            ) { dialog, which ->
                val pickPhoto = Intent(
                    Intent.ACTION_PICK,
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                )
                startActivityForResult(pickPhoto, OPEN_GALLERY)
            }
            alertDialog.setButton(
                AlertDialog.BUTTON_NEGATIVE, getString(R.string.take_picture)
            ) { dialog, which ->
                val takePicture = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                val photo = File(getExternalFilesDir(null), "Pic.jpg")
                imageUri =
                    FileProvider.getUriForFile(applicationContext, "$packageName.provider", photo)
                takePicture.putExtra(MediaStore.EXTRA_OUTPUT, imageUri)
                startActivityForResult(takePicture, TAKE_PHOTO)
            }
            alertDialog.show()
            val btnPositive = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE)
            val btnNegative = alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE)
            val layoutParams = btnPositive.layoutParams as LinearLayout.LayoutParams
            layoutParams.weight = 10f
            btnPositive.layoutParams = layoutParams
            btnNegative.layoutParams = layoutParams
        } catch (e: Exception) {
            mylog(TAG, "openDialogBox: Error=" + e.localizedMessage)
        }
    }

    fun getResizedBitmap(image: Bitmap, maxSize: Int): Bitmap {
        var width = image.width
        var height = image.height
        val bitmapRatio = width.toFloat() / height.toFloat()
        if (bitmapRatio > 1) {
            width = maxSize
            height = (width / bitmapRatio).toInt()
        } else {
            height = maxSize
            width = (height * bitmapRatio).toInt()
        }
        return Bitmap.createScaledBitmap(image, width, height, true)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, imageReturnedIntent: Intent?) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent)
        when (requestCode) {
            TAKE_PHOTO -> if (resultCode == RESULT_OK) {
                try {
                    var selectedImage = MediaStore.Images.Media.getBitmap(contentResolver, imageUri)
                    selectedImage = getResizedBitmap(
                        selectedImage,
                        400
                    ) // 400 is for example, replace with desired size
                    Glide.with(applicationContext).asBitmap().placeholder(R.drawable.ic_profile)
                        .load(selectedImage).into(mIvProfileImage!!)
                    convertBitmapToFile(selectedImage)
                } catch (e: Exception) {
                    mylog(TAG, "onActivityResult: TAKE_PICTURE Errror=" + e.localizedMessage)
                }
            }

            OPEN_GALLERY -> if (resultCode == RESULT_OK) {
                try {
                    val imageUri = imageReturnedIntent!!.data
                    val imageStream = contentResolver.openInputStream(imageUri!!)
                    var selectedImage = BitmapFactory.decodeStream(imageStream)
                    selectedImage = getResizedBitmap(
                        selectedImage,
                        400
                    ) // 400 is for example, replace with desired size
                    Glide.with(applicationContext).asBitmap().placeholder(R.drawable.ic_profile)
                        .load(selectedImage).into(mIvProfileImage!!)
                    convertBitmapToFile(selectedImage)
                } catch (e: Exception) {
                    mylog(TAG, "onActivityResult: OPEN_GALLERY Errror=" + e.localizedMessage)
                }
            }
        }
    }

    private fun convertBitmapToFile(selectedImage: Bitmap) {
        try {
            //create a file to write bitmap data
            val file = File(cacheDir, "ProfilePic")
            file.createNewFile()

            //Convert bitmap to byte array
            val bos = ByteArrayOutputStream()
            selectedImage.compress(Bitmap.CompressFormat.PNG, 70 /*ignored for PNG*/, bos)
            val bitmapdata = bos.toByteArray()

            //write the bytes in file
            val fos = FileOutputStream(file)
            fos.write(bitmapdata)
            fos.flush()
            fos.close()


            if (file != null) {
                val requestFile: RequestBody =
                    RequestBody.create("image/png".toMediaTypeOrNull(), file)
                mylog(TAG, "convertBitmapToFile: file.getName()=" + file.name)
                // MultipartBody.Part is used to send also the actual file name
                body = MultipartBody.Part.createFormData("ProfilePic", file.name + ".png", requestFile)
            } else mylog(TAG, "convertBitmapToFile: file is null")

        } catch (e: Exception) {
            mylog(TAG, "convertBitmapToFile: Error=" + e.localizedMessage)
        }
    }

    private fun updateInformation() {
        try {
            if (isNetworkAvailable(this)) {
                val FName: RequestBody = RequestBody.create(
                    "multipart/form-data".toMediaTypeOrNull(),
                    mEdFirstName!!.text.toString()
                )
                val LName: RequestBody = RequestBody.create(
                    "multipart/form-data".toMediaTypeOrNull(),
                    mEdLastName!!.text.toString()
                )
                val gender: RequestBody = RequestBody.create(
                    "multipart/form-data".toMediaTypeOrNull(),
                    mSpGender!!.selectedItem.toString()
                )
                val email: RequestBody = RequestBody.create(
                    "multipart/form-data".toMediaTypeOrNull(),
                    mEdEmail!!.text.toString()
                )
                val referedby: RequestBody = RequestBody.create(
                    "multipart/form-data".toMediaTypeOrNull(),
                    mEdReferralCode!!.text.toString()
                )

                val device_token: RequestBody = RequestBody.create(
                    "multipart/form-data".toMediaTypeOrNull(),
                    getPreference(this, Constants.deviceToken)
                )
                val office_add: RequestBody =
                    RequestBody.create("multipart/form-data".toMediaTypeOrNull(), officeAddress)
                val office_lat: RequestBody =
                    RequestBody.create("multipart/form-data".toMediaTypeOrNull(), officeLat)
                val office_lng: RequestBody =
                    RequestBody.create("multipart/form-data".toMediaTypeOrNull(), officeLng)
                val home_add: RequestBody =
                    RequestBody.create("multipart/form-data".toMediaTypeOrNull(), homeAddress)
                val home_lat: RequestBody =
                    RequestBody.create("multipart/form-data".toMediaTypeOrNull(), homeLat)
                val home_lng: RequestBody =
                    RequestBody.create("multipart/form-data".toMediaTypeOrNull(), homeLng)
                val home_Leave_Time: RequestBody =
                    RequestBody.create("multipart/form-data".toMediaTypeOrNull(), homeLeaveTime)
                val office_Leave_Time: RequestBody =
                    RequestBody.create("multipart/form-data".toMediaTypeOrNull(), officeLeaveTime)

                var socialid: RequestBody? = null
                var mode: RequestBody? = null
                if (isRegisteredWithSocial) {
                    socialid = RequestBody.create(
                        "multipart/form-data".toMediaTypeOrNull(),
                        socialLoginUserDetails!!.socialId
                    )
                    mode = RequestBody.create(
                        "multipart/form-data".toMediaTypeOrNull(),
                        socialLoginUserDetails!!.mode
                    )
                } else {
                    socialid = RequestBody.create("multipart/form-data".toMediaTypeOrNull(), "")
                    mode = RequestBody.create("multipart/form-data".toMediaTypeOrNull(), "")
                }
                updateHomeAndOfficeAddress()
                LoadingDialog.showLoadingDialog(this, getString(R.string.pls_wait_loading))


                homeFragmentViewModel?.let {
                    LoadingDialog.showLoadingDialog(this, getString(R.string.pls_wait_loading))

                    it.updateUserProfile(this,
                        getPreference(this, Constants.TOKEN),
                        body,
                        FName,
                        LName,
                        gender,
                        email,
                        referedby,
                        socialid,
                        mode,
                        device_token,
                        home_add,
                        home_lat,
                        home_lng,
                        office_add,
                        office_lat,
                        office_lng,
                        home_Leave_Time,
                        office_Leave_Time
                    )
                }

            } else toast(this, getString(R.string.txt_Network))
        } catch (e: Exception) {
            mylog(TAG, "updateInformation: Error=" + e.localizedMessage)
            LoadingDialog.cancelLoading()
        }
    }

    private fun updateHomeAndOfficeAddress() {
        try {
            homeFragmentViewModel?.let {
                LoadingDialog.showLoadingDialog(this, getString(R.string.pls_wait_loading))
                it.addOfficeRideAddress(
                    this,
                    getPreference(this, Constants.TOKEN),
                    homeLat,
                    homeLng,
                    homeAddress,
                    homeLeaveTime,
                    officeLat,
                    officeLng,
                    officeAddress,
                    officeLeaveTime
                )
            }
        } catch (e: java.lang.Exception) {
            mylog(TAG, "updateHomeAndOfficeAddress: Error=${e.localizedMessage}")
        }
    }

    /* validations */
    private fun validate(): Boolean {
        var flag = true
        if (TextUtils.isEmpty(mEdFirstName!!.text)) {
            flag = false
            toast(this, getString(R.string.msg_first_name))
        } else if (TextUtils.isEmpty(mEdLastName!!.text)) {
            flag = false
            toast(this, getString(R.string.msg_last_name))
        } else if (TextUtils.isEmpty(mEdEmail!!.text) || !isEmailValid(mEdEmail!!.text.toString())) {
            flag = false
            toast(this, getString(R.string.msg_email_valid))
        } else if (TextUtils.isEmpty(mEdContact!!.text)) {
            flag = false
            toast(this, getString(R.string.msg_mobile_number))
        }


        /*else if (TextUtils.isEmpty(mTvHomeAddress!!.text)) {
            flag = false
            toast(this, getString(R.string.msg_home_add))
        } else if (TextUtils.isEmpty(mTvOfficeAddress!!.text)) {
            flag = false
            toast(this, getString(R.string.msg_office_add))
        } else if (TextUtils.isEmpty(mTvHomeTime!!.text)) {
            flag = false
            toast(this, getString(R.string.msg_time))
        } else if (TextUtils.isEmpty(mTvOfficeTime!!.text)) {
            flag = false
            toast(this, getString(R.string.msg_time))
        }*/
        return flag
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onEventMainThread(pusher: UpdateLocationEvent) {
        try {
            mylog(TAG, "UpdateLocationEvent: " + pusher.locationData.title)
            mylog(TAG, "UpdateLocationEvent: " + pusher.updateFor)
            mylog(TAG, "UpdateLocationEvent: DATA=${Gson().toJson(pusher)}" + pusher.updateFor)
            if (pusher.updateFor == UPDATE_OFFICE) {
                officeAddress = pusher.locationData.title
                officeLat = pusher.locationData.locationLatitude.toString()
                officeLng = pusher.locationData.locationLongitude.toString()
                mTvOfficeAddress!!.setText(officeAddress)
            } else if (pusher.updateFor == UPDATE_HOME) {
                homeAddress = pusher.locationData.title
                homeLat = pusher.locationData.locationLatitude.toString()
                homeLng = pusher.locationData.locationLongitude.toString()
                mTvHomeAddress!!.setText(homeAddress)
            }

        } catch (e: Exception) {
            mylog(TAG, "setLocationData: ")
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }

    companion object {
        private const val TAKE_PHOTO = 102
        private const val OPEN_GALLERY = 103
    }
}