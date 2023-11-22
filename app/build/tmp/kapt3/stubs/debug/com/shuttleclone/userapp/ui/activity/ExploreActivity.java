package com.shuttleclone.userapp.ui.activity;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0012\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0014J\b\u0010\u0015\u001a\u00020\u0012H\u0014J\b\u0010\u0016\u001a\u00020\u0012H\u0014J\u000e\u0010\u0017\u001a\u00020\u00122\u0006\u0010\u0018\u001a\u00020\u0019R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u0007\u001a\u00020\b8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\t\u0010\nR\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001a"}, d2 = {"Lcom/shuttleclone/userapp/ui/activity/ExploreActivity;", "Lcom/shuttleclone/userapp/BaseActivity;", "()V", "TAG", "", "busRoutesAdapter", "Lcom/shuttleclone/userapp/ui/adapter/BusRoutesAdapter;", "homeFragmentViewModel", "Lcom/shuttleclone/userapp/ViewModel/HomeFragmentViewModel;", "getHomeFragmentViewModel", "()Lcom/shuttleclone/userapp/ViewModel/HomeFragmentViewModel;", "homeFragmentViewModel$delegate", "Lkotlin/Lazy;", "notRoutesFoundLayout", "Landroid/widget/LinearLayout;", "routesRecyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onResume", "onStart", "viewRouteStops", "stopsData", "Lcom/shuttleclone/userapp/model/RoutesDataItem;", "app_debug"})
public final class ExploreActivity extends com.shuttleclone.userapp.BaseActivity {
    @org.jetbrains.annotations.NotNull
    private java.lang.String TAG = "ExploreActivity";
    @org.jetbrains.annotations.NotNull
    private final kotlin.Lazy homeFragmentViewModel$delegate = null;
    @org.jetbrains.annotations.Nullable
    private androidx.recyclerview.widget.RecyclerView routesRecyclerView;
    @org.jetbrains.annotations.Nullable
    private com.shuttleclone.userapp.ui.adapter.BusRoutesAdapter busRoutesAdapter;
    @org.jetbrains.annotations.Nullable
    private android.widget.LinearLayout notRoutesFoundLayout;
    
    public ExploreActivity() {
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
    
    @java.lang.Override
    protected void onStart() {
    }
    
    public final void viewRouteStops(@org.jetbrains.annotations.NotNull
    com.shuttleclone.userapp.model.RoutesDataItem stopsData) {
    }
}