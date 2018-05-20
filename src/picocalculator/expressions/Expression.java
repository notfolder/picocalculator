package picocalculator.expressions;

import picocalculator.Context;
import picocalculator.exceptions.ParsingErrorException;
import picocalculator.tokens.AbstractExpressionToken;
import picocalculator.tokens.AbstractToken;

public class Expression<T> extends AbstractParser<T> {
    private final AbstractParser<T> _left;
    private final AbstractParser<T> _right;

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
