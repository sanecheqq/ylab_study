package test_lecture3.file_sort;

import org.junit.Test;
import io.ylab.intensive.task_lecture3.file_sort.ExternalMergeSort;
import io.ylab.intensive.task_lecture3.file_sort.Generator;
import io.ylab.intensive.task_lecture3.file_sort.Validator;
import java.io.File;
import java.io.IOException;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class FileSorterTest {
//    @Test
//    public void testSorterWithBigData() throws IOException {
//        File dataFile = new Generator().generate("src/test/java/test_lecture3/file_sort/data.txt", 10_000_000);
//        File sortedFile = new ExternalMergeSort().startSort(dataFile);
//
//        assertThat(new Validator(dataFile).isSorted()).isFalse();
//        assertThat(new Validator(sortedFile).isSorted()).isTrue();
//    }

    @Test
    public void testSorterWithSmallData() throws IOException {
        File dataFile = new Generator().generate("src/test/java/test_lecture3/file_sort/data_small.txt", 100);
        File sortedFile = new ExternalMergeSort().startSort(dataFile);

        assertThat(new Validator(dataFile).isSorted()).isFalse();
        assertThat(new Validator(sortedFile).isSorted()).isTrue();
    }
}
