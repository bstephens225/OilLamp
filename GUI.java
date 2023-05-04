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
    /**glitch */
    public void glitch(){
        double random=Math.random();
        if(random<.25){
            dimBrightness();
        }else if(random<.3){
            //add item to inventory
            Item spoon=new Item("spoon","a silver spoon",false,false,false);
            you.grab(spoon);
        }else if(random<.35){
            Item flower=new Item("rose","a withered rose",false,false,true);
            if (you.getLoc().getContents().contains(flower)==false){
                you.getLoc().getContents().add(flower);
            }
            //add item to room
        }else{
            //
        }

    }
    
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
        paragraph = new JTextArea("You wake up in a Victorian era mansion. You don’t remember how you got there. It is dark except for a gas lamp which will not run out or turn off.\n"+  you.getLocName()+you.getLoc().getDecription());
        paragraph.setForeground(light);
        paragraph.setBackground(Color.black);
        paragraph.setBounds(350,320,400,150);
        paragraph.setEditable(false);
        paragraph.setLineWrap(true);
        paragraph.setWrapStyleWord(true);
        paragraph.setFont(paragraph.getFont().deriveFont(Font.ITALIC));

       
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
                        String str=talk.response();
                        dimBrightness();
                        glitch();
                        if(str=="The house burns. Light is everywhere. You must go to it."){
                            paragraph.setForeground(light);
                            gameover=true;
                        }
                        paragraph.setText(command+"\n\n"+str);
                        //System.out.print("go=false");
                    }catch(Exception e){
                        if(e.getMessage()=="gameover"){
                            gameover=true;
                            paragraph.setForeground(light);
                            paragraph.setText("Game Over");
                        }
                        
                    }
        
                }else{
                    paragraph.setForeground(light);
                    paragraph.setText("Game Over");
                }
                
            }
        });
    }

    public String setResponse(String response){
        this.response=response;
        return response;
    }
    public void dimBrightness(){
        if(brightness>0){
            brightness-=5;
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