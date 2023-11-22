package com.shuttleclone.userapp.ui.activity;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000~\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u00012\u00020\u0002B\u0005\u00a2\u0006\u0002\u0010\u0003J6\u0010$\u001a\u0004\u0018\u00010\t2\u0006\u0010%\u001a\u00020&2\u0006\u0010\'\u001a\u00020&2\b\u0010(\u001a\u0004\u0018\u00010\u00072\b\u0010)\u001a\u0004\u0018\u00010\u00072\u0006\u0010*\u001a\u00020+H\u0004J\u0012\u0010,\u001a\u00020-2\b\u0010.\u001a\u0004\u0018\u00010/H\u0014J\b\u00100\u001a\u00020-H\u0014J\b\u00101\u001a\u00020-H\u0016J\u0010\u00102\u001a\u00020-2\u0006\u00103\u001a\u00020\u0012H\u0016J\b\u00104\u001a\u00020-H\u0014J\b\u00105\u001a\u00020-H\u0014J\b\u00106\u001a\u00020-H\u0014J\b\u00107\u001a\u00020-H\u0014J\b\u00108\u001a\u00020-H\u0002J\u0010\u00109\u001a\u00020-2\u0006\u0010:\u001a\u00020;H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0007X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u000b\u001a\u00020\f8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\r\u0010\u000eR\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\t0\u0016\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0010\u0010\u0019\u001a\u0004\u0018\u00010\u001aX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u001b\u001a\u0004\u0018\u00010\u001cX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u0007X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u0007X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\u0007X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010 \u001a\u0004\u0018\u00010!X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\"\u001a\u0004\u0018\u00010#X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006<"}, d2 = {"Lcom/shuttleclone/userapp/ui/activity/TrackRoutesActivity;", "Lcom/shuttleclone/userapp/BaseActivity;", "Lcom/google/android/gms/maps/OnMapReadyCallback;", "()V", "DEFAULT_ZOOM", "", "TAG", "", "currentMarker", "Lcom/google/android/gms/maps/model/Marker;", "dropId", "homeFragmentViewModel", "Lcom/shuttleclone/userapp/ViewModel/HomeFragmentViewModel;", "getHomeFragmentViewModel", "()Lcom/shuttleclone/userapp/ViewModel/HomeFragmentViewModel;", "homeFragmentViewModel$delegate", "Lkotlin/Lazy;", "map", "Lcom/google/android/gms/maps/GoogleMap;", "mapView", "Lcom/google/android/gms/maps/MapView;", "markers", "", "getMarkers", "()Ljava/util/List;", "myHandler", "Landroid/os/Handler;", "myRunnable", "Ljava/lang/Runnable;", "pickupId", "pnrNo", "routeId", "rvBusRoutesStops", "Landroidx/recyclerview/widget/RecyclerView;", "tvRouteStopTitle", "Landroid/widget/TextView;", "createMarker", "latitude", "", "longitude", "title", "snippet", "iconResID", "", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onLowMemory", "onMapReady", "gmap", "onPause", "onResume", "onStart", "onStop", "startTracker", "updateBusTrackStatus", "data", "Lcom/shuttleclone/userapp/model/TrackData;", "app_debug"})
public final class TrackRoutesActivity extends com.shuttleclone.userapp.BaseActivity implements com.google.android.gms.maps.OnMapReadyCallback {
    @org.jetbrains.annotations.NotNull
    private java.lang.String TAG = "TrackRoutesActivity";
    @org.jetbrains.annotations.NotNull
    private final kotlin.Lazy homeFragmentViewModel$delegate = null;
    @org.jetbrains.annotations.Nullable
    private android.widget.TextView tvRouteStopTitle;
    @org.jetbrains.annotations.Nullable
    private androidx.recyclerview.widget.RecyclerView rvBusRoutesStops;
    @org.jetbrains.annotations.NotNull
    private java.lang.String routeId = "";
    @org.jetbrains.annotations.NotNull
    private java.lang.String pickupId = "";
    @org.jetbrains.annotations.NotNull
    private java.lang.String dropId = "";
    @org.jetbrains.annotations.NotNull
    private java.lang.String pnrNo = "";
    @org.jetbrains.annotations.Nullable
    private com.google.android.gms.maps.MapView mapView;
    @org.jetbrains.annotations.NotNull
    private final java.util.List<com.google.android.gms.maps.model.Marker> markers = null;
    @org.jetbrains.annotations.Nullable
    private com.google.android.gms.maps.GoogleMap map;
    @org.jetbrains.annotations.Nullable
    private com.google.android.gms.maps.model.Marker currentMarker;
    private final float DEFAULT_ZOOM = 17.0F;
    @org.jetbrains.annotations.Nullable
    private android.os.Handler myHandler;
    @org.jetbrains.annotations.Nullable
    private java.lang.Runnable myRunnable;
    
    public TrackRoutesActivity() {
        super();
    }
    
    private final com.shuttleclone.userapp.ViewModel.HomeFragmentViewModel getHomeFragmentViewModel() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.List<com.google.android.gms.maps.model.Marker> getMarkers() {
        return null;
    }
    
    @java.lang.Override
    protected void onCreate(@org.jetbrains.annotations.Nullable
    android.os.Bundle savedInstanceState) {
    }
    
    private final void updateBusTrackStatus(com.shuttleclone.userapp.model.TrackData data) {
    }
    
    @java.lang.Override
    public void onMapReady(@org.jetbrains.annotations.NotNull
    com.google.android.gms.maps.GoogleMap gmap) {
    }
    
    private final void startTracker() {
    }
    
    @org.jetbrains.annotations.Nullable
    protected final com.google.android.gms.maps.model.Marker createMarker(double latitude, double longitude, @org.jetbrains.annotations.Nullable
    java.lang.String title, @org.jetbrains.annotations.Nullable
    java.lang.String snippet, int iconResID) {
        return null;
    }
    
    @java.lang.Override
    protected void onStart() {
    }
    
    @java.lang.Override
    protected void onResume() {
    }
    
    @java.lang.Override
    protected void onStop() {
    }
    
    @java.lang.Override
    protected void onPause() {
    }
    
    @java.lang.Override
    protected void onDestroy() {
    }
    
    @java.lang.Override
    public void onLowMemory() {
    }
}