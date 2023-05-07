import java.awt.*;
import javax.swing.*;

import java.awt.event.*;

public class GUI{
    //set brightness and color to be used in the font
    int brightness=255;
    Color light=new Color(brightness,brightness,brightness);

    //make different elements to appear on the page
    JTextField textField;
    JTextArea paragraph;

    //store the parser's response and whether the game is over
    String response;
    boolean gameover=false;

    //store the player Character
    Character you;

    /**glitch
     * 25% chance of the lamp getting even dimmer
     * 5% chance of a spoon appearing in your inventory
     * 5% chance of a rose appearing in the room with you
     */
    public void glitch(){
        double random=Math.random();
        if(random<.25){
            dimBrightness();
        }else if(random<.3){
            //add item to inventory
            Item spoon=new Item("spoon","a silver spoon you picked up earlier",false,false,false);
            you.grab(spoon);
        }else if(random<.35){
            Item flower=new Item("rose","a withered rose that's been here the whole time",false,false,true);
            if (you.getLoc().getContents().contains(flower)==false){
                you.getLoc().addItem(flower);
            }
            //add item to room
        }else{
            //nothing happens
        }

    }
    /** 
     * @param Character player character
    */
    public GUI(Character you) {
        //open window 800 by 600
        JFrame frame = new JFrame("LampLight");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout());
        this.you=you;

        //set the background image
        ImageIcon background = new ImageIcon("lamplightbackground.png");
        JLabel labelBackground = new JLabel(background);
        labelBackground.setLayout(null);
        
        
        //add text area for command and response
        paragraph = new JTextArea("You wake up in a Victorian era mansion. You don’t remember how you got there. It is dark except for a gas lamp which will not run out or turn off.\n"+  you.getLocName()+you.getLoc().getDecription());
        paragraph.setForeground(light);
        paragraph.setBackground(Color.black);
        paragraph.setBounds(350,320,400,150);
        paragraph.setEditable(false);
        paragraph.setLineWrap(true);
        paragraph.setWrapStyleWord(true);
        paragraph.setFont(paragraph.getFont().deriveFont(Font.ITALIC));

       //make a white border to the textfield
        javax.swing.border.Border tfBorder = BorderFactory.createLineBorder(Color.white);
        
        //add text field
        textField = new JTextField(20);
        textField.setForeground(light);
        textField.setBackground(Color.black);
        textField.setBorder(tfBorder);
        textField.setBounds(350,480,250,20);
        textField.setCaretColor(light);
        labelBackground.add(paragraph);
        labelBackground.add(textField);

        //add background image to frame
        frame.add(labelBackground);
        frame.setVisible(true);

        //respond to enter key hit by parsing the command and printing the command and response in the paragraph
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

    //change response to new string
    public String setResponse(String response){
        this.response=response;
        return response;
    }

    //darken the brightness of the text
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
    }
    
   
    
}