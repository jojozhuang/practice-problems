package johnny.filesystem2;

public class File extends BaseFile {
    private String content;
    private int size;

    public File(String name) {
        super(name, true);
        content = "";
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
        if (content == null || content.isEmpty()) {
            this.size = 0;
        } else {
            this.size = this.content.length();
        }
    }

    public void appendContent(String content) {
        if (content == null || content.isEmpty()) {
            return;
        }
        this.content += content;
        this.size = this.content.length();
    }

    @Override
    public int size() {
        return size;
    }
}
