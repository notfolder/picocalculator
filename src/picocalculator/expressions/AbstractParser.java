package picocalculator.expressions;

import picocalculator.Context;
import picocalculator.exceptions.ParsingErrorException;

/**
 * 計算を行うParserの抽象クラス
 *
 * @author notfolder
 *
 * @param <T> 計算を行う際の型
 */
public abstract class AbstractParser<T> {
    /** BNF構文における自クラスのレベル */
    private final int _level;
    /** BNF構文における各レベルのParserを作成するためのFactory */
    protected final AbstractParserFactory<T> _factory;

    /**
     * コンストラクタ
     *
     * @param level BNF構文における自クラスのレベル
     * @param factory 各levelのParserを作成するFactory
     */
    public AbstractParser(int level, AbstractParserFactory<T> factory) {
        _level = level;
        _factory = factory;
    }

    /**
     * 構文解釈を実行するメソッド
     *
     * @param context lexerオブジェクト
     * @return 構文解釈を実行した結果の値
     * @throws ParsingErrorException 構文解析時にエラーがあった際に投げる例外
     */
    public abstract T interpret(Context<T> context) throws ParsingErrorException;

    /**
     * 自クラスのBNF構文レベルを得るメソッド
     *
     * @return 自クラスのBNF構文レベル
     */
    protected int getLevel() {
        return _level;
    }

}
