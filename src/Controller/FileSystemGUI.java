package Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FileSystemGUI {

	private FileSystem fileSystem;
    private JFrame frame;
    private JTextArea displayArea;
    private JTextField pathField;
    private JTextField contentField;

    public FileSystemGUI(FileSystem fileSystem) {
        this.fileSystem = fileSystem;
        createGUI();
    }

    private void createGUI() {
        frame = new JFrame("Simulación de Sistema de Archivos FAT");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        displayArea = new JTextArea();
        displayArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(displayArea);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(3, 2));

        pathField = new JTextField();
        contentField = new JTextField();

        JButton listButton = new JButton("Listar");
        listButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listDirectory();
            }
        });

        JButton createFileButton = new JButton("Crear Archivo");
        createFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createFile();
            }
        });

        JButton createDirButton = new JButton("Crear Directorio");
        createDirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createDirectory();
            }
        });

        JButton modifyFileButton = new JButton("Modificar Archivo");
        modifyFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modifyFile();
            }
        });

        JButton deleteButton = new JButton("Eliminar");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteEntry();
            }
        });

        inputPanel.add(new JLabel("Ruta:"));
        inputPanel.add(pathField);
        inputPanel.add(new JLabel("Contenido:"));
        inputPanel.add(contentField);
        inputPanel.add(createFileButton);
        inputPanel.add(createDirButton);
        inputPanel.add(modifyFileButton);
        inputPanel.add(deleteButton);
        inputPanel.add(listButton);

        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(inputPanel, BorderLayout.SOUTH);

        frame.add(panel);
        frame.setVisible(true);
    }

    private void listDirectory() {
        String path = pathField.getText();
        displayArea.setText("");
        for (String key : fileSystem.getFiles().keySet()) {
            if (key.startsWith(path) && !key.equals(path)) {
                displayArea.append(key + "\n");
            }
        }
    }

    private void createFile() {
        String path = pathField.getText();
        String content = contentField.getText();
        fileSystem.createFile(path, content);
        listDirectory();
    }

    private void createDirectory() {
        String path = pathField.getText();
        fileSystem.createDirectory(path);
        listDirectory();
    }

    private void modifyFile() {
        String path = pathField.getText();
        String content = contentField.getText();
        fileSystem.modifyFile(path, content);
        listDirectory();
    }

    private void deleteEntry() {
        String path = pathField.getText();
        fileSystem.delete(path);
        listDirectory();
    }
    
}
