# Elevator
Objective:  Implement a complex project emphasizing object design and development while using a variety of data structures.
Write a program to simulate elevators in a building.  The goal is to be able to test various elevator configurations for a building, gathering statistics on average wait time and maximum wait time for people to arrive at their destination.
The program should allow for multiple elevators (up to four), specialized elevators such as an express elevator for top floors, variable building sizes (up to 10 floors) and a variety of methods for generating simulated people such as random generation, file controlled generation, or user interaction.

The user interface should allow for selecting the configuration of the simulation run, display the on-going results of the run and display final statistics.
It does not have to be a graphic display.
The Elevator can “hold” people in an array, while the Floor needs to hold people in a queue to be sure the first person waiting is the first to get on the elevator if it is going in the right direction.
The elevators should run on their own thread. The simulation should allow for trying different controllers for the elevators.
The program should be written in good object oriented style, with industry standard coding techniques. Javadoc standard comments should allow for automatic documentation.
Tests are part of the programming process and will be submitted as well as the source code. 
As a larger, more complex lab, the goal is to have several classes interact, use several different data structures and develop industry standard techniques.

