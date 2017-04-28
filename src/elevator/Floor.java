/**
 * @author Carlos Alonzo 00011329
 * Elevator Simulator Project 
 * Floor Class. 
 * Code generated with the help of sample code provided by Professor RGouveia.
 */
package elevator;

import java.util.*;

/**
 *
 * @author Carlos Alonzo 00011329
 */
public class Floor {
    
    private FloorButton FButton;
    private Queue <Person>UpQueue;
    private Queue <Person>DownQueue;
    private  int FloorNum;
    
    public Floor(int fnum)
    {
        this.FButton = new FloorButton();
        this.UpQueue= new LinkedList();
        this.DownQueue= new LinkedList();
        this.FloorNum=fnum;    
    }

    public int getFloorNum() {
        return FloorNum;
    }
    
    public Person getPersonOnUpQ()
    {
        return UpQueue.peek();
    
    }
    public Person getPersonOnDownQ()
    {
        return DownQueue.peek();
    
    }
    public Person getNextOnUpQ()
    {
        return UpQueue.remove();
    
    }
    public Person getNextOnDownQ()
    {
        return DownQueue.remove();
    
    }
    
    public boolean isUpQEmpty()
    {
        return UpQueue.isEmpty();
    
    }
    public boolean isDownQEmpty()
    {
        return DownQueue.isEmpty();
    
    }
    
    
    public void lightUp()
    {
        FButton.requestUp();
        System.out.println("Person on floor: " + this.FloorNum + " pushed Up Button for elevator");
    }
    
    public void lightDown()
    {
        FButton.requestDown();    
        System.out.println("Person on floor: " + this.FloorNum + " pushed Down Button for elevator");
    }        
    
    public boolean isOn()
    {
        return FButton.isOn();
    }
    
    public boolean isWantDown() 
    {
        return FButton.isWantDown();
    }

    public boolean isWantGoUp() 
    {
        return FButton.isWantGoUp();
    }
    
    
    
    public boolean onUpQ()
    {
        return !UpQueue.isEmpty();    
    }
    
    public boolean onDownQ()
    {
        return !DownQueue.isEmpty(); 
    }
    public int goingUp()
    {
        return UpQueue.size() ;    
    }
    
    public int goingDown()
    {
        return DownQueue.size(); 
    }
    
    public boolean queuePerson(Person p)
    {
        boolean success=false;
        
        if(FButton.isWantGoUp())
        {   
            success=this.UpQueue.add(p);                           
        }
        
        else if(FButton.isWantDown())
        {  
            success=this.DownQueue.add(p);   
        }
        
        else
            success= false;
        
        return success;
        
    }
    
    public boolean dequeuePerson(Person p)
    {
        boolean success=false;
        
        if(UpQueue.contains(p))
        { 
            success=this.UpQueue.remove(p);
            if(UpQueue.isEmpty())
                FButton.resetUp();
        }

        else if(DownQueue.contains(p))
        { 
            success=this.DownQueue.remove(p);
            if(DownQueue.isEmpty())
                FButton.resetDown();
        }
        
        else
            success=false;
        
        return success;
    }    
    
    public void clearFloor()
    {
        this.FButton.resetUp();
        this.FButton.resetDown();
        this.UpQueue.clear();
        this.DownQueue.clear();
    }
    
}
