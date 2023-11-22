package com.shuttleclone.userapp.ui.activity;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B\u0005\u00a2\u0006\u0002\u0010\u0004J\b\u0010\u000f\u001a\u00020\u0010H\u0002J\b\u0010\u0011\u001a\u00020\u0010H\u0002J\u0010\u0010\u0012\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J\u0010\u0010\u0012\u001a\u00020\u00102\u0006\u0010\u0015\u001a\u00020\u000bH\u0016J\u0012\u0010\u0016\u001a\u00020\u00102\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0014J\b\u0010\u0019\u001a\u00020\u0010H\u0014R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\"\u0010\t\u001a\u0016\u0012\u0004\u0012\u00020\u000b\u0018\u00010\nj\n\u0012\u0004\u0012\u00020\u000b\u0018\u0001`\fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001a"}, d2 = {"Lcom/shuttleclone/userapp/ui/activity/PackageActivity;", "Lcom/shuttleclone/userapp/BaseActivity;", "Landroid/view/View$OnClickListener;", "Lcom/shuttleclone/userapp/ui/adapter/ViewPackageAdapter$onClickListener;", "()V", "mAdapter", "Lcom/shuttleclone/userapp/ui/adapter/ViewPackageAdapter;", "mIvBack", "Landroid/widget/ImageView;", "mPackageList", "Ljava/util/ArrayList;", "Lcom/shuttleclone/userapp/model/NewPackageModel;", "Lkotlin/collections/ArrayList;", "mRvOffer", "Landroidx/recyclerview/widget/RecyclerView;", "initLayouts", "", "initializeListeners", "onClick", "v", "Landroid/view/View;", "aPackageModel", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onResume", "app_debug"})
public final class PackageActivity extends com.shuttleclone.userapp.BaseActivity implements android.view.View.OnClickListener, com.shuttleclone.userapp.ui.adapter.ViewPackageAdapter.onClickListener {
    @org.jetbrains.annotations.Nullable
    private androidx.recyclerview.widget.RecyclerView mRvOffer;
    @org.jetbrains.annotations.Nullable
    private java.util.ArrayList<com.shuttleclone.userapp.model.NewPackageModel> mPackageList;
    @org.jetbrains.annotations.Nullable
    private android.widget.ImageView mIvBack;
    @org.jetbrains.annotations.Nullable
    private com.shuttleclone.userapp.ui.adapter.ViewPackageAdapter mAdapter;
    
    public PackageActivity() {
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
    
    @java.lang.Override
    public void onClick(@org.jetbrains.annotations.NotNull
    com.shuttleclone.userapp.model.NewPackageModel aPackageModel) {
    }
}