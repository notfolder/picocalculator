package picocalculator.tokens;

import java.util.function.BiFunction;

public abstract class AbstractTermToken<T> extends AbstractBiOperatorToken<T> {

	public AbstractTermToken(BiFunction<T, T, T> function) {
		super(function);
	}
}
