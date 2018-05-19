package picocalculator.expressions;

import picocalculator.Context;
import picocalculator.exceptions.ParsingErrorException;
import picocalculator.tokens.AbstractExpressionToken;
import picocalculator.tokens.AbstractToken;

public class Expression<T> extends AbstractExpression<T> {

	/**
	 * TODO: NoSuchElementExceptionのキャッチを行う
	 */
	@Override
	public T interpret(Context<T> context) throws ParsingErrorException {
		AbstractExpression<T> left = new ExpressionTerm<T>();
		T ret = left.interpret(context);
		while (context.hasNext()) {
			AbstractToken<T> token = context.next();
			if (!(token instanceof AbstractExpressionToken)) {
				context.pushToken(token);
				return ret;
			}
			AbstractExpression<T> right = new ExpressionTerm<T>();
			ret = token.evalute(ret, right.interpret(context));
		}
		return ret;
	}

}
