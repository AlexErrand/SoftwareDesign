import javax.swing.JFrame;
//Note: for all classes besides CharacterConversion, I based my code off of the code we learned in class and
//from the textbook
public class ServerTest
{
    public static void main(String[] args)
    {
        Server application = new Server();
        application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        application.runServer();
    }
}