package com.shuttleclone.userapp.model;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\t\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002R\u0018\u0010\u0003\u001a\u0004\u0018\u00010\u00048\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0018\u0010\u0007\u001a\u0004\u0018\u00010\u00048\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0006R\u0018\u0010\t\u001a\u0004\u0018\u00010\u00048\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0006R\u0018\u0010\u000b\u001a\u0004\u0018\u00010\u00048\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u0006R\u0016\u0010\r\u001a\u00020\u000e8\u0006X\u0087D\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010\u00a8\u0006\u0011"}, d2 = {"Lcom/shuttleclone/userapp/model/UserDetail;", "Ljava/io/Serializable;", "()V", "email", "", "getEmail", "()Ljava/lang/String;", "firstname", "getFirstname", "gender", "getGender", "lastname", "getLastname", "phone", "", "getPhone", "()J", "app_debug"})
public final class UserDetail implements java.io.Serializable {
    @com.google.gson.annotations.SerializedName(value = "firstname")
    @org.jetbrains.annotations.Nullable
    private final java.lang.String firstname = null;
    @com.google.gson.annotations.SerializedName(value = "gender")
    @org.jetbrains.annotations.Nullable
    private final java.lang.String gender = null;
    @com.google.gson.annotations.SerializedName(value = "phone")
    private final long phone = 0L;
    @com.google.gson.annotations.SerializedName(value = "email")
    @org.jetbrains.annotations.Nullable
    private final java.lang.String email = null;
    @com.google.gson.annotations.SerializedName(value = "lastname")
    @org.jetbrains.annotations.Nullable
    private final java.lang.String lastname = null;
    
    public UserDetail() {
        super();
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getFirstname() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getGender() {
        return null;
    }
    
    public final long getPhone() {
        return 0L;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getEmail() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getLastname() {
        return null;
    }
}