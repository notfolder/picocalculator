package picocalculator;

import picocalculator.tokens.AbstractToken;
import picocalculator.tokens.DivisionToken;
import picocalculator.tokens.LiteralToken;
import picocalculator.tokens.MinusToken;
import picocalculator.tokens.MultiToken;
import picocalculator.tokens.ParenthesesEndToken;
import picocalculator.tokens.ParenthesesStartToken;
import picocalculator.tokens.PlusToken;

/**
*
* Iteratorなので、内部でLexingをすべて実施してArrayListのIteratorに委譲してもよい。
*
* @author SATORU
*
* @param <T>
*/
public class IntegerLexer extends AbstractLexer<Integer> {

	public IntegerLexer(String str) {
		super(str);
	}

	@Override
	protected AbstractToken<Integer> parseToken(String str) {
		char c = str.charAt(0);
		switch (c) {
		case '+':
			return new PlusToken<Integer>((left, right) -> {
				return left + right;
			});
		case '-':
			return new MinusToken<Integer>((left, right) -> {
				return left - right;
			});
		case '*':
			return new MultiToken<Integer>((left, right) -> {
				return left * right;
			});
		case '/':
			return new DivisionToken<Integer>((left, right) -> {
				return left / right;
			});
		case '(':
			return new ParenthesesStartToken<Integer>();
		case ')':
			return new ParenthesesEndToken<Integer>();
		default:
			// TODO: 8桁以上の数字だったら例外
			return new LiteralToken<Integer>(Integer.parseInt(str));
		}
	}
}
