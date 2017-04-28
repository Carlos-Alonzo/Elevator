/**
 * @author Carlos Alonzo 00011329
 * This KeyPadButton is for the Keypad inside elevator
 */
package elevator;

/**
 *
 * @author Carlos Alonzo
 */
public class KeyPadButton {
    
    private int floorNum;
    private boolean lit;
    

    /**
     *
     * @param floorNum
     */
    public KeyPadButton(int floorNum) 
    {
        this.floorNum = floorNum;
        this.lit = false;
    }

    /**
     *
     * @return
     */
    public int getFloorNum() 
    {
        return floorNum;
    }
    
    /**
     *
     * @param lit
     */
    public void setLit() 
    {
        this.lit = true;
        System.out.println("KeyPad Button: " + getFloorNum() +" has been pushed");
    }

    /**
     *
     * @return
     */
    public boolean isLit() 
    {
        return lit;
    }
    
    /**
     *
     */
    public void reset()
    {
        this.lit = false;
            
    }
    
    
}
