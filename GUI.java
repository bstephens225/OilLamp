import java.awt.*;
import javax.swing.*;

import java.awt.event.*;

public class GUI{
    int brightness=255;
    Color light=new Color(brightness,brightness,brightness);
    JTextField textField;
    JTextArea paragraph;
    String response;
    public GUI() {
        JFrame frame = new JFrame("LampLight");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout());
        
        // set the background image
        ImageIcon background = new ImageIcon("lamplightbackground.png");
        JLabel labelBackground = new JLabel(background);
        labelBackground.setLayout(null);
        
        
        // add label
        paragraph = new JTextArea("You wake up in a Victorian era mansion. You don’t remember how you got there. It is dark except for a gas lamp which will not run out or turn off.");
        paragraph.setForeground(light);
        paragraph.setBackground(Color.black);
        paragraph.setBounds(350,350,400,100);
        paragraph.setEditable(false);
        paragraph.setLineWrap(true);
        paragraph.setWrapStyleWord(true);

       
        javax.swing.border.Border tfBorder = BorderFactory.createLineBorder(Color.white);
        //javax.swing.cursor.Cursor tfCursor = (Color.white);

        //add text field
        textField = new JTextField(20);
        textField.setForeground(light);
        textField.setBackground(Color.black);
        textField.setBorder(tfBorder);
        textField.setBounds(350,480,250,20);
        textField.setCaretColor(light);
        labelBackground.add(paragraph);
        labelBackground.add(textField);

        frame.add(labelBackground);
        frame.setVisible(true);

        textField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //save text and clear text field
                String command = textField.getText();
                textField.setText("");
                
                paragraph.setText(command+"\n\n"+response);
                dimBrightness();
            }
        });
    }

    public String setResponse(String response){
        this.response=response;
        return response;
    }
    public void dimBrightness(){
        if(brightness>0){brightness-=5;
            Color darker=new Color(brightness,brightness,brightness);
            textField.setCaretColor(darker);
            paragraph.setForeground(darker);
            textField.setForeground(darker);
        }
        
    //light(brightness,brightness,brightness);
    }
    
    public static void main(String[] args) {
        String resp="ohh";
        new GUI();
    }
    
}