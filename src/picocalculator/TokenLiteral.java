package picocalculator;

public class TokenLiteral<T> extends AbstractToken<T> {
	T _value;

	public TokenLiteral(T value) {
		_value = value;
	}

	@Override
	public T evalute(T left, T right) {
		throw new UnsupportedOperationException();
	}

	@Override
	public T getValue() throws UnsupportedOperationException {
		return _value;
	}

}
