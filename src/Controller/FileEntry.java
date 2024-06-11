package Controller;

public class FileEntry {

	@SuppressWarnings("unused")
    private String name;
    private boolean isDirectory;
    private String content;

    public FileEntry(String name, boolean isDirectory) {
        this.name = name;
        this.isDirectory = isDirectory;
        this.content = "";
    }

    public FileEntry(String name, boolean isDirectory, String content) {
        this.name = name;
        this.isDirectory = isDirectory;
        this.content = content;
    }

    public boolean isDirectory() {
        return isDirectory;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    
}
