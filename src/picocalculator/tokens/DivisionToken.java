package picocalculator.tokens;

import java.util.function.BiFunction;

public class DivisionToken<T> extends AbstractTermToken<T> {

	public DivisionToken(BiFunction<T, T, T> function) {
		super(function);
	}
}
