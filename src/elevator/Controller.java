/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elevator;
import static elevator.Configuration.*;
import java.util.*;

/**
 * One method returns which direction to go in, parameters of current floor, 
 * current direction and building size. continues in it's current direction until it can't 
 * - go up to the top, down to the bottom.
 * @author Carlos
 */
public class Controller 
{
    private LinkedList<String> Direction;

    public Controller() 
    {
        Direction = new LinkedList<>();
        
    }
    
    /*public String whichWay() //direction
    {
        int currfloor=elev.getCurrentFloor();
        String direction;
        
        if(currfloor == elev.getLastFloor())
        {
            direction="down";
            if(!Direction.isEmpty())
                Direction.removeLast();
            
            Direction.add(direction);
        }
        else if (currfloor == 1)
        {
            direction="up";
            if(!Direction.isEmpty())
                Direction.removeLast();
            
            Direction.add(direction);
        }
        
        else
        {   
            if(Direction.peekLast().equals(UP))
               direction="up";
            
            else
                direction="down";        
        }
        
        return direction;    
    }*/

    public void whatNext(Elevator elev) //direction
    {
        //elev.dropOff();
        String lastway = (!Direction.isEmpty())?Direction.peekLast():IDLE;
        
        int cfloor = elev.getCurrentFloor(),
            lastflr = elev.getLastFloor(),
            NextDropOffFlrUp = elev.getNextDropOffFlrUp(), 
            NextDropOffFlrDown = elev.getNextDropOffFlrDown(),
            nextpickupflr = elev.nextPickupFlr(cfloor, lastway);
        
        
        //First floor
        if(cfloor == 1)
        {   
           if(NextDropOffFlrDown==1)
               elev.dropOff();
                   
           if(nextpickupflr == 1 && !elev.isFull())
               elev.pickUpGoingUp();
           
           else if(nextpickupflr > 1 || NextDropOffFlrUp > 1 )
           {
               Direction.add(UP);
               elev.goUpOne();           
           }       
           
           else
           {
               System.out.println("are there pickups?: " + elev.thereArePickups());
               System.out.println("are there dropoffs?: " + elev.thereAreDropOffs());
           }
        }
        
        else //if (cfloor > 1 && cfloor <= lastflr)
        {
            //make a dropoff if current floor is the desired one regardless of direction
            if(NextDropOffFlrDown == cfloor || NextDropOffFlrUp == cfloor)
               elev.dropOff();
            //pick up the ones going up if we were going up on current floor
            if(nextpickupflr == cfloor && lastway.equals(UP) && !elev.isFull())
               elev.pickUpGoingUp();
            //pickup the ones going down if we were going down on current floor
            else if(nextpickupflr == cfloor && lastway.equals(DOWN) && !elev.isFull())
               elev.pickUpGoingDown();
            
            //keep going up if there are drop offs/pickups to be made going up
            else if((NextDropOffFlrUp > cfloor || nextpickupflr > cfloor) && lastway.equals(UP) )
           {
               //Direction.add(UP);
               elev.goUpOne();           
           }
            //keep going down if was going the same way and there are more dropoffs/pickups going down
            else if((NextDropOffFlrDown < cfloor || nextpickupflr < cfloor )&& lastway.equals(DOWN)  )
           {
               //Direction.add(DOWN);
               elev.goDownOne();           
           }
            //no more dropoffs going up, come back down, as long as there are dropoffs.
            else if(NextDropOffFlrUp < cfloor && lastway.equals(UP) && NextDropOffFlrUp!=0)
           {
               Direction.add(DOWN);
               elev.goDownOne();           
           }
            //
            else if(NextDropOffFlrUp > cfloor && lastway.equals(DOWN)  )
           {
               Direction.add(UP);
               elev.goUpOne();           
           }
           
            else if (nextpickupflr == 0 && NextDropOffFlrUp == 0 && NextDropOffFlrDown == 0)
            {
               System.out.println("are there pickups?: " + elev.thereArePickups());
               System.out.println("are there dropoffs?: " + elev.thereAreDropOffs());
               System.out.println("Going to first floor to wait for requests");
               elev.goDownOne();
                
            }
        
        }
        
            
        
    }
    
            
}
