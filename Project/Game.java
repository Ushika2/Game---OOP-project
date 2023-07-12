import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import character.*;
import spell.*;
import weapon.*;

public class Game{
    JFrame window;
	Container con;
	JPanel titleNamePanel, startButtonPanel, continueButtonPanel, mapButtonPanel, mainTextPanel, mapPanel, choiceButtonPanel, playerPanel,textPanel,inputPanel, monsterPanel; //panel to display text;
	JLabel titleNameLabel, hpLabel, hpLabelNumber, weaponLabel, weaponLabelName,characterLabel, characterLabelName,locationLabel, locationName, monsterLabel, monsterName, monsterHP,monsterHPLabel, textLabel;
	Font titleFont = new Font("Times New Roman", Font.PLAIN, 90);
	Font normalFont = new Font("Times New Roman", Font.PLAIN, 18);
	Font normaltextFont = new Font("Times New Roman", Font.PLAIN, 26);  //custom font
	JButton startButton, continueButton, mapButton, choice1, choice2, choice3, choice4,enterButton; //add button
	JTextArea mainTextArea, mapTextArea;
	JTextField jtf;  //text box for user input

	int playerHP;
	String playerName, weapon, position, location;
	
	TitleScreenHandler tsHandler = new TitleScreenHandler();
	MapScreenHandler mapHandler = new MapScreenHandler();
	ChoiceHandler choiceHandler = new ChoiceHandler();
	InputHandler iHandler = new InputHandler();
	
    
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
		startButtonPanel.setBounds(300, 350, 200, 50);
		startButtonPanel.setBackground(Color.black);
		
		startButton = new JButton("START");
		startButton.setBackground(Color.black);
		startButton.setForeground(Color.white);
		startButton.setFont(normalFont);
		startButton.addActionListener(tsHandler);
		startButton.setFocusPainted(false);
		startButton.setActionCommand("start");
		startButton.setBorder(null);
		
		// Continue button 
		continueButtonPanel = new JPanel();
		continueButtonPanel.setBounds(300, 400, 200, 50);
		continueButtonPanel.setBackground(Color.black);

		continueButton = new JButton("CONTINUE");
		continueButton.setBackground(Color.black);
		continueButton.setForeground(Color.white);
		continueButton.setFont(normalFont);
		continueButton.addActionListener(tsHandler);
		continueButton.setFocusPainted(false);
		continueButton.setActionCommand("continue");
		continueButton.setBorder(null);

		titleNamePanel.add(titleNameLabel);
		startButtonPanel.add(startButton);
		continueButtonPanel.add(continueButton);

		con.add(titleNamePanel);
		con.add(startButtonPanel);
		con.add(continueButtonPanel);

		window.setVisible(true);
	}

	//get name from user
	public void userInput(){

		titleNamePanel.setVisible(false);
		startButtonPanel.setVisible(false);
		continueButtonPanel.setVisible(false);

		//add text to the panel
        textPanel = new JPanel();
        textPanel.setBounds(150,250,500,100);
        textPanel.setBackground(Color.black);
        textLabel = new JLabel("Enter your name:");  // adding name
        textLabel.setFont(normaltextFont);
		textLabel.setForeground(Color.white);
		
        //panel takes user input
        inputPanel = new JPanel();
        inputPanel.setBounds(150,450,500,50);
        inputPanel.setBackground(Color.black);
        inputPanel.setLayout(new GridLayout(1,2)); //1 and 2 because panel is divoded into 2 parts textbox and button horizontally
        
        jtf = new JTextField();
        inputPanel.add(jtf);//add jtf(text box) to input panel
		

        enterButton = new JButton("ENTER");  //shows enter on the button
        enterButton.setForeground(Color.white);
		enterButton.setBackground(Color.black);
        enterButton.addActionListener(iHandler);
		enterButton.setFocusPainted(false);

		textPanel.add(textLabel);
		inputPanel.add(jtf);//add jtf(text box) to input panel
        inputPanel.add(enterButton); //add button to input panel

		con.add(textPanel);
        con.add(inputPanel); //add input panel to ocntainer
          
		window.setVisible(true);

	}

	public void Gameplay(String startOrContinue){
		// titleNamePanel.setVisible(false);
		// startButtonPanel.setVisible(false);

		textPanel.setVisible(false);
		inputPanel.setVisible(false);

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

		//monster panel
		monsterPanel = new JPanel();
		monsterPanel.setBounds(70, 500, 500, 50);
		monsterPanel.setBackground(Color.black);
		monsterPanel.setLayout(new FlowLayout(FlowLayout.LEADING,5,5));
		con.add(monsterPanel);
		monsterHPLabel = new JLabel("HP:");  // adding Hp
		monsterHPLabel.setFont(normalFont);
		monsterHPLabel.setForeground(Color.white);
		monsterPanel.add(monsterHPLabel);
		monsterHP = new JLabel();
		monsterHP.setFont(normalFont);
		monsterHP.setForeground(Color.white);
		monsterPanel.add(monsterHP);
		monsterLabel = new JLabel(" |Monster:");  // adding character
		monsterLabel.setFont(normalFont);
		monsterLabel.setForeground(Color.white);
		monsterPanel.add(monsterLabel);
		monsterName = new JLabel();
		monsterName.setFont(normalFont);
		monsterName.setForeground(Color.white);
		monsterPanel.add(monsterName);
		monsterPanel.setVisible(false);

		if(startOrContinue.equals("start")){
			playerSetup();
			intro();
		}
		else if(startOrContinue.equals("continue")){
			loadData();
		}

		// playerSetup();
		// intro();

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
		choice1.setActionCommand("c1");  //to differentiate between the 4 choice button
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

		choiceButtonPanel.setVisible(false);

		
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
    position = "map";

	choiceButtonPanel.setVisible(true);
    mainTextPanel.setVisible(false); // Hide the main text panel
	mapButtonPanel.setVisible(false); //hide the view map button when on the map
	monsterPanel.setVisible(false);

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

	choice1.setText("Townn");
	choice2.setText("Forest"); // in forest, you go to river, mountain or back to town
	choice3.setText("Village");
	choice4.setText("River");
	// choice 5 for moauntain
    
	//ensure that all choices appear when map is called
	choice1.setVisible(true);
	choice2.setVisible(true);
	choice3.setVisible(true);
	choice4.setVisible(true);
	   
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

	//trial to create mage
	// public void mageSetup(){
	// 	Spell spell = new Fire("Fireball", 50);
	// 	Mage mage = new Mage("Gandalf", 100, spell);
	// }

	public void intro(){
		// Begining of story - town
		position = "intro";
		mainTextArea = new JTextArea("At the entrance of the hometown, you see someone standing. You approach the person. Hello," + jtf.getText() + " I am the chief of this town. We need your help. The safety of the town is threatened by a monster. To ensure the safety of the townfolks you must find the monster and defeat it. You may begin your quest by viewing the map."); // to change
		mainTextArea.setBounds(100, 100, 600, 250);
		mainTextPanel.setBackground(Color.black);
		mainTextArea.setForeground(Color.black);  //maybe to change later
		//startButton.setFont(normalFont);
		mainTextArea.setLineWrap(true);
		mainTextArea.setWrapStyleWord(true); 
		mainTextArea.setEditable(false); 	
		mainTextPanel.add(mainTextArea);
	}

	// Going to village for 1st time
	public void townn(){
		position = "townn";
		location = "Town";
		locationName.setText(location);
		mapPanel.setVisible(false);
		mainTextPanel.setVisible(true);

		mainTextArea.setText("You are at the town.\nYou see a checkpoint to save the game.");		
		choice1.setText("Save game");
		choice2.setText("View map");
		choice3.setText("");
		choice4.setText("");
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
		choice2.setText("view map");
		choice3.setText("");
		choice4.setText("");
		
		choice3.setVisible(false);
		choice4.setVisible(false);
		
	}

	//Gobin Attack
	public void goblinAttack(){
		position = "goblinAttack";
		monsterPanel.setVisible(true);
		goblinSetup();

		mapButtonPanel.setVisible(false);

		int damage = new java.util.Random().nextInt(1,6);
		mainTextArea.setText("The goblin attacked you giving" + damage + "damage.");

		warrior.takeDamage(damage);
		// update hp of player
		hpLabelNumber.setText(""+warrior.getHP());

		choice1.setText("Attack goblin");
		choice2.setText("");
		choice3.setText("");
		choice4.setText("");
		
		choice3.setVisible(false);
		choice4.setVisible(false);
		
	}

	public void AttackGoblin(){
		position = "attackGoblin";
		mapButtonPanel.setVisible(false);
		int damage = knife.getDamage();
		mainTextArea.setText("You attack the goblin back, giving it a" + damage + "damage.");
		//warrior.attack(goblin);
		goblin.takeDamage(damage);
		// update hp of goblin
		monsterHP.setText("" + goblin.getHP());

		if (goblin.getHP() > 0){
			goblinAttack();
		}
		else{
			goblin.setHP(0);
			monsterHP.setText("" + goblin.getHP());
			mainTextArea.setText("You attack the goblin back, giving it a" + damage + "damage. The goblin has been defeated.");
			choice1.setText("Move forward");
			choice2.setText("View map");
			choice3.setText("");
			choice4.setText("");
			
			choice3.setVisible(false);
			choice4.setVisible(false);
		}
	}

	public void saveFile(){
		Path filePath = Paths.get("saveFile.txt");
		List<String> lines = new ArrayList<>();
		lines.add(String.valueOf(warrior.getHP()));
		// Add other data you want to save to the file here

		try {
			Files.write(filePath, lines, StandardCharsets.UTF_8);
		} catch (IOException e) {
			e.printStackTrace();
		}

		hpLabelNumber.setText(String.valueOf(warrior.getHP()));
		townn();
	}

	public void loadData(){
		try{
			BufferedReader br = new BufferedReader(new FileReader("saveFile.txt"));
			playerHP = Integer.parseInt(br.readLine());
			weapon = br.readLine();
			br.close();
		}
		catch(Exception e){
			
		}
		hpLabelNumber.setText("" + playerHP);
		weaponLabelName.setText(weapon);
		townn();
	}

	public class ChoiceHandler implements ActionListener{
		public void actionPerformed(ActionEvent event){
			String yourChoice = event.getActionCommand();  

			switch(position){      //the game recognize player's current position
				case "map": 
					switch(yourChoice){
						case "c1": townn(); break;
						case "c2": forest1(); break;
						case "c3": village1(); break;
					}
					break;
				case "townn":
					switch(yourChoice){
						case "c1": saveFile(); break;
						case "c2": Map(); break;
					}

				case "forest":   
				    switch(yourChoice){     
						case "c1": goblinAttack(); break;
						case "c2": Map(); break;
					}
					break;
				case "goblinAttack":
                    switch(yourChoice){
			     		case "c1":AttackGoblin();break;
				 	}
					break;
				case "attackGoblin":
					switch(yourChoice){
						//case "c1": 
						case "c2": Map(); break;
					}
			}
		}
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

	public class TitleScreenHandler implements ActionListener{
		public void actionPerformed(ActionEvent event){
			String yourChoice = event.getActionCommand();
			switch(yourChoice){
				case "start": userInput(); break;
				case "continue": Gameplay("continue"); break;
			}
			//userInput();
		}
	}
	public class MapScreenHandler implements ActionListener{
		public void actionPerformed(ActionEvent event){
			Map();
		}
	}

	public class InputHandler implements ActionListener{
        public void actionPerformed(ActionEvent event){
			String text = jtf.getText();
			Gameplay("start");
        }
    }
}
