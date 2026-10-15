package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.ConnectionsAmber
import com.example.ui.theme.EditorialSansFontFamily
import com.example.ui.theme.MiniBlue
import com.example.ui.theme.NytBorder
import com.example.ui.theme.NytCardBg
import com.example.ui.theme.NytLightGray
import com.example.ui.theme.SpellingBeeYellow
import com.example.ui.theme.WordleGreen

enum class GameType {
    WORDLE,
    CONNECTIONS,
    SPELLING_BEE,
    THE_MINI
}

@Composable
fun NytGamesShowcaseCard(
    onGameClick: (GameType) -> Unit,
    modifier: Modifier = Modifier
) {
    val textColor = MaterialTheme.colorScheme.onBackground

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(NytCardBg, RoundedCornerShape(2.dp))
            .border(1.dp, NytBorder, RoundedCornerShape(2.dp))
            .padding(12.dp)
            .testTag("nyt_games_showcase")
    ) {
        // Header
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "PLAY NYT GAMES",
                fontFamily = EditorialSansFontFamily,
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                color = textColor,
                letterSpacing = 0.8.sp
            )
            Text(
                text = "Daily Puzzles",
                fontFamily = EditorialSansFontFamily,
                fontSize = 10.sp,
                color = NytLightGray
            )
        }

        HorizontalDivider(color = NytBorder, thickness = 1.dp, modifier = Modifier.padding(bottom = 8.dp))

        // 2x2 Grid of Games
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // Wordle
            GameItemCard(
                name = "Wordle",
                subtitle = "No. 1,211",
                nameColor = WordleGreen,
                onClick = { onGameClick(GameType.WORDLE) },
                modifier = Modifier
                    .weight(1f)
                    .testTag("game_item_wordle")
            )
            // Connections
            GameItemCard(
                name = "Connections",
                subtitle = "Group by topic",
                nameColor = ConnectionsAmber,
                onClick = { onGameClick(GameType.CONNECTIONS) },
                modifier = Modifier
                    .weight(1f)
                    .testTag("game_item_connections")
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // Spelling Bee
            GameItemCard(
                name = "Spelling Bee",
                subtitle = "Genius ranking",
                nameColor = SpellingBeeYellow,
                onClick = { onGameClick(GameType.SPELLING_BEE) },
                modifier = Modifier
                    .weight(1f)
                    .testTag("game_item_spelling_bee")
            )
            // The Mini
            GameItemCard(
                name = "The Mini",
                subtitle = "Quick solve",
                nameColor = MiniBlue,
                onClick = { onGameClick(GameType.THE_MINI) },
                modifier = Modifier
                    .weight(1f)
                    .testTag("game_item_the_mini")
            )
        }
    }
}

@Composable
private fun GameItemCard(
    name: String,
    subtitle: String,
    nameColor: Color,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .background(Color.White, RoundedCornerShape(4.dp))
            .border(1.dp, Color(0xFFE5E7EB), RoundedCornerShape(4.dp))
            .clickable { onClick() }
            .padding(vertical = 10.dp, horizontal = 6.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = name,
                fontFamily = EditorialSansFontFamily,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                color = nameColor
            )
            Text(
                text = subtitle,
                fontFamily = EditorialSansFontFamily,
                fontSize = 9.sp,
                color = NytLightGray
            )
        }
    }
}
