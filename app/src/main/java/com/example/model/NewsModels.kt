package com.example.model

import androidx.compose.ui.graphics.Color

data class Article(
    val id: String,
    val kicker: String = "",
    val kickerColor: Long = 0xFFB82218,
    val title: String,
    val excerpt: String,
    val fullBody: List<String> = emptyList(),
    val author: String,
    val authorRole: String = "",
    val readTime: String,
    val date: String = "Wednesday, October 14, 2026",
    val localImageRes: Int? = null,
    val imageUrl: String? = null,
    val imageCaption: String = "",
    val imageCredit: String = "",
    val section: String = "U.S.",
    val isLead: Boolean = false,
    val relatedArticles: List<RelatedArticle> = emptyList(),
    val comments: List<ArticleComment> = emptyList()
)

data class RelatedArticle(
    val title: String,
    val tag: String? = null,
    val isAnalysis: Boolean = false
)

data class ArticleComment(
    val id: String,
    val userName: String,
    val userLocation: String,
    val timeAgo: String,
    val content: String,
    val upvotes: Int = 0,
    val isRecommended: Boolean = false
)

data class PollData(
    val stateName: String,
    val demPct: Float,
    val gopPct: Float,
    val undecPct: Float,
    val marginText: String
)

enum class NytSection(val displayName: String) {
    FRONT_PAGE("Front Page"),
    US("U.S."),
    WORLD("World"),
    BUSINESS("Business"),
    ARTS("Arts"),
    LIFESTYLE("Lifestyle"),
    OPINION("Opinion"),
    VIDEO("Video"),
    SHOWS("Shows"),
    GAMES("Games"),
    COOKING("Cooking"),
    WIRECUTTER("Wirecutter"),
    THE_ATHLETIC("The Athletic")
}

enum class NytEdition(val displayName: String) {
    US("U.S."),
    INTERNATIONAL("International"),
    CANADA("Canada"),
    ESPANOL("Español"),
    CHINESE("中文")
}
