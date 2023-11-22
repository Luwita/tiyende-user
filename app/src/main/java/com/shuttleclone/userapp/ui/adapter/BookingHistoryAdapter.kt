package com.shuttleclone.userapp.ui.adapter

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Paint
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.PictureDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.shuttleclone.userapp.BaseActivity
import com.shuttleclone.userapp.R
import com.shuttleclone.userapp.model.GetbookingData
import com.shuttleclone.userapp.ui.activity.TrackRoutesActivity
import com.shuttleclone.userapp.ui.adapter.BookingHistoryAdapter.BookingViewHolder
import com.shuttleclone.userapp.ui.fragment.MyBookingFragment
import com.shuttleclone.userapp.utils.*
import com.shuttleclone.userapp.utils.Constants.*
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.rajat.pdfviewer.PdfViewerActivity
import de.hdodenhof.circleimageview.CircleImageView


class BookingHistoryAdapter(
    private val mCtx: Context,
    private val listner: MyBookingFragment,
    private val mBookList: List<GetbookingData>
) : RecyclerView.Adapter<BookingViewHolder>() {
    private val TAG = "BookingAdapter"

    /*  inflate layout */
    @SuppressLint("InflateParams")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookingViewHolder {
        return BookingViewHolder(LayoutInflater.from(mCtx).inflate(R.layout.item_booking, null))
    }

    /*bind viewholder*/
    override fun onBindViewHolder(holder1: BookingViewHolder, position: Int) {
        try {


            val bookingData = mBookList[position]

            holder1.mTvDestination.text =
                "${bookingData.pickupName} ${mCtx.getString(R.string.txt_to)} ${bookingData.dropName}"
            holder1.mTvDuration.text = convertDateToBeautify(bookingData.startDate!!.toString())
            holder1.mTvStartTime.text = bookingData.startTime
            holder1.mTvSeatNo.text = bookingData.seatNos.toString() + ""
            holder1.mTvTotalTime.text = diffTime(bookingData.startTime!!, bookingData.dropTime!!)
            holder1.mTvEndTime.text = bookingData.dropTime
            holder1.mTvPassengersNo.text = bookingData.passengers
            holder1.mTvPNRNo.text = bookingData.pnrNo
            holder1.mTvConfirm.text = bookingData.travelStatus
            holder1.mTvBusNo.text = bookingData.busModelNo
            holder1.mTvBusName.text = bookingData.busName



            if (!bookingData.discount.equals("0")) {
                holder1.mTvTotalFareBeforeDiscount!!.text =
                    String.format(
                        "%s%s",
                        getPreference(mCtx, DEFAULT_CURRENCY),
                        bookingData.finalTotalFare
                    )
                holder1.mTvTotalFareBeforeDiscount!!.paintFlags =
                    holder1.mTvTotalFareBeforeDiscount!!.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                try {
                    (mCtx as BaseActivity).showView(holder1.mTvTotalFareBeforeDiscount)
                    val amount =
                        bookingData.finalTotalFare!!.toInt() - bookingData.discount!!.toInt()
                    holder1.mTvTotalFare!!.text =
                        String.format("%s%s", getPreference(mCtx, DEFAULT_CURRENCY), amount)
                } catch (e: Exception) {
                    mylog(TAG, "showData: discount Error=${e.localizedMessage}")
                }
            } else {
                holder1.mTvTotalFare.text =
                    String.format(
                        "%s%s",
                        getPreference(mCtx, DEFAULT_CURRENCY),
                        bookingData.finalTotalFare
                    )
            }
            try {


                when (bookingData.travelStatus) {
                    SCHEDULED, ONBOARDED -> {
                        holder1.mTvConfirm.setTextColor(
                            mCtx.getResources().getColor(R.color.color_check)
                        )

                        bookingData.driverInfo?.let {
                            if (it.driverName != null && it.driverNo != null) {
                                holder1.mLayCallDriver.isVisible = true
                                holder1.mTvDriverName.text = it.driverName
                                Glide.with(mCtx).load(bookingData.driverInfo.driverPic)
                                    .into(holder1.mIvDriverPic)
                            } else holder1.mLayCallDriver.isVisible = false

                        }

                        (mCtx as BaseActivity).showView(holder1.mIvShowMore)
                        mCtx.hideView(holder1.mRlShowMore)
                        mCtx.fadeOutIn(holder1.mIvShowMore)
                        mCtx.showView(holder1.mTvConfirm)
                        mCtx.fadeOutIn(holder1.mTvConfirm)
                        mCtx.showView(holder1.mTvCancelBooking)
                        mCtx.showView(holder1.mTvTrackBooking)
                        mCtx.showView(holder1.mTvInvoiceAlert)
                        if (bookingData.travelStatus.equals(ONBOARDED)) mCtx.hideView(holder1.mTvCancelBooking)
                        else mCtx.showView(holder1.mTvCancelBooking)
                    }

                    EXPIRED, CANCELLED -> {
                        holder1.mTvConfirm.setTextColor(mCtx.getResources().getColor(R.color.red))
                        holder1.mLayCallDriver.isVisible = false
                        (mCtx as BaseActivity).showView(holder1.mIvShowMore)
                        mCtx.hideView(holder1.mRlShowMore)
                        mCtx.fadeOutIn(holder1.mIvShowMore)
                        mCtx.showView(holder1.mTvConfirm)
                        mCtx.fadeOutIn(holder1.mTvConfirm)
                        mCtx.hideView(holder1.mTvCancelBooking)
                        mCtx.hideView(holder1.mTvTrackBooking)
                        mCtx.hideView(holder1.mTvInvoiceAlert)
                        mCtx.hideView(holder1.mTvDownloadInvoice)
                        mCtx.hideView(holder1.mRlShowMore)
                        holder1.mIvShowMore.visibility = View.INVISIBLE
                    }

                    COMPLETED -> {
                        holder1.mTvConfirm.setTextColor(
                            mCtx.getResources().getColor(R.color.color_check)
                        )
                        holder1.mLayCallDriver.isVisible = false

                        (mCtx as BaseActivity).showView(holder1.mIvShowMore)
                        mCtx.hideView(holder1.mRlShowMore)
                        mCtx.fadeOutIn(holder1.mIvShowMore)
                        mCtx.showView(holder1.mTvConfirm)
                        mCtx.fadeOutIn(holder1.mTvConfirm)
                        mCtx.hideView(holder1.mTvCancelBooking)
                        mCtx.hideView(holder1.mTvTrackBooking)
                        mCtx.hideView(holder1.mTvInvoiceAlert)
                        mCtx.showView(holder1.mTvDownloadInvoice)
                    }

                    else -> {
                        holder1.mTvConfirm.setTextColor(
                            mCtx.getResources().getColor(R.color.rating)
                        )
                        holder1.mLayCallDriver.isVisible = false
                        (mCtx as BaseActivity).showView(holder1.mIvShowMore)
                        mCtx.hideView(holder1.mRlShowMore)
                        mCtx.fadeOutIn(holder1.mIvShowMore)
                        mCtx.showView(holder1.mTvConfirm)
                        mCtx.fadeOutIn(holder1.mTvConfirm)
                        mCtx.hideView(holder1.mTvCancelBooking)
                        mCtx.hideView(holder1.mTvTrackBooking)
                        mCtx.hideView(holder1.mTvInvoiceAlert)
                        mCtx.hideView(holder1.mTvDownloadInvoice)
                        holder1.mIvShowMore.visibility = View.INVISIBLE
                    }

                }


                when (bookingData.travelStatus) {
                    SCHEDULED, ONBOARDED -> {
                        if (!bookingData.png_string.equals("")) {
                            val qrCodeFile = svgFromString(bookingData.png_string!!)
                            Glide.with((mCtx as BaseActivity))
                                .load(qrCodeFile)
                                .placeholder(R.drawable.qr_code)
                                .into(holder1.mIvQr)
                            holder1.mIvQr.setOnClickListener { showFullCode(qrCodeFile) }
                        } else {
                            Glide.with((mCtx as BaseActivity))
                                .load(R.drawable.qr_code)
                                .into(holder1.mIvQr)
                            holder1.mIvQr.visibility=View.GONE
                        }
                    }
                    else->holder1.mIvQr.visibility=View.GONE
                }


            } catch (e: Exception) {
                mylog(TAG, "onBindViewHolder: Error=" + e.localizedMessage)
            }


            holder1.mRlContent.setOnClickListener {
                when (bookingData.travelStatus) {
                    SCHEDULED, ONBOARDED, COMPLETED -> {
                        (mCtx as BaseActivity).showView(holder1.mRlShowMore)
                        mCtx.hideView(holder1.mIvShowMore)
                        mCtx.hideView(holder1.mTvConfirm)
                    }
                }

            }

            holder1.mFabCall.clickWithThrottle {
                bookingData.driverInfo?.let {
                    listner.callDriver(it.driverNo)
                }
            }

            holder1.mRlShowMore.setOnClickListener {
                when (bookingData.travelStatus) {
                    SCHEDULED, ONBOARDED, COMPLETED -> {
                        (mCtx as BaseActivity).showView(holder1.mIvShowMore)
                        mCtx.hideView(holder1.mRlShowMore)
                        mCtx.fadeOutIn(holder1.mIvShowMore)
                        mCtx.showView(holder1.mTvConfirm)
                        mCtx.fadeOutIn(holder1.mTvConfirm)
                    }
                }

            }
            holder1.mTvReminder.setOnClickListener {
                listner.setReminder(bookingData.pnrNo, bookingData.startDate)
            }

            holder1.mTvCancelBooking.setOnClickListener {
                vibratePhone(mCtx)
                listner.cancelBookingDialog(bookingData.pnrNo)
            }

            holder1.mTvTrackBooking.setOnClickListener {
                try {
                    vibratePhone(mCtx)
                    val intent = Intent(mCtx, TrackRoutesActivity::class.java)
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    intent.putExtra("pnrNo", bookingData.pnrNo)
                    intent.putExtra("routeId", bookingData.routeId)
                    intent.putExtra("pickupId", bookingData.pickupId)
                    intent.putExtra("dropId", bookingData.dropoffId)
                    mCtx.startActivity(intent)
                } catch (e: Exception) {
                    mylog("Booking", "onBindViewHolder: Error=${e.localizedMessage}")
                }
            }

            holder1.mTvDownloadInvoice.setOnClickListener {
                try {
                    vibratePhone(mCtx)
                    mCtx.startActivity(
                        PdfViewerActivity.launchPdfFromUrl(           //PdfViewerActivity.Companion.launchPdfFromUrl(..   :: incase of JAVA
                            mCtx,
                            "${bookingData.invoiceUrl}",                                // PDF URL in String format
                            "Invoice_${bookingData.pnrNo}",                        // PDF Name/Title in String format
                            "",                  // If nothing specific, Put "" it will save to Downloads
                            enableDownload = true                    // This param is true by defualt.
                        )
                    )
                } catch (e: Exception) {
                    mylog("Booking", "onBindViewHolder: Error=${e.localizedMessage}")
                }
            }

        } catch (e: Exception) {
            mylog(TAG, "onBindViewHolder: Error=${e.localizedMessage}")
        }

    }


    private fun showFullCode(imageFile: PictureDrawable) {
        try {
            vibratePhone(mCtx)
            val dialog = Dialog(mCtx as BaseActivity)
            dialog.setContentView(R.layout.dialog_full_qrcode)
            dialog.setCancelable(true)
            dialog.window!!.setBackgroundDrawable(ColorDrawable(0))
            val ivFullCode = dialog.findViewById<ImageView>(R.id.ivFullCode)

            Glide.with(mCtx as BaseActivity)
                .load(imageFile)
                .placeholder(R.drawable.qr_code)
                .into(ivFullCode)
            dialog.show()

        } catch (e: Exception) {
            mylog(TAG, "showFullCode: Error=" + e.localizedMessage)
        }
    }

    /*item count*/
    override fun getItemCount(): Int {
        return if (null!=mBookList){
            return if (mBookList.isNotEmpty())
                mBookList.size
            else 0
        }else 0
    }

    /*view holder*/
    inner class BookingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mTvDestination: TextView
        val mTvDuration: TextView
        val mTvStartTime: TextView
        val mTvTotalTime: TextView
        val mTvEndTime: TextView
        val mTvPassengersNo: TextView
        val mTvPNRNo: TextView
        val mTvTotalFare: TextView
        val mTvTotalFareBeforeDiscount: TextView
        val mTvSeatNo: TextView
        val mTvConfirm: TextView
        val mTvReminder: TextView
        val mTvBusNo: TextView
        val mTvBusName: TextView
        val mTvDownloadInvoice: TextView
        val mTvInvoiceAlert: TextView
        val mTvCancelBooking: TextView
        val mTvTrackBooking: TextView
        val mRlShowMore: RelativeLayout
        val mRlContent: RelativeLayout
        val mIvShowMore: ImageView
        val mIvQr: ImageView
        val mLayCallDriver: LinearLayout
        val mTvDriverName: TextView
        val mFabCall: FloatingActionButton
        val mIvDriverPic: CircleImageView

        init {
            mLayCallDriver = itemView.findViewById(R.id.layCallDriver)
            mTvDriverName = itemView.findViewById(R.id.tvDriverName)
            mFabCall = itemView.findViewById(R.id.fabCall)
            mTvDownloadInvoice = itemView.findViewById(R.id.tvDownloadInvoice)
            mTvInvoiceAlert = itemView.findViewById(R.id.tvInvoiceAlert)
            mTvDestination = itemView.findViewById(R.id.tvDestination)
            mTvDuration = itemView.findViewById(R.id.tvDuration)
            mTvStartTime = itemView.findViewById(R.id.tvStartTime)
            mTvBusNo = itemView.findViewById(R.id.tvBusNo)
            mTvBusName = itemView.findViewById(R.id.tvBusName)
            mTvTotalTime = itemView.findViewById(R.id.tvTotalTime)
            mTvEndTime = itemView.findViewById(R.id.tvEndTime)
            mTvPassengersNo = itemView.findViewById(R.id.tvPassengersNo)
            mTvPNRNo = itemView.findViewById(R.id.tvPNRNo)
            mTvTotalFare = itemView.findViewById(R.id.tvTotalFare)
            mTvTotalFareBeforeDiscount = itemView.findViewById(R.id.tvTotalFareBeforeDiscount)
            mIvShowMore = itemView.findViewById(R.id.ivShowMore)
            mRlShowMore = itemView.findViewById(R.id.rlShowMore)
            mRlContent = itemView.findViewById(R.id.rlContent)
            mTvSeatNo = itemView.findViewById(R.id.tvSeatNo)
            mTvConfirm = itemView.findViewById(R.id.tvConfirmed)
            mTvReminder = itemView.findViewById(R.id.tv_set_reminder)
            mIvQr = itemView.findViewById(R.id.ivQr)
            mTvCancelBooking = itemView.findViewById(R.id.tvCancelBooking)
            mTvTrackBooking = itemView.findViewById(R.id.tvTrackBooking)
            mIvDriverPic = itemView.findViewById(R.id.ivDriverPic)
        }
    }
}