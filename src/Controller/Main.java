package Controller;

public class Main {

	public static void main(String[] args) {
        FileSystem fileSystem = new FileSystem();
        fileSystem.mount();
        new FileSystemGUI(fileSystem);
    }
	
}
