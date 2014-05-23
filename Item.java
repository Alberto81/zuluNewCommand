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
    private boolean canBeTaken;
    
    /**
     * Constructor for objects of class Item
     * 
     * @param description The item's description
     * @param weight The item's weight
     */
    public Item(String description, float weight, boolean canBeTaken, String nombreCorto, String combinaCon)
    {
        this.description = description;
        this.weight = weight;
        this.canBeTaken = canBeTaken;
        this.nombreCorto = nombreCorto;
        this.combinaCon = combinaCon;
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



 


