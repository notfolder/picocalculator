package picocalculator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

import picocalculator.exceptions.ParsingErrorException;
import picocalculator.expressions.Expression;

public class PicoCalculator {
    private static boolean _debug = false;

    public static void main(String[] args) throws IOException {
        // 起動時引数に--debugがあればデバッグモード
        if (args.length >= 1 && args[0].equals("--debug")) {
            _debug = true;
        }
        InputStreamReader is = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(is);
        do {
            System.out.println("式を入力してください.(「.」のみで終了します)");
            String str = br.readLine();
            if (str.equals(".")) {
                break;
            }
            Context<Integer> context = new IntegerLexer(str);
            Expression<Integer> expr = new Expression<Integer>();
            try {
                int value = expr.interpret(context);
                System.out.println(context.toString() + "=" + value);
                if (_debug) {
                    System.out.println(expr.printTokenTree());
                }
            } catch (ParsingErrorException e) {
                System.out.println("式の形が正しくありません: " + e.toString());
                System.out.println(context.toString());
                // index分だけスペース作成
                char[] cc = new char[e.getIndex()];
                Arrays.fill(cc, ' ');
                System.out.println(new String(cc) + "^-- この近くに誤りがあります");
                // for debug
                if (_debug) {
                    System.out.println(expr.printTokenTree());
                }
            }
        } while (true);
    }
}
