package task_lecture3.password_validator;

public class WrongPasswordException extends Throwable {
    public WrongPasswordException() {

    }

    public WrongPasswordException(String message) {
        super(message);
    }
}
