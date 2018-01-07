package ai.chatty.core.domain

interface ComposerListener {
    fun translationFailed()
    fun translationComplete()
}