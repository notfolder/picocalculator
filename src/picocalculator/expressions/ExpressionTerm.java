package picocalculator.expressions;

import picocalculator.Context;
import picocalculator.exceptions.ParsingErrorException;
import picocalculator.tokens.AbstractTermToken;
import picocalculator.tokens.AbstractToken;

/**
 * BNF構文における<tem>レベルの構文解析を行うParser
 *
 * @author notfolder
 *
 * @param <T> 計算を行う際の型
 */
public class ExpressionTerm<T> extends AbstractParser<T> {
    /** 左辺値を解析するためのParser */
    private final AbstractParser<T> _left;
    /** 右辺値を解析するためのParser */
    private final AbstractParser<T> _right;

    /**
     * コンストラクタ
     *
     * @param level 自クラスのBNF構文レベル
     * @param factory 下位のparserを生成するためのFactory
     */
    public ExpressionTerm(int level, AbstractParserFactory<T> factory) {
        super(level, factory);
        _left = _factory.createParser(getLevel()+1);
        _right = _factory.createParser(getLevel()+1);
    }

    @Override
    public T interpret(Context<T> context) throws ParsingErrorException {
        T ret = _left.interpret(context);
        while (context.hasNext()) {
            AbstractToken<T> token = context.next();
            // 次のtokenが*か/でなかったらtokenを戻して値を返す
            if (!(token instanceof AbstractTermToken)) {
                context.pushToken(token);
                return ret;
            }
            T rightValue = _right.interpret(context);
            ret = token.evalute(ret, rightValue);
            context.addTokenList(token);
        }
        return ret;
    }

}
