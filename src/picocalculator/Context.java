package picocalculator;

import java.util.NoSuchElementException;

public interface Context<T> {
	public AbstractToken<T> nextToken() throws NoSuchElementException, NumberFormatException;
	public boolean isEnd();
	public void pushToken(AbstractToken<T> token);
}
