package picocalculator.tokens;

public class LiteralToken<T> extends AbstractToken<T> {
	T _value;

	public LiteralToken(int index, String str, T value) {
		super(index, str);
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
