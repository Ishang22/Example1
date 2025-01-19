import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeMap;

public class AirtelRound2 {

    final private TreeMap<Integer, Integer> bookings;

    AirtelRound2() {
        bookings = new TreeMap<>();
    }
    /*
    Number of Meeting can user attend
     */
    public static int meetingAttend3(int[] startDate, int[] endDate) {
        ArrayList<int[]> meeting = new ArrayList<>();
        int size = 1;

        for (int i = 0; i < startDate.length; i++) {
            meeting.add(new int[]{startDate[i], endDate[i]});
        }

        meeting.sort((a, b) -> a[0] - b[0]);

        for (int i = 1; i < meeting.size(); i++) {
            if (meeting.get(i - 1)[1] > meeting.get(i)[0]) {
                System.out.println("overlap");
            } else {
                size++;
            }
        }

        return size;
    }
    /*
    How many platforms required
    */
    static int findPlatform(int[] arr, int[] dep, int n) {
        Arrays.sort(arr);
        Arrays.sort(dep);

        int plat_needed = 1, result = 1;
        int i = 1, j = 0;

        while (i < n && j < n) {
            if (arr[i] <= dep[j]) {
                plat_needed++;
                i++;
            } else if (arr[i] > dep[j]) {
                plat_needed--;
                j++;
            }

            if (plat_needed > result) {
                result = plat_needed;
            }

        }

        return result;
    }
    /*
    How many rooms required
     */
    public static int meetingAttend2(int[] arr, int[] dep) {
        int n = arr.length;

        Arrays.sort(arr);
        Arrays.sort(dep);

        int plat_needed = 1, result = 1;

        int i = 1, j = 0;

        //    a
        //    d
        while (i < n && j < n) {
            if (arr[i] < dep[j]) {
                plat_needed++;
                i++;
            } else if (arr[i] >= dep[j]) {
                plat_needed--;
                j++;
            }

            if (plat_needed > result) {
                result = plat_needed;
            }

        }

        return result;
    }

    public boolean book(int start, int end) {
        //. log(n)
        Integer prevBooking = bookings.floorKey(start);

        Integer nextBooking = bookings.ceilingKey(start);

        if (
                (prevBooking == null || bookings.get(prevBooking) <= start)
                        &&
                (nextBooking == null || end <= nextBooking)
        ) {
            bookings.put(start, end);
            return true;
        }
        return false;
    }

    public static void main(String[] args) {

        System.out.println(meetingAttend3(new int[]{1, 2, 6, 8, 9, 12, 5}, new int[]{3, 4, 8, 12, 12, 13, 6}));//5

        System.out.println(meetingAttend2(new int[]{1, 2, 6, 8, 9, 12, 5}, new int[]{3, 4, 8, 12, 12, 13, 6}));//2

        //1, 2, 6, 8,9,12,5
        //3, 4, 8, 12,12,13,6
    }
}
