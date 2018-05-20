package picocalculator.tokens;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractToken<T> {
    public abstract T evalute(T left, T right) throws UnsupportedOperationException;

    public abstract T getValue() throws UnsupportedOperationException;

    private final int _index;

    private final String _string;

    private AbstractToken<T> _parent = null;
    private List<AbstractToken<T>> _children = new ArrayList<AbstractToken<T>>(2);

    public AbstractToken(int index, String string) {
        _index = index;
        _string = string;
    }

    public int getIndex() {
        return _index;
    }

    @Override
    public String toString() {
        return _string;
    }

    public void setParent(AbstractToken<T> parent) {
        _parent = parent;
    }

    public AbstractToken<T> getParent() {
        return _parent;
    }

    public void addChild(AbstractToken<T> child) {
        _children.add(child);
    }

    public List<AbstractToken<T>> getChildren() {
        return _children;
    }
}
