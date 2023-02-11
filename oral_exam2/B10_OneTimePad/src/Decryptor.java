import javafx.util.Pair;

public class Decryptor {
    //not enough fields/objects to warrant a whole class for
    private static String input;

    public Decryptor(String input) {
            this.input = input;
        }

    public static String getInput() {
            return input;
        }

    public void setInput(String s) {
            input = input;
        }

    final static char[] alphabet =
            {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H',
                    'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R',
                    'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};


    /**
     * cypherDecrypt takes in the same variables as cypherEncrypt, however it instead returns a Pair<String, Integer>
     *     to be able to return the correct index that the key array has left off on to write it to the right file in
     *     the Driver.
     *     @param input is the text to be unscrambled
     *     @param start is the index that the decryption will begin at
     *     @param keys is the array of integers that will be used as keys. It will loop around
     *      *                   if the end of the array is reached.
     * */
    public static Pair<String, Integer> cypherDecrypt(String input, int[] keys, int start){
        int keyIndex = 0;
        int currKey;
        char[] userInput = input.toUpperCase().toCharArray();
        StringBuilder output = new StringBuilder();
        for (int i=0; i<userInput.length; i++) {
            keyIndex = (start+i) % keys.length;
            currKey = keys[keyIndex];
            for (int j = 0; j < alphabet.length; j++) {
                if (userInput[i] == ' '){
                    output.append(' ');
                    start--;
                    break;
                }
                if (userInput[i] == alphabet[j] && (j - currKey >= 0)) {
                    output.append(alphabet[(j-currKey)]);
                }
                if(userInput[i] == alphabet[j] && (j-currKey) < 0){
                    output.append(alphabet[(alphabet.length+(j-currKey))]);
                }

            }
        }
        return new Pair<>(output.toString(), keyIndex);
    }
}
