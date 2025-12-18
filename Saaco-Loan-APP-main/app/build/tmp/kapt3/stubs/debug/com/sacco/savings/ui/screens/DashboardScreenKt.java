package com.sacco.savings.ui.screens;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u00008\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0010\u0006\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001af\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00010\t2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00010\t2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00010\t2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00010\t2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00010\tH\u0007\u001a>\u0010\u000e\u001a\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u00072\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00010\t2\u001e\u0010\u0010\u001a\u001a\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u00010\u0011H\u0007\u001a8\u0010\u0014\u001a\u00020\u00012\u0006\u0010\u0015\u001a\u00020\u00132\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00010\t2\u0018\u0010\u0010\u001a\u0014\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u00010\u0016H\u0007\u00a8\u0006\u0017"}, d2 = {"DashboardScreen", "", "accountViewModel", "Lcom/sacco/savings/ui/viewmodel/AccountViewModel;", "userId", "", "repository", "Lcom/sacco/savings/repository/SaccoRepository;", "onLogout", "Lkotlin/Function0;", "onNavigateToTransactions", "onNavigateToMembers", "onNavigateToAccount", "onNavigateToSettings", "DepositBottomSheet", "onDismiss", "onConfirm", "Lkotlin/Function3;", "", "", "TransactionDialog", "title", "Lkotlin/Function2;", "app_debug"})
public final class DashboardScreenKt {
    
    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
    @androidx.compose.runtime.Composable
    public static final void DashboardScreen(@org.jetbrains.annotations.NotNull
    com.sacco.savings.ui.viewmodel.AccountViewModel accountViewModel, long userId, @org.jetbrains.annotations.NotNull
    com.sacco.savings.repository.SaccoRepository repository, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function0<kotlin.Unit> onLogout, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function0<kotlin.Unit> onNavigateToTransactions, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function0<kotlin.Unit> onNavigateToMembers, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function0<kotlin.Unit> onNavigateToAccount, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function0<kotlin.Unit> onNavigateToSettings) {
    }
    
    @androidx.compose.runtime.Composable
    public static final void TransactionDialog(@org.jetbrains.annotations.NotNull
    java.lang.String title, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function0<kotlin.Unit> onDismiss, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function2<? super java.lang.Double, ? super java.lang.String, kotlin.Unit> onConfirm) {
    }
    
    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
    @androidx.compose.runtime.Composable
    public static final void DepositBottomSheet(@org.jetbrains.annotations.NotNull
    com.sacco.savings.repository.SaccoRepository repository, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function0<kotlin.Unit> onDismiss, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function3<? super java.lang.Long, ? super java.lang.Double, ? super java.lang.String, kotlin.Unit> onConfirm) {
    }
}