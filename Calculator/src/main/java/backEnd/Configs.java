package backEnd;

import ui.Buttons;
import ui.Display;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScans({@ComponentScan("ui"), @ComponentScan("backEnd")})
public class Configs {

    @Bean
    public Display getDisplay() {
        return new Display();
    }

    @Bean
    public Buttons getButtons() {
        return new Buttons();
    }

    @Bean
    public Calculator getCalculator() {
        return new Calculator();
    }
}
