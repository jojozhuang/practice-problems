package johnny.problem.keyvaluestore.test;

import johnny.problem.keyvaluestore.KeyValueStore;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class KeyValueStoreTest {

    @Test
    public void tesKeyValueStore() {
        System.out.println("tesVersionedStore");

        /*
        PUT(#1) key1 = 5
        PUT(#2) key2 = 6
        GET key1 = 5
        GET key1(#1) = 5
        GET key2(#2) = 6
        PUT(#3) key1 = 7
        GET key1(#1) = 5
        GET key1(#2) = 5
        GET key1(#3) = 7
        GET key4 = <NULL>
        GET key1(#4) = 7
        */
        KeyValueStore kvs = new KeyValueStore();
        assertEquals(kvs.put("key1", "5"), 1);
        assertEquals(kvs.put("key2", "6"), 2);
        assertEquals(kvs.get("key1"), "5");
        assertEquals(kvs.get("key1", 1), "5");
        assertEquals(kvs.get("key2", 2), "6");
        assertEquals(kvs.put("key1", "7"), 3);
        assertEquals(kvs.get("key1", 1), "5");
        assertEquals(kvs.get("key1", 2), "5");
        assertEquals(kvs.get("key1", 3), "7");
        assertNull(kvs.get("key4"));
        assertEquals(kvs.get("key1", 8), "7");
        assertNull(kvs.get("key1", 0));
    }
}
