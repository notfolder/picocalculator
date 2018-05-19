package picocalculator.tokens;

import java.util.function.BiFunction;

public abstract class AbstractTermToken<T> extends AbstractBiOperatorToken<T> {

	public AbstractTermToken(int index, String str, BiFunction<T, T, T> function) {
		super(index, str, function);
	}
}
