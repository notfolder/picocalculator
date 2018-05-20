package picocalculator.expressions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Stack;

import picocalculator.Context;
import picocalculator.exceptions.ParsingErrorException;
import picocalculator.tokens.AbstractToken;
import picocalculator.tokens.AbstractTokenFactory;

public class SimpleLexer<T> implements Context<T> {
    private Stack<AbstractToken<T>> _stack = new Stack<>();
    private String _string = "";
    private List<AbstractToken<T>> _tokenList = new ArrayList<AbstractToken<T>>();
    private AbstractTokenFactory<T> _factory;
    private int _index = 0;
    private static final char[] _reserved = "+-*/()".toCharArray();
    static {
        Arrays.sort(_reserved);
    }

    @Override
    public int getIndex() {
        return _index;
    }

    public SimpleLexer(String str, AbstractTokenFactory<T> factory) {
        _string = str;
        _factory = factory;
    }

    public void addTokenList(AbstractToken<T> token) {
        _tokenList.add(token);
    }

    @Override
    public String getRPN() {
        return _tokenList.toString();
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

    @Override
    public boolean hasNext() {
        trim();
        if (!_stack.isEmpty()) {
            return true;
        }
        return _hasNext();
    }

    private boolean _hasNext() {
        return _index < _string.length();
    }

    private String nextString() {
        trim();
        // 空白を読み飛ばした結果、tokenがないのでエラー
        if (!hasNext()) {
            throw new NoSuchElementException();
        }

        StringBuilder sb = new StringBuilder();
        char c = _string.charAt(_index++);
        sb.append(c);
        // 次のtokenが予約語の場合
        if (Arrays.binarySearch(_reserved, c) >= 0) {
            return sb.toString();
        }
        // 予約語以外は数値前提
        while (hasNext()) {
            // 先読みして次が予約語か空白かだったら止まる
            c = _string.charAt(_index);
            if (Character.isWhitespace(c)) {
                break;
            }
            if (Arrays.binarySearch(_reserved, c) >= 0) {
                break;
            }
            // 数値前提の文字なのでストックして次の文字
            sb.append(c);
            _index++;
        }
        return sb.toString();
    }

    private void trim() {
        while (_hasNext()) {
            char c = _string.charAt(_index);
            if (!Character.isWhitespace(c)) {
                break;
            }
            _index++;
        }
    }

    private AbstractToken<T> nextToken() throws ParsingErrorException {
        String str = nextString();
        try {
            return _factory.createToken(str, _index);
        } catch (NumberFormatException e) {
            throw new ParsingErrorException("数値ではありません: " + getRPN(), _index);
        }
    }

}
