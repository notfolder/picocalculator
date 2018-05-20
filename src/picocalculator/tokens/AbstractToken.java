package picocalculator.tokens;

public abstract class AbstractToken<T> {
    public abstract T evalute(T left, T right) throws UnsupportedOperationException;

    public abstract T getValue() throws UnsupportedOperationException;

    private final int _index;

    private final String _string;

    public AbstractToken(int index, String string) {
        _index = index;
        _string = string;
    }

    public int getIndex() {
        return _index;
    }

    @Override
    public String toString() {
        return _string;
    }
}
