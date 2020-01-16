package backEnd.listeners.buttons;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ui.Buttons;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.Map;

@Component
public class ButtonsKeyBinder {
    private Buttons buttons;

    @Autowired
    public ButtonsKeyBinder(Buttons buttons) {
        this.buttons = buttons;
        bindButtons();
    }

    private void bindButtons() {
        for(Map.Entry<String, JButton> entry : buttons.getButtonsMap().entrySet()){
            JButton button = entry.getValue();
            chooseButtonsKeyEvents(button);
        }
    }

    private void chooseButtonsKeyEvents(JButton btn){
        switch (btn.getText()){
            case "(":
                bindButtonsKey(btn, KeyEvent.VK_9, InputEvent.SHIFT_DOWN_MASK);
                bindButtonsKey(btn, KeyEvent.VK_DIVIDE, InputEvent.SHIFT_DOWN_MASK); break;

            case ")":
                bindButtonsKey(btn, KeyEvent.VK_0, InputEvent.SHIFT_DOWN_MASK);
                bindButtonsKey(btn, KeyEvent.VK_MULTIPLY, InputEvent.SHIFT_DOWN_MASK); break;

            case "<":
                bindButtonsKey(btn, KeyEvent.VK_BACK_SPACE, 0); break;

            case "C":
                bindButtonsKey(btn, KeyEvent.VK_C, 0); break;

            case "0":
                bindButtonsKey(btn, KeyEvent.VK_0, 0);
                bindButtonsKey(btn, KeyEvent.VK_NUMPAD0, 0); break;

            case "1":
                bindButtonsKey(btn, KeyEvent.VK_1, 0);
                bindButtonsKey(btn, KeyEvent.VK_NUMPAD1, 0); break;

            case "2":
                bindButtonsKey(btn, KeyEvent.VK_2, 0);
                bindButtonsKey(btn, KeyEvent.VK_NUMPAD2, 0); break;

            case "3":
                bindButtonsKey(btn, KeyEvent.VK_3, 0);
                bindButtonsKey(btn, KeyEvent.VK_NUMPAD3, 0); break;

            case "4":
                bindButtonsKey(btn, KeyEvent.VK_4, 0);
                bindButtonsKey(btn, KeyEvent.VK_NUMPAD4, 0); break;

            case "5":
                bindButtonsKey(btn, KeyEvent.VK_5, 0);
                bindButtonsKey(btn, KeyEvent.VK_NUMPAD5, 0); break;

            case "6":
                bindButtonsKey(btn, KeyEvent.VK_6, 0);
                bindButtonsKey(btn, KeyEvent.VK_NUMPAD6, 0); break;

            case "7":
                bindButtonsKey(btn, KeyEvent.VK_7, 0);
                bindButtonsKey(btn, KeyEvent.VK_NUMPAD7, 0); break;

            case "8":
                bindButtonsKey(btn, KeyEvent.VK_8, 0);
                bindButtonsKey(btn, KeyEvent.VK_NUMPAD8, 0); break;

            case "9":
                bindButtonsKey(btn, KeyEvent.VK_9, 0);
                bindButtonsKey(btn, KeyEvent.VK_NUMPAD9, 0); break;

            case "/":
                bindButtonsKey(btn, KeyEvent.VK_DIVIDE, 0);
                bindButtonsKey(btn, KeyEvent.VK_SLASH, InputEvent.SHIFT_DOWN_MASK); break;

            case "*":
                bindButtonsKey(btn, KeyEvent.VK_MULTIPLY, 0);
                bindButtonsKey(btn, KeyEvent.VK_8, InputEvent.SHIFT_DOWN_MASK); break;

            case "-":
                bindButtonsKey(btn, KeyEvent.VK_SUBTRACT, 0);
                bindButtonsKey(btn, KeyEvent.VK_MINUS, 0); break;

            case "+":
                bindButtonsKey(btn, KeyEvent.VK_ADD, 0);
                bindButtonsKey(btn, KeyEvent.VK_EQUALS, InputEvent.SHIFT_DOWN_MASK); break;

            case "=":
                bindButtonsKey(btn, KeyEvent.VK_EQUALS, 0);
                bindButtonsKey(btn, KeyEvent.VK_ENTER, 0); break;

            case ".":
                bindButtonsKey(btn, KeyEvent.VK_DECIMAL, 0);
                bindButtonsKey(btn, KeyEvent.VK_PERIOD, 0); break;
        }
    }

    private void bindButtonsKey (JButton btn, int keyCode, int keyModifier) {
        KeyStroke keyStroke = KeyStroke.getKeyStroke(keyCode, keyModifier);
        btn.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(keyStroke, btn.getText());
        btn.getActionMap().put(btn.getText(), clickButtonByText(btn.getText()));
    }

    private AbstractAction clickButtonByText(final String btnTxt) {
        return new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttons.getButtonByText(btnTxt).doClick();
            }
        };
    }
}
