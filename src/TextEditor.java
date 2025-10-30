import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class TextEditor implements ActionListener {
    // Declaring properties of Text Editor
    // Whole window of the Text Editor
    JFrame frame;

    // Menu Section of Text Editor
    JMenuBar menuBar;

    // Subsections of the Menu Bar
    JMenu file, edit;

    // Menu items for the File Menu
    JMenuItem newFile, openFile, saveFile;

    // Menu items for the Edit Menu
    JMenuItem cut, copy, paste, selectAll, close;

    // Text Area for typing anything
    JTextArea textArea;

    TextEditor(){
        // Initialize a frame for the Text Editor Window
        frame = new JFrame();

        // Initialize text area
        textArea = new JTextArea();

        // Initialize Menu bar
        menuBar = new JMenuBar();

        // Initialize menus
        file = new JMenu("File");
        edit = new JMenu("Edit");

        // Initialize file menu items
        newFile = new JMenuItem("New Window");
        openFile = new JMenuItem("Open File");
        saveFile = new JMenuItem("Save File");

        // Add action listener to file menu items
        newFile.addActionListener(this);
        openFile.addActionListener(this);
        saveFile.addActionListener(this);

        // Add menu items to file menu
        file.add(newFile);
        file.add(openFile);
        file.add(saveFile);

        // Initialize edit menu items
        cut = new JMenuItem("Cut");
        copy = new JMenuItem("Copy");
        paste = new JMenuItem("Paste");
        selectAll = new JMenuItem("Select All");
        close = new JMenuItem("Close");

        // Add action listener to edit menu items
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectAll.addActionListener(this);
        close.addActionListener(this);

        // Add menu items to edit menu
        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectAll);
        edit.add(close);

        // Add menus to Menu bar
        menuBar.add(file);
        menuBar.add(edit);

        // Create a Content Pane
        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(5, 5, 5,5));
        panel.setLayout(new BorderLayout(0, 0));

        // Add the text area to panel
        panel.add(textArea, BorderLayout.CENTER);

        // Create Scroll Pane
        JScrollPane scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        // Add Scroll Pane to Panel
        panel.add(scrollPane);

        // Add panel to frame
        frame.add(panel);

        
        // Set Menu bar to frame
        frame.setJMenuBar(menuBar);

        // Set Dimension for the frame
        frame.setBounds(420, 150, 500, 550);
        frame.setTitle("Local Text Editor");
        frame.setVisible(true);
        frame.setLayout(null);

    }

    @Override
    public void actionPerformed(ActionEvent actionEvent){
        // Action Performed for the file menu items
        if(actionEvent.getSource() == newFile){
            // Perform new File operation
            TextEditor newTextEditor = new TextEditor();
        }
        if(actionEvent.getSource() == openFile){
            // Open a file chooser
            JFileChooser fileChooser = new JFileChooser("C:");
            int chooseOption = fileChooser.showOpenDialog(null);
            // if we have clicked on open button
            if(chooseOption == JFileChooser.APPROVE_OPTION){
                // Getting the selected file
                File file = fileChooser.getSelectedFile();
                // Get the path of the choosen file
                String filePath = file.getPath();
                try{
                    // Initialize file reader
                    FileReader fileReader = new FileReader(filePath);

                    // Initialize the buffer reader
                    BufferedReader bufferedReader = new BufferedReader(fileReader);
                    String intermediate = "";
                    String output = "";

                    // Read contents of file line by line
                    while((intermediate = bufferedReader.readLine()) != null){
                        output += intermediate+"\n";
                    }

                    // Set the output to text area
                    textArea.setText(output);
                }
                catch (FileNotFoundException e){
                    e.printStackTrace();
                }
                catch (IOException e){
                    e.printStackTrace();
                }
            }

        }

        if(actionEvent.getSource() == saveFile){

            //Initialize a file picker
            JFileChooser fileChooser = new JFileChooser("C:");
            // Get choose option from file chooser
            int chooseOption = fileChooser.showSaveDialog(null);
            // Check if we click on save button
            if(chooseOption == JFileChooser.APPROVE_OPTION){
                // Create a new file with chosen directory path and file name
                File file = new File(fileChooser.getSelectedFile().getAbsolutePath()+".txt");
                try{

                    //Initialize file write to write the copied contents from text area
                    FileWriter fileWriter = new FileWriter(file);
                    //Initialize Buffer Writer
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                    // Write contents of text area
                    textArea.write(bufferedWriter);
                    bufferedWriter.close();

                }
                catch (IOException e){
                    e.printStackTrace();
                }
            }


        }

        // Action Performed for the edit menu items
        if(actionEvent.getSource() == cut){
            // Perform cut operation
            textArea.cut();
        }

        if(actionEvent.getSource() == copy){
            // Perform copy operation
            textArea.copy();

        }

        if(actionEvent.getSource() == paste){
            // Perform paste operation
            textArea.paste();

        }

        if(actionEvent.getSource() == selectAll){
            // Perform select all operation
            textArea.selectAll();

        }

        if(actionEvent.getSource() == close){
            // close the text editor
            System.exit(0);

        }


    }

    public static void main(String[] args) {

        TextEditor textEditor = new TextEditor();

    }
}