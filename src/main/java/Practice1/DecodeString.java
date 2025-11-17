package Practice1;
import java.util.Stack;


public class DecodeString {
    public static String decodeString(String s) {
/*
3[a2[c]]

 */
        Stack<Integer> numberStack = new Stack<>();
        Stack<String> stringStack = new Stack<>();

        String currentString = "";
        int number = 0;

        for (char ch : s.toCharArray()) {

            if (Character.isDigit(ch)) {
                number = number * 10 + (ch - '0');
            }
            else if (ch == '[') {
                numberStack.push(number);
                stringStack.push(currentString);
                number = 0;
                currentString = "";
            }
            else if (ch == ']') {
                StringBuilder temp = new StringBuilder(stringStack.pop());
                int repeatTimes = numberStack.pop();
                temp.append(currentString.repeat(repeatTimes));
                currentString = temp.toString();
            }
            else {
                currentString += ch;
            }

        }

        return currentString;
    }

    public static void main(String[] args) {
        String input = "3[a2[c]]";
        String result = decodeString(input);
        System.out.println(result);  // Output: accaccacc
    }
}

