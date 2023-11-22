package com.shuttleclone.userapp.model;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b4\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u00f5\u0001\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\u0010\b\u0002\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u0003\u0012\u0010\b\u0002\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\u0002\u0010\u0019J\u000b\u00100\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u00101\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u0011\u00102\u001a\n\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u0006H\u00c6\u0003J\u000b\u00103\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u00104\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u00105\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u00106\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u00107\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u00108\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u00109\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u0010:\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u0010;\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u0011\u0010<\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0006H\u00c6\u0003J\u000b\u0010=\u001a\u0004\u0018\u00010\bH\u00c6\u0003J\u000b\u0010>\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u0010?\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u0010@\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u0010A\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u0010B\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u00f9\u0001\u0010C\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\u0010\b\u0002\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u00032\u0010\b\u0002\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u00062\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u0003H\u00c6\u0001J\u0013\u0010D\u001a\u00020E2\b\u0010F\u001a\u0004\u0018\u00010GH\u00d6\u0003J\t\u0010H\u001a\u00020IH\u00d6\u0001J\t\u0010J\u001a\u00020\u0003H\u00d6\u0001R\u001e\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u00068\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0018\u0010\t\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0018\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001dR\u0018\u0010\u0011\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u001dR\u0018\u0010\n\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b \u0010\u001dR\u0018\u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b!\u0010\u001dR\u0018\u0010\u0007\u001a\u0004\u0018\u00010\b8\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\"\u0010#R\u0018\u0010\u0017\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b$\u0010\u001dR\u0018\u0010\u0014\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b%\u0010\u001dR\u0018\u0010\u0015\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b&\u0010\u001dR\u0018\u0010\f\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\'\u0010\u001dR\u0018\u0010\u000b\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b(\u0010\u001dR\u001e\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u00068\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b)\u0010\u001bR\u0018\u0010\u0012\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b*\u0010\u001dR\u0018\u0010\u0013\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b+\u0010\u001dR\u0018\u0010\u0016\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b,\u0010\u001dR\u0018\u0010\r\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b-\u0010\u001dR\u0018\u0010\u000e\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b.\u0010\u001dR\u0018\u0010\u0018\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b/\u0010\u001d\u00a8\u0006K"}, d2 = {"Lcom/shuttleclone/userapp/model/BusData;", "Ljava/io/Serializable;", "busModelNo", "", "busType", "busAmenities", "", "buslayout", "Lcom/shuttleclone/userapp/model/BusLayout;", "busBrand", "busRegNo", "id", "final_total_fare", "tax", "taxAmount", "passesResponseModel", "Lcom/shuttleclone/userapp/model/PassesList;", "busName", "pickupName", "pickupTime", "dropName", "dropTime", "seatNo", "createdDate", "userWalletAmount", "(Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Lcom/shuttleclone/userapp/model/BusLayout;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getBusAmenities", "()Ljava/util/List;", "getBusBrand", "()Ljava/lang/String;", "getBusModelNo", "getBusName", "getBusRegNo", "getBusType", "getBuslayout", "()Lcom/shuttleclone/userapp/model/BusLayout;", "getCreatedDate", "getDropName", "getDropTime", "getFinal_total_fare", "getId", "getPassesResponseModel", "getPickupName", "getPickupTime", "getSeatNo", "getTax", "getTaxAmount", "getUserWalletAmount", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "", "other", "", "hashCode", "", "toString", "app_debug"})
public final class BusData implements java.io.Serializable {
    @com.google.gson.annotations.SerializedName(value = "bus_model_no")
    @org.jetbrains.annotations.Nullable
    private final java.lang.String busModelNo = null;
    @com.google.gson.annotations.SerializedName(value = "bus_type")
    @org.jetbrains.annotations.Nullable
    private final java.lang.String busType = null;
    @com.google.gson.annotations.SerializedName(value = "bus_amenities")
    @org.jetbrains.annotations.Nullable
    private final java.util.List<java.lang.String> busAmenities = null;
    @com.google.gson.annotations.SerializedName(value = "buslayoutId")
    @org.jetbrains.annotations.Nullable
    private final com.shuttleclone.userapp.model.BusLayout buslayout = null;
    @com.google.gson.annotations.SerializedName(value = "bus_brand")
    @org.jetbrains.annotations.Nullable
    private final java.lang.String busBrand = null;
    @com.google.gson.annotations.SerializedName(value = "bus_reg_no")
    @org.jetbrains.annotations.Nullable
    private final java.lang.String busRegNo = null;
    @com.google.gson.annotations.SerializedName(value = "id")
    @org.jetbrains.annotations.Nullable
    private final java.lang.String id = null;
    @com.google.gson.annotations.SerializedName(value = "final_total_fare")
    @org.jetbrains.annotations.Nullable
    private final java.lang.String final_total_fare = null;
    @com.google.gson.annotations.SerializedName(value = "tax")
    @org.jetbrains.annotations.Nullable
    private final java.lang.String tax = null;
    @com.google.gson.annotations.SerializedName(value = "tax_amount")
    @org.jetbrains.annotations.Nullable
    private final java.lang.String taxAmount = null;
    @com.google.gson.annotations.SerializedName(value = "final_pass_fare")
    @org.jetbrains.annotations.Nullable
    private final java.util.List<com.shuttleclone.userapp.model.PassesList> passesResponseModel = null;
    @com.google.gson.annotations.SerializedName(value = "bus_name")
    @org.jetbrains.annotations.Nullable
    private final java.lang.String busName = null;
    @com.google.gson.annotations.SerializedName(value = "pickup_name")
    @org.jetbrains.annotations.Nullable
    private final java.lang.String pickupName = null;
    @com.google.gson.annotations.SerializedName(value = "pickup_time")
    @org.jetbrains.annotations.Nullable
    private final java.lang.String pickupTime = null;
    @com.google.gson.annotations.SerializedName(value = "drop_name")
    @org.jetbrains.annotations.Nullable
    private final java.lang.String dropName = null;
    @com.google.gson.annotations.SerializedName(value = "drop_time")
    @org.jetbrains.annotations.Nullable
    private final java.lang.String dropTime = null;
    @com.google.gson.annotations.SerializedName(value = "seat_no")
    @org.jetbrains.annotations.Nullable
    private final java.lang.String seatNo = null;
    @com.google.gson.annotations.SerializedName(value = "created_date")
    @org.jetbrains.annotations.Nullable
    private final java.lang.String createdDate = null;
    @com.google.gson.annotations.SerializedName(value = "user_total_wallet_amount")
    @org.jetbrains.annotations.Nullable
    private final java.lang.String userWalletAmount = null;
    
    public BusData(@org.jetbrains.annotations.Nullable
    java.lang.String busModelNo, @org.jetbrains.annotations.Nullable
    java.lang.String busType, @org.jetbrains.annotations.Nullable
    java.util.List<java.lang.String> busAmenities, @org.jetbrains.annotations.Nullable
    com.shuttleclone.userapp.model.BusLayout buslayout, @org.jetbrains.annotations.Nullable
    java.lang.String busBrand, @org.jetbrains.annotations.Nullable
    java.lang.String busRegNo, @org.jetbrains.annotations.Nullable
    java.lang.String id, @org.jetbrains.annotations.Nullable
    java.lang.String final_total_fare, @org.jetbrains.annotations.Nullable
    java.lang.String tax, @org.jetbrains.annotations.Nullable
    java.lang.String taxAmount, @org.jetbrains.annotations.Nullable
    java.util.List<com.shuttleclone.userapp.model.PassesList> passesResponseModel, @org.jetbrains.annotations.Nullable
    java.lang.String busName, @org.jetbrains.annotations.Nullable
    java.lang.String pickupName, @org.jetbrains.annotations.Nullable
    java.lang.String pickupTime, @org.jetbrains.annotations.Nullable
    java.lang.String dropName, @org.jetbrains.annotations.Nullable
    java.lang.String dropTime, @org.jetbrains.annotations.Nullable
    java.lang.String seatNo, @org.jetbrains.annotations.Nullable
    java.lang.String createdDate, @org.jetbrains.annotations.Nullable
    java.lang.String userWalletAmount) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getBusModelNo() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getBusType() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.util.List<java.lang.String> getBusAmenities() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final com.shuttleclone.userapp.model.BusLayout getBuslayout() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getBusBrand() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getBusRegNo() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getId() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getFinal_total_fare() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getTax() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getTaxAmount() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.util.List<com.shuttleclone.userapp.model.PassesList> getPassesResponseModel() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getBusName() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getPickupName() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getPickupTime() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getDropName() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getDropTime() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getSeatNo() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getCreatedDate() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getUserWalletAmount() {
        return null;
    }
    
    public BusData() {
        super();
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component1() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component10() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.util.List<com.shuttleclone.userapp.model.PassesList> component11() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component12() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component13() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component14() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component15() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component16() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component17() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component18() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component19() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component2() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.util.List<java.lang.String> component3() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final com.shuttleclone.userapp.model.BusLayout component4() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component5() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component6() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component7() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component8() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component9() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.shuttleclone.userapp.model.BusData copy(@org.jetbrains.annotations.Nullable
    java.lang.String busModelNo, @org.jetbrains.annotations.Nullable
    java.lang.String busType, @org.jetbrains.annotations.Nullable
    java.util.List<java.lang.String> busAmenities, @org.jetbrains.annotations.Nullable
    com.shuttleclone.userapp.model.BusLayout buslayout, @org.jetbrains.annotations.Nullable
    java.lang.String busBrand, @org.jetbrains.annotations.Nullable
    java.lang.String busRegNo, @org.jetbrains.annotations.Nullable
    java.lang.String id, @org.jetbrains.annotations.Nullable
    java.lang.String final_total_fare, @org.jetbrains.annotations.Nullable
    java.lang.String tax, @org.jetbrains.annotations.Nullable
    java.lang.String taxAmount, @org.jetbrains.annotations.Nullable
    java.util.List<com.shuttleclone.userapp.model.PassesList> passesResponseModel, @org.jetbrains.annotations.Nullable
    java.lang.String busName, @org.jetbrains.annotations.Nullable
    java.lang.String pickupName, @org.jetbrains.annotations.Nullable
    java.lang.String pickupTime, @org.jetbrains.annotations.Nullable
    java.lang.String dropName, @org.jetbrains.annotations.Nullable
    java.lang.String dropTime, @org.jetbrains.annotations.Nullable
    java.lang.String seatNo, @org.jetbrains.annotations.Nullable
    java.lang.String createdDate, @org.jetbrains.annotations.Nullable
    java.lang.String userWalletAmount) {
        return null;
    }
    
    @java.lang.Override
    public boolean equals(@org.jetbrains.annotations.Nullable
    java.lang.Object other) {
        return false;
    }
    
    @java.lang.Override
    public int hashCode() {
        return 0;
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.NotNull
    public java.lang.String toString() {
        return null;
    }
}