package picocalculator.exceptions;

/**
 * 構文エラーを検出した際に投げる例外
 *
 * @author notfolder
 */
public class ParsingErrorException extends Exception {
    /** 例外が発生した文字の位置 */
    private final int _index;

    /**
     * コンストラクタ
     *
     * @param message 構文エラーの説明文
     * @param index 構文エラーの文字の位置
     */
    public ParsingErrorException(String message, int index) {
        super(message);
        _index = index;
    }

    /**
     * 構文エラーが発生した文字の位置を得るメソッド
     *
     * @return 構文エラーが発生した文字の位置
     */
    public int getIndex() {
        return _index;
    }
}
