package picocalculator.expressions;

import picocalculator.Context;
import picocalculator.exceptions.ParsingErrorException;
import picocalculator.tokens.AbstractToken;
import picocalculator.tokens.LiteralToken;
import picocalculator.tokens.MinusToken;
import picocalculator.tokens.ParenthesesEndToken;
import picocalculator.tokens.ParenthesesStartToken;

/**
 * BNF構文における<factor>レベルの構文解析を行うParser
 *
 * @author notfolder
 *
 * @param <T> 計算を行う際の型
 */
public class ExpressionFactor<T> extends AbstractParser<T> {
    /**
     * コンストラクタ
     *
     * @param level 自クラスのBNF構文レベル
     * @param factory 上位のparserを生成するためのFactory
     */
    public ExpressionFactor(int level, AbstractParserFactory<T> factory) {
        super(level, factory);
    }

    @Override
    public T interpret(Context<T> context) throws ParsingErrorException {
        if (!context.hasNext()) {
            throw new ParsingErrorException("数値か括弧'('が足りません", context.getIndex());
        }
        AbstractToken<T> token = context.next();

        // マイナスで始まる数値
        int sign = 1;
        if (token instanceof MinusToken) {
            sign = -1;
            context.addTokenList(token);
            if (!context.hasNext()) {
                throw new ParsingErrorException("数値か括弧'('が足りません", context.getIndex());
            }
            token = context.next();
        }

        if (token instanceof LiteralToken) {
            context.addTokenList(token);
            if (sign == -1) {
                ((LiteralToken<T>) token).setMinus();
            }
            return token.getValue();
        } else if (token instanceof ParenthesesStartToken) {
            // ( だったら中身を評価して
            AbstractParser<T> expr = _factory.createParser(getLevel() + 1);
            T ret = expr.interpret(context);
            // )があることを確認して
            if (!context.hasNext()) {
                throw new ParsingErrorException("括弧')'が足りません: " + context.getRPN(), context.getIndex());
            }
            token = context.next();
            if (!(token instanceof ParenthesesEndToken)) {
                throw new ParsingErrorException("括弧')'が必要です :" + context.getRPN(), token.getIndex());
            }
            // 値を返す
            return ret;
        } else {
            throw new ParsingErrorException("数値か括弧'('を指定してください: " + context.getRPN(), token.getIndex());
        }
    }
}
