import java.util.*;
/**
 * This class operates as the generator of values for the
 * input buffer for the MultithreadingRootFinder.
 * "FM" is referring to "From Master"
 * */
public class Master implements Runnable {

    private final int polyCount; //used to change how many polynomials are made

    public Random rng = new Random(); //used to randomize the variables
    private final CircularBufferFM bufferFM; //buffer declaration
    private final CircularBufferFS bufferFS;
    /**
     * The multi argument constructor for Master takes in the following parameters:
     * @param bufferFM used to send information between the Master and Slave
     * @param bufferFS used to send information between the Master and Slave
     * @param polyCount used to determine how many pairs of coefficients are made
     *
     * */

    public Master(CircularBufferFM bufferFM, CircularBufferFS bufferFS, int polyCount) {
        this.bufferFM = bufferFM;
        this.bufferFS = bufferFS;
        this.polyCount = polyCount;

    }


    /**
     * creates the TripletGenerators and puts them into the "From Master" Circular Buffer, while also attempting to
     * receive a String of roots to output from the "From Slave" Circular Buffer.
     * @throws InterruptedException in case of a runtime exception.
     *
     */
    @Override
    public void run() {
        //iterate through polycount and generate new TripletGenerators
        for (int i = 0; i < polyCount; i++) {
            try {
                TripletGenerator toBeInserted = new TripletGenerator(rng.nextInt(2001)-1000, rng.nextInt(2001)-1000, rng.nextInt(2001)-1000); //generate
                ///System.out.println(toBeInserted.getA()+ "," + toBeInserted.getB()+ "," + toBeInserted.getC());
                bufferFM.blockingPut(toBeInserted);
                bufferFS.blockingGetStr();
                //System.out.println("From Slave: " + output);

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

