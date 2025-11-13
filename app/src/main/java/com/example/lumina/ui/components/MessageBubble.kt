package com.example.lumina.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.lumina.data.local.MessageEntity
import dev.jeziellago.compose.markdowntext.MarkdownText

@Composable
fun ChatBubble(
    message: MessageEntity,
    modifier: Modifier = Modifier
) {
    val isUser = message.isUser
    val textColor = if (isUser) Color.White else Color(0xFFE0E0E0)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 6.dp),
        horizontalArrangement = if (isUser) Arrangement.End else Arrangement.Start
    ) {
        Surface(
            color = if (isUser) Color(0xFF1565C0) else Color(0xFF1E1E1E),
            shape = RoundedCornerShape(
                topStart = 16.dp,
                topEnd = 16.dp,
                bottomEnd = if (isUser) 0.dp else 16.dp,
                bottomStart = if (isUser) 16.dp else 0.dp
            ),
            shadowElevation = 6.dp,
            modifier = modifier.widthIn(max = 320.dp)
        ) {
            Column(modifier = Modifier.padding(12.dp)) {
                if (!isUser) {
                    Text(
                        text = "Lumina",
                        color = Color(0xFF90CAF9),
                        style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold)
                    )
                    Spacer(Modifier.height(4.dp))
                }

                // Render rich Markdown + code blocks
                RenderMarkdownWithCode(message.text, textColor)
            }
        }
    }
}

@Composable
fun RenderMarkdownWithCode(text: String, textColor: Color) {
    val codeBlockRegex = Regex("```(.*?)```", RegexOption.DOT_MATCHES_ALL)
    val parts = codeBlockRegex.split(text)
    val codeBlocks = codeBlockRegex.findAll(text).map { it.groupValues[1] }.toList()

    for (i in parts.indices) {
        // Normal Markdown text
        if (parts[i].isNotBlank()) {
            MarkdownText(
                markdown = parts[i],
                color = textColor,
                style = MaterialTheme.typography.bodyMedium,
                linkColor = Color(0xFF4FC3F7)
            )
        }

        // Code blocks styled separately
        if (i < codeBlocks.size) {
            CodeBlock(codeBlocks[i])
        }
    }
}

@Composable
fun CodeBlock(code: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
            .background(Color(0xFF263238), shape = RoundedCornerShape(8.dp))
            .padding(10.dp)
    ) {
        Text(
            text = code.trim(),
            color = Color(0xFF80CBC4),
            fontFamily = FontFamily.Monospace,
            style = MaterialTheme.typography.bodySmall
        )
    }
}
