package com.shuttleclone.userapp.ui.activity;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000\u0080\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u00002\u00020\u00012\u00020\u0002:\u0001;B\u0005\u00a2\u0006\u0002\u0010\u0003J\b\u0010/\u001a\u000200H\u0002J\b\u00101\u001a\u000200H\u0002J\u0010\u00102\u001a\u0002002\u0006\u00103\u001a\u000204H\u0016J\u0012\u00105\u001a\u0002002\b\u00106\u001a\u0004\u0018\u000107H\u0014J\b\u00108\u001a\u000200H\u0014J\b\u00109\u001a\u00020:H\u0002R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\tX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\tX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\tX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\tX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\tX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\tX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0013X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0013X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u0017X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u0018\u001a\n\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u0019X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\"\u0010\u001a\u001a\u0016\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u001bj\n\u0012\u0004\u0012\u00020\u0011\u0018\u0001`\u001cX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u001d\u001a\u0004\u0018\u00010\u001eX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u001f\u001a\u0004\u0018\u00010\u001eX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010 \u001a\u0004\u0018\u00010!X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\"\u001a\u0004\u0018\u00010!X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010#\u001a\u0004\u0018\u00010$X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010%\u001a\u0004\u0018\u00010$X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010&\u001a\u0004\u0018\u00010$X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\'\u001a\u0004\u0018\u00010$X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010(\u001a\u0004\u0018\u00010$X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010)\u001a\u0004\u0018\u00010$X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010*\u001a\u0004\u0018\u00010$X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010+\u001a\u0004\u0018\u00010$X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010,\u001a\u0004\u0018\u00010$X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0016\u0010-\u001a\n\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u0019X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\"\u0010.\u001a\u0016\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u001bj\n\u0012\u0004\u0012\u00020\u0011\u0018\u0001`\u001cX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006<"}, d2 = {"Lcom/shuttleclone/userapp/ui/activity/CardDetailActivity;", "Lcom/shuttleclone/userapp/BaseActivity;", "Landroid/view/View$OnClickListener;", "()V", "mBtnBook", "Landroid/widget/Button;", "mCardList", "Lcom/shuttleclone/userapp/model/CardModel;", "mEdCode", "Landroid/widget/EditText;", "mEdCvv", "mEdDigit1", "mEdDigit2", "mEdDigit3", "mEdDigit4", "mEdHolderName", "mFlagValue", "", "mIVHidePwd", "Landroid/widget/ImageView;", "mIvBack", "mIvShowPWd", "mLlContent", "Landroid/widget/LinearLayout;", "mMonthAdapter", "Landroid/widget/ArrayAdapter;", "mMonthList", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "mRlDetail", "Landroid/widget/RelativeLayout;", "mRlHeading", "mSpinMonth", "Landroid/widget/Spinner;", "mSpinYear", "mTvDetail", "Landroid/widget/TextView;", "mTvDropping", "mTvFrom", "mTvOfferCode", "mTvPickup", "mTvTimer", "mTvTitle", "mTvTo", "mTvTotal", "mYearAdapter", "mYearList", "initLayouts", "", "initializeListeners", "onClick", "v", "Landroid/view/View;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onResume", "validate", "", "GenericTextWatcher", "app_debug"})
public final class CardDetailActivity extends com.shuttleclone.userapp.BaseActivity implements android.view.View.OnClickListener {
    @org.jetbrains.annotations.Nullable
    private android.widget.ArrayAdapter<java.lang.String> mYearAdapter;
    @org.jetbrains.annotations.Nullable
    private android.widget.ArrayAdapter<java.lang.String> mMonthAdapter;
    @org.jetbrains.annotations.Nullable
    private android.widget.EditText mEdDigit1;
    @org.jetbrains.annotations.Nullable
    private android.widget.EditText mEdDigit2;
    @org.jetbrains.annotations.Nullable
    private android.widget.EditText mEdDigit3;
    @org.jetbrains.annotations.Nullable
    private android.widget.EditText mEdDigit4;
    @org.jetbrains.annotations.Nullable
    private android.widget.EditText mEdHolderName;
    @org.jetbrains.annotations.Nullable
    private android.widget.EditText mEdCode;
    @org.jetbrains.annotations.Nullable
    private android.widget.EditText mEdCvv;
    @org.jetbrains.annotations.Nullable
    private android.widget.Spinner mSpinYear;
    @org.jetbrains.annotations.Nullable
    private android.widget.Spinner mSpinMonth;
    @org.jetbrains.annotations.Nullable
    private java.lang.String mFlagValue;
    @org.jetbrains.annotations.Nullable
    private android.widget.ImageView mIvBack;
    @org.jetbrains.annotations.Nullable
    private android.widget.ImageView mIvShowPWd;
    @org.jetbrains.annotations.Nullable
    private android.widget.ImageView mIVHidePwd;
    @org.jetbrains.annotations.Nullable
    private java.util.ArrayList<java.lang.String> mYearList;
    @org.jetbrains.annotations.Nullable
    private java.util.ArrayList<java.lang.String> mMonthList;
    @org.jetbrains.annotations.Nullable
    private com.shuttleclone.userapp.model.CardModel mCardList;
    @org.jetbrains.annotations.Nullable
    private android.widget.TextView mTvTo;
    @org.jetbrains.annotations.Nullable
    private android.widget.TextView mTvPickup;
    @org.jetbrains.annotations.Nullable
    private android.widget.TextView mTvDropping;
    @org.jetbrains.annotations.Nullable
    private android.widget.TextView mTvTotal;
    @org.jetbrains.annotations.Nullable
    private android.widget.TextView mTvOfferCode;
    @org.jetbrains.annotations.Nullable
    private android.widget.TextView mTvFrom;
    @org.jetbrains.annotations.Nullable
    private android.widget.TextView mTvDetail;
    @org.jetbrains.annotations.Nullable
    private android.widget.Button mBtnBook;
    @org.jetbrains.annotations.Nullable
    private android.widget.LinearLayout mLlContent;
    @org.jetbrains.annotations.Nullable
    private android.widget.RelativeLayout mRlDetail;
    @org.jetbrains.annotations.Nullable
    private android.widget.RelativeLayout mRlHeading;
    @org.jetbrains.annotations.Nullable
    private android.widget.TextView mTvTitle;
    @org.jetbrains.annotations.Nullable
    private android.widget.TextView mTvTimer;
    
    public CardDetailActivity() {
        super();
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
    
    private final void initializeListeners() {
    }
    
    @java.lang.Override
    public void onClick(@org.jetbrains.annotations.NotNull
    android.view.View v) {
    }
    
    private final boolean validate() {
        return false;
    }
    
    @kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\r\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\b\u0086\u0004\u0018\u00002\u00020\u0001B\u000f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\u0002\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016J(\u0010\t\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\rH\u0016J(\u0010\u0010\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\rH\u0016R\u0010\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0011"}, d2 = {"Lcom/shuttleclone/userapp/ui/activity/CardDetailActivity$GenericTextWatcher;", "Landroid/text/TextWatcher;", "view", "Landroid/view/View;", "(Lcom/shuttleclone/userapp/ui/activity/CardDetailActivity;Landroid/view/View;)V", "afterTextChanged", "", "editable", "Landroid/text/Editable;", "beforeTextChanged", "arg0", "", "arg1", "", "arg2", "arg3", "onTextChanged", "app_debug"})
    public final class GenericTextWatcher implements android.text.TextWatcher {
        @org.jetbrains.annotations.Nullable
        private final android.view.View view = null;
        
        public GenericTextWatcher(@org.jetbrains.annotations.Nullable
        android.view.View view) {
            super();
        }
        
        @java.lang.Override
        public void afterTextChanged(@org.jetbrains.annotations.NotNull
        android.text.Editable editable) {
        }
        
        @java.lang.Override
        public void beforeTextChanged(@org.jetbrains.annotations.NotNull
        java.lang.CharSequence arg0, int arg1, int arg2, int arg3) {
        }
        
        @java.lang.Override
        public void onTextChanged(@org.jetbrains.annotations.NotNull
        java.lang.CharSequence arg0, int arg1, int arg2, int arg3) {
        }
    }
}