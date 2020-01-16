package ui;

import javax.swing.*;
import java.awt.*;


public class Display extends JTextField {

    public Display() {
        super(10);
        setFont(Buttons.BTN_FONT.deriveFont(Font.PLAIN));
        setText("0");
        setEditable(false);
    }

    public void setText(String s){
        super.setText(s);
    }
}
