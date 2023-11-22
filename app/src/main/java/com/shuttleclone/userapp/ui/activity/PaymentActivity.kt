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

class PaymentActivity : BaseActivity(), View.OnClickListener, CardsAdapter.onClickListener {
    /*variable declaration*/
    private var mCardList: ArrayList<CardModel>? = null
    private var mRvCard: RecyclerView? = null
    private var mIvBack: ImageView? = null
    private var mIvAdd: ImageView? = null
    private var mCardAdapter: CardsAdapter? = null
    private var mRlMain: RelativeLayout? = null
    private var mRlDebit: RelativeLayout? = null
    private var mRlCredit: RelativeLayout? = null


    override fun onResume() {
        super.onResume()
        LocaleManager().setLocale(this) //This method update the language when the activity orientation changes
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LocaleManager().setLocale(this) //This method update the language when the activity orientation changes
        setContentView(R.layout.activity_payment)
        initLayouts()
        initializeListeners()
        enableSwipeToDeleteAndUndo()
    }

    /* init layout */
    private fun initLayouts() {
        mRvCard = findViewById(R.id.rvCard)
        mIvBack = findViewById(R.id.ivBack)
        mIvAdd = findViewById(R.id.ivAdd)
        mRlMain = findViewById(R.id.rlMain)
        mRlCredit = findViewById(R.id.rlCreditCard)
        mRlDebit = findViewById(R.id.rlDebitCard)
    }

    /* initialize listener */
    private fun initializeListeners() {
        mIvBack!!.setOnClickListener(this)
        mIvAdd!!.setOnClickListener(this)
        mRlCredit!!.setOnClickListener(this)
        mRlDebit!!.setOnClickListener(this)
        mCardList = ArrayList()
        mRvCard!!.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
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
        mCardAdapter = CardsAdapter(this, mCardList)
        mRvCard!!.adapter = mCardAdapter
        mCardAdapter!!.setOnClickListener(this)
        RunLayoutAnimation(mRvCard)
    }

    /* onClick listener */
    override fun onClick(v: View) {
        if (v === mIvBack) onBackPressedDispatcher.onBackPressed() else if (v === mIvAdd) startActivity(CardDetailActivity::class.java) else if (v === mRlCredit) startActivity(
            CardDetailActivity::class.java
        ) else if (v === mRlDebit) startActivity(CardDetailActivity::class.java)
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

    /* onClick listener */
    override fun onClick(cardModel: CardModel) {
        val intent = Intent(this, CardDetailActivity::class.java)
        intent.putExtra(Constants.intentdata.CARDDETAIL, cardModel)
        startActivity(intent)
    }
}