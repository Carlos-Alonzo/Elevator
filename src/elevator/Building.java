/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elevator;
/**
 * Building class (has Floor array and elevator and Elevator controller [simplified))
 * public boolean moveElevator(Direction), addPerson (by handing to Floor Array)
 * @author Carlos
 */
public class Building 
{
    private Elevator Elevador;
    private Controller Joystick;
    
    
    public Building(int capacity, int numoffloors) 
    {
        Elevador = new Elevator( capacity , numoffloors );
        Joystick = new Controller();        
    }
    
    public void smartController()
    {
        Joystick.whatNext(Elevador);
        
    }
    public int getNextPickupFlr(int cfloor, String direction)
    {
        return Elevador.nextPickupFlr(cfloor, direction);
    }
            
    public boolean moveElevator(String direction)
    {
        boolean wentsomewhere=false;
        
        switch(direction)
        {
            case "up":
                    wentsomewhere = Elevador.goUpOne();
                    break;
                    
            case "down":
                    wentsomewhere = Elevador.goDownOne();
                    break;
                    
            case "idle":
                    Elevador.setOnCurrentFloor(1);
                    wentsomewhere = true;
                    break;
              
            default:
                    wentsomewhere = false;
                    break;
        }
        return wentsomewhere;
    
    }
    
    public boolean addPerson (Person rider, int floor) 
    {
        return Elevador.addToFloorQueue(rider, floor);
    }
    
    public boolean pickUp()
    {
        return Elevador.pickUp();
    
    }
    public boolean smartPickUp(String direction)
    {
        return Elevador.smartPickUp(direction);
    
    }
    public boolean dropOff()
    {
        return Elevador.dropOff();
    
    }
    public boolean pickupsPending()
    {
        return Elevador.thereArePickups();
    }
    public boolean dropsPending()
    {
        return Elevador.thereAreDropOffs();
    }
    public boolean isEmpty()
    {
        return Elevador.isEmpty();
    }
}
