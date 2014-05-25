public class Command
{
    private Option commandWord;
    private String secondWord;
    private String terceraPalabra;

    /**
     * Create a command object. First and second word must be supplied, but
     * either one (or both) can be null.
     * @param firstWord The first word of the command. Null if the command
     *                  was not recognised.
     * @param secondWord The second word of the command.
     */
    public Command(Option firstWord, String secondWord, String terceraPalabra)
    {
        commandWord = firstWord;
        this.secondWord = secondWord;
        this.terceraPalabra = terceraPalabra;
    } 

    /**
     * Return the command word (the first word) of this command. If the
     * command was not understood, the result is null.
     * @return The command word.
     */
    public Option getCommandWord()
    {
        return commandWord;
    }

    /**
     * @return The second word of this command. Returns null if there was no
     * second word.
     */
    public String getSecondWord()
    {
        return secondWord;
    }
    
    /**
     * @return The second word of this command. Returns null if there was no
     * second word.
     */
    public String getTerceraPalabra()
    {
        return terceraPalabra;
    }
    
    /**
     * @return true if this command was not understood.
     */
    public boolean isUnknown()
    {
        return (commandWord == Option.UNKNOWN);
    }

    /**
     * @return true if the command has a second word.
     */
    public boolean hasSecondWord()
    {
        return (secondWord != null);
    }
}

