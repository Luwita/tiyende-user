package com.shuttleclone.userapp.ui.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shuttleclone.userapp.R;
import com.shuttleclone.userapp.model.RoutesItem;
import com.shuttleclone.userapp.ui.activity.BookingActivity;
import com.shuttleclone.userapp.ui.activity.BusRoutesActivity;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import static com.shuttleclone.userapp.utils.UtilMethodsKt.diffTime;
import static com.shuttleclone.userapp.utils.UtilMethodsKt.mylog;

public class ItemBusAdapter extends RecyclerView.Adapter<ItemBusAdapter.BusitemViewHolder> {
    /*variable declaration*/
    private Context mContext;
    private List<RoutesItem> mBusList;
    private String bookingType, has_return, dayOfWeek;
    private Boolean isBusAvailable = false;

    /*constructor*/

    public ItemBusAdapter(Context aCtx, List<RoutesItem> aBusList, String bookingType, String has_return, String dayOfWeek) {
        /* initialize parameter*/
        this.mContext = aCtx;
        this.mBusList = aBusList;
        this.bookingType = bookingType;
        this.has_return = has_return;
        this.dayOfWeek = dayOfWeek;
    }

    public ItemBusAdapter(Context aCtx) {
        this.mContext = aCtx;
    }


    public void setData( List<RoutesItem> aBusList, String bookingType, String has_return, String dayOfWeek) {
        this.mBusList = aBusList;
        this.bookingType = bookingType;
        this.has_return = has_return;
        this.dayOfWeek = dayOfWeek;

        notifyDataSetChanged();
    }

    /*  inflate layout */
    @SuppressLint("InflateParams")
    @NonNull
    @Override
    public ItemBusAdapter.BusitemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ItemBusAdapter.BusitemViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_available_bus, null));
    }

    /*bind viewholder*/
    @SuppressLint("DefaultLocale")
    @Override
    public void onBindViewHolder(@NonNull ItemBusAdapter.BusitemViewHolder holder1, int position) {
        try {

            final RoutesItem mBusModel = mBusList.get(position);
            holder1.mTvTravellerName.setText(mContext.getString(R.string.app_name));
            holder1.mTvStartTime.setText(mBusModel.getPickupStopDepartureTime());
            holder1.mTvTypeCoach.setText("AC");

            try {

                for (String amenitie : mBusModel.getBusDetails().getAmenities()) {
                    switch (amenitie) {
                        case "ac":
                            holder1.ac.setVisibility(View.VISIBLE);
                            break;
                        case "snacks-and-drinks":
                            holder1.snacks_drinks.setVisibility(View.VISIBLE);
                            break;
                        case "wc":
                            holder1.wc.setVisibility(View.VISIBLE);
                            break;
                        case "wifi":
                            holder1.wifi.setVisibility(View.VISIBLE);
                            break;
                        case "gps":
                            holder1.gps.setVisibility(View.VISIBLE);
                            break;
                        case "usb":
                            holder1.usb.setVisibility(View.VISIBLE);
                            break;
                        case "tv":
                            holder1.tv.setVisibility(View.VISIBLE);
                            break;
                    }
                }
            } catch (Exception e) {
                Log.i("TAG", "onBindViewHolder: Errrpr");
            }

            holder1.mTvEndTime.setText(mBusModel.getDropStopArrivalTime());
//            holder1.mTvTotalDuration.setText("2");
            holder1.mTvTotalDuration.setText(diffTime(mBusModel.getPickupStopDepartureTime(), mBusModel.getDropStopArrivalTime()));

//        holder1.mTvStartTimeAA.setText(mBusModel.getmStartTimeAA());
//        holder1.mTvEndTimeAA.setText(mBusModel.getmEndTimeAA());
            holder1.mTvHold.setText(String.format(mContext.getString(R.string.text_addhold), mBusModel.getTotalOfStops().toString()));
            holder1.mTvRatingBar.setText(mBusModel.getPickupStopName() + mContext.getString(R.string.lbl_to) + mBusModel.getDropStopName());

            if (!isRouteActive(dayOfWeek, mBusModel.getRouteBusTimetable())) {
                isBusAvailable = false;
                holder1.mRelativeLayout.setBackgroundColor(mContext.getResources().getColor(R.color.gray));
                holder1.mTvBusNotAvailable.setVisibility(View.VISIBLE);
            } else {
                holder1.mRelativeLayout.setBackgroundColor(mContext.getResources().getColor(R.color.white));
                holder1.mTvBusNotAvailable.setVisibility(View.GONE);
                isBusAvailable = true;
            }

            holder1.mRelativeLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (isBusAvailable) {
                        Intent i = new Intent(mContext, BookingActivity.class);
                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        i.putExtra("routeId", mBusModel.getRouteId());
                        i.putExtra("route_timetableId", mBusModel.getBusScheduleId());
                        i.putExtra("busId", mBusModel.getRouteBusId());
                        i.putExtra("pickupId", mBusModel.getPickupStopId());
                        i.putExtra("dropId", mBusModel.getDropStopId());
                        i.putExtra("stops", mBusModel.getTotalOfStops());
                        i.putExtra("bookingType", bookingType);
                        i.putExtra("has_return", has_return);
                        mContext.startActivity(i);
                    }
                }
            });

            holder1.mTvViewRoutes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(mContext, BusRoutesActivity.class);
                    i.putExtra("routeId", mBusModel.getRouteId());
                    i.putExtra("pickupId", mBusModel.getPickupStopId());
                    i.putExtra("dropId", mBusModel.getDropStopId());
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    mContext.startActivity(i);
                }
            });
        } catch (Exception e) {
            mylog("ItemBusAdapter", "onBindViewHolder: Errrror=" + e.getLocalizedMessage());
        }
    }


    /*item count*/
    @Override
    public int getItemCount() {
        if (null != mBusList)
            return mBusList.size();
        else return 0;
    }


    /*view holder*/
    class BusitemViewHolder extends RecyclerView.ViewHolder {

        private TextView mTvTravellerName, mTvBusNotAvailable, mTvStartTime, mTvEndTime, mTvTotalDuration, mTvHold, mTvViewRoutes, mTvTypeCoach, mTvRatingBar, mTvStartTimeAA, mTvEndTimeAA;
        private RelativeLayout mRelativeLayout;
        private ImageView wifi, ac, usb, tv, gps, wc, snacks_drinks;

        BusitemViewHolder(View itemView) {
            super(itemView);
            mTvTravellerName = itemView.findViewById(R.id.tvTravellerName);
            mTvStartTime = itemView.findViewById(R.id.tvStartTime);
            mTvEndTime = itemView.findViewById(R.id.tvEndTime);
            mTvTotalDuration = itemView.findViewById(R.id.tvTotalDuration);
            mTvHold = itemView.findViewById(R.id.tvHold);
            mTvRatingBar = itemView.findViewById(R.id.tvRatingbar);
            mTvViewRoutes = itemView.findViewById(R.id.tvViewRoutes);
            mTvTypeCoach = itemView.findViewById(R.id.tvTypeCoach);
            mRelativeLayout = itemView.findViewById(R.id.rlMain);
            mTvStartTimeAA = itemView.findViewById(R.id.tvStartTimeAA);
            mTvEndTimeAA = itemView.findViewById(R.id.tvEndTimeAA);
            mTvBusNotAvailable = itemView.findViewById(R.id.tvBusNotAvailable);

            wifi = itemView.findViewById(R.id.wifi);
            ac = itemView.findViewById(R.id.ac);
            usb = itemView.findViewById(R.id.usb);
            tv = itemView.findViewById(R.id.tv);
            gps = itemView.findViewById(R.id.gps);
            wc = itemView.findViewById(R.id.wc);
            snacks_drinks = itemView.findViewById(R.id.snacks_drinks);
        }

    }

    private boolean isRouteActive(String dayOfWeek, List<String> routeBusTimetable) {
        if (routeBusTimetable.contains(dayOfWeek)) {
            mylog("ItemBusAdaptor", "checkIsDayValid: valid date=" + dayOfWeek);
            return true;
        } else {
            mylog("ItemBusAdaptor", "checkIsDayValid: invalid =" + dayOfWeek);
            return false;
        }
    }


}