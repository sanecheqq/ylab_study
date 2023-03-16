package task_lecture2.snils_validator;

public class SnilsValidatorImpl implements SnilsValidator {
    @Override
    public boolean validate(String snils) {
        try {
            snils = checkStringCorrectnessAndDeleteSpaces(snils);
            int sumOfMultiplications = calculateSumOfMultiplications(snils);
            int controlNumber = calculateControlNumber(sumOfMultiplications);
            int expectedControlNumber = calculateExpectedControlNumber(snils);

            compareControlNumbers(controlNumber, expectedControlNumber);
        } catch (InvalidSnilsSyntax e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    private String checkStringCorrectnessAndDeleteSpaces(String snils) throws InvalidSnilsSyntax {
        if (snils == null) {
            throw new InvalidSnilsSyntax("Snils is can not be null");
        }
        snils = snils.replaceAll(" ", "");
        if (snils.length() != 11) {
            throw new InvalidSnilsSyntax("Snils must contain only 11 digits");
        }
        return snils;
    }

    private int calculateSumOfMultiplications(String snils) throws InvalidSnilsSyntax {
        int sumOfMultiplications = 0;
        for (int i = 0; i < 9; i++) {
            char digit = snils.charAt(i);
            sumOfMultiplications += calculateMultiplication(digit, i);
        }
        return sumOfMultiplications;
    }

    private int calculateMultiplication(char digit, int position) throws InvalidSnilsSyntax {
        if (Character.isDigit(digit)) {
            int intDigit = digit - '0';
            return intDigit * (9 - position);
        } else {
            throw new InvalidSnilsSyntax("Snils can not contain any symbols besides digits");
        }
    }

    private int calculateControlNumber(int sumOfMultiplications) {
        int controlNumber;
        if (sumOfMultiplications < 100) {
            controlNumber = sumOfMultiplications;
        } else if (sumOfMultiplications == 100) {
            controlNumber = 0;
        } else {
            int remainder = sumOfMultiplications % 101;
            controlNumber = (remainder == 100 ? 0 : remainder);
        }
        return controlNumber;
    }

     private int calculateExpectedControlNumber(String snils) throws InvalidSnilsSyntax {
         if (Character.isDigit(snils.charAt(9)) && Character.isDigit(snils.charAt(10))) {
            return Integer.parseInt(snils.substring(9, 10+1));
         } else {
             throw new InvalidSnilsSyntax("Snils control number can not contain any symbols besides digits");
         }
     }

    private void compareControlNumbers(int leftNumber, int rightNumber) throws InvalidSnilsSyntax {
        if (leftNumber!= rightNumber) {
            throw new InvalidSnilsSyntax("Snils control numbers must be equal");
        }
    }
}
