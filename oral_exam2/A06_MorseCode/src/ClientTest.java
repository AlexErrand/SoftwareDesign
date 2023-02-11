import javax.swing.JFrame;

//Note: for all classes besides CharacterConversion, I based my code off of the code we learned in class and
//from the textbook
public class ClientTest
{
    public static void main(String[] args)
    {
        Client application;
        if (args.length == 0)
            application = new Client("127.0.0.1"); //setting ip address
        else
            application = new Client(args[0]);

        application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        application.runClient();
    }
}

