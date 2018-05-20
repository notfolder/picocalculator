package picocalculator;

import picocalculator.expressions.AbstractParser;
import picocalculator.expressions.AbstractParserFactory;
import picocalculator.expressions.SimpleParserFactory;
import picocalculator.tokens.AbstractTokenFactory;
import picocalculator.tokens.IntegerTokenFactory;
import picocalculator.tokens.SimpleLexer;

/**
 * 問題3の仕様にしたがい、整数、四則演算のみの計算機用ParserとLexerを生成するファクトリ
 *
 * @author notfolder
 */
public class SimpleCalculatorFactory extends AbstractCalculatorFactory<Integer> {
    /** 整数値用Lexer生成ファクトリ */
    private final AbstractTokenFactory<Integer> _tokenFactory = new IntegerTokenFactory();

    @Override
    public Context<Integer> createContext(String str) {
        return new SimpleLexer<Integer>(str, _tokenFactory);
    }

    @Override
    public AbstractParser<Integer> createParser() {
        // 四則演算のみParser生成
        AbstractParserFactory<Integer> factory = new SimpleParserFactory<Integer>();
        return factory.createParser(0);
    }

}
