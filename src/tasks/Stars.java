package tasks;

import java.util.Scanner;

public class Stars {
    public static void main(String[] args) throws Exception {
        try (Scanner scanner = new Scanner(System.in)) {
            int n = scanner.nextInt();
            int m = scanner.nextInt();
            String template = scanner.hasNext() ? scanner.next() : "";
            printTemplate(n, m, template);
        }
    }

    public static void printTemplate(int n, int m, String template) {
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                System.out.print(template + " ");
            }
            System.out.println("");
        }
    }
}
