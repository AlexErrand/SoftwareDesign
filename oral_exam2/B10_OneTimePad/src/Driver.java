import javafx.util.Pair;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

/**
 * The driver of the OneTimePad
 */
public class Driver {


    public static void main(String[] args) {
        //ask for files from the user to prevent constant annoying prompt for file
        Scanner OS = new Scanner(System.in);
        System.out.println("Please initialize the file path for the key location (it is ok if the file does not exist yet, it will be created if it doesn't exist):");
        String keyFileLocation = OS.nextLine();
        System.out.println("Please initialize the file path for the encrypt location(it is ok if the file does not exist yet, it will be created if it doesn't exist):");
        String encFileLocation = OS.nextLine();

        System.out.println("Welcome to the OneTimePad!");

        while (true) {

            System.out.println("/////////////Menu////////////////");
            System.out.println("1. Create a new Key");
            System.out.println("2. Encrypt a message");
            System.out.println("3. Decrypt a message");
            System.out.println("4. Update your Key file locations");
            System.out.println("5. Update your encryption file location");
            System.out.println("6. Close the program");
            System.out.println("///////////////////////////////// Input Command Below:");

            int command = OS.nextInt();

            switch (command) {
                case 1 -> { //use keygenerator to make a key file
                    try {
                        File myObj = new File(keyFileLocation);
                        //keyFileLocation = myObj.getAbsolutePath();
                        if (myObj.createNewFile()) {
                            System.out.println("File created: " + myObj.getName());
                        } else {
                            System.out.println("File already exists.");
                        }
                    } catch (IOException e) {
                        System.out.println("An error occurred.");
                        e.printStackTrace();
                    }
                    try {
                        //checking if the key is valid
                        int x = 0;
                        boolean isValidLength = true;
                        while (isValidLength) {

                            System.out.println("Please write how long you want the key to be");
                            x = OS.nextInt();
                            if (x > 0) {
                                isValidLength = false;
                            } else {
                                System.out.println("Key length must be greater than zero, please type a new key:");
                                x = 0;
                            }
                        }
                        //checking for index and it that is valid
                        int[] keyArray = KeyGenerator.keyGen(x);
                        System.out.println(Arrays.toString(keyArray));
                        boolean validKey = true;
                        boolean isIndexValid = true;
                        while (validKey && isIndexValid) {
                            System.out.println("Please Select the key index to start from:");
                            int y = OS.nextInt();
                            if (y > 0) {
                                isIndexValid = false;
                            }
                            if (KeyGenerator.keyIndexPick(y, keyArray)) {//loop to prevent a user from moving further if not false
                                FileWriter myWriter = new FileWriter(keyFileLocation);
                                myWriter.write(y + "\n" + Arrays.toString(keyArray));
                                myWriter.close();
                                System.out.println("Successfully wrote to the file.");
                                validKey = false;

                            } else {
                                System.out.println("ERROR: key not in file. Please Select a valid key:");
                            }
                        }


                    } catch (IOException e) {
                        System.out.println("An error occurred.");
                        e.printStackTrace();

                    }
                }

                case 2 -> {
                    int keyIndex;
                    String arrayOfkeys;

                    System.out.println("please type the phrase you would like to encrypt");
                    OS.nextLine();
                    String userInput;
                    userInput = OS.nextLine();

                    try {
                        //System.out.println("Please write the file directory of your key file:");
                        //String keyDirectory = OS.nextLine();
                        //ask for file name "C:\\Users\\maste\\Desktop\\fileSWDReader\\SWDKey.txt"
                        //Scans the file for a key file
                        File file = new File(keyFileLocation);
                        Scanner scan = new Scanner(file);

                        keyIndex = Integer.parseInt(scan.nextLine());
                        arrayOfkeys = scan.nextLine();
                        //takes in the two lines of the file to separate the index from the key
                        //System.out.println(keyIndex);
                        //System.out.println(arrayOfkeys);
                        scan.close();

                        assert arrayOfkeys != null;
                        int[] keyValues = KeyGenerator.stringToArray(arrayOfkeys); //convert keys back to an array
                        String encryptedText = Encryptor.cypherEncrypt(userInput, keyValues, keyIndex);


                        FileWriter encryptWriter = new FileWriter(encFileLocation);
                        //"C:\\Users\\maste\\Desktop\\fileSWDReader\\EncryptedText.txt"
                        //System.out.println(Decryptor.cypherDecrypt((Encryptor.cypherEncrypt(userInput,keyValues,keyIndex)), keyValues, keyIndex));
                        encryptWriter.write(keyIndex + "\n" + encryptedText); //write new file based on encrypted text
                        encryptWriter.close();

                        System.out.println("successfully wrote to encrypted file");

                    } catch (IOException e) {
                        System.out.println("An error occurred.");
                        e.printStackTrace();
                    }

                }
                case 3 -> {
                    int currKeyIndex;
                    int updateKeyIndex;
                    int keyIndexWithCypher;
                    String arrayOfKeysD;
                    String cypher;
                    String decryptedText;


                    try {
                        File encryptedText = new File(encFileLocation);
                        File keyFile = new File(keyFileLocation);
                        Scanner obtainKeys = new Scanner(keyFile);
                        Scanner obtainCypher = new Scanner(encryptedText);//locate both files and obtain the correct keys

                        currKeyIndex = Integer.parseInt(obtainKeys.nextLine());//obtain key and cypher information based on file input
                        arrayOfKeysD = obtainKeys.nextLine();
                        keyIndexWithCypher = Integer.parseInt(obtainCypher.nextLine());
                        cypher = obtainCypher.nextLine();

                        int[] arrWKeys = KeyGenerator.stringToArray(arrayOfKeysD);

                        Pair<String, Integer> decryptedTextAndKey = (Decryptor.cypherDecrypt(cypher, arrWKeys, keyIndexWithCypher)); //put decrypted cypher into a string and integer pair with the new key
                        decryptedText = decryptedTextAndKey.getKey();
                        updateKeyIndex = decryptedTextAndKey.getValue();

                        System.out.println("The decoded message was: " + decryptedText);
                        System.out.println("the new starting index of the key is " + updateKeyIndex);

                        FileWriter fileOut = new FileWriter(keyFile);
                        fileOut.write(updateKeyIndex + "\n" + arrayOfKeysD); //update the key
                        fileOut.close();

                        System.out.println("successfully updated key file");

                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }


                }
                case 4 -> {
                    System.out.println("Please update your Key File location: "); //update file location
                    keyFileLocation = OS.nextLine();

                }
                case 5 -> {
                    System.out.println("Please update your Encryption file location:");//update file location
                    encFileLocation = OS.nextLine();
                }
                case 6 -> {
                    System.out.println("Thank you for using the OneTimePad");//close the program
                    System.exit(0);
                }
            }
        }
    }
}





