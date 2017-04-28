/**
 * @author Carlos Alonzo 00011329
 * Elevator Simulator Project 
 * Button Class. This is the button outside of the elevator that is pressed to request it.
 * Code generated with the help of sample code provided by Professor RGouveia.
 */

package elevator;

/**
 *
 * @author Carlos
 */
public class FloorButton 
{
    //properties
    private boolean wantGoUp;
    private boolean wantDown;
    private boolean lit;
    
    //default constructor

    /**
     *
     * @param floor
     */
    public FloorButton ()
    {
        this.wantGoUp = false;
        this.wantDown = false;
        this.lit=false;
        
    }
    
    

    public boolean isWantDown() {
        return wantDown;
    }

    public boolean isWantGoUp() {
        return wantGoUp;
    }
    /**
     *
     */
    public void requestUp()
    {
        this.wantGoUp=true;
        this.lit = true;
    }
    
    
    /**
     *
     */
    public void requestDown()
    {
        this.wantDown=true;
        this.lit = true;
    }
    
    /**
     *
     */
    public void resetUp()
    {
        this.wantGoUp = false;
        this.lit = this.wantDown;
           
        System.out.println("Floor Up Button has been reset");
    }
    public void resetDown()
    {
        this.wantDown = false;
        this.lit = this.wantGoUp;
           
        System.out.println("Floor Down Button has been reset");
    }
    
    /**
     *
     */
    public void display()
    {
        System.out.println("the status is " + isOn());
    }
    
    /**
     *
     * @return
     */
    public boolean isOn()
    {
        return this.lit;//will change using constant
    }
    
    
}

