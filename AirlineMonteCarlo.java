
/**
 * AirlineMonteCarlo takes inputs of tickets sold, amount of available seats, and no show probability. It then runs a certain number
 * of simulations and calculates average seats filled, number of times overbooked, and the percentage of times it is overbooked.
 *
 * @author Isabel Hsieh with help from Jessica
 * @version 2019-10-24
 */
public class AirlineMonteCarlo
{
    private int ticketsSold;
    private int seats;
    private double noShowProbability;
    private int overbooked;
    private double averageSeatsFilled;
    private double percentage;
    private final int NUM_SIMULATIONS = 100000;
    
    /**
     * Constructor for objects of class AirlineMonteCarlo
     */
    public AirlineMonteCarlo(int t, int s, double p)
    {
        this.ticketsSold = t;
        this.seats = s;
        this.noShowProbability = p;
    }
    
    /**
     * Simulates one flight
     * @param numShow number of people who show up
     * @return number of people who show up
     */
    public int simulateOneFlight()
    {
        int numShow = 0; //number of people who show up
        for (int i = 1; i <= ticketsSold; i++)
        {
            if (Math.random() >= noShowProbability)
            {
                numShow = numShow + 1;
            }
        }
        return numShow;
    }
    
    /**
     * Runs the one flight simulation for the number of simulations
     * Records number of times overbooked out of all simulations
     * Calculates average seats filled
     * Calculates percentage of times flight is overbooked
     * @param runningTotal total seats filled out of all simulations
     */
    public void runSimulation()
    {
        int runningTotal = 0; //total seats filled out of all simulations
        overbooked = 0; //number of times out of all simulations the plane was overbooked
        for (int k = 1; k <= NUM_SIMULATIONS; k++)
        {
            int numShow = simulateOneFlight();
            runningTotal = runningTotal + numShow;
            
            if (numShow > seats)
            {
                overbooked = overbooked + 1;
            }
        }
        averageSeatsFilled = (double)runningTotal / NUM_SIMULATIONS;
        percentage = (double)overbooked / NUM_SIMULATIONS * 100;
    }
    
    /**
     * Reports results of all simulations
     */
    public void reportResults()
    {
        System.out.print("Simulation: " + ticketsSold + " tickets sold for " + seats + " seats; no-show probability = " + noShowProbability);
        System.out.print("\n Based on " + NUM_SIMULATIONS + " simulations:");
        System.out.print("\n Average seats filled: " + averageSeatsFilled);
        System.out.print("\n Number of times overbooked: " + overbooked + " (" + percentage + " percent) ");
    }
    
    /**
     * Runs the code based on the three inputs
     */
    public static void main (String[] args)
    {
        AirlineMonteCarlo mySim = new AirlineMonteCarlo (140, 136, 0.04);
        mySim.runSimulation();
        mySim.reportResults();
        System.out.print("\n\n ");
        AirlineMonteCarlo Sim2 = new AirlineMonteCarlo (218, 210, 0.06);
        Sim2.runSimulation();
        Sim2.reportResults();
    }

}
