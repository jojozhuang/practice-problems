package johnny.filesystem2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Directory extends BaseFile {
    protected Map<String, BaseFile> files;

    public Directory(String name) {
        super(name, false);
        files = new HashMap<>();
    }

    @Override
    public int size() {
        int size = 0;
        for (BaseFile bf : files.values()) {
            size += bf.size();
        }
        return size;
    }

    public int numberOfFiles() {
        int count = 0;
        for (BaseFile bf : files.values()) {
            if (bf instanceof Directory) {
                //count++; // Directory counts as a file
                Directory d = (Directory)bf;
                count += d.numberOfFiles();
            } else if (bf instanceof File) {
                count++;
            }
        }
        return count;
    }

    public boolean deleteFile(BaseFile bf) {
        if (bf == null) {
            return true;
        }
        if (!files.containsKey(bf.getName())) {
            return false;
        }

        files.remove(bf.getName());
        return true;
    }

    public void createFile(BaseFile bf) {
        if (bf == null) {
            return;
        }
        files.put(bf.getName(), bf);
    }

    public BaseFile getDir(String name) {
        if (files.containsKey(name) && !files.get(name).isFile) {
            return files.get(name);
        } else {
            return null;
        }
    }

    public BaseFile getFile(String name) {
        if (files.containsKey(name) && files.get(name).isFile) {
            return files.get(name);
        } else {
            return null;
        }
    }

    public boolean containsDir(String name) {
        return files.containsKey(name) && !files.get(name).isFile;
    }

    public boolean containsFile(String name) {
        return files.containsKey(name) && files.get(name).isFile;
    }

    protected List<BaseFile> getFiles() {
        List<BaseFile> list = new ArrayList<>(files.values());
        return list;
    }
}
