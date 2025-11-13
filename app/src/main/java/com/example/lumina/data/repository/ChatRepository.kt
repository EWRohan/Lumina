package com.example.lumina.data.repository

import android.util.Log
import com.example.lumina.data.local.MessageDao
import com.example.lumina.data.local.MessageEntity
import com.example.lumina.data.model.GroqMessage
import com.example.lumina.data.model.GroqRequest
import com.example.lumina.data.remote.GroqApiService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ChatRepository @Inject constructor(
    private val api: GroqApiService,
    private val dao: MessageDao
) {

    fun getMessages(): Flow<List<MessageEntity>> = dao.getAllMessages()

    suspend fun saveMessage(message: MessageEntity) {
        Log.d("ChatRepository", "Saving message to DB: ${message.text}")
        dao.insertMessage(message)
    }

    suspend fun clearMessages() = dao.clearMessages()

    suspend fun getAIResponse(prompt: String): String {
        val request = GroqRequest(
            messages = listOf(
                GroqMessage(role = "user", content = prompt)
            )
        )

        val response = api.generateResponse(request)
        return response.choices.firstOrNull()?.message?.content ?: "No response"
    }
}