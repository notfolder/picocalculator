package picocalculator.tokens;

import java.util.function.BiFunction;

public class MultiToken<T> extends AbstractTermToken<T> {

	public MultiToken(int index, String str, BiFunction<T, T, T> function) {
		super(index, str, function);
	}
}
