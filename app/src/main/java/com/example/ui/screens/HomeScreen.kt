package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
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
import com.example.data.NewsRepository
import com.example.model.Article
import com.example.model.NytEdition
import com.example.ui.components.ArticleDetailView
import com.example.ui.components.GameType
import com.example.ui.components.LeadStoryArticle
import com.example.ui.components.LoginDialog
import com.example.ui.components.MiniCrosswordDialog
import com.example.ui.components.NytFooter
import com.example.ui.components.NytGamesShowcaseCard
import com.example.ui.components.NytHeader
import com.example.ui.components.OpinionColumnistRow
import com.example.ui.components.PollingTrackerWidget
import com.example.ui.components.SearchDialog
import com.example.ui.components.SecondaryArticleCard
import com.example.ui.components.SubscriptionDialog
import com.example.ui.components.UkraineWarPackage
import com.example.ui.components.WirecutterCard
import com.example.ui.components.WordleGameDialog
import com.example.ui.theme.EditorialSansFontFamily
import com.example.ui.theme.HeadlineFontFamily
import com.example.ui.theme.NytBlack
import com.example.ui.theme.NytBorder
import com.example.ui.theme.NytCardBg
import com.example.ui.theme.NytDarkGray
import com.example.ui.theme.NytLightGray
import com.example.ui.theme.NytRed

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier
) {
    var selectedArticle by remember { mutableStateOf<Article?>(null) }
    var selectedSection by remember { mutableStateOf("Front Page") }
    var currentEdition by remember { mutableStateOf(NytEdition.US) }
    var showEditionDialog by remember { mutableStateOf(false) }
    var showSearchDialog by remember { mutableStateOf(false) }
    var showSubscribeDialog by remember { mutableStateOf(false) }
    var showLoginDialog by remember { mutableStateOf(false) }
    var activeGameDialog by remember { mutableStateOf<GameType?>(null) }
    var activeTrendingQuery by remember { mutableStateOf<String?>(null) }
    var pollStateIndex by remember { mutableIntStateOf(0) }

    // If an article is open, display full reader view
    if (selectedArticle != null) {
        ArticleDetailView(
            article = selectedArticle!!,
            onBack = { selectedArticle = null }
        )
        return
    }

    val scrollState = rememberScrollState()
    val pollData = NewsRepository.pollDataList[pollStateIndex % NewsRepository.pollDataList.size]

    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = Color.White
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(scrollState)
        ) {
            // Masthead, utility bar, section bar, and trending strip
            NytHeader(
                currentEdition = currentEdition,
                selectedSection = selectedSection,
                onEditionClick = { showEditionDialog = true },
                onSearchClick = { showSearchDialog = true },
                onSubscribeClick = { showSubscribeDialog = true },
                onLoginClick = { showLoginDialog = true },
                onSectionSelected = { section ->
                    selectedSection = section
                },
                onTrendingSelected = { trendingTopic ->
                    activeTrendingQuery = trendingTopic
                    showSearchDialog = true
                }
            )

            // Main Editorial Content Container
            BoxWithConstraints(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 14.dp, vertical = 8.dp)
            ) {
                val isWideScreen = maxWidth >= 840.dp

                if (isWideScreen) {
                    // 3-COLUMN DESKTOP / TABLET EDITORIAL GRID
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        // COLUMN 1: Lead Story & White House Transition (5 cols weight: 5f)
                        Column(
                            modifier = Modifier
                                .weight(5f)
                                .border(width = 0.dp, color = Color.Transparent)
                        ) {
                            LeadStoryArticle(
                                article = NewsRepository.leadArticle,
                                onArticleClick = { selectedArticle = it }
                            )

                            HorizontalDivider(color = NytBorder, thickness = 1.dp, modifier = Modifier.padding(vertical = 12.dp))

                            val secondArticle = NewsRepository.articlesList.firstOrNull { it.id == "press-secretary-bar" }
                            if (secondArticle != null) {
                                SecondaryArticleCard(
                                    article = secondArticle,
                                    onArticleClick = { selectedArticle = it }
                                )
                            }
                        }

                        // Vertical Divider
                        Box(
                            modifier = Modifier
                                .width(1.dp)
                                .height(720.dp)
                                .background(NytBorder)
                        )

                        // COLUMN 2: Secondary World & Domestic Focus (4 cols weight: 4f)
                        Column(
                            modifier = Modifier.weight(4f)
                        ) {
                            val ukraineArticle = NewsRepository.articlesList.firstOrNull { it.id == "ukraine-satellite" }
                            if (ukraineArticle != null) {
                                UkraineWarPackage(
                                    article = ukraineArticle,
                                    onArticleClick = { selectedArticle = it }
                                )
                            }

                            HorizontalDivider(color = NytBorder, thickness = 1.dp, modifier = Modifier.padding(vertical = 10.dp))

                            val electionArticle = NewsRepository.articlesList.firstOrNull { it.id == "election-rust-belt" }
                            if (electionArticle != null) {
                                SecondaryArticleCard(
                                    article = electionArticle,
                                    onArticleClick = { selectedArticle = it }
                                )
                            }

                            // Polling Widget
                            PollingTrackerWidget(
                                pollData = pollData,
                                onNextStateClick = { pollStateIndex++ },
                                modifier = Modifier.padding(vertical = 8.dp)
                            )

                            HorizontalDivider(color = NytBorder, thickness = 1.dp, modifier = Modifier.padding(vertical = 10.dp))

                            val aiArticle = NewsRepository.articlesList.firstOrNull { it.id == "ai-dinner-party" }
                            if (aiArticle != null) {
                                SecondaryArticleCard(
                                    article = aiArticle,
                                    onArticleClick = { selectedArticle = it }
                                )
                            }
                        }

                        // Vertical Divider
                        Box(
                            modifier = Modifier
                                .width(1.dp)
                                .height(720.dp)
                                .background(NytBorder)
                        )

                        // COLUMN 3: Opinion, NYT Games, Wirecutter (3 cols weight: 3f)
                        Column(
                            modifier = Modifier.weight(3f),
                            verticalArrangement = Arrangement.spacedBy(14.dp)
                        ) {
                            // Opinion Block
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .border(1.dp, NytBorder, RoundedCornerShape(2.dp))
                                    .padding(10.dp)
                            ) {
                                Text(
                                    text = "OPINION",
                                    fontFamily = EditorialSansFontFamily,
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = NytBlack,
                                    letterSpacing = 1.sp,
                                    modifier = Modifier.padding(bottom = 6.dp)
                                )
                                HorizontalDivider(color = NytBlack, thickness = 2.dp, modifier = Modifier.padding(bottom = 6.dp))

                                val opinionArticles = NewsRepository.articlesList.filter { it.section == "Opinion" }
                                opinionArticles.forEachIndexed { idx, op ->
                                    OpinionColumnistRow(
                                        article = op,
                                        onArticleClick = { selectedArticle = it },
                                        showDivider = idx > 0
                                    )
                                }
                            }

                            // NYT Games Showcase Card
                            NytGamesShowcaseCard(
                                onGameClick = { gameType ->
                                    activeGameDialog = gameType
                                }
                            )

                            // Wirecutter Card
                            val wirecutterArticle = NewsRepository.articlesList.firstOrNull { it.id == "wirecutter-vacuums" }
                            if (wirecutterArticle != null) {
                                WirecutterCard(
                                    article = wirecutterArticle,
                                    onArticleClick = { selectedArticle = it }
                                )
                            }
                        }
                    }
                } else {
                    // COMPACT MOBILE EDITORIAL LAYOUT (Optimized for Handhelds)
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(14.dp)
                    ) {
                        // 1. Lead Story with full photo & bullets
                        LeadStoryArticle(
                            article = NewsRepository.leadArticle,
                            onArticleClick = { selectedArticle = it }
                        )

                        HorizontalDivider(color = NytBorder, thickness = 1.dp)

                        // 2. War in Ukraine package
                        val ukraineArticle = NewsRepository.articlesList.firstOrNull { it.id == "ukraine-satellite" }
                        if (ukraineArticle != null) {
                            UkraineWarPackage(
                                article = ukraineArticle,
                                onArticleClick = { selectedArticle = it }
                            )
                        }

                        HorizontalDivider(color = NytBorder, thickness = 1.dp)

                        // 3. Elections Tracker & Polling Widget
                        val electionArticle = NewsRepository.articlesList.firstOrNull { it.id == "election-rust-belt" }
                        if (electionArticle != null) {
                            SecondaryArticleCard(
                                article = electionArticle,
                                onArticleClick = { selectedArticle = it }
                            )
                        }

                        PollingTrackerWidget(
                            pollData = pollData,
                            onNextStateClick = { pollStateIndex++ }
                        )

                        HorizontalDivider(color = NytBorder, thickness = 1.dp)

                        // 4. Play NYT Games Card (Wordle, Connections, The Mini, Spelling Bee)
                        NytGamesShowcaseCard(
                            onGameClick = { gameType ->
                                activeGameDialog = gameType
                            }
                        )

                        HorizontalDivider(color = NytBorder, thickness = 1.dp)

                        // 5. White House Transition
                        val secondArticle = NewsRepository.articlesList.firstOrNull { it.id == "press-secretary-bar" }
                        if (secondArticle != null) {
                            SecondaryArticleCard(
                                article = secondArticle,
                                onArticleClick = { selectedArticle = it }
                            )
                        }

                        HorizontalDivider(color = NytBorder, thickness = 1.dp)

                        // 6. A.I. & Society
                        val aiArticle = NewsRepository.articlesList.firstOrNull { it.id == "ai-dinner-party" }
                        if (aiArticle != null) {
                            SecondaryArticleCard(
                                article = aiArticle,
                                onArticleClick = { selectedArticle = it }
                            )
                        }

                        HorizontalDivider(color = NytBorder, thickness = 1.dp)

                        // 7. Opinion Block
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .border(1.dp, NytBorder, RoundedCornerShape(2.dp))
                                .padding(12.dp)
                        ) {
                            Text(
                                text = "OPINION",
                                fontFamily = EditorialSansFontFamily,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                color = NytBlack,
                                letterSpacing = 1.sp,
                                modifier = Modifier.padding(bottom = 6.dp)
                            )
                            HorizontalDivider(color = NytBlack, thickness = 2.dp, modifier = Modifier.padding(bottom = 8.dp))

                            val opinionArticles = NewsRepository.articlesList.filter { it.section == "Opinion" }
                            opinionArticles.forEachIndexed { idx, op ->
                                OpinionColumnistRow(
                                    article = op,
                                    onArticleClick = { selectedArticle = it },
                                    showDivider = idx > 0
                                )
                            }
                        }

                        HorizontalDivider(color = NytBorder, thickness = 1.dp)

                        // 8. Wirecutter Card
                        val wirecutterArticle = NewsRepository.articlesList.firstOrNull { it.id == "wirecutter-vacuums" }
                        if (wirecutterArticle != null) {
                            WirecutterCard(
                                article = wirecutterArticle,
                                onArticleClick = { selectedArticle = it }
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Footer Section with Directory & Copyright
            NytFooter(
                onSectionClick = { section ->
                    selectedSection = section
                },
                onSubscribeClick = { showSubscribeDialog = true },
                onLoginClick = { showLoginDialog = true }
            )
        }
    }

    // Interactive Dialogs
    if (showSearchDialog) {
        SearchDialog(
            initialQuery = activeTrendingQuery ?: "",
            onArticleSelected = { article ->
                selectedArticle = article
            },
            onDismiss = {
                showSearchDialog = false
                activeTrendingQuery = null
            }
        )
    }

    if (showSubscribeDialog) {
        SubscriptionDialog(
            onDismiss = { showSubscribeDialog = false }
        )
    }

    if (showLoginDialog) {
        LoginDialog(
            onDismiss = { showLoginDialog = false }
        )
    }

    if (activeGameDialog == GameType.WORDLE) {
        WordleGameDialog(
            onDismiss = { activeGameDialog = null }
        )
    } else if (activeGameDialog == GameType.THE_MINI) {
        MiniCrosswordDialog(
            onDismiss = { activeGameDialog = null }
        )
    } else if (activeGameDialog == GameType.CONNECTIONS || activeGameDialog == GameType.SPELLING_BEE) {
        // Quick interactive puzzle showcase dialog
        Dialog(onDismissRequest = { activeGameDialog = null }) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                shape = RoundedCornerShape(8.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Column(
                    modifier = Modifier.padding(18.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    val gameName = if (activeGameDialog == GameType.CONNECTIONS) "Connections" else "Spelling Bee"
                    val desc = if (activeGameDialog == GameType.CONNECTIONS)
                        "Group words that share a common thread! Four groups of four await your discovery."
                    else
                        "How many words can you make with 7 letters? Center letter is mandatory."

                    Text(
                        text = gameName,
                        fontFamily = EditorialSansFontFamily,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = NytBlack
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = desc,
                        fontFamily = HeadlineFontFamily,
                        fontSize = 14.sp,
                        color = NytDarkGray
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Daily puzzle ready for play. Tap to start solving!",
                        fontSize = 12.sp,
                        color = NytLightGray
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    androidx.compose.material3.Button(
                        onClick = { activeGameDialog = null },
                        colors = androidx.compose.material3.ButtonDefaults.buttonColors(containerColor = NytBlack),
                        shape = RoundedCornerShape(4.dp)
                    ) {
                        Text("BACK TO FRONT PAGE", fontFamily = EditorialSansFontFamily, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    }

    // Edition Switcher Dialog
    if (showEditionDialog) {
        Dialog(onDismissRequest = { showEditionDialog = false }) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                shape = RoundedCornerShape(8.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Select Edition",
                        fontFamily = EditorialSansFontFamily,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = NytBlack,
                        modifier = Modifier.padding(bottom = 12.dp)
                    )
                    HorizontalDivider(color = NytBorder, thickness = 1.dp, modifier = Modifier.padding(bottom = 8.dp))
                    NytEdition.values().forEach { edition ->
                        val isSelected = currentEdition == edition
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    currentEdition = edition
                                    showEditionDialog = false
                                }
                                .padding(vertical = 10.dp, horizontal = 8.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = edition.displayName,
                                fontFamily = EditorialSansFontFamily,
                                fontSize = 14.sp,
                                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                                color = if (isSelected) NytBlack else NytDarkGray
                            )
                            if (isSelected) {
                                Text(text = "✓", color = NytBlack, fontWeight = FontWeight.Bold)
                            }
                        }
                    }
                }
            }
        }
    }
}
