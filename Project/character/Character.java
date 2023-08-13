package character;  //package declaration

// Character 
public abstract class Character{  //abstract class definition
    private String name;   // private instance variables
    private int HP; //healthpoints

    public Character(String name, int HP){  // constructor, taking the attributes and assigning them to their respective fields
        this.name = name;
        this.HP = HP;
    }

    public String getName(){  //getter method that returns the name of the character
        return name;
    }

    public int getHP(){  //getter method that returns the Hp of the character
        return HP;
    }

    public void setHP(int HP){  //setter method which allow external class to set the Hp of the character
        this.HP = HP;
    }

    // Abstract methods
    public abstract void takeDamage(int damage);   //method where the character receives damage
    public abstract int damage();   //method which determine the amount of damage the character can cause
    
}


// Character:
// warrior
// Healer
// mage
// archer
