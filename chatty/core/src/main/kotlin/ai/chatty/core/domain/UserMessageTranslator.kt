package ai.chatty.core.domain

import com.squareup.moshi.JsonEncodingException
import com.squareup.moshi.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi

class UserMessageTranslator (private val eventListener: TranslationEventListener) {


    fun translate(msg : String) {
        var translation : Conversation? = null

        try {
            translation = ConversationAdapter.fromString(msg)
        } catch (e : JsonEncodingException) { null }

        if (translation == null){
            eventListener.translationFailed()
        } else
            eventListener.translationComplete(translation)

    }

}

