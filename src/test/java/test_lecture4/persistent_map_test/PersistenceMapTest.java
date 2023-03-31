package test_lecture4.persistent_map_test;

import io.ylab.intensive.lesson04.DbUtil;
import io.ylab.intensive.lesson04.persistentmap.PersistentMap;
import io.ylab.intensive.lesson04.persistentmap.PersistentMapImpl;

import javax.sql.DataSource;
import java.sql.SQLException;

public class PersistenceMapTest {
    public static void main(String[] args) throws SQLException {
        DataSource dataSource = initDb();
        PersistentMap persistentMap = new PersistentMapImpl(dataSource);
        persistentMap.init("map1");
        persistentMap.put("key1", "value1");
        persistentMap.put("key2", "value2");
        System.out.println("Contains key <k> in map1: " + persistentMap.containsKey("k"));
        System.out.println("Contains key <key1> in map1: " + persistentMap.containsKey("key1"));
        System.out.println("All keys of map1:");
        for (String key : persistentMap.getKeys())
            System.out.println("\tKey: " + key);

        persistentMap.init("map2");
        persistentMap.put("key1", "value1");
        persistentMap.put("key2", "value2");
        System.out.println("Value in <key2> map2: " + persistentMap.get("key2"));
        persistentMap.remove("xxx"); // remove nothing
        persistentMap.put("key2", "newValue2");
        System.out.println("New value in key2 map2: " + persistentMap.get("key2"));

        persistentMap.put("x", null);
        System.out.println("Contains key <x>: " + persistentMap.containsKey("x"));
        persistentMap.remove("x");
        System.out.println("Contains key <x> after remove: " + persistentMap.containsKey("x"));
        persistentMap.clear();
        persistentMap.init("map1");
        persistentMap.clear();
    }

    public static DataSource initDb() throws SQLException {
        String createMapTable = ""
                + "drop table if exists persistent_map; "
                + "CREATE TABLE if not exists persistent_map (\n"
                + "   map_name varchar,\n"
                + "   KEY varchar,\n"
                + "   value varchar\n"
                + ");";
        DataSource dataSource = DbUtil.buildDataSource();
        DbUtil.applyDdl(createMapTable, dataSource);
        return dataSource;
    }
}
