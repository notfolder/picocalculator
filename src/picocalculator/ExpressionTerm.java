package picocalculator;

public class ExpressionTerm<T> extends AbstractExpression<T> {

	/**
	 * TODO: NoSuchElementExceptionのキャッチを行う
	 */
	@Override
	public T interpret(Context<T> context) throws ExceptionParsingError {
		AbstractExpression<T> left = new ExpressionFactor<T>();
		T ret = left.interpret(context);
		while (!context.isEnd()) {
			AbstractToken<T> token = context.nextToken();
			if ( !(token instanceof AbstractTokenTerm ) ) {
				context.pushToken(token);
				return ret;
			}
			AbstractExpression<T> right = new ExpressionFactor<T>();
			ret = token.evalute(ret, right.interpret(context));
		}
		return ret;
	}

}
