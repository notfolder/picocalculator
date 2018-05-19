package picocalculator.exceptions;

public class ParsingErrorException extends Exception {
	private final int _index;

	public ParsingErrorException(String message, int index) {
		super(message);
		_index = index;
	}

	public int getIndex() {
		return _index;
	}
}
