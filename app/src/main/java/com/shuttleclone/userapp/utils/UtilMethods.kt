package com.shuttleclone.userapp.utils

import android.Manifest
import android.animation.ValueAnimator
import android.app.Activity
import android.app.Dialog
import android.app.Service
import android.content.ClipData
import android.content.Context
import android.content.Context.VIBRATOR_SERVICE
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.graphics.drawable.PictureDrawable
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.NetworkInfo
import android.os.Build
import android.os.SystemClock
import android.os.VibrationEffect
import android.os.Vibrator
import android.provider.Settings
import android.text.ClipboardManager
import android.util.Log
import android.view.View
import android.view.animation.AnimationUtils
import android.view.animation.LinearInterpolator
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.RecyclerView
import com.amulyakhare.textdrawable.TextDrawable
import com.amulyakhare.textdrawable.util.ColorGenerator
import com.caverock.androidsvg.SVG
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.gson.Gson
import com.razorpay.Checkout
import com.shuttleclone.userapp.R
import com.shuttleclone.userapp.retrofitRepository.ApiCallBack
import com.shuttleclone.userapp.retrofitRepository.RetrofitClient
import com.shuttleclone.userapp.model.CommonData
import com.shuttleclone.userapp.model.DeviceInfoDataModel
import com.shuttleclone.userapp.model.InitiateData
import com.shuttleclone.userapp.model.RefreshTokenModel
import com.shuttleclone.userapp.model.RzPayData
import com.shuttleclone.userapp.ui.activity.DashboardActivity
import com.shuttleclone.userapp.ui.activity.SelectionActivity
import com.shuttleclone.userapp.utils.Constants.DateFormat.yyyy_MM_dd
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import org.json.JSONObject
import java.text.DateFormat
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.concurrent.TimeUnit
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.pow
import kotlin.math.sin
import kotlin.math.sqrt


private const val TAG = "UtilMethods"

fun getPreference(context: Context?, key: String?): String {
    return PreferenceManager.getDefaultSharedPreferences(context!!).getString(key, "").toString()
}

fun savePreference(context: Context?, key: String?, value: String?) {
    PreferenceManager.getDefaultSharedPreferences(context!!).edit().putString(key, value).apply()
}


fun isPreference(context: Context?, key: String?): Boolean {
    return PreferenceManager.getDefaultSharedPreferences(context!!).getBoolean(key, false)
}


fun savePreference(context: Context?, key: String?, value: Boolean) {
    PreferenceManager.getDefaultSharedPreferences(context!!).edit().putBoolean(key, value).apply()
}

fun setTextImage(name: String): TextDrawable {
    //get first letter of each String item
    val firstLetter = name[0].toString()
    val generator = ColorGenerator.MATERIAL // or use DEFAULT
    // generate random color
    val color = generator.randomColor
    return TextDrawable.builder()
        .buildRound(firstLetter, color)
}

/*show custom toast*/

fun toast(context: Context?, aContent: String? = "Please check your internet Connection") {
    val mToast = CustomToast(context)
    mToast.setCustomView(aContent)
    mToast.duration = Toast.LENGTH_SHORT
    mToast.show()
}

fun goHome(context: Context?) {
    val intent = Intent(context, DashboardActivity::class.java)
    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
    context!!.startActivity(intent)
}

/*show Log*/

fun mylog(logActivity: String?, log: String) {
    Log.i(logActivity, "printLog: LOG--> $log")
}


fun apiResponse(dataClass: Any?): String {
    return Gson().toJson(dataClass)
}

fun showKeyboard(context: Context) {
    val inputMethodManager =
        context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
}

fun closeKeyboard(context: Context) {
    val inputMethodManager =
        context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0)
}

fun View.clickWithThrottle(debounceTime: Long = 500L, action: () -> Unit) {
    this.setOnClickListener(object : View.OnClickListener {
        private var lastClickTime: Long = 0

        override fun onClick(v: View) {
            if (SystemClock.elapsedRealtime() - lastClickTime < debounceTime) return
            else action()

            lastClickTime = SystemClock.elapsedRealtime()
        }
    })
}


fun checkToken(mContext: Context, apiCallBack: ApiCallBack) {
    RetrofitClient.getClient().refreshToken(
        getPreference(mContext, Constants.PHONE_NO),
        getPreference(mContext, Constants.csrfTOKEN),
        Constants.OnMODEL
    ).subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeWith(object : DisposableSingleObserver<RefreshTokenModel?>() {
            override fun onSuccess(response: RefreshTokenModel) {
                mylog(TAG, "onSuccess: response=" + Gson().toJson(response))
                if (response.status!!) {
                    savePreference(mContext, Constants.csrfTOKEN, response.data?.csrfToken)
                    savePreference(mContext, Constants.TOKEN, "Bearer " + response.data?.token)

                    apiCallBack.onResponse(true)

                } else apiCallBack.onResponse(false)
            }

            override fun onError(e: Throwable) {
                mylog(TAG, "onError: checkToken Error=" + e.localizedMessage)
                toast(mContext, e.message)
                apiCallBack.onResponse(false)
            }
        })

}

fun sessionExpireDialog(context: Context) {
    try {
        val dialogBuilder = AlertDialog.Builder(context)

        dialogBuilder.setMessage(context.getString(R.string.session_expire_msg))
            .setCancelable(false)
            .setPositiveButton(
                context.getString(R.string.ok),
                DialogInterface.OnClickListener { dialog, id ->
                    savePreference(context, Constants.FirstTimeUser, "NO")
                    savePreference(context, Constants.csrfTOKEN, "")
                    savePreference(context, Constants.TOKEN, "")
                    savePreference(context, Constants.IsUserRegistered, false)
                    savePreference(context, Constants.REGISTER_WITH_SOCIAL, false)
                    savePreference(context, Constants.SOCIAL_USER_DETAILS, "")
                    context.startActivity(Intent(context, SelectionActivity::class.java))
                    (context as Activity).finishAffinity()
                })

        val alert = dialogBuilder.create()
        alert.setTitle(context.getString(R.string.session_expire))
        if (!alert.isShowing)
            alert.show()
    } catch (e: Exception) {
        mylog("SessionExpire", "sessionExpireDialog: Error=${e.localizedMessage}")
    }
}

fun getLocationName(
    latitude: Double,
    longitude: Double,
    mContext: Context
): String? {
    try {
        val geocoder: Geocoder
        val addresses: List<Address>
        geocoder = Geocoder(mContext, Locale.getDefault())
        addresses = geocoder.getFromLocation(
            latitude,
            longitude,
            1
        )!! // Here 1 represent max location result to returned, by documents it recommended 1 to 5


        val address: String = addresses[0]
            .getAddressLine(0) // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
        val city: String = addresses[0].getLocality()
        val state: String = addresses[0].getAdminArea()
        val country: String = addresses[0].getCountryName()

        savePreference(mContext, Constants.CURRENT_CITY, city)
        savePreference(mContext, Constants.CURRENT_STATE, state)
        mylog("TAG", "getLocationName: address=$address")
        mylog("TAG", "**************************************")

        return address
    } catch (e: Exception) {
        mylog("TAG", "getLocationName: Error=" + e.message)
    }
    return "Unknown"
}

fun getZoneName(
    latitude: Double,
    longitude: Double,
    mContext: Context
): String? {
    try {
        val geocoder: Geocoder
        val addresses: List<Address>
        geocoder = Geocoder(mContext, Locale.getDefault())
        addresses = geocoder.getFromLocation(
            latitude,
            longitude,
            1
        )!! // Here 1 represent max location result to returned, by documents it recommended 1 to 5


        val address: String = addresses[0]
            .getAddressLine(0) // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
        val city: String = addresses[0].getLocality()
        val state: String = addresses[0].getAdminArea()
        val country: String = addresses[0].getCountryName()


        mylog("TAG", "ZONE:=${city + "_" + state}")
        return city + "_" + state
    } catch (e: Exception) {
        mylog("TAG", "getLocationName: Error=" + e.message)
    }
    return "Unknown"
}


/*fun isNetworkAvailable(mContext: Context): Boolean {
    val conManager = mContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val internetInfo = conManager.activeNetworkInfo
    return internetInfo != null && internetInfo.isConnected
}*/

fun isNetworkAvailable(context: Context): Boolean {
    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        val nw      = connectivityManager.activeNetwork ?: return false
        val actNw = connectivityManager.getNetworkCapabilities(nw) ?: return false
        return when {
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            //for other device how are able to connect with Ethernet
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            //for check internet over Bluetooth
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_BLUETOOTH) -> true
            else -> false
        }
    } else {
        return connectivityManager.activeNetworkInfo?.isConnected ?: false
    }
}

fun checkNetwork(mContext: Context): Boolean {

    val connectivity =
        mContext.getSystemService(Service.CONNECTIVITY_SERVICE) as ConnectivityManager

    if (connectivity != null) {
        val info = connectivity!!.activeNetworkInfo

        if (info != null) {
            if (info!!.state == NetworkInfo.State.CONNECTED) {
                Toast.makeText(mContext, "CONNECTED", Toast.LENGTH_LONG).show()
                return true
            }
        } else {
            Toast.makeText(mContext, "NOT CONNECTED", Toast.LENGTH_LONG).show()
            return false
        }
    }

    return false
}

fun setClipboard(context: Context, text: String) {
    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
        val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        clipboard.text = text
    } else {
        val clipboard =
            context.getSystemService(Context.CLIPBOARD_SERVICE) as android.content.ClipboardManager
        val clip = ClipData.newPlainText("Copied Text", text)
        clipboard.setPrimaryClip(clip)
    }
}

fun convertDateToBeautify(putdate: Date): String {
    val formatter = SimpleDateFormat("yyyy-MM-dd")
    val output: String = formatter.format(putdate)
    return output
}

fun increaseDaysInCalendar(days: Int): Calendar {
    val calendar = Calendar.getInstance()
    calendar.add(Calendar.DAY_OF_YEAR, days)
    return calendar
}

fun convertTimeToBeautify(time: Date): String {
    val formatter = SimpleDateFormat("HH:mm")
    val output: String = formatter.format(time)
    return output
}

fun calculateNext3rdDate(mDepartDateCalendar: Calendar): String {
    return try {
        val cal = Calendar.getInstance()
        cal.time = mDepartDateCalendar.time
        cal.add(Calendar.DATE, 3)
        Constants.DateFormat.YEAR_MONTH_DAY_FORMATTER.format(cal.time)
    } catch (e: Exception) {
        mylog(TAG, "calculateNext3rdDate: Error=${e.localizedMessage}")
        "N/A"
    }
}

fun vibratePhone(context: Context) {
    if (Build.VERSION.SDK_INT >= 26) {
        (context.getSystemService(VIBRATOR_SERVICE) as Vibrator).vibrate(
            VibrationEffect.createOneShot(
                150,
                VibrationEffect.DEFAULT_AMPLITUDE
            )
        )
    } else {
        (context.getSystemService(VIBRATOR_SERVICE) as Vibrator).vibrate(150)
    }
}


fun convertDateToBeautify(putdate: String): String? {
    try {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd")
        val outputFormat: DateFormat = SimpleDateFormat("EEE, d MMM yyyy")
        val date = inputFormat.parse(putdate)
        return outputFormat.format(date)
    } catch (e: Exception) {
        mylog(TAG, "convertDateToBeautify: Error=${e.localizedMessage}")
        return "--------"
    }

}


fun diffTime(time1: String, time2: String): String {
    var totalHours = ""
    val difference: Long
    try {
        val simpleDateFormat =
            SimpleDateFormat("HH:mm") // for 12-hour system, hh should be used instead of HH
        // There is no minute different between the two, only 8 hours difference. We are not considering Date, So minute will always remain 0
        val date1 = simpleDateFormat.parse(time1)
        val date2 = simpleDateFormat.parse(time2)
        difference = (date2.time - date1.time) / 1000
        val hours = Math.abs((difference % (24 * 3600) / 3600)) // Calculating Hours
        var minute =
            Math.abs(difference % 3600 / 60) // Calculating minutes if there is any minutes difference

        totalHours =
            "${if (hours < 10) "0$hours" else hours}:${if (minute < 10) "0$minute" else minute} hr"// This will be our final minutes. Multiplying by 60 as 1 hour contains 60 mins
    } catch (e: Throwable) {
        e.printStackTrace()
    }
    return totalHours
}

/*Animation*/
fun RunLayoutAnimation(context: Context, recyclerView: RecyclerView) {
    val controller = AnimationUtils.loadLayoutAnimation(context, R.anim.anim_up_to_down)
    recyclerView.layoutAnimation = controller
    recyclerView.adapter!!.notifyDataSetChanged()
    recyclerView.scheduleLayoutAnimation()
}

fun getCountOfDays(createdDateString: String?, bookingDateString: String?): String? {
    try {
        val myFormat = SimpleDateFormat(yyyy_MM_dd)
        val date1 = myFormat.parse(createdDateString)
        val date2 = myFormat.parse(bookingDateString)
        val diff = date2.time - date1.time
        System.out.println("Days: " + TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS))
        return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS).toString()
    } catch (e: Exception) {
        e.printStackTrace()
        return "null"
    }
}

fun startPassPayment(data: RzPayData, application: Context, amount: String): JSONObject? {
    try {
        // initialize Razorpay account.
        val checkout = Checkout()
        checkout.setKeyID(data.key!!)

        // set image
//        checkout.setImage(R.mipmap.ic_launcher)
        val options = JSONObject()
        options.put("name", data.name)
        options.put("description", "Reference No. #123456")
        options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.png")
        options.put("order_id", "orderId");//from response of step 3.
        options.put("theme.color", "#3399cc")
        options.put("currency", "INR")
        options.put("amount", amount) //pass amount in currency subunits
        options.put("prefill.email", getPreference(application, Constants.EMAIL))
        options.put("prefill.contact", getPreference(application, Constants.PHONE_NO))
        options.put("send_sms_hash", true)
        options.put("allow_rotation", false)
        val retryObj = JSONObject()
        retryObj.put("enabled", false)
        retryObj.put("max_count", 0)
        options.put("retry", retryObj)

        mylog(TAG, "makePayment options: $options")
        return options
    } catch (e: Exception) {
        mylog(TAG, "makePayment: Error=" + e.localizedMessage)
    }
    return null
}

fun makePayment(data: InitiateData): JSONObject? {
    try {
        // initialize Razorpay account.
        val checkout = Checkout()
        checkout.setKeyID(data.paymentSettings!!.key)

        // set image
//        checkout.setImage(R.mipmap.ic_launcher)
        val options = JSONObject()
        options.put("name", data.paymentSettings!!.textName)
        options.put("description", data.paymentSettings!!.description)
        options.put("image", data.paymentSettings!!.logo)
        options.put("order_id", data.orderId);//from response of step 3.
        options.put("theme.color", data.paymentSettings!!.themeColor)
        options.put("currency", data.paymentSettings!!.currency)
        options.put("amount", data.amount) //pass amount in currency subunits
        options.put("prefill.email", data.prefill!!.email)
        options.put("prefill.contact", data.prefill!!.contact)
        options.put("send_sms_hash", true)
        options.put("allow_rotation", false)
        val retryObj = JSONObject()
        retryObj.put("enabled", false)
        retryObj.put("max_count", 0)
        options.put("retry", retryObj)
        return options
    } catch (e: Exception) {
        mylog(TAG, "makePayment: Error=" + e.localizedMessage)
    }
    return null
}

fun getMarkerIconFromDrawable(drawable: Drawable): BitmapDescriptor? {
    val canvas = Canvas()
    val bitmap = Bitmap.createBitmap(
        drawable.intrinsicWidth,
        drawable.intrinsicHeight,
        Bitmap.Config.ARGB_8888
    )
    canvas.setBitmap(bitmap)
    drawable.setBounds(0, 0, drawable.intrinsicWidth, drawable.intrinsicHeight)
    drawable.draw(canvas)
    return BitmapDescriptorFactory.fromBitmap(bitmap)
}


fun animateMarker(destination: Location, marker: Marker?) {
    if (marker != null) {
        val startPosition = marker.position
        val endPosition = LatLng(destination.getLatitude(), destination.getLongitude())
        val startRotation = marker.rotation
        val latLngInterpolator: LatLngInterpolator = LatLngInterpolator.LinearFixed()
        val valueAnimator: ValueAnimator = ValueAnimator.ofFloat(0f, 1f)
        valueAnimator.setDuration(1000) // duration 1 second
        valueAnimator.setInterpolator(LinearInterpolator())
        valueAnimator.addUpdateListener(object : ValueAnimator.AnimatorUpdateListener {
            override fun onAnimationUpdate(animation: ValueAnimator) {
                try {
                    val v: Float = animation.getAnimatedFraction()
                    val newPosition: LatLng =
                        latLngInterpolator.interpolate(v, startPosition, endPosition)!!
                    marker.setPosition(newPosition)
                    marker.rotation =
                        computeRotation(v, startRotation, destination.getBearing())
                } catch (ex: java.lang.Exception) {
                    // I don't care atm..
                }
            }
        })
        valueAnimator.start()
    }
}

fun computeRotation(fraction: Float, start: Float, end: Float): Float {
    val normalizeEnd = end - start // rotate start to 0
    val normalizedEndAbs = (normalizeEnd + 360) % 360
    val direction: Float =
        if (normalizedEndAbs > 180) -1f else 1.toFloat() // -1 = anticlockwise, 1 = clockwise
    val rotation: Float
    rotation = if (direction > 0) {
        normalizedEndAbs
    } else {
        normalizedEndAbs - 360
    }
    val result = fraction * rotation + start
    return (result + 360) % 360
}

fun Context.hideKeyboard(view: View) {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}

fun checkAndRequestPermissions(mContext: Context?): Boolean {

    /*val camerapermission = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
    val writepermission = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)*/
    val locationpermission =
        ContextCompat.checkSelfPermission(mContext!!, Manifest.permission.ACCESS_FINE_LOCATION)

    val listPermissionsNeeded = ArrayList<String>()


    /* if (writepermission != PackageManager.PERMISSION_GRANTED) {
         listPermissionsNeeded.add(Manifest.permission.WRITE_EXTERNAL_STORAGE)
     }*/
    if (locationpermission != PackageManager.PERMISSION_GRANTED) {
        listPermissionsNeeded.add(Manifest.permission.ACCESS_FINE_LOCATION)
    }
    /*if (camerapermission != PackageManager.PERMISSION_GRANTED) {
        listPermissionsNeeded.add(Manifest.permission.CAMERA)
    }*/

    if (!listPermissionsNeeded.isEmpty()) {

        mylog("jjjjjjjjjjjjj", "dkfsakjdlf" + listPermissionsNeeded.size)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

        }
        return false
    }
    return true
}

fun successFailureDialog(context: Context?, isSuccess: Boolean?, msg: String) {
    val dialog = Dialog(context!!)
    if (isSuccess!!) dialog.setContentView(R.layout.dialog_success)
    else dialog.setContentView(R.layout.dialog_failure)
    dialog.setCancelable(true)
    dialog.window!!.setBackgroundDrawable(ColorDrawable(0))
    dialog.findViewById<TextView>(R.id.tvMsg).text = msg
    dialog.findViewById<View>(R.id.btnClose).setOnClickListener {
        if (dialog.isShowing) dialog.dismiss()
    }
    dialog.show()


}


fun svgFromString(png_string: String): PictureDrawable {
    return PictureDrawable(SVG.getFromString(png_string).renderToPicture())
}

fun subscribeOnBackground(function: () -> Unit) {
    Single.fromCallable {
        function()
    }
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe()
}

fun getDeviceId(context: Context): String? {
    return Settings.Secure.getString(
        context.contentResolver,
        Settings.Secure.ANDROID_ID
    )
}

private var mAlertDialog: Dialog? = null
fun alertDialog(mContext: Context, alert: Any) {
    try {
        if (mAlertDialog == null)
            mAlertDialog = Dialog(mContext)

        mAlertDialog!!.setContentView(R.layout.alert_dailog_layout)
        mAlertDialog!!.setCancelable(true)
        mAlertDialog!!.window?.setBackgroundDrawable(ColorDrawable(0))

        mAlertDialog!!.findViewById<TextView>(R.id.tvMsg).text=Gson().toJson(alert)

        mAlertDialog!!.findViewById<View>(R.id.btnOkay).setOnClickListener {

            if (mAlertDialog!!.isShowing) mAlertDialog!!.dismiss()

            mAlertDialog=null

//            mContext.finish()
        }
        mAlertDialog!!.setCancelable(false)
        mAlertDialog!!.setCanceledOnTouchOutside(false)

        if (!mAlertDialog!!.isShowing)
            mAlertDialog!!.show()
    } catch (e: java.lang.Exception) {
        mylog(TAG, "alertDialog: Error=${e.localizedMessage}")
    }
}

var dialogBuilder:AlertDialog.Builder?=null
var alertDialog:AlertDialog?=null
fun alertDialogs(mContext: Context, alert: Any) {
    try {
        if (dialogBuilder==null)
            dialogBuilder = AlertDialog.Builder(mContext)

        dialogBuilder!!.setTitle(mContext.getString(R.string.alert))
        dialogBuilder!!.setMessage(Gson().toJson(alert))
            .setCancelable(false)
            .setPositiveButton(
                mContext.getString(R.string.text_close),
                DialogInterface.OnClickListener { dialog, id ->
                    dialog.dismiss()
                    alertDialog=null
                    dialogBuilder=null
                })

        dialogBuilder!!.show()

        if (alertDialog==null)
            alertDialog = dialogBuilder!!.create()

        if (!alertDialog!!.isShowing)
            alertDialog!!.show()
    } catch (e: Exception) {
        mylog("alertDialog", "alertDialog: Error=${e.localizedMessage}")
    }
}

var df: DecimalFormat = DecimalFormat("###.##")
fun deg2rad(deg: Double): Double {
    return deg * (Math.PI / 180)
}
fun getDistanceFromLatLonInM(lat1: Double, lon1: Double, lat2: Double, lon2: Double): Double {
    var a = sin(deg2rad(lat2 - lat1) / 2).pow(2.0) +
            cos(deg2rad(lat1)) * cos(deg2rad(lat2)) *
            sin(deg2rad(lon2 - lon1) / 2).pow(2.0);
    return df.format((12742000 * atan2(sqrt(a), sqrt(1 - a)))/1000).toDouble()
}



fun isEmulator(): Boolean {
    return (Build.FINGERPRINT.startsWith("generic")
            || Build.FINGERPRINT.startsWith("unknown")
            || Build.MODEL.contains("google_sdk")
            || Build.MODEL.contains("Emulator")
            || Build.MODEL.contains("Android SDK built for x86")
            || Build.MANUFACTURER.contains("Genymotion"))
            || Build.BRAND.startsWith("generic")
            && Build.DEVICE.startsWith("generic")
            || "google_sdk" == Build.PRODUCT
}

fun getDeviceDetails(): String {
    return Gson().toJson(DeviceInfoDataModel("Android(${(Build.VERSION.SDK_INT)})",Build.BRAND,Build.MODEL,Build.VERSION.RELEASE))
}

fun getCommonDataDetails(context: Context): CommonData {
    return Gson().fromJson(getPreference(context,Constants.COMMON_DATA),CommonData::class.java)
}

fun isEmailValid(email: String): Boolean {
    val regexPattern = Regex("[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}")
    return regexPattern.matches(email)
}


fun convertTo24HourFormat(time12Hour: String): String {
    val inputFormat = SimpleDateFormat("hh:mm a")
    val outputFormat = SimpleDateFormat("HH:mm")

    val date: Date = inputFormat.parse(time12Hour) ?: return ""
    return outputFormat.format(date)
}