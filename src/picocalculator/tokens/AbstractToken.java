package picocalculator.tokens;

/**
 * tokenをあらわす抽象クラス
 *
 * @author notfolder
 *
 * @param <T> 計算をする際の型
 */
public abstract class AbstractToken<T> {
    /** このtokenの文字の位置 */
    private final int _index;
    /** このtokenの文字 */
    private final String _string;

    /**
     * 2項演算子の評価
     *
     * @param left 左辺値
     * @param right 右辺値
     * @return 計算した結果
     * @throws UnsupportedOperationException そのトークンで2項演算子をサポートしていない際に投げる例外
     */
    public abstract T evalute(T left, T right) throws UnsupportedOperationException;

    /**
     * リテラル値の評価
     *
     * @return リテラル値
     * @throws UnsupportedOperationException そのトークンでリテラル値を持っていない際に投げる例外
     */
    public abstract T getValue() throws UnsupportedOperationException;

    /**
     * コンストラクタ
     *
     * @param index このtokenの文字の位置
     * @param string このtokenの文字
     */
    public AbstractToken(int index, String string) {
        _index = index;
        _string = string;
    }

    /**
     * このtokenの文字の位置を得るメソッド
     *
     * @return このtokenの文字の位置
     */
    public int getIndex() {
        return _index;
    }

    @Override
    public String toString() {
        return _string;
    }
}
