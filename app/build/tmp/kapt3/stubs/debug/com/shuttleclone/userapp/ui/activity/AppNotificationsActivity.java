package com.shuttleclone.userapp.ui.activity;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u00012\u00020\u0002B\u0005\u00a2\u0006\u0002\u0010\u0003J\b\u0010\u001e\u001a\u00020\u001fH\u0002J\b\u0010 \u001a\u00020\u001fH\u0002J\b\u0010!\u001a\u00020\u001fH\u0002J\b\u0010\"\u001a\u00020\u001fH\u0002J\u0010\u0010#\u001a\u00020\u001f2\u0006\u0010$\u001a\u00020%H\u0016J\u0012\u0010&\u001a\u00020\u001f2\b\u0010\'\u001a\u0004\u0018\u00010(H\u0014J\b\u0010)\u001a\u00020\u001fH\u0014J\b\u0010*\u001a\u00020\u001fH\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082D\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u0006\u001a\u00020\u00078BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\b\u0010\tR\u000e\u0010\f\u001a\u00020\rX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0011X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001e\u0010\u0015\u001a\u0012\u0012\u0004\u0012\u00020\u00170\u0016j\b\u0012\u0004\u0012\u00020\u0017`\u0018X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0019\u001a\u0004\u0018\u00010\u001aX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u001b\u001a\u0004\u0018\u00010\u001cX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\rX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006+"}, d2 = {"Lcom/shuttleclone/userapp/ui/activity/AppNotificationsActivity;", "Lcom/shuttleclone/userapp/BaseActivity;", "Landroid/view/View$OnClickListener;", "()V", "TAG", "", "homeFragmentViewModel", "Lcom/shuttleclone/userapp/ViewModel/HomeFragmentViewModel;", "getHomeFragmentViewModel", "()Lcom/shuttleclone/userapp/ViewModel/HomeFragmentViewModel;", "homeFragmentViewModel$delegate", "Lkotlin/Lazy;", "limit", "", "loadingBookingItems", "", "mIvBack", "Landroid/widget/ImageView;", "mIvDeleteAll", "mLayNoDataAvailable", "Landroid/widget/LinearLayout;", "mNotificationDataList", "Ljava/util/ArrayList;", "Lcom/shuttleclone/userapp/model/NotificationsDataItem;", "Lkotlin/collections/ArrayList;", "mRvAppNotifictaions", "Landroidx/recyclerview/widget/RecyclerView;", "notificationsAdapter", "Lcom/shuttleclone/userapp/ui/adapter/NotificationsAdapter;", "offSet", "deleteNotifications", "", "getNotifications", "initLayouts", "initializeListeners", "onClick", "v", "Landroid/view/View;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onResume", "viewModelNotifications", "app_debug"})
public final class AppNotificationsActivity extends com.shuttleclone.userapp.BaseActivity implements android.view.View.OnClickListener {
    @org.jetbrains.annotations.NotNull
    private final java.lang.String TAG = "AppNotificationsActivity";
    @org.jetbrains.annotations.Nullable
    private androidx.recyclerview.widget.RecyclerView mRvAppNotifictaions;
    @org.jetbrains.annotations.Nullable
    private android.widget.LinearLayout mLayNoDataAvailable;
    @org.jetbrains.annotations.Nullable
    private android.widget.ImageView mIvBack;
    @org.jetbrains.annotations.Nullable
    private android.widget.ImageView mIvDeleteAll;
    @org.jetbrains.annotations.Nullable
    private com.shuttleclone.userapp.ui.adapter.NotificationsAdapter notificationsAdapter;
    @org.jetbrains.annotations.NotNull
    private final kotlin.Lazy homeFragmentViewModel$delegate = null;
    private int offSet = 0;
    private int limit = 10;
    private boolean loadingBookingItems = false;
    @org.jetbrains.annotations.NotNull
    private java.util.ArrayList<com.shuttleclone.userapp.model.NotificationsDataItem> mNotificationDataList;
    
    public AppNotificationsActivity() {
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
    
    private final void initializeListeners() {
    }
    
    private final void viewModelNotifications() {
    }
    
    private final void getNotifications() {
    }
    
    private final void initLayouts() {
    }
    
    @java.lang.Override
    public void onClick(@org.jetbrains.annotations.NotNull
    android.view.View v) {
    }
    
    private final void deleteNotifications() {
    }
}