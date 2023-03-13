package task_lecture2.complex_numbers;


import java.util.Objects;

public final class ComplexNumberImpl implements ComplexNumber {
    private final double real;
    private double imaginary;

    public ComplexNumberImpl(double real) {
        this.real = real;
    }

    public ComplexNumberImpl(double real, double imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }

    @Override
    public ComplexNumber add(ComplexNumber number) {
        double newReal = this.getReal() + number.getReal();
        double newImaginary = this.getImaginary() + number.getImaginary();
        return new ComplexNumberImpl(newReal, newImaginary);
    }

    @Override
    public ComplexNumber subtract(ComplexNumber number) {
        double newReal = this.getReal() - number.getReal();
        double newImaginary = this.getImaginary() - number.getImaginary();
        return new ComplexNumberImpl(newReal, newImaginary);
    }

    @Override
    public ComplexNumber multiply(ComplexNumber number) {
        double newReal = this.getReal() * number.getReal() - this.getImaginary() * number.getImaginary();
        double newImaginary = this.getImaginary() * number.getReal() + this.getReal() * number.getImaginary();
        return new ComplexNumberImpl(newReal, newImaginary);
    }

    @Override
    public double getAbsoluteValue() {
        return Math.sqrt(Math.pow(this.getReal(), 2) + Math.pow(this.getImaginary(), 2));
    }

    @Override
    public String toString() {
        return "ComplexNumberImpl{" +
                "real=" + getReal() +
                ", imaginary=" + getImaginary() +
                '}';
    }

    public double getReal() {
        return real;
    }

    public double getImaginary() {
        return imaginary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ComplexNumber that = (ComplexNumberImpl) o;
        return Double.compare(that.getReal(), getReal()) == 0 && Double.compare(that.getImaginary(), getImaginary()) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getReal(), getImaginary());
    }
}
