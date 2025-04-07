package Practice1;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * Description:<br>
 * Date: 21/03/25-6:14 pm
 *
 * @author ishangarg
 * @since
 */
//Given a string s, find the length of the longest substring without duplicate characters.

 // Index    which is more good
//select * from T where A = ? and B = ?
//select * from T where B = ? and A = ?
public class Example5678 {
    //input - "zxyzxyz" , output = 3
    Stack<Integer> s1 = new Stack<>();
    Stack<Integer> minStack = new Stack<>();
    int minVariable = Integer.MAX_VALUE;

    public static void main(String args[]) {
        String input = "zxyzxyz";
        char[] input1 = input.toCharArray();
        Map<Character, Integer> map = new HashMap<>();
        int max = 0;
        int i = 0, j = 0;

        while (i < input.length() && j < input.length()) {
            if (map.containsKey(input1[j])) {
                i = j;
                map.remove(input1[j]);
            } else {
                map.put(input1[j], 1);
                j++;
                max = Math.max(max, j - i + 1);
            }
        }

        System.out.println("max length is max {}" + max);

    }

    //41 31 1 2 13 15
    public void push(int i) {
        s1.push(i);
        if (i < minVariable) {
            minVariable = i;
        }
        minStack.push(minVariable);
    }

    public int pop() {
        int removeelement = s1.pop();
        minVariable = minStack.pop();
        return removeelement;
    }

    public int findMin() {
        return minStack.peek();
    }

}
