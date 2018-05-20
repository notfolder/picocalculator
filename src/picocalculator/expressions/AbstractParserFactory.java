package picocalculator.expressions;

public abstract class AbstractParserFactory<T> {
    public abstract AbstractParser<T> createParser(int level);
}
