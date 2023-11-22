package com.shuttleclone.userapp.ui.activity

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.shuttleclone.userapp.BaseActivity
import com.shuttleclone.userapp.R
import com.shuttleclone.userapp.model.CardModel
import com.shuttleclone.userapp.ui.adapter.CardsAdapter
import com.shuttleclone.userapp.utils.Constants
import com.shuttleclone.userapp.utils.LocaleManager
import com.shuttleclone.userapp.utils.SwipeToDeleteCallback

class CardsActivity : BaseActivity(), View.OnClickListener {
    /*variable declaration*/
    private var mCardList: ArrayList<CardModel>? = null
    private var mRvCard: RecyclerView? = null
    private var mIvBack: ImageView? = null
    private var mIVAddCardDetail: ImageView? = null
    private var mCardAdapter: CardsAdapter? = null
    private var mRlMain: RelativeLayout? = null
    private var mFlags: String? = null

    override fun onResume() {
        super.onResume()
        LocaleManager().setLocale(this) //This method update the language when the activity orientation changes
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LocaleManager().setLocale(this) //This method update the language when the activity orientation changes
        setContentView(R.layout.activity_card)
        initLayouts()
        initializeListeners()
        enableSwipeToDeleteAndUndo()
    }

    /* initialize listener */
    private fun initializeListeners() {
        mIvBack!!.setOnClickListener(this)
        mIVAddCardDetail!!.setOnClickListener(this)
    }

    /* init layout */
    private fun initLayouts() {
        setTitle(R.string.tite_toolbar_cards)
        mRvCard = findViewById(R.id.rvCard)
        mIvBack = findViewById(R.id.ivBack)
        mRlMain = findViewById(R.id.rlMain)
        mIVAddCardDetail = findViewById(R.id.ivAddCardDetail)
        mRvCard!!.setLayoutManager(LinearLayoutManager(this, RecyclerView.VERTICAL, false))
        mCardList = ArrayList()
        mCardList!!.add(
            CardModel(
                getString(R.string.lbl_cardtype1),
                R.drawable.ic_card,
                getString(R.string.lbl_card_digit1),
                getString(R.string.lbl_card_digit2),
                getString(R.string.lbl_card_digit3),
                getString(R.string.lbl_card_digit4),
                getString(R.string.lbl_card_validdate1),
                getString(R.string.lbl_booking_passengername1)
            )
        )
        mCardList!!.add(
            CardModel(
                getString(R.string.lbl_cardtype2),
                R.drawable.ic_card3,
                getString(R.string.lbl_card_digit2),
                getString(R.string.lbl_card_digit4),
                getString(R.string.lbl_card_digit1),
                getString(R.string.lbl_card_digit3),
                getString(R.string.lbl_card_validdate1),
                getString(R.string.lbl_booking_passengername1)
            )
        )
        mCardAdapter = CardsAdapter(this, mCardList)
        mRvCard!!.setAdapter(mCardAdapter)
        RunLayoutAnimation(mRvCard)
        mFlags = intent.getStringExtra(Constants.intentdata.CARDFLAG)
    }

    /* onClick listener */
    override fun onClick(v: View) {
        if (v === mIvBack) onBackPressedDispatcher.onBackPressed()
        if (v === mIVAddCardDetail) {
            val intent = Intent(this@CardsActivity, CardDetailActivity::class.java)
            intent.putExtra(Constants.intentdata.CARDFLAG, mFlags)
            startActivity(intent)
        }
    }

    /* swipe to delete & undo */
    private fun enableSwipeToDeleteAndUndo() {
        val swipeToDeleteCallback: SwipeToDeleteCallback = object : SwipeToDeleteCallback(this) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, i: Int) {
                val position = viewHolder.adapterPosition
                val mCardModel: CardModel
                mCardModel = mCardAdapter!!.data[position]
                mCardAdapter!!.removeItem(position)
                val snackbar = Snackbar
                    .make(mRlMain!!, getString(R.string.text_remove), Snackbar.LENGTH_LONG)
                snackbar.setAction(getString(R.string.text_undo)) {
                    mCardAdapter!!.restoreItem(mCardModel, position)
                    mRvCard!!.scrollToPosition(position)
                }
                snackbar.setActionTextColor(Color.YELLOW)
                snackbar.show()
            }
        }
        val itemTouchhelper = ItemTouchHelper(swipeToDeleteCallback)
        itemTouchhelper.attachToRecyclerView(mRvCard)
    }
}