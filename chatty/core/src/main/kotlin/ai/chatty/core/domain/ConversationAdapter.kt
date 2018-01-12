package ai.chatty.core.domain

import com.squareup.moshi.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi

class ConversationAdapter {
    companion object {
        fun fromString(txt: String): Conversation? {
            val moshi = Moshi.Builder()
                    .add(KotlinJsonAdapterFactory())
                    .build()

            val conversationAdapter = moshi.adapter(Conversation::class.java)
            return conversationAdapter.fromJson(txt)
        }
    }
}