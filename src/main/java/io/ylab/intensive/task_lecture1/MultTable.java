package io.ylab.intensive.task_lecture1;

public class MultTable {
    public static void main(String[] args) {
        for (int i = 1; i < 10; ++i) {
            for (int j = 1; j < 10; ++j) {
                System.out.format("%d x %d = %d\n", i, j, i*j);
            }
        }
    }
}
