package com.example.lumina.di

import android.content.Context
import androidx.room.Room
import com.example.lumina.data.local.ChatDatabase
import com.example.lumina.data.local.MessageDao
import com.example.lumina.data.remote.GroqApiService
import com.example.lumina.data.remote.RetrofitInstance
import com.example.lumina.data.repository.ChatRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import com.example.lumina.BuildConfig
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): ChatDatabase =
        Room.databaseBuilder(
            context,
            ChatDatabase::class.java,
            "chat_db"
        ).build()

    @Provides
    fun provideDao(db: ChatDatabase): MessageDao = db.messageDao()

    @Provides
    @Singleton
    fun provideApi(): GroqApiService = RetrofitInstance.create(BuildConfig.GROQ_API_KEY)

    @Provides
    @Singleton
    fun provideRepository(api: GroqApiService, dao: MessageDao): ChatRepository =
        ChatRepository(api, dao)
}