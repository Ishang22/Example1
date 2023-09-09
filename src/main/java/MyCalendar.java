import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;


class MyCalendar {
    static ArrayList<int[]> arl = new ArrayList<int[]>();

    public MyCalendar() {

    }

    public static boolean book(int start, int end) {

        for (int i = 0; i < arl.size(); i++) {
            if (start < arl.get(i)[1] && arl.get(i)[0] < end) {
                return false;
            }
        }

        int[] inArray = new int[2];
        inArray[0] = start;
        inArray[1] = end;
        arl.add(inArray);

        return true;
    }

    public static void main(String args[]) {

        Scanner sc = new Scanner(System.in);

        // String input
        String name = sc.nextLine();

        System.out.println("========="+Integer.parseInt(name));

        System.out.println("===MyCalendar=1==" + MyCalendar.book(3, 8));
        System.out.println("===MyCalendar==2=" + MyCalendar.book(3, 8));

        Map<String, String> treeMap = new TreeMap<>();

        treeMap.put("22", "11");
        treeMap.put("11", "22");
        System.out.println("=======" + treeMap);
    }
}

/**
 * Your MyCalendar object will be instantiated and called as such:
 * MyCalendar obj = new MyCalendar();
 * boolean param_1 = obj.book(start,end);
 */