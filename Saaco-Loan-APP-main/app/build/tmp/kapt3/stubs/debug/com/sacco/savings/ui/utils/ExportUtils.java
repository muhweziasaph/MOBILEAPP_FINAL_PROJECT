package com.sacco.savings.ui.utils;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J(\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\b\u0010\n\u001a\u0004\u0018\u00010\u000bJ(\u0010\f\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\b\u0010\n\u001a\u0004\u0018\u00010\u000b\u00a8\u0006\r"}, d2 = {"Lcom/sacco/savings/ui/utils/ExportUtils;", "", "()V", "generateCSV", "Landroid/net/Uri;", "context", "Landroid/content/Context;", "transactions", "", "Lcom/sacco/savings/data/model/Transaction;", "accountNumber", "", "generatePDF", "app_debug"})
public final class ExportUtils {
    @org.jetbrains.annotations.NotNull
    public static final com.sacco.savings.ui.utils.ExportUtils INSTANCE = null;
    
    private ExportUtils() {
        super();
    }
    
    @org.jetbrains.annotations.Nullable
    public final android.net.Uri generatePDF(@org.jetbrains.annotations.NotNull
    android.content.Context context, @org.jetbrains.annotations.NotNull
    java.util.List<com.sacco.savings.data.model.Transaction> transactions, @org.jetbrains.annotations.Nullable
    java.lang.String accountNumber) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final android.net.Uri generateCSV(@org.jetbrains.annotations.NotNull
    android.content.Context context, @org.jetbrains.annotations.NotNull
    java.util.List<com.sacco.savings.data.model.Transaction> transactions, @org.jetbrains.annotations.Nullable
    java.lang.String accountNumber) {
        return null;
    }
}