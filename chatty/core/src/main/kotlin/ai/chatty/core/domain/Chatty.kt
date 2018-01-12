package ai.chatty.core.domain

interface Chatty {
    fun isAvailable() : Boolean
    fun uploadConversation(conversation : Conversation)
    fun getConversation(botId: String): Conversation?
}