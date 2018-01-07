package ai.chatty.core.domain

interface Chatty {
    fun isAvailable() : Boolean
    fun uploadConversation(conversation : Conversation)
}