import java.awt.*;
import javax.swing.*;

import java.awt.event.*;

public class GUI{
    int brightness=255;
    Color light=new Color(brightness,brightness,brightness);
    JTextField textField;
    JTextArea paragraph;
    String response;
    boolean gameover=false;

    Character you;
    public GUI(Character you) {
        JFrame frame = new JFrame("LampLight");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout());
        this.you=you;

        // set the background image
        ImageIcon background = new ImageIcon("lamplightbackground.png");
        JLabel labelBackground = new JLabel(background);
        labelBackground.setLayout(null);
        
        
        // add label
        paragraph = new JTextArea(you.getLocName()+you.getLoc().getDecription());
        paragraph.setForeground(light);
        paragraph.setBackground(Color.black);
        paragraph.setBounds(350,320,400,150);
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
            public void actionPerformed(ActionEvent a) {
                //take commands and respond
                if(gameover==false){
                    try{
                        String command=textField.getText();
                        textField.setText("");
                        Parser talk= new Parser(you,command);
                        paragraph.setText(command+"\n\n"+talk.response());
                        //System.out.print("go=false");
                        dimBrightness();
                    }catch(Exception e){
                        if(e.getMessage()=="gameover"){
                            gameover=true;
                            paragraph.setText("Game Over");
                        }
                    }
        
                }
                
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
        }else{
            gameover=true;
        }
        
    //light(brightness,brightness,brightness);
    }
    
   
    
}