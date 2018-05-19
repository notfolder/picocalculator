package picocalculator.tokens;

import java.util.function.BiFunction;

public abstract class AbstractBiOperatorToken<T> extends AbstractToken<T> {
	private BiFunction<T, T, T> _function;

	public AbstractBiOperatorToken(BiFunction<T, T, T> function) {
		_function = function;
	}

	@Override
	public T evalute(T left, T right) throws UnsupportedOperationException {
		return _function.apply(left, right);
	}

	@Override
	public T getValue() throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}

}
