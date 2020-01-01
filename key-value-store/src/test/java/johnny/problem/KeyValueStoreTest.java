package johnny.problem;

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
        assertEquals(1, kvs.put("key1", "5"));
        assertEquals(2, kvs.put("key2", "6"));
        assertEquals("5", kvs.get("key1"));
        assertEquals("5", kvs.get("key1", 1));
        assertEquals("6", kvs.get("key2", 2));
        assertEquals(3, kvs.put("key1", "7"));
        assertEquals("5", kvs.get("key1", 1));
        assertEquals("5", kvs.get("key1", 2));
        assertEquals("7", kvs.get("key1", 3));
        assertNull(kvs.get("key4"));
        assertEquals("7", kvs.get("key1", 8));
        assertNull(kvs.get("key1", 0));
    }
}
