package johnny.filesystem2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class FileSystem {
    Directory root;

    public FileSystem() {
        this.root = new Directory("");
    }

    // create directory with the given absolute path, eg. /a/b/c/d
    public Directory mkdir(String path) {
        String[] dirs = path.split("/");
        Directory node = root;
        for (String dir : dirs) {
            if (dir.isEmpty()) {
                continue;
            }
            if (!node.containsDir(dir)) {
                node.createFile(new Directory(dir));
            }
            node = (Directory)node.getDir(dir);
        }

        return node;
    }

    // create file with path and name
    public void mkfile(String path, String name) {
        Directory dir = mkdir(path);
        dir.createFile(new File(name));
    }

    // add content to existing file, path contains name
    public boolean addContentToFile(String path, String content) {
        String[] dirs = path.split("/");
        String[] dir = Arrays.copyOf(dirs, dirs.length - 1);
        String fileName = dirs[dirs.length - 1];
        Directory node = root;
        node = search(node, dir);
        if (node == null || !node.containsFile(fileName)) {
            System.out.println("File is not found:"+path);
            return false;
        }
        File file = (File)node.getFile(fileName);
        file.setContent(content);
        return true;
    }

    // read file content
    public String readContentFromFile(String path) {
        String[] dirs = path.split("/");
        String[] dir = Arrays.copyOf(dirs, dirs.length - 1);
        String fileName = dirs[dirs.length - 1];
        Directory node = root;
        node = search(node, dir);
        if (node == null || !node.containsFile(fileName)) {
            System.out.println("File is not found:"+path);
            return "";
        }
        File file = (File)node.getFile(fileName);
        return file.getContent();
    }

    // find files with the given directory
    public List<String> ls(String path) {
        List<String> list = new ArrayList<>();
        String[] dirs = path.split("/");
        Directory node = root;
        node = search(node, dirs);
        if (node == null) {
            System.out.println("Directory is not found:"+path);
            return list;
        }

        for (BaseFile bf : node.getFiles()) {
            list.add(bf.name);
        }
        Collections.sort(list);

        return list;
    }

    private Directory search(Directory node, String[] dirs) {
        for (String dir : dirs) {
            if (dir.isEmpty()) {
                continue;
            }
            if (!node.containsDir(dir)) {
                return null;
            }
            node = (Directory)node.getDir(dir);
        }

        return node;
    }
}
