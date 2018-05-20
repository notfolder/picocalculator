package picocalculator.tokens;

import java.util.function.BiFunction;

public class MinusToken<T> extends AbstractExpressionToken<T> {
    public MinusToken(int index, String str, BiFunction<T, T, T> function) {
        super(index, str, function);
    }
}
