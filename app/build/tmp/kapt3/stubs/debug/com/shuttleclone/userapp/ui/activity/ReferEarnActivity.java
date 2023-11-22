package com.shuttleclone.userapp.ui.activity;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 .2\u00020\u00012\u00020\u0002:\u0001.B\u0005\u00a2\u0006\u0002\u0010\u0003J\u0010\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019H\u0002J\u0014\u0010\u001a\u001a\u0004\u0018\u00010\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u0019H\u0002J\b\u0010\u001d\u001a\u00020\u0017H\u0002J\b\u0010\u001e\u001a\u00020\u0017H\u0002J\b\u0010\u001f\u001a\u00020\u0017H\u0002J\u0010\u0010 \u001a\u00020\u00172\u0006\u0010!\u001a\u00020\"H\u0017J\u0012\u0010#\u001a\u00020\u00172\b\u0010$\u001a\u0004\u0018\u00010%H\u0014J\b\u0010&\u001a\u00020\u0017H\u0014J\u0012\u0010\'\u001a\u00020\u00172\b\u0010(\u001a\u0004\u0018\u00010)H\u0002J\u0018\u0010*\u001a\u00020\u00172\b\u0010\u001c\u001a\u0004\u0018\u00010\u00192\u0006\u0010\u0018\u001a\u00020\u0019J\u0018\u0010+\u001a\u00020\u00172\u0006\u0010,\u001a\u00020\u00192\u0006\u0010\u0018\u001a\u00020\u0019H\u0002J\b\u0010-\u001a\u00020\u0017H\u0002R\u001b\u0010\u0004\u001a\u00020\u00058BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\u0006\u0010\u0007R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\u000bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u000bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u000bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0012X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0012X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0012X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006/"}, d2 = {"Lcom/shuttleclone/userapp/ui/activity/ReferEarnActivity;", "Lcom/shuttleclone/userapp/BaseActivity;", "Landroid/view/View$OnClickListener;", "()V", "homeFragmentViewModel", "Lcom/shuttleclone/userapp/ViewModel/HomeFragmentViewModel;", "getHomeFragmentViewModel", "()Lcom/shuttleclone/userapp/ViewModel/HomeFragmentViewModel;", "homeFragmentViewModel$delegate", "Lkotlin/Lazy;", "mIvBack", "Landroid/widget/ImageView;", "mIvFaceBook", "mIvGoogle", "mIvNotification", "mIvTwitter", "mIvWhatsapp", "mTvCode", "Landroid/widget/TextView;", "mTvLink", "mTvRfrAmount", "tvReferPolicy", "createDynamicLink", "", "shareOn", "", "getInvitationMessage", "", "linkUrl", "getReferDetails", "initLayouts", "initializeListeners", "onClick", "v", "Landroid/view/View;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onResume", "setDetails", "response", "Lcom/shuttleclone/userapp/model/Data;", "shareInviteLink", "shareWithNoImage", "shareMessage", "viewModelReferDetailsResponse", "Companion", "app_debug"})
public final class ReferEarnActivity extends com.shuttleclone.userapp.BaseActivity implements android.view.View.OnClickListener {
    @org.jetbrains.annotations.Nullable
    private android.widget.TextView mTvLink;
    @org.jetbrains.annotations.Nullable
    private android.widget.TextView mTvCode;
    @org.jetbrains.annotations.Nullable
    private android.widget.TextView tvReferPolicy;
    @org.jetbrains.annotations.Nullable
    private android.widget.TextView mTvRfrAmount;
    @org.jetbrains.annotations.Nullable
    private android.widget.ImageView mIvBack;
    @org.jetbrains.annotations.Nullable
    private android.widget.ImageView mIvFaceBook;
    @org.jetbrains.annotations.Nullable
    private android.widget.ImageView mIvWhatsapp;
    @org.jetbrains.annotations.Nullable
    private android.widget.ImageView mIvGoogle;
    @org.jetbrains.annotations.Nullable
    private android.widget.ImageView mIvTwitter;
    @org.jetbrains.annotations.Nullable
    private android.widget.ImageView mIvNotification;
    @org.jetbrains.annotations.NotNull
    private final kotlin.Lazy homeFragmentViewModel$delegate = null;
    @org.jetbrains.annotations.NotNull
    private static final java.lang.String TAG = "ReferEarnActivity";
    @org.jetbrains.annotations.NotNull
    public static final com.shuttleclone.userapp.ui.activity.ReferEarnActivity.Companion Companion = null;
    
    public ReferEarnActivity() {
        super();
    }
    
    private final com.shuttleclone.userapp.ViewModel.HomeFragmentViewModel getHomeFragmentViewModel() {
        return null;
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
    
    private final void viewModelReferDetailsResponse() {
    }
    
    private final void getReferDetails() {
    }
    
    private final void setDetails(com.shuttleclone.userapp.model.Data response) {
    }
    
    private final void initializeListeners() {
    }
    
    @java.lang.Override
    @androidx.annotation.RequiresApi(api = android.os.Build.VERSION_CODES.M)
    public void onClick(@org.jetbrains.annotations.NotNull
    android.view.View v) {
    }
    
    private final void createDynamicLink(java.lang.String shareOn) {
    }
    
    public final void shareInviteLink(@org.jetbrains.annotations.Nullable
    java.lang.String linkUrl, @org.jetbrains.annotations.NotNull
    java.lang.String shareOn) {
    }
    
    private final void shareWithNoImage(java.lang.String shareMessage, java.lang.String shareOn) {
    }
    
    private final java.lang.Object getInvitationMessage(java.lang.String linkUrl) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lcom/shuttleclone/userapp/ui/activity/ReferEarnActivity$Companion;", "", "()V", "TAG", "", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}