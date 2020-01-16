package backEnd.listeners.display;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ui.Display;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

@Component
public class SignsSwapper implements DocumentListener {
    private Display display;

    @Autowired
    public SignsSwapper(Display display) {
        this.display = display;
        display.getDocument().addDocumentListener(this);
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        swapLastCharIfSign();
    }

    @Override
    public void removeUpdate(DocumentEvent e) {

    }

    @Override
    public void changedUpdate(DocumentEvent e) {

    }

    private void swapLastCharIfSign(){
        Runnable doSwap = new Runnable() {
            @Override
            public void run() {
                if(areSignsLastTwoCharacters()) {
                    display.setText(getExpressionWithDeletedSecondLastChar());
                }
            }
        };
        SwingUtilities.invokeLater(doSwap);
    }

    private boolean areSignsLastTwoCharacters() {
        String signs = "/*-+.";
        if(display.getText().length() > 1) {
            String firstChar = Character.toString(getLastTwoCharacters().charAt(0));
            String secondChar = Character.toString(getLastTwoCharacters().charAt(1));
            return signs.contains(firstChar) & signs.contains(secondChar);
        }
        return false;
    }

    private String getLastTwoCharacters() {
        String text = display.getText();
        return text.substring(text.length() - 2);
    }

    private String getExpressionWithDeletedSecondLastChar() {
        StringBuilder stringBuilder = new StringBuilder(display.getText());
        return String.valueOf(stringBuilder.deleteCharAt(display.getText().length()-2));
    }
}
