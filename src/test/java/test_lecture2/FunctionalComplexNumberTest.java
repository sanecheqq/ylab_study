package test_lecture2;

import io.ylab.intensive.task_lecture2.complex_numbers.ComplexNumber;
import io.ylab.intensive.task_lecture2.complex_numbers.ComplexNumberImpl;
import org.junit.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class FunctionalComplexNumberTest {

    @Test
    public void testAddAndSubtract() {
        ComplexNumber num = new ComplexNumberImpl(1, 2);
        ComplexNumber numToAdd = new ComplexNumberImpl(0.5, 0.5);
        ComplexNumber numToSubtract = numToAdd;

        ComplexNumber addResult = num.add(numToAdd);
        ComplexNumber subtractResult = addResult.subtract(numToSubtract);

        assertThat(num)
                .isEqualTo(subtractResult)
                .isNotEqualTo(addResult);

        ComplexNumber real = new ComplexNumberImpl(5);
        ComplexNumber numToAdd2 = new ComplexNumberImpl(2,2);

        ComplexNumber addResult2 = real.add(numToAdd2);
        ComplexNumber expectedResult = new ComplexNumberImpl(7, 2);

        assertThat(addResult2).isEqualTo(expectedResult);
    }

    @Test
    public void testMultiply() {
        ComplexNumber num = new ComplexNumberImpl(1, 2);
        ComplexNumber numToMultiply = new ComplexNumberImpl(5, 0.5);

        ComplexNumber multiplyResult = num.multiply(numToMultiply);
        ComplexNumber expectedMultiplyValue = new ComplexNumberImpl(4, 10.5);

        assertThat(multiplyResult).isEqualTo(expectedMultiplyValue);
    }

    @Test
    public void testGettingAbsoluteValue() {
        ComplexNumber num = new ComplexNumberImpl(6, 8);

        double absValue = num.getAbsoluteValue();
        double expectedAbsValue = Math.sqrt(6 * 6 + 8 * 8);

        assertThat(absValue).isEqualTo(expectedAbsValue);
    }

    @Test
    public void testToString() {
        ComplexNumber num1 = new ComplexNumberImpl(555);
        ComplexNumber num2 = new ComplexNumberImpl(666, 777);
        System.out.println(num1);
        System.out.println(num2);

        assertThat(num1.toString()).isNotEqualTo(num2.toString());
    }
}
