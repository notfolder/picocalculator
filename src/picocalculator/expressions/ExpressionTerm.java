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
			if (!(token instanceof AbstractTermToken)) {
				context.pushToken(token);
				return ret;
			}
			AbstractExpression<T> right = new ExpressionFactor<T>();
			ret = token.evalute(ret, right.interpret(context));
		}
		return ret;
	}

}
