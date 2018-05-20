package picocalculator.expressions;

import picocalculator.Context;
import picocalculator.exceptions.ParsingErrorException;
import picocalculator.tokens.AbstractExpressionToken;
import picocalculator.tokens.AbstractToken;

public class Expression<T> extends AbstractExpression<T> {

    @Override
    public T interpret(Context<T> context) throws ParsingErrorException {
        AbstractExpression<T> left = new ExpressionTerm<T>();
        T leftValue = left.interpret(context);
        while (context.hasNext()) {
            AbstractToken<T> token = context.next();
            // 次のtokenが+か-でなかったらtokenを戻して値を返す
            if (!(token instanceof AbstractExpressionToken)) {
                context.pushToken(token);
                return leftValue;
            }
            AbstractExpression<T> right = new ExpressionTerm<T>();
            T rightValue = right.interpret(context);
            leftValue = token.evalute(leftValue, rightValue);
            context.addTokenList(token);
        }
        return leftValue;
    }

}
