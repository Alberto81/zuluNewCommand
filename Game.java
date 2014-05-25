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

        // create the rooms
        Room zapateria = new Room("Una tienda donde venden todo tipo de calzado.");
        Room plaza = new Room("amplia plaza redonda con una fuente enmedio");
        Room tiendaRopa = new Room("trapos a gogo");
        Room peluqueria = new Room("la peluqueria del centro comercial");
        Room descansillo = new Room("un espacio amplio al sur del centro comercial");
        Room servicios = new Room("los wc del centro comercial");
        Room salida = new Room("la salida del centro comercial");

        // initialise room exits
        zapateria.setExit("southEast", peluqueria);
        zapateria.setExit("south", plaza);
        zapateria.setExit("salta", salida);

        plaza.setExit("north", zapateria);
        plaza.setExit("west", tiendaRopa);
        plaza.setExit("south", descansillo);
        plaza.setExit("east", peluqueria);
        plaza.setExit("bucea", servicios);
        
        tiendaRopa.setExit("east",plaza );
        
        peluqueria.setExit("west", plaza);
        peluqueria.setExit("northWest", zapateria);

        descansillo.setExit("north", plaza);
        descansillo.setExit("south", salida);
        descansillo.setExit("east", servicios);
        descansillo.setExit("vuela", zapateria);

        servicios.setExit("east",descansillo );
        servicios.setExit("bucea", plaza);
        
        salida.setExit("north", descansillo);
        
        //define the items
        plaza.addItem(new Item("una gran maceta con una palmera.", 49.950F, true, "maceta", "no combina"));
        plaza.addItem(new Item("una cartera perdida.", 0.05F, true, "cartera", "no combina"));
        zapateria.addItem(new Item("una moneda reluciente.", 0.005F, true, "moneda", "maquina"));
        peluqueria.addItem(new Item("una revista del corazón.",0.2F, true, "revista", "no combina"));
        servicios.addItem(new Item("una llave perdida.", 0.03F, true, "llave", "no combina"));
        descansillo.addItem(new Item("una maquina de refrescos.", 150.0F, false, "maquina", "moneda"));
        
        player.setCurrentRoom(plaza);
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
        System.out.println("Bienvenido a Zuul!");
        System.out.println("World of Zuul es un nuevo e increiblemente aburrido juego.");
        System.out.println("escribe ayuda para saber de los comandos.");
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
            System.out.println("No entiendo lo que dices...");
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
        case USE:
            System.out.println(command.getSecondWord()+" "+command.getTerceraPalabra());
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



















