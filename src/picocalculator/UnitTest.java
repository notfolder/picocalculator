package picocalculator;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

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
