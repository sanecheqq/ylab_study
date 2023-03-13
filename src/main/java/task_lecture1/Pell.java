package task_lecture1;

import java.util.Scanner;

public class Pell {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int n = scanner.nextInt();
            int[] pellSequnce = new int[n + 1];
            if (n >= 1) {
                fillPellSequnce(pellSequnce);
            }
            System.out.println(pellSequnce[n]);
        }
    }

    public static void fillPellSequnce(int[] pellSequnce) {
        pellSequnce[1] = 1;
        for (int i = 2; i < pellSequnce.length; ++i) {
            pellSequnce[i] = 2*pellSequnce[i - 1] + pellSequnce[i - 2];
        }
    }
}
