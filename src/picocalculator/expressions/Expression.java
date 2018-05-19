package picocalculator.expressions;

import picocalculator.Context;
import picocalculator.exceptions.ParsingErrorException;
import picocalculator.tokens.AbstractExpressionToken;
import picocalculator.tokens.AbstractToken;

public class Expression<T> extends AbstractExpression<T> {

	@Override
	public T interpret(Context<T> context) throws ParsingErrorException {
		AbstractExpression<T> left = new ExpressionTerm<T>();
		T ret = left.interpret(context);
		_topToken = left.getTopToken();
		AbstractToken<T> prev = null;
		while (context.hasNext()) {
			AbstractToken<T> token = context.next();
			// 次のtokenが+か-でなかったらtokenを戻して値を返す
			if (!(token instanceof AbstractExpressionToken)) {
				context.pushToken(token);
				return ret;
			}
			if (prev != null) {
				prev.addChild(token);
			} else {
				_topToken = token;
				token.addChild(left.getTopToken());
			}
			AbstractExpression<T> right = new ExpressionTerm<T>();
			ret = token.evalute(ret, right.interpret(context));
			token.addChild(right.getTopToken());
			prev = token;
		}
		return ret;
	}

}
