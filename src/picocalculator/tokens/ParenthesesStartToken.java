package picocalculator.tokens;

public class ParenthesesStartToken<T> extends AbstractToken<T> {

	public ParenthesesStartToken(int index, String string) {
		super(index, string);
	}

	@Override
	public T evalute(T left, T right) throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}

	@Override
	public T getValue() throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}
}
