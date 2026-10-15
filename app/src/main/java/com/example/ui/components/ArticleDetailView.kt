package com.example.ui.components

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.ChatBubbleOutline
import androidx.compose.material.icons.filled.Forward10
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Replay10
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.model.Article
import com.example.model.ArticleComment
import com.example.ui.theme.EditorialSansFontFamily
import com.example.ui.theme.HeadlineFontFamily
import com.example.ui.theme.MastheadFontFamily
import com.example.ui.theme.NytBlack
import com.example.ui.theme.NytBorder
import com.example.ui.theme.NytCardBg
import com.example.ui.theme.NytDarkGray
import com.example.ui.theme.NytLightGray
import com.example.ui.theme.NytRed
import com.example.ui.theme.NytSubscribeBlue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArticleDetailView(
    article: Article,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    BackHandler { onBack() }

    var isBookmarked by remember { mutableStateOf(false) }
    var fontScale by remember { mutableFloatStateOf(1f) }
    var isPlayingAudio by remember { mutableStateOf(false) }
    var audioProgress by remember { mutableFloatStateOf(0.15f) }
    var showAudioBar by remember { mutableStateOf(false) }
    var showComments by remember { mutableStateOf(false) }

    val comments = remember {
        mutableStateListOf<ArticleComment>().apply {
            addAll(article.comments)
        }
    }
    var newCommentText by remember { mutableStateOf("") }

    val textColor = MaterialTheme.colorScheme.onBackground
    val scrollState = rememberScrollState()

    Scaffold(
        topBar = {
            Column(modifier = Modifier.fillMaxWidth().background(Color.White)) {
                TopAppBar(
                    title = {
                        Text(
                            text = "The New York Times",
                            fontFamily = MastheadFontFamily,
                            fontSize = 20.sp,
                            color = NytBlack
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = onBack, modifier = Modifier.testTag("article_back_btn")) {
                            Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = NytBlack)
                        }
                    },
                    actions = {
                        // Text Size Adjuster
                        IconButton(onClick = {
                            fontScale = if (fontScale >= 1.3f) 0.9f else fontScale + 0.15f
                        }) {
                            Text("aA", fontFamily = HeadlineFontFamily, fontWeight = FontWeight.Bold, fontSize = 14.sp, color = NytBlack)
                        }

                        // Listen audio
                        IconButton(onClick = { showAudioBar = !showAudioBar }) {
                            Icon(
                                imageVector = Icons.Default.VolumeUp,
                                contentDescription = "Listen",
                                tint = if (showAudioBar) NytSubscribeBlue else NytBlack
                            )
                        }

                        // Bookmark
                        IconButton(onClick = { isBookmarked = !isBookmarked }) {
                            Icon(
                                imageVector = if (isBookmarked) Icons.Default.Bookmark else Icons.Default.BookmarkBorder,
                                contentDescription = "Save",
                                tint = if (isBookmarked) NytSubscribeBlue else NytBlack
                            )
                        }

                        // Comments count
                        IconButton(onClick = { showComments = !showComments }) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = Icons.Default.ChatBubbleOutline,
                                    contentDescription = "Comments",
                                    tint = NytBlack,
                                    modifier = Modifier.size(18.dp)
                                )
                                Text(
                                    text = "${comments.size}",
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold,
                                    fontFamily = EditorialSansFontFamily,
                                    color = NytBlack,
                                    modifier = Modifier.padding(start = 2.dp)
                                )
                            }
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White)
                )

                // Optional Audio Player bar
                AnimatedVisibility(visible = showAudioBar) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(NytCardBg)
                            .border(1.dp, NytBorder)
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "NARRATION • 5 MIN READ",
                                fontFamily = EditorialSansFontFamily,
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                color = NytSubscribeBlue
                            )
                            Text(
                                text = "1x Speed",
                                fontFamily = EditorialSansFontFamily,
                                fontSize = 10.sp,
                                color = NytLightGray
                            )
                        }

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            IconButton(onClick = { isPlayingAudio = !isPlayingAudio }, modifier = Modifier.size(36.dp)) {
                                Icon(
                                    imageVector = if (isPlayingAudio) Icons.Default.Pause else Icons.Default.PlayArrow,
                                    contentDescription = "Play Audio",
                                    tint = NytBlack
                                )
                            }

                            Slider(
                                value = audioProgress,
                                onValueChange = { audioProgress = it },
                                modifier = Modifier.weight(1f),
                                colors = SliderDefaults.colors(
                                    thumbColor = NytBlack,
                                    activeTrackColor = NytBlack,
                                    inactiveTrackColor = NytBorder
                                )
                            )

                            Text(
                                text = "1:15 / 4:52",
                                fontFamily = EditorialSansFontFamily,
                                fontSize = 10.sp,
                                color = NytDarkGray
                            )
                        }
                    }
                }

                HorizontalDivider(color = NytBorder, thickness = 1.dp)
            }
        }
    ) { paddingValues ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color.White)
                .verticalScroll(scrollState)
                .padding(horizontal = 18.dp, vertical = 12.dp)
        ) {
            // Kicker
            if (article.kicker.isNotBlank()) {
                Text(
                    text = article.kicker,
                    fontFamily = EditorialSansFontFamily,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = NytRed,
                    letterSpacing = 0.8.sp,
                    modifier = Modifier.padding(bottom = 6.dp)
                )
            }

            // Headline
            Text(
                text = article.title,
                fontFamily = HeadlineFontFamily,
                fontSize = (30 * fontScale).sp,
                fontWeight = FontWeight.Bold,
                lineHeight = (36 * fontScale).sp,
                color = NytBlack,
                modifier = Modifier.padding(bottom = 10.dp)
            )

            // Subdeck / Excerpt
            Text(
                text = article.excerpt,
                fontFamily = HeadlineFontFamily,
                fontSize = (16 * fontScale).sp,
                lineHeight = (22 * fontScale).sp,
                color = NytDarkGray,
                modifier = Modifier.padding(bottom = 12.dp)
            )

            HorizontalDivider(color = NytBorder, thickness = 0.5.dp, modifier = Modifier.padding(bottom = 10.dp))

            // Author Byline & Date
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = article.author,
                        fontFamily = EditorialSansFontFamily,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = NytBlack
                    )
                    if (article.authorRole.isNotBlank()) {
                        Text(
                            text = article.authorRole,
                            fontFamily = EditorialSansFontFamily,
                            fontSize = 10.sp,
                            color = NytLightGray
                        )
                    }
                }
                Text(
                    text = article.date,
                    fontFamily = EditorialSansFontFamily,
                    fontSize = 10.sp,
                    color = NytLightGray
                )
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Photo if available
            if (article.localImageRes != null || !article.imageUrl.isNullOrBlank()) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1.5f)
                        .clip(RoundedCornerShape(2.dp))
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

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 4.dp, bottom = 14.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = article.imageCaption,
                        fontFamily = EditorialSansFontFamily,
                        fontSize = 10.sp,
                        color = NytLightGray,
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
            }

            // Article Body Paragraphs
            article.fullBody.forEachIndexed { index, paragraph ->
                Text(
                    text = paragraph,
                    fontFamily = HeadlineFontFamily,
                    fontSize = (17 * fontScale).sp,
                    lineHeight = (26 * fontScale).sp,
                    color = Color(0xFF1E1E1E),
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                // Insert a stylish pull quote in the middle
                if (index == 1) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(width = 1.dp, color = NytBorder)
                            .padding(14.dp)
                            .padding(vertical = 4.dp)
                    ) {
                        Text(
                            text = "“The speed of senior staffing changes has left several key West Wing desks facing abrupt leadership vacuums.”",
                            fontFamily = HeadlineFontFamily,
                            fontStyle = androidx.compose.ui.text.font.FontStyle.Italic,
                            fontSize = (19 * fontScale).sp,
                            lineHeight = (26 * fontScale).sp,
                            color = NytBlack
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }

            // Comments Section
            HorizontalDivider(color = NytBlack, thickness = 2.dp, modifier = Modifier.padding(top = 16.dp, bottom = 12.dp))

            Text(
                text = "Comments (${comments.size})",
                fontFamily = EditorialSansFontFamily,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = NytBlack,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            // Add Comment input box
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(NytCardBg, RoundedCornerShape(4.dp))
                    .border(1.dp, NytBorder, RoundedCornerShape(4.dp))
                    .padding(10.dp)
            ) {
                OutlinedTextField(
                    value = newCommentText,
                    onValueChange = { newCommentText = it },
                    placeholder = { Text("What are your thoughts?", fontSize = 12.sp, color = NytLightGray) },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(2.dp)
                )

                Spacer(modifier = Modifier.height(8.dp))

                Button(
                    onClick = {
                        if (newCommentText.isNotBlank()) {
                            comments.add(
                                ArticleComment(
                                    id = "c_${System.currentTimeMillis()}",
                                    userName = "You",
                                    userLocation = "Reader",
                                    timeAgo = "Just now",
                                    content = newCommentText.trim(),
                                    upvotes = 1
                                )
                            )
                            newCommentText = ""
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = NytBlack),
                    shape = RoundedCornerShape(2.dp),
                    modifier = Modifier.align(Alignment.End)
                ) {
                    Text("POST", fontFamily = EditorialSansFontFamily, fontSize = 11.sp, fontWeight = FontWeight.Bold)
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Comments list
            comments.forEach { comment ->
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                        .border(1.dp, NytBorder, RoundedCornerShape(2.dp))
                        .padding(10.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "${comment.userName} • ${comment.userLocation}",
                            fontFamily = EditorialSansFontFamily,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = NytBlack
                        )
                        Text(text = comment.timeAgo, fontSize = 10.sp, color = NytLightGray)
                    }
                    Text(
                        text = comment.content,
                        fontFamily = HeadlineFontFamily,
                        fontSize = 14.sp,
                        color = NytDarkGray,
                        modifier = Modifier.padding(vertical = 4.dp)
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Icon(imageVector = Icons.Default.ThumbUp, contentDescription = "Upvote", modifier = Modifier.size(12.dp), tint = NytLightGray)
                        Text(text = "${comment.upvotes}", fontSize = 10.sp, color = NytLightGray)
                    }
                }
            }

            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}
