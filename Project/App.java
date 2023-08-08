public class App {

    static UI ui = new UI();  //declare static field ui
    Storyline storyline = new Storyline(null, ui);  //declare instance field of type Storyline

    public static void main(String[] args) throws Exception {  // Main method
        System.out.println("Hello, World!");
        new App();  // Constructor call initializes the application and executes the code inside the constructor.

    }

    // Constructor - App
    public App(){ 
        ui.titleUI();  // call method create ui from UI class

    }

}