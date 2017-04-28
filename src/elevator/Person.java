/**
 * @author Carlos Alonzo 00011329
 * Elevator Simulator Project 
 * Person Class. 
 * Code generated with the help of sample code provided by Professor RGouveia.
 */

package elevator;

/**
 *
 * @author Carlos
 */
public class Person 
{
    private int ID;
    private static int RiderID=000;
    private int origin;
    private int dest;
     
              
    
    Person(int origin, int destination)
    {
        
        this.ID = newRiderID(); 
        this.origin=origin;
        this.dest=destination;
    }
    
    Person(Person p1)
    {
        this.ID=p1.getRiderID();
        this.origin=p1.getOrigin();
        this.dest=p1.getDest();    
    
    }

        
    public boolean equals(Person p1) 
    {
        return (this.ID==p1.ID && this.dest == p1.getDest()); 
    }

    public int getOrigin() {
        return origin;
    }
    
    public int getDest() {
        return dest;
    }
    
    /**
     *
     * @return
     */
    public static int newRiderID()
    {
        return RiderID++;
    }
    
    /**
     *
     * @return
     */
    public int getRiderID()
    {
        return this.ID;
    }
    
    
    
}
