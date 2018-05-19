package picocalculator.tokens;

import java.util.function.BiFunction;

public abstract class AbstractExpressionToken<T> extends AbstractBiOperatorToken<T> {

	public AbstractExpressionToken(int index, String str, BiFunction<T, T, T> function) {
		super(index, str, function);
	}
}
