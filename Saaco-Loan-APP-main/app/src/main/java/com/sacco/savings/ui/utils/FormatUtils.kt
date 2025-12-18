package com.sacco.savings.ui.utils

import java.text.NumberFormat
import java.util.Locale

fun formatCurrency(amount: Double): String {
    val formatter = NumberFormat.getNumberInstance(Locale.getDefault())
    return "UGX ${formatter.format(amount)}"
}

