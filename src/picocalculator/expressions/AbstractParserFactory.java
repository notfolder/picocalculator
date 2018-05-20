package picocalculator.expressions;

/**
 * Parserを生成するための抽象Factory
 *
 * @author notfolder
 *
 * @param <T> 計算するときに使用する型
 */
public abstract class AbstractParserFactory<T> {
    /**
     * パーサーを作成するメソッド
     *
     * @param level 作成するパーサーのBNF構文のレベル
     * @return パーサーオブジェクト
     */
    public abstract AbstractParser<T> createParser(int level);
}
