package picocalculator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

import picocalculator.exceptions.ParsingErrorException;
import picocalculator.expressions.Expression;

public class PicoCalculator {
	public static void main(String[] args) {
		InputStreamReader is = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(is);
		try {
			do {
				System.out.println("式を入力してください.");
				String str = br.readLine();
				Context<Integer> context = new IntegerLexer(str);
				Expression<Integer> expr = new Expression<Integer>();
				try {
					int value = expr.interpret(context);
					System.out.println(context.toString() + "=" + value);
					System.out.println(expr.printTokenTree());
				} catch (ParsingErrorException e) {
					System.out.println("式の形が正しくありません: " + e.toString());
					System.out.println(context.toString());
					// index分だけスペース作成
					char[] cc = new char[e.getIndex()];
					Arrays.fill(cc, ' ');
					System.out.println(new String(cc) + "^-- この近くに誤りがあります");
					if (context.currentToken() != null) {
						System.out.println(context.currentToken().toString());
					}
					System.out.println(expr.printTokenTree());
				}
			} while(true);
		} catch (IOException e) {
			System.out.println("入出力エラーです.プログラムを終了します.");
		}
	}
}
