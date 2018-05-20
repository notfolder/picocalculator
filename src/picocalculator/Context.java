package picocalculator;

import picocalculator.exceptions.ParsingErrorException;
import picocalculator.tokens.AbstractToken;

public interface Context<T> {
    public void pushToken(AbstractToken<T> token);

    public int getIndex();

    public AbstractToken<T> next() throws ParsingErrorException;

    public AbstractToken<T> currentToken();

    public boolean hasNext();
}
