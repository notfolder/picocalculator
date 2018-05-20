package picocalculator.tokens;

public abstract class AbstractTokenFactory<T> {
    public abstract AbstractToken<T> createToken(String str, int index);
}
