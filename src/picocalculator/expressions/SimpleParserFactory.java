package picocalculator.expressions;

public class SimpleParserFactory<T> extends AbstractParserFactory<T> {

    @Override
    public AbstractParser<T> createParser(int level) {
        switch (level) {
        case 0:
            return new Expression<T>(0, this);
        case 1:
            return new ExpressionTerm<T>(1, this);
        case 2:
            return new ExpressionFactor<T>(2, this);
        case 3:
            return new Expression<T>(0, this);
        default:
            throw new UnsupportedOperationException();
        }
    }

}
