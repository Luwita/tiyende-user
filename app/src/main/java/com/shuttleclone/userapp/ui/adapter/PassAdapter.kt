package com.shuttleclone.userapp.ui.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.shuttleclone.userapp.R
import com.shuttleclone.userapp.model.PassesList
import com.shuttleclone.userapp.ui.activity.PassDetailsActivity
import com.shuttleclone.userapp.utils.Constants
import com.shuttleclone.userapp.utils.getPreference
import java.io.Serializable

class PassAdapter(
    val context: Context,
    val passesList: List<PassesList>?
) :RecyclerView.Adapter<PassAdapter.MyViewHolder>() {

    class MyViewHolder(val view: View) :RecyclerView.ViewHolder(view) {
        val tvNoOfRides=view.findViewById<TextView>(R.id.tvNoOfRides)
        val tvNoOfDays=view.findViewById<TextView>(R.id.tvNoOfDays)
        val tvFarePerRide=view.findViewById<TextView>(R.id.tvFarePerRide)
        val tvActualFarePerRide=view.findViewById<TextView>(R.id.tvActualFarePerRide)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view=LayoutInflater.from(context).inflate(R.layout.adp_pass_layout,parent,false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return passesList?.size!!
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.apply {
            tvActualFarePerRide.text= "${getPreference(context, Constants.DEFAULT_CURRENCY)}${passesList!![position].totalFare}"
            tvNoOfRides.text= "${passesList!![position].passNoOfRides} ${context.getString(R.string.rides)}"
            tvNoOfDays.text= "${passesList!![position].passNoOfValidDays} ${context.getString(R.string.days_validity)}"
            tvFarePerRide.text= "${getPreference(context, Constants.DEFAULT_CURRENCY)}${passesList!![position].totalsingleFare}/${context.getString(R.string.ride)}"

            view.setOnClickListener {
                val intent=Intent(context,PassDetailsActivity::class.java)
                intent.putExtra("passDetails",passesList[position] as Serializable)
                context.startActivity(intent)
            }
        }

    }
}

