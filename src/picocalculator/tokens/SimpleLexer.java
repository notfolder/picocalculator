package picocalculator.tokens;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Stack;

import picocalculator.Context;
import picocalculator.exceptions.ParsingErrorException;

/**
 * 整数を扱うlexer
 *
 * @author notfolder
 *
 * @param <T> 計算に使用する型
 */
public class SimpleLexer<T> implements Context<T> {
    /** 読み戻したtokenを保存するためのスタック */
    private Stack<AbstractToken<T>> _stack = new Stack<>();
    /** 解析対象の文字列 */
    private String _string = "";
    /** RPNを保持するためのtokenリスト */
    private List<AbstractToken<T>> _tokenList = new ArrayList<AbstractToken<T>>();
    /** tokenを生成するfactory */
    private AbstractTokenFactory<T> _factory;
    /** 現在解析中の文字の位置 */
    private int _index = 0;
    /** 予約語(キャラクタ) */
    private static final char[] _reserved = "+-*/()".toCharArray();
    /** あとで検索するためにsortしておく */
    static {
        Arrays.sort(_reserved);
    }

    @Override
    public int getIndex() {
        return _index;
    }

    /**
     * コンストラクタ
     *
     * @param str 解析対象の文字列
     * @param factory tokenを作成するfactory
     */
    public SimpleLexer(String str, AbstractTokenFactory<T> factory) {
        _string = str;
        _factory = factory;
    }

    /**
     * RPNを作成するためのtokenを追加するメソッド
     *
     * @param token RPNに追加するtoken
     */
    public void addTokenList(AbstractToken<T> token) {
        _tokenList.add(token);
    }

    @Override
    public String getRPN() {
        return _tokenList.toString();
    }

    @Override
    public AbstractToken<T> next() throws ParsingErrorException {
        if (!_stack.isEmpty()) {
            return _stack.pop();
        }
        return nextToken();
    }

    @Override
    public String toString() {
        return _string;
    }

    @Override
    public void pushToken(AbstractToken<T> token) {
        _stack.push(token);
    }

    @Override
    public boolean hasNext() {
        trim();
        if (!_stack.isEmpty()) {
            return true;
        }
        return _hasNext();
    }

    /**
     * 内部用hasNext。読み戻しを考慮しない
     *
     * @return まだ読み込める文字がある場合true
     */
    private boolean _hasNext() {
        return _index < _string.length();
    }

    /**
     * 次の文字列を取得する関数。lexerのメイン処理
     *
     * @return 次の文字列
     */
    private String nextString() {
        trim();
        // 空白を読み飛ばした結果、tokenがないのでエラー
        if (!hasNext()) {
            throw new NoSuchElementException();
        }

        StringBuilder sb = new StringBuilder();
        char c = _string.charAt(_index++);
        sb.append(c);
        // 次のtokenが予約語の場合、その文字を返す
        if (Arrays.binarySearch(_reserved, c) >= 0) {
            return sb.toString();
        }
        // 予約語以外は数値前提
        while (hasNext()) {
            // 先読みして次が予約語か空白かだったら止まる
            c = _string.charAt(_index);
            if (Character.isWhitespace(c)) {
                break;
            }
            if (Arrays.binarySearch(_reserved, c) >= 0) {
                break;
            }
            // 数値前提の文字なのでストックして次の文字
            sb.append(c);
            _index++;
        }
        return sb.toString();
    }

    /**
     * 空白文字を読み飛ばす処理
     */
    private void trim() {
        while (_hasNext()) {
            char c = _string.charAt(_index);
            if (!Character.isWhitespace(c)) {
                break;
            }
            _index++;
        }
    }

    /**
     * 次のtokenを取得するメソッド
     *
     * @return 次のtoken
     * @throws ParsingErrorException 文法誤りがあった際に投げる例外
     */
    private AbstractToken<T> nextToken() throws ParsingErrorException {
        String str = nextString();
        try {
            return _factory.createToken(str, _index);
        } catch (NumberFormatException e) {
            throw new ParsingErrorException("数値の形式がただしくありません: " + getRPN(), _index);
        }
    }

}
