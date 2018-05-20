package picocalculator.expressions;

import picocalculator.Context;
import picocalculator.exceptions.ParsingErrorException;
import picocalculator.tokens.AbstractTermToken;
import picocalculator.tokens.AbstractToken;

public class ExpressionTerm<T> extends AbstractExpression<T> {

    @Override
    public T interpret(Context<T> context) throws ParsingErrorException {
        AbstractExpression<T> left = new ExpressionFactor<T>();
        T ret = left.interpret(context);
        while (context.hasNext()) {
            AbstractToken<T> token = context.next();
            // 次のtokenが*か/でなかったらtokenを戻して値を返す
            if (!(token instanceof AbstractTermToken)) {
                context.pushToken(token);
                return ret;
            }
            AbstractExpression<T> right = new ExpressionFactor<T>();
            T rightValue = right.interpret(context);
            ret = token.evalute(ret, rightValue);
            context.addTokenList(token);
        }
        return ret;
    }

}
