package com.shuttleclone.userapp.ui.adapter

import com.shuttleclone.userapp.model.GetbookingData
import com.shuttleclone.userapp.ui.adapter.CancelAdapter.CancelViewHolder
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Paint
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import com.shuttleclone.userapp.R
import com.shuttleclone.userapp.BaseActivity
import android.widget.TextView
import android.widget.RelativeLayout
import androidx.recyclerview.widget.RecyclerView
import com.shuttleclone.userapp.ui.fragment.MyBookingFragment
import com.shuttleclone.userapp.utils.Constants
import com.shuttleclone.userapp.utils.convertDateToBeautify
import com.shuttleclone.userapp.utils.diffTime
import com.shuttleclone.userapp.utils.getPreference
import com.shuttleclone.userapp.utils.mylog

class CancelAdapter(
    private val mCtx: Context,
    private val mCancelList: List<GetbookingData>,
    val listner: MyBookingFragment
) : RecyclerView.Adapter<CancelViewHolder>() {
    private val TAG = "CancelAdapter"
    /*  inflate layout */
    @SuppressLint("InflateParams")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CancelViewHolder {
        return CancelViewHolder(LayoutInflater.from(mCtx).inflate(R.layout.item_cancel, null))
    }

    /*bind viewholder*/
    override fun onBindViewHolder(holder1: CancelViewHolder, position: Int) {
        val bookingData = mCancelList[position]
        holder1.mTvDestination.text = "${bookingData.pickupName} ${mCtx.getString(R.string.txt_to)} ${bookingData.dropName}"
        holder1.mTvDuration.text = convertDateToBeautify(bookingData.startDate!!)
        holder1.mTvStartTime.text = bookingData.startTime
        holder1.mTvSeatNo.text = bookingData.seatNos.toString() + ""
        holder1.mTvTotalTime.text = diffTime(bookingData.startTime!!, bookingData.dropTime!!)
        holder1.mTvEndTime.text = bookingData.dropTime
        holder1.mTvPassengersNo.text = bookingData.passengers
        holder1.mTvPNRNo.text = bookingData.pnrNo

        if (!bookingData.discount.equals("0")) {
            holder1.mTvTotalFareBeforeDiscount!!.text = String.format("%s%s", getPreference(mCtx,Constants.DEFAULT_CURRENCY), bookingData.finalTotalFare)
            holder1.mTvTotalFareBeforeDiscount!!.paintFlags = holder1.mTvTotalFareBeforeDiscount!!.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
            try {
                (mCtx as BaseActivity).showView(holder1.mTvTotalFareBeforeDiscount)
                val amount = bookingData.finalTotalFare!!.toInt() - bookingData.discount!!.toInt()
                holder1.mTvTotalFare!!.text=String.format("%s%s", getPreference(mCtx,Constants.DEFAULT_CURRENCY), amount)
            } catch (e: Exception) {
                mylog(TAG, "showData: discount Error=${e.localizedMessage}")
            }
        } else {
            holder1.mTvTotalFare.text = String.format("%s%s", getPreference(mCtx,Constants.DEFAULT_CURRENCY), bookingData.finalTotalFare)
        }

        holder1.mRlContent.setOnClickListener(
            View.OnClickListener {
                (mCtx as BaseActivity).showView(holder1.mRlShowMore)
                mCtx.hideView(holder1.mIVShowMore)
                mCtx.hideView(holder1.mTvCancel)
            })
        holder1.mRlShowMore.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
                (mCtx as BaseActivity).showView(holder1.mIVShowMore)
                mCtx.showView(holder1.mTvCancel)
                mCtx.hideView(holder1.mRlShowMore)
                mCtx.fadeOutIn(holder1.mIVShowMore)
                mCtx.fadeOutIn(holder1.mTvCancel)
            }
        })

    }

    /*item count*/
    override fun getItemCount(): Int {
        return if (null!=mCancelList){
            return if (mCancelList.isNotEmpty())
                mCancelList.size
            else 0
        }else 0
    }

    /*view holder*/
    inner class CancelViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
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
        val mTvCancel: TextView
        val mRlShowMore: RelativeLayout
        val mRlContent: RelativeLayout
        val mIVShowMore: ImageView

        init {
            mTvDestination = itemView.findViewById(R.id.tvDestination)
            mTvDuration = itemView.findViewById(R.id.tvDuration)
            mTvStartTime = itemView.findViewById(R.id.tvStartTime)
            mTvTotalTime = itemView.findViewById(R.id.tvTotalTime)
            mTvEndTime = itemView.findViewById(R.id.tvEndTime)
            mTvPassengersNo = itemView.findViewById(R.id.tvPassengersNo)
            mTvPNRNo = itemView.findViewById(R.id.tvPNRNo)
            mTvTotalFareBeforeDiscount = itemView.findViewById(R.id.tvTotalFareBeforeDiscount)
            mTvTotalFare = itemView.findViewById(R.id.tvTotalFare)
            mIVShowMore = itemView.findViewById(R.id.ivShowMore)
            mRlShowMore = itemView.findViewById(R.id.rlShowMore)
            mRlContent = itemView.findViewById(R.id.rlContent)
            mTvSeatNo = itemView.findViewById(R.id.tvSeatNo)
            mTvCancel = itemView.findViewById(R.id.tvCancelled)
        }
    }
}