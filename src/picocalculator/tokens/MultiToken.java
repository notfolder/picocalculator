package picocalculator.tokens;

import java.util.function.BiFunction;

/**
 * 掛け算(*)をあらわすtoken
 *
 * @author notfolder
 *
 * @param <T> 計算を行う際の型
 */
public class MultiToken<T> extends AbstractTermToken<T> {
    /**
     * コンストラクタ
     *
     * @param index このtokenの位置
     * @param str このtokenの文字
     * @param function このtokenを評価する関数オブジェクト
     */
    public MultiToken(int index, String str, BiFunction<T, T, T> function) {
        super(index, str, function);
    }
}
