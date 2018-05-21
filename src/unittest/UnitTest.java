package unittest;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.DynamicTest.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

import picocalculator.AbstractCalculatorFactory;
import picocalculator.Context;
import picocalculator.PicoCalculator;
import picocalculator.SimpleCalculatorFactory;
import picocalculator.exceptions.ParsingErrorException;
import picocalculator.expressions.AbstractParser;
import picocalculator.tokens.ParenthesesEndToken;
import picocalculator.tokens.ParenthesesStartToken;

class UnitTest {

    @Test
    void testNormal1() throws ParsingErrorException {
        testExecute("1+1", "[1, 1, +]", 2);
    }

    @Test
    void testException1() {
        testExecuteExceptionExpected("1++1", 3);
    }

    @Test
    void testException2() {
        testExecuteExceptionExpected("1+", 2);
    }

    @Test
    void testException3() {
        testExecuteExceptionExpected("1+(2", 4);
    }

    @Test
    void testException4() {
        testExecuteExceptionExpected("1+(2 *", 6);
    }

    @Test
    void testException5() {
        testExecuteExceptionExpected("9999999999", 10);
    }

    @TestFactory
    Iterable<DynamicTest> dynamicTestNormal() {
        return Arrays.asList(
                dynamicTest("マイナス値:        -1+3",       () -> { testExecute("-1+3",         "[-, 1, 3, +]", 2);}),
                dynamicTest("マイナス値:        3*-2",       () -> { testExecute("3*-2",         "[3, -, 2, *]", -6);}),
                // 四則演算正常系
                dynamicTest("足し算1つ:         1+2",        () -> { testExecute("1+2",         "[1, 2, +]", 3);}),
                dynamicTest("足し算2つ:         1+2+3",      () -> { testExecute("1+2+3",       "[1, 2, +, 3, +]", 6);}),
                dynamicTest("引き算1つ:         2-1",        () -> { testExecute("2-1",         "[2, 1, -]", 1);}),
                dynamicTest("引き算2つ:         3-2-1",      () -> { testExecute("3-2-1",       "[3, 2, -, 1, -]", 0);}),
                dynamicTest("掛け算1つ:         1*2",        () -> { testExecute("1*2",         "[1, 2, *]", 2);}),
                dynamicTest("掛け算2つ:         1*2*3",      () -> { testExecute("1*2*3",       "[1, 2, *, 3, *]", 6);}),
                dynamicTest("割り算1つ:         2/1",        () -> { testExecute("2/1",         "[2, 1, /]", 2);}),
                dynamicTest("割り算2つ:         8/2/2",      () -> { testExecute("8/2/2",       "[8, 2, /, 2, /]", 2);}),
                dynamicTest("足し算と掛け算:    1*2+3",      () -> { testExecute("1*2+3",       "[1, 2, *, 3, +]", 5);}),
                dynamicTest("引き算と割り算:    2/1-3",      () -> { testExecute("2/1-3",       "[2, 1, /, 3, -]", -1);}),
                dynamicTest("四則演算:          1+2*3-6/2",  () -> { testExecute("1+2*3-6/2",   "[1, 2, 3, *, +, 6, 2, /, -]", 4);}),
                dynamicTest("四則演算:          3+2*(3-6)/3",() -> { testExecute("3+2*(3-6)/3", "[3, 2, 3, 6, -, *, 3, /, +]", 1);}),
                // リテラル系
                dynamicTest("スペース:          1 + 2 + 3",  () -> { testExecute(" 1 + 2 + 3",  "[1, 2, +, 3, +]", 6);}),
                dynamicTest("最後にスペース:    1 + 2 + 3 ", () -> { testExecute(" 1 + 2 + 3 ", "[1, 2, +, 3, +]", 6);}),
                dynamicTest("最後に括弧:        1+(2+3)",    () -> { testExecute("1+(2+3)",     "[1, 2, 3, +, +]", 6);}),
                dynamicTest("LAST リテラルのみ: 1",          () -> { testExecute("1",           "[1]", 1);})
        );
    }

    private static AbstractCalculatorFactory _factory = null;

    @BeforeAll
    static void setup() {
        _factory = new SimpleCalculatorFactory();
        //_factory = new BigDecimalCalculatorFactory();
        //_factory = new DoubleCalculatorFactory();
        //_factory = new VariableBigDecimalCalculatorFactory();
    }

    void testExecute(String str, String rpn, int answer) throws ParsingErrorException {
        AbstractParser expr = _factory.createParser();
        Context context = _factory.createContext(str);
        int value = Integer.parseInt(expr.interpret(context).toString());
        assertEquals(answer, value);
        assertEquals(rpn, context.getRPN());
    }

    void testExecuteExceptionExpected(String str, int errorIndex) {
        AbstractParser<Integer> expr = _factory.createParser();
        Context<Integer> context = _factory.createContext(str);
        ParsingErrorException exception = assertThrows(ParsingErrorException.class, () -> {
            expr.interpret(context);
        });
        assertEquals(errorIndex, exception.getIndex());
    }

    @Test
    void testParenthesesStartTokenEvalute() {
        assertThrows(UnsupportedOperationException.class, () -> {
            new ParenthesesStartToken<Integer>(0, "(").evalute(0, 0);
        });
    }

    @Test
    void testParenthesesStartTokenGetValue() {
        assertThrows(UnsupportedOperationException.class, () -> {
            new ParenthesesStartToken<Integer>(0, "(").getValue();
        });
    }

    @Test
    void testParenthesesEndTokenEvalute() {
        assertThrows(UnsupportedOperationException.class, () -> {
            new ParenthesesEndToken<Integer>(0, "(").evalute(0, 0);
        });
    }

    @Test
    void testParenthesesEndTokenGetValue() {
        assertThrows(UnsupportedOperationException.class, () -> {
            new ParenthesesEndToken<Integer>(0, "(").getValue();
        });
    }

    /**
     * 標準入力に対し、文字列入力を行う
     */
    public class StandardInputSnatcher extends InputStream {

        private StringBuilder buffer = new StringBuilder();
        private String _crlf = System.getProperty("line.separator");

        /**
         * 文字列を入力する。改行は自動的に行う
         * @param str 入力文字列
         */
        public void inputln(String str) {
            buffer.append(str).append(_crlf);
        }

        @Override
        public int read() throws IOException {
            if (buffer.length() == 0) {
                return -1;
            }
            int result = buffer.charAt(0);
            buffer.deleteCharAt(0);
            return result;
        }
    }

    @Test
    void testMainDebug() throws IOException, InstantiationException, IllegalAccessException {
        StandardInputSnatcher in = new StandardInputSnatcher();
        in.inputln("1+1");
        in.inputln("1+");
        in.inputln(".");
        System.setIn(in);
        System.setOut(new PrintStream(new ByteArrayOutputStream()));
        PicoCalculator.main(new String[]{"--debug"});
        System.setIn(null);
        // TODO: ByteArrayOutputStreamの内容をチェックする必要があるが、取り急ぎ意図しない例外が発生しないことの確認のみ
    }
    @Test

    void testMainNormal() throws IOException, InstantiationException, IllegalAccessException {
        StandardInputSnatcher in = new StandardInputSnatcher();
        in.inputln("1+1");
        in.inputln("1+");
        in.inputln(".");
        System.setIn(in);
        System.setOut(new PrintStream(new ByteArrayOutputStream()));
        PicoCalculator.main(new String[]{});
        System.setIn(null);
        // TODO: ByteArrayOutputStreamの内容をチェックする必要があるが、取り急ぎ意図しない例外が発生しないことの確認のみ
    }
}
