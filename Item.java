/**
 * An item contained in a room
 * 
 * @author Miguel Bayon
 * @version 1.0
 */
public class Item
{
    private String description;
    private float weight;
    private String nombreCorto;
    private String combinaCon;
    private String accion;
    private boolean canBeTaken;
    
    /**
     * Constructor for objects of class Item
     * 
     * @param description The item's description
     * @param weight The item's weight
     */
    public Item(String description, float weight, boolean canBeTaken, String nombreCorto, String combinaCon, String accion)
    {
        this.description = description;
        this.weight = weight;
        this.canBeTaken = canBeTaken;
        this.nombreCorto = nombreCorto;
        this.combinaCon = combinaCon;
        this.accion = accion;
    }
    
     /**
     * asi solo daremos valores utiles a los objetos que se puedan combinar, sin tener que tocar el constructor de los items.
     */
    public void activaUsar(String combinaCon, String accion)
    {
        combinaCon = combinaCon;
        accion = accion;
    
    }
    
    /**
     * Get the long description of item
     * 
     * @return     The long description of item
     */
    public String getLongDescription()
    {
        return "Item: " + nombreCorto + "; " + description + " (" + weight + " kg.)";
    }
    
    
    /**
     * Devuelve el id del objeto
     * 
     * @return el id del objeto
     */
    public String getNombre()
    {
        return nombreCorto;
    }
    
    /**
     * Get the item's weight
     * 
     * @return weight the item's weight in kg
     */
    public double getWeight()  
    {
    	return weight;
    }  
    
    /**
     * Return if the item can be taken
     * 
     * @return true if the item can be taken, false otherwise
     */
    public boolean canBeTaken() {
     	return canBeTaken;
    }  
}



 


