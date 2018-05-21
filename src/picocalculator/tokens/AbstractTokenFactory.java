package picocalculator.tokens;

import java.util.HashMap;
import java.util.Map;

/**
 * tokenを生成するFactoryの抽象クラス
 *
 * @author notfolder
 *
 * @param <T> 計算を行う際の型
 */
public abstract class AbstractTokenFactory<T> {
    /** 変数値を持つMap */
    protected Map<String, T> _variables = new HashMap<String, T>();

    /**
     * トークンを生成するメソッド
     *
     * @param str tokenの文字列
     * @param index tokenの位置
     * @return tokenオブジェクト
     */
    public abstract AbstractToken<T> createToken(String str, int index);
}
