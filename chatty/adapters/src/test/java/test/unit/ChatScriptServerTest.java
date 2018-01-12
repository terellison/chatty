package test.unit;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import ai.chatty.adapters.chatscript.ChatScriptServer;
import ai.chatty.core.domain.Chatty;
import ai.chatty.core.domain.Conversation;
import ai.chatty.core.domain.ScriptFormatter;

public class ChatScriptServerTest {
    private final Mockery context = new Mockery();
    private final ScriptFormatter formatter = context.mock(ScriptFormatter.class);
    private final Chatty server = new ChatScriptServer("localhost:8080", formatter);

    @Test @DisplayName("Test Translation to ChatScript Format Occurs") public void
    asksConvoToFormatAsChatScript() {
        final Conversation convo = new Conversation("introductions", "Hello, talk to me!");

        context.checking(new Expectations() {{
            oneOf(formatter).format(with(any(Conversation.class)));
        }});

        server.uploadConversation(convo);

        context.assertIsSatisfied();
    }

}
