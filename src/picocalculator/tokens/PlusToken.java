package picocalculator.tokens;

import java.util.function.BiFunction;

public class PlusToken<T> extends AbstractExpressionToken<T> {
	public PlusToken(BiFunction<T, T, T> function) {
		super(function);
	}
}
