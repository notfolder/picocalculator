package picocalculator.tokens;

import java.util.function.Function;

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
    /** マイナス値を計算するための関数オブジェクト */
    private Function<T, T> _function;

    /**
     * コンストラクタ
     *
     * @param index このtokenの位置
     * @param str このtokenの文字列
     * @param value このtokenのliteral値
     * @param function マイナス計算をするための関数オブジェクト
     */
    public LiteralToken(int index, String str, T value, Function<T, T> function) {
        super(index, str);
        _value = value;
        _function = function;
    }

    public void setMinus() {
        _value = _function.apply(_value);
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
