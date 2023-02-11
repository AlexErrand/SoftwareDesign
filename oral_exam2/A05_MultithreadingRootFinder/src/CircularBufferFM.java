/**
 * This class operates as the input buffer for the MultithreadingRootFinder.
 * "FM" is referring to "From Master"
 *
 * */

public class CircularBufferFM implements Buffer {

   public static TripletGenerator[] buffer = new TripletGenerator[30];

   private int occupiedCells = 0;
   private int writeIndex = 0;
   private int readIndex = 0;

   /**
    * returns the length of a buffer.
    * @return the length of the buffer, buffer.length.
    *
    * */
   @Override
   public int length() {
      return buffer.length;
   }



   @Override
   public void blockingPutStr(String output) throws InterruptedException {
      //implementation not needed as not used by this buffer
   }

   @Override
   public String blockingGetStr() throws InterruptedException {
      return null;
   }

   // place value into buffer
   @Override
   public synchronized void blockingPut(TripletGenerator value)
      throws InterruptedException {               
   
      // wait until buffer has space available, then write value; 
      // while no empty locations, place thread in blocked state  
      while (occupiedCells == buffer.length) {
         //System.out.printf("Buffer is full, Master Waits BlockingPut%n");
         wait(); // wait until a buffer cell is free              
      }

      buffer[readIndex] = value; // set new buffer value

      // update circular write index
      writeIndex = (writeIndex + 1) % buffer.length;

      ++occupiedCells; // consume +1 cell and write new values
      System.out.println(("Master writes new a,b,c values:" + value.getA() + "," + value.getB() + "," + value.getC()));
      notifyAll();
   }
    

   @Override //get value from buffer
   public synchronized TripletGenerator blockingGet() throws InterruptedException {
      //while there are no occupied cells in buffer
      while (occupiedCells == 0) {
         //System.out.printf("Buffer is empty, Slave waits.BlockingGet%n");
         wait();
      } 

      TripletGenerator readValue = buffer[readIndex]; // read and update value from buffer


      readIndex = (readIndex + 1) % buffer.length;

      --occupiedCells;

      notifyAll();

      return readValue;
   }

}

