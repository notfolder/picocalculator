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
		if (!context.hasNext()) {
			throw new ParsingErrorException("数値か括弧'('が足りません", context.getIndex());
		}
		AbstractToken<T> token = context.next();
		_topToken = token;
		if (token instanceof LiteralToken) {
			return token.getValue();
		} else if (token instanceof ParenthesesStartToken) {
			// ( だったら中身を評価して
			AbstractExpression<T> expr = new Expression<T>();
			T ret = expr.interpret(context);
			token.addChild(expr.getTopToken());
			// )があることを確認して
			if (!context.hasNext()) {
				throw new ParsingErrorException("括弧')'が足りません", context.getIndex());
			}
			token = context.next();
			if (!(token instanceof ParenthesesEndToken)) {
				throw new ParsingErrorException("括弧')'が必要です", token.getIndex());
			}
			// 値を返す
			return ret;
		} else {
			throw new ParsingErrorException("数値か括弧'('を指定してください", token.getIndex());
		}
	}

}
