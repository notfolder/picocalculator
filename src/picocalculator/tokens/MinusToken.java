package picocalculator.tokens;

import java.util.function.BiFunction;

public class MinusToken<T> extends AbstractExpressionToken<T> {
	public MinusToken(BiFunction<T, T, T> function) {
		super(function);
	}
}
