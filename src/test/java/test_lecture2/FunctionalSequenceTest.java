package test_lecture2;


import task_lecture2.sequences.Sequences;
import task_lecture2.sequences.SequencesImpl;

public class FunctionalSequenceTest {
    public static void main(String[] args) {
        Sequences sequencesGenerator = new SequencesImpl();

        sequencesGenerator.sequenceA(10);
        sequencesGenerator.sequenceB(10);
        sequencesGenerator.sequenceC(10);
        sequencesGenerator.sequenceD(10);
        sequencesGenerator.sequenceE(10);
        sequencesGenerator.sequenceF(10);
        sequencesGenerator.sequenceG(10);
        sequencesGenerator.sequenceH(10);
        sequencesGenerator.sequenceI(10);
        sequencesGenerator.sequenceJ(10);
    }
}
