package picocalculator;

public class Expression<T> extends AbstractExpression<T> {

	/**
	 * TODO: NoSuchElementExceptionのキャッチを行う
	 */
	@Override
	public T interpret(Context<T> context) throws ExceptionParsingError {
		AbstractExpression<T> left = new ExpressionTerm<T>();
		T ret = left.interpret(context);
		while (!context.isEnd()) {
			AbstractToken<T> token = context.nextToken();
			if ( !(token instanceof AbstractTokenExpression ) ) {
				context.pushToken(token);
				return ret;
			}
			AbstractExpression<T> right = new ExpressionTerm<T>();
			ret = token.evalute(ret, right.interpret(context));
		}
		return ret;
	}

}
