package picocalculator;

public abstract class AbstractExpression<T> {
	public abstract T interpret(Context<T> context) throws ExceptionParsingError;
}
