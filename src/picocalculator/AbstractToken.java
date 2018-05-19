package picocalculator;

public abstract class AbstractToken<T> {
	public abstract T evalute(T left, T right) throws UnsupportedOperationException;
	public abstract T getValue() throws UnsupportedOperationException;
}
