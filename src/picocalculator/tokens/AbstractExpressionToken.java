package picocalculator.tokens;

import java.util.function.BiFunction;

/**
 * <expression>レベルのtokenをあらわす抽象クラス
 *
 * @author notfolder
 *
 * @param <T> 計算に使用する型
 */
public abstract class AbstractExpressionToken<T> extends AbstractBiOperatorToken<T> {
    /**
     * コンストラクタ
     *
     * @param index このtokenの文字の位置
     * @param str このtokenの文字
     * @param function このtokenの評価をする関数オブジェクト
     */
    public AbstractExpressionToken(int index, String str, BiFunction<T, T, T> function) {
        super(index, str, function);
    }
}
