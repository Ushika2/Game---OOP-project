public class App {

    static UI ui = new UI();
    Storyline storyline = new Storyline(null, ui);

    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        new App();

    }

    
    public App()
    {
     
        ui.titleUI();  // call method create ui from UI class

    }


}