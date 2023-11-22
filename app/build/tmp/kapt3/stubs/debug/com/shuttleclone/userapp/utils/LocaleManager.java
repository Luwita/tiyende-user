package com.shuttleclone.userapp.utils;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\u0018\u0000 \u000f2\u00020\u0001:\u0001\u000fB\u0005\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bJ\u0016\u0010\n\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\fJ\u0018\u0010\r\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\fH\u0002\u00a8\u0006\u0010"}, d2 = {"Lcom/shuttleclone/userapp/utils/LocaleManager;", "", "()V", "getLocale", "Ljava/util/Locale;", "res", "Landroid/content/res/Resources;", "setLocale", "Landroid/content/Context;", "mContext", "setNewLocale", "language", "", "updateResources", "context", "Companion", "app_debug"})
public final class LocaleManager {
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String ENGLISH = "en";
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String ARABIC = "ar";
    @org.jetbrains.annotations.NotNull
    public static final com.shuttleclone.userapp.utils.LocaleManager.Companion Companion = null;
    
    public LocaleManager() {
        super();
    }
    
    /**
     * set current pref locale
     */
    @org.jetbrains.annotations.NotNull
    public final android.content.Context setLocale(@org.jetbrains.annotations.NotNull
    android.content.Context mContext) {
        return null;
    }
    
    /**
     * Set new Locale with context
     */
    @org.jetbrains.annotations.NotNull
    public final android.content.Context setNewLocale(@org.jetbrains.annotations.NotNull
    android.content.Context mContext, @org.jetbrains.annotations.NotNull
    java.lang.String language) {
        return null;
    }
    
    /**
     * update resource
     */
    private final android.content.Context updateResources(android.content.Context context, java.lang.String language) {
        return null;
    }
    
    /**
     * get current locale
     */
    @org.jetbrains.annotations.NotNull
    public final java.util.Locale getLocale(@org.jetbrains.annotations.NotNull
    android.content.res.Resources res) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0006"}, d2 = {"Lcom/shuttleclone/userapp/utils/LocaleManager$Companion;", "", "()V", "ARABIC", "", "ENGLISH", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}