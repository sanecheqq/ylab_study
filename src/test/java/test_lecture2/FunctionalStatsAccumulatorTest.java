package test_lecture2;

import org.junit.Test;
import io.ylab.intensive.task_lecture2.stats_accumulator.StatsAccumulator;
import io.ylab.intensive.task_lecture2.stats_accumulator.StatsAccumulatorImpl;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class FunctionalStatsAccumulatorTest {

    @Test
    public void testAccumulatorMethods() {
        StatsAccumulator accumulator = new StatsAccumulatorImpl();

        accumulator.add(2);
        accumulator.add(3);
        assertThat(accumulator.getAvg()).isEqualTo(2.5);
        accumulator.add(-1);

        assertThat(accumulator.getCount()).isEqualTo(3);
        assertThat(accumulator.getMax()).isEqualTo(3);
        assertThat(accumulator.getMin()).isEqualTo(-1);
        assertThat(accumulator.getAvg()).isEqualTo(4./3.);
    }
}
