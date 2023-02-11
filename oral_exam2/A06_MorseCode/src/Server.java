import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.awt.BorderLayout;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
//Note: for all classes besides CharacterConversion, I based my code off of the code we learned in class and
//from the textbook
public class Server extends JFrame
{
    private JTextArea displayArea;
    private ObjectOutputStream output;
    private ObjectInputStream input;
    private ServerSocket server;
    private Socket connection;



    // set up GUI
    /**
     * Server constructor used to set up JFrame elements
     * */
    public Server()
    {
        super("Server - Character Converter - Morse and English");
        //jframe operations
        displayArea = new JTextArea();
        add(new JScrollPane(displayArea), BorderLayout.CENTER);

        setSize(300, 150); // set size of window
        setVisible(true); // show window

    }

    /**
     *  Starts the server and creates a ServerSocket to connect to the client
     *  Also runs waitForConnection(), getStreams() and processConnection() while waiting for a message from the Client
     *
     */
    public void runServer()
    {
        try // set up server to receive and process connections
        {
            server = new ServerSocket(12345, 100); // create ServerSocket to connect with client

            while (true)
            {
                try
                {
                    waitForConnection(); // wait for a connection
                    getStreams(); // get input & output streams
                    processConnection(); // process connection
                }
                catch (EOFException eofException)
                {
                    displayMessage("\nServer terminated connection");
                }
                finally
                {
                    closeConnection(); //  close the  connection

                }
            }
        }
        catch (IOException ioException)
        {
            ioException.printStackTrace(); //prints the IOException if one occurs
        }
    }

    /**
     * waitForConnection Waits for a new connection from a soon-to-be made client
     * */
    private void waitForConnection() throws IOException
    {
        displayMessage("Waiting for connection\n");
        connection = server.accept();
        displayMessage("Connection received from: " +
                connection.getInetAddress().getHostName());
    }

    /**
     * getStreams is used to manage the streams of text from the client and server, sending/receiving to the socket
     * based on what information is being sent/received.
     * @throws IOException if an error occurs with the IO operations
     * */
    private void getStreams() throws IOException
    {

        output = new ObjectOutputStream(connection.getOutputStream());
        output.flush();


        input = new ObjectInputStream(connection.getInputStream());

        displayMessage("\nGot I/O streams\n");
    }

    /**
     * processConnection() attemps to process a specific message being sent from the client and tries to apply
     * the conversion logic I have implemented below. I use a pattern to help determine what kind of conversion
     * will be necessary, and then send the data accordingly.
     * @throws IOException if an error occurs with the IO operations
     * */

    private void processConnection() throws IOException {
        String message = "Connection successful";
        sendData(message);
        CharacterConversion morse = new CharacterConversion("");

    do
    {
        try
        {
            //logic to choose which conversion will be selected, will move to a method in CharacterConversion later
            String morseMessage = (String) input.readObject();
            displayMessage("\nCONVERTING FROM CLIENT>>> " + morseMessage);
            String output;
            morse.setConvert(morseMessage);
            Pattern pattern = Pattern.compile("[a-zA-Z0-9]");
            Matcher matchingChar = pattern.matcher(morse.getConvert());
            boolean patternValidator = matchingChar.find();

            if (patternValidator) {
                output = CharacterConversion.convertE2M(morse.getConvert());
                System.out.println(output);
                sendData(output);
            } else {
                output = CharacterConversion.convertM2E(morse.getConvert());
                System.out.println(output);
                sendData(output);
            }

            displayMessage("\nSENDING BACK TO CLIENT...");
        } catch (ClassNotFoundException classNotFoundException) {
            displayMessage("\nUNKNOWN OBJECT - ERROR");
        }
    } while (!message.equals("UNKNOWN OBJECT - ERROR"));
    }

    /**
     * closeConnection() ends the connection between the server and client
     * @throws IOException if an error occurs with the IO operations
     * */
    private void closeConnection()
    {
        displayMessage("\nTerminating connection\n");

        try
        {
            output.close();
            input.close();
            connection.close();
        }
        catch (IOException ioException)
        {
            ioException.printStackTrace();
        }
    }

    /**
     * sendData() writes to the output and sends the data to the client
     * @param message to be sent
     * @throws IOException if an error occurs with the IO operations
     * */
    private void sendData(String message)
    {
        try
        {
            output.writeObject("SERVER>>> " + message);
            output.flush();
            displayMessage("\nSERVER>>> " + message);
        }
        catch (IOException ioException)
        {
            displayArea.append("\nError writing object");
        }
    }
    /**
     * isplayMessage uses a final String to determine what message to display
     * @param messageToDisplay determines message to be displayed
     * */
    private void displayMessage(final String messageToDisplay)
    {
        SwingUtilities.invokeLater(
                new Runnable() //anonymous inner class, Runnable means instances are intended to be executed by a thread
                {
                    public void run()
                    {
                        displayArea.append(messageToDisplay);
                    }
                }
        );
    }


}