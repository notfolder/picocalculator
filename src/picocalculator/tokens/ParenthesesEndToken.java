package picocalculator.tokens;

public class ParenthesesEndToken<T> extends AbstractToken<T> {

	@Override
	public T evalute(T left, T right) throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}

	@Override
	public T getValue() throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}
}
