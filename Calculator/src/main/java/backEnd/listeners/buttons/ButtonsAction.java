package backEnd.listeners.buttons;

import backEnd.Calculator;
import ui.Buttons;
import ui.Display;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.script.ScriptException;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;


@Component
@Resource(name = "ui/Buttons")
public class ButtonsAction {

    private Display display;
    private Buttons buttons;
    private Calculator calculator;
    private boolean startNewExpression = true;
    private boolean isError = false;

    @Autowired
    public ButtonsAction(Display display, Buttons buttons, Calculator calculator) {
        this.display = display;
        this.buttons = buttons;
        this.calculator = calculator;
        initButtonsActionListener();
    }

    private void initButtonsActionListener() {
        for(Map.Entry<String, JButton> entry : buttons.getButtonsMap().entrySet()){
            JButton button = entry.getValue();
            button.addActionListener(chooseActionListener(button.getText()));
        }
    }

    private ActionListener chooseActionListener(String buttonText){
        switch (buttonText) {
            case "<": return backSpaceButtonActionListener();
            case "C": return CButtonActionListener();
            case "=": return equalsButtonActionListener();
            default: return allButtonsExceptBackSpaceEqualsAndCActionListener(buttonText);
        }
    }

    private ActionListener equalsButtonActionListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String expressionBeforeCalculation = display.getText();
                try{
                    display.setText(String.valueOf(calculator.calculate(display.getText())));
                } catch (ScriptException | NumberFormatException ex) {
                    display.setText("Error");
                    isError = true;
                }
                startNewExpression = !display.getText().equals(expressionBeforeCalculation);
            }
        };
    }

    private ActionListener backSpaceButtonActionListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isError) {
                    display.setText("");
                    isError = false;
                }
                if(isWrittenSomething()) {
                    display.setText(display.getText().substring(0, display.getText().length() - 1));
                    startNewExpression = false;
                }
            }
        };
    }

    private boolean isWrittenSomething() {
        return display.getText().length() != 0;
    }

    private ActionListener CButtonActionListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                display.setText("0");
                startNewExpression = true;
            }
        };
    }

    private ActionListener allButtonsExceptBackSpaceEqualsAndCActionListener(final String name) {
        return new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(display.getText().equals("0") & !areSigns(name)) {
                    display.setText(name);
                    startNewExpression = false;
                    isError = false;
                }
                else if(startNewExpression & !areSigns(name)) {
                    display.setText(name);
                    startNewExpression = false;
                    isError = false;
                }
                else if(isError){
                    display.setText(name);
                    isError = false;
                    startNewExpression = false;
                }
                else {
                    display.setText(display.getText() + name);
                    isError = false;
                    startNewExpression = false;
                }
            }
        };
    }

    private boolean areSigns(String ch){
        String signs = "/*-+.";
        return signs.contains(ch);
    }
}