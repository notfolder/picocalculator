package picocalculator.tokens;

public class IntegerTokenFactory extends AbstractTokenFactory<Integer> {

    @Override
    public AbstractToken<Integer> createToken(String str, int index) {
        char c = str.charAt(0);
        switch (c) {
        case '+':
            return new PlusToken<Integer>(index, str, (left, right) -> {
                return left + right;
            });
        case '-':
            return new MinusToken<Integer>(index, str, (left, right) -> {
                return left - right;
            });
        case '*':
            return new MultiToken<Integer>(index, str, (left, right) -> {
                return left * right;
            });
        case '/':
            return new DivisionToken<Integer>(index, str, (left, right) -> {
                return left / right;
            });
        case '(':
            return new ParenthesesStartToken<Integer>(index, str);
        case ')':
            return new ParenthesesEndToken<Integer>(index, str);
        default:
            // TODO: 8桁以上の数字だったら例外
            return new LiteralToken<Integer>(index, str, Integer.parseInt(str));
        }
    }

}
