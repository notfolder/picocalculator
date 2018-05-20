package picocalculator.tokens;

import java.util.function.BiFunction;

public class PlusToken<T> extends AbstractExpressionToken<T> {
    public PlusToken(int index, String str, BiFunction<T, T, T> function) {
        super(index, str, function);
    }
}
