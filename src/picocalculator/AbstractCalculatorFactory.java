package picocalculator;

import picocalculator.expressions.AbstractParser;

public abstract class AbstractCalculatorFactory<T> {
    public abstract Context<T> createContext(String str);
    public abstract AbstractParser<T> createParser();
}
