package picocalculator;

import java.util.Iterator;

import picocalculator.tokens.AbstractToken;

public interface Context<T> extends Iterator<AbstractToken<T>> {
	public void pushToken(AbstractToken<T> token);
}
