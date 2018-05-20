package picocalculator.tokens;

/**
 * <literal>をあらわすtokenのクラス
 *
 * @author notfolder
 *
 * @param <T>
 */
public class LiteralToken<T> extends AbstractToken<T> {
    /** このtokenのliteral値 */
    private T _value;

    /**
     * コンストラクタ
     *
     * @param index このtokenの位置
     * @param str このtokenの文字列
     * @param value このtokenのliteral値
     */
    public LiteralToken(int index, String str, T value) {
        super(index, str);
        _value = value;
    }

    @Override
    public T evalute(T left, T right) {
        throw new UnsupportedOperationException();
    }

    @Override
    public T getValue() throws UnsupportedOperationException {
        return _value;
    }

}
