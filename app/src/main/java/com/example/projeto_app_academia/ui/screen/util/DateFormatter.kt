package com.example.projeto_app_academia.ui.screen.util

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun formatarDataCriacao(timestamp: Long): String {
    val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    val date = Date(timestamp)
    return dateFormat.format(date)
}