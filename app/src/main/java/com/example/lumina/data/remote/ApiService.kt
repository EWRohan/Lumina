package com.example.lumina.data.remote


import com.example.lumina.data.model.GroqRequest
import com.example.lumina.data.model.GroqResponse
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface GroqApiService {
    @Headers("Content-Type: application/json")
    @POST("chat/completions")
    suspend fun generateResponse(
        @Body request: GroqRequest
    ): GroqResponse
}
