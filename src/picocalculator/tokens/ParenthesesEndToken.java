package picocalculator.tokens;

public class ParenthesesEndToken<T> extends AbstractToken<T> {

    public ParenthesesEndToken(int index, String str) {
        super(index, str);
    }

    @Override
    public T evalute(T left, T right) throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    @Override
    public T getValue() throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }
}
