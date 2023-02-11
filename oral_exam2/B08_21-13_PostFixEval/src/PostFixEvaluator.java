import java.util.Scanner;

public class PostFixEvaluator {
    public static void main(String[] args) {

        Scanner OS = new Scanner(System.in);

        while (true) {

            System.out.println("Welcome to the PostFixEvaluator!");
            System.out.println("Select a choice below:");
            System.out.println("A. Evaluate a postfix expression");
            System.out.println("B. Close the program");

            String command = OS.nextLine();

            switch (command) {
                case "A" -> {
                    System.out.println("Please type in a postfix expression:");
                    StringBuffer toBeEvaluated = new StringBuffer(OS.nextLine());
                    PostFixMethod userInput = new PostFixMethod(toBeEvaluated);
                    System.out.println("Evaluation complete, answer is: " + userInput.evaluatePostfixExpression(userInput.getPostFixVal()));
                }
                case "B"->{
                    System.out.println("Goodbye");
                    System.exit(0);
                }
            }

}}}
