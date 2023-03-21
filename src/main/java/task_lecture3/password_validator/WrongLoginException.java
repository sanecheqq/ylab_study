package task_lecture3.password_validator;

public class WrongLoginException extends Throwable{
    public WrongLoginException() {
    }
    public WrongLoginException(String message) {
        super(message);
    }

}
