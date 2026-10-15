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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
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
import com.example.ui.theme.NytBlack
import com.example.ui.theme.WordleGray
import com.example.ui.theme.WordleGreen
import com.example.ui.theme.WordleYellow

@Composable
fun WordleGameDialog(
    onDismiss: () -> Unit
) {
    val targetWord = "TIMES"
    val guesses = remember { mutableStateListOf<String>() }
    var currentGuess by remember { mutableStateOf("") }
    var gameWon by remember { mutableStateOf(false) }
    var gameOver by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    fun submitGuess() {
        if (currentGuess.length != 5) {
            errorMessage = "Not enough letters"
            return
        }
        val guess = currentGuess.uppercase()
        guesses.add(guess)
        if (guess == targetWord) {
            gameWon = true
            gameOver = true
        } else if (guesses.size >= 6) {
            gameOver = true
        }
        currentGuess = ""
        errorMessage = null
    }

    Dialog(onDismissRequest = onDismiss) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .testTag("wordle_dialog"),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Header
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "WORDLE",
                        fontFamily = EditorialSansFontFamily,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = NytBlack,
                        letterSpacing = 1.sp
                    )
                    IconButton(onClick = onDismiss, modifier = Modifier.size(24.dp)) {
                        Icon(imageVector = Icons.Default.Close, contentDescription = "Close", tint = NytBlack)
                    }
                }

                Text(
                    text = "Puzzle No. 1,211 • Get 6 chances to guess a 5-letter word.",
                    fontFamily = EditorialSansFontFamily,
                    fontSize = 11.sp,
                    color = Color.Gray,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(bottom = 12.dp)
                )

                if (errorMessage != null) {
                    Text(
                        text = errorMessage ?: "",
                        fontFamily = EditorialSansFontFamily,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Red,
                        modifier = Modifier.padding(bottom = 6.dp)
                    )
                }

                // 6 Rows of 5 tiles
                Column(
                    verticalArrangement = Arrangement.spacedBy(4.dp),
                    modifier = Modifier.padding(bottom = 12.dp)
                ) {
                    for (row in 0 until 6) {
                        Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                            for (col in 0 until 5) {
                                val charInRow: Char? = when {
                                    row < guesses.size -> guesses[row].getOrNull(col)
                                    row == guesses.size -> currentGuess.getOrNull(col)
                                    else -> null
                                }

                                val bgColor: Color = when {
                                    row < guesses.size -> {
                                        val c = guesses[row][col]
                                        when {
                                            targetWord[col] == c -> WordleGreen
                                            targetWord.contains(c) -> WordleYellow
                                            else -> WordleGray
                                        }
                                    }
                                    else -> Color.White
                                }

                                val fontColor: Color = if (row < guesses.size) Color.White else NytBlack
                                val borderColor: Color = when {
                                    row < guesses.size -> bgColor
                                    charInRow != null -> NytBlack
                                    else -> Color(0xFFD3D6DA)
                                }

                                Box(
                                    modifier = Modifier
                                        .size(42.dp)
                                        .background(bgColor, RoundedCornerShape(2.dp))
                                        .border(2.dp, borderColor, RoundedCornerShape(2.dp)),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = charInRow?.toString() ?: "",
                                        fontFamily = EditorialSansFontFamily,
                                        fontSize = 20.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = fontColor
                                    )
                                }
                            }
                        }
                    }
                }

                // Game outcome banner
                if (gameOver) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(if (gameWon) WordleGreen else Color(0xFFEF4444), RoundedCornerShape(6.dp))
                            .padding(10.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = if (gameWon) "Splendid! You guessed $targetWord in ${guesses.size} tries!" else "Nice try! The word was $targetWord.",
                            fontFamily = EditorialSansFontFamily,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            textAlign = TextAlign.Center
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                }

                // Virtual On-Screen Keyboard
                if (!gameOver) {
                    val row1 = listOf("Q","W","E","R","T","Y","U","I","O","P")
                    val row2 = listOf("A","S","D","F","G","H","J","K","L")
                    val row3 = listOf("Z","X","C","V","B","N","M")

                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(4.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(horizontalArrangement = Arrangement.spacedBy(3.dp)) {
                            row1.forEach { letter ->
                                KeyboardKey(letter = letter, onClick = {
                                    if (currentGuess.length < 5) currentGuess += letter
                                })
                            }
                        }
                        Row(horizontalArrangement = Arrangement.spacedBy(3.dp)) {
                            row2.forEach { letter ->
                                KeyboardKey(letter = letter, onClick = {
                                    if (currentGuess.length < 5) currentGuess += letter
                                })
                            }
                        }
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(3.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            // ENTER key
                            Box(
                                modifier = Modifier
                                    .height(38.dp)
                                    .background(Color(0xFFD3D6DA), RoundedCornerShape(4.dp))
                                    .clickable { submitGuess() }
                                    .padding(horizontal = 6.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Text("ENTER", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = NytBlack)
                            }
                            row3.forEach { letter ->
                                KeyboardKey(letter = letter, onClick = {
                                    if (currentGuess.length < 5) currentGuess += letter
                                })
                            }
                            // DEL key
                            Box(
                                modifier = Modifier
                                    .height(38.dp)
                                    .background(Color(0xFFD3D6DA), RoundedCornerShape(4.dp))
                                    .clickable {
                                        if (currentGuess.isNotEmpty()) {
                                            currentGuess = currentGuess.dropLast(1)
                                        }
                                    }
                                    .padding(horizontal = 8.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Text("⌫", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = NytBlack)
                            }
                        }
                    }
                } else {
                    Button(
                        onClick = {
                            guesses.clear()
                            currentGuess = ""
                            gameOver = false
                            gameWon = false
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = NytBlack),
                        shape = RoundedCornerShape(4.dp),
                        modifier = Modifier.padding(top = 8.dp)
                    ) {
                        Text("PLAY AGAIN", fontFamily = EditorialSansFontFamily, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    }
}

@Composable
private fun KeyboardKey(
    letter: String,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .size(width = 28.dp, height = 38.dp)
            .background(Color(0xFFD3D6DA), RoundedCornerShape(4.dp))
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = letter,
            fontFamily = EditorialSansFontFamily,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = NytBlack
        )
    }
}
