package com.example.ui.components

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.R
import com.example.model.Article
import com.example.model.PollData
import com.example.ui.theme.DemBlue
import com.example.ui.theme.EditorialSansFontFamily
import com.example.ui.theme.GopRed
import com.example.ui.theme.HeadlineFontFamily
import com.example.ui.theme.NytBlack
import com.example.ui.theme.NytBorder
import com.example.ui.theme.NytCardBg
import com.example.ui.theme.NytDarkGray
import com.example.ui.theme.NytLightGray
import com.example.ui.theme.NytRed
import com.example.ui.theme.UndecidedYellow
import com.example.ui.theme.WirecutterOrange

@Composable
fun LeadStoryArticle(
    article: Article,
    onArticleClick: (Article) -> Unit,
    modifier: Modifier = Modifier
) {
    val textColor = MaterialTheme.colorScheme.onBackground

    Column(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onArticleClick(article) }
            .testTag("lead_story_card")
    ) {
        // Headline
        Text(
            text = article.title,
            fontFamily = HeadlineFontFamily,
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            lineHeight = 32.sp,
            color = textColor,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        // Subdeck / Excerpt
        Text(
            text = article.excerpt,
            fontFamily = HeadlineFontFamily,
            fontSize = 15.sp,
            lineHeight = 20.sp,
            color = NytDarkGray,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        // Byline & Read time
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(6.dp),
            modifier = Modifier.padding(bottom = 10.dp)
        ) {
            Text(
                text = article.author,
                fontFamily = EditorialSansFontFamily,
                fontSize = 11.sp,
                fontWeight = FontWeight.SemiBold,
                color = textColor
            )
            Text(text = "•", fontSize = 11.sp, color = NytLightGray)
            Text(
                text = article.readTime,
                fontFamily = EditorialSansFontFamily,
                fontSize = 11.sp,
                color = NytLightGray
            )
        }

        // Photo with caption & credit
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1.5f)
                .background(Color(0xFFEEEEEE))
                .clip(RoundedCornerShape(1.dp))
        ) {
            if (article.localImageRes != null) {
                Image(
                    painter = painterResource(id = article.localImageRes),
                    contentDescription = article.imageCaption,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.matchParentSize()
                )
            } else if (!article.imageUrl.isNullOrBlank()) {
                AsyncImage(
                    model = article.imageUrl,
                    contentDescription = article.imageCaption,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.matchParentSize()
                )
            }
        }

        // Caption & Photo Credit
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp, bottom = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top
        ) {
            Text(
                text = article.imageCaption,
                fontFamily = EditorialSansFontFamily,
                fontSize = 10.sp,
                color = NytLightGray,
                lineHeight = 13.sp,
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = article.imageCredit,
                fontFamily = EditorialSansFontFamily,
                fontSize = 9.sp,
                color = Color(0xFF999999)
            )
        }

        // Related Bullets
        if (article.relatedArticles.isNotEmpty()) {
            HorizontalDivider(color = NytBorder, thickness = 1.dp)
            Column(
                modifier = Modifier.padding(vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                article.relatedArticles.forEach { related ->
                    Row(
                        verticalAlignment = Alignment.Top,
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        if (related.isAnalysis) {
                            Text(
                                text = "ANALYSIS",
                                fontFamily = EditorialSansFontFamily,
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                color = NytRed,
                                letterSpacing = 0.5.sp
                            )
                        } else {
                            Text(text = "•", color = NytLightGray, fontSize = 12.sp)
                        }
                        Text(
                            text = related.title,
                            fontFamily = HeadlineFontFamily,
                            fontSize = 13.sp,
                            fontWeight = if (related.isAnalysis) FontWeight.SemiBold else FontWeight.Normal,
                            lineHeight = 17.sp,
                            color = textColor
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun SecondaryArticleCard(
    article: Article,
    onArticleClick: (Article) -> Unit,
    modifier: Modifier = Modifier
) {
    val textColor = MaterialTheme.colorScheme.onBackground

    Column(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onArticleClick(article) }
            .testTag("article_${article.id}")
            .padding(vertical = 8.dp)
    ) {
        if (article.kicker.isNotBlank()) {
            Text(
                text = article.kicker,
                fontFamily = EditorialSansFontFamily,
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
                color = NytRed,
                letterSpacing = 0.6.sp,
                modifier = Modifier.padding(bottom = 3.dp)
            )
        }

        Text(
            text = article.title,
            fontFamily = HeadlineFontFamily,
            fontSize = 19.sp,
            fontWeight = FontWeight.Bold,
            lineHeight = 23.sp,
            color = textColor,
            modifier = Modifier.padding(bottom = 4.dp)
        )

        Text(
            text = article.excerpt,
            fontFamily = HeadlineFontFamily,
            fontSize = 13.sp,
            lineHeight = 17.sp,
            color = NytDarkGray,
            maxLines = 3,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(bottom = 4.dp)
        )

        Text(
            text = article.readTime,
            fontFamily = EditorialSansFontFamily,
            fontSize = 10.sp,
            color = NytLightGray
        )
    }
}

@Composable
fun UkraineWarPackage(
    article: Article,
    onArticleClick: (Article) -> Unit,
    modifier: Modifier = Modifier
) {
    val textColor = MaterialTheme.colorScheme.onBackground
    val infiniteTransition = rememberInfiniteTransition(label = "pulse")
    val alpha by infiniteTransition.animateFloat(
        initialValue = 0.4f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(800),
            repeatMode = RepeatMode.Reverse
        ),
        label = "alpha"
    )

    Column(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onArticleClick(article) }
            .testTag("ukraine_war_card")
            .padding(vertical = 6.dp)
    ) {
        // Red Pulsing Live Dot + Kicker
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(6.dp),
            modifier = Modifier.padding(bottom = 4.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(8.dp)
                    .clip(CircleShape)
                    .background(NytRed.copy(alpha = alpha))
            )
            Text(
                text = "WAR IN UKRAINE",
                fontFamily = EditorialSansFontFamily,
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
                color = NytRed,
                letterSpacing = 0.6.sp
            )
        }

        Text(
            text = article.title,
            fontFamily = HeadlineFontFamily,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            lineHeight = 22.sp,
            color = textColor,
            modifier = Modifier.padding(bottom = 4.dp)
        )

        Text(
            text = article.excerpt,
            fontFamily = HeadlineFontFamily,
            fontSize = 13.sp,
            lineHeight = 17.sp,
            color = NytDarkGray,
            modifier = Modifier.padding(bottom = 6.dp)
        )

        // Sublinks
        if (article.relatedArticles.isNotEmpty()) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(width = 0.5.dp, color = NytBorder)
                    .padding(8.dp),
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                article.relatedArticles.forEach { sublink ->
                    Text(
                        text = "• ${sublink.title}",
                        fontFamily = HeadlineFontFamily,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium,
                        lineHeight = 16.sp,
                        color = textColor
                    )
                }
            }
        }
    }
}

@Composable
fun PollingTrackerWidget(
    pollData: PollData,
    onNextStateClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(NytCardBg, RoundedCornerShape(2.dp))
            .border(1.dp, NytBorder, RoundedCornerShape(2.dp))
            .clickable { onNextStateClick() }
            .padding(10.dp)
            .testTag("polling_widget")
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = pollData.stateName.uppercase(),
                fontFamily = EditorialSansFontFamily,
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
                color = NytDarkGray,
                letterSpacing = 0.5.sp,
                modifier = Modifier.weight(1f)
            )
            Text(
                text = pollData.marginText,
                fontFamily = EditorialSansFontFamily,
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                color = DemBlue
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Three-segment bar
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(8.dp)
                .clip(RoundedCornerShape(4.dp))
                .background(Color(0xFFE5E7EB))
        ) {
            Box(
                modifier = Modifier
                    .weight(pollData.demPct)
                    .height(8.dp)
                    .background(DemBlue)
            )
            Box(
                modifier = Modifier
                    .weight(pollData.undecPct)
                    .height(8.dp)
                    .background(UndecidedYellow)
            )
            Box(
                modifier = Modifier
                    .weight(pollData.gopPct)
                    .height(8.dp)
                    .background(GopRed)
            )
        }

        Spacer(modifier = Modifier.height(6.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Democrat ${pollData.demPct}%",
                fontFamily = EditorialSansFontFamily,
                fontSize = 9.sp,
                color = NytLightGray
            )
            Text(
                text = "Undecided ${pollData.undecPct}%",
                fontFamily = EditorialSansFontFamily,
                fontSize = 9.sp,
                color = NytLightGray
            )
            Text(
                text = "Republican ${pollData.gopPct}%",
                fontFamily = EditorialSansFontFamily,
                fontSize = 9.sp,
                color = NytLightGray
            )
        }

        Text(
            text = "Tap to view other battleground states ▶",
            fontFamily = EditorialSansFontFamily,
            fontSize = 9.sp,
            color = Color(0xFF6B7280),
            modifier = Modifier.padding(top = 4.dp)
        )
    }
}

@Composable
fun OpinionColumnistRow(
    article: Article,
    onArticleClick: (Article) -> Unit,
    showDivider: Boolean = true,
    modifier: Modifier = Modifier
) {
    val textColor = MaterialTheme.colorScheme.onBackground

    Column(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onArticleClick(article) }
            .testTag("opinion_${article.id}")
            .padding(vertical = 4.dp)
    ) {
        if (showDivider) {
            HorizontalDivider(color = NytBorder, thickness = 0.5.dp, modifier = Modifier.padding(bottom = 6.dp))
        }

        Text(
            text = article.author,
            fontFamily = EditorialSansFontFamily,
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            color = textColor
        )

        Text(
            text = article.title,
            fontFamily = HeadlineFontFamily,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            lineHeight = 18.sp,
            color = textColor,
            modifier = Modifier.padding(vertical = 2.dp)
        )

        Text(
            text = article.authorRole.ifBlank { "OPINION COLUMNIST" },
            fontFamily = EditorialSansFontFamily,
            fontSize = 9.sp,
            fontWeight = FontWeight.Medium,
            color = NytLightGray,
            letterSpacing = 0.5.sp
        )
    }
}

@Composable
fun WirecutterCard(
    article: Article,
    onArticleClick: (Article) -> Unit,
    modifier: Modifier = Modifier
) {
    val textColor = MaterialTheme.colorScheme.onBackground

    Column(
        modifier = modifier
            .fillMaxWidth()
            .border(1.dp, NytBorder, RoundedCornerShape(2.dp))
            .clickable { onArticleClick(article) }
            .testTag("wirecutter_card")
            .padding(12.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(6.dp),
            modifier = Modifier.padding(bottom = 6.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(8.dp)
                    .background(WirecutterOrange, RoundedCornerShape(1.dp))
            )
            Text(
                text = "WIRECUTTER",
                fontFamily = EditorialSansFontFamily,
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
                color = textColor,
                letterSpacing = 0.8.sp
            )
        }

        Text(
            text = article.title,
            fontFamily = HeadlineFontFamily,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            lineHeight = 18.sp,
            color = textColor,
            modifier = Modifier.padding(bottom = 4.dp)
        )

        Text(
            text = "Independent expert reviews",
            fontFamily = EditorialSansFontFamily,
            fontSize = 10.sp,
            color = NytLightGray
        )
    }
}
