package picocalculator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

import picocalculator.exceptions.ParsingErrorException;
import picocalculator.expressions.AbstractParser;

/**
 * 電卓アプリのMainクラス
 *
 * @author notfoler
 */
public class PicoCalculator {
    /** デバッグフラグ。trueの場合デバッグ用逆ポーランド記法の出力をする */
    private static boolean _debug = false;

    /**
     * main関数
     *
     * jarをコマンドラインで実行した際に呼び出されるコマンド。
     *
     * @param args 引数。--debugを指定するとデバッグモードで動作する
     * @throws IOException 標準入力からのエラー発生時に投げる
     */
    public static void main(String[] args) throws IOException {
        // 起動時引数に--debugがあればデバッグモード
        if (args.length >= 1 && args[0].equals("--debug")) {
            _debug = true;
        }
        InputStreamReader is = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(is);

        // ファクトリを生成してパーサーを作成する
        AbstractCalculatorFactory<Integer> factory = new SimpleCalculatorFactory();
        AbstractParser<Integer> expr = factory.createParser();
        do {
            System.out.println("式を入力してください.(「.」のみで終了します)");
            String str = br.readLine();
            if (str.equals(".")) {
                break;
            }
            // Lexerを作成してParserを呼び出し、計算させる
            Context<Integer> context = factory.createContext(str);
            try {
                int value = expr.interpret(context);
                System.out.println(context.toString() + "=" + value);
                if (_debug) {
                    // デバッグ用逆ポーランド記法出力
                    System.out.println(context.getRPN());
                }
            } catch (ParsingErrorException e) {
                // Parsingの結果、式が正しくないことが判明したときの例外処理
                // どこが正しくないかユーザーに案内する
                System.out.println("式の形が正しくありません: " + e.getMessage());
                System.out.println(context.toString());
                // index分だけスペース作成
                char[] cc = new char[e.getIndex()];
                Arrays.fill(cc, ' ');
                System.out.println(new String(cc) + "^-- この近くに誤りがあります");
                // for debug
                if (_debug) {
                    System.out.println(context.getRPN());
                }
            }
        } while (true);
    }
}
