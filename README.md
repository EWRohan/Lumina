# 🌟 Lumina — AI Chat App

**Lumina** is a minimal, futuristic AI chat app built with **Jetpack Compose** and **Kotlin**.  
It connects directly to the **Groq Cloud API**, using the **Llama 3 model**, and saves chat history locally with **Room Database**.  


---

## ✨ Features

- 🤖 Chat with Llama 3 via **Groq API**
- 💬 Beautiful chat UI with dynamic chat bubbles
- 💾 Local chat history storage (Room Database)
- 🧹 One-tap "Clear Chat" option
- 🌗 Adaptive UI — designed for both light and dark chat bubbles
- 🔒 Secure API key management using `BuildConfig`

---

## 🧠 Tech Stack

| Layer | Technology |
|-------|-------------|
| **UI** | Jetpack Compose |
| **Language** | Kotlin |
| **Local Storage** | Room Database |
| **Networking** | Retrofit + Coroutines |
| **AI Model** | Groq Cloud (Llama 3 / Mixtral) |
| **Dependency Injection** | Hilt |
| **Build System** | Gradle (KTS) |

---

## ⚙️ Setup Instructions

### 1️⃣ Clone the Repository
```bash
git clone https://github.com/<your-username>/Lumina.git
cd Lumina
```
## 2️⃣ Get a Groq API Key

  - Go to https://console.groq.com

  - Sign up (free)

  - Generate your API key
## 3️⃣ Add the API Key Securely

Create a file named local.properties :
```bash
GROQ_API_KEY=your_api_key_here
```
Then in your build.kts(app module) :
```Kotlin
android {
    defaultConfig {
        ...
        buildConfigField(
            "String",
            "GROQ_API_KEY",
            "\"${project.findProperty("GROQ_API_KEY") ?: ""}\""
        )
    }
}
```
Accens it via Kotlin :
```Kotlin
val apiKey = BuildConfig.GROQ_API_KEY
```

## 4️⃣ Run the App

- Connect your Android device or emulator

- Run the app from Android Studio (Shift + F10 or ▶️ button)

- Start chatting with Lumina 💬

## 🧾 Project Structure
```wasm
app/
├── data/
│   ├── local/ (Room DB)
│   ├── model/ (ChatMessage, API models)
│   ├── remote/ (Groq API integration)
├── ui/
│   ├── chat/ (Compose screens)
│   ├── theme/
├── di/ (Hilt modules)
└── MainActivity.kt
```

## 🧩 Future Enhancements

- 🧠 Markdown rendering for AI responses (coming soon)

- 🔊 Voice input/output

- 💬 Multi-turn memory

- 🌙 Full dark mode customization

## 🧑‍💻 Developed by

**Rohan Singh Azad**

Department of Computer Science & Engineering
(2021–2025)
