package com.example.lumina.data.model

data class GroqMessage(
    val role: String,
    val content: String
)

data class GroqRequest(
    val model: String = "llama-3.1-8b-instant",
    val messages: List<GroqMessage>
)

data class GroqChoice(
    val message: GroqMessage
)

data class GroqResponse(
    val choices: List<GroqChoice>
)