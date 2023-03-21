package task_lecture3.password_validator;

public class PasswordValidator {
    public static boolean passwordValidator(String login, String password, String confirmPassword) {
        try {
            checkLoginSymbols(login);
            checkLoginSize(login);
            checkPasswordSymbols(password);
            checkPasswordSize(password);
            checkEqualsOfPasswords(password, confirmPassword);
            return true;
        } catch (WrongLoginException | WrongPasswordException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    private static void checkLoginSymbols(String login) throws WrongLoginException {
        if (!login.matches("^[a-zA-Z0-9_]+$")) {
            throw new WrongLoginException("Логин содержит недопустимые символы");
        }
    }

    private static void checkLoginSize(String login) throws WrongLoginException {
        if (login.length() >= 20) {
            throw new WrongLoginException("Логин слишком длинный");
        }
    }
    private static void checkPasswordSymbols(String password) throws WrongPasswordException {
        if (!password.matches("^[a-zA-Z0-9_]+$")) {
            throw new WrongPasswordException("Пароль содержит недопустимые символы");
        }
    }

    private static void checkPasswordSize(String password) throws WrongPasswordException {
        if (password.length() >= 20) {
            throw new WrongPasswordException("Пароль слишком длинный");
        }
    }

    private static void checkEqualsOfPasswords(String password, String confirmPassword) throws WrongPasswordException {
        if (!password.equals(confirmPassword)) {
            throw new WrongPasswordException("Пароль и подтверждение не совпадают");
        }
    }
}
