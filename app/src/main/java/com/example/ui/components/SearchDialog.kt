package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.data.NewsRepository
import com.example.model.Article
import com.example.ui.theme.EditorialSansFontFamily
import com.example.ui.theme.HeadlineFontFamily
import com.example.ui.theme.NytBlack
import com.example.ui.theme.NytBorder
import com.example.ui.theme.NytDarkGray
import com.example.ui.theme.NytLightGray
import com.example.ui.theme.NytRed

@Composable
fun SearchDialog(
    initialQuery: String = "",
    onArticleSelected: (Article) -> Unit,
    onDismiss: () -> Unit
) {
    var query by remember { mutableStateOf(initialQuery) }
    val searchResults = remember(query) {
        NewsRepository.searchArticles(query)
    }

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Card(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .testTag("search_dialog"),
            shape = RoundedCornerShape(8.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                // Header with Search input
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    OutlinedTextField(
                        value = query,
                        onValueChange = { query = it },
                        placeholder = { Text("Search The New York Times...", fontSize = 13.sp, color = NytLightGray) },
                        leadingIcon = {
                            Icon(imageVector = Icons.Default.Search, contentDescription = "Search", tint = NytBlack)
                        },
                        trailingIcon = {
                            if (query.isNotEmpty()) {
                                IconButton(onClick = { query = "" }) {
                                    Icon(imageVector = Icons.Default.Clear, contentDescription = "Clear", tint = NytLightGray)
                                }
                            }
                        },
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = NytBlack,
                            unfocusedBorderColor = NytBorder
                        ),
                        singleLine = true,
                        modifier = Modifier
                            .weight(1f)
                            .testTag("search_input_field")
                    )

                    IconButton(onClick = onDismiss, modifier = Modifier.size(36.dp)) {
                        Icon(imageVector = Icons.Default.Close, contentDescription = "Close", tint = NytBlack)
                    }
                }

                HorizontalDivider(color = NytBorder, thickness = 1.dp, modifier = Modifier.padding(vertical = 12.dp))

                // Results count or quick tags
                if (query.isBlank()) {
                    Text(
                        text = "POPULAR SEARCHES",
                        fontFamily = EditorialSansFontFamily,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        color = NytLightGray,
                        letterSpacing = 0.8.sp,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        listOf("Trump", "Ukraine", "Polls", "AI", "Wirecutter").forEach { suggestion ->
                            Box(
                                modifier = Modifier
                                    .background(Color(0xFFF3F4F6), RoundedCornerShape(14.dp))
                                    .clickable { query = suggestion }
                                    .padding(horizontal = 12.dp, vertical = 6.dp)
                            ) {
                                Text(suggestion, fontSize = 11.sp, fontFamily = EditorialSansFontFamily, color = NytBlack)
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                } else {
                    Text(
                        text = "${searchResults.size} results for \"$query\"",
                        fontFamily = EditorialSansFontFamily,
                        fontSize = 11.sp,
                        color = NytLightGray,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                }

                // Results list
                LazyColumn(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(searchResults) { article ->
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    onArticleSelected(article)
                                    onDismiss()
                                }
                                .padding(vertical = 8.dp)
                        ) {
                            if (article.kicker.isNotBlank()) {
                                Text(
                                    text = article.kicker,
                                    fontFamily = EditorialSansFontFamily,
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = NytRed,
                                    letterSpacing = 0.5.sp
                                )
                            }
                            Text(
                                text = article.title,
                                fontFamily = HeadlineFontFamily,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                color = NytBlack,
                                lineHeight = 20.sp
                            )
                            Text(
                                text = article.excerpt,
                                fontFamily = HeadlineFontFamily,
                                fontSize = 12.sp,
                                color = NytDarkGray,
                                maxLines = 2,
                                lineHeight = 16.sp,
                                modifier = Modifier.padding(vertical = 2.dp)
                            )
                            Text(
                                text = "${article.author} • ${article.readTime}",
                                fontFamily = EditorialSansFontFamily,
                                fontSize = 10.sp,
                                color = NytLightGray
                            )
                            HorizontalDivider(color = NytBorder, thickness = 0.5.dp, modifier = Modifier.padding(top = 8.dp))
                        }
                    }
                }
            }
        }
    }
}
