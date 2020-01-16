package ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.swing.*;

@Component
public class CalculatorFrame extends JFrame {

    private MainPanel mainPanel;

    @Autowired
    public CalculatorFrame(MainPanel mainPanel) {
        super("Calculator");
        this.mainPanel = mainPanel;
        initFrame();
    }

    private void initFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        getContentPane().add(this.mainPanel);
        pack();
        setLocationRelativeTo(null);
        mainPanel.focusButtonsPanel();
    }
}
