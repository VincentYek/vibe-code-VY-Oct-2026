package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.EditorialSansFontFamily
import com.example.ui.theme.MastheadFontFamily
import com.example.ui.theme.NytBlack
import com.example.ui.theme.NytBorder
import com.example.ui.theme.NytDarkGray
import com.example.ui.theme.NytLightGray

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun NytFooter(
    onSectionClick: (String) -> Unit,
    onSubscribeClick: () -> Unit,
    onLoginClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val textColor = MaterialTheme.colorScheme.onBackground

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(horizontal = 16.dp, vertical = 20.dp)
    ) {
        // Thick top border
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(2.dp)
                .background(NytBlack)
        )

        Spacer(modifier = Modifier.height(14.dp))

        // Masthead logo and quick subscribe links
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "The New York Times",
                fontFamily = MastheadFontFamily,
                fontSize = 24.sp,
                color = NytBlack
            )

            Row(
                horizontalArrangement = Arrangement.spacedBy(6.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Subscribe",
                    fontFamily = EditorialSansFontFamily,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = NytBlack,
                    modifier = Modifier.clickable { onSubscribeClick() }
                )
                Text(text = "|", fontSize = 10.sp, color = NytLightGray)
                Text(
                    text = "Home Delivery",
                    fontFamily = EditorialSansFontFamily,
                    fontSize = 11.sp,
                    color = NytLightGray,
                    modifier = Modifier.clickable { onSubscribeClick() }
                )
            }
        }

        HorizontalDivider(color = NytBorder, thickness = 1.dp, modifier = Modifier.padding(vertical = 14.dp))

        // Categories Grid: 2 columns on mobile
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // News
            FooterDirectoryColumn(
                title = "NEWS",
                links = listOf("Home Page", "U.S.", "World", "Politics", "Business"),
                onSectionClick = onSectionClick,
                modifier = Modifier.weight(1f)
            )

            // Opinion
            FooterDirectoryColumn(
                title = "OPINION",
                links = listOf("Today's Opinion", "Columnists", "Editorials", "Guest Essays"),
                onSectionClick = onSectionClick,
                modifier = Modifier.weight(1f)
            )

            // Arts
            FooterDirectoryColumn(
                title = "ARTS",
                links = listOf("Books", "Movies", "Music", "Television"),
                onSectionClick = onSectionClick,
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(modifier = Modifier.height(14.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Living
            FooterDirectoryColumn(
                title = "LIVING",
                links = listOf("Style", "Food", "Travel", "Real Estate"),
                onSectionClick = onSectionClick,
                modifier = Modifier.weight(1f)
            )

            // More
            FooterDirectoryColumn(
                title = "MORE",
                links = listOf("Cooking", "Wirecutter", "The Athletic", "Games"),
                onSectionClick = onSectionClick,
                modifier = Modifier.weight(1f)
            )

            // Account
            FooterDirectoryColumn(
                title = "ACCOUNT",
                links = listOf("Manage Account", "Gift Subs", "Mobile Apps"),
                onSectionClick = { onLoginClick() },
                modifier = Modifier.weight(1f)
            )
        }

        HorizontalDivider(color = NytBorder, thickness = 1.dp, modifier = Modifier.padding(vertical = 16.dp))

        // Copyright & Legal Links
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "© 2026 The New York Times Company",
                fontFamily = EditorialSansFontFamily,
                fontSize = 10.sp,
                color = NytLightGray,
                modifier = Modifier.padding(bottom = 6.dp)
            )

            FlowRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                listOf(
                    "NYTCo",
                    "Contact Us",
                    "Accessibility",
                    "Work with us",
                    "Advertise",
                    "Privacy Policy",
                    "Terms of Service"
                ).forEachIndexed { i, link ->
                    Text(
                        text = link,
                        fontFamily = EditorialSansFontFamily,
                        fontSize = 10.sp,
                        color = NytDarkGray,
                        modifier = Modifier.padding(horizontal = 4.dp)
                    )
                    if (i < 6) {
                        Text(text = "•", fontSize = 8.sp, color = NytLightGray)
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
private fun FooterDirectoryColumn(
    title: String,
    links: List<String>,
    onSectionClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(horizontal = 4.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(
            text = title,
            fontFamily = EditorialSansFontFamily,
            fontSize = 10.sp,
            fontWeight = FontWeight.Bold,
            color = NytBlack,
            letterSpacing = 0.6.sp,
            modifier = Modifier.padding(bottom = 4.dp)
        )
        links.forEach { link ->
            Text(
                text = link,
                fontFamily = EditorialSansFontFamily,
                fontSize = 11.sp,
                color = NytDarkGray,
                modifier = Modifier
                    .clickable { onSectionClick(link) }
                    .padding(vertical = 2.dp)
            )
        }
    }
}
