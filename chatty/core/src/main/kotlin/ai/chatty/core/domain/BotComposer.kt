package ai.chatty.core.domain


class BotComposer (private val composerListener: ComposerListener, private val chatty: Chatty) : TranslationEventListener {

    override fun translationFailed() {
        composerListener.translationFailed()
    }

    override fun translationComplete(conversation : Conversation) {
        chatty.uploadConversation(conversation)
        composerListener.translationComplete()
    }
}