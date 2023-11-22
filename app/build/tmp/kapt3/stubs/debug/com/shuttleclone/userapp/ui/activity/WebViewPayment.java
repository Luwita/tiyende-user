package com.shuttleclone.userapp.ui.activity;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u00012\u00020\u0002:\u0001 B\u0005\u00a2\u0006\u0002\u0010\u0003J\u0016\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014J\b\u0010\u0015\u001a\u00020\u0010H\u0002J\b\u0010\u0016\u001a\u00020\u0010H\u0002J\b\u0010\u0017\u001a\u00020\u0010H\u0016J\u0010\u0010\u0018\u001a\u00020\u00102\u0006\u0010\u0019\u001a\u00020\u001aH\u0016J\u0012\u0010\u001b\u001a\u00020\u00102\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0014J\b\u0010\u001e\u001a\u00020\u0010H\u0014J\b\u0010\u001f\u001a\u00020\u0010H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006!"}, d2 = {"Lcom/shuttleclone/userapp/ui/activity/WebViewPayment;", "Lcom/shuttleclone/userapp/BaseActivity;", "Landroid/view/View$OnClickListener;", "()V", "TAG", "", "gatewayUrl", "mAlertDialog", "Landroid/app/Dialog;", "mIvBack", "Landroid/widget/ImageView;", "mIvNotification", "mWebViewPayment", "Landroid/webkit/WebView;", "paymentName", "alertDialog", "", "mContext", "Landroid/content/Context;", "alert", "", "initLayouts", "initializeListeners", "onBackPressed", "onClick", "v", "Landroid/view/View;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onResume", "setUpWebViewClient", "WebAppInterface", "app_debug"})
public final class WebViewPayment extends com.shuttleclone.userapp.BaseActivity implements android.view.View.OnClickListener {
    @org.jetbrains.annotations.Nullable
    private android.widget.ImageView mIvBack;
    @org.jetbrains.annotations.Nullable
    private android.widget.ImageView mIvNotification;
    @org.jetbrains.annotations.Nullable
    private android.webkit.WebView mWebViewPayment;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String TAG = "WebViewPayment";
    @org.jetbrains.annotations.NotNull
    private java.lang.String gatewayUrl = "gatewayUrl";
    @org.jetbrains.annotations.NotNull
    private java.lang.String paymentName = "paymentName";
    @org.jetbrains.annotations.Nullable
    private android.app.Dialog mAlertDialog;
    
    public WebViewPayment() {
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
    public void onBackPressed() {
    }
    
    public final void alertDialog(@org.jetbrains.annotations.NotNull
    android.content.Context mContext, @org.jetbrains.annotations.NotNull
    java.lang.Object alert) {
    }
    
    private final void setUpWebViewClient() {
    }
    
    @java.lang.Override
    public void onClick(@org.jetbrains.annotations.NotNull
    android.view.View v) {
    }
    
    @kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0007R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\u0004\u00a8\u0006\f"}, d2 = {"Lcom/shuttleclone/userapp/ui/activity/WebViewPayment$WebAppInterface;", "", "mContext", "Lcom/shuttleclone/userapp/ui/activity/WebViewPayment;", "(Lcom/shuttleclone/userapp/ui/activity/WebViewPayment;)V", "getMContext", "()Lcom/shuttleclone/userapp/ui/activity/WebViewPayment;", "setMContext", "paymentResponse", "", "data", "", "app_debug"})
    public static final class WebAppInterface {
        @org.jetbrains.annotations.NotNull
        private com.shuttleclone.userapp.ui.activity.WebViewPayment mContext;
        
        public WebAppInterface(@org.jetbrains.annotations.NotNull
        com.shuttleclone.userapp.ui.activity.WebViewPayment mContext) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull
        public final com.shuttleclone.userapp.ui.activity.WebViewPayment getMContext() {
            return null;
        }
        
        public final void setMContext(@org.jetbrains.annotations.NotNull
        com.shuttleclone.userapp.ui.activity.WebViewPayment p0) {
        }
        
        @android.webkit.JavascriptInterface
        public final void paymentResponse(@org.jetbrains.annotations.NotNull
        java.lang.String data) {
        }
    }
}