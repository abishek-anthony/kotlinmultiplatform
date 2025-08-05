package com.abishekanthony.kmp.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun EditFieldWithApplyButton(
    message: String,
    onMessageChange: (String) -> Unit,
    onApplyClick: () -> Unit,
    placeHolder: String,
    buttonText: String,
){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        TextField(
            value = message,
            onValueChange = onMessageChange,
            modifier = Modifier.weight(1f),
            placeholder = { Text(placeHolder) }
        )
        Spacer(modifier = Modifier.width(8.dp))
        Button(
            onClick = {
                onMessageChange(message.trim())
                onApplyClick()
                onMessageChange("")
            },
            enabled = message.isNotBlank()
        ) {
            Text(buttonText)
        }
    }
}