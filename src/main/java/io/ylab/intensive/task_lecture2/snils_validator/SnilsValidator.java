package io.ylab.intensive.task_lecture2.snils_validator;

public interface SnilsValidator {

    /**
     * Checks, that the given snils is valid.
     * @param snils the snils in String representation to be checked.
     * @return true, if the given snils is valid, false otherwise.
     */
    boolean validate(String snils);
}
