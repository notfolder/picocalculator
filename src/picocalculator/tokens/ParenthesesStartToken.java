package picocalculator.tokens;

/**
 * 括弧'('をあらわすtoken
 *
 * @author notfolder
 *
 * @param <T>
 */
public class ParenthesesStartToken<T> extends AbstractFactorToken<T> {
    /**
     * コンストラクタ
     *
     * @param index このtokenの位置
     * @param str このtokenの文字
     */
    public ParenthesesStartToken(int index, String string) {
        super(index, string);
    }

    @Override
    public T evalute(T left, T right) throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    @Override
    public T getValue() throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }
}
