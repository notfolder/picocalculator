package picocalculator;

import java.math.BigDecimal;

import picocalculator.expressions.AbstractParser;
import picocalculator.expressions.AbstractParserFactory;
import picocalculator.expressions.VariableParserFactory;
import picocalculator.tokens.AbstractTokenFactory;
import picocalculator.tokens.BigDecimalTokenFactory;
import picocalculator.tokens.SimpleLexer;

public class VariableBigDecimalCalculatorFactory extends AbstractCalculatorFactory<BigDecimal> {
    /** 整数値用Lexer生成ファクトリ */
    private final AbstractTokenFactory<BigDecimal> _tokenFactory = new BigDecimalTokenFactory();

    @Override
    public Context<BigDecimal> createContext(String str) {
        return new SimpleLexer<BigDecimal>(str, _tokenFactory);
    }

    @Override
    public AbstractParser<BigDecimal> createParser() {
        // 四則演算のみParser生成
        AbstractParserFactory<BigDecimal> factory = new VariableParserFactory<BigDecimal>();
        return factory.createParser(0);
    }
}
