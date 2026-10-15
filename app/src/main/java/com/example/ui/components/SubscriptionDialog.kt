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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.ui.theme.EditorialSansFontFamily
import com.example.ui.theme.MastheadFontFamily
import com.example.ui.theme.NytBlack
import com.example.ui.theme.NytBorder
import com.example.ui.theme.NytLightGray
import com.example.ui.theme.NytSubscribeBlue

@Composable
fun SubscriptionDialog(
    onDismiss: () -> Unit
) {
    var isSubscribed by remember { mutableStateOf(false) }

    Dialog(onDismissRequest = onDismiss) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .testTag("subscription_dialog"),
            shape = RoundedCornerShape(8.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Top close
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "The New York Times",
                        fontFamily = MastheadFontFamily,
                        fontSize = 22.sp,
                        color = NytBlack
                    )
                    IconButton(onClick = onDismiss, modifier = Modifier.size(24.dp)) {
                        Icon(imageVector = Icons.Default.Close, contentDescription = "Close", tint = NytBlack)
                    }
                }

                HorizontalDivider(color = NytBorder, thickness = 1.dp, modifier = Modifier.padding(vertical = 12.dp))

                if (!isSubscribed) {
                    Text(
                        text = "SPECIAL OFFER",
                        fontFamily = EditorialSansFontFamily,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        color = NytSubscribeBlue,
                        letterSpacing = 1.sp
                    )

                    Text(
                        text = "Subscribe for $1/week",
                        fontFamily = EditorialSansFontFamily,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = NytBlack,
                        modifier = Modifier.padding(vertical = 4.dp)
                    )

                    Text(
                        text = "Billed as $4 every 4 weeks for the first year. Cancel anytime.",
                        fontFamily = EditorialSansFontFamily,
                        fontSize = 12.sp,
                        color = NytLightGray,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )

                    // Features list
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color(0xFFF9FAFB), RoundedCornerShape(6.dp))
                            .padding(12.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        SubscriptionFeatureItem("Unlimited access to all of The New York Times")
                        SubscriptionFeatureItem("NYT Games: Wordle, Connections, and The Crossword")
                        SubscriptionFeatureItem("NYT Cooking: 20,000+ recipes and meal planning")
                        SubscriptionFeatureItem("Wirecutter: Independent, unbiased product reviews")
                        SubscriptionFeatureItem("The Athletic: In-depth sports journalism")
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(
                        onClick = { isSubscribed = true },
                        colors = ButtonDefaults.buttonColors(containerColor = NytSubscribeBlue),
                        shape = RoundedCornerShape(4.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(44.dp)
                            .testTag("confirm_subscription_button")
                    ) {
                        Text(
                            text = "GET THE ALL ACCESS PASS",
                            fontFamily = EditorialSansFontFamily,
                            fontWeight = FontWeight.Bold,
                            fontSize = 12.sp,
                            letterSpacing = 0.5.sp
                        )
                    }
                } else {
                    Box(
                        modifier = Modifier
                            .size(56.dp)
                            .background(Color(0xFFDCFCE7), RoundedCornerShape(28.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Check,
                            contentDescription = "Success",
                            tint = Color(0xFF16A34A),
                            modifier = Modifier.size(32.dp)
                        )
                    }

                    Text(
                        text = "Welcome to All Access!",
                        fontFamily = EditorialSansFontFamily,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = NytBlack,
                        modifier = Modifier.padding(top = 12.dp, bottom = 4.dp)
                    )

                    Text(
                        text = "Your digital subscription is active. Enjoy unlimited access across all NYT applications.",
                        fontFamily = EditorialSansFontFamily,
                        fontSize = 12.sp,
                        color = NytLightGray,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )

                    Button(
                        onClick = onDismiss,
                        colors = ButtonDefaults.buttonColors(containerColor = NytBlack),
                        shape = RoundedCornerShape(4.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("START READING", fontFamily = EditorialSansFontFamily, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    }
}

@Composable
private fun SubscriptionFeatureItem(text: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Icon(
            imageVector = Icons.Default.Check,
            contentDescription = null,
            tint = NytSubscribeBlue,
            modifier = Modifier.size(16.dp)
        )
        Text(
            text = text,
            fontFamily = EditorialSansFontFamily,
            fontSize = 11.sp,
            color = NytBlack
        )
    }
}

@Composable
fun LoginDialog(
    onDismiss: () -> Unit
) {
    var loggedIn by remember { mutableStateOf(false) }

    Dialog(onDismissRequest = onDismiss) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .testTag("login_dialog"),
            shape = RoundedCornerShape(8.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Log In",
                        fontFamily = EditorialSansFontFamily,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = NytBlack
                    )
                    IconButton(onClick = onDismiss, modifier = Modifier.size(24.dp)) {
                        Icon(imageVector = Icons.Default.Close, contentDescription = "Close", tint = NytBlack)
                    }
                }

                HorizontalDivider(color = NytBorder, thickness = 1.dp, modifier = Modifier.padding(vertical = 12.dp))

                if (!loggedIn) {
                    Text(
                        text = "Log in to your New York Times account to access your saved articles, crosswords, and preferences.",
                        fontFamily = EditorialSansFontFamily,
                        fontSize = 12.sp,
                        color = NytLightGray,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )

                    Button(
                        onClick = { loggedIn = true },
                        colors = ButtonDefaults.buttonColors(containerColor = NytBlack),
                        shape = RoundedCornerShape(4.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(42.dp)
                            .testTag("submit_login_btn")
                    ) {
                        Text("CONTINUE AS SUBSCRIBER", fontFamily = EditorialSansFontFamily, fontWeight = FontWeight.Bold, fontSize = 11.sp)
                    }
                } else {
                    Text(
                        text = "Logged in successfully as subscriber.",
                        fontFamily = EditorialSansFontFamily,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF16A34A),
                        modifier = Modifier.padding(bottom = 12.dp)
                    )
                    Button(
                        onClick = onDismiss,
                        colors = ButtonDefaults.buttonColors(containerColor = NytBlack),
                        shape = RoundedCornerShape(4.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("DONE", fontFamily = EditorialSansFontFamily)
                    }
                }
            }
        }
    }
}
