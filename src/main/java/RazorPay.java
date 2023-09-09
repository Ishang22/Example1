
public class RazorPay {

    public static boolean isLeap(int year) {
        if (year % 4 == 0) {
            if (year % 100 == 0) {
                if (year % 400 == 0)
                    return true;
                else
                    return false;
            } else
                return true;
        } else
            return false;
    }

    public static int getNumberOfDaysIn(int mm, int yy) {
        if (mm == 2) {
            if (isLeap(yy)) {
                return 29;
            }
            return 28;
        } else if (mm == 1 || mm == 3 || mm == 5 || mm == 7 || mm == 8 || mm == 10 || mm == 12) {
            return 31;
        } else {
            return 30;
        }
    }

    public static int getNumberOfDaysInYear(int yy) {
        if (isLeap(yy)) {
            return 366;
        }
        return 365;
    }

    public static void dateIncreamenter(int dd, int mm, int yy, int daysToIncreament) {
        //366 -leap
        //365-normal

        int daysLeftInCurrentMonth = getNumberOfDaysIn(mm, yy) - dd;

        if (daysLeftInCurrentMonth >= daysToIncreament) {
            System.out.println("date :" + (dd+daysToIncreament) + "    Month :" + mm + "    Year :" + yy);
            return;
        } else {
            daysToIncreament = daysToIncreament - daysLeftInCurrentMonth;
        }


        while (daysToIncreament > 0) {


            //Month Increament
            if (mm < 12) {
                mm++;
            } else {
                yy++;
                mm=1;
            }

            int numberOfDays = getNumberOfDaysIn(mm, yy);

            if (numberOfDays >= daysToIncreament) {
                System.out.println("date :" + daysToIncreament + "    Month :" + mm + "    Year :" + yy);
                break;
            } else {
                daysToIncreament = daysToIncreament - numberOfDays;
            }


        }


    }

    public static void main(String args[]) {
        dateIncreamenter(23, 2, 2023, 1);
        dateIncreamenter(23, 2, 2023, 5);
        dateIncreamenter(23, 2, 2023, 6);
        dateIncreamenter(23, 2, 2023, 50);
        dateIncreamenter(23, 2, 2023, 100);

        dateIncreamenter(23, 2, 2023, 1000);
        dateIncreamenter(23, 2, 2023, 10000);
    }
}
