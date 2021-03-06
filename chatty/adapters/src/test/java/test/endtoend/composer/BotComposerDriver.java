package test.endtoend.composer;

import com.objogate.wl.swing.AWTEventQueueProber;
import com.objogate.wl.swing.driver.JComboBoxDriver;
import com.objogate.wl.swing.driver.JFrameDriver;
import com.objogate.wl.swing.driver.JLabelDriver;
import com.objogate.wl.swing.driver.JTableDriver;
import com.objogate.wl.swing.gesture.GesturePerformer;

import ai.chatty.adapters.presentation.swing.MainWindow;
import static com.objogate.wl.swing.matcher.JLabelTextMatcher.withLabelText;
import static org.hamcrest.core.IsEqual.equalTo;

public class BotComposerDriver extends JFrameDriver{

    public BotComposerDriver(int timeoutMillis){
        super(new GesturePerformer(),
                JFrameDriver.topLevelFrame(
                        named(MainWindow.MAIN_WINDOW_NAME),
                        showingOnScreen()),
                        new AWTEventQueueProber(timeoutMillis, 100));
    }

    public void showsComposerStatus(String statusText) {
        new JLabelDriver(
                this, named(MainWindow.COMPOSER_STATUS_NAME)).hasText(equalTo(statusText));

    }

    public void showsConversationStatus(String statusText) {
        new JTableDriver(this).hasCell(withLabelText(equalTo(statusText)));
    }
}
