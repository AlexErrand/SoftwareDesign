import javax.swing.JFrame;
public class QuizTest {


    public static void main(String[] args)
    {
        QuizInterface quiz = new QuizInterface();
        quiz.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        quiz.setSize(800, 600);
        quiz.setVisible(true);
        quiz.setTitle("Math Quiz Unit 1.1");

    }
}
