package ai.chatty.adapters.presentation.swing;

import java.io.IOException;
import javax.swing.SwingUtilities;

import ai.chatty.adapters.chatscript.ChatScriptServer;
import ai.chatty.core.domain.BotComposer;
import ai.chatty.core.domain.BotServer;
import ai.chatty.core.domain.Chatty;
import ai.chatty.core.domain.ComposerListener;
import ai.chatty.core.domain.UserMessageTranslator;

public class Main implements ComposerListener{
    private static final int ARG_HOSTNAME   = 0;
    private static final int ARG_PORT       = 1;
    private static final int ARG_USERNAME   = 2;
    private static final int ARG_PASSWORD   = 3;
    private static final int ARG_TEXT       = 4;

    public static final String STATUS_ONLINE = "Connected";
    public static final String STATUS_NOT_ONLINE = "Disconnected";
    public static final String STATUS_CONVERSATION_ACTIVE = "Active";
    public static final String STATUS_TRANSLATION_FAILED = "Invalid Conversation";

    public static final String DB_PATH = "~/Dev/chatty/chatty/db/composer.db";

    private MainWindow ui;
    private Chatty chattyServer;


    public Main(String hostName, String port, String txt) throws Exception {
        startUserInterface();
        if (isServerAvailable("http://" + hostName + ":" + port))
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    ui.showStatus(Main.STATUS_ONLINE);
                }
            });
        translate(txt);
    }

    public static void main( String... args ) throws Exception {
        Main main = new Main(args[ARG_HOSTNAME],
                             args[ARG_PORT],
                             args[ARG_TEXT]);

    }


    public void translationFailed() {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                ui.showStatus(Main.STATUS_TRANSLATION_FAILED);
            }
        });
    }

    public void translationComplete() {

    }

    private void startUserInterface() throws Exception {
        SwingUtilities.invokeAndWait(new Runnable() {
            public void run() {
                ui = new MainWindow();
            }
        });
    }

    private boolean isServerAvailable(String url) throws IOException {
        chattyServer = new ChatScriptServer(url);
        return chattyServer.isAvailable();
    }

    private void translate(final String txt) {
        BotComposer composer = new BotComposer(this, chattyServer);
        UserMessageTranslator translator = new UserMessageTranslator(composer);
        translator.translate(txt);
    }

}
