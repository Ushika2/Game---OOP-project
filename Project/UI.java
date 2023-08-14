import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class UI{

    JFrame window;
	Container con;
    JPanel titleNamePanel, startButtonPanel, choiceButtonPanel, mapButtonPanel, mainTextPanel, playerPanel,textPanel,inputPanel, monsterPanel, healerButtonPanel, mageButtonPanel, warriorButtonPanel; //panel to display text;
	JLabel titleNameLabel, hpLabel, hpLabelNumber, weaponLabel, weaponLabelName,characterLabel, characterLabelName,locationLabel, locationName, monsterLabel, monsterName, monsterHP,monsterHPLabel, textLabel,goldLabel,goldLabelNumber;
	Font titleFont = new Font("Times New Roman", Font.PLAIN, 90);
	Font normalFont = new Font("Times New Roman", Font.PLAIN, 18);
	Font normaltextFont = new Font("Times New Roman", Font.PLAIN, 20);  //custom font
	JButton startButton, continueButton, choice1, choice2, choice3, choice4, mapButton, enterButton, healerButton, mageButton, warriorButton; //add button
	JTextArea mainTextArea,mapTextArea;
	
	// Creating instances
    TitleScreenHandler tsHandler = new TitleScreenHandler();
    ContinueScreenHandler conHandler = new ContinueScreenHandler();
    MapScreenHandler mapHandler = new MapScreenHandler();
	HealerScreenHandler healerHandler = new HealerScreenHandler();
	MageScreenHandler mageHandler = new MageScreenHandler();
	WarriorScreenHandler warriorHandler = new WarriorScreenHandler();

	Storyline story;  //declaring variable 'story'

    public void titleUI(){

        //Creating WINDOW frame
        window = new JFrame();
		window.setSize(800, 600);
		//window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//disable the default behavior of the close button. 
		//allow to manually call the dispose() method whenever you want to close the window programmatically.

		window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		window.getContentPane().setBackground(Color.black);
        window.setLayout(null);
		
        // Add container to the window
        con = window.getContentPane();

		// initializes the story variable with a new instance of the Storyline class, passing con and this as arguments to the constructor.
		story = new Storyline(con,this);
		
	    // Creating TITLE SCREEN
        titleNamePanel = new JPanel();
		titleNamePanel.setBounds(100, 100, 600, 150);
		titleNamePanel.setBackground(Color.black);
		titleNameLabel = new JLabel("ADVENTURE");
		titleNameLabel.setForeground(Color.white);
		titleNameLabel.setFont(titleFont);	
        //titleNamePanel.setLayout(new BorderLayout());
        //titleNamePanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        // Creating START BUTTON
		startButtonPanel = new JPanel();
		startButtonPanel.setBounds(300, 400, 200, 50);
		startButtonPanel.setBackground(Color.black);

        //startButtonPanel.setLayout(new BorderLayout());
        //startButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
       
		startButton = new JButton("START  ");
		startButton.setToolTipText(null);
		startButton.setBackground(Color.black);
		startButton.setForeground(Color.white);
		startButton.setFont(normalFont);
		startButton.setBorder(null);
		startButton.addActionListener(tsHandler);
		startButton.setFocusPainted(false);

		// Creating CONTINUE BUTTON
		continueButton = new JButton("CONTINUE");
		continueButton.setToolTipText(null); 
		continueButton.setBackground(Color.black);
		continueButton.setForeground(Color.white);
		continueButton.setBorder(null);
		continueButton.setFont(normalFont);
		continueButton.addActionListener(conHandler);
		continueButton.setFocusPainted(false);
		continueButton.setActionCommand("continue");
		//continueButton.setBorder(null);
    
		// Adding label & buttons to respective panels
		titleNamePanel.add(titleNameLabel);
		startButtonPanel.add(startButton);
		startButtonPanel.add(continueButton);

		con.add(titleNamePanel);  //Add panels to window frame
		con.add(startButtonPanel);

        window.setVisible(true);

    }

	public void choices(){

		// Creating choices panel & 4 choice buttons
		choiceButtonPanel = new JPanel();
		choiceButtonPanel.setBounds(250, 370, 300, 150);
		choiceButtonPanel.setBackground(Color.black);
		//Container con;
		
		con.add(choiceButtonPanel);

		choice1 = createChoiceButton("");
		choice1.setActionCommand("c1");  //to differentiate between the 4 choice button

		choice2 = createChoiceButton("");
		choice2.setActionCommand("c2");

		choice3 = createChoiceButton("");
		choice3.setActionCommand("c3");

		choice4 = createChoiceButton("");
		choice4.setActionCommand("c4");

		choice1.setText(""); // Set text after creating the buttons
		choice2.setText(""); 
		choice3.setText(""); 
		choice4.setText(""); 

		choice1.setVisible(true);  //Make choice button visible
		choice2.setVisible(true);
		choice3.setVisible(true);
		choice4.setVisible(true);

		choiceButtonPanel.setVisible(false);
	}
	
	//ENCAPSULATION
	private JButton createChoiceButton(String text){ //creates and configures a JButton component for displaying choices in GUI
		JButton button = new JButton(text); //Button creation
		button.setToolTipText(null);  //Button customization
		button.setBackground(Color.black);
		button.setForeground(Color.white);
		button.setFont(normalFont);
		button.setFocusPainted(false);
		button.addActionListener(e -> handleChoiceButtonClick(e));  //Button action listener
		choiceButtonPanel.add(button);
		return button;
	}

    public void Gameplay(String startOrContinue){

		titleNamePanel.setVisible(false);
		startButtonPanel.setVisible(false);

		// Creating main text panel
		mainTextPanel = new JPanel();
		mainTextPanel.setBounds(100,100, 600, 250);
		mainTextPanel.setBackground(Color.black); 
		//mainTextPanel.setBackground(Color.white);
		con.add(mainTextPanel);

		// Creating player panel containg hp label, character name, weapon, location and gold amount
		playerPanel = new JPanel();
		playerPanel.setBounds(70, 5, 630, 50);
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
		playerPanel.add(weaponLabel);
		weaponLabelName = new JLabel();
		weaponLabelName.setFont(normalFont);
		weaponLabelName.setForeground(Color.white);
		playerPanel.add(weaponLabelName);
		locationLabel = new JLabel(" |Location:");  // adding location
		locationLabel.setFont(normalFont);
		locationLabel.setForeground(Color.white);
		playerPanel.add(locationLabel);
		locationName = new JLabel();
		locationName.setFont(normalFont);
		locationName.setForeground(Color.white);
		playerPanel.add(locationName);
		goldLabel = new JLabel(" |Gold:");  // adding Gold
		goldLabel.setFont(normalFont);
		goldLabel.setForeground(Color.white);
		playerPanel.add(goldLabel);
		goldLabelNumber = new JLabel();
		goldLabelNumber.setFont(normalFont);
		goldLabelNumber.setForeground(Color.white);
		playerPanel.add(goldLabelNumber);

		// Creating monster panel containing monster name and Hp
		monsterPanel = new JPanel();
		monsterPanel.setBounds(70, 500, 350, 50);
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

		if(startOrContinue.equals("start")){  //condition if player choose to start the game from begining 
			story.playerSetup();  //method calls
            story.intro();
		}
		
        choices();
       
		// choice4.setContentAreaFilled(false);  // disable highlighting on press

		// Creating Map button panel
		mapButtonPanel = new JPanel();
		mapButtonPanel.setToolTipText(null);
		mapButtonPanel.setBounds(500, 500, 300, 150);
		mapButtonPanel.setBackground(Color.black); 

		mapButton = new JButton("View MAP");
		mapButton.setToolTipText(null);
		mapButton.setBackground(Color.black); 
		mapButton.setForeground(Color.white);
		mapButton.setFont(normalFont);
		mapButton.addActionListener(mapHandler);
		mapButton.setFocusPainted(false);
		mapButtonPanel.add(mapButton);
		con.add(mapButtonPanel);
		window.setVisible(true);
        
		// Creating Healer button panel to allow for character switch
		healerButtonPanel = new JPanel();
		healerButtonPanel.setBounds(500, 450, 300, 150);
		healerButtonPanel.setBackground(Color.black); 

		healerButton = new JButton("Healer");
		healerButton.setBackground(Color.black);
		healerButton.setForeground(Color.white);
		healerButton.setFont(normalFont);
		healerButton.addActionListener(healerHandler);
		healerButton.setFocusPainted(false);
		healerButtonPanel.add(healerButton);
		con.add(healerButtonPanel);
		window.setVisible(true);
		healerButtonPanel.setVisible(false);

		// Creating Mage button panel to allow for character switch
		mageButtonPanel = new JPanel();
		mageButtonPanel.setBounds(500, 400, 300, 150);
		mageButtonPanel.setBackground(Color.black);

		mageButton = new JButton("Mage");
		mageButton.setBackground(Color.black); 
		mageButton.setForeground(Color.white);
		mageButton.setFont(normalFont);
		mageButton.addActionListener(mageHandler);
		mageButton.setFocusPainted(false);
		mageButtonPanel.add(mageButton);
		con.add(mageButtonPanel);
		window.setVisible(true);
		mageButtonPanel.setVisible(false);

		// Creating Warrior button panel to allow for character switch
		warriorButtonPanel = new JPanel();
		//warriorButtonPanel.setBounds(600, 350, 200, 50);
	    warriorButtonPanel.setBounds(500, 500, 300, 150);
		warriorButtonPanel.setBackground(Color.black); 

		warriorButton = new JButton("Warrior");
		warriorButton.setBackground(Color.black);
		warriorButton.setForeground(Color.white);
		warriorButton.setFont(normalFont);
		warriorButton.addActionListener(warriorHandler);
		warriorButton.setFocusPainted(false);
		warriorButtonPanel.add(warriorButton);
		con.add(warriorButtonPanel);
		window.setVisible(true);
		warriorButtonPanel.setVisible(true);

		if(startOrContinue.equals("continue")){ //condition if player choose to load the game from previous save 				 
			story.loadData();
		}

	}
	
	//Methods that implements the action listener which handle actions triggered by button clicks

    public class TitleScreenHandler implements ActionListener{
		public void actionPerformed(ActionEvent event){
		   Gameplay("start");
        
		}
    }

	public class ContinueScreenHandler implements ActionListener{
		public void actionPerformed(ActionEvent event){
				titleNamePanel.setVisible(false);
				startButtonPanel.setVisible(false);
				Gameplay("continue"); // Load the saved game data
				
		}
	}

    public class MapScreenHandler implements ActionListener{
		public void actionPerformed(ActionEvent event){
			story.Map();
		}
	}

	public class HealerScreenHandler implements ActionListener{
		public void actionPerformed(ActionEvent event){
			story.character = "healer";
			story.healerSetup();
		}
	}

	public class MageScreenHandler implements ActionListener{
		public void actionPerformed(ActionEvent event){
			story.character = "mage";
			story.mageSetup();
		}
	}

	public class WarriorScreenHandler implements ActionListener{
		public void actionPerformed(ActionEvent event){
			story.character = "warrior";
			story.warriorSetup();
		}
	}
    
	// method also takes into account for different choices at different part of the game and calls appropraite methods for the storyline
	private void handleChoiceButtonClick(ActionEvent event) {
		String yourChoice = event.getActionCommand();
		switch (story.position){
			case "dead":
				switch (yourChoice) {
					case "c1": story.Restart();break; 
					case "c2": story.continueGame(); break;
				}
				break;
			case "map":
				switch (yourChoice) {
					case "c1": story.townn(); break;
					case "c2": if(story.forestCount == 0){story.goblinSetup();story.forest1();} else{story.endForest1();}; break;
					case "c3": if(story.villageCount == 0){story.villagegoblinSetup();story.village1();} else{story.village2();}; break;
					case "c4": if(story.riverCount == 0){story.river1();} else{story.river2();}; break;
				}
				break;
			case "townn":
				switch(yourChoice){
					case "c1": story.saveGame();story.saved();break;
					case "c2": story.Map(); break;
				}
				break;
			case "gameSaved":
				switch(yourChoice){
					case "c1": story.Map(); break;
				}
				break;
			
			case "village1":
				switch(yourChoice){
					case "c1": story.aidHealer1(); break;
					case "c2": story.hideWatch(); break;
				}
				break;
			case "aidHealer1":
				switch(yourChoice){
					case "c1": story.dodge(); break;
					case "c2": story.blockAttack(); break;
					//case "c3": story.villageAttackGoblin(); break;
				}
				break;
			case "villagegoblinAttack":
				switch(yourChoice){
					case "c1":story.villageAttackGoblin();break;
					case "c2": story.Map(); break;
					case "c3":story.dead();break;
				}
				break;
			case "villageattackGoblin":
				switch(yourChoice){
					case "c1":story.villagegoblinAttack(); break;
					case "c3":story.dead(); break;
				}
				break;
			case "endFightVillage":
				switch(yourChoice){
					case "c1": story.villageFightEnd(); break;
				}
				break;
			case "hide":
				switch(yourChoice){
					case "c1": story.aidHealer2(); break;
					case "c2": story.notHelp1(); break;
				}
				break;
			case "aidHealer2":
				switch(yourChoice){
					case "c1": story.offerWater(); break;
				}
				break;
			case "noHelp1":
				switch(yourChoice){
					case "c1": story.offerWater(); break;
					case "c2": story.walkPast(); break;
				}
				break;
			case "endQuest1":
				switch(yourChoice){
					case "c1": story.shop(); break;
					case "c2": story.Map(); break;
				}
				break;
			case "shop":
				switch(yourChoice){
					case "c1": story.sell(); break;
					case "c2": story.buy(); break;
					case "c3": story.Map(); break;
				}
				break;
			case "sell":
				switch(yourChoice){
					case "c1": story.sellItem("GoblinTeeth"); break;
					case "c2": story.sellItem("WraithCloth"); break;
					case "c3": story.sellItem("OrgreClaw"); break;
					case "c4": story.shop(); break;
				}
				break;
			case "sellItem":
				switch(yourChoice){
					case "c1": story.shop(); break;
				}
				break;

				case "buy":
				switch(yourChoice){
					case "c1": story.weapons(); break;
					case "c2": story.spells(); break;
					case "c3": story.potions(); break;
					case "c4": story.shop(); break;
				}
				break;

			case "chooseWeapon":
				switch(yourChoice){
					case "c1": story.buyWeapons("Sword"); break;
					case "c2": story.buyWeapons("Axe");  break;
					case "c4": story.shop(); break;
				}
				break;
			case "buyWeapon":
				switch(yourChoice){
					case "c1": story.buy(); break;
				}
				break;
			case "buySpell":
				switch(yourChoice){
					case "c1": story.buySpells("Fire"); break;
					case "c2": story.buySpells("Lightning");  break;
					case "c3": story.buySpells("Frost"); break;
					case "c4": story.shop(); break;
				}
				break;
			case "chooseSpell":
				switch(yourChoice){
					case "c1": story.buy(); break;
				}
				break;
			case "buyPotion":
				switch(yourChoice){
					case "c1": story.buyPotions("HealingPotion"); break;
					case "c2": story.buyPotions("CuringPotion"); break;
					case "c4": story.shop(); break;
				}
				break;
			case "choosePotion":
				switch(yourChoice){
					case "c1": story.buy(); break;
				}
				break;
			case "river1":
				switch(yourChoice){
					case "c1": story.investigate(); break;
					case "c2": story.Map(); break;
				}
				break;
			case "investigate":
				switch(yourChoice){
					case "c1": story.saveRiver(); break;
					case "c2": story.usePotion("CuringPotion"); break;
					case "c3": story.Map(); break;
				}
				break;
			case "saveRiver":
				switch(yourChoice){
					case "c1": story.dead();break; //player dies
					case "c2": story.healerSetup(); break;  //switch to healer
					case "c3": story.saveBoy(); break;
					case "c4": story.NosaveBoy(); break;
				}
				break;
			case "potion":
				switch(yourChoice){
					case "c1": story.Map(); break;
					case "c2": story.investigate(); break;
					case "c3": story.saveRiver2(); break;
					case "c4": story.saveBoy(); break;
				}
				break;
			case "endRiver":
				switch(yourChoice){
					case "c1": story.rest(); break;
					case "c2": story.Map(); break;
					// case "c1": story.Map(); break;
					// case "c2": story.rest(); break;
				}
				break;
			case "rest":
				switch(yourChoice){
					case "c1": story.Map(); break;
				}
				break;
			case "forest":   
				    switch(yourChoice){     
						case "c1": story.goblinAttack(); break; 

					}
					break;
			case "endforest1":
			     switch(yourChoice){     
						case "c1": story.forest2(); break;
						case "c2": story.Map(); break;
					}
					break;
			case "goblinAttack":
				switch(yourChoice){
					case "c1":if(story.character == "mage"){story.mageAttack();}else{story.AttackGoblin();};break;
					case "c2": story.dead(); break;
					case "c4": story.heal(); break;
				}
				break;
			case "attackGoblin":
				switch(yourChoice){
					case "c1":story.goblinAttack(); break;
					case "c4": story.heal(); break;
				}
				break;
			case "endFightForest":
				switch(yourChoice){
					case "c1": story.forest2(); break;
					case "c2": story.Map(); break;
				}
				break;
			case "forest2":   
				switch(yourChoice){     
					case "c1": story.right(); break;
					case "c2": story.left(); break;
					case "c3": story.Map(); break;
				}
				break;
			case "right":   
				switch(yourChoice){     
					case "c1": if(story.orgreCount == 0){story.OrgreSetup();story.orgre();}else{story.Mountain();} break;  //Orgre setup
					case "c2": story.forest2(); break;
				}
				break;
			case "orgre":   
				switch(yourChoice){     
					case "c1": if(story.character == "mage"){story.mageAttack();}else{story.attackMonster("orgre");}; break;
					case "c2": story.Map(); break;
				}
				break;
			case "attackOrgre":
				switch(yourChoice){     
					case "c1": story.monsterAttack("orgre"); break;
					case "c2": story.Mountain(); break;
					case "c3": story.Map(); break;
					case "c4": story.heal(); break;
				}
				break;
			case "orgreAttack":
				switch(yourChoice){     
					case "c1": if(story.character == "mage"){story.mageAttack();}else{story.attackMonster("orgre");}; break;
					case "c2": story.forest2(); break;
					case "c3": story.dead(); break;
					case "c4": story.heal(); break;
				}
				break;
			case "heal":
				switch(yourChoice){     
					case "c1": story.heal(); break;
				}
				break;
			case "left":   
				switch(yourChoice){     
					case "c1": story.goldchest(); break;
					case "c2": if(story.waterfallCount == 0){story.WraithSetup();story.waterfall();} else{story.waterfall2();} break; //Wraith setup
					case "c3": story.forest2(); break;
				}
				break;
			case "goldchest":   
				switch(yourChoice){     
					case "c1": if(story.waterfallCount == 0){story.WraithSetup();story.waterfall();} else{story.waterfall2();} break;
					case "c2": story.forest2(); break;
				}
				break;
			case "waterfall":   
				switch(yourChoice){     
					case "c1": story.swim(); break;
					case "c2": story.forest2(); break;
				}
				break;

			case "swim":   
				switch(yourChoice){     
					case "c1": story.dead(); break;
				}
				break;
			case "waterfallfight":   
				switch(yourChoice){     
					case "c1": if(story.character == "mage"){story.mageAttack();}else{story.attackMonster("wraith");}; break;
					case "c2": story.forest2(); break;
				}
				break;
			case "attackWraith":
				switch(yourChoice){     
					case "c1": story.monsterAttack("wraith"); break;
					case "c2": story.waterfall2();break;
					//case "c3": story.forest2(); break;
					case "c4": story.heal(); break;
				}
				break;
			case "wraithAttack":
				switch(yourChoice){     
					case "c1": if(story.character == "mage"){story.mageAttack();}else{story.attackMonster("wraith");}; break;
					case "c2": story.forest2(); break;
					case "c3": story.dead();break;
					case "c4": story.heal(); break;
				}
				break;
			case "mountainTop":
				switch(yourChoice){
					case "c1": story.torch=1; story.cave(); break;
					case "c2": story.cave(); break;
					case "c3": story.Map(); break;
				}
				break;
			case "cave":
				switch(yourChoice){
					case "c1": story.statues(); break;
					case "c2": story.dead(); break;
				}
				break;	
			case "statues":
				switch(yourChoice){
					case "c1": story.WolfSetup();story.statueRadiance(); break;
					case "c2": story.DragonSetup();story.statueVigor();break;
				}
				break;
			case "puzzle":
				switch(yourChoice){
					case "c1": story.inside(); break;
				}
				break;
			case "puzzleAttack":
				switch(yourChoice){
					case "c1": story.monsterAttack("wolf"); break;
				}
				break;
			case "attackWolf":
				switch(yourChoice){     
					case "c1": story.monsterAttack("wolf"); break;
					case "c2": story.DragonSetup();story.inside(); break;
					case "c4": story.heal(); break;
				}
				break;
			case "wolfAttack":
				switch(yourChoice){     
					case "c1": if(story.character == "mage"){story.mageAttack();}else{story.attackMonster("wolf");}; break;
					case "c2": story.dead(); break;
					case "c4": story.heal(); break;
				}
				break;
			case "inside":
				switch(yourChoice){     
					case "c1": if(story.character == "mage"){story.mageAttack();}else{story.attackMonster("dragon");}; break; 
				}
				break;
			case "attackDragon":
				switch(yourChoice){     
					case "c1": story.monsterAttack("dragon"); break;
					case "c2": story.end(); break;
					case "c4": story.heal(); break;
				}
				break;
			case "dragonAttack":
				switch(yourChoice){   
					case "c1": if(story.character == "mage"){story.mageAttack();}else{story.attackMonster("dragon");}; break;
					case "c2": story.dead(); break;  
					case "c4": story.heal(); break;
				}
				break;
			case "mageAttack":
				switch(yourChoice){   
					case "c1": 
						story.attack = "blast";
						if(story.location == "Forest"){story.AttackGoblin(); break;}
						if(story.location == "Waterfall"){story.attackMonster("wraith"); break;}
						if(story.location == "Mountain"){story.attackMonster("orgre"); break;}
						if(story.location == "Cave"){story.attackMonster("dragon"); break;}
						if(story.location == "left"){story.attackMonster("wolf"); break;}
						break;

					case "c2": 
						story.attack = "fire";
						if(story.location == "Forest"){story.AttackGoblin(); break;}
						if(story.location == "Waterfall"){story.attackMonster("wraith"); break;}
						if(story.location == "Mountain"){story.attackMonster("orgre"); break;}
						if(story.location == "Cave"){story.attackMonster("dragon"); break;}
						if(story.location == "left"){story.attackMonster("wolf"); break;}
						break;
						 
					case "c3": 
						story.attack = "frost";
						if(story.location == "Forest"){story.AttackGoblin(); break;}
						if(story.location == "Waterfall"){story.attackMonster("wraith"); break;}
						if(story.location == "Mountain"){story.attackMonster("orgre"); break;}
						if(story.location == "Cave"){story.attackMonster("dragon"); break;}
						if(story.location == "left"){story.attackMonster("wolf"); break;}
						
					case "c4": 
						story.attack = "light";
						if(story.location == "Forest"){story.AttackGoblin(); break;}
						if(story.location == "Waterfall"){story.attackMonster("wraith"); break;}
						if(story.location == "Mountain"){story.attackMonster("orgre"); break;}
						if(story.location == "Cave"){story.attackMonster("dragon"); break;}
						if(story.location == "left"){story.attackMonster("wolf"); break;}	
				}
				break;
		}
	}
}

