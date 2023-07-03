import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import character.*;
import weapon.*;

public class Game{
    JFrame window;
	Container con;
	JPanel titleNamePanel, startButtonPanel, mapButtonPanel, mainTextPanel, choiceButtonPanel, playerPanel;
	JLabel titleNameLabel, hpLabel, hpLabelNumber, weaponLabel, weaponLabelName,locationLabel, locationName;
	Font titleFont = new Font("Times New Roman", Font.PLAIN, 90);
	Font normalFont = new Font("Times New Roman", Font.PLAIN, 28);
	JButton startButton, mapButton, choice1, choice2, choice3, choice4;
	JTextArea mainTextArea;
	int playerHP, monsterHP;
	String weapon, position, location;
	
	TitleScreenHandler tsHandler = new TitleScreenHandler();
	ChoiceHandler choiceHandler = new ChoiceHandler();
	
	//ImageIcon logo = new ImageIcon(".//res//jackfrost.jpg");

	public static void main(String[] args) {
		new Game();
	}
	
	public Game(){
		
		window = new JFrame();
		window.setSize(800, 600);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.getContentPane().setBackground(Color.black);
		window.setLayout(null);
		//window.setIconImage(logo.getImage());
		con = window.getContentPane();
		
		titleNamePanel = new JPanel();
		titleNamePanel.setBounds(100, 100, 600, 150);
		titleNamePanel.setBackground(Color.black);
		titleNameLabel = new JLabel("ADVENTURE");
		titleNameLabel.setForeground(Color.white);
		titleNameLabel.setFont(titleFont);	
		
		// Start button panel
		startButtonPanel = new JPanel();
		startButtonPanel.setBounds(300, 400, 200, 100);
		startButtonPanel.setBackground(Color.black);
		
		startButton = new JButton("START");
		startButton.setBackground(Color.black);
		startButton.setForeground(Color.white);
		startButton.setFont(normalFont);
		startButton.addActionListener(tsHandler);
		startButton.setFocusPainted(false);
		
		titleNamePanel.add(titleNameLabel);
		startButtonPanel.add(startButton);
		
		con.add(titleNamePanel);
		con.add(startButtonPanel);
		
		window.setVisible(true);
	}

	public void Gameplay(){
		titleNamePanel.setVisible(false);
		startButtonPanel.setVisible(false);

		mainTextPanel = new JPanel();
		mainTextPanel.setBounds(100,100, 600, 250);
		mainTextPanel.setBackground(Color.white);
		con.add(mainTextPanel);

		//player panel
		playerPanel = new JPanel();
		playerPanel.setBounds(70, 5, 600, 50);
		playerPanel.setBackground(Color.black);
		playerPanel.setLayout(new FlowLayout(FlowLayout.LEADING,10,5));
		con.add(playerPanel);
		hpLabel = new JLabel("HP:");  // adding Hp
		hpLabel.setFont(normalFont);
		hpLabel.setForeground(Color.white);
		playerPanel.add(hpLabel);
		hpLabelNumber = new JLabel();
		hpLabelNumber.setFont(normalFont);
		hpLabelNumber.setForeground(Color.white);
		playerPanel.add(hpLabelNumber);
		weaponLabel = new JLabel(" |Weapon:");  // adding weapon
		weaponLabel.setFont(normalFont);
		weaponLabel.setForeground(Color.white);
		weaponLabel.setBackground(Color.red);
		playerPanel.add(weaponLabel);
		weaponLabelName = new JLabel();
		weaponLabelName.setFont(normalFont);
		weaponLabelName.setForeground(Color.white);
		playerPanel.add(weaponLabelName);
		locationLabel = new JLabel(" |Location:");  // adding location
		locationLabel.setFont(normalFont);
		locationLabel.setForeground(Color.white);
		locationLabel.setBackground(Color.red);
		playerPanel.add(locationLabel);
		locationName = new JLabel();
		locationName.setFont(normalFont);
		locationName.setForeground(Color.white);
		playerPanel.add(locationName);

		playerSetup();

		// Begining of story - town
		mainTextArea = new JTextArea("At the entrance of the hometown, you see someone standing. You approach the person. Hello ..playername.. I am the chief of this town. We need your help. The safety of the town is threatened by a monster. To ensure the safety of the townfolks you must find the monster and defeat it. You may begin your quest by viewing the map."); // to change
		mainTextArea.setBounds(100, 100, 600, 250);
		mainTextPanel.setBackground(Color.black);
		mainTextArea.setForeground(Color.black);  //maybe to change later
		startButton.setFont(normalFont);
		mainTextArea.setLineWrap(true);
		mainTextArea.setWrapStyleWord(true); 
		mainTextArea.setEditable(false); 	
		mainTextPanel.add(mainTextArea);

		// Map Button panel
		mapButtonPanel = new JPanel();
		mapButtonPanel.setBounds(300, 400, 200, 100);
		mapButtonPanel.setBackground(Color.black);

		mapButton = new JButton("View MAP");
		mapButton.setBackground(Color.black);
		mapButton.setForeground(Color.white);
		mapButton.setFont(normalFont);
		mapButton.addActionListener(tsHandler);
		mapButton.setFocusPainted(false);
		mapButtonPanel.add(mapButton);
		con.add(mapButtonPanel);

		// choices panel
		choiceButtonPanel = new JPanel();
		choiceButtonPanel.setBounds(250, 350, 300, 150);
		choiceButtonPanel.setBackground(Color.black);
		con.add(choiceButtonPanel);
		choice1 = new JButton("");  // choice 1 
		choice1.setBackground(Color.black);
		choice1.setForeground(Color.white);
		choice1.setFont(normalFont);
		choice1.setFocusPainted(false);
		choice1.addActionListener(choiceHandler);
		choice1.setActionCommand("c1");
		choiceButtonPanel.add(choice1);
		choice2 = new JButton("");  // choice 2
		choice2.setBackground(Color.black);
		choice2.setForeground(Color.white);
		choice2.setFont(normalFont);
		choice2.setFocusPainted(false);
		choice2.addActionListener(choiceHandler);
		choice2.setActionCommand("c2");
		choiceButtonPanel.add(choice2);
		choice3 = new JButton("");  // choice 3 
		choice3.setBackground(Color.black);
		choice3.setForeground(Color.white);
		choice3.setFont(normalFont);
		choice3.setFocusPainted(false);
		choice3.addActionListener(choiceHandler);
		choice3.setActionCommand("c3");
		choiceButtonPanel.add(choice3);
		choice4 = new JButton("");  // choice 4 
		choice4.setBackground(Color.black);
		choice4.setForeground(Color.white);
		choice4.setFont(normalFont);
		choice4.setFocusPainted(false);
		choice4.addActionListener(choiceHandler);
		choice4.setActionCommand("c4");
		choiceButtonPanel.add(choice4);

		// choice4.setContentAreaFilled(false);  // disable highlighting on press

	}

	public void Map(){ // modify
		position = "map";

		//mapButtonPanel.setVisible(false);

		mainTextPanel = new JPanel();
		mainTextPanel.setBounds(100,100, 600, 250);
		mainTextPanel.setBackground(Color.white);
		con.add(mainTextPanel);

		mainTextArea = new JTextArea("Choose where to go.");
		mainTextArea.setBounds(100, 100, 600, 250);
		mainTextPanel.setBackground(Color.black);
		mainTextArea.setForeground(Color.black);  //maybe to change later
		startButton.setFont(normalFont);
		mainTextArea.setLineWrap(true);
		mainTextArea.setWrapStyleWord(true); 
		mainTextArea.setEditable(false); 	
		mainTextPanel.add(mainTextArea);

		choice1.setText("Town");
		choice2.setText("Forest"); // in forest, you go to river, mountain or back to town
		choice3.setText("Village");
		choice4.setText("");
	}

	public void playerSetup(){ //to modify
		playerHP = 15;
		weapon = "knife";
		location = "Town";
		weaponLabelName.setText(weapon);
		hpLabelNumber.setText("" + playerHP);
		locationName.setText(location);
	}

	public class TitleScreenHandler implements ActionListener{
		public void actionPerformed(ActionEvent event){
			Gameplay();
		}
	}

	public class ChoiceHandler implements ActionListener{
		public void actionPerformed(ActionEvent event){
			String yourChoice = event.getActionCommand();

			// switch(position){
			// 	case "map":
			// 		switch(yourChoice){
			// 			case "c1": town();break;
			// 			case "c2": Forest();break;
			// 			case "c3": Village();break;
			// 		}
			// 		break;
			// }
		}
	}
}
