package picocalculator.expressions;

import picocalculator.Context;
import picocalculator.exceptions.ParsingErrorException;
import picocalculator.tokens.AbstractToken;
import picocalculator.tokens.EqualToken;
import picocalculator.tokens.VariableToken;

public class ExpressionStatement<T> extends AbstractParser<T> {
    /** 右辺値を解析するためのParser */
    private final AbstractParser<T> _right;

    /**
     * コンストラクタ
     *
     * @param level 自クラスのBNF構文レベル
     * @param factory 下位のparserを生成するためのFactory
     */
    public ExpressionStatement(int level, AbstractParserFactory<T> factory) {
        super(level, factory);
        _right = _factory.createParser(getLevel()+1);
    }

    @Override
    public T interpret(Context<T> context) throws ParsingErrorException {
        if (!context.hasNext()) {
            throw new ParsingErrorException("数式がありません", context.getIndex());
        }
        AbstractToken<T> token = context.next();
        // 最初のtokenが変数でなければ数式になる
        if (!(token instanceof VariableToken)) {
            context.pushToken(token);
            return _right.interpret(context);
        }
        if (!context.hasNext()) {
            // 変数のみの式
            context.pushToken(token);
            return _right.interpret(context);
        }
        context.addTokenList(token);
        AbstractToken<T> variable = token;
        token = context.next();
        if (!(token instanceof EqualToken)) {
            // 2番目が=でないので変数で始まる式
            context.pushToken(token);
            context.pushToken(variable);
            return _right.interpret(context);
        }
        // 右辺を評価
        T rightValue = _right.interpret(context);
        // 右辺値を変数に代入
        variable.setValue(rightValue);
        context.addTokenList(token);
        return rightValue;
    }
}
