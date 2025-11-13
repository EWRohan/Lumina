package com.example.lumina.data.model

data class ChatSession(
    val id: Int = 0,
    val title: String,
    val messages: List<Message> = emptyList()
)
