package picocalculator.tokens;

public class EqualToken<T> extends AbstractToken<T> {

    public EqualToken(int index, String string) {
        super(index, string);
    }

    @Override
    public T evalute(T left, T right) throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    @Override
    public T getValue() throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setValue(T value) throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setMinus() {
        throw new UnsupportedOperationException();
    }
}
