package picocalculator;

import java.util.Arrays;
import java.util.NoSuchElementException;

import picocalculator.exceptions.ParsingErrorException;
import picocalculator.tokens.AbstractToken;
import picocalculator.tokens.DivisionToken;
import picocalculator.tokens.LiteralToken;
import picocalculator.tokens.MinusToken;
import picocalculator.tokens.MultiToken;
import picocalculator.tokens.ParenthesesEndToken;
import picocalculator.tokens.ParenthesesStartToken;
import picocalculator.tokens.PlusToken;

public class IntegerLexer extends AbstractLexer<Integer> {
	private String _string;
	private int _index = 0;
	private static final char[] _reserved = "+-*/()".toCharArray();
	static {
		Arrays.sort(_reserved);
	}
	private AbstractToken<Integer> _currentToken = null;

	public IntegerLexer(String str) {
		super(str);
		_string = str;
	}

	@Override
	public int getIndex() {
		return _index;
	}

	@Override
	public boolean hasNext() {
		return _index < _string.length();
	}

	private String nextString() {
		trim();
		// 空白を読み飛ばした結果、tokenがないのでエラー
		if (!hasNext()) {
			throw new NoSuchElementException();
		}

		StringBuilder sb= new StringBuilder();
		char c =_string.charAt(_index++);
		sb.append(c);
		// 次のtokenが予約語の場合
		if (Arrays.binarySearch(_reserved, c) >= 0) {
			return sb.toString();
		}
		// 予約語以外は数値前提
		while(hasNext()) {
			// 先読みして次が予約語か空白かだったら止まる
			c = _string.charAt(_index);
			if (Character.isWhitespace(c)) {
				break;
			}
			if (Arrays.binarySearch(_reserved, c) >= 0) {
				break;
			}
			// 数値前提の文字なのでストックして次の文字
			sb.append(c);
			_index++;
		}
		return sb.toString();
	}

	private void trim() {
		while (hasNext()) {
			char c =_string.charAt(_index);
			if (!Character.isWhitespace(c)) {
				break;
			}
			_index++;
		}
	}

	@Override
	protected AbstractToken<Integer> nextToken()  throws ParsingErrorException {
		int current = _index;
		String str = nextString();
		char c = str.charAt(0);
		switch (c) {
		case '+':
			return new PlusToken<Integer>(current, str, (left, right) -> {
				return left + right;
			});
		case '-':
			return new MinusToken<Integer>(current, str, (left, right) -> {
				return left - right;
			});
		case '*':
			return new MultiToken<Integer>(current, str, (left, right) -> {
				return left * right;
			});
		case '/':
			return new DivisionToken<Integer>(current, str, (left, right) -> {
				return left / right;
			});
		case '(':
			return new ParenthesesStartToken<Integer>(current, str);
		case ')':
			return new ParenthesesEndToken<Integer>(current, str);
		default:
			// TODO: 8桁以上の数字だったら例外
			try {
			return new LiteralToken<Integer>(current, str, Integer.parseInt(str));
			} catch (NumberFormatException e) {
				throw new ParsingErrorException("数値ではありません", _index);
			}
		}
	}

	@Override
	public AbstractToken<Integer> currentToken() {
		return _currentToken;
	}
}
