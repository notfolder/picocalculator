package picocalculator.tokens;

import java.util.function.BiFunction;

/**
 * 割り算</>を表すtokenクラス
 *
 * @author notfolder
 *
 * @param <T> 計算を行う際の型
 */
public class DivisionToken<T> extends AbstractTermToken<T> {
    /**
     * コンストラクタ
     *
     * @param index このtokenの位置
     * @param str このtokenの文字
     * @param function このtokenを評価する関数
     */
    public DivisionToken(int index, String str, BiFunction<T, T, T> function) {
        super(index, str, function);
    }
}
