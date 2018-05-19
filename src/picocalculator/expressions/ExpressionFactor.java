package picocalculator.expressions;

import picocalculator.Context;
import picocalculator.exceptions.ParsingErrorException;
import picocalculator.tokens.AbstractToken;
import picocalculator.tokens.LiteralToken;
import picocalculator.tokens.ParenthesesEndToken;
import picocalculator.tokens.ParenthesesStartToken;

public class ExpressionFactor<T> extends AbstractExpression<T> {

	@Override
	public T interpret(Context<T> context) throws ParsingErrorException {
		AbstractToken<T> token = context.next();
		if (token instanceof LiteralToken) {
			return token.getValue();
		} else if (token instanceof ParenthesesStartToken) {
			AbstractExpression<T> expr = new Expression<T>();
			T ret = expr.interpret(context);
			token = context.next();
			if (!(token instanceof ParenthesesEndToken)) {
				throw new ParsingErrorException("括弧')'を指定してください" + context.toString());
			}
			return ret;
		} else {
			throw new ParsingErrorException("数値か括弧'('を指定してください" + context.toString());
		}
	}

}
