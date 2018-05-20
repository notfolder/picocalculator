package unittest;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.DynamicTest.*;

import java.util.Arrays;

import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

import picocalculator.Context;
import picocalculator.IntegerLexer;
import picocalculator.exceptions.ParsingErrorException;
import picocalculator.expressions.Expression;

class UnitTest {

    @Test
    void testNormal1() throws ParsingErrorException {
        testExecute("1+1", "[1, 1, +]", 2);
    }

    @Test
    void testException1() {
        testExecuteExceptionExpected("1++1", 2);
    }

    @Test
    void testException2() {
        testExecuteExceptionExpected("1+", 2);
    }

    @TestFactory
    Iterable<DynamicTest> dynamicTestNormal() {
        return Arrays.asList(
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

    void testExecute(String str, String rpn, int answer) throws ParsingErrorException {
        Context<Integer> context = new IntegerLexer(str);
        Expression<Integer> expr = new Expression<Integer>();
        int value = expr.interpret(context);
        assertEquals(answer, value);
        assertEquals(rpn, context.getRPN());
    }

    void testExecuteExceptionExpected(String str, int errorIndex) {
        Context<Integer> context = new IntegerLexer(str);
        Expression<Integer> expr = new Expression<Integer>();
        ParsingErrorException exception = assertThrows(ParsingErrorException.class, () -> {
            expr.interpret(context);
        });
        assertEquals(errorIndex, exception.getIndex());
    }
}
