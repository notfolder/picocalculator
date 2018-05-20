package picocalculator;

import java.util.Stack;

import picocalculator.exceptions.ParsingErrorException;
import picocalculator.tokens.AbstractToken;

public abstract class AbstractLexer<T> implements Context<T> {
    private Stack<AbstractToken<T>> _stack = new Stack<>();
    private String _string = "";

    protected abstract AbstractToken<T> nextToken() throws ParsingErrorException;

    public AbstractLexer(String str) {
        _string = str;
    }

    @Override
    public AbstractToken<T> next() throws ParsingErrorException {
        if (!_stack.isEmpty()) {
            return _stack.pop();
        }
        return nextToken();
    }

    @Override
    public String toString() {
        return _string;
    }

    @Override
    public void pushToken(AbstractToken<T> token) {
        _stack.push(token);
    }
}
