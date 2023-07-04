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
	JPanel titleNamePanel, startButtonPanel, mapButtonPanel, mainTextPanel, mapPanel, choiceButtonPanel, playerPanel;
	JLabel titleNameLabel, hpLabel, hpLabelNumber, weaponLabel, weaponLabelName,characterLabel, characterLabelName,locationLabel, locationName, monsterName, monsterHP;
	Font titleFont = new Font("Times New Roman", Font.PLAIN, 90);
	Font normalFont = new Font("Times New Roman", Font.PLAIN, 18);
	JButton startButton, mapButton, choice1, choice2, choice3, choice4;
	JTextArea mainTextArea, mapTextArea;
	int playerHP;
	String playerName, weapon, position, location;
	
	TitleScreenHandler tsHandler = new TitleScreenHandler();
	MapScreenHandler mapHandler = new MapScreenHandler();
	ChoiceHandler choiceHandler = new ChoiceHandler();

	Knife knife = new Knife("knife",10);
	Warrior warrior = new Warrior("warrior",100, knife, null);
	Goblin goblin = new Goblin("goblin",13);
	
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
		playerPanel.setLayout(new FlowLayout(FlowLayout.LEADING,5,5));
		con.add(playerPanel);
		hpLabel = new JLabel("HP:");  // adding Hp
		hpLabel.setFont(normalFont);
		hpLabel.setForeground(Color.white);
		playerPanel.add(hpLabel);
		hpLabelNumber = new JLabel();
		hpLabelNumber.setFont(normalFont);
		hpLabelNumber.setForeground(Color.white);
		playerPanel.add(hpLabelNumber);
		characterLabel = new JLabel(" |Character:");  // adding character
		characterLabel.setFont(normalFont);
		characterLabel.setForeground(Color.white);
		playerPanel.add(characterLabel);
		characterLabelName = new JLabel();
		characterLabelName.setFont(normalFont);
		characterLabelName.setForeground(Color.white);
		playerPanel.add(characterLabelName);
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
		position = "town1";
		mainTextArea = new JTextArea("At the entrance of the hometown, you see someone standing. You approach the person. Hello ..playername.. I am the chief of this town. We need your help. The safety of the town is threatened by a monster. To ensure the safety of the townfolks you must find the monster and defeat it. You may begin your quest by viewing the map."); // to change
		mainTextArea.setBounds(100, 100, 600, 250);
		mainTextPanel.setBackground(Color.black);
		mainTextArea.setForeground(Color.black);  //maybe to change later
		startButton.setFont(normalFont);
		mainTextArea.setLineWrap(true);
		mainTextArea.setWrapStyleWord(true); 
		mainTextArea.setEditable(false); 	
		mainTextPanel.add(mainTextArea);

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

		// Map button panel
		mapButtonPanel = new JPanel();
		mapButtonPanel.setBounds(500, 500, 300, 150);
		mapButtonPanel.setBackground(Color.black);

		mapButton = new JButton("View MAP");
		mapButton.setBackground(Color.black);
		mapButton.setForeground(Color.white);
		mapButton.setFont(normalFont);
		mapButton.addActionListener(mapHandler);
		mapButton.setFocusPainted(false);
		mapButtonPanel.add(mapButton);
		con.add(mapButtonPanel);
		window.setVisible(true);

	}

	public void Map() {
    //position = "map";

    mainTextPanel.setVisible(false); // Hide the main text panel

    mapPanel = new JPanel();
    mapPanel.setBounds(100, 100, 600, 250);
    mapPanel.setBackground(Color.black);
    con.add(mapPanel);

    mapTextArea = new JTextArea("Choose where to go.");
    mapTextArea.setBounds(100, 150, 600, 250);
    mapTextArea.setBackground(Color.red);
    mapTextArea.setForeground(Color.white);
    mapTextArea.setFont(normalFont);
    mapTextArea.setLineWrap(true);
    mapTextArea.setWrapStyleWord(true);
    mapTextArea.setEditable(false);

    mapPanel.add(mapTextArea);
    mapPanel.setVisible(true); // Show the map panel

	choice1.setText("Town");
	choice2.setText("Forest"); // in forest, you go to river, mountain or back to town
	choice3.setText("Village");
	choice4.setText("River");
	// choice 5 for moauntain
	}

	public void playerSetup(){ //to modify
		location = "Town";

		weaponLabelName.setText(knife.getName());
		playerHP = warrior.getHP();
		hpLabelNumber.setText("" + playerHP);
		locationName.setText(location);
		characterLabelName.setText("" + warrior.getName());
	}

	// Goblin setup
	public void goblinSetup(){
		monsterName.setText(goblin.getName());
		monsterHP.setText("" + goblin.getHP());

	}

	// Going to village for 1st time
	public void village1(){
		position = "village1";
		location = "Village";
		locationName.setText(location);
		mapPanel.setVisible(false);
		mainTextPanel.setVisible(true);

		mainTextArea.setText("You are at the village.\nYou see a shop where you can buy weapons.You go in.");		
		choice1.setText("");
		choice2.setText("");
		choice3.setText("");
		choice4.setText("");
	}
	// Going to forest for the 1st time
	public void forest1(){
		position = "forest";
		location = "Forest";
		locationName.setText(location);
		mapPanel.setVisible(false);
		mainTextPanel.setVisible(true);

		mainTextArea.setText("You make way to the forest where you see a goblin coming towards you. You can either fight it or run away, by opening your map.");		
		choice1.setText("Fight the goblin");
		choice2.setText("");
		choice3.setText("");
		choice4.setText("");
	}

	//Gobin Attack
	public void goblinAttack(){
		position = "goblinAttack";
		//goblinSetup();
		
		//create goblin
		// Goblin goblin = new Goblin("goblin",13);
		// monsterName.setText(goblin.getName());
		// monsterHP.setText("" + goblin.getHP());

		int damage = new java.util.Random().nextInt(6);
		mainTextArea.setText("The goblin attacked you giving" + damage + "damage.");
		//goblin.attack(warrior);
		// update hp of player
		//playerHP = playerHP - goblin.getDamage();
		//hpLabelNumber.setText(""+warrior.getHP());

		choice1.setText("");
		choice2.setText("");
		choice3.setText("");
		choice4.setText("");
	}

	public class ChoiceHandler implements ActionListener{
		public void actionPerformed(ActionEvent event){
			String yourChoice = event.getActionCommand();

			switch(position){
				case "town1":
					switch(yourChoice){
						//case "c1": town1(); break;
						case "c2": forest1(); break;
						case "c3": village1(); break;
					}
					break;
				case "forest":
					switch(yourChoice){
						case "c1": goblinAttack(); break;
					}
			}
		}
	}

	public class TitleScreenHandler implements ActionListener{
		public void actionPerformed(ActionEvent event){
			Gameplay();
		}
	}
	public class MapScreenHandler implements ActionListener{
		public void actionPerformed(ActionEvent event){
			//mainTextPanel.setVisible(false);
			Map();
		}
	}
}
