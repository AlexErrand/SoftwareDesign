// Fig. 23.18: CircularBuffer.java
// Synchronizing access to a shared three-element bounded buffer.
/**
 * This class operates as the output buffer for the MultithreadingRootFinder.
 * "FM" is referring to "From Master"
 *
 * */
public class CircularBufferFS implements Buffer {
   private String[] buffer = new String[3000]; // shared buffer

   //current occupied cells, the index of the following element to write to ,and the next element to read
   private int occupiedCells = 0;
   private int writeIndex = 0;
   private int readIndex = 0;

   /**
    * returns the length of a buffer.
    *
    * @return the length of the buffer, buffer.length.
    */
   @Override
   public int length() {
      return buffer.length;
   }


   /**
    * Takes in a generated String to be put into a buffer
    * @param value to be put into buffer to be sent to the master
    *
    * */
   @Override //place string value into BufferFM
   public synchronized void blockingPutStr(String value)
           throws InterruptedException {

      //program will wait until there is a free cell to use
      while (occupiedCells == buffer.length) {
         //System.out.printf("Buffer is full. Master is now waiting. Calling: blockingPutStr%n");
         wait();
      }

      //new index
      buffer[readIndex] = value;


      writeIndex = (writeIndex + 1) % buffer.length; //update the index

      ++occupiedCells; // one more buffer cell is full

      notifyAll(); // notify threads waiting to read from buffer
   }

   // return value from buffer
   @Override
   public synchronized String blockingGetStr() throws InterruptedException {
      //where there are no occupied cells, wait until a buffer cell is filled.
      while (occupiedCells == 0) {
         //System.out.printf("Buffer is empty, Slave is now Waiting. Calling: blockingGetStr%n");
         wait();
      }

      String readValue = buffer[readIndex]; // read value from buffer and update index below

      readIndex = (readIndex + 1) % buffer.length;

      --occupiedCells;
      System.out.println("Roots Calculated from Slave: " + readValue);
      notifyAll();

      return readValue;
   }

   @Override
   public void blockingPut(TripletGenerator value) throws InterruptedException {
      //implementation is not required other than to satisfy Buffer interface
   }

   @Override
   public TripletGenerator blockingGet() throws InterruptedException {
      return null; //implementation is not required other than to satisfy Buffer interface
   }

}
