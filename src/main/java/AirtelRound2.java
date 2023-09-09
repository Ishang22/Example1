import java.util.ArrayList;
import java.util.Collections;

public class AirtelRound2 {


    public static int meetingAttend(int startDate[], int endDate[]) {
        ArrayList<int[]> meeting = new ArrayList<>();

        for (int i = 0; i < startDate.length; i++) {
            meeting.add(new int[]{startDate[i], endDate[i]});
        }


        meeting.sort((a, b) -> a[1] - b[1]);
        int endTime = meeting.get(0)[1], meetingAttend = 1;


        for (int i = 1; i < meeting.size(); i++) {
            if (meeting.get(i)[0] >= endTime) {
                endTime = meeting.get(i)[1];
                meetingAttend++;
            }
        }

        return meetingAttend;


    }

    public static void main(String[] args) {
        System.out.println(meetingAttend(new int[]{1, 2, 6, 8,9,12,5}, new int[]{3, 4, 8, 12,12,13,6}));
        //1, 2, 6, 8,9,12,5
        //3, 4, 8, 12,12,13,6

    }
}
