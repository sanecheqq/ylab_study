package task_lecture2.sequences;

public class SequencesImpl implements Sequences {
    @Override
    public void sequenceA(int n) {
        printSequenceName("A: ");
        for (int i = 1; i <= n; ++i) {
            printSequenceElement(i*2);
            printCommaAndEnterIfNeeded(i, n);
        }
    }

    @Override
    public void sequenceB(int n) {
        printSequenceName("B: ");
        for (int i = 1; i <= n; ++i) {
            printSequenceElement(i*2 - 1);
            printCommaAndEnterIfNeeded(i, n);
        }
    }

    @Override
    public void sequenceC(int n) {
        printSequenceName("C: ");
        for (int i = 1; i <= n; ++i) {
            printSequenceElement((int) Math.pow(i, 2));
            printCommaAndEnterIfNeeded(i, n);
        }
    }

    @Override
    public void sequenceD(int n) {
        printSequenceName("D: ");
        for (int i = 1; i <= n; ++i) {
            printSequenceElement((int) Math.pow(i, 3));
            printCommaAndEnterIfNeeded(i, n);
        }
    }

    @Override
    public void sequenceE(int n) {
        printSequenceName("E: ");
        for (int i = 1; i <= n; ++i) {
            printSequenceElement((int) Math.pow(-1, i));
            printCommaAndEnterIfNeeded(i, n);
        }
    }

    @Override
    public void sequenceF(int n) {
        printSequenceName("F: ");
        for (int i = 1; i <= n; ++i) {
            printSequenceElement((int) (i * Math.pow(-1, i - 1)));
            printCommaAndEnterIfNeeded(i, n);
        }
    }

    @Override
    public void sequenceG(int n) {
        printSequenceName("G: ");
        for (int i = 1; i <= n; ++i) {
            printSequenceElement( (int) (Math.pow(i, 2) * Math.pow(-1, i - 1)));
            printCommaAndEnterIfNeeded(i, n);
        }
    }

    @Override
    public void sequenceH(int n) {
        printSequenceName("H: ");
        for (int i = 1; i <= n; ++i) {
            printSequenceElement((int) (Math.pow(0, (i+1) % 2) * (i+1) / 2));
            printCommaAndEnterIfNeeded(i, n);
        }
    }

    @Override
    public void sequenceI(int n) {
        printSequenceName("I: ");
        int prevElement = 1;
        for (int i = 1; i <= n; ++i) {
            int currentElement = prevElement * i;
            prevElement = currentElement;

            printSequenceElement(currentElement);
            printCommaAndEnterIfNeeded(i, n);
        }
    }

    @Override
    public void sequenceJ(int n) {
        printSequenceName("J: ");
        int prevElement = 0;
        for (int i = 1; i <= n; ++i) {
            int currentElement = prevElement + i;
            prevElement = currentElement;

            printSequenceElement(currentElement);
            printCommaAndEnterIfNeeded(i, n);
        }
    }

    private void printCommaAndEnterIfNeeded(int i, int n) {
        if (i < n) {
            System.out.print(", ");
        } else {
            System.out.println();
        }
    }

    private void printSequenceElement(int element) {
        System.out.print(element);
    }

    private void printSequenceName(String name) {
        System.out.print(name);
    }
}
