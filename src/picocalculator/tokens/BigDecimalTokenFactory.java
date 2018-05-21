package picocalculator.tokens;

import java.math.BigDecimal;

public class BigDecimalTokenFactory extends AbstractTokenFactory<BigDecimal> {

    @Override
    public AbstractToken<BigDecimal> createToken(String str, int index) {
        char c = str.charAt(0);
        switch (c) {
        case '+':
            return new PlusToken<BigDecimal>(index, str, (left, right) -> {
                return left.add(right);
            });
        case '-':
            return new MinusToken<BigDecimal>(index, str, (left, right) -> {
                return left.subtract(right);
            });
        case '*':
            return new MultiToken<BigDecimal>(index, str, (left, right) -> {
                return left.multiply(right);
            });
        case '/':
            return new DivisionToken<BigDecimal>(index, str, (left, right) -> {
                return left.divide(right);
            });
        case '(':
            return new ParenthesesStartToken<BigDecimal>(index, str);
        case ')':
            return new ParenthesesEndToken<BigDecimal>(index, str);
        case '=':
            return new EqualToken<BigDecimal>(index, str);
        default:
            if (Character.isDigit(c)) {
                BigDecimal val = new BigDecimal(str);
                return new LiteralToken<BigDecimal>(index, str, val, (value) -> {
                    return value.negate();
                });
            } else {
                return new VariableToken<BigDecimal>(index, str, null, (value) -> {
                    return value.negate();
                }, _variables);
            }
        }
    }
}
