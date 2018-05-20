package picocalculator;

import picocalculator.expressions.AbstractParser;

/**
 * 計算機を作成するための抽象Factoryの定義
 *
 * @author notfolder
 *
 * @param <T> 計算を行う際の型
 */
public abstract class AbstractCalculatorFactory<T> {
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
}
