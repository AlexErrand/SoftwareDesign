/**
 * This class operates as the interface that specifies methods called by the master and slave classes.
 * "FM" is referring to "From Master"
 *
 * */

public interface Buffer {
   /**
    * Takes in a generated String to be put into a buffer
    * @param output to be put into buffer to be sent to the master
    *
    * */
   public void blockingPutStr(String output) throws InterruptedException;

   /**
    * Takes out a string of roots from a buffer
    * @return a String of roots from the buffer
    *
    * */
   public String blockingGetStr() throws InterruptedException;


   /**
    * Takes in a generated Triplet to be put into a buffer
    * @param value to be put into buffer for conversion
    *
    * */
   public void blockingPut(TripletGenerator value) throws InterruptedException;


   /**
    * Takes out a generated Triplet from a buffer
    * @return TripletGenerator from the buffer
    *
    * */
   public TripletGenerator blockingGet() throws InterruptedException;



   int length();



}


/**************************************************************************
 * (C) Copyright 1992-2015 by Deitel & Associates, Inc. and               *
 * Pearson Education, Inc. All Rights Reserved.                           *
 *                                                                        *
 * DISCLAIMER: The authors and publisher of this book have used their     *
 * best efforts in preparing the book. These efforts include the          *
 * development, research, and testing of the theories and programs        *
 * to determine their effectiveness. The authors and publisher make       *
 * no warranty of any kind, expressed or implied, with regard to these    *
 * programs or to the documentation contained in these books. The authors *
 * and publisher shall not be liable in any event for incidental or       *
 * consequential damages in connection with, or arising out of, the       *
 * furnishing, performance, or use of these programs.                     *
 *************************************************************************/