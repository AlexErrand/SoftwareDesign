import java.util.Stack;
import java.lang.Math;

public class PostFixMethod {

    private final StringBuffer postFixVal;

    public StringBuffer getPostFixVal() {
        return postFixVal;
    }

    /**
     * PostFixMethod constructor takes in a StringBuffer as an argument in order for it to be
     * used later in evaluatePostfoxExpression
     *
     * */
    public PostFixMethod(StringBuffer postFixVal){
        this.postFixVal = postFixVal;
    }

    /**
     * evaluatePostfixExpression() takes in a StringBuffer to be used to calculate the final
     * value of the PostFixExpression
     * @param string , also known as the expression to be evaluated
     * @return toBeAdded, which is used for pushing new values into the stack
     * */
    public static int evaluatePostfixExpression(StringBuffer string){
        char x;
        Stack<Integer> postfix = new Stack<>();
        string.append(')');
        int pop1, pop2; //variables I will use for the operations
        int toBeAdded = 0; //"Temporary" variable used for storing the value to be pushed
        for(int i = 0; string.charAt(i)!= ')'; i++){
            if(Character.isDigit(string.charAt(i))){ //"6 2 + 5 * 8 4 /-" example case should equal 38
                toBeAdded = string.charAt(i) - '0';
                postfix.push(toBeAdded);//adds new digit to queue
                //System.out.println("push case = " + toBeAdded);
            } else if (string.charAt(i) == '^' || string.charAt(i) == '+' || string.charAt(i) == '-' || string.charAt(i) == '*' || string.charAt(i) == '/' || string.charAt(i) == '%') {
               x = string.charAt(i);
               pop1 = postfix.pop();//processes numbers greater than 9
               pop2 = postfix.pop();
               toBeAdded = calculate(x,pop2,pop1);
               postfix.push(toBeAdded);
               //System.out.println("operation applied = " + toBeAdded);
            }
        }
        return toBeAdded; //returns the final value of the stack
    }

    /**
     * evaluatePostfixExpression() takes in a StringBuffer to be used to calculate the final
     * value of the PostFixExpression
     * @param mathOperator , which selects the operand to use based on its input
     * @param num1 , first number to use the operand on
     * @param num2 , second number to use the operand on
     * @return toBeAdded, which is used for pushing new values into the stack
     * */
    public static int calculate (char mathOperator, int num1, int num2){ //performs different operations depending on mathOperator input
        if (Character.valueOf(mathOperator).equals('^')){
            return (int) Math.pow(num1,num2);
        }
        if (Character.valueOf(mathOperator).equals('+')){
            return num1 + num2;
        }
        if (Character.valueOf(mathOperator).equals('-')){
            return num1 - num2;
        }
        if (Character.valueOf(mathOperator).equals('*')){
            return num1 * num2;
        }
        if (Character.valueOf(mathOperator).equals('/')) {
            return num1 / num2;
        }
        if (Character.valueOf(mathOperator).equals('%')) {
            return num1 % num2;
        }
        return 0;
    }
}
