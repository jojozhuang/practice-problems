package johnny.filesystem;

import org.junit.Test;

import java.util.List;
import java.util.function.Predicate;

import static org.junit.Assert.assertEquals;

public class FileSystemTest {

    @Test
    public void test() {
        System.out.println("FileSystem");
        FileSystem instance = new FileSystem();

        Predicate<Integer> greaterThanTen = (i) -> i > -1;
        List<String> result1 = ListUtil.buildList(new String[] {});
        assertEquals(result1, instance.ls("/", greaterThanTen));
        instance.mkdir("/a/b/c");
        instance.mkfile("/a/b/c", "d");
        instance.addContentToFile("/a/b/c/d","hello");
        List<String> result2 = ListUtil.buildList(new String[] {"a"});
        assertEquals(result2, instance.ls("/", greaterThanTen));
        assertEquals("hello", instance.readContentFromFile("/a/b/c/d"));
    }

    @Test
    public void test2() {
        System.out.println("FileSystem");
        FileSystem instance = new FileSystem();

        Predicate<Integer> greaterThanTen = (i) -> i > -1;

        List<String> result1 = ListUtil.buildList(new String[] {});
        assertEquals(result1, instance.ls("/", greaterThanTen));

        // create directory
        instance.mkdir("/johnny");
        instance.mkdir("/workspace");
        instance.mkdir("/github/algorithm/leetcode");
        List<String> result2 = ListUtil.buildList(new String[] {"github","johnny","workspace"});
        assertEquals(result2, instance.ls("/", greaterThanTen));

        // create file
        instance.mkfile("/workspace/dir1", "file1");
        instance.mkfile("/workspace/dir2", "file2");
        instance.mkfile("/workspace/dir2", "file3");
        List<String> result3 = ListUtil.buildList(new String[] {"dir1","dir2"});
        assertEquals(result3, instance.ls("/workspace", greaterThanTen));
        List<String> result4 = ListUtil.buildList(new String[] {"file2","file3"});
        assertEquals(result4, instance.ls("/workspace/dir2", greaterThanTen));

        // add and read content
        assertEquals(false, instance.addContentToFile("/workspace/dir2","hello"));
        assertEquals(false, instance.addContentToFile("/workspace/dir2/nofile","hello"));
        assertEquals(true, instance.addContentToFile("/workspace/dir2/file2","hello"));
        assertEquals("", instance.readContentFromFile("/workspace/dir2/nofile"));
        assertEquals("hello", instance.readContentFromFile("/workspace/dir2/file2"));
    }
}
