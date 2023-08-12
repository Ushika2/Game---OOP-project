import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.util.Random;
import java.io.*;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import character.*;
import spell.*;
import weapon.*;
import monster.*;

public class Storyline {

	private JPanel mapPanel;

	Container con;

	Font normalFont = new Font("Times New Roman", Font.PLAIN, 18);

	String playerName, weapon, position, location, goblinName, wraithName, orgreName, wolfName, dragonName, character,
			attack;
	int playerHP = 100, goblinHP, orgreHP, wolfHP, wraithHP, dragonHP, gold, turn = 0;

	int chestForest = 0, forestCount = 0, villageCount = 0, riverCount = 0, waterfallCount = 0, HealPotionCount = 0,
			CurePotionCount = 0, healerCount = 0, mageCount = 0, torch = 0, orgreCount = 0;
	int swordCount = 0, axeCount = 0;

	int goblinTeeth = 0, orgreClaw = 0, wraithCloth = 0;
	int fireC = 0, frostC = 0, lightC = 0;

	//Initializing prices of items
	int priceGoblin = 10;    //price of Goblin Teeth
	int priceWraith = 25;   // price of Wraith Cloth
	int priceOrgre = 30;   //  price of Orgre Claw

	// Creating weapons and assigning a name and damage
	Axe axe = new Axe("Axe", 20);
	Knife knife = new Knife("Knife", 10);
	Sword sword = new Sword("Sword", 25);
	Grimoire grimoire = new Grimoire("Grimoire", 10);

	// Creating spells, assigning name and damage
	Blast blast = new Blast("Blast", 12); // default spell
	Fire fire = new Fire("Fire", 30);
	Frost frost = new Frost("Frost", 15);
	Lightning lightning = new Lightning("Lightning", 20);

	// Creating characters, assigning a name, Hp and weapon
	Warrior warrior = new Warrior("Warrior", playerHP, knife, null);
	Healer healer = new Healer("Healer", playerHP, 20, knife);
	Mage mage = new Mage("Mage", playerHP, blast, grimoire);

	// Creating monsters, assigning name and Hp
	Goblin goblin = new Goblin("Goblin", 13);
	Orgre orgre = new Orgre("Orgre", 60);
	Wolf wolf = new Wolf("Wolf", 80);
	Wraith wraith = new Wraith("Wraith", 35);
	Dragon dragon = new Dragon("Dragon", 150);

	public UI ui;

	public Storyline(Container container, UI ui) { /// to avoid the null pointer exception
		this.con = container;
		this.ui = ui;

	}

	// CHARACTER SETUP
	public void playerSetup() { // update player panel
		location = "Town"; // initial location
		character = "warrior";
		ui.weaponLabelName.setText(knife.getName());
		if (swordCount == 1) {
			ui.weaponLabelName.setText(knife.getName() + " " + sword.getName());
		}
		playerHP = warrior.getHP();
		ui.hpLabelNumber.setText("" + playerHP);
		ui.locationName.setText(location);
		playerName = warrior.getName();
		ui.goldLabelNumber.setText("" + gold);
		ui.characterLabelName.setText("" + warrior.getName());
	}

	public void healerSetup() { // update player panel when switching to healer
		character = "healer";
		ui.characterLabelName.setText("" + healer.getName()); // update weapon
		if (axeCount == 1) {
			ui.weaponLabelName.setText("" + axe.getName());
		} else if (axeCount == 0) {
			ui.weaponLabelName.setText("" + knife.getName());
		}
		if (location == "River") {
			saveRiver();
		}
	}

	public void heal() { // increaing playerHp when healing
		if (character == "healer" && turn > 3) { // character should be healer and heal is available after every 3
													// attacks done by player
			playerHP += healer.getHealingPower();
			if (playerHP > 100) {
				playerHP = 100;
			}
			ui.hpLabelNumber.setText("" + playerHP);
			warrior.setHP(playerHP);
			turn = 0; // restart count for number of attacks
			ui.choice4.setVisible(false);
		}

		if (character != "healer") {
			ui.mainTextArea.setText("Healer have healing skill.\n\nSwitch to healer and heal again. Then proceed");
		}

	}

	public void mageSetup() { // update player panel when switching to mage
		character = "mage";
		ui.characterLabelName.setText("" + mage.getName()); // change characterlabel
		ui.weaponLabelName.setText(grimoire.getName()); // change weapon
	}

	public void warriorSetup() { // update player panel when switching back to warrior
		character = "warrior";
		ui.characterLabelName.setText("" + warrior.getName());
		ui.weaponLabelName.setText(knife.getName());
		if (swordCount == 1) {
			ui.weaponLabelName.setText(knife.getName() + "," + sword.getName());
		}
	}

	public void mageAttack() { // display spells (that player has) choices during fights
		position = "mageAttack";
		ui.choice1.setText("Blast");

		ui.choice1.setVisible(true);
		ui.choice2.setVisible(false);
		ui.choice3.setVisible(false);
		ui.choice4.setVisible(false);

		if (fireC == 1) {
			ui.choice2.setText("Fire");
			ui.choice2.setVisible(true);

			ui.choice3.setVisible(false);
			ui.choice4.setVisible(false);
		}
		if (frostC == 1) {
			ui.choice3.setText("Frost");
			ui.choice3.setVisible(true);

			ui.choice4.setVisible(false);
		}
		if (lightC == 1) {
			ui.choice4.setText("Lightning");
			ui.choice4.setVisible(true);
		}

	}

	// MONSTER Setup which will be displayed in the monster panel during fights

	// Goblin setup in forest
	public void goblinSetup() {
		ui.monsterName.setText(goblin.getName());
		goblinHP = 13;
		ui.monsterHP.setText("" + goblinHP);
		goblinName = goblin.getName();
	}

	// Village Goblin setup
	public void villagegoblinSetup() {
		ui.monsterName.setText(goblin.getName());
		goblinHP = 13;
		ui.monsterHP.setText("" + goblinHP);
		goblinName = goblin.getName();

	}

	// Orgre setup
	public void OrgreSetup() {
		ui.monsterName.setText(orgre.getName());
		orgreName = orgre.getName();
		ui.monsterHP.setText("" + orgre.getHP());
		orgreHP = orgre.getHP();

	}

	// Wolf setup
	public void WolfSetup() {
		ui.monsterName.setText(wolf.getName());
		wolfName = wolf.getName();
		ui.monsterHP.setText("" + wolf.getHP());
		wolfHP = wolf.getHP();

	}

	// Wraith setup
	public void WraithSetup() {
		ui.monsterName.setText(wraith.getName());
		wraithName = wraith.getName();
		ui.monsterHP.setText("" + wraith.getHP());
		wraithHP = wraith.getHP();

	}

	// Dragon setup
	public void DragonSetup() {
		ui.monsterName.setText(dragon.getName());
		dragonName = dragon.getName();
		ui.monsterHP.setText("" + dragon.getHP());
		dragonHP = dragon.getHP();

	}

	// -----SAVE AND LOAD GAME DATA-----

	public void saveGame() {
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter("saveFile.txt")); // creating text file
			bw.write("" + playerHP); // player Hp
			bw.newLine();
			bw.write(warrior.getName()); // character name
		    bw.newLine();
			bw.write(knife.getName()); // character weapon
			bw.newLine();
			bw.write(location); // location
			bw.newLine();
			bw.write("" + gold); // amount of gold
			// bw.newLine();
			// bw.write(goblinName); // save goblin name & Hp
			bw.newLine();
			bw.write("" + goblin.getHP());
			bw.newLine();
			bw.write("" + villageCount); // save village quest
			bw.newLine();
			bw.write("" + healerCount); // save if acquired healer or not
			bw.newLine();
			bw.write("" + mageCount); // save if acquired mage or not
			bw.newLine();
			bw.write("" + riverCount); // save river side quest
			bw.newLine();
			bw.write("" + chestForest); // save if chest has been opened
			bw.newLine();
			bw.write("" + axeCount); // save weapon - axe if acquired
			bw.newLine();
			bw.write("" + swordCount); // save weapon - sword if acquired
			bw.newLine();
			bw.write("" + forestCount); // save if (forest) goblin has been defeated
			bw.newLine();
			bw.write("" + waterfallCount); // save if wraith has been defeated at waterfall
			bw.newLine();
			bw.write("" + wraith.getHP()); // save wraith Hp
			bw.newLine();
			bw.write("" + orgre.getHP()); // save orgre Hp
			bw.newLine();
			bw.write("" + fireC); // save acquired spell
			bw.newLine();
			bw.write("" + frostC);
			bw.newLine();
			bw.write("" + lightC);
			bw.newLine();
			bw.write("" + goblinTeeth); // save monster materials obtained after fight
			bw.newLine();
			bw.write("" + orgreClaw);
			bw.newLine();
			bw.write("" + wraithCloth);
			bw.newLine();
			bw.write("" + orgreCount);
			bw.newLine();
			bw.write("" + turn); //save attacks to get option heal

			bw.close();
		} catch (Exception e) {
			e.printStackTrace();

		}
		System.out.println("saved yes");
	}

	public void loadData() {
		try {
			BufferedReader br = new BufferedReader(new FileReader("saveFile.txt")); // read data from text file
			playerHP = Integer.parseInt(br.readLine());
			playerName = br.readLine();
			weapon = br.readLine();
			location = br.readLine();
			gold = Integer.parseInt(br.readLine());
			//goblinName = br.readLine();
			goblinHP = Integer.parseInt(br.readLine());
			villageCount = Integer.parseInt(br.readLine());
			healerCount = Integer.parseInt(br.readLine());
			mageCount = Integer.parseInt(br.readLine());
			riverCount = Integer.parseInt(br.readLine());
			chestForest = Integer.parseInt(br.readLine());
			axeCount = Integer.parseInt(br.readLine());
			swordCount = Integer.parseInt(br.readLine());
			forestCount = Integer.parseInt(br.readLine());
			waterfallCount = Integer.parseInt(br.readLine());
			wraithHP = Integer.parseInt(br.readLine());
			orgreHP = Integer.parseInt(br.readLine());
			fireC = Integer.parseInt(br.readLine());
			frostC = Integer.parseInt(br.readLine());
			lightC = Integer.parseInt(br.readLine());
			goblinTeeth = Integer.parseInt(br.readLine());
			orgreClaw = Integer.parseInt(br.readLine());
			wraithCloth = Integer.parseInt(br.readLine());
			orgreCount = Integer.parseInt(br.readLine());
			turn = Integer.parseInt(br.readLine());

			br.close();
		} catch (Exception e) { // handle exception like NumberFormatException e and IOException e
			e.printStackTrace();

			// Provide a user-friendly error message
			ui.mainTextArea.setText("Failed to load the game. Please check your save file and try again.");

		}

		// update player panel as per load data
		character = "warrior";
		ui.hpLabelNumber.setText("" + playerHP);
		ui.characterLabelName.setText("" + warrior.getName());
		ui.weaponLabelName.setText(weapon);
		if (swordCount == 1) {
			ui.weaponLabelName.setText(knife.getName() + "," + sword.getName());
			warrior.setLeftHandWeapon(sword);
			warrior.setRightHandWeapon(knife);
		}
		ui.locationName.setText(location);
		ui.goldLabelNumber.setText("" + gold);

		// update goblin setup
		goblin.setHP(goblinHP);
		ui.monsterHP.setText("" + goblin.getHP());

		// update wraith setup
		wraith.setHP(wraithHP);

		// update orgre setup
		orgre.setHP(orgreHP);

		if (healerCount == 1) {
			ui.healerButton.setVisible(true);
			ui.healerButtonPanel.setVisible(true);
		}
		if (axeCount == 1) {
			healer.setWeapon(axe);
		}
		if (mageCount == 1) {
			ui.mageButton.setVisible(true);
			ui.mageButtonPanel.setVisible(true);
		}
		loadgame();

	}

	public void intro() { // Begining of story - town
		position = "intro";
		// output narration of storyline
		ui.mainTextArea = new JTextArea(
				"At the entrance of the hometown, you see someone standing. You approach the person.\n\n'Hello adventurer, I am the chief of this town. I plead for your help to find my daughter who has been kidnapped.'\nFeeling empathy, you accept to help him.\n\nYou can visit the village to buy items to help you in this quest.");
		ui.mainTextArea.setBounds(100, 100, 600, 250); // mainTextArea customization
		ui.mainTextPanel.setBackground(Color.black);
		ui.mainTextArea.setBackground(Color.black);
		ui.mainTextArea.setForeground(Color.white);
		ui.mainTextArea.setFont(ui.normaltextFont);
		ui.mainTextArea.setLineWrap(true);
		ui.mainTextArea.setWrapStyleWord(true);
		ui.mainTextArea.setEditable(false);
		ui.mainTextPanel.add(ui.mainTextArea); // adding mainTextArea to mainTextPanel

	}

	public void loadgame() { // display message to show game has been loaded
		position = "continue";
		ui.mainTextArea = new JTextArea("Game loaded successfully");
		ui.mainTextArea.setBounds(100, 100, 600, 250);
		ui.mainTextPanel.setBackground(Color.black);
		ui.mainTextArea.setBackground(Color.black);
		ui.mainTextArea.setForeground(Color.white);
		ui.mainTextArea.setFont(ui.normaltextFont);
		ui.mainTextArea.setLineWrap(true);
		ui.mainTextArea.setWrapStyleWord(true);
		ui.mainTextArea.setEditable(false);
		ui.mainTextPanel.add(ui.mainTextArea);
		System.out.println("load game" + goblinHP);

	}

	public void Map() { // method for when player opens map
		position = "map";

		ui.warriorButton.setVisible(true);
		ui.choiceButtonPanel.setVisible(true);
		ui.mainTextPanel.setVisible(false); // Hide the main text panel
		ui.mapButtonPanel.setVisible(false); // hide the view map button when on the map
		ui.monsterPanel.setVisible(false);

		mapPanel = new JPanel();
		mapPanel.setBounds(100, 100, 600, 250);
		mapPanel.setBackground(Color.black);
		con.add(mapPanel);

		ui.mapTextArea = new JTextArea("Choose where to go.");
		ui.mapTextArea.setBounds(100, 150, 600, 250); // customization
		ui.mapTextArea.setBackground(Color.black);
		ui.mapTextArea.setForeground(Color.white);
		ui.mapTextArea.setFont(normalFont);
		ui.mapTextArea.setLineWrap(true);
		ui.mapTextArea.setWrapStyleWord(true);
		ui.mapTextArea.setEditable(false);

		mapPanel.add(ui.mapTextArea);
		mapPanel.setVisible(true); // Show the map panel

		ui.choice1.setText("Town"); // choices to different location
		ui.choice2.setText("Forest");
		ui.choice3.setText("Village");
		ui.choice4.setText("River");

		// ensure that all choices appear when map is called
		ui.choice1.setVisible(true);
		ui.choice2.setVisible(true);
		ui.choice3.setVisible(true);
		ui.choice4.setVisible(true);
	}

	// Going to town for 1st time
	public void townn() {
		position = "townn";
		location = "Town";
		ui.locationName.setText(location);
		mapPanel.setVisible(false);
		ui.mainTextPanel.setVisible(true);

		ui.mainTextArea.setText("You are at the town.\nYou see a checkpoint to save the game.\n\nYou can save the game at any moment during gameplay");
		ui.choice1.setText("Save game");
		ui.choice2.setText("View map");
		ui.choice3.setText(" ");
		ui.choice4.setText("");

		ui.choice3.setVisible(false);
		ui.choice4.setVisible(false);
	}

	public void saved() {
		position = "gameSaved";
		location = "Town";
		ui.locationName.setText(location);
		mapPanel.setVisible(false);
		ui.mainTextPanel.setVisible(true);

		ui.mainTextArea.setText("Game saved successfully.\n\nView map to explore diffrent territories.");
		ui.choice1.setText("View map");
		ui.choice2.setText("");
		ui.choice3.setText(" ");
		ui.choice4.setText("");

		ui.choice2.setVisible(false);
		ui.choice3.setVisible(false);
		ui.choice4.setVisible(false);
	}

	// Going to village for 1st time - side quest initiated
	public void village1() {
		villageCount = 1;
		position = "village1";
		location = "Village";
		ui.locationName.setText(location);
		mapPanel.setVisible(false);
		ui.mainTextPanel.setVisible(true);

		ui.mainTextArea.setText(
				"You decide to go take a look at the forlorn village.\n\nUpon arrival, you find the village is under attack by a horde of goblins.\n\nAmidst the chaos, you catch sight of a solitary figure battling the malignant goblins.");
		ui.choice1.setText("Join him in the fight");
		ui.choice2.setText("Hide and watch");
		ui.choice3.setText("");
		ui.choice4.setText("");

		ui.choice3.setVisible(false);
		ui.choice4.setVisible(false);
	}

	public void aidHealer1() {
		position = "aidHealer1";
		ui.mainTextArea.setText(
				"You rush to his aid and battling the monsters side by side.\n\nSuddenly, you turn to your left and see a goblin charging at you with an axe.");
		ui.choice1.setText("Dodge");
		ui.choice2.setText("Block attack");

	}

	public void dodge() {
		position = "villageattackGoblin";
		Random random = new Random();
		int randomNumber = random.nextInt(2); // make dodge random
		if (randomNumber == 0) {
			ui.mainTextArea.setText("You duck your head and was able to dodge the attack");
		} else {
			int damage = new java.util.Random().nextInt(5, 20);
			ui.mainTextArea.setText("You try to dodge, but failed, taking in" + damage + "damage");
			warrior.takeDamage(damage);

			// Update playerHP based on the damage taken
			playerHP -= damage;

			// Ensure playerHP doesn't go below 0
			if (playerHP <= 0) {
				playerHP = 0;

				// Game Over
				ui.choice3.setText(">");

				ui.choice3.setVisible(true);
				ui.choice1.setVisible(false);
			}

			// Update hp of player in the UI
			ui.hpLabelNumber.setText("" + playerHP);
		}
		ui.choice1.setText("Attack");
		ui.choice2.setVisible(false);
	}

	public void blockAttack() {
		position = "villageattackGoblin";
		if (warrior.getLeftHandWeapon().getDamage() < axe.getDamage()) {

			int damage = new java.util.Random().nextInt(1, 10);
			ui.mainTextArea.setText(
					"You try to block the attack, but failed. You stumble back and receives " + damage + " damage.");
			warrior.takeDamage(damage);
			// Update playerHP based on the damage taken
			playerHP -= damage;

			// Ensure playerHP doesn't go below 0
			if (playerHP <= 0) {
				playerHP = 0;

				// Game Over
				ui.choice3.setText(">");
				ui.choice3.setVisible(true);

				ui.choice1.setVisible(false);
			}

			// Update hp of player in the UI
			ui.hpLabelNumber.setText("" + playerHP);
		} else {
			ui.mainTextArea.setText("You raise your weapon and block the attack.");
		}
		ui.choice1.setText(">");
		ui.choice2.setVisible(false);

	}

	// Gobin Attack
	public void villagegoblinAttack() {
		position = "villagegoblinAttack";
		ui.monsterPanel.setVisible(true);

		ui.mapButtonPanel.setVisible(false);
		ui.mainTextPanel.setVisible(true);

		int damage = new java.util.Random().nextInt(1, 20); // make damage bt goblin random in range of 1 to 20
		ui.mainTextArea.setText("The goblin attacked you giving " + damage + " damage.");

		warrior.takeDamage(damage); // decrease HP of warrior

		// Update playerHP based on the damage taken
		playerHP -= damage;

		// Ensure playerHP doesn't go below 0
		if (playerHP <= 0) {
			playerHP = 0;

			// Game Over
			ui.choice3.setText(">");
			ui.choice3.setVisible(true);
			ui.choice1.setVisible(false);
			ui.choice2.setVisible(false);
		}

		// Update hp of player in the UI
		ui.hpLabelNumber.setText("" + playerHP);

		if (playerHP > 0) {
			ui.choice1.setText("Attack goblin");
			if (location == "Forest") {
				ui.choice2.setText("Retreat");
				ui.choice2.setVisible(true);
			} else {
				ui.choice2.setVisible(false);
			}
			ui.choice3.setText("");
			ui.choice4.setText("");
			ui.choice3.setVisible(false);
			ui.choice4.setVisible(false);
		}

	}

	public void villageAttackGoblin() {
		position = "villageattackGoblin";
		ui.mapButtonPanel.setVisible(false);
		ui.mainTextPanel.setVisible(true);

		int damage = knife.getDamage();

		ui.mainTextArea.setText("You attack the goblin back, giving it " + goblinHP
				+ " damage.\nThe goblin has been defeated.\n\n\nYou've acquired 6 Goblin Teeth.\n\nItem can be sold in the shop.");
		
		goblin.takeDamage(damage);
		// update hp of goblin
		goblinHP -= damage;
		// Ensure playerHP doesn't go below 0
		if (goblinHP <= 0) {
			goblinHP = 0;
		}

		ui.monsterHP.setText("" + goblinHP);

		if (goblinHP > 0) {
			ui.mainTextArea.setText("You attack the goblin back, giving it " + damage + " damage.");
			ui.choice1.setText(">");
			ui.choice2.setText("");
			ui.choice3.setText("");
			ui.choice4.setText("");

			ui.choice2.setVisible(false);
			ui.choice3.setVisible(false);
			ui.choice4.setVisible(false);

		} else {
			goblin.setHP(0);
			ui.monsterHP.setText("" + goblin.getHP());

			position = "endFightVillage";
			ui.choice1.setText(">");
			ui.choice2.setVisible(false);

			ui.choice1.setVisible(true);
			ui.choice3.setVisible(false);
			ui.choice4.setVisible(false);

			goblinTeeth = goblinTeeth + 6;
		}

	}

	public void villageFightEnd() {
		position = "endQuest1";

		ui.monsterPanel.setVisible(false);
		ui.mainTextArea.setText(
				"Eventually, you were able to drive away the mischievous creatures.\n'Thank you adventurer, I appreciate the help. I am Brook, a healer, allow me to mend your injuries.'     (Recover 25 Hp)\nYou thank him and tell him about your quest. Wanting to return the favor, he offers to join you.\n\nHEALER ACQUIRED\t    Skill: Heal after a set of attacks\n\nYou roam around the village and come accross a desolated street where a small shady shop comes into view.");
		playerHP = playerHP + 25;

		if (playerHP > 100) { // make sure player Hp doesn't go beyond limit
			playerHP = 100;
		}

		warrior.setHP(playerHP);
		ui.hpLabelNumber.setText("" + playerHP);

		ui.choice1.setText("Enter shop");
		ui.choice2.setText("View Map");
		ui.choice2.setVisible(true);

		// gain healer
		healerCount = 1;
		ui.healerButtonPanel.setVisible(true);
		ui.warriorButton.setVisible(true);
	}

	public void hideWatch() {
		position = "hide";
		ui.mainTextArea.setText(
				"You find a place to hide and watch the fight closely. After some struggle, the figure manages to fight them off, but then collapses to the ground.");
		ui.choice1.setText("Rush to his aid");
		ui.choice2.setText("Don't bother to help");
	}

	public void aidHealer2() {
		position = "aidHealer2";
		ui.mainTextArea.setText("You rush to his side and manage to drag him on a nearby bench.\n'Water, please.'");
		ui.choice1.setText("Offer him some water");
	}

	public void notHelp1() {
		position = "noHelp1";
		ui.mainTextArea.setText("The latter started to whimper in pain, asking for some water.");
		ui.choice1.setText("Offer him some water");
		ui.choice2.setText("Walk past him");
	}

	public void offerWater() {
		position = "endQuest1";
		ui.mainTextArea.setText(
				"You offer him some water and after a few minutes, he sits up, feeling better.'Thank you adventurer, I appreciate the help. I am Brook. A healer. And you are?'You introduce yourself and while chatting you tell him about your quest and he offers to join you.\nBrook can fight alongside you and can heal you in case of serious injuries.\n\nHEALER ACQUIRED\t    Skill: Heal after a set of attacks\n\nYou roam around the village and come accross a desolated street where a small shady shop comes into view. ");
		ui.choice1.setText("Enter shop");
		ui.choice2.setText("View map");
		ui.choice1.setVisible(true);
		ui.choice2.setVisible(true);

		// gain healer
		healerCount = 1;
		ui.healerButtonPanel.setVisible(true);
	}

	public void walkPast() {
		position = "endQuest1";
		ui.mainTextArea.setText(
				"You walk past him, heading inside the village. While roaming around, you found a small shop.");
		ui.choice1.setText("Enter shop");
		ui.choice2.setText("View map");
		ui.choice2.setVisible(true);
	}

	public void village2() { // going to the village after side quest
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

	public void shop() {
		position = "shop";
		ui.monsterPanel.setVisible(false);
		ui.mainTextArea.setText(
				"Greeted by the old lady, she offers you a look at her goods.\n'Anything that interests you lad?'");
		ui.choice1.setText("Sell");
		ui.choice2.setText("Buy");
		ui.choice3.setText("View Map");
		ui.choice1.setVisible(true);
		ui.choice2.setVisible(true);
		ui.choice3.setVisible(true);
		ui.choice4.setVisible(false);
	}

	public void sell() {
		position = "sell";
		ui.mainTextArea.setText(
				"You can sell all the items you've acquired from destroying monsters.\n\nTradeable Possessions:\tPrice per item:\n Goblin Teeth - "
						+ goblinTeeth +"\t "+ priceGoblin +"  Golds\n Wraith Cloth - " + wraithCloth + "\t "+ priceWraith +"  Golds\n Orgre Claw - " + orgreClaw + "\t\t " + priceOrgre + "  Golds");
		ui.choice1.setText("Goblin Teeth");
		ui.choice2.setText("Wraith Cloth");
		ui.choice3.setText("Orgre Claw");
		ui.choice4.setText("Back");

		ui.choice2.setVisible(true);
		ui.choice3.setVisible(true);
		ui.choice4.setVisible(true);
	}

	public void sellItem(String item) { // method takes as attribute the name of item player wants to sell
		position = "sellItem";
		if (item == "GoblinTeeth") {
			if (goblinTeeth == 0) {
				ui.mainTextArea.setText("You do not have any Goblin Teeth to sell.");
			} else {
				int total = priceGoblin * goblinTeeth;
				gold = gold + total; // update gold after sale
				ui.goldLabelNumber.setText("" + gold);
				ui.mainTextArea.setText("You sold " + goblinTeeth + " Goblin Teeth and received " + total + " golds.");
				goblinTeeth = 0;
			}
		} else if (item == "WraithCloth") {
			if (wraithCloth == 0) {
				ui.mainTextArea.setText("You do not have any Wraith Cloth to sell.");
			} else {
				int total = priceWraith * wraithCloth;
				gold = gold + total; // update gold
				ui.goldLabelNumber.setText("" + gold);
				ui.mainTextArea.setText("You sold " + wraithCloth + " Wraith Cloth and received " + total + " golds.");
				wraithCloth = 0;
			}
		} else if (item == "OrgreClaw") {
			if (orgreClaw == 0) {
				ui.mainTextArea.setText("You do not have any Orgre Claw to sell.");
			} else {
				int total = priceOrgre * orgreClaw;
				gold = gold + total; // update gold
				ui.goldLabelNumber.setText("" + gold);
				ui.mainTextArea.setText("You sold " + orgreClaw + " Orgre Claw and received " + total + " golds.");
				orgreClaw = 0;
			}
		}
		ui.choice1.setText("Back");
		ui.choice2.setVisible(false);
		ui.choice3.setVisible(false);
		ui.choice4.setVisible(false);
	}

	public void buy() {
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

	public void weapons() {
		position = "chooseWeapon";
		ui.mainTextArea.setText("Choose a weapon:\nSword - 25 damage\nAxe - 20 damage");
		ui.choice1.setText("Sword - 80 gold");
		ui.choice2.setText("Axe - 40 gold");
		ui.choice3.setText("");
		ui.choice4.setText("Back");
		ui.choice3.setVisible(false);
		ui.choice4.setVisible(true);
		if (swordCount == 1) { // condition because item are only purchased once
			ui.choice1.setVisible(false);
		}
		if (axeCount == 1) {
			ui.choice2.setVisible(false);
		}
		if (swordCount == 1 && axeCount == 1) {
			ui.mainTextArea.setText("Weapons are currently out of stock");
		}
	}

	public void buyWeapons(String item) {
		position = "buyWeapon";
		if (item == "Sword") { // give to warrior
			if (gold < 80) {
				ui.mainTextArea.setText("You do not have enough gold to buy the sword.");
			} else {
				ui.mainTextArea.setText("You have acquired a sword.\nWarrior weapon\n\nDamage: " + sword.getDamage());
				swordCount = 1;
				gold = gold - 80;
				ui.goldLabelNumber.setText("" + gold);
				if (character == "warrior") {
					ui.weaponLabelName.setText(knife.getName() + "," + sword.getName());
				}
				warrior.setRightHandWeapon(sword);
			}
		} else if (item == "Axe") { // give to healer
			if (gold < 40) {
				ui.mainTextArea.setText("You do not have enough gold to buy the axe.");
			} else if (healerCount == 0) {
				ui.mainTextArea.setText("Axe can only be used by healer.");
			} else {
				ui.mainTextArea.setText("You have acquired an axe. Healer weapon.\n\nDamage: " + axe.getDamage());
				axeCount = 1;
				gold = gold - 40;
				ui.goldLabelNumber.setText("" + gold);
				if (character == "healer") {
					ui.weaponLabelName.setText("" + axe.getName());
				}
				healer.setWeapon(axe);
			}
		}
		ui.choice1.setText("Back");
		ui.choice1.setVisible(true);
		ui.choice2.setVisible(false);
		ui.choice3.setVisible(false);
		ui.choice4.setVisible(false);
	}

	public void spells() {
		position = "buySpell";
		ui.mainTextArea.setText("Choose a spell:\n\nFire - 30 damage \nLightning - 20 damage\nFrost - 15 damage\n");
		ui.choice1.setText("Fire - 70 gold");
		ui.choice2.setText("Lightning - 50 gold");
		ui.choice3.setText("Frost - 40 gold");
		ui.choice4.setText("Back");
		ui.choice4.setVisible(true);
		if (fireC == 1) {
			ui.choice1.setVisible(false);
		}
		if (lightC == 1) {
			ui.choice2.setVisible(false);
		}
		if (frostC == 1) {
			ui.choice3.setVisible(false);
		}
		if (fireC == 1 && lightC == 1 && frostC == 1) {
			ui.mainTextArea.setText("Spells are currently out of stock");
		}
	}

	public void buySpells(String item) {
		position = "chooseSpell";
		if (item == "Fire") {
			if (gold < 70) {
				ui.mainTextArea.setText("You do not have enough gold to buy this spell.");
			} else {
				ui.mainTextArea.setText("You have acquired the fire spell.\nDamage: " + fire.getDamage());
				gold = gold - 70;
				ui.goldLabelNumber.setText("" + gold);
				fireC = 1;

			}
		} else if (item == "Lightning") {
			if (gold < 50) {
				ui.mainTextArea.setText("You do not have enough gold to buy this spell.");
			} else {
				ui.mainTextArea.setText("You have acquired the lightning spell.\nDamage: " + lightning.getDamage());
				gold = gold - 50;
				ui.goldLabelNumber.setText("" + gold);
				lightC = 1;
			}
		} else if (item == "Frost") {
			if (gold < 40) {
				ui.mainTextArea.setText("You do not have enough gold to buy this spell.");
			} else {
				ui.mainTextArea.setText("You have acquired the frost spell.\nDamage: " + frost.getDamage());
				gold = gold - 40;
				ui.goldLabelNumber.setText("" + gold);
				frostC = 1;
			}
		}
		ui.choice1.setText("Back");
		ui.choice1.setVisible(true);
		ui.choice2.setVisible(false);
		ui.choice3.setVisible(false);
		ui.choice4.setVisible(false);
	}

	public void potions() {
		position = "buyPotion";
		ui.mainTextArea.setText("Choose potion:\n\nHealing potion - Recover Hp  (One-time use, activates upon purchase)\nCuring potion - Cure poison");
		ui.choice1.setText("Healing potion - 80 gold");
		ui.choice2.setText("Curing potion - 80 gold");
		ui.choice3.setText(" ");
		ui.choice4.setText("Back");

		if (HealPotionCount == 1) {
			ui.choice1.setVisible(false);
		}
		if (CurePotionCount == 1) {
			ui.choice2.setVisible(false);
		}
		if (HealPotionCount == 1 && CurePotionCount == 1) {
			ui.mainTextArea.setText("Potions are currently out of stock");
		}
		ui.choice3.setVisible(false);
		ui.choice4.setVisible(true);
	}

	public void buyPotions(String item) {
		position = "choosePotion";
		int healingPotionHP = 60;
		if (item == "HealingPotion") {
			if (gold < 80) {
				ui.mainTextArea.setText("You do not have enough gold to buy healing potion.");
			} else if (playerHP >= 70) {
				ui.mainTextArea.setText("Hp is already above 70. Buy potion later");
			} else {
				ui.mainTextArea.setText("You have acquired the healing potion.\nRecover: " + healingPotionHP + " Hp");
				gold = gold - 80;
				ui.goldLabelNumber.setText("" + gold);
				playerHP = playerHP + healingPotionHP;

				// update Hp to a maximum of 100
				if (playerHP > 100) {
					playerHP = 100;
				}

				warrior.setHP(playerHP);
				ui.hpLabelNumber.setText("" + playerHP);
				HealPotionCount = 1;
			}
		} else if (item == "CuringPotion") {
			if (gold < 80) {
				ui.mainTextArea.setText("You do not have enough gold to buy curing potion.");
			} else {
				ui.mainTextArea.setText("You have acquired the curing potion, which can be used against poison.");
				gold = gold - 80;
				ui.goldLabelNumber.setText("" + gold);
				CurePotionCount = 1;
			}
		}
		ui.choice1.setText("Back");
		ui.choice1.setVisible(true);
		ui.choice2.setVisible(false);
		ui.choice3.setVisible(false);
		ui.choice4.setVisible(false);
	}

	public void river1() { // going to river for the 1st time - side quest
		position = "river1";
		location = "River";
		ui.locationName.setText(location);
		mapPanel.setVisible(false);
		ui.mainTextPanel.setVisible(true);
		ui.mapButtonPanel.setVisible(false);

		ui.mainTextArea.setText(
				"You went down to a nearby river to rest and bath, when you see that the water has been poisoned.\n\nPeople in surrounding camps seem to be infected and in agony.");
		ui.choice1.setText("Investigate");
		ui.choice2.setText("View map");
		ui.choice3.setText("");
		ui.choice4.setText("");

		ui.choice1.setVisible(true);
		ui.choice2.setVisible(true);
		ui.choice3.setVisible(false);
		ui.choice4.setVisible(false);
	}

	public void investigate() {
		position = "investigate";
		location = "River";
		ui.locationName.setText(location);
		ui.mainTextPanel.setVisible(true);

		ui.mainTextArea.setText(
				"You move towards a person lying on the ground, hoping to get some informations from him.\n\nFeebly, he said:\n'The water, it's bad. Got poisoned by a witch. We need a cure. Please help us.'");
		ui.choice1.setText("Try to save them");
		ui.choice2.setText("Use curing potion");
		ui.choice3.setText("View map");

		ui.choice1.setVisible(true);
		ui.choice2.setVisible(true);
		ui.choice3.setVisible(true);
		ui.choice4.setVisible(false);
	}

	public void saveRiver() {
		position = "saveRiver";
		ui.healerButtonPanel.setVisible(false);
		ui.mainTextPanel.setVisible(true);
		if (healerCount == 0) { // player die without healer
			ui.mainTextArea.setText(
					"As you venture closer to investigate, you loose balance and fall in the water.\nThe poison starts to infect your whole body until you feel numb.\nWithout much help, you end up getting poisoned and you die in your own suffering.");
			playerHP = 0;
			warrior.setHP(0);
			ui.hpLabelNumber.setText("" + playerHP);

			// Game Over
			ui.choice1.setText(">");
			ui.choice1.setVisible(true);
			ui.choice2.setVisible(false);
			ui.choice3.setVisible(false);
			ui.choice4.setVisible(false);
		} else {
			if (character == "warrior") { // guide player to switch to healer first
				ui.mainTextArea.setText(
						"Having a good expertise, Brook tells you that he will be able to remove the poison and heal the people.");

				ui.choice2.setText("Switch to Brook");
				ui.choice1.setVisible(false);
				ui.choice3.setVisible(false);
				ui.choice4.setVisible(false);
			} else if (character == "healer") { // if character already == healer
				ui.mainTextArea.setText(
						"You get in the water without any issues and with a  powerful cleansing magic, you were able to cleanse the river from all the poison.\n\nHowever, this power came at a cost of 35 Hp\n\nFilled with hope, one of the boys comes up to you begging you to save him.\n\nCost of helping - 15 Hp");
				playerHP = warrior.getHP() - 35;
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

	public void usePotion(String name) { // alternate solution if player doesn't possess a healer
		position = "potion";
		if (name == "CuringPotion") {
			if (CurePotionCount == 0) {
				ui.mainTextArea.setText("You do not possess the potion. You might find one at the village shop.");
				ui.choice1.setText("View map");
				ui.choice2.setText("Back");
				ui.choice2.setVisible(true);
				ui.choice3.setVisible(false);
			} else if (CurePotionCount == 1) {
				ui.mainTextArea.setText(
						"You reach for your pockets and take out the curing potion.\n\nAt the same time, one of the boys commes towards you, begging you to save him.\n\nCost to saving the boy - 15 Hp\n\nBut, you possess only 1 potion.\nYou have to make a choice.");
				ui.choice3.setText("Save river");
				ui.choice4.setText("Save boy");
				ui.choice1.setVisible(false);
				ui.choice2.setVisible(false);
				ui.choice3.setVisible(true);
				ui.choice4.setVisible(true);
			}
		}
	}

	public void saveRiver2() {
		riverCount = 1;
		position = "endRiver";
		ui.healerButtonPanel.setVisible(true);
		ui.mainTextArea.setText(
				"You decide that saving the river would be the best choice and you empty the flask into the river.\n\nThe boy was left in anguish.\nUnable to fight against the poison, he succumbs to his death.");
		ui.choice1.setText("View map");
		ui.choice1.setVisible(true);
		ui.choice2.setVisible(false);
		ui.choice3.setVisible(false);
		ui.choice4.setVisible(false);
	}

	public void saveBoy() { // acquire mage
		riverCount = 1;
		position = "endRiver";
		ui.healerButtonPanel.setVisible(true);
		ui.mainTextArea.setText(
				"Feeling pity for the boy, you decide to save him.\n\nOut of gratitude, he decides to help you in your quest.\n\nYou found yourself a mage. Sam can fight alongside you using all sorts of spells.\n\nMAGE ACQUIRED\n\nWeapon: Grimoire - 10 damage      Spell: Blast - 12 damage");
		playerHP = playerHP - 15;
		ui.choice1.setText("View map");
		ui.choice1.setVisible(true);
		ui.choice2.setVisible(false);
		ui.choice3.setVisible(false);
		ui.choice4.setVisible(false);

		// gain mage
		mageCount = 1;
		ui.mageButtonPanel.setVisible(true);
	}

	public void NosaveBoy() {
		riverCount = 1;
		position = "endRiver";
		location = "River";
		ui.locationName.setText(location);
		ui.healerButtonPanel.setVisible(true);
		ui.mainTextPanel.setVisible(true);

		ui.mainTextArea.setText(
				"You move away from him, leaving him in anguish. Unable to fight against the poison, he succumbs to his death.");
		ui.choice1.setText("View map");

		ui.choice1.setVisible(true);
		ui.choice2.setVisible(false);
		ui.choice3.setVisible(false);
		ui.choice4.setVisible(false);
	}

	// Going to river after the quest
	public void river2() {
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

	// Going to forest for the 1st time - encounter goblin
	public void forest1() {
		forestCount = 1;
		position = "forest";
		location = "Forest";
		ui.locationName.setText(location);
		mapPanel.setVisible(false);
		ui.mainTextPanel.setVisible(true);
		ui.choiceButtonPanel.setVisible(true); // Set the choiceButtonPanel to visible

		goblinSetup();
		if (goblinHP > 0) {
			ui.mainTextArea.setText(
					"You make way to the forest where you see a goblin coming towards you.\n\nYou engage in a fight with the goblin");
			ui.choice1.setText("Fight the goblin");
			ui.choice2.setText("");
			ui.choice3.setText("");
			ui.choice4.setText("");

			ui.choice2.setVisible(false);
			ui.choice3.setVisible(false);
			ui.choice4.setVisible(false);
			System.out.println("" + goblinHP);
		}
	}

	public void endForest1() { // going back to forest after defeating goblin
		position = "endforest1";
		location = "Forest";
		ui.locationName.setText(location);
		mapPanel.setVisible(false);
		ui.mainTextPanel.setVisible(true);
		ui.choiceButtonPanel.setVisible(true); // Set the choiceButtonPanel to visible

		ui.mainTextArea
				.setText("You go back to the forest and see no monsters.\n\nYou can advance further in the forest");
		ui.choice1.setText("Advance in forest");
		ui.choice2.setText("View map");

		ui.choice3.setVisible(false);
		ui.choice4.setVisible(false);

	}

	// Gobin Attack
	public void goblinAttack() {
		position = "goblinAttack";
		ui.monsterPanel.setVisible(true);
		ui.mapButtonPanel.setVisible(false);
		ui.mainTextPanel.setVisible(true);

		int damage = new java.util.Random().nextInt(1, 20); // goblin attack causing random damage
		ui.mainTextArea.setText("The goblin attacked you giving " + damage + " damage.");

		warrior.takeDamage(damage); // decrease HP of warrior

		// Update playerHP based on the damage taken
		playerHP -= damage;
		// Ensure playerHP doesn't go below 0
		if (playerHP < 0) {
			playerHP = 0;

			// Game Over
			ui.choice2.setText(">");
			ui.choice2.setVisible(true);
			ui.choice1.setVisible(false);
			ui.choice3.setVisible(false);
		}

		// Update hp of player in the UI
		ui.hpLabelNumber.setText("" + playerHP);

		if (playerHP > 0) {
			ui.choice1.setText("Attack goblin");

			ui.choice4.setText("");
			ui.choice4.setVisible(false);
		}

		// No heal option if Hp = 0
		if (healerCount == 1 && turn > 3 && playerHP <= 0) {
			ui.choice4.setVisible(false);
		}
	}

	public void AttackGoblin() {
		position = "attackGoblin";
		ui.mapButtonPanel.setVisible(false);
		ui.mainTextPanel.setVisible(true);

		int damage = 0;
		turn += 1; // Turn = total number of attacks

		if (character == "warrior") {
			damage = warrior.damage(); // method identifies weapon/s of warrior and calculate its total damage.
		} else if (character == "healer") {
			damage = healer.damage(); // method identifies weapon of healer and calculate its total damage.
		} else if (character == "mage") {
			if (attack == "blast") {
				damage = blast.getDamage() + mage.damage(); // method to get damage of each spell + damage of grimoire
			}
			if (attack == "fire") {
				damage = fire.getDamage() + mage.damage();
			}
			if (attack == "frost") {
				damage = frost.getDamage() + mage.damage();
			}
			if (attack == "light") {
				damage = lightning.getDamage() + mage.damage();
			}
		}

		ui.mainTextArea.setText("You attack the goblin back, giving it " + goblinHP
				+ " damage. The goblin has been defeated.\n\n\nYou've acquired 3 Goblin Teeth.\n\nItem can be sold for gold in the shop in village.");
		
		goblin.takeDamage(damage); // update hp of goblin
		goblinHP -= damage;
		// Ensure playerHP doesn't go below 0
		if (goblinHP < 0) {
			goblinHP = 0;
		}

		ui.monsterHP.setText("" + goblinHP);

		if (goblinHP > 0) { // if goblin still alive
			ui.mainTextArea.setText("You attack the goblin back, giving it " + damage + " damage.");
			ui.choice1.setText(">");
			ui.choice2.setText("");
			ui.choice3.setText("");
			ui.choice4.setText("");

			ui.choice2.setVisible(false);
			ui.choice3.setVisible(false);
			ui.choice4.setVisible(false);
		} else { // if goblin has been defeated
			goblin.setHP(0);
			ui.monsterHP.setText("" + goblin.getHP()); // diplay goblin hp as 0

			position = "endFightForest";

			ui.choice1.setText("Advance in the forest");
			ui.choice2.setText("View map");
			ui.choice2.setVisible(true);

			ui.choice1.setVisible(true);
			ui.choice3.setVisible(false);
			ui.choice4.setVisible(false);

			goblinTeeth = goblinTeeth + 6;
		}

		// give healer option to heal after a number of attack
		if (healerCount == 1 && turn > 3) {
			ui.choice4.setText("Heal");
			ui.choice4.setVisible(true);
		}
	}

	public void forest2() {
		position = "forest2";
		location = "Forest";
		ui.locationName.setText(location);
		mapPanel.setVisible(false);
		ui.monsterPanel.setVisible(false);
		ui.mainTextPanel.setVisible(true);
		ui.mapButtonPanel.setVisible(false);

		ui.mainTextArea.setText("You arrive at a crossroad in the forest.\n\nYou can either go right or left.");
		ui.choice1.setText("Right");
		ui.choice2.setText("Left");
		ui.choice3.setText("View map");
		ui.choice4.setText("");

		ui.choice1.setVisible(true);
		ui.choice2.setVisible(true);
		ui.choice3.setVisible(true);
		ui.choice4.setVisible(false);
	}

	public void left() {
		position = "left";

		if (chestForest == 0) { // condition ensuring that chest can be opened only once
			ui.mainTextArea.setText("You find a hidden chest.");
			ui.choice1.setVisible(true);
		} else {
			ui.mainTextArea.setText("You already opened the chest.");
			// ui.choice1.setVisible(false);
			ui.choice2.setVisible(true);
			ui.choice1.setVisible(false);

		}

		ui.choice1.setText("Open Chest");
		ui.choice2.setText("Advanced in forest");
		ui.choice3.setText("Turn back");
		ui.choice4.setText("");

		// ui.choice2.setVisible(false);
		ui.choice3.setVisible(true);
		ui.choice4.setVisible(false);

	}

	public void waterfall() {
		location = "Waterfall";
		position = "waterfallfight";
		ui.monsterPanel.setVisible(true);
		ui.mainTextArea.setText("You arrived at a waterfall and the place is filled with wraith.");
		ui.choice1.setText("Attack");
		ui.choice2.setText("Turn back");
		ui.choice3.setText("");
		ui.choice4.setText("");

		ui.choice1.setVisible(true);
		ui.choice2.setVisible(true);
		ui.choice3.setVisible(false);
		ui.choice4.setVisible(false);
	}

	public void waterfall2() { // going back to waterfall after defeating wraith
		position = "waterfall";
		ui.monsterPanel.setVisible(false);
		ui.mapButtonPanel.setVisible(false);
		ui.mainTextPanel.setVisible(true);
		ui.mainTextArea.setText("The waterfall is now free from monsters.");

		ui.choice1.setText("Go for a swim");
		ui.choice2.setText("Turn back");
		ui.choice3.setText("");
		ui.choice4.setText("");

		ui.choice1.setVisible(true);
		ui.choice2.setVisible(true);
		ui.choice3.setVisible(false);
		ui.choice4.setVisible(false);
	}

	public void swim() { // player dies
		position = "swim";
		ui.mapButtonPanel.setVisible(false);
		ui.healerButtonPanel.setVisible(false);
		ui.warriorButtonPanel.setVisible(false);
		ui.mageButtonPanel.setVisible(false);
		ui.mainTextPanel.setVisible(true);
		ui.mainTextArea.setText("You decide to go for a swim.\n\nHowever, the water was too deep and you drowned.");
		ui.choice1.setText(">");
		ui.choice1.setVisible(true);
		ui.choice2.setVisible(false);
		ui.choice3.setVisible(false);
		ui.choice4.setVisible(false);
	}

	public void right() {
		position = "right";
		ui.mainTextArea.setText(
				"While venturing deep in the forest, you arrived at the foot of a mountain.\n\nThe path to the summit appears treacherous, windy through the rocky terrain.\n\nIts shadowy twists hinting at the dangers that lie ahead.");
		ui.choice1.setText("Climb the mountain");
		ui.choice2.setText("Turn back");
		ui.choice3.setText("");
		ui.choice4.setText("");

		ui.choice1.setVisible(true);
		ui.choice2.setVisible(true);
		ui.choice3.setVisible(false);
		ui.choice4.setVisible(false);
	}

	public void orgre() {
		location = "Mountain";
		ui.locationName.setText(location);
		ui.monsterPanel.setVisible(true);
		position = "orgre";
		ui.mainTextArea.setText("On your way up, you find yourself face to face with an enormous orgre.");
		ui.choice1.setText("Attack");
		ui.choice2.setText("View map");
	}

	public void Mountain() {
		position = "mountainTop";
		ui.monsterPanel.setVisible(false);
		ui.mainTextArea.setText(
				"Breathless, you arrive at the top and notice a small dark cave.\n\nAt the entrance, you find a dirty old torch, but still usable.\n\n(Danger: Proceeding to the mountain will not allow you to go back/open your map. Adventurers are advised to be fully prepared.)");
		ui.choice1.setText("Take the torch");
		ui.choice2.setText("Leave torch");
		ui.choice3.setText("View map");
		ui.choice4.setText("");

		ui.choice1.setVisible(true);
		ui.choice2.setVisible(true);
		ui.choice3.setVisible(true);
		ui.choice4.setVisible(false);
	}

	public void cave() {
		location = "Cave";
		ui.locationName.setText(location);
		ui.healerButtonPanel.setVisible(true);
		ui.choice2.setVisible(false);
		position = "cave";

		if (torch == 0) {
			ui.mainTextArea.setText(
					"Leaving the torch, you entered the dark cave. Unable to avoid the obstacles, you take damage.\n\nDamage inccured = 10 HP\n\nEventually you find yourself at a dead end.\n\nBefore you, two towering statues stand guard.");
			playerHP -= 15;
			ui.hpLabelNumber.setText("" + playerHP);

			if (playerHP <= 0) {

				playerHP = 0;
				ui.choice2.setText(">");
				ui.choice2.setVisible(true);
				ui.choice1.setVisible(false);

			}
		} else {
			ui.mainTextArea.setText(
					"You pick up the torch and lit it.\nIts flickering flame casting a feeble glow, providing some light as you walk in the darkness.\n\nGuided by the torchlight, you successfully avoid the obstacles.\n\nEventually you find yourself at a dead end.\n\nBefore you, two towering statues stand guard.");
		}
		ui.choice1.setText("Approach statues");
		ui.choice3.setVisible(false);

	}

	// PUZZLE: STATUE'S DILEMMA
	public void statues() {
		position = "statues";

		ui.mainTextArea.setText(
				"Approaching the statutes, inscriptions on the wall catches your attention:\n\n'Two paths lie ahead, but choose wisely.One shall lead to triumph, the other to dismay.'\n\nStatue of Radiance: The statue exudes warmth,emiting a gentle, soothing light.\n\nStatue of Vigor: The statue radiates strength, shimmering with a vibrant gleam\n\n");

		ui.choice1.setText("Push the Statue of Radiance");
		ui.choice2.setText("Push the Statue of Vigor");
		ui.choice2.setVisible(true);
		ui.choice3.setVisible(false);

	}

	public void statueVigor() { // puzzle solved
		position = "puzzle";
		ui.mainTextArea.setText("The statue shifts to the side, revealing a previously concealed pathway.");
		ui.choice1.setText("Advance");
		ui.choice2.setVisible(false);
		ui.choice3.setVisible(false);
	}

	public void statueRadiance() {
		position = "puzzleAttack";
		ui.monsterPanel.setVisible(true);
		ui.mainTextArea.setText(
				"The statue shifts to the side, triggering a hidden trap.\n\nYour action frees a wolf from its ages-old confinement.\n\nThe creature's growl echoes as it lunges forward, catching you off guard and forcing you into a fierce struggle.");
		ui.choice1.setText("Fight");
		ui.choice2.setVisible(false);
		ui.choice3.setVisible(false);
	}

	public void inside() {
		position = "inside";
		location = "Cave";
		ui.monsterPanel.setVisible(true);
		ui.mainTextArea.setText(
				"Once inside, you see a girl trapped in a cage.\nYou rush to free her.\n\nUnkowingly, you woke up the dragon and it charges at you.");
		ui.choice1.setText("Attack");
		ui.choice1.setVisible(true);
		ui.choice2.setVisible(false);
		ui.choice3.setVisible(false);
	}

	public void monsterAttack(String name) {
		if (name == "wolf") {

			position = "wolfAttack";
			location = "left";

			int damage = new java.util.Random().nextInt(2, 30); // random damage
			ui.mainTextArea.setText("The wolf attacked you giving " + damage + " damage.");

			warrior.takeDamage(damage); // decrease HP of warrior

			// Update playerHP based on the damage taken
			playerHP -= damage;

			// Ensure playerHP doesn't go below 0
			if (playerHP <= 0) {
				playerHP = 0;

				ui.choice2.setText(">");
				ui.choice2.setVisible(true);
				ui.choice1.setVisible(false);
				ui.choice3.setVisible(false);
			}

			// Update hp of player in the UI
			ui.hpLabelNumber.setText("" + playerHP);

			if (playerHP > 0) {

				ui.choice1.setText("Attack wolf");
				ui.choice2.setVisible(false);
				ui.choice3.setVisible(false);
				ui.choice4.setVisible(false);

			}
		}

		else if (name == "orgre") { // path to mountain
			position = "orgreAttack";

			int damage = new java.util.Random().nextInt(4, 25);

			ui.mainTextArea.setText("The orgre attacked you giving " + damage + " damage.");

			warrior.takeDamage(damage); // decrease HP of warrior
			// healer.takeDamage(damage);

			// Update playerHP based on the damage taken
			playerHP -= damage;
			// Ensure playerHP doesn't go below 0
			if (playerHP <= 0) {
				playerHP = 0;

				ui.choice3.setText(">");
				ui.choice3.setVisible(true);
				ui.choice1.setVisible(false);
				ui.choice2.setVisible(false);
			}

			// Update hp of player in the UI
			ui.hpLabelNumber.setText("" + playerHP);

			if (playerHP > 0) {
				ui.choice1.setText("Attack orgre");
				ui.choice2.setText("Retreat");
				ui.choice3.setText("");
				ui.choice4.setText("");

				ui.choice1.setVisible(true);
				ui.choice2.setVisible(true);
				ui.choice3.setVisible(false);
				ui.choice4.setVisible(false);
			}
		} else if (name == "wraith") { // waterfall
			position = "wraithAttack";
			int damage = new java.util.Random().nextInt(10, 30);

			ui.mainTextArea.setText("The wraith attacked you giving " + damage + " damage.");

			warrior.takeDamage(damage); // decrease HP of warrior

			// Update playerHP based on the damage taken
			playerHP -= damage;
			// Ensure playerHP doesn't go below 0
			if (playerHP <= 0) {
				playerHP = 0;

				ui.choice3.setText(">");
				ui.choice3.setVisible(true);
				ui.choice1.setVisible(false);
				ui.choice2.setVisible(false);
			}

			// Update hp of player in the UI
			ui.hpLabelNumber.setText("" + playerHP);

			if (playerHP > 0) {
				ui.choice1.setText("Attack wraith");
				ui.choice2.setText("Retreat");
				ui.choice3.setText("");
				ui.choice4.setText("");
				ui.choice1.setVisible(true);
				ui.choice2.setVisible(true);
				ui.choice3.setVisible(false);
				ui.choice4.setVisible(false);
			}
		} else if (name == "dragon") { // final boss
			position = "dragonAttack";

			int damage = new java.util.Random().nextInt(10, 30);

			ui.mainTextArea.setText("The dragon attacked you giving " + damage + " damage.");

			warrior.takeDamage(damage); // decrease HP of warrior

			// Update playerHP based on the damage taken
			playerHP -= damage;
			// Ensure playerHP doesn't go below 0
			if (playerHP <= 0) {
				playerHP = 0;

				ui.choice2.setText(">");
				ui.choice2.setVisible(true);
				ui.choice1.setVisible(false);
			}

			// Update hp of player in the UI
			ui.hpLabelNumber.setText("" + playerHP);

			if (playerHP > 0) {
				ui.choice1.setText("Attack dragon");
				ui.choice3.setText("");
				ui.choice4.setText("");
				ui.choice1.setVisible(true);
				ui.choice2.setVisible(false);
				ui.choice3.setVisible(false);
				ui.choice4.setVisible(false);
			}
		}

		// No heal option if Hp = 0
		if (healerCount == 1 && turn > 3 && playerHP <= 0) {
			ui.choice4.setVisible(false);
		}
	}

	// Method for attacking monster, taking the attribute monster name (to know
	// which monster we are attacking).
	public void attackMonster(String name) {
		if (name == "wolf") { // in cave - puzzle failed
			position = "attackWolf";
			ui.monsterPanel.setVisible(true);

			turn += 1; // Turn = total number of attacks
			int damage = 0;

			if (character == "warrior") {
				damage = warrior.damage(); // method identifies weapon/s of warrior and calculate its total damage.
			} else if (character == "healer") {
				damage = healer.damage(); // method identifies weapon of healer and calculate its total damage.
			} else if (character == "mage") {
				if (attack == "blast") {
					damage = blast.getDamage() + mage.damage(); // method to get damage of each spell + damage of
																// grimoire
				}
				if (attack == "fire") {
					damage = fire.getDamage() + mage.damage();
				}
				if (attack == "frost") {
					damage = frost.getDamage() + mage.damage();
				}
				if (attack == "light") {
					damage = lightning.getDamage() + mage.damage();
				}
			}

			if (wolf.getHP() <= damage) {
				ui.mainTextArea.setText("You attack the wolf, giving it " + wolfHP + " damage, defeating it.");
				wolf.takeDamage(damage); // method updates and set hp of wolf
				wolfHP -= damage;

				// Ensure HP doesn't go below 0
				if (wolfHP < 0) {
					wolfHP = 0;
				}
				wolf.setHP(0);
				ui.monsterHP.setText("" + wolfHP); // method hp of wolf in monster panel

				ui.choice2.setText("Advance");

				ui.choice2.setVisible(true);
				ui.choice1.setVisible(false);
				ui.choice3.setVisible(false);
				ui.choice4.setVisible(false);
			} else if (wolf.getHP() > damage) {
				ui.mainTextArea.setText("You attack the wolf, giving it " + damage + " damage.");
				wolf.takeDamage(damage); // method update & set hp of wolf
				wolfHP -= damage;
				ui.monsterHP.setText("" + wolfHP); // method hp of wolf in monster panel

				ui.choice1.setText(">");
				ui.choice2.setText("");
				ui.choice3.setText("");
				ui.choice4.setText("");

				ui.choice2.setVisible(false);
				ui.choice3.setVisible(false);
				ui.choice4.setVisible(false);
			}
		}

		else if (name == "orgre") { // on the way to mountain
			position = "attackOrgre";
			ui.monsterPanel.setVisible(true);

			turn += 1; // total number of attacks

			int damage = 0;

			if (character == "warrior") {
				damage = warrior.damage(); // method identifies weapon/s of warrior and calculate its total damage.
			} else if (character == "healer") {
				damage = healer.damage(); // method identifies weapon of healer and calculate its total damage.
			} else if (character == "mage") {
				if (attack == "blast") {
					damage = blast.getDamage() + mage.damage(); // method to get damage of each spell + damage of
																// grimoire
				}
				if (attack == "fire") {
					damage = fire.getDamage() + mage.damage();
				}
				if (attack == "frost") {
					damage = frost.getDamage() + mage.damage();
				}
				if (attack == "light") {
					damage = lightning.getDamage() + mage.damage();
				}
			}

			if (orgre.getHP() <= damage) {
				orgreCount = 1;
				ui.mainTextArea.setText("You attack the orgre, giving it " + orgreHP
						+ " damage, defeating it.\n\n\nYou've acquired 3 Orgre Claw.\n\nItem can be sold for gold in the shop in village.");
				orgreClaw = orgreClaw + 3;
				orgre.takeDamage(damage); // method update & set hp of orgre
				orgreHP -= damage;

				// Ensure HP doesn't go below 0
				if (orgreHP < 0) {
					orgreHP = 0;
				}
				orgre.setHP(0);
				ui.monsterHP.setText("" + orgreHP); // update orgre hp in monster panel

				ui.choice2.setText("Advance");
				ui.choice3.setText("View map");

				ui.choice2.setVisible(true);
				ui.choice3.setVisible(true);
				ui.choice1.setVisible(false);
			} else if (orgre.getHP() > damage) {
				ui.mainTextArea.setText("You attack the orgre, giving it " + damage + " damage.");
				orgre.takeDamage(damage); // method update & set hp of orgre
				orgreHP -= damage;
				ui.monsterHP.setText("" + orgreHP); // update orgre hp in monster panel

				ui.choice1.setText(">");
				ui.choice2.setText("");
				ui.choice3.setText("");
				ui.choice4.setText("");

				ui.choice2.setVisible(false);
				ui.choice3.setVisible(false);
				ui.choice4.setVisible(false);
			}
		}

		else if (name == "wraith") { // waterfall
			position = "attackWraith";
			ui.monsterPanel.setVisible(true);

			turn += 1; // total number of attacks
			int damage = 0;

			if (character == "warrior") {
				damage = warrior.damage(); // method identifies weapon/s of warrior and calculate its total damage.
			} else if (character == "healer") {
				damage = healer.damage(); // method identifies weapon of healer and calculate its total damage.
			} else if (character == "mage") {
				if (attack == "blast") {
					damage = blast.getDamage() + mage.damage(); // method to get damage of each spell + damage of
																// grimoire
				}
				if (attack == "fire") {
					damage = fire.getDamage() + mage.damage();
				}
				if (attack == "frost") {
					damage = frost.getDamage() + mage.damage();
				}
				if (attack == "light") {
					damage = lightning.getDamage() + mage.damage();
				}
			}

			if (wraith.getHP() <= damage) {
				waterfallCount = 1;
				ui.mainTextArea.setText("You attack the wraith back, giving it " + wraithHP
						+ " damage. The wraith has been defeated.\n\n\nYou've acquired 4 Wraith Cloth.\n\nItem can be sold for gold in the shop in village.");
				wraithCloth += wraithCloth + 4;
				wraith.takeDamage(damage); // method update & set hp of wraith
				wraithHP -= damage;

				// Ensure HP doesn't go below 0
				if (wraithHP < 0) {
					wraithHP = 0;
				}
				wraith.setHP(0);
				ui.monsterHP.setText("" + wraithHP); // update wraith hp in monster panel

				ui.choice2.setText("Advance in forest");

				ui.choice2.setVisible(true);
				ui.choice3.setVisible(false);
				ui.choice1.setVisible(false);
				ui.choice4.setVisible(false);
			} else if (wraith.getHP() > damage) {
				ui.mainTextArea.setText("You attack the wraith, giving it " + damage + " damage.");
				wraith.takeDamage(damage); // method update & set hp of wraith
				wraithHP -= damage;
				ui.monsterHP.setText("" + wraithHP); // update wraith hp in monster panel

				ui.choice1.setText(">");
				ui.choice2.setText("");
				ui.choice3.setText("");
				ui.choice4.setText("");

				ui.choice2.setVisible(false);
				ui.choice3.setVisible(false);
				ui.choice4.setVisible(false);
			}

		}

		else if (name == "dragon") { // final boss
			position = "attackDragon";
			ui.monsterPanel.setVisible(true);

			turn += 1; // total number of attacks
			int damage = 0;

			if (character == "warrior") {
				damage = warrior.damage(); // method identifies weapon/s of warrior and calculate its total damage.
			} else if (character == "healer") {
				damage = healer.damage(); // method identifies weapon of healer and calculate its total damage.
			} else if (character == "mage") {
				if (attack == "blast") {
					damage = blast.getDamage() + mage.damage(); // method to get damage of each spell + damage of
																// grimoire
				}
				if (attack == "fire") {
					damage = fire.getDamage() + mage.damage();
				}
				if (attack == "frost") {
					damage = frost.getDamage() + mage.damage();
				}
				if (attack == "light") {
					damage = lightning.getDamage() + mage.damage();
				}
			}

			if (dragon.getHP() <= damage) {
				ui.mainTextArea.setText("You attack the dragon, giving it " + dragonHP
						+ " damage, defeating it.\n\nYou free the girl and take her back to town.");
				dragon.takeDamage(damage); // method update & set hp of dragon
				dragonHP -= damage;

				// Ensure Hp doesn't go below 0
				if (dragonHP < 0) {
					dragonHP = 0;
				}
				dragon.setHP(0);
				ui.monsterHP.setText("" + dragonHP); // update dragon hp in monster panel

				ui.choice2.setText(">");
				ui.choice3.setText("");

				ui.choice2.setVisible(true);
				ui.choice3.setVisible(false);
				ui.choice1.setVisible(false);
			} else if (dragonHP > 0) {
				ui.mainTextArea.setText("You attack the dragon, giving it " + damage + " damage.");
				dragon.takeDamage(damage); // method update & set hp of dragon
				dragonHP -= damage;
				ui.monsterHP.setText("" + dragonHP); // update dragon hp in monster panel

				ui.choice1.setText(">");
				ui.choice2.setText("");
				ui.choice3.setText("");
				ui.choice4.setText("");

				ui.choice2.setVisible(false);
				ui.choice3.setVisible(false);
				ui.choice4.setVisible(false);
			}
		}

		// give healer option to heal after a number of attack
		if (healerCount == 1 && turn > 3) {
			ui.choice4.setText("Heal");
			ui.choice4.setVisible(true);
		}

	}

	public void dead() { // method when player dies
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

	public void Restart() { // restart game after dying
		// To close the JFrame programmatically:
		ui.window.dispose();

		ui.titleUI();
		ui.choices();
		ui.titleNamePanel.setVisible(false);
		ui.startButtonPanel.setVisible(false);
		ui.Gameplay("start");
	}

	public void continueGame() { // load game from previous save, after dying
		// To close the JFrame programmatically:
		ui.window.dispose();

		ui.titleUI();
		ui.choices();
		ui.titleNamePanel.setVisible(false);
		ui.startButtonPanel.setVisible(false);
		ui.Gameplay("continue");
	}

	public void end() { // method for when player wins the game
		ui.mainTextArea.setText("You won!\n\n The End.");
		ui.choice1.setVisible(false);
		ui.choice2.setVisible(false);
		ui.choice3.setVisible(false);
		ui.choice4.setVisible(false);
		ui.healerButtonPanel.setVisible(false);
		ui.warriorButtonPanel.setVisible(false);
		ui.mageButtonPanel.setVisible(false);
		ui.monsterPanel.setVisible(false);
	}

	public void goldchest() {
		position = "goldchest";
		chestForest = 1;
		ui.mapButtonPanel.setVisible(false);
		ui.mainTextPanel.setVisible(true);

		Reward reward = generateRandomReward();

		if (reward instanceof Gold) { // obtain gold from chest
			Gold goldReward = (Gold) reward;
			int addGold = goldReward.getAmount(); // random amount
			gold = gold + addGold; // update gold of player
			// update gold of player
			ui.goldLabelNumber.setText("" + gold);
			ui.mainTextArea.setText("You opened the chest and found " + addGold + " gold.");
		} else if (reward instanceof Weapon) { // obtain weapon from chest

			Weapon weaponReward = (Weapon) reward;
			String weaponName = weaponReward.getName(); // random weapon between sword or axe

			if (weaponName == "Sword") { // update player weapon
				if (character == "warrior" && swordCount == 0) {
					swordCount = 1;
					warrior.setRightHandWeapon(sword);
					ui.weaponLabelName.setText(knife.getName() + "," + sword.getName());
					ui.mainTextArea.setText("You opened the chest and found a " + weaponName + ".\n\nA warrior's weapon\n\nKnife - 10 damage\nSword - 25 damage ");
				
				}

				else {
					ui.mainTextArea.setText("You found a " + weaponName
							+ " but already in possession.\nThere's no need for them to acquire another.");
				}
			}

			if (weaponName == "Axe") {
				if (character == "healer" || axeCount == 0) { // obtain axe even if character not healer (can use when
																// healer obtained)
					axeCount = 1;
					healer.setWeapon(axe);
					ui.mainTextArea.setText("You opened the chest and found an " + weaponName + ".\n\nA healer's weapon\n\nAxe - 20 damage");
				}

				else {
					ui.mainTextArea.setText("You found a " + weaponName
							+ ".\nWeapon already in possession.\nThere's no need for them to acquire another.");
				}
			}
		}

		if (location == "Forest") {
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

	// Serializable interface
	class Reward implements Serializable {
		// Base class for rewards serves as a placeholder base class for different types
		// of rewards
		// it acts as a common superclass to enable polymorphism and help organize the
		// different types of rewards.
	}

	// polymorphism
	class Gold extends Reward { // gold being a sub-class of reward
		private int amount;

		public Gold(int amount) { // constructor taking in parameter the amount of gold
			this.amount = amount;
		}

		public int getAmount() { // retrieve amount of gold
			return amount;
		}
	}

	class Weapon extends Reward { // weapon being a sub-class of reward
		private String name;

		public Weapon(String name) { // constructor taking in parameter the name of weapon
			this.name = name;
		}

		public String getName() { // retrieve name of weapon
			return name;
		}
	}

	private Reward generateRandomReward() { // method that generates random reward
		Random random = new Random();
		int chance = random.nextInt(100); // Generating a random number between 0 and 99 (inclusive)

		if (chance < 80) { // 80% chance to get gold
			int randomGold = random.nextInt(50, 300) + 1; // Generates a random number between 1 and 1000 (inclusive)
			return new Gold(randomGold);
		} else { // 20% chance to get a weapon
			String[] weapons = { "Sword", "Axe" };
			int randomWeaponIndex = random.nextInt(weapons.length);
			return new Weapon(weapons[randomWeaponIndex]);
		}
	}
}
