package test.endtoend.composer;

import java.io.IOException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import jdk.nashorn.internal.ir.annotations.Ignore;

public class ComposerEndToEndTest {

    private final ApplicationRunner application = new ApplicationRunner();
    private final FakeBotServer botServer = new FakeBotServer();
    private final String BOT_NAME = "atillman@chatty.ai";
    private final String COMPLETE_EVENT = "Event: Complete;";


    @Test @DisplayName("Testing Application Shows Errors in Translation") public void
    composerReportsFailedTranslationForInvalidConversationMessage() throws Exception {
        String invalidConversation = "an invalid sentence";
        application.startTranslating(invalidConversation);
        application.hasShownComposerTranslationHasFailed();
    }

    @Test @DisplayName("Testing Application Creates Bot Script and Deploys to Server") public void
    composerTranslatesSingleTopicAndSentenceandDeploys() throws IOException {
        String msg = "topic: introductions, t: Hello, talk to me!";

        botServer.start();

        application.startTranslating(msg);
        botServer.hasRecievedTranslatedScript(msg);

        application.showsConversationActive();

    }


    @AfterEach public void stopApplication() {
        application.stop();
    }


    @AfterEach public void stopServer() {
        botServer.stop();
    }
}
