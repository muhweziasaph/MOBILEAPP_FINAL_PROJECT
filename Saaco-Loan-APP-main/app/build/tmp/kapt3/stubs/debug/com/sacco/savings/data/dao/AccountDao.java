package com.sacco.savings.data.dao;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0005\bg\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u001e\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u00a7@\u00a2\u0006\u0002\u0010\fJ\u0018\u0010\r\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u000e2\u0006\u0010\b\u001a\u00020\tH\'J\u0018\u0010\u000f\u001a\u0004\u0018\u00010\u00052\u0006\u0010\b\u001a\u00020\tH\u00a7@\u00a2\u0006\u0002\u0010\u0010J\u0018\u0010\u0011\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0012\u001a\u00020\u0013H\u00a7@\u00a2\u0006\u0002\u0010\u0014J\u001c\u0010\u0015\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00160\u000e2\u0006\u0010\u0017\u001a\u00020\tH\'J\u0016\u0010\u0018\u001a\u00020\t2\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u0016\u0010\u0019\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u001e\u0010\u001a\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u00a7@\u00a2\u0006\u0002\u0010\f\u00a8\u0006\u001b"}, d2 = {"Lcom/sacco/savings/data/dao/AccountDao;", "", "deleteAccount", "", "account", "Lcom/sacco/savings/data/model/Account;", "(Lcom/sacco/savings/data/model/Account;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deposit", "accountId", "", "amount", "", "(JDLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAccountById", "Lkotlinx/coroutines/flow/Flow;", "getAccountByIdSync", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAccountByNumber", "accountNumber", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAccountsByUserId", "", "userId", "insertAccount", "updateAccount", "withdraw", "app_debug"})
@androidx.room.Dao
public abstract interface AccountDao {
    
    @androidx.room.Query(value = "SELECT * FROM accounts WHERE userId = :userId")
    @org.jetbrains.annotations.NotNull
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.sacco.savings.data.model.Account>> getAccountsByUserId(long userId);
    
    @androidx.room.Query(value = "SELECT * FROM accounts WHERE id = :accountId")
    @org.jetbrains.annotations.NotNull
    public abstract kotlinx.coroutines.flow.Flow<com.sacco.savings.data.model.Account> getAccountById(long accountId);
    
    @androidx.room.Query(value = "SELECT * FROM accounts WHERE id = :accountId")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getAccountByIdSync(long accountId, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.sacco.savings.data.model.Account> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM accounts WHERE accountNumber = :accountNumber")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getAccountByNumber(@org.jetbrains.annotations.NotNull
    java.lang.String accountNumber, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.sacco.savings.data.model.Account> $completion);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object insertAccount(@org.jetbrains.annotations.NotNull
    com.sacco.savings.data.model.Account account, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion);
    
    @androidx.room.Update
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object updateAccount(@org.jetbrains.annotations.NotNull
    com.sacco.savings.data.model.Account account, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "UPDATE accounts SET balance = balance + :amount WHERE id = :accountId")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object deposit(long accountId, double amount, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "UPDATE accounts SET balance = balance - :amount WHERE id = :accountId")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object withdraw(long accountId, double amount, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Delete
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object deleteAccount(@org.jetbrains.annotations.NotNull
    com.sacco.savings.data.model.Account account, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
}