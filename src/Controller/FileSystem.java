package Controller;

import java.util.HashMap;
import java.util.Map;

public class FileSystem {

    private Map<String, FileEntry> files;
    private FAT fat;

    public FileSystem() {
        files = new HashMap<>();
        fat = new FAT();
    }

    public void mount() {
        files.put("/", new FileEntry("/", true));
        System.out.println("Disco montado.");
    }

    public void createDirectory(String path) {
        if (files.containsKey(path)) {
            System.out.println("El directorio ya existe.");
            return;
        }
        files.put(path, new FileEntry(path, true));
        System.out.println("Directorio creado: " + path);
    }

    public void createFile(String path, String content) {
        if (files.containsKey(path)) {
            System.out.println("El archivo ya existe.");
            return;
        }
        files.put(path, new FileEntry(path, false, content));
        fat.allocate(path, content);
        System.out.println("Archivo creado: " + path);
    }

    public void modifyFile(String path, String content) {
        if (!files.containsKey(path) || files.get(path).isDirectory()) {
            System.out.println("El archivo no existe o es un directorio.");
            return;
        }
        files.get(path).setContent(content);
        fat.allocate(path, content);
        System.out.println("Archivo modificado: " + path);
    }

    public void delete(String path) {
        if (!files.containsKey(path)) {
            System.out.println("El archivo o directorio no existe.");
            return;
        }
        files.remove(path);
        fat.deallocate(path);
        System.out.println("Archivo/Directorio eliminado: " + path);
    }

    public Map<String, FileEntry> getFiles() {
        return files;
    }
    
    public void list(String path) {
        System.out.println("Listando contenidos de: " + path);
        for (String key : files.keySet()) {
            if (key.startsWith(path) && !key.equals(path)) {
                System.out.println(key);
            }
        }
    }
}