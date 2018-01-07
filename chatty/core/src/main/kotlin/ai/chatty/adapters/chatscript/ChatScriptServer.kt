package ai.chatty.adapters.chatscript

import ai.chatty.core.domain.Chatty
import ai.chatty.core.domain.Conversation
import java.net.HttpURLConnection
import java.net.URL

class ChatScriptServer(private val url: String) : Chatty {
    override fun uploadConversation(conversation: Conversation) {}

    override fun isAvailable(): Boolean {
        try {
            val siteURL = URL(url)
            val connection = siteURL
                    .openConnection() as HttpURLConnection
            connection.requestMethod = "GET"
            connection.connect()

            val code = connection.responseCode
            if (code == 200) {
                return true
            }
        } catch (e: Exception) {
            return false
        }
        return false
    }
}