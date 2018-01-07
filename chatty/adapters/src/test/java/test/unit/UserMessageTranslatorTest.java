package test.unit;



import com.squareup.moshi.Json;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import ai.chatty.core.domain.Conversation;
import ai.chatty.core.domain.TranslationEventListener;
import ai.chatty.core.domain.UserMessageTranslator;

public class UserMessageTranslatorTest {
    private final Mockery context = new Mockery();
    private final TranslationEventListener listener = context.mock(TranslationEventListener.class);
    private final UserMessageTranslator translator = new UserMessageTranslator(listener);
    private JSONObject simpleConvo;

    @Before public void createSimpleConvo() throws JSONException{
        simpleConvo = new JSONObject();
        simpleConvo.put("topic", "introductions");
        simpleConvo.put("gambit", "Hello, talk to me!");

    }

    @Test public void
    notifiesTranslationEventListenerTranslationFailedForInvalidText() {
        context.checking(new Expectations() {{
            oneOf(listener).translationFailed();
        }});

        String invalidText = "Invalid Text";
        translator.translate(invalidText);

        context.assertIsSatisfied();
    }

    @Test public void
    notifiesTransalationEventListenerWhenTranslationComplete() throws JSONException {
        context.checking(new Expectations() {{
            oneOf(listener).translationComplete(with(any(Conversation.class)));
        }});


        translator.translate(simpleConvo.toString());

        context.assertIsSatisfied();
    }

    @Test public void
    translatesSimpleConversation() throws JSONException {
        context.checking(new Expectations() {{
            oneOf(listener)
                    .translationComplete(
                            new Conversation("introductions", "Hello, talk to me!"));
        }});

        translator.translate(simpleConvo.toString());

        context.assertIsSatisfied();
    }
}
