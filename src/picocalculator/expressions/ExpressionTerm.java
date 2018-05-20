package picocalculator.expressions;

import picocalculator.Context;
import picocalculator.exceptions.ParsingErrorException;
import picocalculator.tokens.AbstractTermToken;
import picocalculator.tokens.AbstractToken;

public class ExpressionTerm<T> extends AbstractParser<T> {
    private final AbstractParser<T> _left;
    private final AbstractParser<T> _right;

    public ExpressionTerm(int level, AbstractParserFactory<T> factory) {
        super(level, factory);
        _left = _factory.createParser(getLevel()+1);
        _right = _factory.createParser(getLevel()+1);
    }

    @Override
    public T interpret(Context<T> context) throws ParsingErrorException {
        T ret = _left.interpret(context);
        while (context.hasNext()) {
            AbstractToken<T> token = context.next();
            // 次のtokenが*か/でなかったらtokenを戻して値を返す
            if (!(token instanceof AbstractTermToken)) {
                context.pushToken(token);
                return ret;
            }
            T rightValue = _right.interpret(context);
            ret = token.evalute(ret, rightValue);
            context.addTokenList(token);
        }
        return ret;
    }

}
