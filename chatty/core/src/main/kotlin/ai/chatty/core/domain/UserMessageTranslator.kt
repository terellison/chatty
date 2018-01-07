package ai.chatty.core.domain

import com.squareup.moshi.JsonEncodingException
import com.squareup.moshi.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi

class UserMessageTranslator (private val eventListener: TranslationEventListener) {


    fun translate(msg : String) {

        val translation: Conversation? =
                try { unpackEventFrom(msg) } catch (e : JsonEncodingException) { null }

        if (translation == null){
            eventListener.translationFailed()
        } else
            eventListener.translationComplete(translation)

    }


    private fun unpackEventFrom(msg: String) : Conversation? {
        val moshi = Moshi.Builder()
                .add(KotlinJsonAdapterFactory())
                .build()

        val conversationAdapter = moshi.adapter(Conversation::class.java)
        return conversationAdapter.fromJson(msg)
    }
}

