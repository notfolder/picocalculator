package picocalculator.expressions;

import picocalculator.Context;
import picocalculator.exceptions.ParsingErrorException;
import picocalculator.tokens.AbstractExpressionToken;
import picocalculator.tokens.AbstractToken;

/**
 * BNF構文における<expression>レベルの構文解析を行うParser
 *
 * @author notfolder
 *
 * @param <T> 計算を行う際の型
 */
public class Expression<T> extends AbstractParser<T> {
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
    public Expression(int level, AbstractParserFactory<T> factory) {
        super(level, factory);
        _left = _factory.createParser(getLevel()+1);
        _right = _factory.createParser(getLevel()+1);
    }

    @Override
    public T interpret(Context<T> context) throws ParsingErrorException {
        T leftValue = _left.interpret(context);
        while (context.hasNext()) {
            AbstractToken<T> token = context.next();
            // 次のtokenが+か-でなかったらtokenを戻して値を返す
            if (!(token instanceof AbstractExpressionToken)) {
                context.pushToken(token);
                return leftValue;
            }
            T rightValue = _right.interpret(context);
            leftValue = token.evalute(leftValue, rightValue);
            context.addTokenList(token);
        }
        return leftValue;
    }

}
