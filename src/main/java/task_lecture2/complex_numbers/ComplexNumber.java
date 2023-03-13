package task_lecture2.complex_numbers;

public interface ComplexNumber {
    /**
     * Adds a passed complex number to the called object.
     * @param number complex number to be added
     * @return the result sum of the two complex numbers as new ComplexNumber
     */
    ComplexNumber add(ComplexNumber number);

    /**
     * Subtracts a passed complex number from the called object.
     * @param number complex number to be subtracted
     * @return the result subtraction of the two complex numbers as new ComplexNumber
     */
    ComplexNumber subtract(ComplexNumber number);

    /**
     * Multiplies a passed complex number with the called object.
     * @param number complex number to be multiplied
     * @return the result multiplication of the two complex numbers as new ComplexNumber.
     */
    ComplexNumber multiply(ComplexNumber number);

    /**
     * Calculates an absolute value of a complex number.
     * @return the absolute value of the complex number.
     */
    double getAbsoluteValue();

    /**
     * Converts the complex number to a String object.
     * @return string representation of a complex number.
     */
    String toString();

    /**
     * @return a real part of the complex number.
     */
    double getReal();

    /**
     * @return an imaginary part of the complex number.
     */
    double getImaginary();

    /**
     * Compares the called Complex Number with a passed Object by fields "real" and "imaginary".
     * @return true if objects are equals, false otherwise.
     */
    boolean equals(Object o);
}
