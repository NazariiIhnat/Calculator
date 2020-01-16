package ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.swing.*;
import java.awt.*;
import java.util.Map;

@Component
public class MainPanel extends JPanel {

    private Display display;
    private Buttons buttons;
    private static JPanel buttonsPanel = new JPanel(new GridLayout(5, 4));
    @Autowired
    public MainPanel(Display display, Buttons buttons) {
        super(new BorderLayout());
        this.display = display;
        this.buttons = buttons;
        initButtonsPanel();
        initMainPanel();
    }

    private void initMainPanel() {
        add(display, BorderLayout.PAGE_START);
        add(buttonsPanel, BorderLayout.CENTER);
        focusButtonsPanel();
    }

    private void initButtonsPanel() {
        for(Map.Entry<String, JButton> entry : buttons.getButtonsMap().entrySet()) {
            JButton button = entry.getValue();
            buttonsPanel.add(button);
        }
    }

    public void focusButtonsPanel() {
        buttonsPanel.requestFocus(true);
    }
}
