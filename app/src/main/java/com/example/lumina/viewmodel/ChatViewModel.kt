package com.example.lumina.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lumina.data.local.MessageEntity
import com.example.lumina.data.repository.ChatRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val repository: ChatRepository
) : ViewModel() {

    private val _messages = MutableStateFlow<List<MessageEntity>>(emptyList())
    val messages = _messages.asStateFlow()

    init {
        viewModelScope.launch {
            repository.getMessages().collect { _messages.value = it }
        }
    }

    fun sendMessage(text: String) {
        viewModelScope.launch {
            try {
                Log.d("ChatViewModel", "Saving user message: $text")
                repository.saveMessage(MessageEntity(text = text, isUser = true))

                Log.d("ChatViewModel", "Fetching AI response...")
                val aiResponse = repository.getAIResponse(text)
                Log.d("ChatViewModel", "AI response received: $aiResponse")

                repository.saveMessage(MessageEntity(text = aiResponse, isUser = false))
            } catch (e: Exception) {
                Log.e("ChatViewModel", "Error sending message", e)
            }
        }
    }

    fun clearChat() = viewModelScope.launch {
        repository.clearMessages()
    }
}