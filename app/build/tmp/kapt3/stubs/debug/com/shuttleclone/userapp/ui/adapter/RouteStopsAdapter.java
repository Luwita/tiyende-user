package com.shuttleclone.userapp.ui.adapter;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0002\"#B\u001d\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u000e\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006\u00a2\u0006\u0002\u0010\bJ\b\u0010\u0019\u001a\u00020\u000fH\u0016J\u0018\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u00022\u0006\u0010\u001d\u001a\u00020\u000fH\u0016J\u0018\u0010\u001e\u001a\u00020\u00022\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\u000fH\u0016R\u000e\u0010\t\u001a\u00020\nX\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\nX\u0082D\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u0003\u001a\u00020\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0019\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u0018X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006$"}, d2 = {"Lcom/shuttleclone/userapp/ui/adapter/RouteStopsAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/shuttleclone/userapp/ui/adapter/RouteStopsAdapter$ViewHolder;", "context", "Lcom/shuttleclone/userapp/ui/activity/ExploreActivity;", "stopsData", "", "Lcom/shuttleclone/userapp/model/StopsList;", "(Lcom/shuttleclone/userapp/ui/activity/ExploreActivity;Ljava/util/List;)V", "DELAY_MS", "", "PERIOD_MS", "getContext", "()Lcom/shuttleclone/userapp/ui/activity/ExploreActivity;", "currentPage", "", "image", "Ljava/util/ArrayList;", "", "getImage", "()Ljava/util/ArrayList;", "getStopsData", "()Ljava/util/List;", "timer", "Ljava/util/Timer;", "getItemCount", "onBindViewHolder", "", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "ImageSliderAdapter", "ViewHolder", "app_debug"})
public final class RouteStopsAdapter extends androidx.recyclerview.widget.RecyclerView.Adapter<com.shuttleclone.userapp.ui.adapter.RouteStopsAdapter.ViewHolder> {
    @org.jetbrains.annotations.NotNull
    private final com.shuttleclone.userapp.ui.activity.ExploreActivity context = null;
    @org.jetbrains.annotations.Nullable
    private final java.util.List<com.shuttleclone.userapp.model.StopsList> stopsData = null;
    private int currentPage = 0;
    @org.jetbrains.annotations.Nullable
    private java.util.Timer timer;
    private final long DELAY_MS = 3000L;
    private final long PERIOD_MS = 5000L;
    @org.jetbrains.annotations.NotNull
    private final java.util.ArrayList<java.lang.String> image = null;
    
    public RouteStopsAdapter(@org.jetbrains.annotations.NotNull
    com.shuttleclone.userapp.ui.activity.ExploreActivity context, @org.jetbrains.annotations.Nullable
    java.util.List<com.shuttleclone.userapp.model.StopsList> stopsData) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.shuttleclone.userapp.ui.activity.ExploreActivity getContext() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.util.List<com.shuttleclone.userapp.model.StopsList> getStopsData() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.ArrayList<java.lang.String> getImage() {
        return null;
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.NotNull
    public com.shuttleclone.userapp.ui.adapter.RouteStopsAdapter.ViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    @java.lang.Override
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull
    com.shuttleclone.userapp.ui.adapter.RouteStopsAdapter.ViewHolder holder, int position) {
    }
    
    @java.lang.Override
    public int getItemCount() {
        return 0;
    }
    
    @kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u00a2\u0006\u0002\u0010\u0007J \u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\b\u0010\u0010\u001a\u00020\rH\u0016J\u0018\u0010\u0011\u001a\u00020\u000f2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0016J\u0018\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u000e\u001a\u00020\u000fH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0016"}, d2 = {"Lcom/shuttleclone/userapp/ui/adapter/RouteStopsAdapter$ImageSliderAdapter;", "Landroidx/viewpager/widget/PagerAdapter;", "context", "Landroid/content/Context;", "imageList", "", "", "(Landroid/content/Context;Ljava/util/List;)V", "destroyItem", "", "container", "Landroid/view/ViewGroup;", "position", "", "object", "", "getCount", "instantiateItem", "isViewFromObject", "", "view", "Landroid/view/View;", "app_debug"})
    public static final class ImageSliderAdapter extends androidx.viewpager.widget.PagerAdapter {
        @org.jetbrains.annotations.NotNull
        private final android.content.Context context = null;
        @org.jetbrains.annotations.NotNull
        private final java.util.List<java.lang.String> imageList = null;
        
        public ImageSliderAdapter(@org.jetbrains.annotations.NotNull
        android.content.Context context, @org.jetbrains.annotations.NotNull
        java.util.List<java.lang.String> imageList) {
            super();
        }
        
        @java.lang.Override
        @org.jetbrains.annotations.NotNull
        public java.lang.Object instantiateItem(@org.jetbrains.annotations.NotNull
        android.view.ViewGroup container, int position) {
            return null;
        }
        
        @java.lang.Override
        public void destroyItem(@org.jetbrains.annotations.NotNull
        android.view.ViewGroup container, int position, @org.jetbrains.annotations.NotNull
        java.lang.Object object) {
        }
        
        @java.lang.Override
        public int getCount() {
            return 0;
        }
        
        @java.lang.Override
        public boolean isViewFromObject(@org.jetbrains.annotations.NotNull
        android.view.View view, @org.jetbrains.annotations.NotNull
        java.lang.Object object) {
            return false;
        }
    }
    
    @kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0019\u0010\u0005\u001a\n \u0007*\u0004\u0018\u00010\u00060\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0019\u0010\n\u001a\n \u0007*\u0004\u0018\u00010\u000b0\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r\u00a8\u0006\u000e"}, d2 = {"Lcom/shuttleclone/userapp/ui/adapter/RouteStopsAdapter$ViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "view", "Landroid/view/View;", "(Landroid/view/View;)V", "tvStopTitle", "Landroid/widget/TextView;", "kotlin.jvm.PlatformType", "getTvStopTitle", "()Landroid/widget/TextView;", "viewPager", "Landroidx/viewpager/widget/ViewPager;", "getViewPager", "()Landroidx/viewpager/widget/ViewPager;", "app_debug"})
    public static final class ViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        private final android.widget.TextView tvStopTitle = null;
        private final androidx.viewpager.widget.ViewPager viewPager = null;
        
        public ViewHolder(@org.jetbrains.annotations.NotNull
        android.view.View view) {
            super(null);
        }
        
        public final android.widget.TextView getTvStopTitle() {
            return null;
        }
        
        public final androidx.viewpager.widget.ViewPager getViewPager() {
            return null;
        }
    }
}