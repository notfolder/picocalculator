package picocalculator;

import picocalculator.exceptions.ParsingErrorException;
import picocalculator.tokens.AbstractToken;

/**
 * interpreterパターンにおけるContext(Lexer)の定義。
 *
 * @author notfolder
 *
 * @param <T> 計算を行う際の型
 */
public interface Context<T> {
    /**
     * 先読みしたtokenを読み戻す。
     *
     * @param token 読み戻すtokenオブジェクト
     */
    public void pushToken(AbstractToken<T> token);

    /**
     * 逆ポーランド記法(RPN)を作成するためのリストへ追加するメソッド
     *
     * @param token リストへ追加するtoken
     */
    public void addTokenList(AbstractToken<T> token);

    /**
     * 逆ポーランド記法(RPN)での構文解析結果を文字列で得るメソッド
     *
     * @return 構文解析結果の逆ポーランド記法の文字列表現
     */
    public String getRPN();

    /**
     * 現在読んでいる文字の位置を得るメソッド
     *
     * @return 現在読んでいる文字の位置
     */
    public int getIndex();

    /**
     * 次のトークンを得るメソッド
     *
     * @return 次のトークン
     * @throws ParsingErrorException 文法に誤りがあった場合の例外
     */
    public AbstractToken<T> next() throws ParsingErrorException;

    /**
     * 次のトークンがあるか確認するメソッド
     *
     * @return 次のトークンがある場合、trueを返す
     */
    public boolean hasNext();
}
