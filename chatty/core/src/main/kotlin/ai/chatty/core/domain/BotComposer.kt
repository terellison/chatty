package ai.chatty.core.domain

import java.awt.SystemColor.text


class BotComposer (private val composerListener: ComposerListener, private val chatty: Chatty) : TranslationEventListener {

    override fun translationFailed() {
        composerListener.translationFailed()
    }

    override fun translationComplete(conversation : Conversation) {
        composerListener.translationComplete()
        chatty.uploadConversation(conversation)
    }
}