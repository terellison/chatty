package test.unit;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import ai.chatty.core.domain.BotComposer;
import ai.chatty.core.domain.Chatty;
import ai.chatty.core.domain.ComposerListener;
import ai.chatty.core.domain.Conversation;
//import ai.chatty.core.domain.Conversation;

public class BotComposerTests {

    private final Mockery context = new Mockery();
    private final Chatty chatty = context.mock(Chatty.class);
    private final ComposerListener listener = context.mock(ComposerListener.class);
    private final BotComposer composer = new BotComposer(listener, chatty);

    @Test @DisplayName("Testing BotComposer Notifies Listener that Translation Failed") public void
    notifiesComposerListenerTranslationFailedForInvalidText() {
        context.checking(new Expectations() {{
            oneOf(listener).translationFailed();
        }});

        composer.translationFailed();

        context.assertIsSatisfied();
    }


    @Test @DisplayName("Testing BotComposer Notifies Listener that Translation Complete") public void
    notifiesComposerListenerTranslationCompleted() {
        context.checking(new Expectations() {{
            oneOf(listener).translationComplete();
            atLeast(1).of(chatty).uploadConversation(with(any(Conversation.class)));
        }});

        composer.translationComplete(
                new Conversation("introductions", "Hello, talk to me."));

        context.assertIsSatisfied();
    }

    /*
    @Test @DisplayName("Testing BotComposer Reports Sends Translation") public void
    sendsChattyTranslation() {
        String topic = "introductions";
        String gambit = "Hello, talk to me!";
        String msg = "Event: New Conversation; Topic: introductions; Gambit: Hello, talk to me!;";

        Conversation convo = new Conversation(topic, gambit);

        context.checking(new Expectations() {{
            oneOf(chatty).uploadConversation(convo);
            atLeast(1).of(listener).translationComplete();
        }});

        composer.translationComplete(convo);

        context.assertIsSatisfied();
    }*/
}
