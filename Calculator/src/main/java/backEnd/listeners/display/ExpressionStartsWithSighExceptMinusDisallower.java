package backEnd.listeners.display;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ui.Display;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

@Component
public class ExpressionStartsWithSighExceptMinusDisallower implements DocumentListener {

    private Display display;

    @Autowired
    public ExpressionStartsWithSighExceptMinusDisallower(Display display) {
        this.display = display;
        display.getDocument().addDocumentListener(this);
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        disallow();
    }

    @Override
    public void removeUpdate(DocumentEvent e) {

    }

    @Override
    public void changedUpdate(DocumentEvent e) {

    }

    private void disallow() {
        Runnable runDisallowing = new Runnable() {
            @Override
            public void run() {
                if(display.getText().length() == 1 && isNotMinusFirstChar())
                    display.setText("");
            }
        };
        SwingUtilities.invokeLater(runDisallowing);
    }

    private boolean isNotMinusFirstChar() {
        return areSignsExceptMinus(display.getText().charAt(0));
    }

    private boolean areSignsExceptMinus(char sign){
        String signsExceptMinus = "/*+.";
        return signsExceptMinus.contains(String.valueOf(sign));
    }
}
