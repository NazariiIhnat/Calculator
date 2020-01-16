import backEnd.Configs;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ui.CalculatorFrame;

public class Main {
    public static void main(String[] args){
        ApplicationContext context = new AnnotationConfigApplicationContext(Configs.class);
        CalculatorFrame frame = context.getBean(CalculatorFrame.class);
    }
}
