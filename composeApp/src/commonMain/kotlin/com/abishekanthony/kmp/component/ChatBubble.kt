package com.abishekanthony.kmp.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.abishekanthony.kmp.dto.ChatMessage


@Composable
fun ChatBubble(chatMessage: ChatMessage, arrangeHorizontalLeft: Boolean, leftArrangementColor: Color = Color.White, rightArrangementColor: Color = Color(0xFFE3F2FD)) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = if (arrangeHorizontalLeft) Arrangement.Start else Arrangement.End,
    ) {
        Card(
            elevation = 4.dp,
            shape = getRoundedCornerShape(),
            backgroundColor = getColor(arrangeHorizontalLeft, leftArrangementColor, rightArrangementColor),
            modifier = Modifier
                .padding(vertical = 4.dp, horizontal = 16.dp)
                .border(
                    width = 1.dp,
                    color = getColor(arrangeHorizontalLeft, leftArrangementColor, rightArrangementColor),
                    shape = getRoundedCornerShape()
                )
        ) {
            Text(
                text = chatMessage.text,
                modifier = Modifier.padding(12.dp)
            )
        }
    }
}

private fun getRoundedCornerShape(): RoundedCornerShape = RoundedCornerShape(12.dp)

private fun getColor(
    arrangeHorizontalLeft: Boolean,
    leftArrangementColor: Color,
    rightArrangementColor: Color
): Color = if (arrangeHorizontalLeft) leftArrangementColor else rightArrangementColor