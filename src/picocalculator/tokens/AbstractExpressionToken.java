package picocalculator.tokens;

import java.util.function.BiFunction;

public abstract class AbstractExpressionToken<T> extends AbstractBiOperatorToken<T> {

	public AbstractExpressionToken(BiFunction<T, T, T> function) {
		super(function);
	}
}
