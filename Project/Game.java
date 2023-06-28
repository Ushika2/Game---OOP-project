import character.*;
import weapon.*;

public class Game{
    public static void main(String[] args){
        Sword sword = new Sword(10);
        Warrior player = new Warrior("John", 100, sword, sword);
    }
}