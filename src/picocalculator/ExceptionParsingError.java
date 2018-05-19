package picocalculator;

public class ExceptionParsingError extends Exception {
	private Context _context;
	private String _message;

	public ExceptionParsingError (String message, Context context) {
		_message = message;
		_context = context;
	}

	@Override
	public String toString() {
		return _message + " [" + _context.toString() + "]<-付近に間違いがあります";
	}
}
