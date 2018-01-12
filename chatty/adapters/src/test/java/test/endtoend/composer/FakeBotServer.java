package test.endtoend.composer;

import java.io.IOException;

import org.jetbrains.annotations.NotNull;

import ai.chatty.adapters.chatscript.ChatScriptServer;
import ai.chatty.core.domain.BotServer;
import ai.chatty.core.domain.Chatty;
import ai.chatty.core.domain.Conversation;
import ai.chatty.core.domain.ScriptFormatter;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FakeBotServer {

    private Process nodeProcess;
    private Chatty botServer;
    private final String SERVER_URL = "https://localhost:8080";

    public void start() throws IOException {
       try {
           ProcessBuilder pb = new ProcessBuilder("./start_server.sh");
           nodeProcess = pb.start();

           ScriptFormatter nullFormatter = new ScriptFormatter() {
               @NotNull public String format(final Conversation conversation) {
                   return "";
               }
           };

           botServer = new ChatScriptServer(SERVER_URL, nullFormatter);
       } catch( Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void stop() {
        if (nodeProcess != null)
            nodeProcess.destroy();
    }

    public void hasRecievedTranslatedScript(String botId, String originalText) {
        Conversation conversation = botServer.getConversation(botId);
        assertEquals(conversation.toString(), originalText);
    }
}
