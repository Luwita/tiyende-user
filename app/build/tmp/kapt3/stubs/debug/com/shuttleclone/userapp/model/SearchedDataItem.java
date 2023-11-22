package com.shuttleclone.userapp.model;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0015\u0018\u00002\u00020\u0001BM\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\u0006\u0010\n\u001a\u00020\u0003\u0012\u0006\u0010\u000b\u001a\u00020\u0003\u0012\u0006\u0010\f\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\rR\u0016\u0010\n\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0016\u0010\b\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000fR\u0016\u0010\u0004\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000fR\u0016\u0010\u0005\u001a\u00020\u00068\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0016\u0010\u0007\u001a\u00020\u00068\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0013R\u001e\u0010\f\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u000f\"\u0004\b\u0016\u0010\u0017R\u0016\u0010\t\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u000fR\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u000fR\u0016\u0010\u000b\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u000f\u00a8\u0006\u001b"}, d2 = {"Lcom/shuttleclone/userapp/model/SearchedDataItem;", "", "title", "", "locationAddress", "locationLatitude", "", "locationLongitude", "id", "state", "city", "type", "nearDistance", "(Ljava/lang/String;Ljava/lang/String;DDLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getCity", "()Ljava/lang/String;", "getId", "getLocationAddress", "getLocationLatitude", "()D", "getLocationLongitude", "getNearDistance", "setNearDistance", "(Ljava/lang/String;)V", "getState", "getTitle", "getType", "app_debug"})
public final class SearchedDataItem {
    @com.google.gson.annotations.SerializedName(value = "title")
    @org.jetbrains.annotations.NotNull
    private final java.lang.String title = null;
    @com.google.gson.annotations.SerializedName(value = "location_address")
    @org.jetbrains.annotations.NotNull
    private final java.lang.String locationAddress = null;
    @com.google.gson.annotations.SerializedName(value = "location_latitude")
    private final double locationLatitude = 0.0;
    @com.google.gson.annotations.SerializedName(value = "location_longitude")
    private final double locationLongitude = 0.0;
    @com.google.gson.annotations.SerializedName(value = "id")
    @org.jetbrains.annotations.NotNull
    private final java.lang.String id = null;
    @com.google.gson.annotations.SerializedName(value = "state")
    @org.jetbrains.annotations.NotNull
    private final java.lang.String state = null;
    @com.google.gson.annotations.SerializedName(value = "city")
    @org.jetbrains.annotations.NotNull
    private final java.lang.String city = null;
    @com.google.gson.annotations.SerializedName(value = "type")
    @org.jetbrains.annotations.NotNull
    private final java.lang.String type = null;
    @com.google.gson.annotations.SerializedName(value = "near_distance")
    @org.jetbrains.annotations.NotNull
    private java.lang.String nearDistance;
    
    public SearchedDataItem(@org.jetbrains.annotations.NotNull
    java.lang.String title, @org.jetbrains.annotations.NotNull
    java.lang.String locationAddress, double locationLatitude, double locationLongitude, @org.jetbrains.annotations.NotNull
    java.lang.String id, @org.jetbrains.annotations.NotNull
    java.lang.String state, @org.jetbrains.annotations.NotNull
    java.lang.String city, @org.jetbrains.annotations.NotNull
    java.lang.String type, @org.jetbrains.annotations.NotNull
    java.lang.String nearDistance) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getTitle() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getLocationAddress() {
        return null;
    }
    
    public final double getLocationLatitude() {
        return 0.0;
    }
    
    public final double getLocationLongitude() {
        return 0.0;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getId() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getCity() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getType() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getNearDistance() {
        return null;
    }
    
    public final void setNearDistance(@org.jetbrains.annotations.NotNull
    java.lang.String p0) {
    }
}