package picocalculator.tokens;

import java.util.function.BiFunction;

/**
 * <term>レベルのtokenをあらわす抽象クラス
 *
 * @author notfolder
 *
 * @param <T> 計算を行う際の型
 */
public abstract class AbstractTermToken<T> extends AbstractBiOperatorToken<T> {
    /**
     * コンストラクタ
     *
     * @param index このtokenの位置
     * @param str このtokenの文字
     * @param function このtokenを評価する関数オブジェクト
     */
    public AbstractTermToken(int index, String str, BiFunction<T, T, T> function) {
        super(index, str, function);
    }
}
