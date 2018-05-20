package picocalculator.tokens;

/**
 * <factor>レベルのtokenをあらわす抽象クラス
 *
 * @author notfolder
 *
 * @param <T> 計算を行う際の型
 */
public abstract class AbstractFactorToken<T> extends AbstractToken<T> {
    /**
     * コンストラクタ
     *
     * @param index このtokenの位置
     * @param str このtokenの文字
     */
    public AbstractFactorToken(int index, String str) {
        super(index, str);
    }
}
