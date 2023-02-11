/**
 * class TripletGenerator is used to help generate tuples, as they do not exist in Java and the only alternative
 * is using an array of length three.
 */
public class TripletGenerator {
    private int a;
    private int b;
    private int c;
    private double rootA;
    private double rootB;
    private double rootA2;
    private double rootB2;

    /**
     * @param a , coefficient 1
     * @param b , coefficient 2
     * @param c , coefficient 3
     *
     */
    public TripletGenerator(int a, int b, int c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }


    /**
     * Sets the roots once created in rootFinder
     * @param rootA the first root of a specific polynomial
     * @param rootB the second root of a specific polynomial
     */
    public void setRoots(double rootA, double rootB) {
        this.rootA = rootA;
        this.rootB = rootB;
    }

    /**
     * Sets the roots for the edge case of roots using a determinant less than zero.
     * @param rootA the first root of a specific polynomial
     * @param rootB the second root of a specific polynomial
     * @param rootA2 the second part of the first root
     * @param rootB2 the second part of the second root
     */
    public void setRootsLessThanZero(double rootA, double rootB, double rootA2, double rootB2){
        this.rootA = rootA;
        this.rootB = rootB;
        this.rootA2 = rootA2;
        this.rootB2 = rootB2;


    }

    public double getRootA() {
        return rootA;
    }


    public double getRootB() {
        return rootB;
    }

    public double getRootA2(){return rootA2; }

    public double getRootB2(){return rootB2;}

    public int getA() {
        return a;
    }

    public int getB() {
        return b;
    }

    public int getC() {
        return c;
    }






}
