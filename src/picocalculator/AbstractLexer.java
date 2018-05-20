package picocalculator;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import picocalculator.exceptions.ParsingErrorException;
import picocalculator.tokens.AbstractToken;

public abstract class AbstractLexer<T> implements Context<T> {
    protected Stack<AbstractToken<T>> _stack = new Stack<>();
    private String _string = "";
    private List<AbstractToken<T>> _tokenList = new ArrayList<AbstractToken<T>>();

    protected abstract AbstractToken<T> nextToken() throws ParsingErrorException;

    public AbstractLexer(String str) {
        _string = str;
    }

    public void addTokenList(AbstractToken<T> token) {
        _tokenList.add(token);
    }

    @Override
    public String getRPN() {
        return _tokenList.toString();
    }

//    @Override
//    public boolean hasNext() {
//        if (!_stack.isEmpty()) {
//            return true;
//        }
//        return hasNext();
//    }

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
