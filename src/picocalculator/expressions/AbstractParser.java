package picocalculator.expressions;

import picocalculator.Context;
import picocalculator.exceptions.ParsingErrorException;

public abstract class AbstractParser<T> {
    private final int _level;
    protected final AbstractParserFactory<T> _factory;

    public AbstractParser(int level, AbstractParserFactory<T> factory) {
        _level = level;
        _factory = factory;
    }

    protected int getLevel() {
        return _level;
    }

    public abstract T interpret(Context<T> context) throws ParsingErrorException;
}
