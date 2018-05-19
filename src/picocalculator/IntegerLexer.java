package picocalculator;

public class IntegerLexer extends AbstractLexer<Integer> {

	public IntegerLexer(String str) {
		super(str);
	}

	@Override
	protected AbstractToken<Integer> parseToken(String str) {
		char c = str.charAt(0);
		switch (c) {
		case '+':
			return new TokenPlus<Integer>( (left, right) -> { return left + right; });
		case '-':
			return new TokenMinus<Integer>( (left, right) -> { return left - right; });
		case '*':
			return new TokenMulti<Integer>( (left, right) -> { return left * right; });
		case '/':
			return new TokenDivision<Integer>( (left, right) -> { return left / right; });
		case '(':
			return new TokenParenthesesStart<Integer>();
		case ')':
			return new TokenParenthesesEnd<Integer>();
		default:
			// TODO: 8桁以上の数字だったら例外
			return new TokenLiteral<Integer>(Integer.parseInt(str));
		}
	}
}
