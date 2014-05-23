/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2011.07.31
 */

public class Game 
{
    private Parser parser;
    private Player player;
    private int siguienteIDAAsignar;
    

    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        player = new Player();
        createRooms();
        parser = new Parser();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room galicia, leon, castilla, portugal, aragon, granada;

        // create the rooms
        galicia = new Room("el reino de Galicia");
        leon = new Room("el reino de León");
        castilla = new Room("el reino de Castilla");
        aragon = new Room("el reino de Aragon");
        granada = new Room("el reino de Granada");
        portugal = new Room("el reino de Portugal");                          

        // initialise room exits
        galicia.setExit("east", leon);
        galicia.setExit("south", portugal);
        galicia.setExit("southEast", castilla);

        leon.setExit("south", castilla);
        leon.setExit("west", galicia);

        castilla.setExit("north", leon);
        castilla.setExit("east", aragon);
        castilla.setExit("south", granada);
        castilla.setExit("west", portugal);
        castilla.setExit("northWest", galicia);

        granada.setExit("north", castilla);
        granada.setExit("jumpingNorthEast", aragon);

        aragon.setExit("west", castilla);

        portugal.setExit("north", galicia);
        portugal.setExit("east", castilla);
        
        //define the items
        galicia.addItem(new Item("Una espada gaelica", 3.5, true));
        galicia.addItem(new Item("Un tonel de vino", 10.2, false));
        
        leon.addItem(new Item("El Santo Grial", 1.4, true));
        
        castilla.addItem(new Item("Un cuchillo afilado", 0.5, true));
        castilla.addItem(new Item("Un cofre de oro", 3.0, true));
        castilla.addItem(new Item("Un baul de madera", 24.8, true));
        
        aragon.addItem(new Item("Una carreta", 35.5, false));
        
        granada.addItem(new Item("Una pocima contra el dolor de cabeza", 0.6, true));
        
        portugal.addItem(new Item("Una armadura", 20.2, false));        
    
        player.setCurrentRoom(leon);
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.

        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        player.printLocationInfo();
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        if(command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        Option commandWord = command.getCommandWord();
        switch (commandWord){
        case HELP:
             printHelp();
             break;
        case GO:
             player.goRoom(command);
             break;
        case LOOK:
             player.look();
             break;
        case EAT:
             player.eat();
             break;
        case BACK:
             player.back();
             break;
        case ITEMS:
             player.items();
             break;
        case TAKE:
             player.take(command);
             break;
        case QUIT:
             wantToQuit = quit(command);
             break;
        case DROP:
             player.drop(command);
             break;
        }
        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }

    

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }

}



















