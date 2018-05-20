package picocalculator.tokens;

import java.util.function.BiFunction;

/**
 * 引き算(-)をあらわすtoken
 *
 * @author notfolder
 *
 * @param <T> 計算に使用する型
 */
public class MinusToken<T> extends AbstractExpressionToken<T> {
    /**
     * コンストラクタ
     *
     * @param index このtokenの位置
     * @param str このtokenの文字
     * @param function このtokenを評価する関数オブジェクト
     */
    public MinusToken(int index, String str, BiFunction<T, T, T> function) {
        super(index, str, function);
    }
}
