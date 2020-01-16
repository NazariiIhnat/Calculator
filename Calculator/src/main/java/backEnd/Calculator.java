package backEnd;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class Calculator {
    private static final ScriptEngineManager mgr = new ScriptEngineManager();
    private static final ScriptEngine engine = mgr.getEngineByName("JavaScript");

    public Object calculate(String stringExpressionToCalculate) throws ScriptException {
        double result = 0;
        result = Double.valueOf(String.valueOf(engine.eval(stringExpressionToCalculate)));
        return checkIfDoubleOrReturnInt(result);
    }

    private Object checkIfDoubleOrReturnInt(double value) {
        if (isDouble(value))
            return value;
        return (int) value;
    }

    private boolean isDouble(double val) {
        return Math.floor(val) != val;
    }
}






