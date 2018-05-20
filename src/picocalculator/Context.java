package picocalculator;

import picocalculator.exceptions.ParsingErrorException;
import picocalculator.tokens.AbstractToken;

public interface Context<T> {
    public void pushToken(AbstractToken<T> token);

    public void addTokenList(AbstractToken<T> token);

    public String getRPN();

    public int getIndex();

    public AbstractToken<T> next() throws ParsingErrorException;

    public boolean hasNext();
}
