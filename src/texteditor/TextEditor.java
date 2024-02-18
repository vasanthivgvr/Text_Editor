
package texteditor;

import java.awt.BorderLayout;
import java.awt.*;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class TextEditor implements ActionListener{
    //Create Frame
    JFrame frame;
    //Create Menus
    JMenu file,edit,more;
    //create MenuBar
    JMenuBar menuBar;
    // initialize Menu Items
    // for File Menu
    JMenuItem newFile,openFile,saveFile;
    // for Edit Menu
    JMenuItem cut, copy, paste, selectAll, close;
   // JMenuItem darkMode;
    //crateing dark Mode button 
    JButton darkModeButton;
    // Decleare the TextArea
    JTextArea textArea;
    
    // adding features like fonts and size
    JMenuItem increaseFontSize, changeFontStyle; // New menu items for font operations
    Font defaultFont = new Font("Arial", Font.PLAIN, 12); // Default font
    
    //DarMode initialize
    boolean darkModeEnabled = false;
    
    // create constructor
    TextEditor(){
        // initialize JFrame
        frame = new JFrame();
        
        //initialize MenuBar
        menuBar = new JMenuBar();
        
        // initialize Text Area
        textArea = new JTextArea();
        
        // Initialize the Menus with its names as string
        file = new JMenu("File");
        edit = new JMenu("Edit");
        more = new JMenu("Format");
        
        // adding dark More menu Item  to more menu 
//        darkMode = new JMenuItem("Dark Mode");
//        darkMode.addActionListener(this);
//        more.add(darkMode);

        // Create JButton for dark mode
        darkModeButton = new JButton("Dark Mode");
        darkModeButton.addActionListener(this);

        // Add button to a panel to mimic direct addition to menu bar
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(darkModeButton);
        buttonPanel.setOpaque(false); // Make the panel transparent
        
        
        // initialize MenuItems
        newFile = new JMenuItem("New Window");
        openFile = new JMenuItem("Open File");
        saveFile = new JMenuItem("Save File");
        
        newFile.addActionListener(this);
        openFile.addActionListener(this);
        saveFile.addActionListener(this);
        
        // add these items into the file Menu
        file.add(newFile);
        file.add(openFile);
        file.add(saveFile);
        
        // initailize edit Menu Items
        cut = new JMenuItem("Cut");
        paste = new JMenuItem("Paste");
        copy = new JMenuItem("Copy");
        selectAll = new JMenuItem("Select All");
        close = new JMenuItem("Close");
        
        cut.addActionListener(this);
        paste.addActionListener(this);
        copy.addActionListener(this);
        selectAll.addActionListener(this);
        close.addActionListener(this);
        
        // add these items into the Edit Menu
        edit.add(copy);
        edit.add(paste);
        edit.add(cut);
        edit.add(selectAll);
        edit.add(close);
        
        // adding font size and style 
        increaseFontSize = new JMenuItem("Increase Font Size");
        increaseFontSize.addActionListener(this);
        changeFontStyle = new JMenuItem("Change Font Style");
        changeFontStyle.addActionListener(this);
        
        more.add(increaseFontSize);
        more.add(changeFontStyle);
          
        // add Menus to the MenuBar
        menuBar.add(file);
        menuBar.add(edit);
        menuBar.add(more);
        menuBar.add(Box.createHorizontalGlue()); // Pushes the button to the right
        menuBar.add(buttonPanel);
        
        // add MenuBar to the Frame
        frame.setJMenuBar(menuBar);
        
        // create content panel
        JPanel  panel = new JPanel();
        panel.setBorder(new EmptyBorder(5,5,5,5));
        panel.setLayout((new BorderLayout(0,0)));
        // add text Area to panel
        panel.add(textArea, BorderLayout.CENTER);
        // Create Scroll pane
        JScrollPane scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        // ADD Scroll pane to panel
        panel.add(scrollPane);
        // add panel to frame
        frame.add(panel);
        
        //add Text Area to the Frame
        //frame.add(textArea);
        
        // set the size of the frome
        frame.setBounds(0, 0, 700, 500);
        frame.setTitle("Text Editor By Asif");
        
        // Center the frame on the screen
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int xPos = (screenSize.width - frame.getWidth()) / 2;
        int yPos = (screenSize.height - frame.getHeight()) / 2;
        frame.setLocation(xPos, yPos);
        
        frame.setVisible(true);
        frame.setLayout(null);
        
        
        
        
    }

    @Override
    public void actionPerformed(ActionEvent ae){
        
        // Adding functionalities of Edit Menu Items
        if(ae.getSource() == cut){
            //cut operation
            textArea.cut();
        }
        if(ae.getSource() == copy){
            // perform copy operation
            textArea.copy();
        }
        if(ae.getSource() == paste){
            // perform close operation
            textArea.paste();
        }
        if(ae.getSource() == selectAll){
            // perform select All operation
            textArea.selectAll();
        }
        if(ae.getSource() == close){
            // perform close operation
            System.exit(0);
        }
        
        //File menu Items  
        // open File 
        if(ae.getSource() == openFile){
            // open a file chooser
            JFileChooser fileChooser = new JFileChooser("C:");
            int chooseOption =  fileChooser.showOpenDialog(null);
            
            // if we have clicked an open button
            if(chooseOption == JFileChooser.APPROVE_OPTION){
                //getting selected file
                File file = fileChooser.getSelectedFile();
                //get the path of the selected file
                String filePath = file.getPath();
                
                try{
                    // initialize file reader
                    FileReader fileReader = new FileReader(filePath);
                    // Initialize Buffered Reader
                    BufferedReader bufferedReader = new BufferedReader(fileReader);
                    String intermediate = "", output = "";
                    
                    // REad Contents of file line by line
                    while((intermediate= bufferedReader.readLine()) != null){
                        output += intermediate +"\n";
                    }
                    // set the output String  to TextArea
                    textArea.setText(output);
                }
                catch(FileNotFoundException e1){
                 //.printStackTrace();
                }
                catch(IOException e){
                    e.getMessage();
                }
            }
        }
        // Save File Operation
        if(ae.getSource() == saveFile){
            // initialize file picker
            JFileChooser   fileChooser = new JFileChooser();
            
            int chooseOption = fileChooser.showSaveDialog(null);
            
            if(chooseOption == JFileChooser.APPROVE_OPTION){
                // create a new file with choosen directory path 
                File file = new File(fileChooser.getSelectedFile().getAbsolutePath() + ".txt");
                
                try{
                    // initialize file writer this time we have to write the file
                    FileWriter fileWriter = new FileWriter(file);
                    // initialize buffered writer
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                    textArea.write(bufferedWriter);
                    bufferedWriter.close();
                }
                catch(IOException e){
                    e.getMessage();
                }
            }
        }
        // New Window Operation
        if(ae.getSource() == newFile){
            TextEditor newTextEditor = new TextEditor();
        }
        // Dark Mode operation
        if(ae.getSource() == darkModeButton){
            // call dark Mode Function
            toggleDarkMode();
        }
        if(ae.getSource() == increaseFontSize){
            increaseFontSize();
        }
        if(ae.getSource() == changeFontStyle){
            changeFontStyle();
        }
         
    }
    private void increaseFontSize() {
        Font currentFont = textArea.getFont();
        int currentSize = currentFont.getSize();
        textArea.setFont(currentFont.deriveFont(currentSize + 1.0f));
    }

    private void changeFontStyle() {
        String[] fontStyles = {"Arial", "Times New Roman","Cooper Black","Cooper","Chiller","Calisto MT","Roman"}; // Example font styles
        String selectedFont = (String) JOptionPane.showInputDialog(
                frame,
                "Select Font Style:",
                "Change Font Style",
                JOptionPane.PLAIN_MESSAGE,
                null,
                fontStyles,
                fontStyles[0]);

        if (selectedFont != null) {
            Font currentFont = textArea.getFont();
            textArea.setFont(new Font(selectedFont, currentFont.getStyle(), currentFont.getSize()));
        }
    }
  
    // function for dark Mode
    public void toggleDarkMode() {
    if (darkModeEnabled) {
        // Change to light mode
        frame.getContentPane().setBackground(Color.WHITE);
        textArea.setBackground(Color.WHITE);
        textArea.setForeground(Color.BLACK);
        // Set other component colors accordingly
    } else {
        // Change to dark mode
        frame.getContentPane().setBackground(Color.BLACK);
        textArea.setBackground(Color.BLACK);
        textArea.setForeground(Color.WHITE);
        // Set other component colors accordingly
    }
    darkModeEnabled = !darkModeEnabled;
}
    public static void main(String[] args) {
        
        TextEditor te = new TextEditor();
    }
    
}
