package Practice1.Arrays;
/*
Given a positive integer, return its corresponding column title as appears in an Excel sheet.
For Example:
    1 -> A
    2 -> B
    3 -> C
    ...
    26 ->  Z
    27 ->  AA
    28 ->  AB
    29 ->  AC
    30  -> AD
    31  -> AE
    ...
Input: 28 | Output: "AB"
Input: 701 | Output: "ZY"
Input: 12345678 | Output: "ZZJUT"

Helper for code:
["A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"]
 */
public class TitleExcelSheet {
    public static void main(String args[])
    {
        int columnNumber = 12345678;
        StringBuilder result = new StringBuilder();
        while (columnNumber > 0) {
            columnNumber--;
            result.append((char) (columnNumber % 26 + 'A'));
            columnNumber /= 26;
        }
        System.out.println(result.reverse().toString());
    }
}
