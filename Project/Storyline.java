import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.io.*;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;

import character.*;
import spell.*;
import weapon.*;
import monster.*;

public class Storyline{
	
    private JPanel mapPanel;          
     
	Container con;

	Font normalFont = new Font("Times New Roman", Font.PLAIN, 18);
	
    String playerName, weapon, position, location, goblinName,  wraithName, orgreName, wolfName, dragonName, character;
    int playerHP=100, goblinHP, orgreHP, wolfHP, wraithHP, dragonHP, gold, turn=0;

	int chestForest = 0, forestCount = 0, villageCount = 0, riverCount = 0, waterfallCount = 0, HealPotionCount = 0, CurePotionCount = 0, healerCount = 0, mageCount = 0, torch = 0;
	int swordCount = 0, axeCount = 0, bowCount = 0;

	int goblinTeeth = 0, wolfSkin = 0, orgreClaw = 0, wraithCloth = 0;

    // Creating weapons
	Axe axe = new Axe("axe",20);  //goblin weapon in village
    Knife knife = new Knife("knife",10);
	Sword sword = new Sword("sword", 25);
	Bow bow = new Bow("Bow", 25);
	Grimoire grimoire = new Grimoire("grimoire",20);

	// Creating spells
	Blast blast = new Blast("blast",10);
	Fire fire = new Fire("fire",18);
	Frost frost = new Frost("frost",12);
	Lightning lightning = new Lightning("lightning", 15);

	// Creating characters
	Warrior warrior = new Warrior("warrior",playerHP, knife, null);
	Healer healer = new Healer("healer", playerHP, 10, null);
	Archer archer = new Archer("archer",playerHP,null);
	Mage mage = new Mage("mage",playerHP,blast,grimoire);

	// Creating monsters
	Goblin goblin = new Goblin("goblin",13);
	Orgre orgre = new Orgre("orgre", 20);
	Wolf wolf = new Wolf("wolf",23);
	Wraith wraith = new Wraith("wraith", 35);
	Dragon dragon = new Dragon("dragon",150);

	

    public UI ui;          

    public Storyline(Container container,UI ui) {     ///to avoid the null pointer exception

		this.con = container;
        this.ui = ui;
		
	}

	//CHARACTER SETUP

    public void playerSetup(){ 
		location = "Town";
        character = "warrior";
		ui.weaponLabelName.setText(knife.getName());
		if(swordCount == 1){
			ui.weaponLabelName.setText(knife.getName() +""+ sword.getName());
		}
		playerHP = warrior.getHP();
		ui.hpLabelNumber.setText("" + playerHP);
		ui.locationName.setText(location);
		playerName = warrior.getName();
		ui.goldLabelNumber.setText(""+ gold);
		ui.characterLabelName.setText("" + warrior.getName());
		
	}

	public void healerSetup(){
		character = "healer";
		ui.characterLabelName.setText("" + healer.getName());
		if(axeCount == 1){
			ui.weaponLabelName.setText(""+ axe.getName());
		}
		else if (swordCount == 1){
			ui.weaponLabelName.setText(""+ knife.getName());
		}
		if(location == "River"){
			saveRiver();
		}
	}

	public void heal(){
		playerHP += healer.getHealingPower();
		ui.hpLabelNumber.setText("" + playerHP);
		warrior.setHP(playerHP);
	}

	public void mageSetup(){
		character = "mage";
		ui.characterLabelName.setText("" + mage.getName());
		ui.weaponLabelName.setText(grimoire.getName());
	}

	public void warriorSetup(){
		character = "warrior";
		ui.characterLabelName.setText("" + warrior.getName());
		ui.weaponLabelName.setText(knife.getName());
		if(swordCount == 1){
			ui.weaponLabelName.setText(knife.getName() +""+ sword.getName());
		}
		
	}

	//-----MONSTER SETUP-----

	// Goblin setup
	public void goblinSetup(){
		ui.monsterName.setText(goblin.getName());
		goblinHP = 13;
		ui.monsterHP.setText("" + goblinHP);
		goblinName = goblin.getName();
	}

	//Village Goblin setup
	public void villagegoblinSetup(){
		ui.monsterName.setText(goblin.getName());
		goblinHP = 13;
		ui.monsterHP.setText("" + goblinHP);
		goblinName = goblin.getName();
		
	}

	//Orgre setup
	public void OrgreSetup(){
		ui.monsterName.setText(orgre.getName());
		orgreName = orgre.getName();
		ui.monsterHP.setText("" + orgre.getHP());
		orgreHP = orgre.getHP();
		
	}

	 //Wolf setup
	 public void WolfSetup(){
		ui.monsterName.setText(wolf.getName());
		wolfName = wolf.getName();
		ui.monsterHP.setText("" + wolf.getHP());
		wolfHP = wolf.getHP();
		
	 }

	//Wraith setup
	public void WraithSetup(){
		ui.monsterName.setText(wraith.getName());
		wraithName = wraith.getName();
		ui.monsterHP.setText("" + wraith.getHP());
		wraithHP = wraith.getHP();
		
	}

	//Dragon setup
	public void DragonSetup(){
		ui.monsterName.setText(dragon.getName());
		dragonName = dragon.getName();
		ui.monsterHP.setText("" + dragon.getHP());
		dragonHP = dragon.getHP();
		
	}


	//-----SAVE AND LOAD GAME DATA-----

	public void saveGame(){
		try{
			BufferedWriter bw = new BufferedWriter(new FileWriter("saveFile.txt"));
			bw.write(""+warrior.getHP());
			bw.newLine();
			bw.write(warrior.getName());
			bw.newLine();
			bw.write(knife.getName());
			bw.newLine();
			bw.write(location);			
			bw.newLine();
			bw.write(""+gold);
			bw.newLine();	
			bw.write(goblinName);			
			bw.newLine();
			bw.write(""+goblin.getHP());	
			bw.newLine();
			bw.write(""+villageCount);
			bw.newLine();
			bw.write(""+healerCount);
			bw.newLine();
			bw.write(""+ mageCount);
			bw.newLine();
			bw.write(""+ riverCount);
			bw.newLine();
			bw.write(""+ chestForest);
			bw.newLine();
			bw.write(""+ axeCount);
			bw.newLine();
			bw.write(""+ forestCount);
			

			bw.close();
		}
		catch(Exception e){
			e.printStackTrace();
		   
		}
		System.out.println("saved yes");
	}

	public void loadData(){
		try{
			BufferedReader br = new BufferedReader(new FileReader("saveFile.txt"));
			playerHP = Integer.parseInt(br.readLine());
			playerName = br.readLine();
			weapon = br.readLine();
			location = br.readLine();
			gold = Integer.parseInt(br.readLine());
			goblinName = br.readLine();
			goblinHP = Integer.parseInt(br.readLine());
           // goblin.setHP(goblinHP);
			villageCount = Integer.parseInt(br.readLine());
			healerCount = Integer.parseInt(br.readLine());
			mageCount = Integer.parseInt(br.readLine());
			riverCount = Integer.parseInt(br.readLine());
			chestForest  = Integer.parseInt(br.readLine());
			axeCount = Integer.parseInt(br.readLine());
			forestCount = Integer.parseInt(br.readLine());

			br.close();
		}
		catch(Exception e){    //handle exception like NumberFormatException e and IOException e
			e.printStackTrace();
			
		// Provide a user-friendly error message
		 ui.mainTextArea.setText("Failed to load the game. Please check your save file and try again.");
		   
		}
		character = "warrior";
		ui.hpLabelNumber.setText("" + playerHP);
		ui.characterLabelName.setText("" + warrior.getName());
		ui.weaponLabelName.setText(weapon);
		ui.locationName.setText(location);
		ui.goldLabelNumber.setText(""+ gold);
		ui.monsterName.setText(""+ goblin.getName());
		ui.monsterHP.setText("" + goblin.getHP());

		if(healerCount == 1){
			ui.healerButton.setVisible(true);
			ui.healerButtonPanel.setVisible(true);
		}
		if(mageCount == 1){
			ui.mageButton.setVisible(true);
			ui.mageButtonPanel.setVisible(true);
		}

	    loadgame();
		
	}
   
    public void intro(){
		// Begining of story - town
		position = "intro";
		ui.mainTextArea = new JTextArea("At the entrance of the hometown, you see someone standing. You approach the person. Hello Player, I am the chief of this town. We need your help. The safety of the town is threatened by a monster. To ensure the safety of the townfolks you must find the monster and defeat it. You may begin your quest by viewing the map."); // to change
		ui.mainTextArea.setBounds(100, 100, 600, 250);
		ui.mainTextPanel.setBackground(Color.black);
		ui.mainTextArea.setForeground(Color.black);  //maybe to change later
		//startButton.setFont(normalFont);
		ui.mainTextArea.setLineWrap(true);
		ui.mainTextArea.setWrapStyleWord(true); 
		ui.mainTextArea.setEditable(false); 	
		ui.mainTextPanel.add(ui.mainTextArea);
		 
	}

	public void loadgame(){
		position = "continue";
		ui.mainTextArea = new JTextArea("Game loaded successfully"); 
		ui.mainTextArea.setBounds(100, 100, 600, 250);
		ui.mainTextPanel.setBackground(Color.black);
		ui.mainTextArea.setForeground(Color.black);  //maybe to change later
		//startButton.setFont(normalFont);
		ui.mainTextArea.setLineWrap(true);
		ui.mainTextArea.setWrapStyleWord(true); 
		ui.mainTextArea.setEditable(false); 	
		ui.mainTextPanel.add(ui.mainTextArea);
		System.out.println("load game"+goblinHP);

	}

	public void Map() {
		position = "map";

		ui.choiceButtonPanel.setVisible(true);
		ui.mainTextPanel.setVisible(false); // Hide the main text panel
		ui.mapButtonPanel.setVisible(false); //hide the view map button when on the map
		ui.monsterPanel.setVisible(false);

		mapPanel = new JPanel();
		mapPanel.setBounds(100, 100, 600, 250);
		mapPanel.setBackground(Color.black);
		con.add(mapPanel);

		ui.mapTextArea = new JTextArea("Choose where to go.");
		ui.mapTextArea.setBounds(100, 150, 600, 250);
		ui.mapTextArea.setBackground(Color.red);
		ui.mapTextArea.setForeground(Color.white);
		ui.mapTextArea.setFont(normalFont);
		ui.mapTextArea.setLineWrap(true);
		ui.mapTextArea.setWrapStyleWord(true);
		ui.mapTextArea.setEditable(false);

		mapPanel.add(ui.mapTextArea);
		mapPanel.setVisible(true); // Show the map panel

		ui.choice1.setText("Town");
		ui.choice2.setText("Forest"); // in forest, you go to river, mountain or back to town
		ui.choice3.setText("Village");
		ui.choice4.setText("River");
		// choice 5 for moauntain
		
		//ensure that all choices appear when map is called
		ui.choice1.setVisible(true);
		ui.choice2.setVisible(true);
		ui.choice3.setVisible(true);
		ui.choice4.setVisible(true);
		//choice.story.position = position;
	}


	// Going to town for 1st time
	public void townn(){
		position = "townn";
		location = "Town";
		ui.locationName.setText(location);
		mapPanel.setVisible(false);
		ui.mainTextPanel.setVisible(true);

		ui.mainTextArea.setText("You are at the town.\nYou see a checkpoint to save the game.");		
		ui.choice1.setText("Save game");
		ui.choice2.setText("View map");
		ui.choice3.setText("end");
		ui.choice4.setText("");

		ui.choice3.setVisible(true);
		ui.choice4.setVisible(false);
	}

	
	// Going to village for 1st time
	public void village1(){
		villageCount = 1;
		position = "village1";
		location = "Village";
		ui.locationName.setText(location);
		mapPanel.setVisible(false);
		ui.mainTextPanel.setVisible(true);

		ui.mainTextArea.setText("You decide to go take a look at the forlorn village. Upon arrival, you find the village under attack by a horde of goblins. Amidst the chaos, you catch sight of a solitary figure battling the malignant goblins.");		
		ui.choice1.setText("Join him in the fight");
		ui.choice2.setText("Hide and watch");
		ui.choice3.setText("");
		ui.choice4.setText("");

		ui.choice3.setVisible(false);
		ui.choice4.setVisible(false);
	}

	public void aidHealer1(){
		position = "aidHealer1";
		ui.mainTextArea.setText("You rush to his side while his gaze acknowledges your kindness and you fight off the monsters alongside.\nYou turn to your left and see a goblin charges at you with an ax.");
		ui.choice1.setText("Dodge");
		ui.choice2.setText("Block attack");
		ui.choice3.setText("Attack back");

	}

	public void dodge(){
		//position = "dodge";
		Random random = new Random();
        int randomNumber = random.nextInt(2);
		if (randomNumber == 0){
			ui.mainTextArea.setText("You duck your head and was able to dodge the attack");
		}
		else{
			int damage = new java.util.Random().nextInt(5,20);
			ui.mainTextArea.setText("You try to dodge, but failed, taking in" + damage + "damage");

			warrior.takeDamage(damage);

			// update hp of player
			// ui.hpLabelNumber.setText(""+warrior.getHP());
			 // Update playerHP based on the damage taken
			 playerHP -= damage;
			 // Ensure playerHP doesn't go below 0
			 if (playerHP < 0) {
				 playerHP = 0;
			 }
		 
			 // Update hp of player in the UI
			 ui.hpLabelNumber.setText("" + playerHP);
		}
		position = "villageattackGoblin";
		ui.choice1.setText("Attack");
		ui.choice2.setVisible(false);
	}

	public void blockAttack(){
		
		if (warrior.getLeftHandWeapon().getDamage() < axe.getDamage()){

			int damage = new java.util.Random().nextInt(1,10);
			ui.mainTextArea.setText("You try to block the attack, but failed. You stumble back and receives " + damage + " damage.");
			warrior.takeDamage(damage);
			//ui.hpLabelNumber.setText(""+warrior.getHP());
			 //Update playerHP based on the damage taken
			 playerHP -= damage;
			 // Ensure playerHP doesn't go below 0
			 if (playerHP < 0) {
				 playerHP = 0;
			 }
		 
			 // Update hp of player in the UI
			 ui.hpLabelNumber.setText("" + playerHP);
		}
		else{
			ui.mainTextArea.setText("You raise your weapon and block the attack.");
		}
		
										 
		position = "villageattackGoblin";
		ui.choice1.setText(">");
		ui.choice2.setVisible(false);
		
	}

	//Gobin Attack
	public void villagegoblinAttack(){
		position = "villagegoblinAttack";
		ui.monsterPanel.setVisible(true);
		//goblin.setHP(13);
		//goblinSetup();

		ui.mapButtonPanel.setVisible(false);
		ui.mainTextPanel.setVisible(true);
        
		int damage = new java.util.Random().nextInt(1,20);

		ui.mainTextArea.setText("The goblin attacked you giving " + damage + " damage.");

		warrior.takeDamage(damage);    //decrease HP of warrior

		// Update playerHP based on the damage taken
		playerHP -= damage;
		// Ensure playerHP doesn't go below 0
		if (playerHP < 0) {
			playerHP = 0;
			dead();
		}
	
		// Update hp of player in the UI
		ui.hpLabelNumber.setText("" + playerHP);

		if(playerHP > 0){
			ui.choice1.setText("Attack goblin");
			if(location == "Forest"){
				ui.choice2.setText("Retreat");
				ui.choice2.setVisible(true);
			}
			else{
				ui.choice2.setVisible(false);
			}
			ui.choice3.setText("");
			ui.choice4.setText("");
			ui.choice3.setVisible(false);
			ui.choice4.setVisible(false);
		}
		
	}

	public void villageAttackGoblin(){
		position = "villageattackGoblin";
		ui.mapButtonPanel.setVisible(false);
		ui.mainTextPanel.setVisible(true);

		int damage = knife.getDamage();

		ui.mainTextArea.setText("You attack the goblin back, giving it " + goblinHP+ " damage. The goblin has been defeated. You've acquired 3 goblin teeth.");
		goblinTeeth = goblinTeeth + 3;
		//warrior.attack(goblin);
		goblin.takeDamage(damage);
		// update hp of goblin
		// ui.monsterHP.setText("" + goblin.getHP());
		//goblinAttack();

		goblinHP -= damage;
		 // Ensure playerHP doesn't go below 0
		 if (goblinHP < 0) {
			 goblinHP = 0;
		 }

		 ui.monsterHP.setText("" + goblinHP);

		if (goblinHP > 0){
			//mainTextArea.setText("You attack the goblin back, giving it a" + goblin.getHP() + "damage.");
		 	//goblinAttack();
			ui.mainTextArea.setText("You attack the goblin back, giving it " + damage + " damage.");
			ui.choice1.setText(">");
			ui.choice2.setText("");
			ui.choice3.setText("");
			ui.choice4.setText("");
			//goblinAttack();
			ui.choice2.setVisible(false);
			ui.choice3.setVisible(false);
			ui.choice4.setVisible(false);
			
		}
		
		else{
			goblin.setHP(0);
			ui.monsterHP.setText("" + goblin.getHP());
			
			if(location == "Forest"){
				position = "endFightForest";

				ui.choice1.setText("Advance in the forest");
				ui.choice2.setText("View map");
				ui.choice2.setVisible(true);
			}
			else if(location == "Village"){
				position = "endFightVillage";
				ui.choice1.setText(">");
				ui.choice2.setVisible(false);
			}
			
			ui.choice1.setVisible(true);
			ui.choice3.setVisible(false);
			ui.choice4.setVisible(false);
		}
	}



	public void villageFightEnd(){
		position = "endQuest1";
		ui.monsterPanel.setVisible(false);
		ui.mainTextArea.setText("Eventually, you were able to drive away these mischievous creatures.\n'Thanks buddy, I appreciate the help. I am Brook by the way. And you are?'\nYou introduce yourself and Brook offers to heal your injuries.\nYou thank him and tell him about your quest. Wanting to return the favor, he offers to join you.\nHe then guides you to a small shady shop at the corner.");
		//playerHP = warrior.getHP() + 25;
		playerHP = playerHP + 25;

		 if (playerHP > 100) {
				 playerHP = 100;
		
		}

		warrior.setHP(playerHP);
		ui.hpLabelNumber.setText(""+playerHP);

		ui.choice1.setText("Enter shop");
		ui.choice2.setText("View Map");
		ui.choice2.setVisible(true);

		//gain healer
		healerCount = 1;
		ui.healerButtonPanel.setVisible(true);
	}

	public void hideWatch(){
		position = "hide";
		ui.mainTextArea.setText("You find a place to hide and watch the fight closely. After some struggle, he managed to fight them off, but then collapsed to the ground.");
		ui.choice1.setText("Rush to his aid");
		ui.choice2.setText("Don't bother to help");
	
	}

	public void aidHealer2(){
		position = "aidHealer2";
		ui.mainTextArea.setText("You rushed to his side and managed to drag him on a nearby bench.'Water, please.'");
		ui.choice1.setText("Offer him some water");

	}

	public void notHelp1(){
		position = "noHelp1";
		ui.mainTextArea.setText("The latter started to whimper in pain, asking for some water.");
		ui.choice1.setText("Offer him some water");
		ui.choice2.setText("Walk past him");
		
	}

	public void offerWater(){
		position = "endQuest1";
		ui.mainTextArea.setText("You offer him some water and after a few minutes, he sits up, feeling better.'Thanks buddy, I appreciate the help. I am Brook by the way. And you are?' You introduce yourself and while chatting. You tell him about your quest and he offers to join you. He then guides you to a small shady shop at the corner.\n\nYou found yourself a healer. Brook can fight alongside you and can heal you in case of serious injuries.");
		ui.choice1.setText("Enter shop");
		ui.choice2.setText("View map");
		ui.choice1.setVisible(true);
		ui.choice2.setVisible(true);

		//gain healer
		healerCount = 1;
		ui.healerButtonPanel.setVisible(true);	

	}

	public void walkPast(){
		position = "endQuest1";
		ui.mainTextArea.setText("You walk past him, heading to the small shady shop at the corner.");
		ui.choice1.setText("Enter shop");
		ui.choice2.setText("View map");
		ui.choice2.setVisible(true);
	}

	public void village2(){
		position = "endQuest1";
		ui.locationName.setText(location);
		mapPanel.setVisible(false);
		ui.mainTextPanel.setVisible(true);

		ui.mainTextArea.setText("You are back to the village.");
		ui.choice1.setText("Enter shop");
		ui.choice2.setText("View map");
		ui.choice2.setVisible(true);
		ui.choice3.setVisible(false);
		ui.choice4.setVisible(false);
	}

	public void shop(){
		position = "shop";
		ui.monsterPanel.setVisible(false);
		ui.mainTextArea.setText("Greeted by the old lady, she offers you a look at her goods.\n'Anything that interests you lad?'");
		ui.choice1.setText("Sell");
		ui.choice2.setText("Buy");
		ui.choice3.setText("View Map");
		ui.choice2.setVisible(true);
		ui.choice3.setVisible(true);
		ui.choice4.setVisible(false);
	}

	public void sell(){
		position = "sell";
		ui.mainTextArea.setText("You can sell all the items you've acquired from destroying monsters.");
		ui.choice1.setText("Goblin teeth");
		ui.choice2.setText("Wolf skin");
		ui.choice3.setText("Wraith Cloth");
		ui.choice4.setText("Orgre Claw");

		ui.choice2.setVisible(true);
		ui.choice3.setVisible(true);
		ui.choice4.setVisible(true);
	}

	public void sellItem(String item){
		position = "sellItem";
		if(item == "GoblinTeeth"){
			if(goblinTeeth == 0){
				ui.mainTextArea.setText("You do not have any goblin teeth to sell.");
			}
			else{
				int priceGoblin = 10;
				int total = priceGoblin*goblinTeeth;
				gold = gold + total;
				ui.goldLabelNumber.setText(""+ gold);
				ui.mainTextArea.setText("You sold all goblin teeth and received " + total + " golds.");
			}
		}
		else if(item == "WolfSkin"){
			if(wolfSkin == 0){
				ui.mainTextArea.setText("You do not have any wolf skin to sell.");
			}
			else{
				int priceWolf = 25;
				int total = priceWolf*wolfSkin;
				gold = gold + total;
				ui.goldLabelNumber.setText(""+ gold);
				ui.mainTextArea.setText("You sold all wolf skins and received " + total + " golds.");
			}
		}
		else if(item == "WraithCloth"){
			if(wraithCloth == 0){
				ui.mainTextArea.setText("You do not have any wraith cloth to sell.");
			}
			else{
				int priceWraith = 22;
				int total = priceWraith*wraithCloth;
				gold = gold + total;
				ui.goldLabelNumber.setText(""+ gold);
				ui.mainTextArea.setText("You sold all wraith cloth and received " + total + " golds.");
			}
		}
		else if(item == "OrgreClaw"){
			if(orgreClaw == 0){
				ui.mainTextArea.setText("You do not have any orgre claw to sell.");
			}
			else{
				int priceOrgre = 30;
				int total = priceOrgre*orgreClaw;
				gold = gold + total;
				ui.goldLabelNumber.setText(""+ gold);
				ui.mainTextArea.setText("You sold all orgre claw and received " + total + " golds.");
			}
		}
		ui.choice1.setText("Back");
		ui.choice2.setVisible(false);
		ui.choice3.setVisible(false);
		ui.choice4.setVisible(false);
	}

	public void buy(){
		position = "buy";
		ui.mainTextArea.setText("You can buy weapons, spells and potions.");
		ui.choice1.setText("Weapons");
		ui.choice2.setText("Spells");
		ui.choice3.setText("Potions");
		ui.choice4.setText("Back");

		ui.choice2.setVisible(true);
		ui.choice3.setVisible(true);
		ui.choice4.setVisible(true);
	}

	public void weapons(){
		position = "chooseWeapon";
		ui.mainTextArea.setText("Choose a weapon: ");
		ui.choice1.setText("Sword - 80 gold");
		ui.choice2.setText("Axe - 40 gold");
		ui.choice3.setText("Bow - 80 gold");
		ui.choice4.setText("Back");
		ui.choice4.setVisible(true);
		if(swordCount == 1){
			ui.choice1.setVisible(false);
		}
		if(axeCount == 1){
			ui.choice2.setVisible(false);
		}
		if(bowCount == 1){
			ui.choice3.setVisible(false);
		}
	}

	public void buyWeapons(String item){
		position = "buyWeapon";
		if(item == "Sword"){  // give to warrior
			if(gold < 80){
				ui.mainTextArea.setText("You do not have enough gold to buy the sword.");
			}
			else if(character == "archer"){
				ui.mainTextArea.setText("Sword not compatible with archer. A bow is recommended.");
			}
			else{
				ui.mainTextArea.setText("You have acquired a sword.\nDamage: "+ sword.getDamage());
				swordCount = 1;
				gold = gold - 80;
				ui.goldLabelNumber.setText(""+ gold);
				if(character == "warrior"){
					ui.weaponLabelName.setText(knife.getName() +","+ sword.getName());
				}
				warrior.setRightHandWeapon(sword);
			}
		}
		else if(item == "Axe"){  // give to healer
			if(gold < 40){
				ui.mainTextArea.setText("You do not have enough gold to buy the axe.");
			}
			else{
				ui.mainTextArea.setText("You have acquired an axe.\nDamage: "+ axe.getDamage());
				axeCount = 1;
				gold = gold - 40;
				ui.goldLabelNumber.setText(""+ gold);
				if(character == "healer"){
					ui.weaponLabelName.setText(""+ axe.getName());
				}
				healer.setWeapon(axe);
			}
		}
		else if(item == "Bow"){  // give to archer
			if(gold < 80){
				ui.mainTextArea.setText("You do not have enough gold to buy the bow.");
			}
			else if(character == "warrior"){
				ui.mainTextArea.setText("Bow not compatible with warrior. A sword is recommended.");
			}
			else{
				ui.mainTextArea.setText("You have acquired a bow.\nDamage: "+ bow.getDamage());
				bowCount = 1;
				gold = gold - 80;
				ui.goldLabelNumber.setText(""+ gold);
				//weaponLabelName.setText(""+ bow.getName());
				archer.setWeapon(bow);
			}
		}
		ui.choice1.setText("Back");
		ui.choice2.setVisible(false);
		ui.choice3.setVisible(false);
		ui.choice4.setVisible(false);
	}

	public void spells(){
		position = "buySpell";
		ui.mainTextArea.setText("Choose a spell: ");
		ui.choice1.setText("Fire - 40 gold");
		ui.choice2.setText("Lightning - 40 gold");
		ui.choice3.setText("Frost - 40 gold");
		ui.choice4.setText("Back");
		ui.choice4.setVisible(true);
	}

	public void buySpells(String item){
		position = "chooseSpell";
		if(item == "Fire spell"){
			if(gold < 40){
				ui.mainTextArea.setText("You do not have enough gold to buy this spell.");
			}
			else{
				ui.mainTextArea.setText("You have acquired the fire spell.\nDamage: "+ fire.getDamage());
				gold = gold - 40;
				ui.goldLabelNumber.setText(""+ gold);
				// add fire spell
				
			}
		}
		else if(item == "Lightning spell"){
			if(gold < 40){
				ui.mainTextArea.setText("You do not have enough gold to buy this spell.");
			}
			else{
				ui.mainTextArea.setText("You have acquired the lightning spell.\nDamage: "+ lightning.getDamage());
				gold = gold - 40;
				ui.goldLabelNumber.setText(""+ gold);
				// add lightning spell
			}
		}
		else if(item == "Frost spell"){
			if(gold < 40){
				ui.mainTextArea.setText("You do not have enough gold to buy this spell.");
			}
			else{
				ui.mainTextArea.setText("You have acquired the frost spell.\nDamage: "+ frost.getDamage());
				gold = gold - 40;
				ui.goldLabelNumber.setText(""+ gold);
				// add frost spell
			}
		}
		ui.choice1.setText("Back");
		ui.choice2.setVisible(false);
		ui.choice3.setVisible(false);
		ui.choice4.setVisible(false);
	}

	public void potions(){
		position = "buyPotion";
		ui.mainTextArea.setText("Choose item: ");
		ui.choice1.setText("Healing potion - 80 gold");
		ui.choice2.setText("Curing potion - 80 gold");
		ui.choice3.setText(" ");
		ui.choice4.setText("Back");
		ui.choice3.setVisible(false);
		ui.choice4.setVisible(true);
	}

	public void buyPotions(String item){
		position = "choosePotion";
		int healingPotionHP = 50;
		if(item == "HealingPotion"){
			if(gold < 80){
				ui.mainTextArea.setText("You do not have enough gold to buy healing potion.");
			}
			else{
				ui.mainTextArea.setText("You have acquired the healing potion.\nRecover: " + healingPotionHP+ "Hp");
				gold = gold - 80;
				ui.goldLabelNumber.setText(""+ gold);
				HealPotionCount = 1;
			}
		}
		else if(item == "CuringPotion"){
			if(gold < 80){
				ui.mainTextArea.setText("You do not have enough gold to buy curing potion.");
			}
			else{
				ui.mainTextArea.setText("You have acquired the curing potion, which can be used against poison.");
				gold = gold - 80;
				ui.goldLabelNumber.setText(""+ gold);
				CurePotionCount = 1;
			}
		}
		ui.choice1.setText("Back");
		ui.choice2.setVisible(false);
		ui.choice3.setVisible(false);
		ui.choice4.setVisible(false);
	}

	public void river1(){
		position = "river1";
		location = "River";
		ui.locationName.setText(location);
		mapPanel.setVisible(false);
		ui.mainTextPanel.setVisible(true);
		ui.mapButtonPanel.setVisible(false);

		ui.mainTextArea.setText("You went down to a nearby river to rest and bath, when you see that water has been poisoned. People in surrounding camps seem to be infected and in agony.");		
		ui.choice1.setText("Investigate");
		ui.choice2.setText("View map");
		ui.choice3.setText("");
		ui.choice4.setText("");

		ui.choice1.setVisible(true);
		ui.choice2.setVisible(true);
		ui.choice3.setVisible(false);
		ui.choice4.setVisible(false);
	}

	public void investigate(){
		position = "investigate";
		location = "River";
		ui.locationName.setText(location);
		ui.mainTextPanel.setVisible(true);

		ui.mainTextArea.setText("You move towards a person lying on the grounds, hoping to get some informations from him. Feebly, he said:\n 'The water, it's bad. Got poisoned by some witch. We need a cure, please. A cure.'");		
		ui.choice1.setText("Try to save them");
		ui.choice2.setText("Use curing potion");
		ui.choice3.setText("View map");

		ui.choice1.setVisible(true);
		ui.choice2.setVisible(true);
		ui.choice3.setVisible(true);
		ui.choice4.setVisible(false);
	}

	public void saveRiver(){
		position = "saveRiver";
		ui.healerButtonPanel.setVisible(false);
		ui.mainTextPanel.setVisible(true);
		if(healerCount == 0){
			ui.mainTextArea.setText("You get in the water, but end up getting poisoned. The poison started to infect your whole body until you feel numb. And without much help, you die in your own suffering.");
			ui.choice1.setText(">");
			ui.choice1.setVisible(true);
			ui.choice2.setVisible(false);
			ui.choice3.setVisible(false);
			ui.choice4.setVisible(false);
		}
		else{
			if(character == "warrior" || character == "archer"){
				ui.mainTextArea.setText("Having a good expertise, Brook tells you that he will be able to remove the poison and heal the people.");
			
				ui.choice2.setText("Switch to Brook");
				ui.choice1.setVisible(false);
				ui.choice3.setVisible(false);
				ui.choice4.setVisible(false);
			}
			else if(character == "healer"){
				ui.mainTextArea.setText("You get in the water without any issues and with some powerful magic, you were able to cleanse the river from all the poison. But all this power cost you 35 Hp\n\nFilled with hope, one of the boys comes up to you begging you to save him.");
				playerHP = healer.getHP() - 35;	
				healer.setHP(playerHP);
				ui.hpLabelNumber.setText("" + playerHP);

				ui.choice3.setText("Save the boy");
				ui.choice4.setText("Leave him be");
				ui.choice1.setVisible(false);
				ui.choice2.setVisible(false);
				ui.choice3.setVisible(true);
				ui.choice4.setVisible(true);
			}
		}
		
	}
	
	public void usePotion(String name){
		position = "potion";
		if(name == "CuringPotion"){
			if(CurePotionCount == 0){
				ui.mainTextArea.setText("You do not possess the potion. You might find one at the village shop.");
				ui.choice1.setText("View map");
				ui.choice2.setVisible(false);
				ui.choice3.setVisible(false);
			}
			else if(CurePotionCount == 1){
				ui.mainTextArea.setText("You reach for your pockets and take out the curing potion. At the same time, one of the boys commes towards you, begging you to save him.But, you possess only 1 potion.");
				ui.choice2.setText("Save river");
				ui.choice3.setText("Save boy");
				ui.choice1.setVisible(false);
				ui.choice3.setVisible(true);
			}
		}
		else if(name == "HealingPotion"){

		}
	}

	public void saveRiver2(){
		riverCount = 1;
		position = "endRiver";
		ui.healerButtonPanel.setVisible(true);
		ui.mainTextArea.setText("You decide that saving the river would be the best choice and you empty the flask into the river. The boy was left in anguish. Unable to fight against the poison, he succumbs to his death.");
		ui.choice1.setText("View map");
		ui.choice1.setVisible(true);
		ui.choice2.setVisible(false);
		ui.choice3.setVisible(false);
		ui.choice4.setVisible(false);
	}

	public void saveBoy(){  // acquire mage
		riverCount = 1;
		position = "endRiver";
		ui.healerButtonPanel.setVisible(true);
		ui.mainTextArea.setText("Feeling pity for the boy, he decide to save him and in less than a minute, he's completely back to normal. Out of gratitude, he decides to help you in your quest.\n\nYou found yourself a mage. Sam can fight alongside you using all sorts of spells.");
		ui.choice1.setText("View map");
		ui.choice1.setVisible(true);
		ui.choice2.setVisible(false);
		ui.choice3.setVisible(false);
		ui.choice4.setVisible(false);

		//gain healer
		mageCount = 1;
		ui.mageButtonPanel.setVisible(true);
	}

	public void NosaveBoy(){
		riverCount = 1;
		position = "endRiver";
		location = "River";
		ui.locationName.setText(location);
		ui.healerButtonPanel.setVisible(true);
		ui.mainTextPanel.setVisible(true);

		ui.mainTextArea.setText("You move away from him, leaving him in anguish. Unable to fight against the poison, he succumbs to his death.");		
		ui.choice1.setText("View map");

		ui.choice1.setVisible(true);
		ui.choice2.setVisible(false);
		ui.choice3.setVisible(false);
		ui.choice4.setVisible(false);
	}

	// Going to river after the quest
	public void river2(){
		position = "endRiver";
		location = "River";
		ui.locationName.setText(location);
		ui.healerButtonPanel.setVisible(true);
		ui.mainTextPanel.setVisible(true);

		ui.mainTextArea.setText("You went back to the river. Nothing in view except from the peaceful stream.");		
		ui.choice1.setText("View map");

		ui.choice1.setVisible(true);
		ui.choice2.setVisible(false);
		ui.choice3.setVisible(false);
		ui.choice4.setVisible(false);
	}

	// Going to forest for the 1st time
	public void forest1(){
		forestCount = 1;
		position = "forest";
		location = "Forest";
		ui.locationName.setText(location);
		mapPanel.setVisible(false);
		ui.mainTextPanel.setVisible(true);
		ui.choiceButtonPanel.setVisible(true); // Set the choiceButtonPanel to visible


		if(goblinHP > 0){
			ui.mainTextArea.setText("You make way to the forest where you see a goblin coming towards you. You can either fight it or run away, by opening your map.");		
			ui.choice1.setText("Fight the goblin");
			ui.choice2.setText("view map");
			ui.choice3.setText("");
			ui.choice4.setText("");

			ui.choice3.setVisible(false);
			ui.choice4.setVisible(false);
			System.out.println(""+goblinHP);
		}
		// else{
		// 	ui.mainTextArea.setText("You go back to the forest and see no monsters. You can advance further in the forest");
		// 	ui.choice3.setText("Advance in forest");
		//     ui.choice4.setText("View map");

		// 	ui.choice1.setVisible(false);
		//     ui.choice2.setVisible(false);	
		// 	System.out.println("else "+goblinHP);
			
		// }
	}

	public void endForest1(){
		position = "endforest1";
		location = "Forest";
		ui.locationName.setText(location);
		mapPanel.setVisible(false);
		ui.mainTextPanel.setVisible(true);
		ui.choiceButtonPanel.setVisible(true); // Set the choiceButtonPanel to visible

		ui.mainTextArea.setText("You go back to the forest and see no monsters. You can advance further in the forest");
			ui.choice1.setText("Advance in forest");
		    ui.choice2.setText("View map");

			ui.choice3.setVisible(false);
		    ui.choice4.setVisible(false);	
			System.out.println("else "+goblinHP);

	}

	//Gobin Attack
	public void goblinAttack(){
		position = "goblinAttack";
		ui.monsterPanel.setVisible(true);
		//goblinSetup();

		ui.mapButtonPanel.setVisible(false);
		ui.mainTextPanel.setVisible(true);

		int damage = new java.util.Random().nextInt(1,20);

		ui.mainTextArea.setText("The goblin attacked you giving " + damage + " damage.");

		warrior.takeDamage(damage);    //decrease HP of warrior

		// Update playerHP based on the damage taken
		playerHP -= damage;
		// Ensure playerHP doesn't go below 0
		if (playerHP < 0) {
			playerHP = 0;
			dead();
		}
	
		// Update hp of player in the UI
		ui.hpLabelNumber.setText("" + playerHP);

		if(playerHP > 0){
			ui.choice1.setText("Attack goblin");
			if(location == "Forest" && goblinHP == 13){
				ui.choice2.setText("Retreat");
				ui.choice2.setVisible(false);
			}
			else{
				ui.choice2.setVisible(false);
			}
			ui.choice3.setText("");
			ui.choice4.setText("");
			ui.choice3.setVisible(false);
			ui.choice4.setVisible(false);
		}
		
	}

	public void AttackGoblin(){
		position = "attackGoblin";
		ui.mapButtonPanel.setVisible(false);
		ui.mainTextPanel.setVisible(true);
		//int damage = knife.getDamage();
         int damage = 0;


		if(character == "warrior"){
				
				if(swordCount == 0){
				//warrior.attack(wraith);
					damage = knife.getDamage();
				}

				else if(axeCount == 1){
					damage = axe.getDamage();
				}

				else if(swordCount == 1){
					warrior.attack(goblin);
					damage = warrior.damage();
				}
				
			}

			else if(character == "healer"){

				if(swordCount == 0 && axeCount == 0){
				//warrior.attack(wraith);
				damage = knife.getDamage();
				}
				else if(swordCount == 0 && axeCount == 1){
					damage = axe.getDamage();
				}
				else if(swordCount == 1){
				// healer.attack(wraith);
				// damage = healer.damage();
				damage = knife.getDamage();
				}
			}


		ui.mainTextArea.setText("You attack the goblin back, giving it " + goblinHP + " damage. The goblin has been defeated. You've acquired 3 goblin teeth.");
		goblinTeeth = goblinTeeth + 3;
		//warrior.attack(goblin);
		goblin.takeDamage(damage);
		// update hp of goblin
		// ui.monsterHP.setText("" + goblin.getHP());
		//goblinAttack();

		goblinHP -= damage;
		 // Ensure playerHP doesn't go below 0
		 if (goblinHP < 0) {
			 goblinHP = 0;
		 }

		 ui.monsterHP.setText("" + goblinHP);

		if (goblinHP > 0){
			//mainTextArea.setText("You attack the goblin back, giving it a" + goblin.getHP() + "damage.");
		 	//goblinAttack();
			ui.mainTextArea.setText("You attack the goblin back, giving it " + damage + " damage.");
			ui.choice1.setText(">");
			ui.choice2.setText("");
			ui.choice3.setText("");
			ui.choice4.setText("");
			//goblinAttack();
			ui.choice2.setVisible(false);
			ui.choice3.setVisible(false);
			ui.choice4.setVisible(false);
			
		}
		
		else{
			goblin.setHP(0);
			ui.monsterHP.setText("" + goblin.getHP());
			
			if(location == "Forest"){
				position = "endFightForest";
				

				ui.choice1.setText("Advance in the forest");
				ui.choice2.setText("View map");
				ui.choice2.setVisible(true);
			}
			else if(location == "Village"){
				position = "endFightVillage";
				ui.choice1.setText(">");
				ui.choice2.setVisible(false);
			}
			
			ui.choice1.setVisible(true);
			ui.choice3.setVisible(false);
			ui.choice4.setVisible(false);
		}
	}


	public void forest2(){
		position = "forest2";
		location = "Forest";
		ui.locationName.setText(location);
		mapPanel.setVisible(false);
		ui.monsterPanel.setVisible(false);	
		ui.mainTextPanel.setVisible(true);
		ui.mapButtonPanel.setVisible(false);

		ui.mainTextArea.setText("You arrive at a crossroad in the forest. You can either go right or left.");		
		ui.choice1.setText("Right");
		ui.choice2.setText("Left");
		ui.choice3.setText("View map");
		ui.choice4.setText("");

		ui.choice1.setVisible(true);
		ui.choice2.setVisible(true);
		ui.choice3.setVisible(true);
		ui.choice4.setVisible(false);
	}

	public void left(){
		position = "left";
		
		if(chestForest == 0){
			ui.mainTextArea.setText("You find a hidden chest.");
			ui.choice1.setVisible(true);
		}
		else{
			ui.mainTextArea.setText("You already opened the chest.");
			ui.choice1.setVisible(false);
		}

		ui.choice1.setText("Open Chest");
		ui.choice2.setText("Advanced in forest");
		ui.choice3.setText("Turn back");
		ui.choice4.setText("");

		ui.choice2.setVisible(true);
		ui.choice3.setVisible(true);
		ui.choice4.setVisible(false);

	}

	public void waterfall(){
		position = "waterfallfight";
		ui.monsterPanel.setVisible(true);
		ui.mainTextArea.setText("You arrived at a river and the place is filled with wraith.");
		ui.choice1.setText("Attack");
		ui.choice2.setText("Turn back");
		ui.choice3.setText("");
		ui.choice4.setText("");

		ui.choice1.setVisible(true);
		ui.choice2.setVisible(true);
		ui.choice3.setVisible(false);
		ui.choice4.setVisible(false);
	}

    public void waterfall2(){
		position = "waterfall";
		ui.monsterPanel.setVisible(false);
		ui.mapButtonPanel.setVisible(false);
		ui.mainTextPanel.setVisible(true);
		ui.mainTextArea.setText("You go upstream and arrived at the waterfall.");

		ui.choice1.setText("Go for a swim");
		ui.choice2.setText("Turn back");
		ui.choice3.setText("");
		ui.choice4.setText("");

		ui.choice1.setVisible(true);
		ui.choice2.setVisible(true);
		ui.choice3.setVisible(false);
		ui.choice4.setVisible(false);

	}

    public void swim(){
		position = "swim";
		ui.mapButtonPanel.setVisible(false);
		ui.healerButtonPanel.setVisible(false);
		ui.warriorButtonPanel.setVisible(false);
		ui.mageButtonPanel.setVisible(false);
		ui.mainTextPanel.setVisible(true);
		ui.mainTextArea.setText("You decide to go for a swim. However, the water was too deep and you drowned.");
		ui.choice1.setText(">");
        ui. choice1.setVisible(true);
		ui.choice2.setVisible(false);
		ui.choice3.setVisible(false);
		ui.choice4.setVisible(false);
	}

	public void right(){
		position = "right";
		ui.mainTextArea.setText("While venturing deep in the forest, you arrived at the foot of a mountain. The path to the summit appears treacherous, windy through the rocky terrain.Its shadowy twists hinting at the dangers that lie ahead.");
		ui.choice1.setText("Climb the mountain");
		ui.choice2.setText("Turn back");
		ui.choice3.setText("");
		ui.choice4.setText("");

		ui.choice1.setVisible(true);
		ui.choice2.setVisible(true);
		ui.choice3.setVisible(false);
		ui.choice4.setVisible(false);
	}

	public void orgre(){
		location = "Mountain";
		ui.locationName.setText(location);
	    ui.monsterPanel.setVisible(true);
		position = "orgre";
		ui.mainTextArea.setText("On your way up, you find yourself face to face with an enormous orgre.");
		ui.choice1.setText("Attack");
		ui.choice2.setText("View map");
	}

	public void Mountain(){
		position = "mountainTop";
		ui.monsterPanel.setVisible(false);
		ui.mainTextArea.setText("Breathless, you arrive at the top and notice a small dark cave. At the entrance, you find a dirty old torch, but still usable.\r\n(Danger: Proceeding to the mountain will not allow you to go back/open your map. Adventurers are advised to be fully prepared.)");
		ui.choice1.setText("Take the torch");
		ui.choice2.setText("Leave torch");
		ui.choice3.setText("View map");
		ui.choice4.setText("");

		ui.choice1.setVisible(true);
		ui.choice2.setVisible(true);
		ui.choice3.setVisible(true);
		ui.choice4.setVisible(false);
	}

	public void cave(){
		location = "Cave";
		ui.locationName.setText(location);
		ui.healerButtonPanel.setVisible(true);
		position = "cave";
		if(torch == 0){
			ui.mainTextArea.setText("You leave the torch on the ground and go into the cave trying to make out the path in the dark. You were not able to avoid the obstacles along the way and took some damage. Eventually you find yourself at a dead end.\nYou notice two statues, one facing you and the other facing to the left.");
			playerHP -= 10;
			ui.hpLabelNumber.setText("" + playerHP);
		}
		else{
			ui.mainTextArea.setText("You pick it up, pull a lighter from your pockets and try to ignite the worn fabric. Its flickering flame casting a feeble glow was able to provide some light as you walk in the darkness. After evading some obstacle along the way, you arrive at a dead end.\nYou notice two statues, one facing you and the other facing to the left.");
		}
		ui.choice1.setText("Turn the second statue to the right");
		ui.choice2.setText("Turn the second statue to the left");
	}

	public void statueRight(){ //puzzle solved
		position = "puzzle";
		ui.mainTextArea.setText("You put the statue in place and hear rumbling sound, opening a door infront of you.");
		ui.choice1.setText("Advance");
		ui.choice2.setVisible(false);
		ui.choice3.setVisible(false);
	}

	public void statueLeft(){
		position = "puzzleAttack";
		ui.monsterPanel.setVisible(true);
		ui.mainTextArea.setText("You push the statue the wrong way. A door opens and out comes, a ferocious wolf");
		ui.choice1.setText(">");
		ui.choice2.setVisible(false);
		ui.choice3.setVisible(false);
	}

	public void inside(){
		position = "inside";
		ui.monsterPanel.setVisible(true);
		ui.mainTextArea.setText("Once inside, you see a girl trapped in a cage. You rush to free her. Unkowingly, you woke up the dragon and it charges at you.");
		ui.choice1.setText("Attack");
		ui.choice1.setVisible(true);
		ui.choice2.setVisible(false);
		ui.choice3.setVisible(false);
	}
	
	public void monsterAttack(String name){
		if(name == "wolf"){ //forest & in cave
			position = "wolfAttack";
			ui.monsterPanel.setVisible(true);
			ui.monsterName.setText(wolf.getName());
			ui.monsterHP.setText("" + wolf.getHP());

			int damage = new java.util.Random().nextInt(2,11);

			ui.mainTextArea.setText("The wolf attacked you giving " + damage + " damage.");

			warrior.takeDamage(damage);    //decrease HP of warrior
			//healer.takeDamage(damage);

			// Update playerHP based on the damage taken
			playerHP -= damage;
			// Ensure playerHP doesn't go below 0
			if (playerHP < 0) {
				playerHP = 0;
				dead();
			}
	
			// Update hp of player in the UI
			ui.hpLabelNumber.setText("" + playerHP);

			if(location == "Forest"){
				ui.choice1.setText("Attack wolf");
				ui.choice2.setText("Retreat");
				ui.choice3.setText("");
				ui.choice4.setText("");
				ui.choice3.setVisible(false);
				ui.choice4.setVisible(false);
			}
			else{
				ui.choice1.setText("Attack wolf");
				ui.choice2.setVisible(false);
				ui.choice3.setVisible(false);
				ui.choice4.setVisible(false);
			}
		}

		else if(name == "orgre"){  // path mountain
			position = "orgreAttack";
			int damage = new java.util.Random().nextInt(4,25);

			ui.mainTextArea.setText("The orgre attacked you giving " + damage + " damage.");

			warrior.takeDamage(damage);    //decrease HP of warrior
			//healer.takeDamage(damage);

			// Update playerHP based on the damage taken
			playerHP -= damage;
			// Ensure playerHP doesn't go below 0
			if (playerHP < 0) {
				playerHP = 0;
				dead();
			}
	
			// Update hp of player in the UI
			ui.hpLabelNumber.setText("" + playerHP);

			if(playerHP > 0){
				ui.choice1.setText("Attack orgre");
				ui.choice2.setText("Retreat");
				ui.choice3.setText("");
				ui.choice4.setText("");
				ui.choice3.setVisible(false);
				ui.choice4.setVisible(false);
			}
		}
		else if(name == "wraith"){  //waterfall
			position = "wraithAttack";
			int damage = new java.util.Random().nextInt(10,30);

			ui.mainTextArea.setText("The wraith attacked you giving " + damage + " damage.");

			warrior.takeDamage(damage);    //decrease HP of warrior
			//healer.takeDamage(damage);

			// Update playerHP based on the damage taken
			playerHP -= damage;
			// Ensure playerHP doesn't go below 0
			if (playerHP < 0) {
				playerHP = 0;
				dead();
			}
	
			// Update hp of player in the UI
			ui.hpLabelNumber.setText("" + playerHP);

			if(playerHP > 0){
				ui.choice1.setText("Attack wraith");
				ui.choice2.setText("Retreat");
				ui.choice3.setText("");
				ui.choice4.setText("");
				ui.choice2.setVisible(true);
				ui.choice3.setVisible(false);
				ui.choice4.setVisible(false);
			}
		}
		else if(name == "dragon"){  // final boss
			position = "dragonAttack";
			int damage = new java.util.Random().nextInt(10,30);

			ui.mainTextArea.setText("The dragon attacked you giving " + damage + " damage.");

			warrior.takeDamage(damage);    //decrease HP of warrior
			//healer.takeDamage(damage);

			// Update playerHP based on the damage taken
			playerHP -= damage;
			// Ensure playerHP doesn't go below 0
			if (playerHP < 0) {
				playerHP = 0;
				//dead();
				ui.choice2.setText(">");
				ui.choice2.setVisible(true);
				ui.choice1.setVisible(false);
			}
	
			// Update hp of player in the UI
			ui.hpLabelNumber.setText("" + playerHP);

			if(playerHP > 0){
				ui.choice1.setText("Attack dragon");
				ui.choice3.setText("");
				ui.choice4.setText("");
				ui.choice1.setVisible(true);
				ui.choice2.setVisible(false);
				ui.choice3.setVisible(false);
				ui.choice4.setVisible(false);
			}
		}

	}

	public void attackMonster(String name){
		if(name == "wolf"){  //forest & in cave - puzzle failed
			position = "attackWolf";

			turn += 1;  //TO COMMENT WHAT TURN DOES

			int damage=0;

			// if(character == "warrior"){
			// 	warrior.attack(wolf);
			// 	damage = warrior.damage();
			// }
			// else if(character == "healer"){
			// 	healer.attack(wolf);
			// 	damage = healer.damage();
			// }

			// ui.monsterHP.setText("" + wolf.getHP());

			if(character == "warrior"){

				if(swordCount == 0){
					damage = knife.getDamage();
				}

				else if(axeCount == 1){
					damage = axe.getDamage();
				}

				else if(swordCount == 1){

					warrior.attack(wolf);
					damage = warrior.damage();
				}
				
			}
			else if(character == "healer"){

				if(swordCount == 0 && axeCount == 0){
					damage = knife.getDamage();
				}
				else if(swordCount == 0 && axeCount == 1){
					damage = axe.getDamage();
				}
				else if(swordCount == 1){
			
					damage = knife.getDamage();
				
				}
			}

			ui.mainTextArea.setText("You attack the wolf, giving it " + wolfHP + " damage, defeating it. You've acquired 4 wolf skin.");
			wolfSkin = wolfSkin + 4;

			wolf.takeDamage(damage);

			wolfHP -= damage;

			// Ensure playerHP doesn't go below 0
			if (wolfHP < 0) {
				wolfHP = 0;
			}

			ui.monsterHP.setText("" + wolfHP);

			if (wolfHP > 0){
				ui.mainTextArea.setText("You attack the wolf back, giving it " + damage + " damage.");
				ui.choice1.setText(">");
				ui.choice2.setText("");
				ui.choice3.setText("");
				ui.choice4.setText("");
	
				ui.choice2.setVisible(false);
				ui.choice3.setVisible(false);
				ui.choice4.setVisible(false);
				
			}
		
			else{
				//ui.mainTextArea.setText("You attack the wolf, giving it " + damage + " damage, defeating it. You've acquired 4 wolf skin.");
				//wolfSkin = wolfSkin + 4;
				wolf.setHP(0);
				ui.monsterHP.setText("" + wolfHP);

				ui.choice2.setText("Advance");
				
				ui.choice2.setVisible(true);
				ui.choice1.setVisible(false);
				ui.choice4.setVisible(false);
			}
		}

		else if(name == "orgre"){  // on the way to mountain
			position = "attackOrgre";
			ui.monsterPanel.setVisible(true);
			
			turn += 1;  //TO COMMENT WHAT TURN DOES

			int damage=0;

			if(character == "warrior"){

				if(swordCount == 0){
					damage = knife.getDamage();
				}

				else if(axeCount == 1){
					damage = axe.getDamage();
				}

				else if(swordCount == 1){

					warrior.attack(orgre);
					damage = warrior.damage();
				}
				
			}
			else if(character == "healer"){

				if(swordCount == 0 && axeCount == 0){
					damage = knife.getDamage();
				}
				else if(swordCount == 0 && axeCount == 1){
					damage = axe.getDamage();
				}
				else if(swordCount == 1){
			
					damage = knife.getDamage();
				
				}
			}

			ui.mainTextArea.setText("You attack the orgre, giving it " + orgreHP + " damage, defeating it. You've acquired 2 orgre claw.");
			orgreClaw = orgreClaw + 2;

			orgre.takeDamage(damage);

			orgreHP -= damage;

			// Ensure playerHP doesn't go below 0
			if (orgreHP < 0) {
				orgreHP = 0;
			}

			ui.monsterHP.setText("" + orgreHP);

			if (orgreHP > 0){
				ui.mainTextArea.setText("You attack the orgre, giving it " + damage + " damage.");
				ui.choice1.setText(">");
				ui.choice2.setText("");
				ui.choice3.setText("");
				ui.choice4.setText("");
	
				ui.choice1.setVisible(true);
				ui.choice2.setVisible(false);
				ui.choice3.setVisible(false);
				ui.choice4.setVisible(false);
				
			}
		
			else{
				orgre.setHP(0);
				ui.monsterHP.setText("" + orgreHP);
				
				ui.choice2.setText("Advance");
				ui.choice3.setText("View map");

				ui.choice2.setVisible(true);
				ui.choice3.setVisible(true);
				ui.choice1.setVisible(false);
			}
		}

		else if(name == "wraith"){  //waterfall
			position = "attackWraith";

			ui.monsterName.setText(wraith.getName());
			// ui.monsterHP.setText("" + wraith.getHP());
			
			ui.monsterPanel.setVisible(true);
			
			turn += 1;
			int damage=0;

			
			if(character == "warrior"){
				
				if(swordCount == 0){
				//warrior.attack(wraith);
					damage = knife.getDamage();
				}

				else if(axeCount == 1){
					damage = axe.getDamage();
				}

				else if(swordCount == 1){
					warrior.attack(wraith);
					damage = warrior.damage();
				}
				
			}

			else if(character == "healer"){

				if(swordCount == 0 && axeCount == 0){
				//warrior.attack(wraith);
				damage = knife.getDamage();
				}
				else if(swordCount == 0 && axeCount == 1){
					damage = axe.getDamage();
				}
				else if(swordCount == 1){
				// healer.attack(wraith);
				// damage = healer.damage();
				damage = knife.getDamage();
				}
			}

		   ui.mainTextArea.setText("You attack the wraith back, giving it " + wraithHP+ " damage. The wraith has been defeated. You've acquired 3 wraith cloth.");
		
           wraithCloth += wraithCloth + 3;
			wraith.takeDamage(damage);
			

			wraithHP -= damage;
		 // Ensure playerHP doesn't go below 0
		 if (wraithHP < 0) {
			 wraithHP = 0;
		 }

			ui.monsterHP.setText("" + wraithHP);


			if (wraith.getHP() > 0){
				ui.mainTextArea.setText("You attack the wraith, giving it " + damage + " damage.");
				ui.choice1.setText(">");
				ui.choice2.setText("");
				ui.choice3.setText("");
				ui.choice4.setText("");
	
				ui.choice2.setVisible(false);
				ui.choice3.setVisible(false);
				ui.choice4.setVisible(false);
				
			}
		
			else{
			//	ui.mainTextArea.setText("You attack the wraith, giving it " + damage + " damage, defeating it. You obtain 3 wraith cloth");
			//	wraithCloth += wraithCloth + 3;
				wraith.setHP(0);
				ui.monsterHP.setText("" + wraithHP);
				
				
				ui.choice2.setText("Advance in forest");
				ui.choice3.setText("Go back");

				ui.choice2.setVisible(true);
				ui.choice3.setVisible(true);
				ui.choice1.setVisible(false);
			}

		// 	position = "villageattackGoblin";
		// ui.mapButtonPanel.setVisible(false);
		// ui.mainTextPanel.setVisible(true);

		//int damage = knife.getDamage();

		// ui.mainTextArea.setText("You attack the goblin back, giving it " + goblinHP+ " damage. The goblin has been defeated. You've acquired 3 goblin teeth.");
		// goblinTeeth = goblinTeeth + 3;
		// //warrior.attack(goblin);
		// goblin.takeDamage(damage);
		// // update hp of goblin
		// // ui.monsterHP.setText("" + goblin.getHP());
		// //goblinAttack();

		// goblinHP -= damage;
		//  // Ensure playerHP doesn't go below 0
		//  if (goblinHP < 0) {
		// 	 goblinHP = 0;
		//  }

		//  ui.monsterHP.setText("" + goblinHP);

		// if (goblinHP > 0){
		// 	//mainTextArea.setText("You attack the goblin back, giving it a" + goblin.getHP() + "damage.");
		//  	//goblinAttack();
		// 	ui.mainTextArea.setText("You attack the goblin back, giving it " + damage + " damage.");
		// 	ui.choice1.setText(">");
		// 	ui.choice2.setText("");
		// 	ui.choice3.setText("");
		// 	ui.choice4.setText("");
		// 	//goblinAttack();
		// 	ui.choice2.setVisible(false);
		// 	ui.choice3.setVisible(false);
		// 	ui.choice4.setVisible(false);
			
		// }
		
		// else{
		// 	goblin.setHP(0);
		// 	ui.monsterHP.setText("" + goblin.getHP());
			
		// 	if(location == "Forest"){
		// 		position = "endFightForest";

		// 		ui.choice1.setText("Advance in the forest");
		// 		ui.choice2.setText("View map");
		// 		ui.choice2.setVisible(true);
		// 	}
		// 	else if(location == "Village"){
		// 		position = "endFightVillage";
		// 		ui.choice1.setText(">");
		// 		ui.choice2.setVisible(false);
		// 	}
			
		// 	ui.choice1.setVisible(true);
		// 	ui.choice3.setVisible(false);
		// 	ui.choice4.setVisible(false);
		
	//}



		}

		else if(name == "dragon"){  // final boss
			position = "attackDragon";
			ui.monsterPanel.setVisible(true);
			// ui.monsterName.setText(dragon.getName());
			// ui.monsterHP.setText("" + dragon.getHP());
			
			turn += 1;
			int damage=0;

			if(character == "warrior"){

				if(swordCount == 0){
					damage = knife.getDamage();
				}

				else if(axeCount == 1){
					damage = axe.getDamage();
				}

				else if(swordCount == 1){

					warrior.attack(dragon);
					damage = warrior.damage();
				}
				
			}
			else if(character == "healer"){

				if(swordCount == 0 && axeCount == 0){
					damage = knife.getDamage();
				}
				else if(swordCount == 0 && axeCount == 1){
					damage = axe.getDamage();
				}
				else if(swordCount == 1){
			
					damage = knife.getDamage();
				
				}
			}

			ui.mainTextArea.setText("You attack the dragon, giving it " + dragonHP + " damage, defeating it. You free the girl and take her back to town.");
			// if(character == "warrior"){
			// 	warrior.attack(dragon);
			// 	damage = warrior.damage();
			// }
			// else if(character == "healer"){
			// 	healer.attack(dragon);
			// 	damage = healer.damage();
			// }

			dragon.takeDamage(damage);
			

			dragonHP -= damage;
			// Ensure dragonHP doesn't go below 0
			if (dragonHP < 0) {
				dragonHP = 0;
			}


			ui.monsterHP.setText("" + dragonHP);

			if (dragonHP > 0){
				ui.mainTextArea.setText("You attack the dragon, giving it " + damage + " damage.");
				ui.choice1.setText(">");
				ui.choice2.setText("");
				ui.choice3.setText("");
				ui.choice4.setText("");
	
				ui.choice2.setVisible(false);
				ui.choice3.setVisible(false);
				ui.choice4.setVisible(false);
				
			}
		
			else{
				//ui.mainTextArea.setText("You attack the dragon, giving it " + damage + " damage, defeating it. You free the girl and take her back to town.");
				dragon.setHP(0);
				ui.monsterHP.setText("" + dragonHP);
				
				ui.choice2.setText(">");

				ui.choice2.setVisible(true);
				ui.choice3.setVisible(false);
				ui.choice1.setVisible(false);
			}
		}

		// give option to heal 
		if(character == "healer" && turn > 2){
			ui.choice4.setText("Heal");
			ui.choice4.setVisible(true);
		}
	}

	
	public void dead(){
		position = "dead";
		ui.mapButtonPanel.setVisible(false);
		ui.monsterPanel.setVisible(false);
		ui.healerButtonPanel.setVisible(false);
		ui.warriorButtonPanel.setVisible(false);
		ui.mageButtonPanel.setVisible(false);
		ui.mainTextPanel.setVisible(true);
        ui.mainTextArea.setVisible(true);
		ui.choiceButtonPanel.setVisible(true);
		 playerHP = 0;
		
		 // Update hp of player in the UI
		ui.hpLabelNumber.setText("" + playerHP);

		ui.mainTextArea.setText("Game Over!");

			
		ui.choice1.setText("Restart");
		ui.choice2.setText("Load game");
		ui.choice3.setText("");
		ui.choice4.setText("");

		ui.choice1.setVisible(true);
		ui.choice2.setVisible(true);

		ui.choice3.setVisible(false);
		ui.choice4.setVisible(false);
	
	}

	public void Restart(){
		ui.titleUI();
		ui.choices();
		ui.titleNamePanel.setVisible(false);
		ui.startButtonPanel.setVisible(false);
		ui.Gameplay("start");


}

	

	public void continueGame(){
		ui.titleUI();
		ui.choices();
		ui.titleNamePanel.setVisible(false);
		ui.startButtonPanel.setVisible(false);
		ui.Gameplay("continue");

	}


	public void end(){
		ui.mainTextArea.setText("You won!\n\n The End.");
		ui.choice1.setVisible(false);
		ui.healerButtonPanel.setVisible(false);
		ui.warriorButtonPanel.setVisible(false);
		ui.mageButtonPanel.setVisible(false);
		ui.monsterPanel.setVisible(false);
	}

	public void goldchest(){
		position = "goldchest";
		chestForest = 1;
		ui.mapButtonPanel.setVisible(false);
		ui.mainTextPanel.setVisible(true);

		Reward reward = generateRandomReward();

		if (reward instanceof Gold) {
			Gold goldReward = (Gold) reward;
			int addGold = goldReward.getAmount();
			// int addgold = new java.util.Random().nextInt(1000) + 1;
			gold = gold + addGold;
			// update gold of player
			ui.goldLabelNumber.setText(""+ gold);
			ui.mainTextArea.setText("You opened the chest and found " + addGold + " gold.");
		}
		else if (reward instanceof Weapon) {
			Weapon weaponReward = (Weapon) reward;
			String weaponName = weaponReward.getName();
			ui.mainTextArea.setText("You opened the chest and found a " + weaponName + ".");
			// Your code to handle the weapon goes here
			// For example, you can equip the weapon to the player or add it to their inventory
		}

		if(location == "Forest"){
			ui.choice1.setText("Advance in forest");
			ui.choice2.setText("Turn back");
			ui.choice3.setText("");
			ui.choice4.setText("");

			ui.choice1.setVisible(true);
			ui.choice2.setVisible(true);
			ui.choice3.setVisible(false);
			ui.choice4.setVisible(false);
		}

	}

	class Reward implements Serializable{
		// Base class for rewards
		//serves as a placeholder base class for different types of rewards
		// it acts as a common superclass to enable polymorphism and help organize the different types of rewards.
	}
	
	class Gold extends Reward{
		private int amount;
	
		public Gold(int amount) {
			this.amount = amount;
		}
	
		public int getAmount() {
			return amount;
		}
	}
	
	class Weapon extends Reward{
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
	
		if (chance < 80) {
			// 80% chance to get gold
			int randomGold = random.nextInt(1000) + 1; // Generates a random number between 1 and 1000 (inclusive)
			return new Gold(randomGold);
		} else {
			// 20% chance to get a weapon
			String[] weapons = {"Sword", "Axe", "Bow", "Staff", "Dagger"};
			int randomWeaponIndex = random.nextInt(weapons.length);
			return new Weapon(weapons[randomWeaponIndex]);
		}
	}

}
