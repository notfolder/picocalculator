package picocalculator.expressions;

import java.util.ArrayList;
import java.util.List;

import picocalculator.Context;
import picocalculator.exceptions.ParsingErrorException;
import picocalculator.tokens.AbstractToken;

public abstract class AbstractExpression<T> {
	protected AbstractToken<T> _topToken = null;
	public abstract T interpret(Context<T> context) throws ParsingErrorException;

	public AbstractToken<T> getTopToken() {
		return _topToken;
	}

	private static final String LINE_SEPARATOR = System.getProperty("line.separator");

	public String printTokenTree() {
		List<String> arr = new ArrayList<String>();
		AbstractToken<T> top = getTopToken();
		if (top == null) {
			return "";
		}
		treeIterator(getTopToken(), arr, "");
		StringBuilder sb = new StringBuilder();
		for (String str : arr) {
			sb.append(str);
			sb.append(LINE_SEPARATOR);
		}
		return sb.toString();
	}

	private void treeIterator(AbstractToken<T> target, List<String> tree, String level) {
		StringBuilder sb = new StringBuilder();
		sb.append(level).append(target.toString());
		tree.add(sb.toString());
		if (!target.getChildren().isEmpty()) {
			for (AbstractToken<T> child : target.getChildren()) {
				treeIterator(child, tree, level + " ");
			}
		}
	}
}
