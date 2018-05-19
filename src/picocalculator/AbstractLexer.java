package picocalculator;

import java.util.Stack;
import java.util.StringTokenizer;

public abstract class AbstractLexer<T> implements Context<T> {
	private StringTokenizer _tokenizer = null;
	private StringBuilder _current = new StringBuilder();
	private Stack<AbstractToken<T>> _stack = new Stack<>();

	public AbstractLexer(String str) {
		_tokenizer = new StringTokenizer(str);
	}

	protected abstract AbstractToken<T> parseToken(String str);

	@Override
	public AbstractToken<T> nextToken() {
		if (!_stack.isEmpty()) {
			return _stack.pop();
		}
		String str = _tokenizer.nextToken();
		_current.append(str);
		return parseToken(str);
	}

	@Override
	public boolean isEnd() {
		return !_tokenizer.hasMoreTokens();
	}

	@Override
	public String toString() {
		return _current.toString();
	}

	@Override
	public void pushToken(AbstractToken<T> token) {
		_stack.push(token);
	}
}
