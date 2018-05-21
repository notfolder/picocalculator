package picocalculator.tokens;

/**
 * 整数のみを扱うtokenを生成するfactory。
 *
 * @author notfolder
 *
 */
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
        case '=':
            return new EqualToken<Integer>(index, str);
        default:
            if (Character.isDigit(c)) {
                Integer val = Integer.parseInt(str);
                if (Math.abs(val) > 99999999) {
                    throw new NumberFormatException("8桁以上の数値です");
                }
                return new LiteralToken<Integer>(index, str, val, (value) -> {
                    return -1 * value;
                });
            } else {
                return new VariableToken<Integer>(index, str, null, (value) -> {
                    return -1 * value;
                }, _variables);
            }
        }
    }
}
