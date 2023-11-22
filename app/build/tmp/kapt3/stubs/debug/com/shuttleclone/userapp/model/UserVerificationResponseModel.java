package com.shuttleclone.userapp.model;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002R\u0018\u0010\u0003\u001a\u0004\u0018\u00010\u00048\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0018\u0010\u0007\u001a\u0004\u0018\u00010\u00048\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0006R\u0016\u0010\t\u001a\u00020\n8\u0006X\u0087D\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0016\u0010\r\u001a\u00020\u000e8\u0006X\u0087D\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000fR\u0018\u0010\u0010\u001a\u0004\u0018\u00010\u00048\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0006R\u0016\u0010\u0012\u001a\u00020\n8\u0006X\u0087D\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\fR\u0018\u0010\u0014\u001a\u0004\u0018\u00010\u00048\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0006R\u0018\u0010\u0016\u001a\u0004\u0018\u00010\u00178\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019\u00a8\u0006\u001a"}, d2 = {"Lcom/shuttleclone/userapp/model/UserVerificationResponseModel;", "Ljava/io/Serializable;", "()V", "csrfToken", "", "getCsrfToken", "()Ljava/lang/String;", "errorMessage", "getErrorMessage", "flag", "", "getFlag", "()I", "isStatus", "", "()Z", "message", "getMessage", "otp", "getOtp", "token", "getToken", "userDetail", "Lcom/shuttleclone/userapp/model/UserDetail;", "getUserDetail", "()Lcom/shuttleclone/userapp/model/UserDetail;", "app_debug"})
public final class UserVerificationResponseModel implements java.io.Serializable {
    @com.google.gson.annotations.SerializedName(value = "flag")
    private final int flag = 0;
    @com.google.gson.annotations.SerializedName(value = "csrfToken")
    @org.jetbrains.annotations.Nullable
    private final java.lang.String csrfToken = null;
    @com.google.gson.annotations.SerializedName(value = "userDetail")
    @org.jetbrains.annotations.Nullable
    private final com.shuttleclone.userapp.model.UserDetail userDetail = null;
    @com.google.gson.annotations.SerializedName(value = "otp")
    private final int otp = 0;
    @com.google.gson.annotations.SerializedName(value = "message")
    @org.jetbrains.annotations.Nullable
    private final java.lang.String message = null;
    @com.google.gson.annotations.SerializedName(value = "errorMessage")
    @org.jetbrains.annotations.Nullable
    private final java.lang.String errorMessage = null;
    @com.google.gson.annotations.SerializedName(value = "status")
    private final boolean isStatus = false;
    @com.google.gson.annotations.SerializedName(value = "token")
    @org.jetbrains.annotations.Nullable
    private final java.lang.String token = null;
    
    public UserVerificationResponseModel() {
        super();
    }
    
    public final int getFlag() {
        return 0;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getCsrfToken() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final com.shuttleclone.userapp.model.UserDetail getUserDetail() {
        return null;
    }
    
    public final int getOtp() {
        return 0;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getMessage() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getErrorMessage() {
        return null;
    }
    
    public final boolean isStatus() {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getToken() {
        return null;
    }
}