package com.daniel.commons


object DateConverter {
    fun formatDate(date: String): String? {
        return date.substring(0,10)
    }
}