package test_lecture2;

import org.junit.Test;
import io.ylab.intensive.task_lecture2.snils_validator.SnilsValidator;
import io.ylab.intensive.task_lecture2.snils_validator.SnilsValidatorImpl;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class FunctionalSnilsValidatorTest {

    @Test
    public void testValidator() {
        SnilsValidator snilsValidator = new SnilsValidatorImpl();
        String[] validSnilses = {"98306342729", "96754707977", "92508922420", "28684834443", "67411292390",
                                "594 867 437 74", "526 870 810 10", "919 002 765 97"};
        String[] invalidSnilses = {"asdb21", "abcdefghijk","11111111111", "01010101999", "", null, "111111111 1111111 11111"};
        for (String snils : validSnilses) {
            assertThat(snilsValidator.validate(snils)).isTrue();
        }
        for (String snils : invalidSnilses) {
            assertThat(snilsValidator.validate(snils)).isFalse();
        }
    }
}
