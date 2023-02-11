import static java.lang.Math.abs;

public class Encryptor {

    /**
     * Encryptor constructor takes in an input string as an argument to be encrypted  */

    //alphabet but all uppercase letters
    final static char alphabet[] =
            {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H',
                    'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R',
                    'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

    /**
     * cypherEncrypt takes in an input, the array of keys and a starting index.
     * @param input is the text to be scrambled
     * @param start is the index that the encryption will begin at
     * @param keyStorage is the array of integers that will be used as keys. It will loop around
     *                   if the end of the array is reached.
     * */
    public static String cypherEncrypt(String input, int[] keyStorage, int start) {
        int currKey;
        char[] userInput = input.toUpperCase().toCharArray();

        StringBuilder output = new StringBuilder();

        for (int i = 0; i < userInput.length; i++) {
            int keyIndex = (start+i) % keyStorage.length;
            currKey = keyStorage[keyIndex];

            //j = current letter in alphabet
                for (int j = 0; j < alphabet.length; j++) {

                    if (userInput[i] == ' ') {
                        output.append(' ');
                        start--;
                        break;
                    }
                    if (userInput[i] == alphabet[j] && !(j + currKey >= alphabet.length)) {
                        output.append(alphabet[j + currKey]);
                    }
                    if (userInput[i] == alphabet[j] && (j + currKey) >= alphabet.length) {
                        output.append(alphabet[abs(alphabet.length - (j + currKey))]);
                    }
                }
            }
        return output.toString();
        }

}
