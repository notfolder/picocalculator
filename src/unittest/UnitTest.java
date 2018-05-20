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
        testExecute("1+1", 2);
    }

    @Test
    void testException1() {
        testExecuteExceptionExpected("1++1", 2);
    }

    @TestFactory
    Iterable<DynamicTest> dynamicTestNormal() {
        return Arrays.asList(
                dynamicTest("足し算1つ: 1+2", () -> { testExecute("1+2", 3);}),
                dynamicTest("足し算2つ: 1+2+3", () -> { testExecute("1+2+3", 6);}),
                dynamicTest("スペース: 1 + 2 + 3", () -> { testExecute(" 1 + 2 + 3", 6);}),
                dynamicTest("最後にスペース: 1 + 2 + 3 ", () -> { testExecute(" 1 + 2 + 3 ", 6);}),
                dynamicTest("LAST リテラルのみ: 1", () -> { testExecute("1", 1);})
        );
    }

    void testExecute(String str, int answer) throws ParsingErrorException {
        Context<Integer> context = new IntegerLexer(str);
        Expression<Integer> expr = new Expression<Integer>();
        int value = expr.interpret(context);
        assertEquals(answer, value);
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
