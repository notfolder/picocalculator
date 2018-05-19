package picocalculator;

public class PicoCalculator {
	public static void main(String[] args) {
		Context<Integer> context = new IntegerLexer("2 + 100 * 10 + ( 10 + 1 ) ");
		Expression<Integer> expr = new Expression<Integer>();
		try {
			int value = expr.interpret(context);
			System.out.println(context.toString() + "=" + value);
//		} catch (NumberFormatException e) {
//			System.out.println("数値の形式が正しくありません [" + context.toString() + "]<-付近に間違いがあります");
//		} catch (NoSuchElementException e) {
//			System.out.println("式の末尾に達しました [" + context.toString() + "]<-付近に間違いがあります");
		} catch (ExceptionParsingError e) {
			System.out.println("式の形が正しくありません: " + e.toString());
		}
	}
}
