import java.util.Stack;
import java.util.ArrayList;
/**
 * A player of the game
 *
 */
public class Player
{

    private Room currentRoom;
    private Stack<Room> visitedRooms;
    private ArrayList<Item> mochila;
    private double cargaMaxima;
    private static final double CARGA_MAXIMA_POR_DEFECTO=50;

    public Player()
    {
        currentRoom = null;
        visitedRooms = new Stack<>();
        mochila = new ArrayList<Item>();
        cargaMaxima = CARGA_MAXIMA_POR_DEFECTO;
    }

    /** 
     * Try to go in one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     */
    public void goRoom(Command command) 
    {

        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.      
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            visitedRooms.push(currentRoom);          
            currentRoom = nextRoom;
            printLocationInfo();
        }
    }

    /** 
     * Print the room's long description 
     */   
    public void look()
    {
        printLocationInfo();
    }

    /**
     * The player eats
     */  
    public void eat() 
    {
        System.out.println("You have eaten now and you are not hungry any more");
    }

    /**
     * Return to the previous room
     */
    public void back()
    {
        if (!visitedRooms.empty()) {
            currentRoom = visitedRooms.pop();
            printLocationInfo();
        }
        else {
            System.out.println("You are at the beggining of the game");
            System.out.println();
        }
    }

    public void printLocationInfo()
    {
        System.out.println(currentRoom.getLongDescription());      
    }

    /**
     * Modifica la habitacion en la que esta el jugador
     * 
     * @param nuevaRoom la nueva habitacion
     */
    public void setCurrentRoom(Room nuevaRoom)
    {
        currentRoom = nuevaRoom;
    }

    /**
     * Imprime por pantalla los items que tiene ese jugador
     * 
     */
    public void items()
    {
        System.out.println("En la mochila hay " + mochila.size() + " objetos:");
        for(Item item : mochila)
        {
            System.out.println(item.getLongDescription());
        }

    }

    public String usar(Command command)
    {
        String codigo = null;

        if (!command.hasSecondWord()||!command.hasTerceraPalabra()){
            System.out.println("introduce el comando y los dos objetos separados por un espacio");
        }
        else{
            String item1 = command.getSecondWord();
            String item2 = command.getTerceraPalabra();
            int index = 0;
            boolean findObj1 = false; //nos interesa que cuando encuentre el objeto, deje de buscarlo, por si este está repetido
            boolean findObj2 = false;
            Item objeto = null;
            while(index < mochila.size()||(!findObj1 && !findObj2)){
                if(!findObj1){
                    if(mochila.get(index).getNombre().equals(item1)){
                        findObj1 = true;//dejamos de buscar la coincidencia con item1 y lo marcamos como encontrado
                        objeto = mochila.get(index);//guardamos uno de los dos objetos, para en caso de total coincidencia mirar si es la pareja de objetos necesitada.
                    }
                }

                if(!findObj2){
                    if(mochila.get(index).getNombre().equals(item2)){
                        findObj2 = true;//dejamos de buscar la coincidencia con item2, encontrado
                    }
                }
                index++;
            }//nos falta por buscar por los objetos fijos de la habitación.si un objeto ya ha sido encontrado, no lo buscaremos.
            //podriamos hacer esta parte del metodo con otro metodo que trabajase desde room, pero por estar trabajando con tantas varibles locales, lo mejor será crear
            //un método en room para traernos el arrayList de objetos.
            ArrayList<Item> itSuelo = currentRoom.getItems();
            index = 0;
           
            while(index < itSuelo.size()||(!findObj1 && !findObj2)){
                 
                if (!(itSuelo.get(index).canBeTaken() ) ){               //este primer condicional es para tener en cuenta solo los objetos fijos al suelo.
                    // estos bloques son iguales que en el bucle de mochila
                    if(!findObj1){
                        if(itSuelo.get(index).getNombre().equals(item1)){
                            findObj1 = true;//dejamos de buscar la coincidencia con item1 y lo marcamos como encontrado
                            objeto = itSuelo.get(index);//guardamos uno de los dos objetos, para en caso de total coincidencia mirar si es la pareja de objetos necesitada.
                        }
                    }

                    if(!findObj2){
                        if(itSuelo.get(index).getNombre().equals(item2)){
                            findObj2 = true;//dejamos de buscar la coincidencia con item2, encontrado
                        }
                    }
                }
                index++;
            }
            //ya hemos buscado todo lo que teniamos que buscar, llegados aqui sabemos si existen los dos objetos, en caso de existir:

            if (findObj1){System.out.println("objeto1 encontrad0");}
            if (findObj2){System.out.println("objeto2 encontrad0");}            
            
            
            
            if (findObj1 && findObj2){//si encontramos los dos objetos:
                if(objeto.getCombina().equals(item2)){
                    codigo = objeto. getCodigo();
                }else{System.out.println("los objetos no combinan");}
            }else{System.out.println("no dispones de uno o ambos objetos");}

        }
        return codigo;
    }

    public void accion(String cdg)
    {
        if (cdg == null){
            System.out.println("no ha pasado nada");
        }else if (cdg.equals("act1")){//segun añadamos nuevas combinaciones, pondremos más codigos para que cada una haga lo que querramos.
            // pongo peso cero al refresco para simplificar código. 
            mochila.add(new Item("una fresca, deliciosa y burbujeante bebida.", 0.0F, true, "refresco", "",""));
            System.out.println("añadido refresco a la mochila");
            int index =0;
            boolean eliminated = false;
            while(index < mochila.size()||!eliminated){
                if( mochila.get(index).getNombre().equals("moneda")){//así solo eliminamos la primera moneda que encontremos.
                    mochila.remove(index);
                    eliminated = true;
                    System.out.println("eliminada moneda de la mochila"); 
                }
                index++;
            }
        }
    }

    /**
     * Calculate the total weight for player's items.  

     * @return the total weight for the player's items
     */
    public double getTotalWeightItems()
    {
        double peso = 0D;
        for(Item item : mochila){
            peso += item.getWeight();
        }    
        return peso;
    }

    /**
     * Take de item contained in the given command
     */ 
    public void take(Command command){
        if (!command.hasSecondWord()){
            System.out.println("take what?");
            return;
        }

        String id = command.getSecondWord();
        Item item = currentRoom.getItem(id);
        if(item != null)
        {
            if(item.canBeTaken()){
                if(item.getWeight() +  getTotalWeightItems() <= cargaMaxima) {
                    System.out.println("You add a new item to your bag");
                    mochila.add(item);
                    currentRoom.removeItem(id);
                }
                else {
                    System.out.println("No hay espacio para este objeto");
                }
            }else{
                System.out.println("El Objeto no se puede coger");
            }
        }
        else
        {
            System.out.println("You don't select a item");
        }
    }

    /**
     * Drop an item of the player 
     * 
     */
    public void drop(Command command)    
    {
        if (!command.hasSecondWord()){
            System.out.println("drop what?");
            return;
        }

        int index = 0;
        boolean searching = true;
        while( searching && index < mochila.size()){
            Item item = mochila.get(index);
            if(item.getNombre().equals(command.getSecondWord())){
                currentRoom.addItem(item);
                mochila.remove(index);
                searching = false;
                System.out.println("El objeto se ha dejado en la habitacion");
            }
            index++;
        }

        if (searching)
        {
            System.out.println("No estas llevando el objeto que has indicado");
        }

    }

}	  

