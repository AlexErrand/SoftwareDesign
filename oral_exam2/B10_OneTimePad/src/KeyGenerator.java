import java.util.Arrays;
import java.util.Random;

/**
 * KeyGenerator takes in an input to generate a certain number of keys for the
 * encryptor and decryptor to use.
 *
 * */
public class KeyGenerator {

    private final int input;
    /**
     * KeyGenerator constructor takes in an input to generate a certain number of keys
     * @param input is the number of keys to generate to be used in the scrambling
     * */
    public KeyGenerator(int input){
        this.input = input;
    }

    /**
     * KeyGen takes in an input to generate a certain number of keys
     * @param input is the number of keys to generate to be used in the scrambling
     * @return int[] of newly generated keys
     * */
    public static int[] keyGen(int input) { //create array of keys based on length of cypher
        int[] keyStorage = new int[input];
        for (int x = 0; x < input; x++) {
            keyStorage[x] = new Random().nextInt(25);
        }
        //System.out.println(Arrays.toString(keyStorage));
        return keyStorage;
    }


    /**
     * KeyGen takes in an input to generate a certain number of keys
     * @param input is the number of keys to generate to be used in the scrambling
     * @param arrayToCheck is the array of keys I am checking
     * @return boolean of whether or not the key is present
     * */
    public static boolean keyIndexPick(int input, int[] arrayToCheck) {
        return (input >= 0) && (input < arrayToCheck.length);
    }

    /**
     * stringToArray converts a string from the text file to an array of integers
     * @param input from text file
     * @return array of int[] of the numbers in that specific line
     * */
    public static int[] stringToArray(String input){
    //based this off of this stackoverflow query: https://stackoverflow.com/questions/71449855/extracting-an-array-of-integers-from-a-string-using-java-streams
        return Arrays.stream(input.substring(1, input.length()-1).split(","))
                .map(String::trim).mapToInt(Integer::parseInt).toArray();
    }
}
