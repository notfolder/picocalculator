package picocalculator.tokens;

import java.util.function.BiFunction;

public class DivisionToken<T> extends AbstractTermToken<T> {

	public DivisionToken(int index, String str, BiFunction<T, T, T> function) {
		super(index, str, function);
	}
}
