package com.shuttleclone.userapp.AppDb;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\bJ\u0006\u0010\u001a\u001a\u00020\u0018J\u0012\u0010\u001b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006J\u000e\u0010\u001c\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\bJ\u000e\u0010\u001d\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\bR\'\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u00068BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\t\u0010\nR\u001b\u0010\r\u001a\u00020\u000e8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0011\u0010\f\u001a\u0004\b\u000f\u0010\u0010R\u001b\u0010\u0012\u001a\u00020\u00138BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0016\u0010\f\u001a\u0004\b\u0014\u0010\u0015\u00a8\u0006\u001e"}, d2 = {"Lcom/shuttleclone/userapp/AppDb/AppDbRepository;", "", "application", "Landroid/app/Application;", "(Landroid/app/Application;)V", "allRecords", "Landroidx/lifecycle/LiveData;", "", "Lcom/shuttleclone/userapp/AppDb/models/RecentSearchData;", "getAllRecords", "()Landroidx/lifecycle/LiveData;", "allRecords$delegate", "Lkotlin/Lazy;", "database", "Lcom/shuttleclone/userapp/AppDb/AppDatabase;", "getDatabase", "()Lcom/shuttleclone/userapp/AppDb/AppDatabase;", "database$delegate", "recentSearchDao", "Lcom/shuttleclone/userapp/AppDb/Dao/RecentSearchDao;", "getRecentSearchDao", "()Lcom/shuttleclone/userapp/AppDb/Dao/RecentSearchDao;", "recentSearchDao$delegate", "delete", "", "recentSearchData", "deleteAllRecentSearchData", "getAllRecentSearchData", "insert", "update", "app_debug"})
public final class AppDbRepository {
    @org.jetbrains.annotations.NotNull
    private final kotlin.Lazy database$delegate = null;
    @org.jetbrains.annotations.NotNull
    private final kotlin.Lazy recentSearchDao$delegate = null;
    @org.jetbrains.annotations.NotNull
    private final kotlin.Lazy allRecords$delegate = null;
    
    public AppDbRepository(@org.jetbrains.annotations.NotNull
    android.app.Application application) {
        super();
    }
    
    private final com.shuttleclone.userapp.AppDb.AppDatabase getDatabase() {
        return null;
    }
    
    private final com.shuttleclone.userapp.AppDb.Dao.RecentSearchDao getRecentSearchDao() {
        return null;
    }
    
    private final androidx.lifecycle.LiveData<java.util.List<com.shuttleclone.userapp.AppDb.models.RecentSearchData>> getAllRecords() {
        return null;
    }
    
    public final void insert(@org.jetbrains.annotations.NotNull
    com.shuttleclone.userapp.AppDb.models.RecentSearchData recentSearchData) {
    }
    
    public final void update(@org.jetbrains.annotations.NotNull
    com.shuttleclone.userapp.AppDb.models.RecentSearchData recentSearchData) {
    }
    
    public final void delete(@org.jetbrains.annotations.NotNull
    com.shuttleclone.userapp.AppDb.models.RecentSearchData recentSearchData) {
    }
    
    public final void deleteAllRecentSearchData() {
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.LiveData<java.util.List<com.shuttleclone.userapp.AppDb.models.RecentSearchData>> getAllRecentSearchData() {
        return null;
    }
}