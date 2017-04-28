/**
 * @author Carlos Alonzo 00011329
 * Elevator Simulator Project 
 * Elevator Class.
 * Code generated with the help of sample code provided by Professor RGouveia.
 */
package elevator;

import static elevator.Configuration.*;
import java.util.*;

/**
 *
 * @author Carlos
 */
public class Elevator 
{
    private Person PeopleAboard[];
    private KeyPadButton Buttons[];
    private Floor Level[];
    //private boolean full;
    private int onCurrentFloor;
    private int capacity;
    private int onboard;
    
    /**
     *
     * @param capacity
     * @param floors
     */
    public Elevator(int capacity, int floors ) 
    {
        this.capacity=capacity;
        PeopleAboard= new Person[capacity];
        Buttons = new KeyPadButton[floors];
        Level = new Floor[floors];
        //full=false;
        onCurrentFloor=1;
        onboard=0;
        
        for (int i=0;i<floors;i++)
        {
            Buttons[i]=new KeyPadButton(i+1);
            Level[i] = new Floor(i+1);
        }       
        System.out.println("A new Elevator has been built "+
                           "with capacity/floors:" +capacity+"/"+ floors);
    }
    
    public boolean addToFloorQueue(Person p1, int floor)
    {
        boolean addedtofq=false;
        
        if(floor>=1 && floor <= Level.length && p1.getDest()!= floor)
        {
            if(floor < p1.getDest())
            {
                Level[floor-1].lightUp();
                addedtofq=Level[floor-1].queuePerson(p1);
                
            }
            else
            {
                Level[floor-1].lightDown();
                addedtofq=Level[floor-1].queuePerson(p1);
            }
            
        }
        else
        {   
            System.out.println("Cannot add person:"+p1.getRiderID()+" to floor queue"+
                                " because person is going to same floor ori/dest: "+ 
                                  p1.getOrigin()+"/"+p1.getDest()  );
            addedtofq=false;
        }
            
            return addedtofq;
    
    }
    

    /**
     *
     * @param rider
     * @param floor
     * @return
     */
    
    public boolean pressDestButton(int button)
    {
        boolean success;
        
        if (button >= 1 && button <= Buttons.length)
        {
            Buttons[button-1].setLit();
            success=true;
        }
        else
        {
            System.out.println("Not a valid Keypad button");
            success=false;
        }
        return success;
    }
    
    public boolean pickUp()
    {
        boolean pickedup = false;
        int curfloor = getCurrentFloor();
        Floor f = new Floor(curfloor);
        f=Level[curfloor-1];
        System.out.println("On Floor: "+ curfloor);
        System.out.println("Before pickup, onboard: " + onboard);
        
        while(f.isOn() && !isFull())
        {    
            
            if(f.onUpQ())
            { 
                
                PeopleAboard[onboard++]=new Person(f.getPersonOnUpQ());
                pressDestButton(f.getPersonOnUpQ().getDest());
                f.dequeuePerson(f.getPersonOnUpQ()); 
                pickedup=true;
                System.out.println("Pick up made, onboard: "+ onboard);
                
            }
            else
            {
                PeopleAboard[onboard++]=new Person(f.getPersonOnDownQ());
                pressDestButton(f.getPersonOnDownQ().getDest());
                f.dequeuePerson(f.getPersonOnDownQ());
                pickedup=true;
                System.out.println("Pick up made, onboard: "+ onboard);
                
            }
            
        }
        
        
        if(isFull())
        {
            pickedup=false;
            System.out.println("Elevator is full: " + getPeopleAboard()+ ", cannot pickup at this time.");
        }
        
        else
           pickedup=false; 
        
        return pickedup;
    
    }
    public boolean pickUpGoingUp()
    {
        boolean pickedup = false;
        int curfloor = getCurrentFloor();
        Floor f;// = new Floor(curfloor);
        f=Level[curfloor-1];
        System.out.println("PICKUP GOING UP - On Floor: "+ curfloor);
        System.out.println("Before pickup, onboard: " + onboard);
        //System.out.println("Before pickup, onboard: " + getPeopleAboard());
        
        while(!f.isUpQEmpty() && !isFull())
        {    
                
                int emptyslot=0;
                while(PeopleAboard[emptyslot]!=null)
                    emptyslot++;
                Person NewRider = new Person(f.getPersonOnUpQ());
                PeopleAboard[emptyslot]=NewRider;
                //if(PeopleAboard[onboard]!=null)
                onboard++;
                System.out.println("Pick up made, onboard: "+ onboard);
                System.out.println("Pick up made, onboard: "+ getPeopleAboard());
                pressDestButton(f.getPersonOnUpQ().getDest());
                f.dequeuePerson(f.getPersonOnUpQ()); 
                pickedup=true;
                                
        }
         
        return pickedup;
    
    }
    public boolean pickUpGoingDown()
    {
        boolean pickedup = false;
        int curfloor = getCurrentFloor();
        Floor f;// = new Floor(curfloor);
        f=Level[curfloor-1];
        System.out.println("PICKUP GOING DOWN - On Floor: "+ curfloor);
        System.out.println("Before pickup, onboard: " + onboard);
        //System.out.println("Before pickup, onboard: " + getPeopleAboard());
        
        while(!f.isDownQEmpty() && !isFull())
        {    
                int emptyslot=0;
                while(PeopleAboard[emptyslot]!=null)
                    emptyslot++;
                Person NewRider = new Person(f.getPersonOnDownQ());
                PeopleAboard[emptyslot]=NewRider;
                //if(PeopleAboard[onboard]!=null)
                    onboard++;
                System.out.println("Pick up made, onboard: "+ onboard);
                System.out.println("Pick up made, onboard: "+ getPeopleAboard());
                pressDestButton(f.getPersonOnDownQ().getDest());
                f.dequeuePerson(f.getPersonOnDownQ()); 
                pickedup=true;
                               
        }
         
        return pickedup;
    
    }
    public boolean smartPickUp(String direction)
    {
        System.out.println("Direction: " + direction);
        boolean pickedup = false;
        int curfloor = getCurrentFloor();
        Floor f = new Floor(curfloor);
        f=Level[curfloor-1];
        System.out.println("On Floor: "+ curfloor);
        System.out.println("Before pickup, onboard: " + onboard);
        System.out.println("Number of people on floor going up: " + f.goingUp());
        System.out.println("Number of people on floor going down: " + f.goingDown());
        
        if(f.onUpQ() && direction.equals(UP))
        { 
            while(!f.isUpQEmpty() && !isFull())
            {
                Person rider = f.getPersonOnUpQ();
                PeopleAboard[onboard++]= rider;
                pressDestButton(rider.getDest());
                f.dequeuePerson(rider); 
                pickedup=true;
                System.out.println("Pick up made, onboard: "+ onboard);
            }
        }
        
        else if(f.onDownQ()&& direction.equals(DOWN))
        {
            while(!f.isDownQEmpty() && !isFull())
            {
                Person rider = f.getPersonOnDownQ();
                PeopleAboard[onboard++]=rider;
                pressDestButton(rider.getDest());
                f.dequeuePerson(rider);
                pickedup=true;
                System.out.println("Pick up made, onboard: "+ onboard);
            }
        }
        
        else if(isFull())
        {
            pickedup=false;
            System.out.println("Elevator is full: " + getPeopleAboard()+ ", cannot pickup at this time.");
        }
        
        else if (!f.isOn())
        {
            System.out.println("No one for pick up here");
            pickedup=false;  
        }
        else
        {
            System.out.println("direction: "+ direction);
            pickedup=false;
        }
               
        return pickedup;
    
    }
    public boolean dropOff()
    {
        boolean droppedoff = false;
        int curflrindex = getCurrentFloor()-1;
        int curfloor = getCurrentFloor();
        
        if(Buttons[curflrindex].isLit())
        {
            System.out.println("On board: " + onboard);
            System.out.println("On board: " + this.getPeopleAboard());
            
            for(int i=0; i < capacity;i++)
            {
               if(PeopleAboard[i]!=null && PeopleAboard[i].getDest()==curfloor) 
               {
                   System.out.println("Dropping off rider: " +
                           PeopleAboard[i].getRiderID() +
                           " on floor: " + curfloor);
                   PeopleAboard[i]=null;
                   onboard--;
                   
                   droppedoff=true;                 
                   
               }
               
            }
            System.out.println("On board: " + onboard);
            System.out.println("On board: " + this.getPeopleAboard());
            Buttons[curflrindex].reset();
        }
        
        else
            droppedoff=false;
        
        return droppedoff;
    }
    
    public boolean pickUp( Person rider, int floor)
    {
        boolean pickedup;
        System.out.println("Current People on board: " + getPeopleAboard());
        
        if (!isFull() && getCurrentFloor()==floor)
        {
            
            PeopleAboard[onboard++]=new Person(rider);
            System.out.println("Current People on board: " + getPeopleAboard());
            Level[floor].dequeuePerson(rider);     
            pickedup=true;
        }
        
        else if(getCurrentFloor()!=floor)
        {
            pickedup=false;    
            System.out.println("Not on same floor to make pickup");
        }
        
        else
        {
            pickedup=false;
            System.out.println("Cannot pick up because elevator is full");
        }
        
        
        return pickedup;
    } 
    
    /**
     *
     * @param rider
     * @param floor
     * @return
     */
    public boolean dropOff(Person rider)
    {
        
        boolean droppedoff=false;
        
        if(getCurrentFloor()==rider.getDest())
        {   
            for(int i=0; i < capacity;i++)
            {
               if(PeopleAboard[i]!=null && PeopleAboard[i].equals(rider)) 
               {
                   PeopleAboard[i]=null;
                   System.out.println("On board: " + onboard);
                   onboard--;
                   droppedoff=true;
                   System.out.println("Dropoff complete");
                   System.out.println("On board: " + onboard);
               }

            }
        }
        
        else
        {
            System.out.println("Not on right floor to drop off person "+
                                "going to floor" + rider.getDest());
            droppedoff=false;
        }
        
        return droppedoff;
            
    }
    
    /**
     *
     * @return
     */
    public int getCurrentFloor()
    {
        return onCurrentFloor;
    
    }

    public void setOnCurrentFloor(int onCurrentFloor) {
        this.onCurrentFloor = onCurrentFloor;
    }
    
    public boolean goUpOne()
    {
        boolean goup = false;
        System.out.println("on Level:" + onCurrentFloor);
        
        if(onCurrentFloor==Level.length)
            goup = false;
        
        else
        {
            setOnCurrentFloor(onCurrentFloor+1);
            goup = true;
        }
        System.out.println("Went Up a level to floor: "+ getCurrentFloor());
        return goup;
    }        
    
    
    public boolean goDownOne()
    {
        boolean godown = false;
        System.out.println("on Level:" + onCurrentFloor);
        
        if(onCurrentFloor==1)
            godown = false;
        
        else
        {
            setOnCurrentFloor(onCurrentFloor-1);
            godown = true;
        }
        System.out.println("Going Down One Level to Floor: "+ onCurrentFloor);
        return godown;
    
    }
    /**
     *
     * @return
     */
    
    public boolean isFull()
    { return onboard == this.capacity;}
            
    /**
     *
     * @return
     */
    public boolean isEmpty()
    {   return (onboard==0);}
    
    /**
     *
     * @return
     */
    //public int getPeopleAboard()
    //{   return this.onboard;}
    public int getPeopleAboard()
    {  
        int peepcount=0;
        for(Person p : PeopleAboard)
            if(p!=null) peepcount++;
        
        return peepcount;
    }
    
    public int getLastFloor()
    {
        return Level.length;
    }
    
    public boolean thereArePickups()
    {
        boolean therearerequestspending=false;
        
        for(Floor f : Level)
        {
            if(f.isOn())
            {
                therearerequestspending=true;
                break;
            }
        }
        return therearerequestspending;
    
    }
    public int nextPickupFlr(int cfloor, String direction)
    {
        int nextpickupflr=0;
       
        if(direction.equals(UP))
        {
            for(int i=cfloor;i <= Level.length; i++)
            {
                if(Level[i-1].isWantGoUp())
                {
                    nextpickupflr = i;
                    break;
                }
            }
        
        }
        else if(direction.equals(DOWN))
        {
            for(int i=cfloor; i > 0; i--)
            {
                if(Level[i-1].isWantDown())
                {
                    nextpickupflr = i;
                    break;
                }
            }
        
        }
        
        else
        {
            for(Floor F : Level)
            {
                if(F.isOn())
                {
                    nextpickupflr = F.getFloorNum();
                    break;
                }
            }        
        }
        
        System.out.println("On current Floor "+ cfloor +", Next pickup floor is:" + nextpickupflr);
        return nextpickupflr;
    
    }
    public boolean thereAreDropOffs()
    {
        boolean therearedropoffs=false;
        
        for(KeyPadButton kpb : Buttons)
        {
            if(kpb.isLit())
            {
                therearedropoffs=true;
                break;
            }
        }
        return therearedropoffs;
    
    }
    public int getNextDropOffFlrUp()
    {
        int nextflruptopickup=0;
        
        for(int i=this.getCurrentFloor();i <= Buttons.length;i++)
        {
            if(Buttons[i-1].isLit())
            {
                nextflruptopickup=i;
                break;
            }
        }
        return nextflruptopickup;
    
    }
    public int getNextDropOffFlrDown()
    {
        int nextflrdowntopickup=0;
        
        for(int i=this.getCurrentFloor();i > 0;i--)
        {
            if(Buttons[i-1].isLit())
            {
                nextflrdowntopickup=i;
                break;
            }
        }
        return nextflrdowntopickup;
    
    }
    /**
     * @param args the command line arguments
     */
    
    
    
}
