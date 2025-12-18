package com.sacco.savings.data.dao;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0007\bg\u0018\u00002\u00020\u0001J&\u0010\u0002\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00032\u0006\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\tH\'J\u0018\u0010\n\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u0006\u001a\u00020\u0007H\u00a7@\u00a2\u0006\u0002\u0010\fJ\u0018\u0010\r\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u0006\u001a\u00020\u0007H\u00a7@\u00a2\u0006\u0002\u0010\fJ\u001c\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00032\u0006\u0010\u0006\u001a\u00020\u0007H\'J\u0016\u0010\u000f\u001a\u00020\u00072\u0006\u0010\u0010\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0011\u00a8\u0006\u0012"}, d2 = {"Lcom/sacco/savings/data/dao/TransactionDao;", "", "getRecentTransactions", "Lkotlinx/coroutines/flow/Flow;", "", "Lcom/sacco/savings/data/model/Transaction;", "accountId", "", "limit", "", "getTotalDeposits", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getTotalWithdrawals", "getTransactionsByAccountId", "insertTransaction", "transaction", "(Lcom/sacco/savings/data/model/Transaction;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
@androidx.room.Dao
public abstract interface TransactionDao {
    
    @androidx.room.Query(value = "SELECT * FROM transactions WHERE accountId = :accountId ORDER BY transactionDate DESC")
    @org.jetbrains.annotations.NotNull
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.sacco.savings.data.model.Transaction>> getTransactionsByAccountId(long accountId);
    
    @androidx.room.Query(value = "SELECT * FROM transactions WHERE accountId = :accountId ORDER BY transactionDate DESC LIMIT :limit")
    @org.jetbrains.annotations.NotNull
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.sacco.savings.data.model.Transaction>> getRecentTransactions(long accountId, int limit);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object insertTransaction(@org.jetbrains.annotations.NotNull
    com.sacco.savings.data.model.Transaction transaction, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion);
    
    @androidx.room.Query(value = "SELECT SUM(amount) FROM transactions WHERE accountId = :accountId AND transactionType = \'DEPOSIT\'")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getTotalDeposits(long accountId, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Double> $completion);
    
    @androidx.room.Query(value = "SELECT SUM(amount) FROM transactions WHERE accountId = :accountId AND transactionType = \'WITHDRAWAL\'")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getTotalWithdrawals(long accountId, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Double> $completion);
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 3, xi = 48)
    public static final class DefaultImpls {
    }
}