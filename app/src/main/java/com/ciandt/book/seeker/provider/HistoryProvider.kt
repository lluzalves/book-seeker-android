package com.ciandt.book.seeker.provider

import android.content.SearchRecentSuggestionsProvider

class HistorySuggestionProvider : SearchRecentSuggestionsProvider() {
    init {
        setupSuggestions(AUTHORITY, MODE)
    }

    companion object {
        const val AUTHORITY = "com.ciandt.book.seeker.HistorySuggestionProvider"
        const val MODE: Int = DATABASE_MODE_QUERIES
    }
}
