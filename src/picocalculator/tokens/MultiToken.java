package picocalculator.tokens;

import java.util.function.BiFunction;

public class MultiToken<T> extends AbstractTermToken<T> {

	public MultiToken(BiFunction<T, T, T> function) {
		super(function);
	}
}
