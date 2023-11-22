package com.shuttleclone.userapp.ui.adapter

import android.content.Context
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.shuttleclone.userapp.R
import com.shuttleclone.userapp.model.StopsList
import com.shuttleclone.userapp.ui.activity.ExploreActivity
import com.shuttleclone.userapp.utils.alertDialog
import java.util.Timer
import java.util.TimerTask

class RouteStopsAdapter(val context: ExploreActivity, val stopsData: List<StopsList>?) :
    RecyclerView.Adapter<RouteStopsAdapter.ViewHolder>() {
    private var currentPage = 0
    private var timer: Timer? = null
    private val DELAY_MS: Long = 3000
    private val PERIOD_MS: Long = 5000

    val image= arrayListOf("https://picsum.photos/id/237/200/300","https://picsum.photos/seed/picsum/200/300","https://picsum.photos/200/300?grayscale")

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvStopTitle = view.findViewById<TextView>(R.id.tvStopTitle)
        val viewPager = view.findViewById<ViewPager>(R.id.image_slider_pager)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View =
            LayoutInflater.from(context).inflate(R.layout.layout_stops_adapt, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.apply {
            tvStopTitle.text = stopsData!![position]!!.name

            try {
                if (stopsData!![position]!!.images!!.isNotEmpty()) {

                    val adapter = ImageSliderAdapter(context, stopsData!![position]!!.images!!)
                    viewPager.adapter = adapter

                    val handler = Handler()
                    val update = Runnable {
                        if (currentPage == stopsData!![position]!!.images!!.size) {
                            currentPage = 0
                        }
                        viewPager.setCurrentItem(currentPage++, true)
                    }

                    timer = Timer() // This will create a new Timer
                    timer?.schedule(object : TimerTask() {
                        override fun run() {
                            handler.post(update)
                        }
                    }, DELAY_MS, PERIOD_MS)
                }else viewPager.visibility=View.GONE
            }catch (e:Exception){
                viewPager.visibility=View.GONE
                alertDialog(context,e.localizedMessage)
            }
        }

    }

    override fun getItemCount(): Int {
        stopsData?.let {
            return if (stopsData!!.isNotEmpty())
                stopsData!!.size
            else 0
        }
        return 0
    }


    class ImageSliderAdapter(private val context: Context, private val imageList: List<String>) : PagerAdapter() {

        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            val inflater = LayoutInflater.from(context)
            val itemView = inflater.inflate(R.layout.image_slider_item, container, false)
            val imageView = itemView.findViewById<ImageView>(R.id.image_slider_view)
            Glide.with(context).load(imageList[position]).placeholder(R.drawable.ic_routes).into(imageView)
            container.addView(itemView)
            return itemView
        }

        override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
            container.removeView(`object` as View)
        }

        override fun getCount(): Int {
            return imageList.size
        }

        override fun isViewFromObject(view: View, `object`: Any): Boolean {
            return view === `object`
        }
    }
}