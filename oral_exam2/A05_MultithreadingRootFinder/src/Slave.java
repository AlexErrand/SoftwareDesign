import java.util.*;

import static java.lang.Math.abs;

/**
 * This class operates as the generator of values for the
 * input buffer for the MultithreadingRootFinder.
 * "FS" is referring to "From Master"
 */

public class Slave implements Runnable {
    //make a new list in slave, if bufferFS is full, copy onto this list. if FM is empty, and FS is empty, push to output.
    public static boolean stats = true;
    private final CircularBufferFM bufferFM;
    private final CircularBufferFS bufferFS;
    public static HashMap<String, Integer> threadStats = new HashMap<String, Integer>();


    /**
     * The multi argument constructor for Master takes in the following parameters:
     *
     * @param bufferFM used to send information between the Master and Slave
     * @param bufferFS used to send information between the Master and Slave
     * The constructor also initializes the values inside of the Hashmap threadStats.
     */
    public Slave(CircularBufferFM bufferFM, CircularBufferFS bufferFS) {//this implements runnable, starting a new thread
        this.bufferFM = bufferFM;
        this.bufferFS = bufferFS;
        for (int i = 1; i < 11; i++) {
            threadStats.put(String.valueOf(i), 0);
        }
    }


    /**
     * the rootFinder method takes in the following:
     *
     * @param tripletGenerator to return a String of roots to be sent back to the master
     *                         TripletGenerator comes with three integers, and
     * @return
     */
    public String rootFinder(TripletGenerator tripletGenerator) {

        double underRoot = Math.pow(tripletGenerator.getRootB(),2.0) - 4* tripletGenerator.getA()* tripletGenerator.getC();
        double root = Math.sqrt(Math.abs(underRoot));
        System.out.println(underRoot);
        System.out.println(root);
        //quadratic formula: (-b±√(b²-4ac))/(2a)
        //constraints: a != 0
        //root logic will go here
        //Equation formatting inspired by GeeksForGeeks
        if (tripletGenerator.getA() == 0) {
            return "Not a valid equation, A cannot be zero";
        }
        if (underRoot == 0) {
            System.out.println("equal to zero");
            tripletGenerator.setRoots(-(double) tripletGenerator.getB() / (2 * tripletGenerator.getA()), -(double) tripletGenerator.getB() / (2 * tripletGenerator.getA()));
        }

        if (underRoot > 0) {
            System.out.println("Greater than determinant");
            tripletGenerator.setRoots((-tripletGenerator.getB() + root) / (2 * tripletGenerator.getRootA()) ,
                     (-tripletGenerator.getB() - root) / (2 * tripletGenerator.getA()));
        }
        if (underRoot < 0) {
            System.out.println("Less than determinant");
            tripletGenerator.setRoots(-(double)tripletGenerator.getB() / ( 2 * tripletGenerator.getA()) ,-(double)tripletGenerator.getB() / ( 2 * tripletGenerator.getA() ) + root);

        }

        String root1 = String.valueOf(tripletGenerator.getRootA());
        StringBuilder root2 = new StringBuilder(String.valueOf(tripletGenerator.getRootB()));


        return "Root 1: " + tripletGenerator.getRootA() + " Root2: " + tripletGenerator.getRootB();
    }
    //allows operation with multiple threads

    /**
     * creates the Strings and puts them into the "From Slave" Circular Buffer, while also attempting to
     * receive a TripletGenerator of roots to output from the "From Master" Circular Buffer.
     * Prints out the thread used for the calculation of the roots and the number of times it has been used so far
     *
     * @throws InterruptedException in case of a runtime exception.
     *
     */
    @Override
    public void run() { //call rootfinder for each TripleGen, while true, reading in frombuffer
        for (int i = 0; i < bufferFS.length(); i++) {
            try {

                if (stats) {
                    for (Map.Entry<String, Integer> p : threadStats.entrySet()) {
                        if (p.getKey().equals((Thread.currentThread().getName()).substring(14))) {
                            p.setValue(p.getValue() + 1);
                            System.out.println("Updated Thread #:" + p.getKey() + ",Instance of Thread: " + p.getValue());
                            threadStats.put(p.getKey(),p.getValue());
                            break;
                        }
                    }
                }


                TripletGenerator toBeConverted = bufferFM.blockingGet();
                //System.out.println("Root from: " + tripletGenerators[i].getA() + "," + tripletGenerators[i].getB() + "," + tripletGenerators[i].getC() + ":" + rootFinder(tripletGenerators[i]));
                String output = rootFinder(toBeConverted);
                bufferFS.blockingPutStr(output);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}





