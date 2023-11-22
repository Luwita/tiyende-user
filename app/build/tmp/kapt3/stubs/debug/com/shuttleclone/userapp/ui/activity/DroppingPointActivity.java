package com.shuttleclone.userapp.ui.activity;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \u001e2\u00020\u00012\u00020\u0002:\u0001\u001eB\u0005\u00a2\u0006\u0002\u0010\u0003J\b\u0010\u0014\u001a\u00020\u0015H\u0002J\b\u0010\u0016\u001a\u00020\u0015H\u0002J\u0010\u0010\u0017\u001a\u00020\u00152\u0006\u0010\u0018\u001a\u00020\u0019H\u0016J\u0012\u0010\u001a\u001a\u00020\u00152\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cH\u0014J\b\u0010\u001d\u001a\u00020\u0015H\u0014R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0016\u0010\f\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u000fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0012X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001f"}, d2 = {"Lcom/shuttleclone/userapp/ui/activity/DroppingPointActivity;", "Lcom/shuttleclone/userapp/BaseActivity;", "Landroid/view/View$OnClickListener;", "()V", "mDroppingAdapter", "Lcom/shuttleclone/userapp/ui/adapter/DroppingAdapter;", "mDroppingList", "", "Lcom/shuttleclone/userapp/model/DroppingModel;", "mIvBack", "Landroid/widget/ImageView;", "mIvHome", "mPickUpList", "mPickupAdapter", "mRvDropping", "Landroidx/recyclerview/widget/RecyclerView;", "mRvPickup", "mTvDropping", "Landroid/widget/TextView;", "mTvPickup", "initLayouts", "", "initializeListeners", "onClick", "v", "Landroid/view/View;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onResume", "Companion", "app_debug"})
public final class DroppingPointActivity extends com.shuttleclone.userapp.BaseActivity implements android.view.View.OnClickListener {
    @org.jetbrains.annotations.Nullable
    private android.widget.TextView mTvPickup;
    @org.jetbrains.annotations.Nullable
    private android.widget.TextView mTvDropping;
    @org.jetbrains.annotations.Nullable
    private androidx.recyclerview.widget.RecyclerView mRvPickup;
    @org.jetbrains.annotations.Nullable
    private androidx.recyclerview.widget.RecyclerView mRvDropping;
    @org.jetbrains.annotations.Nullable
    private java.util.List<com.shuttleclone.userapp.model.DroppingModel> mPickUpList;
    @org.jetbrains.annotations.Nullable
    private java.util.List<com.shuttleclone.userapp.model.DroppingModel> mDroppingList;
    @org.jetbrains.annotations.Nullable
    private com.shuttleclone.userapp.ui.adapter.DroppingAdapter mPickupAdapter;
    @org.jetbrains.annotations.Nullable
    private com.shuttleclone.userapp.ui.adapter.DroppingAdapter mDroppingAdapter;
    @org.jetbrains.annotations.Nullable
    private android.widget.ImageView mIvBack;
    @org.jetbrains.annotations.Nullable
    private android.widget.ImageView mIvHome;
    @org.jetbrains.annotations.Nullable
    private static java.lang.String mPickup;
    @org.jetbrains.annotations.Nullable
    private static java.lang.String mDropping;
    @org.jetbrains.annotations.NotNull
    public static final com.shuttleclone.userapp.ui.activity.DroppingPointActivity.Companion Companion = null;
    
    public DroppingPointActivity() {
        super();
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
    
    private final void initLayouts() {
    }
    
    @java.lang.Override
    public void onClick(@org.jetbrains.annotations.NotNull
    android.view.View v) {
    }
    
    @kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\b\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001c\u0010\t\u001a\u0004\u0018\u00010\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0006\"\u0004\b\u000b\u0010\b\u00a8\u0006\f"}, d2 = {"Lcom/shuttleclone/userapp/ui/activity/DroppingPointActivity$Companion;", "", "()V", "mDropping", "", "getMDropping", "()Ljava/lang/String;", "setMDropping", "(Ljava/lang/String;)V", "mPickup", "getMPickup", "setMPickup", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.Nullable
        public final java.lang.String getMPickup() {
            return null;
        }
        
        public final void setMPickup(@org.jetbrains.annotations.Nullable
        java.lang.String p0) {
        }
        
        @org.jetbrains.annotations.Nullable
        public final java.lang.String getMDropping() {
            return null;
        }
        
        public final void setMDropping(@org.jetbrains.annotations.Nullable
        java.lang.String p0) {
        }
    }
}