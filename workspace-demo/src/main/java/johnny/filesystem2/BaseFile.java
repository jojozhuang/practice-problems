package johnny.filesystem2;

public abstract class BaseFile {
    protected String name;
    protected boolean isFile;
    protected long created;
    protected long updated;

    public BaseFile(String name, boolean isFile) {
        this.name = name;
        this.isFile = isFile;
        this.created= System.currentTimeMillis();
        this.updated = System.currentTimeMillis();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getIsFile() {
        return isFile;
    }

    public void setIsFile(boolean isFile) {
        this.isFile = isFile;
    }

    public long getCreated() {
        return created;
    }

    public void setCreated(long created) {
        this.created = created;
    }

    public long getUpdated() {
        return updated;
    }

    public void setUpdated(long updated) {
        this.updated = updated;
    }

    public abstract int size();
}
