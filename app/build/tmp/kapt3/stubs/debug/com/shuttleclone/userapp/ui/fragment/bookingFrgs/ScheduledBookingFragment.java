package com.shuttleclone.userapp.ui.fragment.bookingFrgs;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000\u0086\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u0000 F2\u00020\u0001:\u0001FB\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010)\u001a\u00020*2\b\u0010+\u001a\u0004\u0018\u00010\u0006J\u0010\u0010,\u001a\u00020*2\b\u0010-\u001a\u0004\u0018\u00010\u0006J\u0010\u0010.\u001a\u00020*2\b\u0010-\u001a\u0004\u0018\u00010\u0006J\b\u0010/\u001a\u00020\u0015H\u0002J\b\u00100\u001a\u00020*H\u0002J\b\u00101\u001a\u00020*H\u0002J\u0010\u00102\u001a\u00020*2\u0006\u00103\u001a\u000204H\u0003J\b\u00105\u001a\u00020*H\u0003J\b\u00106\u001a\u00020*H\u0002J\b\u00107\u001a\u00020*H\u0002J&\u00108\u001a\u0004\u0018\u0001042\u0006\u00109\u001a\u00020:2\b\u0010;\u001a\u0004\u0018\u00010<2\b\u0010=\u001a\u0004\u0018\u00010>H\u0016J\u000e\u0010?\u001a\u00020*2\u0006\u0010@\u001a\u00020\u0006J\u0018\u0010A\u001a\u00020*2\b\u0010-\u001a\u0004\u0018\u00010\u00062\u0006\u0010B\u001a\u00020\u0006J\u0018\u0010C\u001a\u00020*2\u0006\u0010D\u001a\u00020\u00042\u0006\u0010E\u001a\u00020\u0006H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00060\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u001b\u0010\r\u001a\u00020\u000e8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u000f\u0010\u0010R\u000e\u0010\u0013\u001a\u00020\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u0016\u001a\n\u0012\u0004\u0012\u00020\u0018\u0018\u00010\u0017X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0019\u001a\u0004\u0018\u00010\u001aX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u001b\u001a\u0004\u0018\u00010\u001cX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u001d\u001a\u0004\u0018\u00010\u001eX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u001f\u001a\u0004\u0018\u00010\u001eX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\"\u001a\u0004\u0018\u00010#X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0017\u0010%\u001a\b\u0012\u0004\u0012\u00020\u00060&\u00a2\u0006\b\n\u0000\u001a\u0004\b\'\u0010(\u00a8\u0006G"}, d2 = {"Lcom/shuttleclone/userapp/ui/fragment/bookingFrgs/ScheduledBookingFragment;", "Landroidx/fragment/app/Fragment;", "()V", "REQUEST_PHONE_CALL", "", "TAG", "", "bookingHistoryAdapter", "Lcom/shuttleclone/userapp/ui/adapter/BookingHistoryAdapter;", "daysList", "Ljava/util/ArrayList;", "getDaysList", "()Ljava/util/ArrayList;", "homeFragmentViewModel", "Lcom/shuttleclone/userapp/ViewModel/HomeFragmentViewModel;", "getHomeFragmentViewModel", "()Lcom/shuttleclone/userapp/ViewModel/HomeFragmentViewModel;", "homeFragmentViewModel$delegate", "Lkotlin/Lazy;", "limit", "loadingBookingItems", "", "mBookingHistoryList", "", "Lcom/shuttleclone/userapp/model/GetbookingData;", "mLayNoDataAvailable", "Landroid/widget/LinearLayout;", "mPbLoadData", "Landroid/widget/ProgressBar;", "mRvBookingsHistory", "Landroidx/recyclerview/widget/RecyclerView;", "mRvTripStatus", "offSet", "refundAlert", "setReminderDialog", "Lcom/google/android/material/bottomsheet/BottomSheetDialog;", "travelStatus", "tripStatus", "", "getTripStatus", "()Ljava/util/List;", "callDriver", "", "driverNo", "cancelBooking", "pnrNo", "cancelBookingDialog", "checkCallPermission", "getBookingList", "handleViewModel", "initView", "view", "Landroid/view/View;", "initializeListeners", "loadDataToAdapter", "loadMoreData", "onCreateView", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "selectedStatus", "status", "setReminder", "startDate", "updateDaysList", "count", "label", "Companion", "app_debug"})
public final class ScheduledBookingFragment extends androidx.fragment.app.Fragment {
    @org.jetbrains.annotations.Nullable
    private android.widget.ProgressBar mPbLoadData;
    @org.jetbrains.annotations.Nullable
    private androidx.recyclerview.widget.RecyclerView mRvBookingsHistory;
    @org.jetbrains.annotations.Nullable
    private android.widget.LinearLayout mLayNoDataAvailable;
    @org.jetbrains.annotations.Nullable
    private androidx.recyclerview.widget.RecyclerView mRvTripStatus;
    @org.jetbrains.annotations.Nullable
    private java.util.List<com.shuttleclone.userapp.model.GetbookingData> mBookingHistoryList;
    @org.jetbrains.annotations.NotNull
    private final kotlin.Lazy homeFragmentViewModel$delegate = null;
    @org.jetbrains.annotations.Nullable
    private com.google.android.material.bottomsheet.BottomSheetDialog setReminderDialog;
    @org.jetbrains.annotations.Nullable
    private com.shuttleclone.userapp.ui.adapter.BookingHistoryAdapter bookingHistoryAdapter;
    @org.jetbrains.annotations.NotNull
    private java.lang.String refundAlert = "";
    private int offSet = 0;
    @org.jetbrains.annotations.NotNull
    private java.lang.String travelStatus = "SCHEDULED";
    private int limit = 10;
    private final int REQUEST_PHONE_CALL = 4544;
    @org.jetbrains.annotations.NotNull
    private final java.util.List<java.lang.String> tripStatus = null;
    private boolean loadingBookingItems = false;
    @org.jetbrains.annotations.NotNull
    private final java.util.ArrayList<java.lang.String> daysList = null;
    @org.jetbrains.annotations.NotNull
    private java.lang.String TAG = "MyBookingFragment";
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String mTitle = "My Tickets";
    @org.jetbrains.annotations.NotNull
    public static final com.shuttleclone.userapp.ui.fragment.bookingFrgs.ScheduledBookingFragment.Companion Companion = null;
    
    public ScheduledBookingFragment() {
        super();
    }
    
    private final com.shuttleclone.userapp.ViewModel.HomeFragmentViewModel getHomeFragmentViewModel() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.List<java.lang.String> getTripStatus() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.ArrayList<java.lang.String> getDaysList() {
        return null;
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.Nullable
    public android.view.View onCreateView(@org.jetbrains.annotations.NotNull
    android.view.LayoutInflater inflater, @org.jetbrains.annotations.Nullable
    android.view.ViewGroup container, @org.jetbrains.annotations.Nullable
    android.os.Bundle savedInstanceState) {
        return null;
    }
    
    private final void handleViewModel() {
    }
    
    private final void loadDataToAdapter() {
    }
    
    private final void loadMoreData() {
    }
    
    @android.annotation.SuppressLint(value = {"ResourceType"})
    private final void initView(android.view.View view) {
    }
    
    @android.annotation.SuppressLint(value = {"ResourceType"})
    private final void initializeListeners() {
    }
    
    private final void getBookingList() {
    }
    
    public final void setReminder(@org.jetbrains.annotations.Nullable
    java.lang.String pnrNo, @org.jetbrains.annotations.NotNull
    java.lang.String startDate) {
    }
    
    private final void updateDaysList(int count, java.lang.String label) {
    }
    
    public final void cancelBooking(@org.jetbrains.annotations.Nullable
    java.lang.String pnrNo) {
    }
    
    public final void cancelBookingDialog(@org.jetbrains.annotations.Nullable
    java.lang.String pnrNo) {
    }
    
    public final void callDriver(@org.jetbrains.annotations.Nullable
    java.lang.String driverNo) {
    }
    
    private final boolean checkCallPermission() {
        return false;
    }
    
    public final void selectedStatus(@org.jetbrains.annotations.NotNull
    java.lang.String status) {
    }
    
    @kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lcom/shuttleclone/userapp/ui/fragment/bookingFrgs/ScheduledBookingFragment$Companion;", "", "()V", "mTitle", "", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}