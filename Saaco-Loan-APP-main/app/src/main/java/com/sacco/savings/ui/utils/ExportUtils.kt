package com.sacco.savings.ui.utils

import android.content.Context
import android.net.Uri
import androidx.core.content.FileProvider
import com.itextpdf.kernel.colors.ColorConstants
import com.itextpdf.kernel.pdf.PdfDocument
import com.itextpdf.kernel.pdf.PdfWriter
import com.itextpdf.layout.Document
import com.itextpdf.layout.element.Cell
import com.itextpdf.layout.element.Paragraph
import com.itextpdf.layout.element.Table
import com.itextpdf.layout.properties.TextAlignment
import com.itextpdf.layout.properties.UnitValue
import com.sacco.savings.data.model.Transaction
import java.io.File
import java.io.FileWriter
import java.text.SimpleDateFormat
import java.util.*

object ExportUtils {
    fun generatePDF(context: Context, transactions: List<Transaction>, accountNumber: String?): Uri? {
        return try {
            val fileName = "transactions_${System.currentTimeMillis()}.pdf"
            val file = File(context.getExternalFilesDir(null), fileName)
            
            val writer = PdfWriter(file)
            val pdfDocument = PdfDocument(writer)
            val document = Document(pdfDocument)
            
            // Title
            document.add(
                Paragraph("BF SACCO SAVINGS APP - Transaction History")
                    .setFontSize(18f)
                    .setBold()
                    .setTextAlignment(TextAlignment.CENTER)
                    .setMarginBottom(10f)
            )
            
            if (accountNumber != null) {
                document.add(
                    Paragraph("Account: $accountNumber")
                        .setFontSize(12f)
                        .setMarginBottom(10f)
                )
            }
            
            document.add(
                Paragraph("Generated on: ${SimpleDateFormat("dd MMM yyyy, HH:mm", Locale.getDefault()).format(Date())}")
                    .setFontSize(10f)
                    .setMarginBottom(20f)
            )
            
            // Table
            val table = Table(UnitValue.createPercentArray(floatArrayOf(2f, 2f, 2f, 2f, 2f)))
            table.setWidth(UnitValue.createPercentValue(100f))
            
            // Header
            table.addHeaderCell(Cell().add(Paragraph("Date").setBold()))
            table.addHeaderCell(Cell().add(Paragraph("Type").setBold()))
            table.addHeaderCell(Cell().add(Paragraph("Description").setBold()))
            table.addHeaderCell(Cell().add(Paragraph("Reference").setBold()))
            table.addHeaderCell(Cell().add(Paragraph("Amount").setBold()))
            
            // Data rows
            transactions.forEach { transaction ->
                val dateFormat = SimpleDateFormat("dd MMM yyyy\nHH:mm", Locale.getDefault())
                val dateStr = dateFormat.format(Date(transaction.transactionDate))
                
                table.addCell(Cell().add(Paragraph(dateStr).setFontSize(8f)))
                table.addCell(Cell().add(Paragraph(transaction.transactionType).setFontSize(8f)))
                table.addCell(Cell().add(Paragraph(transaction.description).setFontSize(8f)))
                table.addCell(Cell().add(Paragraph(transaction.referenceNumber).setFontSize(8f)))
                
                val amountCell = Cell().add(
                    Paragraph(formatCurrency(transaction.amount))
                        .setFontSize(8f)
                )
                if (transaction.transactionType == "DEPOSIT") {
                    amountCell.setBackgroundColor(ColorConstants.GREEN)
                } else {
                    amountCell.setBackgroundColor(ColorConstants.RED)
                }
                table.addCell(amountCell)
            }
            
            // Summary
            val totalDeposits = transactions.filter { it.transactionType == "DEPOSIT" }.sumOf { it.amount }
            val totalWithdrawals = transactions.filter { it.transactionType == "WITHDRAWAL" }.sumOf { it.amount }
            val balance = totalDeposits - totalWithdrawals
            
            document.add(table)
            document.add(Paragraph(" ").setMarginTop(20f))
            document.add(
                Paragraph("Summary")
                    .setBold()
                    .setFontSize(14f)
                    .setMarginBottom(10f)
            )
            document.add(Paragraph("Total Deposits: ${formatCurrency(totalDeposits)}"))
            document.add(Paragraph("Total Withdrawals: ${formatCurrency(totalWithdrawals)}"))
            document.add(
                Paragraph("Balance: ${formatCurrency(balance)}")
                    .setBold()
            )
            
            document.close()
            
            FileProvider.getUriForFile(
                context,
                "${context.packageName}.fileprovider",
                file
            )
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
    
    fun generateCSV(context: Context, transactions: List<Transaction>, accountNumber: String?): Uri? {
        return try {
            val fileName = "transactions_${System.currentTimeMillis()}.csv"
            val file = File(context.getExternalFilesDir(null), fileName)
            
            FileWriter(file).use { writer ->
                // Header
                writer.append("BF SACCO SAVINGS APP - Transaction History\n")
                if (accountNumber != null) {
                    writer.append("Account: $accountNumber\n")
                }
                writer.append("Generated on: ${SimpleDateFormat("dd MMM yyyy, HH:mm", Locale.getDefault()).format(Date())}\n\n")
                
                // CSV Header
                writer.append("Date,Type,Description,Reference,Amount\n")
                
                // Data rows
                transactions.forEach { transaction ->
                    val dateFormat = SimpleDateFormat("dd MMM yyyy HH:mm", Locale.getDefault())
                    val dateStr = dateFormat.format(Date(transaction.transactionDate))
                    
                    writer.append("$dateStr,")
                    writer.append("${transaction.transactionType},")
                    writer.append("\"${transaction.description}\",")
                    writer.append("${transaction.referenceNumber},")
                    writer.append("${formatCurrency(transaction.amount)}\n")
                }
                
                // Summary
                val totalDeposits = transactions.filter { it.transactionType == "DEPOSIT" }.sumOf { it.amount }
                val totalWithdrawals = transactions.filter { it.transactionType == "WITHDRAWAL" }.sumOf { it.amount }
                val balance = totalDeposits - totalWithdrawals
                
                writer.append("\n")
                writer.append("Summary\n")
                writer.append("Total Deposits,${formatCurrency(totalDeposits)}\n")
                writer.append("Total Withdrawals,${formatCurrency(totalWithdrawals)}\n")
                writer.append("Balance,${formatCurrency(balance)}\n")
            }
            
            FileProvider.getUriForFile(
                context,
                "${context.packageName}.fileprovider",
                file
            )
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}

