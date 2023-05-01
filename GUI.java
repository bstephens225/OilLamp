import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class GUI{
        
    public GUI() {
        JFrame frame = new JFrame("LampLight");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout());
        
        // set the background image
        ImageIcon background = new ImageIcon("backgroundlamplighttrial.png");
        JLabel labelBackground = new JLabel(background);
        labelBackground.setLayout(null);
        
        
        // add label
        JLabel text = new JLabel("Hi");
        text.setForeground(Color.white);
        text.setBounds(400,550,100,20);        

        //add text field
        JTextField textField = new JTextField(20);
        textField.setForeground(Color.white);
        textField.setBackground(Color.black);
        textField.setBounds(400,500,100,20);

        labelBackground.add(text);
        labelBackground.add(textField);

        frame.add(labelBackground);
        frame.setVisible(true);
    }
    
    public static void main(String[] args) {
        new GUI();
    }
    

    public void actionPerformed(ActionEvent evt) {
        try {
            //String host = tf.getText();
            //l.setText(host);
        } catch (Exception e) {
            //l.setText(e.getMessage());
        }
    }
    
}