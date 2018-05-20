package picocalculator.tokens;

/**
 * tokenを生成するFactoryの抽象クラス
 *
 * @author notfolder
 *
 * @param <T> 計算を行う際の型
 */
public abstract class AbstractTokenFactory<T> {
    /**
     * トークンを生成するメソッド
     *
     * @param str tokenの文字列
     * @param index tokenの位置
     * @return tokenオブジェクト
     */
    public abstract AbstractToken<T> createToken(String str, int index);
}
