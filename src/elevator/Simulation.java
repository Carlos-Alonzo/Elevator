/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elevator;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;

/**
 * Master Simulation class
 * Set configuration, create stuff (& communication) run simulation (the main loop)
 * @author Carlos
 */
public class Simulation 
{

    


    public static void main(String[] args) throws FileNotFoundException, IOException 
    {
        
       Building BroadSt = new Building(Configuration.MAX_CAPACITY,Configuration.MAX_FLOORS);
       LinkedList<Person> Riders = new LinkedList<>(Configuration.readFile());
       
       for(Person p : Riders)
       { BroadSt.addPerson(p, p.getOrigin());}
       
        while(BroadSt.pickupsPending() || BroadSt.dropsPending())
            BroadSt.smartController();
        
        /*
        
        ((Floor)Floors.elementAt(0)).queuePerson(rider1);
        ((Floor)Floors.elementAt(1)).queuePerson(rider2);
        
        myElevator.getCurrentFloor();
        myElevator.pickUp(rider1, rider1.getOrigin());
        myElevator.pickUp(rider2,rider2.getOrigin());
        myElevator.getCurrentFloor();
        myElevator.getPeopleAboard();
        myElevator.dropOff(rider2, rider2.getDest());
        */        
        
    }    
}
