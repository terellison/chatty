package ai.chatty.adapters.chatscript

import ai.chatty.core.domain.Chatty
import ai.chatty.core.domain.Conversation
import ai.chatty.core.domain.ConversationAdapter
import ai.chatty.core.domain.ScriptFormatter

import java.net.HttpURLConnection
import java.net.URL
import java.io.IOException
import java.net.MalformedURLException
import java.io.InputStreamReader
import java.io.BufferedReader



class ChatScriptServer(private val url: String, private val formatter: ScriptFormatter) : Chatty {
    override fun uploadConversation(conversation: Conversation) {
        val script = formatter.format(conversation)
    }

    override fun getConversation(botId: String) : Conversation? {
        var conversation: Conversation
        var output: String = ""

        try {

            val url = URL(url + "/json/conversation/get/" + botId)
            val conn = url.openConnection() as HttpURLConnection
            conn.requestMethod = "GET"
            conn.setRequestProperty("Accept", "application/json")

            if (conn.responseCode != 200) {
                throw RuntimeException("Failed : HTTP error code : " + conn.responseCode)
            }

            val br = BufferedReader(InputStreamReader(
                    conn.inputStream))

            var input = br.readLine()
            while (input != null) {
                output += input
            }

            conn.disconnect()

        } catch (e: MalformedURLException) {

            e.printStackTrace()

        } catch (e: IOException) {

            e.printStackTrace()

        }

        return ConversationAdapter.fromString(output)
    }

    override fun isAvailable(): Boolean {
        try {
            val siteURL = URL(url)
            val connection = siteURL
                    .openConnection() as HttpURLConnection
            connection.requestMethod = "GET"
            connection.connect()

            val code = connection.responseCode
            if (code == 200) {
                return true
            }
        } catch (e: Exception) {
            return false
        }
        return false
    }
}