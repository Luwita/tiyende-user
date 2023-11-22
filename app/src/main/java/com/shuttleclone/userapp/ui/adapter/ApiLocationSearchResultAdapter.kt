package com.shuttleclone.userapp.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.shuttleclone.userapp.R
import com.shuttleclone.userapp.model.SearchedDataItem
import com.shuttleclone.userapp.ui.fragment.SearchLocationDialogFragment
import com.shuttleclone.userapp.utils.Constants
import com.shuttleclone.userapp.utils.getDistanceFromLatLonInM
import com.shuttleclone.userapp.utils.getPreference
import com.shuttleclone.userapp.utils.mylog

class ApiLocationSearchResultAdapter(
    var mContaxt: Context,
    var listner: SearchLocationDialogFragment
) : RecyclerView.Adapter<ApiLocationSearchResultAdapter.ViewHolder>() {

    var searchedDataItem: MutableList<SearchedDataItem> = ArrayList()
    var SET_RESULT_TO: String? = ""
    var SET_ADDRESS_FOR: String? = ""
    var IS_SEARCHING_FOR_NEAREST_STOPS = false
    var TAG = "ApiLocationSearchResult"


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(mContaxt)
            .inflate(R.layout.search_result_adapter_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        try {
            searchedDataItem?.let {
                if (searchedDataItem.size > 0) {
                    holder.primaryLocation_tv.text = searchedDataItem[position].title
                    holder.secondary_location_tv.text = searchedDataItem[position].locationAddress

                    if (searchedDataItem[position].type.equals("location")
                        ||searchedDataItem[position].type.equals("PD")
                    ) {
                        holder.search_result_icon.setBackgroundResource(R.drawable.bg_circle_shape)
                        Glide.with(mContaxt).asDrawable().load(R.drawable.ic_bus_stop_24)
                            .into(holder.search_result_icon)
                    } else if (searchedDataItem[position].type.equals("DA")){
                        holder.search_result_icon.setBackgroundResource(R.drawable.bg_circle_shape)
                        Glide.with(mContaxt).asDrawable().load(R.drawable.ic_bus_stop)
                            .into(holder.search_result_icon)
                    }else {
                        holder.search_result_icon.setBackgroundResource(R.drawable.bg_circle_tint)
                        Glide.with(mContaxt).asDrawable().load(R.drawable.ic_location_24)
                            .into(holder.search_result_icon)
                    }

                    holder.rootLaout.setOnClickListener {
                        try {
                            if (searchedDataItem.size>position) {
                                listner.setSearchResult(
                                    searchedDataItem[position],
                                    SET_RESULT_TO!!,
                                    SET_ADDRESS_FOR,
                                    IS_SEARCHING_FOR_NEAREST_STOPS
                                )
                            }
                        }catch (i:IndexOutOfBoundsException){
                            mylog(TAG, "onBindViewHolder: IndexOutOfBoundsException Error=" + i.message)
                            try {
                                if (searchedDataItem.size>0&&position<=searchedDataItem.size) {
                                    listner.setSearchResult(
                                        searchedDataItem[position - 1],
                                        SET_RESULT_TO!!,
                                        SET_ADDRESS_FOR,
                                        IS_SEARCHING_FOR_NEAREST_STOPS
                                    )
                                }
                            }catch (e:Exception){
                                mylog(TAG, "onBindViewHolder: Inside catch Error=" + i.message)
                            }
                        }
                        catch (e:Exception){
                            mylog(TAG, "onBindViewHolder: LocationSearchResultAdapter Error=" + e.message)
                        }
                    }

                    try {
                        if (null!=searchedDataItem[position].nearDistance && searchedDataItem[position].nearDistance != "0"){
                            holder.avgDistanceTv.text = "Etd-${searchedDataItem[position].nearDistance}"
                        }else {
                            val distance = getDistanceFromLatLonInM(
                                searchedDataItem[position].locationLatitude,
                                searchedDataItem[position].locationLongitude,
                                getPreference(mContaxt, Constants.USER_LATITUDE).toDouble(),
                                getPreference(mContaxt, Constants.USER_LONGITUDE).toDouble()
                            )
                            if (distance > 0)
                                holder.avgDistanceTv.text = "Etd-${distance + 1} km"
                            else holder.avgDistanceTv.text = ""
                        }

                    }catch (e:Exception){
                        mylog(TAG, "onBindViewHolder: Error=${e.localizedMessage}")
                        holder.avgDistanceTv.text = ""
                    }

                }
            }
        } catch (e: Exception) {
            mylog(TAG, "onBindViewHolder: LocationSearchResultAdapter Error=" + e.message)
        }
    }

    override fun getItemCount(): Int {
        return if (null!=searchedDataItem){
            return if (searchedDataItem.size>0)
                searchedDataItem.size
            else 0
        }else 0
    }

    fun setResultTO(resultTO: String?) {
        SET_RESULT_TO = resultTO
    }

    fun setData(
        searchedDataItems: MutableList<SearchedDataItem>,
        setResultTo: String,
        setAddressFor: String,
        isNearestStopSearching: Boolean
    ) {
        searchedDataItem = searchedDataItems
        SET_RESULT_TO = setResultTo
        SET_ADDRESS_FOR = setAddressFor
        IS_SEARCHING_FOR_NEAREST_STOPS = isNearestStopSearching
        notifyDataSetChanged()
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var primaryLocation_tv: TextView
        var secondary_location_tv: TextView
        var avgDistanceTv: TextView
        var search_result_icon: ImageView
        var rootLaout: ConstraintLayout

        init {
            primaryLocation_tv = itemView.findViewById(R.id.location_title_tv)
            secondary_location_tv = itemView.findViewById(R.id.location_desc_tv)
            search_result_icon = itemView.findViewById(R.id.search_result_icon)
            rootLaout = itemView.findViewById(R.id.adpt_search_result_rootlayout)
            avgDistanceTv = itemView.findViewById(R.id.tv_avg_distance)
        }
    }

    companion object {
        private const val TAG = "LocationSearchResultAdapter"
    }

}