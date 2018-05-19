package picocalculator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.NoSuchElementException;

import picocalculator.exceptions.ParsingErrorException;
import picocalculator.expressions.Expression;

public class PicoCalculator {
	public static void main(String[] args) {
		InputStreamReader is = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(is);
		Expression<Integer> expr = new Expression<Integer>();
		try {
			do {
				System.out.println("式を入力してください.");
				String str = br.readLine();
				Context<Integer> context = new IntegerLexer(str);
				try {
					int value = expr.interpret(context);
					System.out.println(context.toString() + "=" + value);
				} catch (NumberFormatException e) {
					System.out.println("数値の形式が正しくありません [" + context.toString() + "]<-付近に間違いがあります");
				} catch (NoSuchElementException e) {
					System.out.println("式の末尾に達しました [" + context.toString() + "]<-付近に間違いがあります");
				} catch (ParsingErrorException e) {
					System.out.println("式の形が正しくありません: " + e.toString());
				}
			} while(true);
		} catch (IOException e) {
			System.out.println("入出力エラーです.プログラムを終了します.");
		}
	}
}
