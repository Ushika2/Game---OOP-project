package User_input;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class User_input{
    JFrame window;
	Container con;
    JPanel textPanel,inputPanel; //panel to display text
    JLabel textLabel;
    Font normalFont = new Font("Times New Roman", Font.PLAIN, 26);  //custom font
    JTextField jtf;  //text box for user input
    JButton enterButton; //add button
    InputHandler iHandler = new InputHandler();

    public static void main(String[] args) {
		new User_input();
}

public void User_input(){
    //new window display
        window = new JFrame();
        window.setSize(800, 600);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().setBackground(Color.black);
        window.setLayout(null);
        con = window.getContentPane();


         //add text to the window panel
        textPanel = new JPanel();
        textPanel.setBounds(150,250,500,100);
        textPanel.setBackground(Color.black);
        textLabel = new JLabel("Enter your name:");  // adding name
        textLabel.setFont(normalFont);
		textLabel.setForeground(Color.white);
		textPanel.add(textLabel);
        con.add(textPanel);

        //panel takes user input
        inputPanel = new JPanel();
        inputPanel.setBounds(150,450,500,50);
        inputPanel.setBackground(Color.black);
        inputPanel.setLayout(new GridLayout(1,2)); //1 and 2 because panel is divoded into 2 parts textbox and button horizontally
        
        jtf = new JTextField();
        inputPanel.add(jtf);//add jtf(text box) to input panel

        enterButton = new JButton("ENTER");  //shows enter on the button
        enterButton.setForeground(Color.black);
        enterButton.addActionListener(iHandler);
        inputPanel.add(enterButton); //add button to input panel

        con.add(inputPanel); //add input panel to ocntainer


        window.setVisible(true);
                
    }

    
    public class InputHandler implements ActionListener{
        public void actionPerformed(ActionEvent event){
             //here is to put result after performing action
             String text = jtf.getText(); //get the text entered from the text box declared as jtf
             //textLabel.setText(text);//displays received input
        }
    }
}