package ai.chatty.core.domain

interface ScriptFormatter {
    fun format(conversation: Conversation) : String
}