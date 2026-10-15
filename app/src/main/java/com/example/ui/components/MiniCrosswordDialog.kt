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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
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
import androidx.compose.runtime.mutableStateMapOf
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
import com.example.ui.theme.MiniBlue
import com.example.ui.theme.NytBlack
import com.example.ui.theme.NytBorder
import com.example.ui.theme.NytCardBg
import com.example.ui.theme.NytLightGray

@Composable
fun MiniCrosswordDialog(
    onDismiss: () -> Unit
) {
    // 5x5 mini crossword solution
    // P R E S S
    // A L O N E
    // P O L L S
    // E X I T S
    // R E A D Y
    val solution = mapOf(
        0 to "PRESS",
        1 to "ALONE",
        2 to "POLLS",
        3 to "EXITS",
        4 to "READY"
    )

    val gridLetters = remember { mutableStateMapOf<Pair<Int, Int>, Char>() }
    var selectedCell by remember { mutableStateOf<Pair<Int, Int>?>(Pair(0, 0)) }
    var isSolved by remember { mutableStateOf(false) }

    fun checkAnswers() {
        var allCorrect = true
        for (r in 0 until 5) {
            val solRow = solution[r] ?: ""
            for (c in 0 until 5) {
                if (gridLetters[Pair(r, c)] != solRow[c]) {
                    allCorrect = false
                }
            }
        }
        isSolved = allCorrect
    }

    Dialog(onDismissRequest = onDismiss) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp)
                .testTag("mini_crossword_dialog"),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Header
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                        Text(
                            text = "THE MINI",
                            fontFamily = EditorialSansFontFamily,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = MiniBlue,
                            letterSpacing = 1.sp
                        )
                        Text(
                            text = "• Quick solve",
                            fontSize = 11.sp,
                            color = NytLightGray,
                            fontFamily = EditorialSansFontFamily
                        )
                    }
                    IconButton(onClick = onDismiss, modifier = Modifier.size(24.dp)) {
                        Icon(imageVector = Icons.Default.Close, contentDescription = "Close", tint = NytBlack)
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                // 5x5 Crossword Grid
                Column(
                    verticalArrangement = Arrangement.spacedBy(2.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    for (row in 0 until 5) {
                        Row(horizontalArrangement = Arrangement.spacedBy(2.dp)) {
                            for (col in 0 until 5) {
                                val cell = Pair(row, col)
                                val isSelected = selectedCell == cell
                                val letter = gridLetters[cell]

                                Box(
                                    modifier = Modifier
                                        .size(44.dp)
                                        .background(
                                            if (isSelected) Color(0xFFA7D8FF) else Color(0xFFFAFAFA),
                                            RoundedCornerShape(2.dp)
                                        )
                                        .border(
                                            1.dp,
                                            if (isSelected) MiniBlue else NytBorder,
                                            RoundedCornerShape(2.dp)
                                        )
                                        .clickable { selectedCell = cell },
                                    contentAlignment = Alignment.Center
                                ) {
                                    // Number badge if top-left or starting cell
                                    val clueNum = when (row to col) {
                                        0 to 0 -> "1"
                                        0 to 1 -> "2"
                                        0 to 2 -> "3"
                                        0 to 3 -> "4"
                                        0 to 4 -> "5"
                                        1 to 0 -> "6"
                                        2 to 0 -> "7"
                                        3 to 0 -> "8"
                                        4 to 0 -> "9"
                                        else -> null
                                    }
                                    if (clueNum != null) {
                                        Text(
                                            text = clueNum,
                                            fontSize = 8.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = NytLightGray,
                                            modifier = Modifier
                                                .align(Alignment.TopStart)
                                                .padding(2.dp)
                                        )
                                    }

                                    Text(
                                        text = letter?.toString() ?: "",
                                        fontSize = 18.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = NytBlack
                                    )
                                }
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                if (isSolved) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color(0xFF22C55E), RoundedCornerShape(4.dp))
                            .padding(8.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Congratulations! Puzzle Completed in 0:42!",
                            fontFamily = EditorialSansFontFamily,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }
                }

                // Clues summary
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(NytCardBg, RoundedCornerShape(4.dp))
                        .padding(8.dp)
                ) {
                    Text(
                        text = "ACROSS CLUES",
                        fontFamily = EditorialSansFontFamily,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        color = NytBlack
                    )
                    Text(text = "1. Journalists corps (PRESS)", fontSize = 11.sp, color = NytBlack)
                    Text(text = "6. Solitary or unaccompanied (ALONE)", fontSize = 11.sp, color = NytBlack)
                    Text(text = "7. Voting survey trackers (POLLS)", fontSize = 11.sp, color = NytBlack)
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "DOWN CLUES",
                        fontFamily = EditorialSansFontFamily,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        color = NytBlack
                    )
                    Text(text = "1. Material to write on (PAPER)", fontSize = 11.sp, color = NytBlack)
                    Text(text = "2. Musical sound (ROAR)", fontSize = 11.sp, color = NytBlack)
                }

                Spacer(modifier = Modifier.height(10.dp))

                // Letter buttons quick entry
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Button(
                        onClick = {
                            // Autofill puzzle to celebrate
                            for (r in 0 until 5) {
                                val rowStr = solution[r] ?: ""
                                for (c in 0 until 5) {
                                    gridLetters[Pair(r, c)] = rowStr[c]
                                }
                            }
                            checkAnswers()
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = MiniBlue),
                        shape = RoundedCornerShape(4.dp),
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("SOLVE", fontSize = 11.sp, fontWeight = FontWeight.Bold)
                    }

                    Button(
                        onClick = {
                            gridLetters.clear()
                            isSolved = false
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Color.LightGray),
                        shape = RoundedCornerShape(4.dp),
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("CLEAR", fontSize = 11.sp, color = NytBlack, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    }
}
