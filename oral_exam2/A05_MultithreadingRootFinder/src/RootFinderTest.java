// Fig. 23.19: CircularBufferTest.java
// Producer and Consumer threads correctly manipulating a circular buffer.

import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * creates the thread pool and the buffers that will be used with the threads,
 * then followed by the two different specifications of the problem:
 * Generate and Solve 30 Random sets of coefficients and
 * Solve 3000 sets of coefficients with Stats
 */
public class RootFinderTest {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();

        CircularBufferFM bufferFM = new CircularBufferFM();
        CircularBufferFS bufferFS = new CircularBufferFS();//new instances of the buffers


        Scanner OS = new Scanner(System.in);
        while (true) {

            System.out.println("Welcome to the RootFinder!");
            System.out.println("Select a choice below:");
            System.out.println("A. Generate and Solve 30 Random sets of coefficients");
            System.out.println("B. Solve 3000 sets of coefficients with Stats");

            String command = OS.nextLine();

            switch (command) {
                case "A" -> { //case A: 30 polynomials

                    Slave.stats = false;//set to false for no mention of threads
                    executorService.execute(new Master(bufferFM, bufferFS, 30));

                    for (int i = 0; i < 10; i++) {
                        executorService.execute(new Slave(bufferFM, bufferFS));
                    }

                    executorService.shutdown();
                    Slave.threadStats.forEach((key, value) -> System.out.println(key + " " + value));
                    executorService.awaitTermination(5, TimeUnit.SECONDS);
                    System.exit(0);

                }
                case "B" -> {//case : 3000 polynomials + updating # of thread use

                    executorService.execute(new Master(bufferFM, bufferFS, 2997));
                    for (int i = 1; i < 10; i++) {
                        executorService.execute(new Slave(bufferFM, bufferFS));
                    }
                    executorService.shutdown();
                    executorService.awaitTermination(5, TimeUnit.SECONDS);
                    Slave.threadStats.forEach((key, value) -> System.out.println(key + " " + value));
                    System.exit(0);
                }
            }
        }
    }
}
