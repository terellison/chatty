package ai.chatty.adapters.presentation.swing;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;

public class MainWindow extends JFrame{

    public static final String MAIN_WINDOW_NAME = "Chatty Bot Composer";
    public static final String COMPOSER_STATUS_NAME = "composer status";
    private final JLabel composerStatus = createLabel(Main.STATUS_NOT_ONLINE);

    public MainWindow() {
        super("Chatty Composer");
        setName(MAIN_WINDOW_NAME);
        add(composerStatus);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void showStatus(String status) {
        composerStatus.setText(status);
    }

    private static JLabel createLabel(String initialText) {
        JLabel result = new JLabel(initialText);
        result.setName(COMPOSER_STATUS_NAME);
        result.setBorder(new LineBorder(Color.BLACK));
        return result;
    }


}
