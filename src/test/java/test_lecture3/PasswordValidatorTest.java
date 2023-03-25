package test_lecture3;

import org.junit.Test;
import io.ylab.intensive.task_lecture3.password_validator.PasswordValidator;
import static org.assertj.core.api.Java6Assertions.assertThat;

public class PasswordValidatorTest {
    @Test
    public void testCorrectDataForValidator() {
        String login = "abcdaaa_";
        String password = "password1";
        String confirmPassword = "password1";
        assertThat(PasswordValidator.passwordValidator(login, password, confirmPassword)).isTrue();
    }

    @Test
    public void testWrongLoginForValidator() {
        String login = "aasvnas**фф";
        String password = "password";
        String confirmPassword = "password";
        assertThat(PasswordValidator.passwordValidator(login, password, confirmPassword)).isFalse();
    }

    @Test
    public void testWrongPasswordForValidator() {
        String login = "abcdaaa_";
        String password = "wrongЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫ*";
        String confirmPassword = "s";
        assertThat(PasswordValidator.passwordValidator(login, password, confirmPassword)).isFalse();
    }
}
