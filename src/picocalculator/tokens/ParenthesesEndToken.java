package picocalculator.tokens;

/**
 * 括弧')'をあらわすtoken
 *
 * @author notfolder
 *
 * @param <T>
 */
public class ParenthesesEndToken<T> extends AbstractFactorToken<T> {
    /**
     * コンストラクタ
     *
     * @param index このtokenの位置
     * @param str このtokenの文字
     */
    public ParenthesesEndToken(int index, String str) {
        super(index, str);
    }

    @Override
    public T evalute(T left, T right) throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    @Override
    public T getValue() throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setValue(T value) throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }
    @Override
    public void setMinus() {
        throw new UnsupportedOperationException();
    }
}
