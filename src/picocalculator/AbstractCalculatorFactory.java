package picocalculator;

import java.util.HashMap;
import java.util.Map;

import picocalculator.expressions.AbstractParser;

/**
 * 計算機を作成するための抽象Factoryの定義
 *
 * @author notfolder
 *
 * @param <T> 計算を行う際の型
 */
public abstract class AbstractCalculatorFactory<T> {
    /** Factoryクラスの名前と暮らすオブジェクトのレジストリ */
    private static Map<String, Class> _registor = new HashMap<String,Class>();

    static {
        _registor.put("SimpleCalculatorFactory", SimpleCalculatorFactory.class);
        _registor.put("BigDecimalCalculatorFactory", BigDecimalCalculatorFactory.class);
        _registor.put("DoubleCalculatorFactory", DoubleCalculatorFactory.class);
    }

    /**
     * Lexer(interpreterパターンでいうContext)を生成するメソッド
     *
     * @param str 計算を行う対象の式の文字列
     * @return 文字列を解釈するLexerオブジェクト
     */
    public abstract Context<T> createContext(String str);

    /**
     * Parser(interpreterパターンでいうExpression)を生成するメソッド
     *
     * @return 構文を解釈するParserオブジェクト
     */
    public abstract AbstractParser<T> createParser();

    /**
     * ファクトリ作成用ファクトリメソッド
     * レイヤーバイオレーション気味なので本来は専用Factoryを作成するべき。
     *
     * @param name ファクトリ名
     * @return 指定された名前のファクトリオブジェクト
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public static AbstractCalculatorFactory createFactory(String name) throws InstantiationException, IllegalAccessException {
        return (AbstractCalculatorFactory) _registor.get(name).newInstance();
    }

}
