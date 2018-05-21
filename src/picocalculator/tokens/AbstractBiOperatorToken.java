package picocalculator.tokens;

import java.util.function.BiFunction;

/**
 * 二項演算子(+,-,*,/など)を表現するtokenの抽象クラス
 *
 * @author notfolder
 *
 * @param <T> 計算する際の型
 */
public abstract class AbstractBiOperatorToken<T> extends AbstractToken<T> {
    /** 計算に使用するFunctionオブジェクト */
    private BiFunction<T, T, T> _function;

    /**
     * コンストラクタ
     *
     * @param index tokenの位置
     * @param str tokenの文字列
     * @param function tokenを処理するFunctionオブジェクト
     */
    public AbstractBiOperatorToken(int index, String str, BiFunction<T, T, T> function) {
        super(index, str);
        _function = function;
    }

    @Override
    public T evalute(T left, T right) throws UnsupportedOperationException {
        return _function.apply(left, right);
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
