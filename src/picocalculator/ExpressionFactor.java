package picocalculator;

public class ExpressionFactor<T> extends AbstractExpression<T> {

	/**
	 * TODO: NoSuchElementExceptionのキャッチを行う
	 */
	@Override
	public T interpret(Context<T> context) throws ExceptionParsingError {
		AbstractToken<T> token = context.nextToken();
		if ( token instanceof TokenLiteral ) {
			return token.getValue();
		} else if ( token instanceof TokenParenthesesStart ) {
			AbstractExpression<T> expr = new Expression<T>();
			T ret = expr.interpret(context);
			token = context.nextToken();
			if (! (token instanceof TokenParenthesesEnd)) {
				throw new ExceptionParsingError("括弧')'を指定してください", context);
			}
			return ret;
		} else {
			throw new ExceptionParsingError("数値か括弧'('を指定してください", context);
		}
	}

}
