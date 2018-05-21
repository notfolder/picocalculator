package picocalculator.tokens;

public class DoubleTokenFactory extends AbstractTokenFactory<Double> {
    @Override
    public AbstractToken<Double> createToken(String str, int index) {
        char c = str.charAt(0);
        switch (c) {
        case '+':
            return new PlusToken<Double>(index, str, (left, right) -> {
                return left + right;
            });
        case '-':
            return new MinusToken<Double>(index, str, (left, right) -> {
                return left - right;
            });
        case '*':
            return new MultiToken<Double>(index, str, (left, right) -> {
                return left * right;
            });
        case '/':
            return new DivisionToken<Double>(index, str, (left, right) -> {
                return left / right;
            });
        case '(':
            return new ParenthesesStartToken<Double>(index, str);
        case ')':
            return new ParenthesesEndToken<Double>(index, str);
        default:
            return new LiteralToken<Double>(index, str, Double.parseDouble(str), (value) -> {
                return -1 * value;
            });
        }
    }
}
