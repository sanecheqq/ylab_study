package test_lecture3;

import org.junit.Test;
import io.ylab.intensive.task_lecture3.dated_map.DatedMap;
import io.ylab.intensive.task_lecture3.dated_map.DatedMapImpl;
import java.util.Date;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class DatedMapTest {

    @Test
    public void testPutGet() {
        DatedMap datedMap = new DatedMapImpl();
        String srcKey = "key1";
        String srcValue = "value1";
        String srcValue2 = "value2";

        datedMap.put(srcKey, srcValue);
        String receivedValue = datedMap.get(srcKey);

        datedMap.put(srcKey, srcValue2);
        String receivedValue2 = datedMap.get(srcKey);

        assertThat(receivedValue).isEqualTo(srcValue);
        assertThat(receivedValue2).isEqualTo(srcValue2);

    }

    @Test
    public void testInsertionDate() {
        DatedMap datedMap = new DatedMapImpl();
        String srcKey = "key";
        String imaginaryKey = "xxxxxxxx";
        String srcValue1 = "value1";
        String srcValue2 = "value2";

        datedMap.put(srcKey, srcValue1);
        Date dateValue1 = datedMap.getKeyLastInsertionDate(srcKey);
        wait100millis();
        datedMap.put(srcKey, srcValue2);
        Date dateValue2 = datedMap.getKeyLastInsertionDate(srcKey);
        Date dateValueNull = datedMap.getKeyLastInsertionDate(imaginaryKey);

        assertThat(dateValue1.getTime()).isNotEqualTo(dateValue2.getTime());
        assertThat(dateValueNull).isNull();

    }

    private void wait100millis() {
        try {
            Thread.sleep(100);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    @Test
    public void testContainsKeyAndRemove() {
        DatedMap datedMap = new DatedMapImpl();
        String srcKey = "key";
        String srcValue1 = "value1";

        datedMap.put(srcKey, srcValue1);
        boolean containsKeyBeforeRemove = datedMap.containsKey(srcKey);

        datedMap.remove(srcKey);
        boolean containsKeyAfterRemove = datedMap.containsKey(srcKey);

        assertThat(containsKeyBeforeRemove).isNotEqualTo(containsKeyAfterRemove);
    }
}
