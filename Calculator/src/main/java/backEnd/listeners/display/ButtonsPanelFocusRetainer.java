package backEnd.listeners.display;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ui.Display;
import ui.MainPanel;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

@Component
public class ButtonsPanelFocusRetainer implements FocusListener {
    private MainPanel mainPanel;

    @Autowired
    public ButtonsPanelFocusRetainer(MainPanel mainPanel, Display display) {
        this.mainPanel = mainPanel;
        display.addFocusListener(this);
    }

    @Override
    public void focusGained(FocusEvent e) {
        mainPanel.focusButtonsPanel();
    }

    @Override
    public void focusLost(FocusEvent e) {

    }
}
