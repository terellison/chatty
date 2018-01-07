package test.endtoend.composer;

import ai.chatty.adapters.presentation.swing.Main;

public class ApplicationRunner {

    private BotComposerDriver driver;
    private static final String BOT_SERVER_HOSTNAME = "localhost";
    private static final String BOT_SERVER_PORT = "8888";
    private static final String USER_ID = "tillman.adrian@spcollege.edu";
    private static final String USER_PWD = "Summer2017$$";

    public void startTranslating(final String msgToTranslate){
        Thread thread = new Thread("Test Application") {
            @Override public void run() {
                try {
                    Main.main(BOT_SERVER_HOSTNAME, BOT_SERVER_PORT, USER_ID, USER_PWD, msgToTranslate);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        thread.setDaemon(true);
        thread.start();
        driver = new BotComposerDriver(1000);
    }

    public void showsServerNotOnline () {
        driver.showsComposerStatus(Main.STATUS_NOT_ONLINE);
    }


    public void showsServerOnline() {
        driver.showsComposerStatus(Main.STATUS_ONLINE);
    }


    public void showsConversationActive() {
        driver.showsConversationStatus(Main.STATUS_CONVERSATION_ACTIVE);
    }

    public void hasShownComposerTranslationHasFailed() {
        driver.showsComposerStatus(Main.STATUS_TRANSLATION_FAILED);
    }

    public void stop() {
        if (driver != null) {
            driver.dispose();
        }
    }

}
