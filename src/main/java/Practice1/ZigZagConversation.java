package Practice1;

import java.util.ArrayList;
import java.util.List;

public class ZigZagConversation {


    public String convert(String s, int numRows) {
        if (numRows == 1) return s;

        List<StringBuilder> ans = new ArrayList<>();

        for (int i = 0; i < numRows; i++) {
            ans.add(new StringBuilder());
        }

        boolean flag = false;
        int i = 0;

/*
0       0      0
1   1   1   1
2       2
 */
        for (char ch : s.toCharArray()) {
            ans.get(i).append(ch);

            if (i == 0 || i == numRows - 1) {
                flag = !flag; // change direction
            }

            if (flag) {
                i++;
            } else {
                i--;
            }
        }

        StringBuilder res = new StringBuilder();
        for (StringBuilder row : ans) {
            res.append(row);
        }

        return res.toString();
    }


}
