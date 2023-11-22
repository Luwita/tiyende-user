package com.shuttleclone.userapp.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import com.shuttleclone.userapp.R
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.shuttleclone.userapp.model.NotificationsDataItem
import java.util.*
import kotlin.collections.ArrayList

class NotificationsAdapter(
    private val mContext: Context
) : RecyclerView.Adapter<NotificationsAdapter.NotificationsViewHolder>() {
    /*  inflate layout */

    private var mNotificationDataList: List<NotificationsDataItem> = ArrayList()

    @SuppressLint("InflateParams")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationsViewHolder {
        return NotificationsViewHolder(
            LayoutInflater.from(mContext).inflate(R.layout.item_app_notifications, null)
        )
    }

    /*bind viewholder*/
    override fun onBindViewHolder(holder: NotificationsViewHolder, position: Int) {

       holder.apply {
           mTvTitle.text=mNotificationDataList[position].title
           mTvDate.text=mNotificationDataList[position].createdAt
           mTvDestination.text=mNotificationDataList[position].content

           if (mNotificationDataList[position].type=="trip"){
               mIvWhImg.setImageResource(R.drawable.ic_routes)
//               mIvWhImg.setBackgroundTintList(getColorStateList(mContext, R.color.darkgreen))
           }else if (mNotificationDataList[position].type=="wallet") {
               mIvWhImg.setImageResource(R.drawable.ic_wallet_3_line)
//               mIvWhImg.setBackgroundTintList(getColorStateList(mContext, R.color.red))
           }else   mIvWhImg.setImageResource(R.drawable.ic_notification_4_line)
       }

    }

    /*item count*/
    override fun getItemCount(): Int {
        return mNotificationDataList.size
    }

    fun setData(data: List<NotificationsDataItem>) {
        mNotificationDataList=data
        notifyDataSetChanged()
    }

    /*view holder*/
class NotificationsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mTvDate: TextView
        val mTvTitle: TextView
        val mTvDestination: TextView
        val mIvWhImg: ImageView


        init {
            mIvWhImg = itemView.findViewById(R.id.ivWhImg)
            mTvDate = itemView.findViewById(R.id.tvDate)
            mTvTitle = itemView.findViewById(R.id.tvTitle)
            mTvDestination = itemView.findViewById(R.id.tvDestination)
        }
    }
}