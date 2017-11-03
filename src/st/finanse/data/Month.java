package st.finanse.data;

public class Month {
    public Month(int year, int month) {
        this.year = year;
        this.month = month;
    }

    public Month(int year, String month) {
        this.year = year;
        int res = -1;
        for (int i = 1; i <= 12; i++) {
            if (reloadMonthName(i, false).equalsIgnoreCase(month)) res = i;
        }
        if (res == -1) throw new RuntimeException();
        this.month = res;
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public String getMonthName() {
        if (monthName == null) {
            monthName = reloadMonthName(month, false);
        }
        return monthName;
    }

    public String getMonthAccusative() {
        if (monthNameAcc == null) {
            monthNameAcc = reloadMonthName(month, true);
        }
        return monthNameAcc;
    }

    private String reloadMonthName(int m, boolean acc) {
        switch (m) {
            case  1: return acc ? "stycznia" : "Styczeń";
            case  2: return acc ? "lutego" : "Luty";
            case  3: return acc ? "marca" : "Marzec";
            case  4: return acc ? "kwietnia" : "Kwiecień";
            case  5: return acc ? "maja" : "Maj";
            case  6: return acc ? "czerwca" : "Czerwiec";
            case  7: return acc ? "lipca" : "Lipiec";
            case  8: return acc ? "sierpnia" : "Sierpień";
            case  9: return acc ? "września" : "Wrzesień";
            case 10: return acc ? "października" : "Październik";
            case 11: return acc ? "listopada" : "Listopad";
            case 12: return acc ? "grudnia" : "Grudzień";
            default: return "[null]";
        }
    }

    public int getMaxDays() {
        if (maxDays == -1) {
            maxDays = reloadMaxDays();
        }
        return maxDays;
    }

    private int reloadMaxDays() {
        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                return 31;
            case 4:
            case 6:
            case 9:
            case 11:
                return 30;
            case 2:
                return isLeapYear() ? 29 : 28;
            default:
                return -1;
        }
    }

    private boolean isLeapYear() {
        if (year % 100 == 0) {
            return year % 400 == 0;
        }
        else {
            return year % 4 == 0;
        }
    }

    @Override
    public String toString() {
        return getMonthName() + " " + year;
    }

    private final int year;
    private final int month;
    private String monthName = null;
    private String monthNameAcc = null;
    private int maxDays = -1;
}
