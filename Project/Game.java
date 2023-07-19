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
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import User_input.User_input.InputHandler;
import character.*;
import spell.*;
import weapon.*;

public class Game{
    JFrame window;
	Container con;
	JPanel titleNamePanel, startButtonPanel, continueButtonPanel, mapButtonPanel, mainTextPanel, mapPanel, choiceButtonPanel, playerPanel,textPanel,inputPanel, monsterPanel, healerButtonPanel; //panel to display text;
	JLabel titleNameLabel, hpLabel, hpLabelNumber, weaponLabel, weaponLabelName,characterLabel, characterLabelName,locationLabel, locationName, monsterLabel, monsterName, monsterHP,monsterHPLabel, textLabel,goldLabel,goldLabelNumber;
	Font titleFont = new Font("Times New Roman", Font.PLAIN, 90);
	Font normalFont = new Font("Times New Roman", Font.PLAIN, 18);
	Font normaltextFont = new Font("Times New Roman", Font.PLAIN, 26);  //custom font
	JButton startButton, continueButton, mapButton, choice1, choice2, choice3, choice4,enterButton, healerButton; //add button
	JTextArea mainTextArea, mapTextArea;
	JTextField jtf;  //text box for user input

	int playerHP;
	int gold;
	int numOfTimesChestOpened = 0;
	String playerName, weapon, position, location;
	
	TitleScreenHandler tsHandler = new TitleScreenHandler();
	MapScreenHandler mapHandler = new MapScreenHandler();
	ChoiceHandler choiceHandler = new ChoiceHandler();
	InputHandler iHandler = new InputHandler();


    Knife knife = new Knife("knife",10);
	Warrior warrior = new Warrior("warrior",100, knife, null);

	// Creating monsters & weapon
	Goblin goblin = new Goblin("goblin",13);
	Axe axe = new Axe("axe",20);  //goblin weapon in village
	Orgre orgre = new Orgre("orgre", 30);
	Wolf wolf = new Wolf("wolf",23);

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

		//textPanel.setVisible(false);
		//inputPanel.setVisible(false);

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
		goldLabel = new JLabel(" |Gold:");  // adding Hp
		goldLabel.setFont(normalFont);
		goldLabel.setForeground(Color.white);
		playerPanel.add(goldLabel);
		goldLabelNumber = new JLabel();
		goldLabelNumber.setFont(normalFont);
		goldLabelNumber.setForeground(Color.white);
		playerPanel.add(goldLabelNumber);

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
			titleNamePanel.setVisible(false);
		    startButtonPanel.setVisible(false);
		    continueButtonPanel.setVisible(false);
			mainTextPanel.setVisible(false); // Hide the main text panel
			//mapButtonPanel.setVisible(true); //hide the view map button when on the map
										 
			loadData();
			//Map();
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
        
		// Healer button panel
		healerButtonPanel = new JPanel();
		healerButtonPanel.setBounds(600, 450, 200, 50);
		healerButtonPanel.setBackground(Color.black);

		healerButton = new JButton("Healer");
		healerButton.setBackground(Color.black);
		healerButton.setForeground(Color.white);
		healerButton.setFont(normalFont);
		healerButton.addActionListener(mapHandler);
		healerButton.setFocusPainted(false);
		healerButtonPanel.add(healerButton);
		con.add(healerButtonPanel);
		window.setVisible(true);
		healerButtonPanel.setVisible(false);
		
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

	choice1.setText("Town");
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
		goldLabelNumber.setText(""+ gold);
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

	// Going to town for 1st time
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

		mainTextArea.setText("You decide to go take a look at the forlorn village. Upon arrival, you find the village under attack by a horde of goblins. Amidst the chaos, you catch sight of a solitary figure battling the malignant goblins.");		
		choice1.setText("Join him in the fight");
		choice2.setText("Hide and watch");
		choice3.setText("");
		choice4.setText("");

		choice3.setVisible(false);
		choice4.setVisible(false);
	}

	public void aidHealer1(){
		position = "aidHealer1";
		mainTextArea.setText("You rush to his side while his gaze acknowledges your kindness and you fight off the monsters alongside.\nYou turn to your left and see a goblin charges at you with an ax.");
		choice1.setText("Dodge");
		choice2.setText("Block attack");
		choice3.setText("Attack back");

	}

	public void dodge(){
		//position = "dodge";
		Random random = new Random();
        int randomNumber = random.nextInt(2);
		if (randomNumber == 0){
			mainTextArea.setText("You duck your head and was able to dodge the attack");
		}
		else{
			int damage = new java.util.Random().nextInt(5,20);
			mainTextArea.setText("You try to dodge, but failed, taking in" + damage + "damage");
			warrior.takeDamage(damage);
			// update hp of player
			hpLabelNumber.setText(""+warrior.getHP());
		}
		position = "attackGoblin";
		choice1.setText("Attack");
		choice2.setVisible(false);
	}

	public void blockAttack(){
		if (warrior.getLeftHandWeapon().getDamage() < axe.getDamage()){
			int damage = new java.util.Random().nextInt(1,10);
			mainTextArea.setText("You try to block the attack, but failed. You stumble back.");
			warrior.takeDamage(damage);
			hpLabelNumber.setText(""+warrior.getHP());
		}
		else{
			mainTextArea.setText("You raise your weapon and block the attack.");
		}
		position = "attackGoblin";
		choice1.setText("Attack");
		choice2.setVisible(false);
	}

	public void villageFightEnd(){
		position = "endQuest1";
		mainTextArea.setText("Eventually, you were able to drive away these mischievous creatures.\n'Thanks buddy, I appreciate the help. I am Brook by the way. And you are?'\nYou introduce yourself and Brook offers to heal your injuries.\nYou thank him and tell him about your quest. Wanting to return the favor, he offers to join you.\nHe then guides you to a small shady shop at the corner.");
		playerHP = warrior.getHP() + 25;
		warrior.setHP(playerHP);
		hpLabelNumber.setText(""+warrior.getHP());

		choice1.setText("Enter shop");
		choice2.setVisible(false);

		//gain healer
		healerButtonPanel.setVisible(true);
	}

	public void hideWatch(){
		position = "hide";
		mainTextArea.setText("You find a place to hide and watch the fight closely. After some struggle, he managed to fight them off, but then collapsed to the ground.");
		choice1.setText("Rush to his aid");
		choice2.setText("Don't bother to help");
	
	}

	public void aidHealer2(){
		position = "aidHealer2";
		mainTextArea.setText("You rushed to his side and managed to drag him on a nearby bench.'Water, please.'");
		choice1.setText("Offer him some water");

	}

	public void notHelp1(){
		position = "noHelp1";
		mainTextArea.setText("The latter started to whimper in pain, asking for some water.");
		choice1.setText("Offer him some water");
		choice2.setText("Walk past him");
		
	}

	public void offerWater(){
		position = "endQuest1";
		mainTextArea.setText("You offer him some water and after a few minutes, he sits up, feeling better.'Thanks buddy, I appreciate the help. I am Brook by the way. And you are?' You introduce yourself and while chatting. You tell him about your quest and he offers to join you. He then guides you to a small shady shop at the corner.\n\nYou found yourself a healer. Brook can fight alongside you and can heal you in case of serious injuries.");
		choice1.setText("Enter shop");
		choice2.setVisible(false);

		//gain healer
		healerButtonPanel.setVisible(true);
	}

	public void walkPast(){
		position = "endQuest1";
		mainTextArea.setText("You walk past him, heading to the small shady shop at the corner.");
		choice1.setText("Enter shop");
		choice2.setVisible(false);
	}

	public void village2(){
		position = "endQuest1";
		mainTextArea.setText("You are back to the village.");
		choice1.setText("Enter shop");
		choice2.setText("View map");
		//choice2.setVisible(false);
	}

	public void shop(){
		position = "shop";
		mainTextArea.setText("Greeted by the old lady, she offers you a look at her goods.\n'Anything that interests you lad?'");
		choice1.setText("Sell");
		choice2.setText("Buy");
		choice2.setVisible(true);
	}

	// Going to forest for the 1st time
	public void forest1(){
		position = "forest";
		location = "Forest";
		locationName.setText(location);
		mapPanel.setVisible(false);
		mainTextPanel.setVisible(true);

		if(goblin.getHP() > 0){
			mainTextArea.setText("You make way to the forest where you see a goblin coming towards you. You can either fight it or run away, by opening your map.");		
			choice1.setText("Fight the goblin");
			choice2.setText("view map");
			choice3.setText("");
			choice4.setText("");
			choice3.setVisible(false);
			choice4.setVisible(false);
		}
		else{
			mainTextArea.setText("The goblin has been defeated. You can advance further in the forest");
			choice3.setText("Advance in forest");
		    choice4.setText("View map");

			choice1.setVisible(false);
		    choice2.setVisible(false);
		}
		
	}

	//Gobin Attack
	public void goblinAttack(){
		position = "goblinAttack";
		monsterPanel.setVisible(true);
		goblinSetup();

		mapButtonPanel.setVisible(false);
		mainTextPanel.setVisible(true);

		int damage = new java.util.Random().nextInt(1,20);
		mainTextArea.setText("The goblin attacked you giving " + damage + " damage.");

		warrior.takeDamage(damage);
		// update hp of player
		hpLabelNumber.setText(""+warrior.getHP());

		choice1.setText("Attack goblin");
		if(location == "Forest"){
			choice2.setText("Retreat");
			choice2.setVisible(true);
		}
		else{
			choice2.setVisible(false);
		}
		choice3.setText("");
		choice4.setText("");
		choice3.setVisible(false);
		choice4.setVisible(false);
		
	}

	public void AttackGoblin(){
		position = "attackGoblin";
		mapButtonPanel.setVisible(false);
		mainTextPanel.setVisible(true);
		int damage = knife.getDamage();

		mainTextArea.setText("You attack the goblin back, giving it " + goblin.getHP() + " damage. The goblin has been defeated.");
		//warrior.attack(goblin);
		goblin.takeDamage(damage);
		// update hp of goblin
		monsterHP.setText("" + goblin.getHP());
		//goblinAttack();
		
		if (goblin.getHP() > 0){
			//mainTextArea.setText("You attack the goblin back, giving it a" + goblin.getHP() + "damage.");
		 	//goblinAttack();
			mainTextArea.setText("You attack the goblin back, giving it " + damage + " damage.");
			choice1.setText("Continue");
			choice2.setText("");
			choice3.setText("");
			choice4.setText("");
			//goblinAttack();
			choice2.setVisible(false);
			choice3.setVisible(false);
			choice4.setVisible(false);
			
		}
		
		else{
			goblin.setHP(0);
			monsterHP.setText("" + goblin.getHP());
			
			if(location == "Forest"){
				position = "endFightForest";
				choice1.setText("Advance in the forest");
				choice2.setText("View map");
				choice2.setVisible(true);
			}
			else if(location == "Village"){
				position = "endFightVillage";
				choice1.setText(">");
				choice2.setVisible(false);
			}
			
			choice1.setVisible(true);
			choice3.setVisible(false);
			choice4.setVisible(false);
		}
	}

	public void forest2(){
		position = "forest2";
		location = "Forest";
		locationName.setText(location);
		mapPanel.setVisible(false);
		mainTextPanel.setVisible(true);
		mapButtonPanel.setVisible(false);

		mainTextArea.setText("You arrive at a crossroad in the forest. You can either go right or left.");		
		choice1.setText("Right");
		choice2.setText("Left");
		choice3.setText("View map");
		choice4.setText("");

		choice1.setVisible(true);
		choice2.setVisible(true);
		choice3.setVisible(true);
		choice4.setVisible(false);
	}

	public void right(){
		position = "right";
		mainTextArea.setText("While venturing deep in the forest, you arrived at the foot of a mountain. The path to the summit appears treacherous, windy through the rocky terrain.Its shadowy twists hinting at the dangers that lie ahead.\r\n" + 
				"(Danger: Proceeding to the mountain will not allow you to go back/open your map. Adventurers are advised to be fully prepared.)\r\n");
		choice1.setText("Climb the mountain");
		choice2.setText("Turn back");
		choice3.setText("");
		choice4.setText("");

		choice1.setVisible(true);
		choice2.setVisible(true);
		choice3.setVisible(false);
		choice4.setVisible(false);
	}



	public void Mountain(){
		position = "mountainFoot";
		location = "Mountain";
		locationName.setText(location);
		mainTextArea.setText("Breathless, you arrive at the top and notice a small dark cave. At the entrance, you find a dirty old torch, but still usable.\r\n");
		choice1.setText("Take the torch");
		choice2.setText("Leave torch");
		choice3.setText("");
		choice4.setText("");

		choice1.setVisible(true);
		choice2.setVisible(true);
		choice3.setVisible(false);
		choice4.setVisible(false);
	}

	public void left(){
		position = "left";
		
		mainTextArea.setText("Dialogue");	

		choice1.setText("Open Chest");
		choice2.setText("Advanced in forest");
		choice3.setText("Turn back");
		choice4.setText("");

		choice1.setVisible(true);
		choice2.setVisible(true);
		choice3.setVisible(true);
		choice4.setVisible(false);

	}
	public void goldchest(){
		position = "goldchest";
		mapButtonPanel.setVisible(false);
		mainTextPanel.setVisible(true);

		
        boolean chestOpened = false;

		if (!chestOpened) {
            if (numOfTimesChestOpened < 1) {   

				Reward reward = generateRandomReward();

				if (reward instanceof Gold) {
					Gold goldReward = (Gold) reward;
					int addGold = goldReward.getAmount();
				// int addgold = new java.util.Random().nextInt(1000) + 1;
				 gold = gold + addGold;
				// // update gold of player
				goldLabelNumber.setText(""+ gold);
				mainTextArea.setText("You opened the chest and found " + addGold + " gold.");
				}
			    else if (reward instanceof Weapon) {
                Weapon weaponReward = (Weapon) reward;
                String weaponName = weaponReward.getName();
                mainTextArea.setText("You opened the chest and found a " + weaponName + ".");
                // Your code to handle the weapon goes here
                // For example, you can equip the weapon to the player or add it to their inventory
            }

                numOfTimesChestOpened++;
            
		} 
			else {
                mainTextArea.setText("The chest is empty...");
                chestOpened = true;
            }
		}

     	choice1.setText("Advance in forest");
		choice2.setText("Turn back");
		choice3.setText("");
		choice4.setText("");

		choice1.setVisible(true);
		choice2.setVisible(true);
		choice3.setVisible(false);
		choice4.setVisible(false);

	}

	class Reward {
		// Base class for rewards
		//serves as a placeholder base class for different types of rewards
		// it acts as a common superclass to enable polymorphism and help organize the different types of rewards.
	}
	
	class Gold extends Reward {
		private int amount;
	
		public Gold(int amount) {
			this.amount = amount;
		}
	
		public int getAmount() {
			return amount;
		}
	}
	
	class Weapon extends Reward {
		private String name;
	
		public Weapon(String name) {
			this.name = name;
		}
	
		public String getName() {
			return name;
		}
	}
	
	private Reward generateRandomReward() {
		Random random = new Random();
		int chance = random.nextInt(100); // Generating a random number between 0 and 99 (inclusive)
	
		if (chance < 70) {
			// 70% chance to get gold
			int randomGold = random.nextInt(1000) + 1; // Generates a random number between 1 and 1000 (inclusive)
			return new Gold(randomGold);
		} else {
			// 30% chance to get a weapon
			String[] weapons = {"Sword", "Axe", "Bow", "Staff", "Dagger"};
			int randomWeaponIndex = random.nextInt(weapons.length);
			return new Weapon(weapons[randomWeaponIndex]);
		}
	}

    public void waterfall(){
		position = "waterfall";
		mapButtonPanel.setVisible(false);
		mainTextPanel.setVisible(true);
		mainTextArea.setText("You advanced further in the forest and arrived at a waterfall.");

		choice1.setText("Go for a swim");
		choice2.setText("Turn back");
		choice3.setText("");
		choice4.setText("");

		choice1.setVisible(true);
		choice2.setVisible(true);
		choice3.setVisible(false);
		choice4.setVisible(false);


	}
    public void swim(){
		position = "swim";
		mapButtonPanel.setVisible(false);
		mainTextPanel.setVisible(true);
		mainTextArea.setText("You decide to go for a swim. However, the water was too deep somethimng somethinmg idk BUT you drowned.");
        choice1.setVisible(false);
		choice2.setVisible(false);
		choice3.setVisible(false);
		choice4.setVisible(false);
	}
	public void saveFile(){
		Path filePath = Paths.get("saveFile.txt");
		List<String> lines = new ArrayList<>();
		lines.add(String.valueOf(warrior.getHP()));
		lines.add(String.valueOf(knife.getName()));
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
	 		e.printStackTrace();
			
	 	}
	 	hpLabelNumber.setText("" + playerHP);
	 	weaponLabelName.setText(weapon);
		
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
					break;
				case "village1":
					switch(yourChoice){
						case "c1": aidHealer1(); break;
						case "c2": hideWatch(); break;
					}
					break;
				case "aidHealer1":
					switch(yourChoice){
						case "c1": dodge(); break;
						case "c2": blockAttack(); break;
						case "c3": AttackGoblin(); break;
					}
					break;
				case "endFightVillage":
					switch(yourChoice){
						case "c1": villageFightEnd(); break;
					}
					break;
				case "hide":
					switch(yourChoice){
						case "c1": aidHealer2(); break;
						case "c2": notHelp1(); break;
					}
					break;
				case "aidHealer2":
					switch(yourChoice){
						case "c1": offerWater(); break;
					}
					break;
				case "noHelp1":
					switch(yourChoice){
						case "c1": offerWater(); break;
						case "c2": walkPast(); break;
					}
					break;
				case "endQuest1":
					switch(yourChoice){
						case "c1": shop(); break;
						case "c2": Map(); break;
					}
					break;
				case "forest":   
				    switch(yourChoice){     
						case "c1": goblinAttack(); break;
						case "c2": Map(); break;
						case "c3": forest2(); break;
						case "c4": Map(); break;
					}
					break;
				case "goblinAttack":
                    switch(yourChoice){
			     		case "c1":AttackGoblin();break;
						case "c2": Map(); break;
				 	}
					break;
				case "attackGoblin":
					switch(yourChoice){
						case "c1":goblinAttack(); break;
					}
					break;
				case "endFightForest":
					switch(yourChoice){
						case "c1": forest2(); break;
						case "c2": Map(); break;
					}
					break;
				case "forest2":   
				    switch(yourChoice){     
						case "c1": right(); break;
					    case "c2": left(); break;
						case "c3": Map(); break;
					}
					break;
				case "right":   
				    switch(yourChoice){     
						case "c1": Mountain(); break;
					    //case "c2": left(); break;
						case "c2": forest2(); break;
					}
					break;
				case "left":   
				    switch(yourChoice){     
						case "c1": goldchest(); break;
						case "c2": waterfall(); break;
						case "c3": forest2(); break;
					}
					break;
				case "goldchest":   
				    switch(yourChoice){     
						case "c1": waterfall(); break;
						case "c2": forest2(); break;
					}
					break;
				case "waterfall":   
				    switch(yourChoice){     
						case "c1": swim(); break;
						case "c2": forest2(); break;
					}
					break;
				case "mountainFoot":
					switch(yourChoice){
						//case "c1": 
						case "c2": Map(); break;
					}
					break;
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
				textPanel.setVisible(false);
		        inputPanel.setVisible(false);
			    Gameplay("start");
        }
    }
}
