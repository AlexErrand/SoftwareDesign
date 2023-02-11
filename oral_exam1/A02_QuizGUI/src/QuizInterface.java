import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;
import javax.swing.Timer;

public class QuizInterface extends JFrame implements ActionListener {

    //text labels for questions and intro screen
    private final JLabel display;
    private final JLabel displayF;
    private final JLabel question1Text;
    private final JLabel question2Text;
    private final JLabel question3Text;
    private final JLabel question4Text;
    private final JLabel question5Text;
    private final Timer timer;
    public String countdownTimer = "60";

    private final ImageIcon background; //should not be local variables, should not be changeable
    private final ImageIcon backgroundAPlus;

    //java swing objects
    private final JButton start;
    private final JRadioButton optTrue1;
    private final JRadioButton optFalse1;
    private final ButtonGroup radioGroup;
    private final JComboBox<String> question2;
    private static final String[] question2Options = {"","Addition","Subtraction","Multiplication","Division"};
    private final JList<String> question3;
    private static final String[] numbers = {"","2.4", "2.5", "2.9", "3.9", "4.9", "3.5", "5.4", "2.6", "4.2", "5.5", "4.5", "9.2"};
    private final JCheckBox question4a;
    private final JCheckBox question4b;
    private final JCheckBox question4c;
    private final JCheckBox question4d;
    private final JComboBox<String> question5;
    private static final String[] question5Options = {"","Triangle","Square","Rectangle","Trapezoid"};
    private final JCheckBox agreement;
    private final JLabel finalGrading;
    private final JButton next;
    private final JButton result;
    public static int pagecounter;
    public static int correctAnswerCount;

    public QuizInterface() {

        getContentPane().setBackground(Color.white);
        setLayout(null);
        background = new ImageIcon(Objects.requireNonNull(getClass().getResource("StartingScreen.png")));
        backgroundAPlus = new ImageIcon(Objects.requireNonNull(getClass().getResource("aPlus.jpg")));

        //question text
        question1Text = new JLabel("Question 1: True or False, does 2.0 plus 2.1 equal 4.1?");
        question1Text.setBounds(120,0,800,600);
        add(question1Text);
        question2Text = new JLabel("Question 2: What do we use to add numbers together?");
        question2Text.setBounds(200,-400,1000,1000);
        add(question2Text);
        question3Text = new JLabel("Question 3: What is 6.2 - 3.3?");
        question3Text.setBounds(120,-200,800,600);
        add(question3Text);
        question4Text = new JLabel("Question 4: How many degrees are in a Circle?");
        question4Text.setBounds(200,-180,800,600);
        add(question4Text);
        question5Text = new JLabel("Question 5: What shape has 90 degrees?");
        question5Text.setBounds(200,-180,800,600);
        add(question5Text);

        //display for background
        display = new JLabel(background);
        display.setBounds(0,0,800,600);
        add(display);

        displayF = new JLabel(backgroundAPlus);
        displayF.setBounds(0,0,600,400);
        add(displayF);

        //Object implementation
        start = new JButton("Click to Begin!");
        start.setBounds(250,450,150,100);
        add(start);

        optTrue1 = new JRadioButton("True", false);
        optTrue1.setBounds(130,350,200,20);
        add(optTrue1);

        optFalse1 = new JRadioButton("False", false);
        optFalse1.setBounds(130,370,200,20);
        add(optFalse1);

        radioGroup = new ButtonGroup();
        radioGroup.add(optTrue1);
        radioGroup.add(optFalse1);

        question2 = new JComboBox<>(question2Options);
        question2.setMaximumRowCount(4);
        question2.setBounds(200,130,225,25);
        add(question2);

        question3 = new JList<>(numbers);
        question3.setVisibleRowCount(4);
        question3.setBounds(190,150, 50,200);
        question3.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        add(question3);

        question4a = new JCheckBox("180");
        question4b = new JCheckBox("90");
        question4c = new JCheckBox("45");
        question4d = new JCheckBox("360");
        question4a.setBounds(300,270,50,45);
        question4b.setBounds(300,240,70,45);
        question4c.setBounds(300,210,45,45);
        question4d.setBounds(300,180,50,45);
        add(question4a);
        add(question4b);
        add(question4c);
        add(question4d);

        question5 = new JComboBox<>(question5Options);
        question5.setMaximumRowCount(5);
        question5.setBounds(200,150,200,20);
        add(question5);

        agreement = new JCheckBox("By checking this box, you are confirming these are your own answers");
        agreement.setBounds(60,400,700,45);
        add(agreement);

        next = new JButton("Next Page");
        next.setBounds(500,450,150,100);
        add(next);
        next.addActionListener(this);

        result = new JButton("Final Result");
        result.setBounds(500,450,150,100);
        add(result);

        finalGrading = new JLabel(); //left blank as I will be obtaining this value with a function later
        finalGrading.setBounds(47,150,800,600);
        finalGrading.setFont(Font.getFont("Comic Sans"));
        add(finalGrading);

        //set the actionListners and visibility for all swing objects
        start.addActionListener(this);
        next.addActionListener(this);
        result.addActionListener(this);
        start.setVisible(true);
        display.setVisible(true);
        question1Text.setVisible(false);
        question2Text.setVisible(false);
        question3Text.setVisible(false);
        question4Text.setVisible(false);
        question5Text.setVisible(false);
        displayF.setVisible(false);
        next.setVisible(false);
        optTrue1.setVisible(false);
        optFalse1.setVisible(false);
        question2.setVisible(false);
        question3.setVisible(false);
        result.setVisible(false);
        question4a.setVisible(false);
        question4b.setVisible(false);
        question4c.setVisible(false);
        question4d.setVisible(false);
        question5.setVisible(false);
        agreement.setVisible(false);
        finalGrading.setVisible(false);
    }

    public int getPageCounter(){
        return pagecounter;
    }

    /**function to put correct quiz answers into a string,
     * checks for correct, incorrect, or unanswered questions.
     *
     * @return String of graded answers and final score
     *
     * */
    public String gradeQuizToString(){
        String[] gradedProblems = new String[5];
        String finalReport;
        if ((optTrue1.isSelected())){
            correctAnswerCount = correctAnswerCount +1;
            gradedProblems[0] = "Correct";
        }else if(optFalse1.isSelected()){
            gradedProblems[0] = "Incorrect";
        }else{
            gradedProblems[0] = "Unanswered";
        }

        if(Objects.requireNonNull(question2.getSelectedItem()).toString().equals("Addition")){
            correctAnswerCount = correctAnswerCount +1;
            gradedProblems[1] = "Correct";
        }else if (question2.getSelectedItem().toString().equals("Subtraction") || question2.getSelectedItem().toString().equals("Multiplication") || question2.getSelectedItem().toString().equals("Division")){
            gradedProblems[1] = "Incorrect";
        }else if (question2.getSelectedItem().toString().equals("")){
            gradedProblems[1] = "Unanswered";
        }

        if(question3.isSelectedIndex(3)){
            correctAnswerCount = correctAnswerCount +1;
            gradedProblems[2] = "Correct";
        }else if (question3.isSelectedIndex(1) || question3.isSelectedIndex(2) || question3.isSelectedIndex(4) || question3.isSelectedIndex(5) || question3.isSelectedIndex(6) || question3.isSelectedIndex(7) || question3.isSelectedIndex(8) || question3.isSelectedIndex(9) || question3.isSelectedIndex(10)){
            gradedProblems[2] = "Incorrect";
        }else if (question3.isSelectionEmpty()){
            gradedProblems[2] = "Unanswered";
        }

        if(question4d.isSelected() && !question4a.isSelected() && !question4b.isSelected() && !question4c.isSelected()){
            correctAnswerCount = correctAnswerCount +1;
            gradedProblems[3] = "Correct";
        }else if (question4a.isSelected() || question4b.isSelected() || question4c.isSelected()){
            gradedProblems[3] = "Incorrect";
        }else{
            gradedProblems[3] = "Unanswered";
        }
        if(Objects.requireNonNull(question5.getSelectedItem()).toString().equals("Triangle")){

            correctAnswerCount = correctAnswerCount +1;
            gradedProblems[4] = "Correct";
        }else if (question5.getSelectedItem().toString().equals("")){
            gradedProblems[4] = "Unanswered";
        }else{
            gradedProblems[4] = "Incorrect";
        }

        finalReport = correctAnswerCount + " out of 5 correct. Q1: " + gradedProblems[0] + " || Q2: " + gradedProblems[1] + "||  Q3: " + gradedProblems[2] + "|| Q4: " + gradedProblems[3] + "||  Q5: " + gradedProblems[4];
        return finalReport;
    }


    /**
     * use actionPerformed to manage the counter for the quiz questions and moving through them. Checks for the source of the action event and applying the corresponding action, such as moving to the next question
     *
     * @param e records action
     *
     */

    @Override
    public void actionPerformed(ActionEvent e) { //
        if ((e.getSource()) == start){
            pagecounter++;
            start.setVisible(false);
            display.setVisible(false);
            question1Text.setVisible(true);
            next.setVisible(true);
            optTrue1.setVisible(true);
            optFalse1.setVisible(true);

        }
        if ((e.getSource()) == next){ //removing visibility of unnecessary objects, but not deleting their values for later use
            pagecounter+=1;
            if(getPageCounter() == 3){
                question1Text.setVisible(false);
                optTrue1.setVisible(false);
                optFalse1.setVisible(false);
                question2Text.setVisible(true);
                question2.setVisible(true);

            }
            if(getPageCounter() == 5){
                question2Text.setVisible(false);
                question2.setVisible(false);
                question3.setVisible(true);
                question3Text.setVisible(true);

            }
            if(getPageCounter() == 7){
                question3Text.setVisible(false);
                question3.setVisible(false);
                question4a.setVisible(true);
                question4b.setVisible(true);
                question4c.setVisible(true);
                question4d.setVisible(true);
                question4Text.setVisible(true);
            }
            if(getPageCounter() == 9){
                question4Text.setVisible(false);
                next.setVisible(false);
                question4a.setVisible(false);
                question4b.setVisible(false);
                question4c.setVisible(false);
                question4d.setVisible(false);
                question5.setVisible(true);
                agreement.setVisible(true);
                result.setVisible(true);
                question5Text.setVisible(true);
            }
        }

        if (e.getSource() == result && agreement.isSelected()){ //checks answers and outputs them through a JLabel finalGrading.
            question5.setVisible(false);                        //If all answers are correct , the student gets a surprise at the end of the exam.
            agreement.setVisible(false);
            result.setVisible(false);
            question5Text.setVisible(false);
            finalGrading.setText(gradeQuizToString());
            finalGrading.setVisible(true);
            if (correctAnswerCount == 5){
                displayF.setVisible(true);
            }

            }
        }
    }
