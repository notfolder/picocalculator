package picocalculator.tokens;

import java.util.Map;
import java.util.function.Function;

import picocalculator.exceptions.ParsingErrorException;

public class VariableToken<T> extends AbstractToken<T> {
    /** 変数名 */
    private String _name;
    /** マイナス値を計算するための関数オブジェクト */
    private Function<T, T> _function;
    /** 変数値を保持するMap */
    private Map<String,T> _variables;
    /** マイナス値トークンか？ */
    private boolean _sign = false;

    /**
     * コンストラクタ
     *
     * @param index このtokenの位置
     * @param str このtokenの文字列
     * @param value このtokenのliteral値(nullの場合は変数名)
     * @param function マイナス計算をするための関数オブジェクト
     */
    public VariableToken(int index, String str, T value, Function<T, T> function, Map<String, T> variables) {
        super(index, str);
        _name = str;
        _function = function;
        _variables = variables;
    }

    public void setMinus() {
        _sign = true;
    }

    @Override
    public T evalute(T left, T right) {
        throw new UnsupportedOperationException();
    }

    @Override
    public T getValue() throws UnsupportedOperationException, ParsingErrorException {
        if (!_variables.containsKey(_name)) {
            throw new ParsingErrorException("変数" + _name + "がありません", getIndex());
        }
        if (_sign) {
            return _function.apply(_variables.get(_name));
        } else {
            return _variables.get(_name);
        }
    }

    @Override
    public void setValue(T value) throws UnsupportedOperationException {
        _variables.put(_name, value);
    }
}
