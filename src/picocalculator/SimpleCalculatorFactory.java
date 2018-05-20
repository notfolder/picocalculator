package picocalculator;

import picocalculator.expressions.AbstractParser;
import picocalculator.expressions.AbstractParserFactory;
import picocalculator.expressions.SimpleLexer;
import picocalculator.expressions.SimpleParserFactory;
import picocalculator.tokens.AbstractTokenFactory;
import picocalculator.tokens.IntegerTokenFactory;

public class SimpleCalculatorFactory extends AbstractCalculatorFactory<Integer> {
    private final AbstractTokenFactory<Integer> _tokenFactory = new IntegerTokenFactory();

    @Override
    public Context<Integer> createContext(String str) {
        return new SimpleLexer<Integer>(str, _tokenFactory);
    }

    @Override
    public AbstractParser<Integer> createParser() {
        AbstractParserFactory<Integer> factory = new SimpleParserFactory<Integer>();
        return factory.createParser(0);
    }

}
