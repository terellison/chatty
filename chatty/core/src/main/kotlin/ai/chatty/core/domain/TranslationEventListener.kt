package ai.chatty.core.domain

interface TranslationEventListener {
    fun translationFailed()
    fun translationComplete(translation : Conversation)
}