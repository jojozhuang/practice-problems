package johnny.filesystem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

public class FileSystem {
    File root;
    //private SizeFilterPredicate sfp;

    public FileSystem() {
        this.root = new File("");
    }

    // create directory with the given absolute path, eg. /a/b/c/d
    public File mkdir(String path) {
        String[] dirs = path.split("/");
        File node = root;
        for (String dir : dirs) {
            if (dir.isEmpty()) {
                continue;
            }
            if (!node.children.containsKey(dir)) {
                node.children.put(dir, new File());
            }
            node = node.children.get(dir);
        }

        return node;
    }

    // create file with path and name
    public void mkfile(String path, String name) {
        File dir = mkdir(path);
        File newFile = new File(name);
        newFile.isFile = true;
        dir.children.put(name, newFile);
    }

    // add content to existing file, path contains name
    public boolean addContentToFile(String path, String content) {
        String[] dirs = path.split("/");
        File node = root;
        node = search(node, dirs);
        if (node == null || node.isFile == false) {
            System.out.println("File is not found:"+path);
            return false;
        }
        node.content += content;
        return true;
    }

    // read file content
    public String readContentFromFile(String path) {
        String[] dirs = path.split("/");
        File node = root;
        node = search(node, dirs);
        if (node == null || node.isFile == false) {
            System.out.println("File is not found:"+path);
            return "";
        }
        return node.content;
    }

    // find files with the given directory
    public List<String> ls(String path, Predicate<Integer> predicate) {
        List<String> list = new ArrayList<>();
        String[] dirs = path.split("/");
        File node = root;
        node = search(node, dirs);
        if (node == null || node.isFile == true) {
            System.out.println("Directory is not found:"+path);
            return list;
        }

        for (String str : node.children.keySet()) {
            if (predicate.test(node.children.get(str).size)) {
                list.add(str);
            }
        }

        Collections.sort(list);

        return list;
    }

    private File search(File node, String[] dirs) {
        for (String dir : dirs) {
            if (dir.isEmpty()) {
                continue;
            }
            if (!node.children.containsKey(dir)) {
                return null;
            }
            node = node.children.get(dir);
        }

        return node;
    }
}
