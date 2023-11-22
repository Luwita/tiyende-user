package com.shuttleclone.userapp.ui.activity;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B\u0005\u00a2\u0006\u0002\u0010\u0004J\b\u0010\u0014\u001a\u00020\u0015H\u0002J\b\u0010\u0016\u001a\u00020\u0015H\u0002J\b\u0010\u0017\u001a\u00020\u0015H\u0002J\u0010\u0010\u0018\u001a\u00020\u00152\u0006\u0010\u0019\u001a\u00020\u001aH\u0016J\u0010\u0010\u0018\u001a\u00020\u00152\u0006\u0010\u001b\u001a\u00020\tH\u0016J\u0012\u0010\u001c\u001a\u00020\u00152\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0014J\b\u0010\u001f\u001a\u00020\u0015H\u0014R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\"\u0010\u0007\u001a\u0016\u0012\u0004\u0012\u00020\t\u0018\u00010\bj\n\u0012\u0004\u0012\u00020\t\u0018\u0001`\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u000fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u000fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006 "}, d2 = {"Lcom/shuttleclone/userapp/ui/activity/PaymentActivity;", "Lcom/shuttleclone/userapp/BaseActivity;", "Landroid/view/View$OnClickListener;", "Lcom/shuttleclone/userapp/ui/adapter/CardsAdapter$onClickListener;", "()V", "mCardAdapter", "Lcom/shuttleclone/userapp/ui/adapter/CardsAdapter;", "mCardList", "Ljava/util/ArrayList;", "Lcom/shuttleclone/userapp/model/CardModel;", "Lkotlin/collections/ArrayList;", "mIvAdd", "Landroid/widget/ImageView;", "mIvBack", "mRlCredit", "Landroid/widget/RelativeLayout;", "mRlDebit", "mRlMain", "mRvCard", "Landroidx/recyclerview/widget/RecyclerView;", "enableSwipeToDeleteAndUndo", "", "initLayouts", "initializeListeners", "onClick", "v", "Landroid/view/View;", "cardModel", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onResume", "app_debug"})
public final class PaymentActivity extends com.shuttleclone.userapp.BaseActivity implements android.view.View.OnClickListener, com.shuttleclone.userapp.ui.adapter.CardsAdapter.onClickListener {
    @org.jetbrains.annotations.Nullable
    private java.util.ArrayList<com.shuttleclone.userapp.model.CardModel> mCardList;
    @org.jetbrains.annotations.Nullable
    private androidx.recyclerview.widget.RecyclerView mRvCard;
    @org.jetbrains.annotations.Nullable
    private android.widget.ImageView mIvBack;
    @org.jetbrains.annotations.Nullable
    private android.widget.ImageView mIvAdd;
    @org.jetbrains.annotations.Nullable
    private com.shuttleclone.userapp.ui.adapter.CardsAdapter mCardAdapter;
    @org.jetbrains.annotations.Nullable
    private android.widget.RelativeLayout mRlMain;
    @org.jetbrains.annotations.Nullable
    private android.widget.RelativeLayout mRlDebit;
    @org.jetbrains.annotations.Nullable
    private android.widget.RelativeLayout mRlCredit;
    
    public PaymentActivity() {
        super();
    }
    
    @java.lang.Override
    protected void onResume() {
    }
    
    @java.lang.Override
    protected void onCreate(@org.jetbrains.annotations.Nullable
    android.os.Bundle savedInstanceState) {
    }
    
    private final void initLayouts() {
    }
    
    private final void initializeListeners() {
    }
    
    @java.lang.Override
    public void onClick(@org.jetbrains.annotations.NotNull
    android.view.View v) {
    }
    
    private final void enableSwipeToDeleteAndUndo() {
    }
    
    @java.lang.Override
    public void onClick(@org.jetbrains.annotations.NotNull
    com.shuttleclone.userapp.model.CardModel cardModel) {
    }
}