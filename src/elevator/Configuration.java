/**
 * @author Carlos Alonzo 00011329
 * Elevator Simulator Project 
 * Configuration Interface.
 * Code made with samples provided by Prf. Gouveia
 */
package elevator;

import java.io.*;
import java.util.*;
/**
 *  Constants (UP/DOWN etc.)
 *  Settings such as MAXFLOOR
 *  Defaults
 *  Modes (how generating people, what to do with results…)
 * @author Carlos
 */
public class Configuration 
{
    public static final int MAX_FLOORS= 10;
    public static final int MAX_WEIGHT= 5000;
    public static final int MAX_CAPACITY=10;
    public static final String UP="up";
    public static final String PUGU="pugu";
    public static final String PUGD="pugd";
    public static final String IDLE="idle";
    public static final String DOWN="down";
    public static final String DELIMITER=",";
    public static final int TWO=2;
    public static final int ORG=0;
    public static final int DEST=1;
    public static final String FILEPATH="C:\\Users\\Carlos\\Dropbox\\CIS 252\\Elevator\\src\\elevator\\people.data";
    
    
    //
    //Code 
    public static LinkedList<Person> readFile() throws IOException, NullPointerException
    {
        LinkedList<Person> Riders = new LinkedList<>();
        String OneLine;
        String [] OrgandDest = new String[TWO];
        
        try
        {
            BufferedReader in = new BufferedReader(new FileReader(FILEPATH));  
            
            while(null != (OneLine = in.readLine()))
            {
                OrgandDest= OneLine.split(DELIMITER);
                Riders.add(new Person(Integer.parseInt(OrgandDest[ORG]),Integer.parseInt(OrgandDest[DEST])));           
            }
       }
    
        catch (IOException | NullPointerException e)
        {
            System.out.println("Error occurred: "+ e.getMessage());
        }
        
        return Riders;
            
    }
    
}
