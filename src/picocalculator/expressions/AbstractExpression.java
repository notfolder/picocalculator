package picocalculator.expressions;

import picocalculator.Context;
import picocalculator.exceptions.ParsingErrorException;

public abstract class AbstractExpression<T> {
	public abstract T interpret(Context<T> context) throws ParsingErrorException;
}
