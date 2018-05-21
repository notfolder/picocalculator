package picocalculator;

import picocalculator.expressions.AbstractParser;
import picocalculator.expressions.AbstractParserFactory;
import picocalculator.expressions.SimpleParserFactory;
import picocalculator.tokens.AbstractTokenFactory;
import picocalculator.tokens.DoubleTokenFactory;
import picocalculator.tokens.SimpleLexer;

public class DoubleCalculatorFactory extends AbstractCalculatorFactory<Double> {
    /** 整数値用Lexer生成ファクトリ */
    private final AbstractTokenFactory<Double> _tokenFactory = new DoubleTokenFactory();

    @Override
    public Context<Double> createContext(String str) {
        return new SimpleLexer<Double>(str, _tokenFactory);
    }

    @Override
    public AbstractParser<Double> createParser() {
        // 四則演算のみParser生成
        AbstractParserFactory<Double> factory = new SimpleParserFactory<Double>();
        return factory.createParser(0);
    }
}
