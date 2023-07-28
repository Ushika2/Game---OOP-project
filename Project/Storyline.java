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
import javax.swing.JPanel;
import javax.swing.JTextArea;

import character.*;
import spell.Blast;
import spell.Fire;
import spell.Frost;
import spell.Lightning;
import weapon.*;

public class Storyline{
	
    private JPanel mapPanel;          
     
	Container con;

	Font normalFont = new Font("Times New Roman", Font.PLAIN, 18);
	
    String playerName, weapon, position, location, goblinName, character;
    int playerHP,goblinHP, gold;

	int numOfTimesChestOpened = 0, villageCount = 0, riverCount = 0, HealPotionCount = 0, CurePotionCount = 0, healerCount = 0, mageCount;

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
	Orgre orgre = new Orgre("orgre", 30);
	Wolf wolf = new Wolf("wolf",23);
	Wraith wraith = new Wraith("wraith", 35);

    public UI ui;          

    public Storyline(Container container,UI ui) {     ///to avoid the null pointer exception

		this.con = container;
        this.ui = ui;
		
	}

    public void playerSetup(){ 

		location = "Town";
        
		ui.weaponLabelName.setText(knife.getName());
		playerHP = warrior.getHP();
		ui.hpLabelNumber.setText("" + playerHP);
		ui.locationName.setText(location);
		playerName = warrior.getName();
		ui.characterLabelName.setText("" + warrior.getName());
		ui.goldLabelNumber.setText(""+ gold);
	}

	// Goblin setup
	public void goblinSetup(){
		ui.monsterName.setText(goblin.getName());
		ui.monsterHP.setText("" + goblin.getHP());
		goblinName = goblin.getName();
		goblinHP = goblin.getHP();
	}

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
			bw.write(""+ riverCount);

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
            goblin.setHP(goblinHP);
			villageCount = Integer.parseInt(br.readLine());

			br.close();
		}
		catch(Exception e){    //handle exception like NumberFormatException e and IOException e
			e.printStackTrace();
			
		// Provide a user-friendly error message
		 ui.mainTextArea.setText("Failed to load the game. Please check your save file and try again.");
		   
		}
		
		ui.hpLabelNumber.setText("" + playerHP);
		ui.characterLabelName.setText("" + warrior.getName());
		ui.weaponLabelName.setText(weapon);
		ui.locationName.setText(location);
		ui.goldLabelNumber.setText(""+ gold);
		ui.monsterName.setText(""+ goblin.getName());
		ui.monsterHP.setText("" + goblin.getHP());

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
		ui.choice3.setText("");
		ui.choice4.setText("");

		ui.choice3.setVisible(false);
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

			// // update hp of player
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
		position = "attackGoblin";
		ui.choice1.setText("Attack");
		ui.choice2.setVisible(false);
	}

	public void blockAttack(){
		if (warrior.getLeftHandWeapon().getDamage() < axe.getDamage()){

			int damage = new java.util.Random().nextInt(1,10);
			ui.mainTextArea.setText("You try to block the attack, but failed. You stumble back and receives " + damage + " damage.");
			warrior.takeDamage(damage);
		//	ui.hpLabelNumber.setText(""+warrior.getHP());
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
		position = "attackGoblin";
		ui.choice1.setText("Attack");
		ui.choice2.setVisible(false);
	}

	public void villageFightEnd(){
		position = "endQuest1";
		ui.mainTextArea.setText("Eventually, you were able to drive away these mischievous creatures.\n'Thanks buddy, I appreciate the help. I am Brook by the way. And you are?'\nYou introduce yourself and Brook offers to heal your injuries.\nYou thank him and tell him about your quest. Wanting to return the favor, he offers to join you.\nHe then guides you to a small shady shop at the corner.");
		//playerHP = warrior.getHP() + 25;
		playerHP = playerHP + 25;
		warrior.setHP(playerHP);
		ui.hpLabelNumber.setText(""+playerHP);

		ui.choice1.setText("Enter shop");
		ui.choice2.setVisible(false);

		//gain healer
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
		ui.choice2.setVisible(false);

		//gain healer
		ui.healerButtonPanel.setVisible(true);
	}

	public void walkPast(){
		position = "endQuest1";
		ui.mainTextArea.setText("You walk past him, heading to the small shady shop at the corner.");
		ui.choice1.setText("Enter shop");
		ui.choice2.setVisible(false);
	}

	public void village2(){
		position = "endQuest1";
		ui.locationName.setText(location);
		mapPanel.setVisible(false);
		ui.mainTextPanel.setVisible(true);

		ui.mainTextArea.setText("You are back to the village.");
		ui.choice1.setText("Enter shop");
		ui.choice2.setText("View map");
		ui.choice3.setVisible(false);
		ui.choice4.setVisible(false);
	}

	public void shop(){
		position = "shop";
		ui.mainTextArea.setText("Greeted by the old lady, she offers you a look at her goods.\n'Anything that interests you lad?'");
		ui.choice1.setText("Sell");
		ui.choice2.setText("Buy");
		ui.choice3.setText("View Map");
		ui.choice2.setVisible(true);
		ui.choice3.setVisible(true);
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
				gold = gold - 80;
				ui.goldLabelNumber.setText(""+ gold);
				//weaponLabelName.setText(""+ sword.getName());
				warrior.setRightHandWeapon(sword);
			}
		}
		else if(item == "Axe"){  // give to healer
			if(gold < 40){
				ui.mainTextArea.setText("You do not have enough gold to buy the axe.");
			}
			else{
				ui.mainTextArea.setText("You have acquired an axe.\nDamage: "+ axe.getDamage());
				gold = gold - 40;
				ui.goldLabelNumber.setText(""+ gold);
				//weaponLabelName.setText(""+ axe.getName());
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
		ui.mainTextPanel.setVisible(true);
		if(healerCount == 0){
			ui.mainTextArea.setText("You get in the water, but end up getting poisoned. The poison started to infect your whole body until you feel numb. And without much help, you die in your own suffering.");
			ui.choice1.setText(" ");
			ui.choice2.setVisible(false);
			ui.choice3.setVisible(false);
		}
		else{
			if(character == "warrior" || character == "archer"){
				ui.mainTextArea.setText("Having a good expertise, Brook tells you that he will be able to remove the poison and heal the people.");
				ui.choice2.setText("Switch to Brook");
				ui.choice3.setVisible(false);
			}
			else if(character == "healer"){
				ui.mainTextArea.setText("You get in the water without any issues and with some powerful magic, you were able to cleanse the river from all the poison. But all this power cost you some Hp\n\nFilled with hope, one of the boys comes up to you begging you to save him.");
				playerHP = healer.getHP() - 35;	
				healer.setHP(playerHP);
				ui.hpLabelNumber.setText("" + playerHP);

				ui.choice3.setText("Save the boy");
				ui.choice4.setText("Leave him be");
				ui.choice1.setVisible(false);
				ui.choice2.setVisible(false);
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
		position = "forest";
		location = "Forest";
		ui.locationName.setText(location);
		mapPanel.setVisible(false);
		ui.mainTextPanel.setVisible(true);
		ui.choiceButtonPanel.setVisible(true); // Set the choiceButtonPanel to visible


		if(goblin.getHP() > 0){
			ui.mainTextArea.setText("You make way to the forest where you see a goblin coming towards you. You can either fight it or run away, by opening your map.");		
			ui.choice1.setText("Fight the goblin");
			ui.choice2.setText("view map");
			ui.choice3.setText("");
			ui.choice4.setText("");

			ui.choice3.setVisible(false);
			ui.choice4.setVisible(false);
			System.out.println(""+goblinHP);
		}
		else{
			ui.mainTextArea.setText("You go back to the forest and see no monsters. You can advance further in the forest");
			ui.choice3.setText("Advance in forest");
		    ui.choice4.setText("View map");

			ui.choice1.setVisible(false);
		    ui.choice2.setVisible(false);	
			System.out.println("else "+goblinHP);
			
		}
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
		 }
	 
		 // Update hp of player in the UI
		 ui.hpLabelNumber.setText("" + playerHP);

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

	public void AttackGoblin(){
		position = "attackGoblin";
		ui.mapButtonPanel.setVisible(false);
		ui.mainTextPanel.setVisible(true);
		int damage = knife.getDamage();

		ui.mainTextArea.setText("You attack the goblin back, giving it " + goblin.getHP() + " damage. The goblin has been defeated. You've acquired 3 goblin teeth.");
		goblinTeeth = goblinTeeth + 3;
		// //warrior.attack(goblin);
		goblin.takeDamage(damage);
		// // update hp of goblin
		// ui.monsterHP.setText("" + goblin.getHP());
		// //goblinAttack();


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

	public void right(){
		position = "right";
		ui.mainTextArea.setText("While venturing deep in the forest, you arrived at the foot of a mountain. The path to the summit appears treacherous, windy through the rocky terrain.Its shadowy twists hinting at the dangers that lie ahead.\r\n" + 
				"(Danger: Proceeding to the mountain will not allow you to go back/open your map. Adventurers are advised to be fully prepared.)\r\n");
		ui.choice1.setText("Climb the mountain");
		ui.choice2.setText("Turn back");
		ui.choice3.setText("");
		ui.choice4.setText("");

		ui.choice1.setVisible(true);
		ui.choice2.setVisible(true);
		ui.choice3.setVisible(false);
		ui.choice4.setVisible(false);
	}

	public void Mountain(){
		position = "mountainFoot";
		location = "Mountain";
		ui.locationName.setText(location);
		ui.mainTextArea.setText("Breathless, you arrive at the top and notice a small dark cave. At the entrance, you find a dirty old torch, but still usable.\r\n");
		ui.choice1.setText("Take the torch");
		ui.choice2.setText("Leave torch");
		ui.choice3.setText("");
		ui.choice4.setText("");

		ui.choice1.setVisible(true);
		ui.choice2.setVisible(true);
		ui.choice3.setVisible(false);
		ui.choice4.setVisible(false);
	}

	public void left(){
		position = "left";
		
		ui.mainTextArea.setText("Dialogue");	

		ui.choice1.setText("Open Chest");
		ui.choice2.setText("Advanced in forest");
		ui.choice3.setText("Turn back");
		ui.choice4.setText("");

		ui.choice1.setVisible(true);
		ui.choice2.setVisible(true);
		ui.choice3.setVisible(true);
		ui.choice4.setVisible(false);

	}
	public void goldchest(){
		position = "goldchest";
		ui.mapButtonPanel.setVisible(false);
		ui.mainTextPanel.setVisible(true);

		
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

                numOfTimesChestOpened++;
            
		} 
			else {
                ui.mainTextArea.setText("The chest is empty...");
                chestOpened = true;
            }
		}

     	ui.choice1.setText("Advance in forest");
		ui.choice2.setText("Turn back");
		ui.choice3.setText("");
		ui.choice4.setText("");

		ui.choice1.setVisible(true);
		ui.choice2.setVisible(true);
		ui.choice3.setVisible(false);
		ui.choice4.setVisible(false);

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
		ui.mapButtonPanel.setVisible(false);
		ui.mainTextPanel.setVisible(true);
		ui.mainTextArea.setText("You advanced further in the forest and arrived at a waterfall.");

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
		ui.mainTextPanel.setVisible(true);
		ui.mainTextArea.setText("You decide to go for a swim. However, the water was too deep somethimng somethinmg idk BUT you drowned.");
       ui. choice1.setVisible(false);
		ui.choice2.setVisible(false);
		ui.choice3.setVisible(false);
		ui.choice4.setVisible(false);
	}

	

}