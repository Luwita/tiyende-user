package com.shuttleclone.userapp.ui.activity

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.shuttleclone.userapp.BaseActivity
import com.shuttleclone.userapp.R
import com.shuttleclone.userapp.model.PassesList
import com.shuttleclone.userapp.utils.Constants
import com.shuttleclone.userapp.utils.getPreference
import com.shuttleclone.userapp.utils.goHome
import com.shuttleclone.userapp.utils.mylog
import com.razorpay.Checkout
import com.razorpay.PaymentResultListener
import com.shuttleclone.userapp.utils.LocaleManager

class PassDetailsActivity : BaseActivity() , PaymentResultListener{

    private val TAG = "PassDetailsActivity"


    companion object{
        @JvmField
        var passDetails: PassesList?=null
    }

    override fun onResume() {
        super.onResume()
        LocaleManager().setLocale(this) //This method update the language when the activity orientation changes
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LocaleManager().setLocale(this) //This method update the language when the activity orientation changes
        setContentView(R.layout.activity_pass_details)
        Checkout.preload(applicationContext)

        val tvNoOfRides=findViewById<TextView>(R.id.tvNoOfRides)
        val tvNoOfDays=findViewById<TextView>(R.id.tvNoOfDays)
        val tvFarePerRide=findViewById<TextView>(R.id.tvFarePerRide)
        val tvActualFarePerRide=findViewById<TextView>(R.id.tvActualFarePerRide)
        val tvPassDescription=findViewById<TextView>(R.id.tvPassDescription)
        val tvOfficePickUp=findViewById<TextView>(R.id.tvOfficePickUp)
        val tvOfficeDrop=findViewById<TextView>(R.id.tvOfficeDrop)
        findViewById<View>(R.id.ivBack).setOnClickListener { onBackPressedDispatcher.onBackPressed() }

        findViewById<Button>(R.id.btnpay).setOnClickListener {
            if (passDetails!=null) {
                val intent = Intent(this, ConfirmPaymentActivity::class.java)
                intent.putExtra("comingFrom", "Pass")
                intent.putExtra("seatNo", getPreference(this,Constants.SEAT_NO))
                startActivity(intent)
            }
        }

        try {
            if (intent!=null){
                passDetails = intent.getSerializableExtra("passDetails") as PassesList
                tvActualFarePerRide.text= "${getPreference(this, Constants.DEFAULT_CURRENCY)}${passDetails!!.totalFare}"
                tvNoOfRides.text= "${passDetails!!.passNoOfRides} ${getString(R.string.rides)}"
                tvNoOfDays.text= "${passDetails!!.passNoOfValidDays} ${getString(R.string.days_validity)}"
                tvFarePerRide.text= "${getPreference(this, Constants.DEFAULT_CURRENCY)}${passDetails!!.totalsingleFare}/${getString(R.string.ride)}"
                tvOfficePickUp.text= getPreference(this,Constants.OFFICE_PICKUP_ADD)
                tvOfficeDrop.text= getPreference(this,Constants.OFFICE_DROP_ADD)

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    tvPassDescription.setText(
                        Html.fromHtml(
                            passDetails!!.passDescription+"\n\n"+passDetails!!.passTerms,
                            Html.FROM_HTML_MODE_COMPACT
                        )
                    );
                } else {
                    tvPassDescription.setText(Html.fromHtml(passDetails!!.passDescription+"\n\n"+passDetails!!.passTerms))
                }
            }
        }catch (e:Exception){
            mylog(TAG, "onCreate: Error=${e.localizedMessage}")
        }

        findViewById<View>(R.id.ivHome).setOnClickListener { goHome(applicationContext) }

    }



    override fun onPaymentSuccess(s: String) {
        // this method is called on payment success.
        Toast.makeText(this, "Payment is successful : $s", Toast.LENGTH_SHORT).show()
        mylog(TAG, "onPaymentSuccess: Response =$s")
    }

    override fun onPaymentError(i: Int, s: String) {
        // on payment failed.
        Toast.makeText(this, "Payment Failed due to error : $s", Toast.LENGTH_SHORT).show()
        mylog(TAG, "onPaymentError: Response=$s")
    }



}