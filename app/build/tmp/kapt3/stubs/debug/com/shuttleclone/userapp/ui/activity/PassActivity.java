package com.shuttleclone.userapp.ui.activity;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0012\u0010\u001e\u001a\u00020\u001f2\b\u0010 \u001a\u0004\u0018\u00010!H\u0014J\b\u0010\"\u001a\u00020\u001fH\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001c\u0010\t\u001a\u0004\u0018\u00010\nX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001c\u0010\u000f\u001a\u0004\u0018\u00010\nX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\f\"\u0004\b\u0011\u0010\u000eR\u0016\u0010\u0012\u001a\n\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u0013X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001c\u0010\u0017\u001a\u0004\u0018\u00010\u0018X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\u000e\u0010\u001d\u001a\u00020\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006#"}, d2 = {"Lcom/shuttleclone/userapp/ui/activity/PassActivity;", "Lcom/shuttleclone/userapp/BaseActivity;", "()V", "TAG", "", "bookingType", "busId", "dropId", "has_return", "layNoDataAvailable", "Landroid/widget/LinearLayout;", "getLayNoDataAvailable", "()Landroid/widget/LinearLayout;", "setLayNoDataAvailable", "(Landroid/widget/LinearLayout;)V", "layPassAlert", "getLayPassAlert", "setLayPassAlert", "passesList", "", "Lcom/shuttleclone/userapp/model/PassesList;", "pickupId", "routeId", "rvPasses", "Landroidx/recyclerview/widget/RecyclerView;", "getRvPasses", "()Landroidx/recyclerview/widget/RecyclerView;", "setRvPasses", "(Landroidx/recyclerview/widget/RecyclerView;)V", "seatNo", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onResume", "app_debug"})
public final class PassActivity extends com.shuttleclone.userapp.BaseActivity {
    @org.jetbrains.annotations.Nullable
    private androidx.recyclerview.widget.RecyclerView rvPasses;
    @org.jetbrains.annotations.Nullable
    private android.widget.LinearLayout layPassAlert;
    @org.jetbrains.annotations.Nullable
    private android.widget.LinearLayout layNoDataAvailable;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String TAG = "PassActivity";
    @org.jetbrains.annotations.NotNull
    private java.lang.String seatNo = "";
    @org.jetbrains.annotations.Nullable
    private java.util.List<com.shuttleclone.userapp.model.PassesList> passesList;
    @org.jetbrains.annotations.NotNull
    private java.lang.String routeId = "";
    @org.jetbrains.annotations.NotNull
    private java.lang.String pickupId = "";
    @org.jetbrains.annotations.NotNull
    private java.lang.String dropId = "";
    @org.jetbrains.annotations.NotNull
    private java.lang.String busId = "";
    @org.jetbrains.annotations.NotNull
    private java.lang.String has_return = "";
    @org.jetbrains.annotations.NotNull
    private java.lang.String bookingType = "";
    
    public PassActivity() {
        super();
    }
    
    @org.jetbrains.annotations.Nullable
    public final androidx.recyclerview.widget.RecyclerView getRvPasses() {
        return null;
    }
    
    public final void setRvPasses(@org.jetbrains.annotations.Nullable
    androidx.recyclerview.widget.RecyclerView p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final android.widget.LinearLayout getLayPassAlert() {
        return null;
    }
    
    public final void setLayPassAlert(@org.jetbrains.annotations.Nullable
    android.widget.LinearLayout p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final android.widget.LinearLayout getLayNoDataAvailable() {
        return null;
    }
    
    public final void setLayNoDataAvailable(@org.jetbrains.annotations.Nullable
    android.widget.LinearLayout p0) {
    }
    
    @java.lang.Override
    protected void onResume() {
    }
    
    @java.lang.Override
    protected void onCreate(@org.jetbrains.annotations.Nullable
    android.os.Bundle savedInstanceState) {
    }
}