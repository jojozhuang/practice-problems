package johnny.filesystem;

import java.util.HashMap;
import java.util.Map;

public class File {
    public String name;
    public boolean isFile;
    public Map<String, File> children;
    public String content;

    public File() {
        this.name = "";
        this.children = new HashMap<>();
        this.isFile = false;
        this.content = "";
    }
    public File(String name) {
        this.name = name;
        this.children = new HashMap<>();
        this.isFile = false;
        this.content = "";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isFile() {
        return isFile;
    }

    public void setFile(boolean file) {
        isFile = file;
    }

    public Map<String, File> getChildren() {
        return children;
    }

    public void setChildren(Map<String, File> children) {
        this.children = children;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


}
