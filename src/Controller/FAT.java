package Controller;

import java.util.HashMap;
import java.util.Map;

public class FAT {

	private Map<String, String> allocationTable;

    public FAT() {
        allocationTable = new HashMap<>();
    }

    public void allocate(String path, String content) {
        allocationTable.put(path, content);
        System.out.println("Espacio asignado para: " + path);
    }

    public void deallocate(String path) {
        allocationTable.remove(path);
        System.out.println("Espacio liberado para: " + path);
    }

    public String getContent(String path) {
        return allocationTable.get(path);
    }
    
}
