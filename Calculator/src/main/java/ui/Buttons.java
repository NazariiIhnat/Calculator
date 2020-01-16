package ui;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedHashMap;
import java.util.Map;

public class Buttons {
    private static final String [][] buttonTexts = new String [][]{
            {"(", ")", "<", "C"},
            {"7", "8", "9", "+"},
            {"4", "5", "6", "-"},
            {"1", "2", "3", "*"},
            {"0", ".", "/", "="}
    };
    static final Font BTN_FONT = new Font(Font.SANS_SERIF, Font.BOLD, 24);
    private static Map<String, JButton> buttonsMap = new LinkedHashMap<>();

    public Buttons() {
        createAndSetTextToEachButton();
        setButtonsFont();
    }

    private void createAndSetTextToEachButton() {
        for (String[] buttonText : buttonTexts) {
            for (String s : buttonText) {
                buttonsMap.put(s, new JButton(s));
            }
        }
    }

    private void setButtonsFont() {
        for(Map.Entry<String, JButton> entry : buttonsMap.entrySet()) {
            JButton button = entry.getValue();
            button.setFont(BTN_FONT);
        }
    }

    public Map<String, JButton> getButtonsMap() {
        return buttonsMap;
    }

    public JButton getButtonByText(String text) {
        return buttonsMap.get(text);
    }
}
