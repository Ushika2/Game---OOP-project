import java.util.*;
import character.*;
import weapon.*;
import spell.*;

// class playerStatus{
//     public void defaultStats(){

//           int playerHP;
//           HP = 10;
          
//           String playerWeapon;
//           playerWeapon = "sword";

//           String location = "Home town entrance";

//           System.out.println("HP: " + playerHP);
//           System.out.println("weapon: " + playerWeapon);
//           System.out.println("Location: " + location);
//     }
//     public void currentstats(){   //give current stats and display
//     }
// }

class gameMap{
    public void map(){
        System.out.println("----------------------------------------------------");
        System.out.println("                       MAP                          ");
        System.out.println("----------------------------------------------------");
        
        System.out.println("1. Town");
        System.out.println("2. North");
        System.out.println("3. South");
        System.out.println("4. East");
        System.out.println("5. West");

       int choice;
        Scanner sc = new Scanner(System.in);  // Create a Scanner object
        choice = sc.nextInt();
        
        if(choice == 1){
            gameMap map = new gameMap();
             map.town();

        }
       if(choice == 2){
             
            gameMap map = new gameMap();
            map.north();

        }
        sc.close();
    }

    public void town(){

        System.out.println("----------------------------------------------------");
        System.out.println("                       TOWN                         ");
        System.out.println("----------------------------------------------------");

        System.out.println("At the entrance of the hometown, you see someone standing.");
        System.out.println("You go talk to the town cheif");

        System.out.println("Hello ..playername.. I am the cheif of this town. We need your help.");
        System.out.println("The safety of the town is threatened by a monster.");
        System.out.println("To ensure the safety of the townfolks you must find the monster and defeat it.");
        System.out.println("You may begin your quest by viewing the map.");

        gameMap map = new gameMap();
        map.map();
    }

    public void north(){
        System.out.println("----------------------------------------------------");
        System.out.println("                     NORTH                          ");
        System.out.println("----------------------------------------------------");

    }
    public void south(){
        System.out.println("----------------------------------------------------");
        System.out.println("                     SOUTH                          ");
        System.out.println("----------------------------------------------------");

    }
    public void east(){
        System.out.println("----------------------------------------------------");
        System.out.println("                       EAST                         ");
        System.out.println("----------------------------------------------------");

    }
    public void west(){
        System.out.println("----------------------------------------------------");
        System.out.println("                       WEST                         ");
        System.out.println("----------------------------------------------------");
    }
}


public class trial{
    public static void main(String args[]){

        String playerName;

        System.out.println("----------------------------------------------------");
        System.out.println("             WELCOME TO ADVENTURE GAME              ");
        System.out.println("----------------------------------------------------");

        Scanner sc = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Please enter your name:");
        playerName = sc.nextLine();  // Read user input

        // create characters
        // System.out.println("Choose a character:\n1.Warrior\n2.Healer\n3.Mage\n4.Archer");
        // int choice = sc.nextInt();
        //if(choice == 1){
            Sword sword = new Sword(10);
            Warrior player = new Warrior(playerName, 100, sword, sword);  //maybe should change afterwards
        //}
        // else if(choice == 2){
        //     Sword sword = new Sword(10);
        //     Healer player = new Healer(playerName, 100, 5, sword);
        // }
        // else if(choice == 3){
        //     Fire fire = new Fire(8);
        //     Mage player = new Mage(playerName, 100, fire);
        // }
        // else if(choice == 4){
        //     Bow bow = new Bow(15);
        //     Archer player = new Archer(playerName, 100, bow);
        // }
        // add error message?

        System.out.println("\nHello, " + playerName + " welcome to the advanture game.\n");
        System.out.println("Current status:");
        String location = "Home town entrance";

        System.out.println("HP: " + player.getHP());
        System.out.println("weapons: " + player.getLeftHandWeapon() + player.getRightHandWeapon());
        System.out.println("Location: " + location);
       
        System.out.println("\nLet the game start.\n");

        gameMap map = new gameMap();
        map.town();
        sc.close();
    }
}
