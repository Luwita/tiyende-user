package com.shuttleclone.userapp.ui.adapter

import android.app.ActionBar
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.shuttleclone.userapp.R
import com.shuttleclone.userapp.ui.fragment.MyBookingFragment
import com.shuttleclone.userapp.ui.fragment.MyBookingFragment.Companion.isBookingCheckStatusEnabled
import com.shuttleclone.userapp.utils.Constants
import com.shuttleclone.userapp.utils.LocaleManager.Companion.ARABIC
import com.shuttleclone.userapp.utils.LocaleManager.Companion.ENGLISH
import com.shuttleclone.userapp.utils.getPreference

class TripStatusAdapter(val listner: MyBookingFragment, val tripStatusList: List<String>) : RecyclerView.Adapter<TripStatusAdapter.ViewHolder>()  {

    var selectedPos : Int = 0
    var row_index : Int = -1
    var mContext : Context?=null
    val arabicTripStatus = listOf("سجل الرحلات", "ألغيت", "على متن وسيلة المواصلات", "مكتملة", "منتهي الصلاحية")


    class ViewHolder(view : View):RecyclerView.ViewHolder(view) {
        val tvTripStatus=view.findViewById<TextView>(R.id.tvTripStatus)
        val cardView=view.findViewById<CardView>(R.id.cvTripStatus)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        mContext=parent.context
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.trip_status_item_adapter,parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.apply {
            if (getPreference(mContext,Constants.LANGUAGE).equals(ENGLISH))
               tvTripStatus.text=tripStatusList[position]
            else if (getPreference(mContext,Constants.LANGUAGE).equals(ARABIC))
                tvTripStatus.text=arabicTripStatus[position]
            else  tvTripStatus.text=tripStatusList[position]
        }


        holder.cardView.setOnClickListener(View.OnClickListener {
            if(isBookingCheckStatusEnabled) {
                notifyItemChanged(selectedPos)
                selectedPos = position
                notifyItemChanged(selectedPos)
            }
        })

        if (selectedPos === position) {

            holder.cardView.elevation = 10F
            val params = ActionBar.LayoutParams(
                RecyclerView.LayoutParams.WRAP_CONTENT,
                RecyclerView.LayoutParams.WRAP_CONTENT
            )
            params.setMargins(10, 10, 10, 10)
            holder.cardView.layoutParams = params
            holder.cardView.setBackgroundResource(R.drawable.selected_circle_bg)
            holder.tvTripStatus.setTextColor(listner.resources.getColor(R.color.white))
            listner.selectedStatus(tripStatusList[position])
        } else {
            holder.cardView.elevation = 0F
            val params = ActionBar.LayoutParams(
                RecyclerView.LayoutParams.WRAP_CONTENT,
                RecyclerView.LayoutParams.WRAP_CONTENT
            )
            params.setMargins(10, 10, 10, 10)
            holder.cardView.layoutParams = params
            holder.cardView.setBackgroundResource(R.drawable.circle_background)
            holder.tvTripStatus.setTextColor(listner.resources.getColor(R.color.dark_gray))
        }



    }

    override fun getItemCount(): Int {
        return if (null!=tripStatusList){
            return if (tripStatusList.isNotEmpty())
                tripStatusList.size
            else 0
        }else 0
    }
}