package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.NytEdition
import com.example.model.NytSection
import com.example.ui.theme.EditorialSansFontFamily
import com.example.ui.theme.MastheadFontFamily
import com.example.ui.theme.NytBlack
import com.example.ui.theme.NytBorder
import com.example.ui.theme.NytDarkGray
import com.example.ui.theme.NytLightGray
import com.example.ui.theme.NytSubscribeBlue

@Composable
fun NytDoubleRuleDivider(
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.onBackground
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 2.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(color)
        )
        Spacer(modifier = Modifier.height(2.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(color)
        )
    }
}

@Composable
fun NytHeader(
    currentEdition: NytEdition,
    selectedSection: String,
    onEditionClick: () -> Unit,
    onSearchClick: () -> Unit,
    onSubscribeClick: () -> Unit,
    onLoginClick: () -> Unit,
    onSectionSelected: (String) -> Unit,
    onTrendingSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val textColor = MaterialTheme.colorScheme.onBackground

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
    ) {
        // TOP UTILITY BAR
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 6.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Left: Search & Edition
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Row(
                    modifier = Modifier
                        .clickable { onSearchClick() }
                        .padding(vertical = 4.dp, horizontal = 4.dp)
                        .testTag("header_search_btn"),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search",
                        modifier = Modifier.size(14.dp),
                        tint = textColor
                    )
                    Text(
                        text = "SEARCH",
                        fontFamily = EditorialSansFontFamily,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        color = textColor,
                        letterSpacing = 0.8.sp
                    )
                }

                Box(
                    modifier = Modifier
                        .height(12.dp)
                        .width(1.dp)
                        .background(NytBorder)
                )

                // Current Edition trigger
                Row(
                    modifier = Modifier
                        .clickable { onEditionClick() }
                        .padding(horizontal = 4.dp, vertical = 4.dp)
                        .testTag("header_edition_btn"),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text(
                        text = currentEdition.displayName,
                        fontFamily = EditorialSansFontFamily,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        color = textColor,
                        letterSpacing = 0.5.sp
                    )
                    Text(
                        text = "▼",
                        fontSize = 7.sp,
                        color = NytLightGray
                    )
                }
            }

            // Right: Subscribe & Log In buttons
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Box(
                    modifier = Modifier
                        .background(NytSubscribeBlue, RoundedCornerShape(2.dp))
                        .clickable { onSubscribeClick() }
                        .padding(horizontal = 10.dp, vertical = 6.dp)
                        .testTag("subscribe_button")
                ) {
                    Text(
                        text = "SUBSCRIBE FOR $1/WK",
                        fontFamily = EditorialSansFontFamily,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        letterSpacing = 0.5.sp
                    )
                }

                Box(
                    modifier = Modifier
                        .border(1.dp, textColor, RoundedCornerShape(2.dp))
                        .clickable { onLoginClick() }
                        .padding(horizontal = 10.dp, vertical = 5.dp)
                        .testTag("login_button")
                ) {
                    Text(
                        text = "LOG IN",
                        fontFamily = EditorialSansFontFamily,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = textColor
                    )
                }
            }
        }

        HorizontalDivider(color = NytBorder, thickness = 1.dp)

        // DATE & MARKET INDICES ROW
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 4.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = "Wednesday, October 14, 2026",
                    fontFamily = EditorialSansFontFamily,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = textColor
                )
                Text(
                    text = "• Today's Paper",
                    fontFamily = EditorialSansFontFamily,
                    fontSize = 11.sp,
                    color = NytLightGray,
                    modifier = Modifier.clickable { onSectionSelected("Front Page") }
                )
            }

            // Markets snippet
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = "S&P 500 +0.42%",
                    fontFamily = EditorialSansFontFamily,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Medium,
                    color = textColor
                )
                Text(
                    text = "Nasdaq +0.68%",
                    fontFamily = EditorialSansFontFamily,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Medium,
                    color = textColor
                )
            }
        }

        // MASTHEAD TITLE
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "The New York Times",
                fontFamily = MastheadFontFamily,
                fontSize = 42.sp,
                color = textColor,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .clickable { onSectionSelected("Front Page") }
                    .testTag("masthead_logo")
            )
        }

        // DOUBLE RULE SEPARATOR
        NytDoubleRuleDivider(modifier = Modifier.padding(horizontal = 12.dp))

        // HORIZONTAL SECTION NAV
        val sectionScrollState = rememberScrollState()
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(sectionScrollState)
                .padding(horizontal = 8.dp, vertical = 4.dp),
            horizontalArrangement = Arrangement.spacedBy(14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            NytSection.values().forEach { section ->
                val isSelected = selectedSection.equals(section.displayName, ignoreCase = true)
                Column(
                    modifier = Modifier
                        .clickable { onSectionSelected(section.displayName) }
                        .padding(horizontal = 4.dp, vertical = 2.dp)
                        .testTag("nav_section_${section.name.lowercase()}"),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = section.displayName,
                        fontFamily = EditorialSansFontFamily,
                        fontSize = 11.sp,
                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                        color = if (isSelected) textColor else NytDarkGray
                    )
                    if (isSelected) {
                        Box(
                            modifier = Modifier
                                .padding(top = 2.dp)
                                .height(2.dp)
                                .width(20.dp)
                                .background(textColor)
                        )
                    }
                }
            }
        }

        HorizontalDivider(color = NytBorder, thickness = 1.dp)

        // TRENDING BAR
        val trendingScrollState = rememberScrollState()
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(trendingScrollState)
                .padding(horizontal = 12.dp, vertical = 5.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = "TRENDING",
                fontFamily = EditorialSansFontFamily,
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
                color = textColor,
                letterSpacing = 0.6.sp
            )
            listOf(
                "Administration Exits",
                "Midterm Polling Tracker",
                "Ukraine Frontlines",
                "Wordle Hints",
                "Silicon Valley AI Lab"
            ).forEach { item ->
                Text(text = "•", color = NytLightGray, fontSize = 10.sp)
                Text(
                    text = item,
                    fontFamily = EditorialSansFontFamily,
                    fontSize = 11.sp,
                    color = textColor,
                    modifier = Modifier
                        .clickable { onTrendingSelected(item) }
                        .testTag("trending_item_$item")
                )
            }
        }

        HorizontalDivider(color = NytBorder, thickness = 1.dp)
    }
}
