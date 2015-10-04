package logic.evaluation;

public class EvaluationHelper {
    public static boolean isNonnegativeNumber(String text){
        if (text.matches("\\d+")){
            return true;
        } else {
            return false;
        }
    }

    public static boolean isNumber(String text){
        if (text.matches("-?\\d+")){
            return true;
        } else {
            return false;
        }
    }

    // check operation symbol according to
    // operation ::= '+' | '-' | '*' | '/'
    public static boolean isOperator(char ch) {
        if ((ch == '*') || (ch == '/') || (ch == '+') || (ch == '-') || (ch == '\''))
            return true;
        else
            return false;
    }
}
