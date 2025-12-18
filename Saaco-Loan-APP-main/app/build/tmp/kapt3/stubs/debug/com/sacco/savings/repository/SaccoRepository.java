package com.sacco.savings.repository;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\f\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ \u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\n2\b\b\u0002\u0010\f\u001a\u00020\rH\u0086@\u00a2\u0006\u0002\u0010\u000eJ\u0016\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0086@\u00a2\u0006\u0002\u0010\u0013J&\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\n2\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\rH\u0086@\u00a2\u0006\u0002\u0010\u001aJ\b\u0010\u001b\u001a\u00020\rH\u0002J\b\u0010\u001c\u001a\u00020\rH\u0002J\u0016\u0010\u001d\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001f0\u001e2\u0006\u0010\u0016\u001a\u00020\nJ\u001a\u0010 \u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001f0!0\u001e2\u0006\u0010\u000b\u001a\u00020\nJ\u0012\u0010\"\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120!0\u001eJ$\u0010#\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020$0!0\u001e2\u0006\u0010\u0016\u001a\u00020\n2\b\b\u0002\u0010%\u001a\u00020&J\u001a\u0010\'\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020$0!0\u001e2\u0006\u0010\u0016\u001a\u00020\nJ\u0018\u0010(\u001a\u0004\u0018\u00010\u00122\u0006\u0010)\u001a\u00020\rH\u0086@\u00a2\u0006\u0002\u0010*J\u0016\u0010+\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00120\u001e2\u0006\u0010\u000b\u001a\u00020\nJ \u0010,\u001a\u0004\u0018\u00010\u00122\u0006\u0010)\u001a\u00020\r2\u0006\u0010-\u001a\u00020\rH\u0086@\u00a2\u0006\u0002\u0010.J\u0016\u0010/\u001a\u00020\n2\u0006\u0010\u0011\u001a\u00020\u0012H\u0086@\u00a2\u0006\u0002\u0010\u0013J\u0016\u00100\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0086@\u00a2\u0006\u0002\u0010\u0013J&\u00101\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\n2\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\rH\u0086@\u00a2\u0006\u0002\u0010\u001aR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u00062"}, d2 = {"Lcom/sacco/savings/repository/SaccoRepository;", "", "userDao", "Lcom/sacco/savings/data/dao/UserDao;", "accountDao", "Lcom/sacco/savings/data/dao/AccountDao;", "transactionDao", "Lcom/sacco/savings/data/dao/TransactionDao;", "(Lcom/sacco/savings/data/dao/UserDao;Lcom/sacco/savings/data/dao/AccountDao;Lcom/sacco/savings/data/dao/TransactionDao;)V", "createAccount", "", "userId", "accountType", "", "(JLjava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteUser", "", "user", "Lcom/sacco/savings/data/model/User;", "(Lcom/sacco/savings/data/model/User;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deposit", "", "accountId", "amount", "", "description", "(JDLjava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "generateAccountNumber", "generateReferenceNumber", "getAccountById", "Lkotlinx/coroutines/flow/Flow;", "Lcom/sacco/savings/data/model/Account;", "getAccountsByUserId", "", "getAllUsers", "getRecentTransactions", "Lcom/sacco/savings/data/model/Transaction;", "limit", "", "getTransactionsByAccountId", "getUserByEmail", "email", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getUserById", "login", "password", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "registerUser", "updateUser", "withdraw", "app_debug"})
public final class SaccoRepository {
    @org.jetbrains.annotations.NotNull
    private final com.sacco.savings.data.dao.UserDao userDao = null;
    @org.jetbrains.annotations.NotNull
    private final com.sacco.savings.data.dao.AccountDao accountDao = null;
    @org.jetbrains.annotations.NotNull
    private final com.sacco.savings.data.dao.TransactionDao transactionDao = null;
    
    public SaccoRepository(@org.jetbrains.annotations.NotNull
    com.sacco.savings.data.dao.UserDao userDao, @org.jetbrains.annotations.NotNull
    com.sacco.savings.data.dao.AccountDao accountDao, @org.jetbrains.annotations.NotNull
    com.sacco.savings.data.dao.TransactionDao transactionDao) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object login(@org.jetbrains.annotations.NotNull
    java.lang.String email, @org.jetbrains.annotations.NotNull
    java.lang.String password, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.sacco.savings.data.model.User> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object registerUser(@org.jetbrains.annotations.NotNull
    com.sacco.savings.data.model.User user, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.Flow<com.sacco.savings.data.model.User> getUserById(long userId) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object getUserByEmail(@org.jetbrains.annotations.NotNull
    java.lang.String email, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.sacco.savings.data.model.User> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.sacco.savings.data.model.User>> getAllUsers() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object updateUser(@org.jetbrains.annotations.NotNull
    com.sacco.savings.data.model.User user, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object deleteUser(@org.jetbrains.annotations.NotNull
    com.sacco.savings.data.model.User user, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object createAccount(long userId, @org.jetbrains.annotations.NotNull
    java.lang.String accountType, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.sacco.savings.data.model.Account>> getAccountsByUserId(long userId) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.Flow<com.sacco.savings.data.model.Account> getAccountById(long accountId) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object deposit(long accountId, double amount, @org.jetbrains.annotations.NotNull
    java.lang.String description, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Boolean> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object withdraw(long accountId, double amount, @org.jetbrains.annotations.NotNull
    java.lang.String description, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Boolean> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.sacco.savings.data.model.Transaction>> getTransactionsByAccountId(long accountId) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.sacco.savings.data.model.Transaction>> getRecentTransactions(long accountId, int limit) {
        return null;
    }
    
    private final java.lang.String generateAccountNumber() {
        return null;
    }
    
    private final java.lang.String generateReferenceNumber() {
        return null;
    }
}